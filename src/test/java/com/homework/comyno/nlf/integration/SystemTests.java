package com.homework.comyno.nlf.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.homework.comyno.nlf.NlfApplication;
import com.homework.comyno.nlf.api.BookBaseInfo;
import com.homework.comyno.nlf.api.LoanInfo;
import com.homework.comyno.nlf.api.LoanRequest;
import com.homework.comyno.nlf.api.ReturnRequest;
import com.homework.comyno.nlf.api.StudentBaseInfo;
import com.homework.comyno.nlf.controllers.DebugController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = NlfApplication.class)
@AutoConfigureMockMvc
class SystemTests {

  @Autowired private MockMvc mvc;

  @BeforeEach
  public void initDb() throws Exception {
    var initRequestBuilder =
        MockMvcRequestBuilders.post("/api/debug/dbInit")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON);
    mvc.perform(initRequestBuilder).andExpect(status().isOk());
    // verify the initial state is as we expect;
    var bookInfo = getBookInfo();
    var studentInfo = getStudentInfo();
    var loanInfo = getLoanInfo();
    assertNotNull(bookInfo);
    assertNotNull(studentInfo);
    assertNotNull(loanInfo);
    assertEquals(3, bookInfo.size());
    assertEquals(3, studentInfo.size());
    assertEquals(1, loanInfo.size());
    assertEquals(loanInfo.get(0).getStudent().getId(), DebugController.studentId1);
    assertEquals(loanInfo.get(0).getBook().getIsbn(), DebugController.isbn1);
  }

  @AfterEach
  public void cleanup() throws Exception {
    var deleteRequestBuilder =
        MockMvcRequestBuilders.delete("/api/debug/clearAll")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON);
    mvc.perform(deleteRequestBuilder).andExpect(status().isOk());
    var bookInfo = getEntityInfo(BookBaseInfo.class, "/api/debug/books");
    var studentInfo = getEntityInfo(StudentBaseInfo.class, "/api/debug/students");
    var loanInfo = getEntityInfo(LoanInfo.class, "/api/loans");
    // verify the initial state is as we expect;
    assertNotNull(bookInfo);
    assertNotNull(studentInfo);
    assertNotNull(loanInfo);
    assertEquals(0, loanInfo.size());
    assertEquals(0, bookInfo.size());
    assertEquals(0, studentInfo.size());
  }

  @Test
  public void test_validCreateLoan() throws Exception {
    var loanRequest = new LoanRequest(DebugController.isbn2, DebugController.studentId2);
    var loanInfo = createLoan(loanRequest);
    assertEquals(2, loanInfo.size());
    var newLoan =
        loanInfo.stream()
            .filter((l) -> !l.getId().equals(DebugController.loanId1))
            .findFirst()
            .orElse(null);
    assertNotNull(newLoan);
    assertEquals(DebugController.isbn2, newLoan.getBook().getIsbn());
    assertEquals(DebugController.studentId2, newLoan.getStudent().getId());
  }

  @Test
  public void test_nonExistentBook() throws Exception {
    var loanRequest = new LoanRequest("dummyId", DebugController.studentId2);
    var jsonLoan = new ObjectMapper().writeValueAsString(loanRequest);
    var loanRequestBuilder =
        MockMvcRequestBuilders.post("/api/loans")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonLoan);
    var jsonResponse = mvc.perform(loanRequestBuilder).andExpect(status().is(404));
    var loanInfo = getLoanInfo();
    assertEquals(1, loanInfo.size());
    var newLoan = loanInfo.get(0);
    assertEquals(DebugController.isbn1, newLoan.getBook().getIsbn());
    assertEquals(DebugController.studentId1, newLoan.getStudent().getId());
  }

  @Test
  public void test_nonExistentStudent() throws Exception {
    var loanRequest = new LoanRequest(DebugController.isbn1, "dummyId");
    var jsonLoan = new ObjectMapper().writeValueAsString(loanRequest);
    var loanRequestBuilder =
        MockMvcRequestBuilders.post("/api/loans")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonLoan);
    var jsonResponse = mvc.perform(loanRequestBuilder).andExpect(status().is(404));
    var loanInfo = getLoanInfo();
    assertEquals(1, loanInfo.size());
    var newLoan = loanInfo.get(0);
    assertEquals(DebugController.isbn1, newLoan.getBook().getIsbn());
    assertEquals(DebugController.studentId1, newLoan.getStudent().getId());
  }

  @Test
  public void test_alreadyLoaned() throws Exception {
    var loanRequest = new LoanRequest(DebugController.isbn1, DebugController.studentId2);
    var jsonLoan = new ObjectMapper().writeValueAsString(loanRequest);
    var loanRequestBuilder =
        MockMvcRequestBuilders.post("/api/loans")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonLoan);
    mvc.perform(loanRequestBuilder)
        .andExpect(
            status().is(409)); // this Response status code indicates a request conflict with the
    // current state of the target resource.
    var loanInfo = getLoanInfo();
    assertEquals(1, loanInfo.size());
    var newLoan = loanInfo.get(0);
    assertEquals(DebugController.isbn1, newLoan.getBook().getIsbn());
    assertEquals(DebugController.studentId1, newLoan.getStudent().getId());
  }

  @Test
  public void test_returnBook() throws Exception {
    var returnRequest = new ReturnRequest(DebugController.isbn1, DebugController.studentId1);
    var jsonReturn = new ObjectMapper().writeValueAsString(returnRequest);
    var deleteRequestBuilder =
        MockMvcRequestBuilders.delete("/api/loans")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonReturn);
    var jsonResponse = mvc.perform(deleteRequestBuilder).andExpect(status().isOk()).andReturn();

    // verify the final state is as we expect;
    var bookInfo = getBookInfo();
    var studentInfo = getStudentInfo();
    var loanInfo = getLoanInfo();
    assertNotNull(bookInfo);
    assertNotNull(studentInfo);
    assertNotNull(loanInfo);
    assertEquals(3, bookInfo.size());
    assertEquals(3, studentInfo.size());
    assertTrue(loanInfo.isEmpty());
  }

  // Helper Methods: probably should be in a separate class
  private List<BookBaseInfo> getBookInfo() throws Exception {
    return getEntityInfo(BookBaseInfo.class, "/api/debug/books");
  }

  private List<StudentBaseInfo> getStudentInfo() throws Exception {
    return getEntityInfo(StudentBaseInfo.class, "/api/debug/students");
  }

  private List<LoanInfo> getLoanInfo() throws Exception {
    return getEntityInfo(LoanInfo.class, "/api/loans");
  }

  private <T> List<T> getEntityInfo(Class<T> type, String endPoint) throws Exception {
    var jsonResponse =
        mvc.perform(get(endPoint).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();
    return jsonArrayToList(jsonResponse, type);
  }

  private List<LoanInfo> createLoan(LoanRequest loanRequest) throws Exception {
    var jsonLoan = new ObjectMapper().writeValueAsString(loanRequest);
    var loanRequestBuilder =
        MockMvcRequestBuilders.post("/api/loans")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonLoan);
    var jsonResponse = mvc.perform(loanRequestBuilder).andReturn();
    return jsonArrayToList(jsonResponse, LoanInfo.class);
  }

  private <T> List<T> jsonArrayToList(MvcResult mvcResult, Class<T> elementClass)
      throws IOException {
    var json = mvcResult.getResponse().getContentAsString();
    var objectMapper = new ObjectMapper();
    var listType =
        objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, elementClass);
    return objectMapper.readValue(json, listType);
  }
}
