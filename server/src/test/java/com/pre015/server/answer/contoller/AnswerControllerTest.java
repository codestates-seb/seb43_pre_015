package com.pre015.server.answer.contoller;

import com.google.gson.Gson;
import com.pre015.server.answer.dto.AnswerDTO;
import com.pre015.server.answer.entity.Answer;
import com.pre015.server.answer.service.AnswerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.startsWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class AnswerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private AnswerService answerService;

    @DisplayName("Create Answer")
    @Test
    void createAnswer() throws Exception {
        // given
        AnswerDTO expectedAnswer = new AnswerDTO();
        expectedAnswer.setAnswerId(1L);
        expectedAnswer.setContent("test");
        expectedAnswer.setLike(0);
        expectedAnswer.setSelectionStatus(false);
        expectedAnswer.setMemberId(1L);
        expectedAnswer.setQuestionId(1L);;
        given(answerService.createAnswer(any(AnswerDTO.POST.class)))
                .willReturn(expectedAnswer);
        AnswerDTO.POST postAnswer = new AnswerDTO.POST("text", 1L, 1L);
        String content = gson.toJson(postAnswer);

        // when
        ResultActions actions = mockMvc.perform(post("/api/answers")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
        );

        // then
        actions.andExpect(status().isCreated())
                .andExpect(result -> {
                    String resultContent = result.getResponse().getContentAsString();
                    AnswerDTO resultAnswer = gson.fromJson(resultContent, AnswerDTO.class);

                    if (!expectedAnswer.equals(resultAnswer)) {
                        throw new AssertionError("Unexpected response result: " + resultContent);
                    }
                });
    }
}