package com.pre015.server.comment.entity;


import com.google.gson.Gson;
import com.pre015.server.answer.dto.AnswerResponseDTO;
import com.pre015.server.answer.entity.Answer;
import com.pre015.server.answer.mapper.AnswerMapper;
import com.pre015.server.answer.service.AnswerService;
import com.pre015.server.comment.controller.CommentController;
import com.pre015.server.comment.dto.CommentDto;
import com.pre015.server.comment.mapper.CommentMapper;
import com.pre015.server.comment.service.CommentService;
import com.pre015.server.member.entity.Member;
import com.pre015.server.member.service.MemberService;
import com.pre015.server.question.entity.Question;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;

@WebMvcTest(CommentController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class CommentPostTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @MockBean
    private CommentMapper commentMapper;

    @MockBean
    private MemberService memberService;

    @MockBean
    private AnswerService answerService;

    @MockBean
    private AnswerMapper answerMapper;

    @Autowired
    private Gson gson;

    @Test
    @DisplayName("Post Comment Test")
    public void postCommentTest() throws Exception {
        Long memberId = 1L;
        Long answerId = 1L;
        CommentDto.Post content = new CommentDto.Post(memberId, answerId, "테스트 contents");
        String json = gson.toJson(content);



        BDDMockito.given(commentMapper.postToComment(Mockito.any(CommentDto.Post.class))).willReturn(new Comment());
        BDDMockito.given(memberService.findVerifiedMember(Mockito.anyLong())).willReturn(new Member());
        BDDMockito.given(answerService.findAnswer(Mockito.anyLong())).willReturn(new AnswerResponseDTO());
        BDDMockito.given(answerMapper
                .responseDtoToEntity(Mockito.any(AnswerResponseDTO.class),Mockito.any(Member.class),Mockito.any(Question.class))).willReturn(new Answer());

        Member member = new Member();
        member.setMemberId(memberId);

        Answer answer = new Answer();
        answer.setAnswerId(answerId);

        Comment comment = new Comment();
        comment.setCommentId(1L);
        comment.setMember(member);
        comment.setAnswer(answer);
        comment.setContent("테스트 contents");

        CommentDto.Response response = commentMapper.commentToResponse(comment);

        BDDMockito.given(commentService.createComment(Mockito.any(Comment.class))).willReturn(new Comment());
        BDDMockito.given(commentMapper.commentToResponse(Mockito.any(Comment.class))).willReturn(response);

        ResultActions actions = mockMvc.perform(
                RestDocumentationRequestBuilders.post("/comments/new")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );

        actions.andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcRestDocumentation.document("post-comment",
                        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                        Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                        requestFields(
                                List.of(
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원"),
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("답변"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("답글")
                                )
                        )
                ));
    }
}