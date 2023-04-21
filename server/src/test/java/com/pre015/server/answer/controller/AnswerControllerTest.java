package com.pre015.server.answer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pre015.server.answer.dto.AnswerPatchDTO;
import com.pre015.server.answer.dto.AnswerPostDTO;
import com.pre015.server.answer.dto.AnswerResponseDTO;
import com.pre015.server.answer.service.AnswerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class AnswerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private AnswerService answerService;
    private AnswerPatchDTO answerPatchDTO;
    private AnswerResponseDTO answerResponseDTO;
    private List<AnswerResponseDTO> answerResponseDTOList;

    @BeforeEach
    void setUp() {
        answerPatchDTO = new AnswerPatchDTO();
        answerPatchDTO.setContent("Test answer");

        answerResponseDTO = new AnswerResponseDTO();
        answerResponseDTO.setAnswerId(1L);
        answerResponseDTO.setContent("Test answer");
        answerResponseDTO.setLikes(0);
        answerResponseDTO.setSelectionStatus(false);
        answerResponseDTO.setMemberId(1L);
        answerResponseDTO.setMemberDisplayName("Test user");
        answerResponseDTO.setQuestionId(1L);
        answerResponseDTO.setCreatedTime(LocalDateTime.now());
        answerResponseDTO.setModifiedTime(LocalDateTime.now());

        answerResponseDTOList = new ArrayList<>();
        answerResponseDTOList.add(answerResponseDTO);
    }

    @DisplayName("Create Answer")
    @Test
    void testCreateAnswer() throws Exception {
        AnswerPostDTO postDTO = new AnswerPostDTO("Test answer", 1L, 1L);

        when(answerService.createAnswer(any(AnswerPostDTO.class))).thenReturn(answerResponseDTO);

        mockMvc.perform(post("/api/answers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.content").value("Test answer"))
                .andDo(document("Answer Create",
                        requestFields(
                                fieldWithPath("content").type(STRING).description("답변 내용 (5000자)"),
                                fieldWithPath("memberId").type(NUMBER).description("답변 작성자 ID"),
                                fieldWithPath("questionId").type(NUMBER).description("답변할 질문 ID")
                        ),
                        responseFields(
                                fieldWithPath("answerId").type(NUMBER).description("답변글 ID"),
                                fieldWithPath("content").type(STRING).description("답변글 내용"),
                                fieldWithPath("likes").type(NUMBER).description("좋아요 카운트"),
                                fieldWithPath("selectionStatus").type(BOOLEAN).description("채택 여부"),
                                fieldWithPath("memberId").type(NUMBER).description("작성자 아이디"),
                                fieldWithPath("memberDisplayName").type(STRING).description("작성자 닉네임"),
                                fieldWithPath("questionId").type(NUMBER).description("질문글 아이디"),
                                fieldWithPath("createdTime").type(STRING).description("생성 일자"),
                                fieldWithPath("modifiedTime").type(STRING).description("수정 일자")
                        )
                ));

        verify(answerService, times(1)).createAnswer(any(AnswerPostDTO.class));
    }

    @DisplayName("Update Answer Content")
    @Test
    void testUpdateAnswer() throws Exception {
        Long id = 1L;
        when(answerService.updateAnswer(id, answerResponseDTO.getContent())).thenReturn(answerResponseDTO);

        mockMvc.perform(patch("/api/answers/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(answerPatchDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Test answer"))
                .andDo(document("Answer Update",
                        pathParameters(
                                parameterWithName("id").description("답변글 아이디")
                        ),
                        requestFields(
                                fieldWithPath("content").type(STRING).description("답변 내용 (5000자)")
                        ),
                        responseFields(
                                fieldWithPath("answerId").type(NUMBER).description("답변글 ID"),
                                fieldWithPath("content").type(STRING).description("답변글 내용"),
                                fieldWithPath("likes").type(NUMBER).description("좋아요 카운트"),
                                fieldWithPath("selectionStatus").type(BOOLEAN).description("채택 여부"),
                                fieldWithPath("memberId").type(NUMBER).description("작성자 아이디"),
                                fieldWithPath("memberDisplayName").type(STRING).description("작성자 닉네임"),
                                fieldWithPath("questionId").type(NUMBER).description("질문글 아이디"),
                                fieldWithPath("createdTime").type(STRING).description("생성 일자"),
                                fieldWithPath("modifiedTime").type(STRING).description("수정 일자")
                        )
                ));

        verify(answerService, times(1)).updateAnswer(id, answerResponseDTO.getContent());
    }

    @DisplayName("Delete Answer")
    @Test
    void testDeleteAnswer() throws Exception {
        Long id = 1L;
        doNothing().when(answerService).deleteAnswer(id);

        mockMvc.perform(delete("/api/answers/{id}", id))
                .andExpect(status().isNoContent())
                .andDo(document("Answer Delete",
                        pathParameters(
                                parameterWithName("id").description("답변글 아이디")
                        )
                ));

        verify(answerService, times(1)).deleteAnswer(id);
    }

    @DisplayName("get Answer By AnswerId")
    @Test
    void testfindAnswer() throws Exception {
        Long id = 1L;
        when(answerService.findAnswer(id)).thenReturn(answerResponseDTO);

        mockMvc.perform(get("/api/answers/{id}", id))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.content").value("Test answer"))
                .andDo(document("Get One Answer By Id",
                        pathParameters(
                                parameterWithName("id").description("답변글 아이디")
                        ),
                        responseFields(
                                fieldWithPath("answerId").type(NUMBER).description("답변글 ID"),
                                fieldWithPath("content").type(STRING).description("답변글 내용"),
                                fieldWithPath("likes").type(NUMBER).description("좋아요 카운트"),
                                fieldWithPath("selectionStatus").type(BOOLEAN).description("채택 여부"),
                                fieldWithPath("memberId").type(NUMBER).description("작성자 아이디"),
                                fieldWithPath("memberDisplayName").type(STRING).description("작성자 닉네임"),
                                fieldWithPath("questionId").type(NUMBER).description("질문글 아이디"),
                                fieldWithPath("createdTime").type(STRING).description("생성 일자"),
                                fieldWithPath("modifiedTime").type(STRING).description("수정 일자")
                        )
                ));

        verify(answerService, times(1)).findAnswer(id);
    }

    @DisplayName("get AnswerList with pagination")
    @Test
    void testFindAllAnswersWithPagination() throws Exception {
        int page = 0;
        int size = 10;
        Page<AnswerResponseDTO> answerResponseDTOPage = new PageImpl<>(answerResponseDTOList);

        when(answerService.findAllAnswers(page, size)).thenReturn(answerResponseDTOPage);

        ResultActions actions = mockMvc.perform(get("/api/answers")
                .param("page", String.valueOf(page))
                .param("size", String.valueOf(size))
        );

        actions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.content[0].content").value("Test answer"))
                .andExpect(jsonPath("$.totalElements").value(answerResponseDTOList.size()))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.number").value(page))
                .andExpect(jsonPath("$.size").value(answerResponseDTOList.size()))
                .andExpect(jsonPath("$.first").value(true))
                .andExpect(jsonPath("$.last").value(true))
                .andDo(document("Get Answer List with Pagination",
                        requestParameters(
                                parameterWithName("page").description("페이지 번호 (0부터 시작)"),
                                parameterWithName("size").description("한 페이지에 출력될 답변글 수")
                        ),
                        responseFields(
                                fieldWithPath("content[].answerId").type(NUMBER).description("답변글 ID"),
                                fieldWithPath("content[].content").type(STRING).description("답변글 내용"),
                                fieldWithPath("content[].likes").type(NUMBER).description("좋아요 카운트"),
                                fieldWithPath("content[].selectionStatus").type(BOOLEAN).description("채택 여부"),
                                fieldWithPath("content[].memberId").type(NUMBER).description("작성자 아이디"),
                                fieldWithPath("content[].memberDisplayName").type(STRING).description("작성자 닉네임"),
                                fieldWithPath("content[].questionId").type(NUMBER).description("질문글 아이디"),
                                fieldWithPath("content[].createdTime").type(STRING).description("생성 일자"),
                                fieldWithPath("content[].modifiedTime").type(STRING).description("수정 일자"),
                                fieldWithPath("totalElements").type(NUMBER).description("전체 답변글 수"),
                                fieldWithPath("totalPages").type(NUMBER).description("전체 페이지 수"),
                                fieldWithPath("number").type(NUMBER).description("현재 페이지 번호"),
                                fieldWithPath("size").type(NUMBER).description("한 페이지에 출력될 답변글 수"),
                                fieldWithPath("first").type(BOOLEAN).description("현재 페이지가 첫 페이지인지 여부"),
                                fieldWithPath("last").type(BOOLEAN).description("현재 페이지가 마지막 페이지인지 여부"),
                                fieldWithPath("pageable").type(STRING).description("페이지 관련 정보"),
                                fieldWithPath("numberOfElements").type(NUMBER).description("현재 페이지의 요소 개수"),
                                fieldWithPath("sort.sorted").type(BOOLEAN).description("정렬된 상태인지 여부"),
                                fieldWithPath("sort.unsorted").type(BOOLEAN).description("정렬되지 않은 상태인지 여부"),
                                fieldWithPath("sort.empty").type(BOOLEAN).description("정렬 정보가 비어 있는지 여부"),
                                fieldWithPath("empty").type(BOOLEAN).description("페이지가 비어 있는지 여부")
                        )
                ));

        verify(answerService, times(1)).findAllAnswers(page, size);
    }

    @DisplayName("get AnswerList By MemberId with pagination")
    @Test
    void testFindAnswersByMemberIdWithPagination() throws Exception {
        Long memberId = 1L;
        int page = 0;
        int size = 10;
        Page<AnswerResponseDTO> answerResponseDTOPage = new PageImpl<>(answerResponseDTOList);

        when(answerService.findAnswersByMember(memberId, page, size)).thenReturn(answerResponseDTOPage);

        mockMvc.perform(get("/api/answers/member/{memberId}", memberId)
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].content").value("Test answer"))
                .andExpect(jsonPath("$.totalElements").value(answerResponseDTOList.size()))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.number").value(page))
                .andExpect(jsonPath("$.size").value(answerResponseDTOList.size()))
                .andExpect(jsonPath("$.first").value(true))
                .andExpect(jsonPath("$.last").value(true))
                .andDo(document("Get Answer List by MemberId with Pagination",
                        pathParameters(
                                parameterWithName("memberId").description("회원 아이디(PK)")
                        ),
                        requestParameters(
                                parameterWithName("page").description("페이지 번호 (0부터 시작)"),
                                parameterWithName("size").description("한 페이지에 출력될 답변글 수")
                        ),
                        responseFields(
                                fieldWithPath("content[].answerId").type(NUMBER).description("답변글 ID"),
                                fieldWithPath("content[].content").type(STRING).description("답변글 내용"),
                                fieldWithPath("content[].likes").type(NUMBER).description("좋아요 카운트"),
                                fieldWithPath("content[].selectionStatus").type(BOOLEAN).description("채택 여부"),
                                fieldWithPath("content[].memberId").type(NUMBER).description("작성자 아이디"),
                                fieldWithPath("content[].memberDisplayName").type(STRING).description("작성자 닉네임"),
                                fieldWithPath("content[].questionId").type(NUMBER).description("질문글 아이디"),
                                fieldWithPath("content[].createdTime").type(STRING).description("생성 일자"),
                                fieldWithPath("content[].modifiedTime").type(STRING).description("수정 일자"),
                                fieldWithPath("totalElements").type(NUMBER).description("전체 답변글 수"),
                                fieldWithPath("totalPages").type(NUMBER).description("전체 페이지 수"),
                                fieldWithPath("number").type(NUMBER).description("현재 페이지 번호"),
                                fieldWithPath("size").type(NUMBER).description("한 페이지에 출력될 답변글 수"),
                                fieldWithPath("first").type(BOOLEAN).description("현재 페이지가 첫 페이지인지 여부"),
                                fieldWithPath("last").type(BOOLEAN).description("현재 페이지가 마지막 페이지인지 여부"),
                                fieldWithPath("pageable").type(STRING).description("페이지 관련 정보"),
                                fieldWithPath("numberOfElements").type(NUMBER).description("현재 페이지의 요소 개수"),
                                fieldWithPath("sort.sorted").type(BOOLEAN).description("정렬된 상태인지 여부"),
                                fieldWithPath("sort.unsorted").type(BOOLEAN).description("정렬되지 않은 상태인지 여부"),
                                fieldWithPath("sort.empty").type(BOOLEAN).description("정렬 정보가 비어 있는지 여부"),
                                fieldWithPath("empty").type(BOOLEAN).description("페이지가 비어 있는지 여부")
                        )
                ));

        verify(answerService, times(1)).findAnswersByMember(memberId, page, size);
    }

    @DisplayName("get AnswerList By QuestionId with pagination")
    @Test
    void testFindAnswersByQuestionIdWithPagination() throws Exception {
        Long questionId = 1L;
        int page = 0;
        int size = 10;
        Page<AnswerResponseDTO> answerResponseDTOPage = new PageImpl<>(answerResponseDTOList);

        when(answerService.findAnswersByQuestion(questionId, page, size)).thenReturn(answerResponseDTOPage);

        mockMvc.perform(get("/api/answers/question/{questionId}", questionId)
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].content").value("Test answer"))
                .andExpect(jsonPath("$.totalElements").value(answerResponseDTOList.size()))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.number").value(page))
                .andExpect(jsonPath("$.size").value(answerResponseDTOList.size()))
                .andExpect(jsonPath("$.first").value(true))
                .andExpect(jsonPath("$.last").value(true))
                .andDo(document("Get Answer List by QuestionId with Pagination",
                        pathParameters(
                                parameterWithName("questionId").description("질문글 아이디(PK)")
                        ),
                        requestParameters(
                                parameterWithName("page").description("페이지 번호 (0부터 시작)"),
                                parameterWithName("size").description("한 페이지에 출력될 답변글 수")
                        ),
                        responseFields(
                                fieldWithPath("content[].answerId").type(NUMBER).description("답변글 ID"),
                                fieldWithPath("content[].content").type(STRING).description("답변글 내용"),
                                fieldWithPath("content[].likes").type(NUMBER).description("좋아요 카운트"),
                                fieldWithPath("content[].selectionStatus").type(BOOLEAN).description("채택 여부"),
                                fieldWithPath("content[].memberId").type(NUMBER).description("작성자 아이디"),
                                fieldWithPath("content[].memberDisplayName").type(STRING).description("작성자 닉네임"),
                                fieldWithPath("content[].questionId").type(NUMBER).description("질문글 아이디"),
                                fieldWithPath("content[].createdTime").type(STRING).description("생성 일자"),
                                fieldWithPath("content[].modifiedTime").type(STRING).description("수정 일자"),
                                fieldWithPath("totalElements").type(NUMBER).description("전체 답변글 수"),
                                fieldWithPath("totalPages").type(NUMBER).description("전체 페이지 수"),
                                fieldWithPath("number").type(NUMBER).description("현재 페이지 번호"),
                                fieldWithPath("size").type(NUMBER).description("한 페이지에 출력될 답변글 수"),
                                fieldWithPath("first").type(BOOLEAN).description("현재 페이지가 첫 페이지인지 여부"),
                                fieldWithPath("last").type(BOOLEAN).description("현재 페이지가 마지막 페이지인지 여부"),
                                fieldWithPath("pageable").type(STRING).description("페이지 관련 정보"),
                                fieldWithPath("numberOfElements").type(NUMBER).description("현재 페이지의 요소 개수"),
                                fieldWithPath("sort.sorted").type(BOOLEAN).description("정렬된 상태인지 여부"),
                                fieldWithPath("sort.unsorted").type(BOOLEAN).description("정렬되지 않은 상태인지 여부"),
                                fieldWithPath("sort.empty").type(BOOLEAN).description("정렬 정보가 비어 있는지 여부"),
                                fieldWithPath("empty").type(BOOLEAN).description("페이지가 비어 있는지 여부")
                        )
                ));

        verify(answerService, times(1)).findAnswersByQuestion(questionId, page, size);
    }

    @DisplayName("Accept to Answer Status")
    @Test
    void testAcceptAnswer() throws Exception {
        Long questionId = 1L;
        Long answerId = 1L;
        doNothing().when(answerService).acceptAnswer(questionId, answerId);

        mockMvc.perform(put("/api/answers/accept/{questionId}/{answerId}", questionId, answerId))
                .andExpect(status().isOk())
                .andDo(document("Get Answer List by MemberId",
                        pathParameters(
                                parameterWithName("questionId").description("질문글 아이디"),
                                parameterWithName("answerId").description("답글 아이디")
                        )
                ));

        verify(answerService, times(1)).acceptAnswer(questionId, answerId);
    }
}