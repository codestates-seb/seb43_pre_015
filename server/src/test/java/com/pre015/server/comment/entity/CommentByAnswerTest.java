package com.pre015.server.comment.entity;

import com.google.gson.Gson;
import com.pre015.server.answer.entity.Answer;
import com.pre015.server.comment.controller.CommentController;
import com.pre015.server.comment.dto.CommentDto;
import com.pre015.server.comment.mapper.CommentMapper;
import com.pre015.server.comment.service.CommentService;
import com.pre015.server.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebMvcTest(CommentController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class CommentByAnswerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private CommentMapper commentMapper;

    @MockBean
    private CommentService commentService;

    @Test
    @DisplayName("get Comment Page By Answer")
    void getPageComment() throws Exception {

        Long answerId = 1L;
        int page = 1;
        int size = 3;

        BDDMockito.given(commentService.findCommentsByAnswer(
                Mockito.anyLong(),Mockito.anyInt(),Mockito.anyInt())).willReturn(new PageImpl<>(new ArrayList<>()));
        List<CommentDto.Response> list = new ArrayList<>();
        Answer answer = new Answer();
        answer.setAnswerId(answerId);
        Member member1 = new Member();
        member1.setMemberId(1L);
        Member member2 = new Member();
        member2.setMemberId(2L);
        Member member3 = new Member();
        member3.setMemberId(3L);

        CommentDto.Response comment1 = new CommentDto.Response(1L,
                "1번 코멘트",
                answerId,
                member1.getMemberId(),
                LocalDateTime.now(),
                LocalDateTime.now());

        CommentDto.Response comment2 = new CommentDto.Response(2L,
                "2번 코멘트",
                answerId,
                member2.getMemberId(),
                LocalDateTime.now(),
                LocalDateTime.now());

        CommentDto.Response comment3 = new CommentDto.Response(3L,
                "3번 코멘트",
                answerId,
                member3.getMemberId(),
                LocalDateTime.now(),
                LocalDateTime.now());

        list.add(comment1);
        list.add(comment2);
        list.add(comment3);

        CommentDto.ResponseAll<List<CommentDto.Response>> listResponseAll = new CommentDto.ResponseAll<>(list, page, size, 15, 5);

        BDDMockito.given(commentMapper.commentsToResponseAll(
                Mockito.anyList(), Mockito.anyInt(),Mockito.anyInt(),Mockito.anyInt(),Mockito.anyInt())).willReturn(listResponseAll);

        ResultActions actions = mockMvc.perform(RestDocumentationRequestBuilders.get("/comments/answer/{answer_id}", answerId)
                .param("page","1")
                .param("size","3")
                .accept(MediaType.APPLICATION_JSON));

        actions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[0].commentId").value(list.get(0).getCommentId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[0].content").value(list.get(0).getContent()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[0].memberId").value(list.get(0).getMemberId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[0].answerId").value(list.get(0).getAnswerId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[1].memberId").value(list.get(1).getMemberId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[1].answerId").value(list.get(1).getAnswerId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[1].content").value(list.get(1).getContent()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[1].commentId").value(list.get(1).getCommentId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[2].memberId").value(list.get(2).getMemberId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[2].answerId").value(list.get(2).getAnswerId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[2].content").value(list.get(2).getContent()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.[2].commentId").value(list.get(2).getCommentId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.page").value(page))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size").value(size))
                .andExpect(MockMvcResultMatchers.jsonPath("$.getTotalElements").value(15))
                .andExpect(MockMvcResultMatchers.jsonPath("$.getTotalPages").value(5))

                .andDo(MockMvcRestDocumentation.document("getCommentsByAnswer",
                        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                        Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                        RequestDocumentation.pathParameters(
                                RequestDocumentation.parameterWithName("answer_id").description("답변 식별자")
                        ),
                        RequestDocumentation.requestParameters(
                                RequestDocumentation.parameterWithName("page").description("현재 페이지"),
                                RequestDocumentation.parameterWithName("size").description("페이지 사이즈")
                        ),
                        PayloadDocumentation.responseFields(
                                List.of(
                                        PayloadDocumentation.fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터"),
                                        PayloadDocumentation.fieldWithPath("data.[0].memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        PayloadDocumentation.fieldWithPath("data.[0].answerId").type(JsonFieldType.NUMBER).description("답변 식별자"),
                                        PayloadDocumentation.fieldWithPath("data.[0].commentId").type(JsonFieldType.NUMBER).description("댓글 식별자"),
                                        PayloadDocumentation.fieldWithPath("data.[0].content").type(JsonFieldType.STRING).description("댓글 내용"),
                                        PayloadDocumentation.fieldWithPath("data.[0].created_at").type(JsonFieldType.STRING).description("생성 시각"),
                                        PayloadDocumentation.fieldWithPath("data.[0].last_modified_at").type(JsonFieldType.STRING).description("수정 시각"),
                                        PayloadDocumentation.fieldWithPath("data.[1].memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        PayloadDocumentation.fieldWithPath("data.[1].answerId").type(JsonFieldType.NUMBER).description("답변 식별자"),
                                        PayloadDocumentation.fieldWithPath("data.[1].commentId").type(JsonFieldType.NUMBER).description("댓글 식별자"),
                                        PayloadDocumentation.fieldWithPath("data.[1].content").type(JsonFieldType.STRING).description("댓글 내용"),
                                        PayloadDocumentation.fieldWithPath("data.[1].created_at").type(JsonFieldType.STRING).description("생성 시각"),
                                        PayloadDocumentation.fieldWithPath("data.[1].last_modified_at").type(JsonFieldType.STRING).description("수정 시각"),
                                        PayloadDocumentation.fieldWithPath("data.[2].memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                        PayloadDocumentation.fieldWithPath("data.[2].answerId").type(JsonFieldType.NUMBER).description("답변 식별자"),
                                        PayloadDocumentation.fieldWithPath("data.[2].commentId").type(JsonFieldType.NUMBER).description("댓글 식별자"),
                                        PayloadDocumentation.fieldWithPath("data.[2].content").type(JsonFieldType.STRING).description("댓글 내용"),
                                        PayloadDocumentation.fieldWithPath("data.[2].created_at").type(JsonFieldType.STRING).description("생성 시각"),
                                        PayloadDocumentation.fieldWithPath("data.[2].last_modified_at").type(JsonFieldType.STRING).description("수정 시각"),
                                        PayloadDocumentation.fieldWithPath("page").type(JsonFieldType.NUMBER).description("현재 페이지"),
                                        PayloadDocumentation.fieldWithPath("size").type(JsonFieldType.NUMBER).description("페이지 사이즈"),
                                        PayloadDocumentation.fieldWithPath("getTotalElements").type(JsonFieldType.NUMBER).description("전체 데이터 개수"),
                                        PayloadDocumentation.fieldWithPath("getTotalPages").type(JsonFieldType.NUMBER).description("전체 페이지 개수")
                                )
                        )
                ));
    }
}
