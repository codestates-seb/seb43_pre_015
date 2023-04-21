package com.pre015.server.question;


import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import com.pre015.server.question.controller.QuestionController;
import com.pre015.server.question.dto.QuestionDto;
import com.pre015.server.question.entity.Question;
import com.pre015.server.question.mapper.QuestionMapper;
import com.pre015.server.question.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;

import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QuestionController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class QuestionControllerDocumentationTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private QuestionService questionService;
    @MockBean
    private QuestionMapper mapper;
    @Autowired
    private Gson gson;


    @Test
    public void postQuestionTest() throws Exception {
        // given
        QuestionDto.Post post = new QuestionDto.Post(1L,"질문 제목","질문 내용");
        String content = gson.toJson(post);
        given(mapper.questionPostDtoToQuestion(Mockito.any(QuestionDto.Post.class))).willReturn(new Question());

        QuestionDto.DetailsResponse mockmockResultResponse = new QuestionDto.DetailsResponse();
        mockmockResultResponse.setMemberId(1L);
        mockmockResultResponse.setQuestionId(1L);
        mockmockResultResponse.setTitle("질문 제목");
        mockmockResultResponse.setContent("질문 내용");
        mockmockResultResponse.setDisplayName("회원 닉네임");
        mockmockResultResponse.setCreatedTime(LocalDateTime.now());
        mockmockResultResponse.setModifiedTime(LocalDateTime.now());
        given(questionService.createQuestion(Mockito.any(QuestionDto.Post.class))).willReturn(mockmockResultResponse);

        // when
        ResultActions actions =
                mockMvc.perform(
                        post("/questions/ask")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        // then
        actions
                .andExpect(status().isCreated())
                .andExpect(header().string("Location","/questions/1"))
                .andDo(document(
                        "post-question",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                List.of(
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 id"),
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("질문 제목"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("질문 내용")
                                )
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.LOCATION).description("등록된 질문의 URI. /questions/{questionId}")
                        )
                ));
    }

    @Test
    public void patchQuestionTest() throws Exception {
        // given
        long questionId = 1L;
        long memberId = 1L;
        List<com.pre015.server.answer.dto.AnswerDTO> answers = new ArrayList<>();

        QuestionDto.Patch patch = new QuestionDto.Patch();
        patch.setQuestionId(questionId);
        patch.setTitle("질문 제목");
        patch.setContent("질문 내용");
        String content = gson.toJson(patch);

        QuestionDto.DetailsResponse responseDto =
                new QuestionDto.DetailsResponse(
                        questionId,
                        memberId,
                        "질문 제목",
                        "질문 내용",
                        "회원 닉네임",
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        answers
                );

        given(mapper.questionPatchDtoToQuestion(Mockito.any(QuestionDto.Patch.class))).willReturn(new Question());
        given(questionService.updateQuestion(Mockito.anyLong(),Mockito.any(QuestionDto.Patch.class))).willReturn(responseDto);
        given(mapper.questionToQuestionDetailsResponseDto(Mockito.any(Question.class))).willReturn(responseDto);

        // when
        ResultActions actions =
                mockMvc.perform(
                        patch("/questions/{question_id}", questionId)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        // then
        actions
                .andExpect(status().isOk())
                .andDo(document(
                        "patch-question",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("question_id").description("질문 식별자")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("질문 id"),
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("수정할 질문 제목"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("수정할 질문 내용")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("질문 id"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 id"),
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("질문 제목"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("질문 내용"),
                                        fieldWithPath("displayName").type(JsonFieldType.STRING).description("닉네임"),
                                        fieldWithPath("createdTime").type(JsonFieldType.STRING).description("질문 생성 시간"),
                                        fieldWithPath("modifiedTime").type(JsonFieldType.STRING).description("질문 수정 시간"),
                                        fieldWithPath("answers").type(JsonFieldType.ARRAY).description("답변 리스트")
                                )
                        )
                ));
    }

    @Test
    public void getQuestionTest() throws Exception {
        // given
        List<com.pre015.server.answer.dto.AnswerDTO> answers = new ArrayList<>();

        QuestionDto.DetailsResponse responseDto =
                new QuestionDto.DetailsResponse(
                        1L,
                        1L,
                        "질문 제목",
                        "질문 내용",
                        "회원 닉네임",
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        answers
                );

        given(questionService.findQuestion(Mockito.anyLong())).willReturn(responseDto);
        given(mapper.questionToQuestionDetailsResponseDto(Mockito.any(Question.class))).willReturn(responseDto);

        // when
        ResultActions actions =
                mockMvc.perform(
                        get("/questions/{question_id}", responseDto.getQuestionId())
                                .accept(MediaType.APPLICATION_JSON)
                );


        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.questionId").value(1L))
                .andExpect(jsonPath("$.title").value(responseDto.getTitle()))
                .andDo(
                        document("get-question",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                pathParameters(
                                        List.of(parameterWithName("question_id").description("질문 식별자"))
                                ),
                                responseFields(
                                        List.of(
                                                fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("질문 id"),
                                                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 id"),
                                                fieldWithPath("title").type(JsonFieldType.STRING).description("질문 제목"),
                                                fieldWithPath("content").type(JsonFieldType.STRING).description("질문 내용"),
                                                fieldWithPath("displayName").type(JsonFieldType.STRING).description("닉네임"),
                                                fieldWithPath("createdTime").type(JsonFieldType.STRING).description("질문 생성 시간"),
                                                fieldWithPath("modifiedTime").type(JsonFieldType.STRING).description("질문 수정 시간"),
                                                fieldWithPath("answers").type(JsonFieldType.ARRAY).description("답변 리스트")
                                        )
                                )
                        )
                );
    }

    @Test
    public void getQuestionsTest() throws Exception {
        //given
        List<QuestionDto.Response> responseDtos = List.of(
                new QuestionDto.Response(
                        1L,
                        1L,
                        "질문 제목",
                        "질문 내용",
                        "회원 닉네임",
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        0
                ),
                new QuestionDto.Response(
                        2L,
                        1L,
                        "질문 제목",
                        "질문 내용",
                        "회원 닉네임",
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        5
                )
        );
        QuestionDto.PageInfo pageInfo = new QuestionDto.PageInfo(1,15,2,1);
        String page = "1";
        String size = "15";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("page", page);
        params.add("size", size);

        QuestionDto.MultiResponse<QuestionDto.Response> Mresponse = new QuestionDto.MultiResponse<>(responseDtos, pageInfo);

        given(questionService.findQuestions(Mockito.anyInt(),Mockito.anyInt())).willReturn(Mresponse);
        //when
        ResultActions actions =
                mockMvc.perform(
                        get("/questions")
                                .params(params)
                                .accept(MediaType.APPLICATION_JSON)
                );
        //then
        MvcResult result =
                actions.andExpect(status().isOk())
                        .andDo(
                               document("get-questions",
                                       preprocessRequest(prettyPrint()),
                                       preprocessResponse(prettyPrint()),
                                       requestParameters(
                                               List.of(
                                                       parameterWithName("page").description("현재 page 번호"),
                                                       parameterWithName("size").description("page 사이즈(15,30,45)")
                                               )
                                       ),
                                       responseFields(
                                               List.of(
                                                       fieldWithPath("questions").type(JsonFieldType.ARRAY).description("질문 리스트"),
                                                       fieldWithPath("questions[].questionId").type(JsonFieldType.NUMBER).description("질문 id"),
                                                       fieldWithPath("questions[].memberId").type(JsonFieldType.NUMBER).description("회원 id"),
                                                       fieldWithPath("questions[].title").type(JsonFieldType.STRING).description("질문 제목"),
                                                       fieldWithPath("questions[].content").type(JsonFieldType.STRING).description("질문 내용"),
                                                       fieldWithPath("questions[].displayName").type(JsonFieldType.STRING).description("닉네임"),
                                                       fieldWithPath("questions[].createdTime").type(JsonFieldType.STRING).description("질문 생성 시간"),
                                                       fieldWithPath("questions[].modifiedTime").type(JsonFieldType.STRING).description("질문 수정 시간"),
                                                       fieldWithPath("questions[].answerCount").type(JsonFieldType.NUMBER).description("답변 리스트"),
                                                       fieldWithPath("pageInfo").type(JsonFieldType.OBJECT).description("페이지 정보"),
                                                       fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("현재 페이지 번호"),
                                                       fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("페이지 사이즈"),
                                                       fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("전체 질문 갯수"),
                                                       fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("전체 페이지 수")
                                               )
                                       )
                               )
                        ).andReturn();
        List list = JsonPath.parse(result.getResponse().getContentAsString()).read("$.questions");
        assertThat(list.size(), is(2));
    }

    @Test
    public void getQuestionsByMemberTest() throws Exception {
        //given
        long memberId = 1L;
        List<QuestionDto.Response> responseDtos = List.of(
                new QuestionDto.Response(
                        1L,
                        memberId,
                        "질문 제목",
                        "질문 내용",
                        "회원 닉네임",
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        0
                ),
                new QuestionDto.Response(
                        2L,
                        memberId,
                        "질문 제목",
                        "질문 내용",
                        "회원 닉네임",
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        5
                )
        );
        QuestionDto.PageInfo pageInfo = new QuestionDto.PageInfo(1,15,2,1);
        String page = "1";
        String size = "15";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("page", page);
        params.add("size", size);

        QuestionDto.MultiResponse<QuestionDto.Response> Mresponse = new QuestionDto.MultiResponse<>(responseDtos, pageInfo);

        given(questionService.responseQuestionsByMember(Mockito.anyLong(),Mockito.anyInt(),Mockito.anyInt())).willReturn(Mresponse);
        //when
        ResultActions actions =
                mockMvc.perform(
                        get("/questions/member/{member_id}", memberId)
                                .params(params)
                                .accept(MediaType.APPLICATION_JSON)
                );
        //then
        MvcResult result =
                actions.andExpect(status().isOk())
                        .andDo(
                                document("get-questions-by-member",
                                        preprocessRequest(prettyPrint()),
                                        preprocessResponse(prettyPrint()),
                                        pathParameters(
                                                List.of(parameterWithName("member_id").description("회원 식별자"))
                                        ),
                                        requestParameters(
                                                List.of(
                                                        parameterWithName("page").description("현재 page 번호"),
                                                        parameterWithName("size").description("page 사이즈(15,30,45)")
                                                )
                                        ),
                                        responseFields(
                                                List.of(
                                                        fieldWithPath("questions").type(JsonFieldType.ARRAY).description("질문 리스트"),
                                                        fieldWithPath("questions[].questionId").type(JsonFieldType.NUMBER).description("질문 id"),
                                                        fieldWithPath("questions[].memberId").type(JsonFieldType.NUMBER).description("회원 id"),
                                                        fieldWithPath("questions[].title").type(JsonFieldType.STRING).description("질문 제목"),
                                                        fieldWithPath("questions[].content").type(JsonFieldType.STRING).description("질문 내용"),
                                                        fieldWithPath("questions[].displayName").type(JsonFieldType.STRING).description("닉네임"),
                                                        fieldWithPath("questions[].createdTime").type(JsonFieldType.STRING).description("질문 생성 시간"),
                                                        fieldWithPath("questions[].modifiedTime").type(JsonFieldType.STRING).description("질문 수정 시간"),
                                                        fieldWithPath("questions[].answerCount").type(JsonFieldType.NUMBER).description("답변 리스트"),
                                                        fieldWithPath("pageInfo").type(JsonFieldType.OBJECT).description("페이지 정보"),
                                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("현재 페이지 번호"),
                                                        fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("페이지 사이즈"),
                                                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("전체 질문 갯수"),
                                                        fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("전체 페이지 수")
                                                )
                                        )
                                )
                        ).andReturn();
        List list = JsonPath.parse(result.getResponse().getContentAsString()).read("$.questions");
        assertThat(list.size(), is(2));
    }

    @Test
    public void deleteQuestionTest() throws Exception {

        // given
        long questionId = 1L;
        doNothing().when(questionService).deleteQuestion(Mockito.anyLong());

        // when
        ResultActions actions =
                mockMvc.perform(
                        delete("/questions/{question_id}", questionId)
                );

        actions.andExpect(status().isNoContent())
                .andDo(
                        document(
                                "delete-question",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                pathParameters(
                                        List.of(parameterWithName("question_id").description("질문 ID"))
                                )
                        )
                );
    }
}

