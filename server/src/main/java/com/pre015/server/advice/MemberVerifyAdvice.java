package com.pre015.server.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.pre015.server.answer.entity.Answer;
import com.pre015.server.answer.repository.AnswerRepository;
import com.pre015.server.auth.jwt.JwtTokenizer;
import com.pre015.server.comment.entity.Comment;
import com.pre015.server.comment.repository.CommentRepository;
import com.pre015.server.exception.BusinessLogicException;
import com.pre015.server.exception.ExceptionCode;
import com.pre015.server.question.entity.Question;
import com.pre015.server.question.repository.QuestionRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.ContentCachingRequestWrapper;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
@Aspect
public class MemberVerifyAdvice extends VerifyAdvice {
    private final ObjectMapper objectMapper;
    private final QuestionRepository questionRepository;
    private final CommentRepository commentRepository;
    private final AnswerRepository answerRepository;

    @Autowired
    public MemberVerifyAdvice(@Lazy JwtTokenizer jwtTokenizer, ObjectMapper objectMapper, QuestionRepository questionRepository, CommentRepository commentRepository, AnswerRepository answerRepository) {
        super(jwtTokenizer);
        this.objectMapper = objectMapper;
        this.questionRepository = questionRepository;
        this.commentRepository = commentRepository;
        this.answerRepository = answerRepository;
    }

    @Pointcut(
            "execution(* com.pre015.server.member.controller.MemberController.patchMember(..)) || " +
                    "execution(* com.pre015.server.member.controller.MemberController.deleteMember(..)) || " +
                    "execution(* com.pre015.server.question.controller.QuestionController.patchQuestion(..)) || " +
                    "execution(* com.pre015.server.question.controller.QuestionController.deleteQuestion(..)) || " +
                    "execution(* com.pre015.server.answer.controller.AnswerController.updateAnswer(..)) || " +
                    "execution(* com.pre015.server.answer.controller.AnswerController.deleteAnswer(..)) || " +
                    "execution(* com.pre015.server.comment.controller.CommentController.updateComment(..)) || " +
                    "execution(* com.pre015.server.comment.controller.CommentController.deleteComment(..))"
    )
    public void verifyMySelfWithPointcut(){}

    @Before(value = "verifyMySelfWithPointcut()")
    public void verifyMySelf(JoinPoint joinPoint) throws IOException {
        String jws = getHeader("Authorization").substring(7);
        long memberIdFromJws = getMemberIdFromJws(jws);
        long memberIdFromRequest = extractMemberId();
        checkSameMember(memberIdFromJws, memberIdFromRequest);
    }

    private long extractMemberId() throws IOException {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        String servletPath = request.getServletPath();

        ContentCachingRequestWrapper wrapper = new ContentCachingRequestWrapper(request);
        byte[] body = wrapper.getContentAsByteArray();
        String bodyJson = objectMapper.readTree(body).toString();

        if (servletPath.contains("/members")) {
            String substr = servletPath.substring(servletPath.indexOf("/members") + 9);
            return Long.parseLong(substr);
        } else if (bodyJson.contains("memberId")) {
            return JsonPath.parse(bodyJson).read("$.memberId", Long.class);
        } else if (servletPath.contains("/questions")) {
            String substr = servletPath.substring(servletPath.indexOf("/questions") + 11);
            Long questionId = Long.parseLong(substr);
            Optional<Question> optionalQuestion = questionRepository.findById(questionId);
            Question question = optionalQuestion.orElseThrow(() -> new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
            return question.getMemberId();
        } else if (servletPath.contains("/answers")) {
            String substr = servletPath.substring(servletPath.indexOf("/answers") + 9);
            Long answersId = Long.parseLong(substr);
            Optional<Answer> optionalAnswer = answerRepository.findById(answersId);
            Answer answer = optionalAnswer.orElseThrow(() -> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
            return answer.getMember().getMemberId();
        } else if (servletPath.contains("/comments")) {
            String substr = servletPath.substring(servletPath.indexOf("/comments") + 10);
            Long commentId = Long.parseLong(substr);
            Optional<Comment> optionalComment = commentRepository.findById(commentId);
            Comment comment = optionalComment.orElseThrow(() -> new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));
            return comment.getMember().getMemberId();
        } else {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
        }
    }

    private void checkSameMember(long memberIdFromJws, long memberIdFromRequest) {
        if (memberIdFromJws != memberIdFromRequest) {
            throw new AccessDeniedException(HttpStatus.FORBIDDEN.toString());
        }
    }
}
