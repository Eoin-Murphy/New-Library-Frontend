package com.homework.comyno.nlf.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.homework.comyno.nlf.NlfApplication;
import com.homework.comyno.nlf.api.BookFullInfo;
import com.homework.comyno.nlf.api.LoanInfo;
import com.homework.comyno.nlf.api.StudentFullInfo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = NlfApplication.class)
@AutoConfigureMockMvc
class SystemTests {

  @Autowired private MockMvc mvc;

  @Test
  public void test1() throws Exception {
    var bookInfo = getEntityInfo(BookFullInfo.class, "/api/debug/books");
    var studentInfo = getEntityInfo(StudentFullInfo.class, "/api/debug/students");
    var loanInfo = getEntityInfo(LoanInfo.class, "/api/loans/");
    assertNotNull(bookInfo);
    assertNotNull(studentInfo);
    assertNotNull(loanInfo);

    assertEquals(1, loanInfo.size());
    assertEquals(1, bookInfo.stream().filter((b) -> b.getBorrower() != null).toList().size());
    assertEquals(1, studentInfo.stream().filter((b) -> !b.getBorrowedBooks().isEmpty()).toList().size());

    // var loansAfterNewCreated()
  }

  private <T> List<T> getEntityInfo(Class<T> type, String endPoint)
      throws Exception {
    var jsonResponse =
        mvc.perform(get(endPoint).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn()
            .getResponse()
            .getContentAsString();

    return jsonArrayToList(jsonResponse, type);
  }

  private <T> List<T> jsonArrayToList(String json, Class<T> elementClass) throws IOException{
    var objectMapper = new ObjectMapper();
    CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, elementClass);
    return objectMapper.readValue(json, listType);
  }
}
