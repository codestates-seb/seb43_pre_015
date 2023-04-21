package com.pre015.server.comment.entity;


import com.pre015.server.comment.controller.CommentController;
import com.pre015.server.comment.mapper.CommentMapper;
import com.pre015.server.comment.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(CommentController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class CommentDeleteTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;
    @MockBean
    private CommentMapper commentMapper;

    @Test
    @DisplayName("Delete Comment Test")
    void commentDeleteTest() throws Exception {
        Long commentId = 1L;
        BDDMockito.doNothing().when(commentService).deleteComment(Mockito.anyLong());
        ResultActions actions = mockMvc.perform(RestDocumentationRequestBuilders.delete("/comments/{comment_id}", commentId)
                .accept(MediaType.APPLICATION_JSON));

        actions.andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcRestDocumentation.document("deleteComment",
                        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                        Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                        RequestDocumentation.pathParameters(
                                RequestDocumentation.parameterWithName("comment_id").description("댓글 식별자")
                        )));
    }
}
