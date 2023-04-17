package com.homework.comyno.nlf.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.homework.comyno.nlf.NlfApplication;
import com.homework.comyno.nlf.api.BookFullInfo;
import com.homework.comyno.nlf.api.LoanInfo;
import com.homework.comyno.nlf.api.LoanRequest;
import com.homework.comyno.nlf.api.StudentFullInfo;
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
    var request =
        MockMvcRequestBuilders.post("/api/debug/dbInit")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON);

    mvc.perform(request).andExpect(status().isOk());

    var bookInfo = getEntityInfo(BookFullInfo.class, "/api/debug/books");
    var studentInfo = getEntityInfo(StudentFullInfo.class, "/api/debug/students");
    var loanInfo = getEntityInfo(LoanInfo.class, "/api/loans");

    // verify the initial state is as we expect;
    assertNotNull(bookInfo);
    assertNotNull(studentInfo);
    assertNotNull(loanInfo);

    assertEquals(1, loanInfo.size());
    assertEquals(3, bookInfo.size());
    assertEquals(3, studentInfo.size());

    var borrowedBooks = bookInfo.stream().filter((b) -> b.getBorrower() != null).toList();
    assertEquals(1, borrowedBooks.size());
    var borrowingStudents =
        studentInfo.stream().filter((s) -> !s.getBorrowedBooks().isEmpty()).toList();
    assertEquals(1, borrowingStudents.size());

    assertEquals(loanInfo.get(0).getStudent().getId(), borrowingStudents.get(0).getId());
    assertEquals(loanInfo.get(0).getBook().getIsbn(), borrowedBooks.get(0).getIsbn());
  }

  @AfterEach
  public void cleanup() throws Exception {
    var request =
        MockMvcRequestBuilders.delete("/api/debug/clearAll")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON);

    mvc.perform(request).andExpect(status().isOk());

    var bookInfo = getEntityInfo(BookFullInfo.class, "/api/debug/books");
    var studentInfo = getEntityInfo(StudentFullInfo.class, "/api/debug/students");
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
    var loanRequest =
        new LoanRequest("loan-test-id", DebugController.isbn2, DebugController.studentId2);
    var loanInfo = createLoan(loanRequest);

    assertEquals(2, loanInfo.size());
    var newLoan =
        loanInfo.stream()
            .filter((l) -> l.getId().equals(loanRequest.getId()))
            .findFirst()
            .orElse(null);
    assertNotNull(newLoan);

    assertEquals(DebugController.isbn2, newLoan.getBook().getIsbn());
    assertEquals(DebugController.studentId2, newLoan.getStudent().getId());
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
    var request =
        MockMvcRequestBuilders.post("/api/loans")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonLoan);

    var jsonResponse = mvc.perform(request).andReturn();
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
