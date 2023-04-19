package com.pre015.server.answer.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.pre015.server.answer.dto.AnswerDTO;
import com.pre015.server.answer.entity.Answer;
import com.pre015.server.answer.service.AnswerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class AnswerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private AnswerService answerService;
    private AnswerDTO answerDTO;
    private List<AnswerDTO> answerDTOList;

    @BeforeEach
    void setUp() {
        answerDTO = new AnswerDTO();
        answerDTO.setAnswerId(1L);
        answerDTO.setContent("Test answer");
        answerDTO.setLike(0);
        answerDTO.setSelectionStatus(false);
        answerDTO.setMemberId(1L);
        answerDTO.setQuestionId(1L);

        answerDTOList = new ArrayList<>();
        answerDTOList.add(answerDTO);
    }

    @DisplayName("Create Answer")
    @Test
    void testCreateAnswer() throws Exception {
        AnswerDTO.POST postDTO = new AnswerDTO.POST("Test answer", 1L, 1L);
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setContent(postDTO.getContent());
        answerDTO.setMemberId(postDTO.getMemberId());
        answerDTO.setQuestionId(postDTO.getQuestionId());

        when(answerService.createAnswer(any(AnswerDTO.POST.class))).thenReturn(answerDTO);

        mockMvc.perform(post("/api/answers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.content").value("Test answer"));

        verify(answerService, times(1)).createAnswer(any(AnswerDTO.POST.class));
    }

    @DisplayName("Update Answer")
    @Test
    void testUpdateAnswer() throws Exception {
        Long id = 1L;
        when(answerService.updateAnswer(id, answerDTO)).thenReturn(answerDTO);

        mockMvc.perform(put("/api/answers/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(answerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Test answer"));

        verify(answerService, times(1)).updateAnswer(id, answerDTO);
    }

    @DisplayName("Delete Answer")
    @Test
    void testDeleteAnswer() throws Exception {
        Long id = 1L;
        doNothing().when(answerService).deleteAnswer(id);

        mockMvc.perform(delete("/api/answers/{id}", id))
                .andExpect(status().isNoContent());

        verify(answerService, times(1)).deleteAnswer(id);
    }

    @DisplayName("get AnswerList")
    @Test
    void testFindAllAnswers() throws Exception {
        when(answerService.findAllAnswers()).thenReturn(answerDTOList);

        mockMvc.perform(get("/api/answers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value("Test answer"));

        verify(answerService, times(1)).findAllAnswers();
    }

    @DisplayName("get Answer By AnswerId")
    @Test
    void testFindAnswerById() throws Exception {
        Long id = 1L;
        when(answerService.findAnswer(id)).thenReturn(answerDTO);

        mockMvc.perform(get("/api/answers/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Test answer"));
        verify(answerService, times(1)).findAnswer(id);
    }

    @DisplayName("get AnswerList By MemberId")
    @Test
    void testFindAnswersByMemberId() throws Exception {
        Long memberId = 1L;
        when(answerService.findAnswersByMember(memberId)).thenReturn(answerDTOList);

        mockMvc.perform(get("/api/answers/member/{memberId}", memberId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value("Test answer"));

        verify(answerService, times(1)).findAnswersByMember(memberId);
    }

    @DisplayName("get AnswerList By QuestionId")
    @Test
    void testFindAnswersByQuestionId() throws Exception {
        Long questionId = 1L;
        when(answerService.findAnswersByQuestion(questionId)).thenReturn(answerDTOList);

        mockMvc.perform(get("/api/answers/question/{questionId}", questionId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value("Test answer"));

        verify(answerService, times(1)).findAnswersByQuestion(questionId);
    }

    @DisplayName("Accept to Answer Status")
    @Test
    void testAcceptAnswer() throws Exception {
        Long questionId = 1L;
        Long answerId = 1L;
        doNothing().when(answerService).acceptAnswer(questionId, answerId);

        mockMvc.perform(put("/api/answers/accept/{questionId}/{answerId}", questionId, answerId))
                .andExpect(status().isOk());

        verify(answerService, times(1)).acceptAnswer(questionId, answerId);
    }
}