package com.oasis.hworld.contest.service;

import com.oasis.hworld.common.exception.CustomException;
import com.oasis.hworld.contest.dto.*;
import com.oasis.hworld.contest.mapper.ContestMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.oasis.hworld.common.exception.ErrorCode.*;

/**
 * 콘테스트 서비스 구현체
 * @author 정은찬
 * @since 2024.08.31
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ------------------------------------------------------
 * 2024.08.31  	정은찬        최초 생성
 * 2024.09.01   정은찬        파라미터를 통해 콘테스트 게시글 목록 조회 메소드 통합, 게시글 상세 조회 메소드 추가
 * 2024.09.02   정은찬        회원 ID를 통한 코디 목록 조회 메소드, 진행중인 콘테스트 게시글 등록 메소드, 댓글 등록 메소드 추가
 * </pre>
 */
@Service
@Log4j
@RequiredArgsConstructor
public class ContestServiceImpl implements ContestService {

    private final ContestMapper mapper;

    /**
     * 콘테스트 게시글 목록 조회
     *
     * @author 정은찬
     */
    public List<PostSummaryDTO> getContestPostList(String contestStatus, String sortBy) {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(currentDate);

        return mapper.selectContestPostList(formattedDate, sortBy, contestStatus);
    }

    /**
     * 게시글 ID를 통해 콘테스트 게시글 상세 조회
     *
     * @author 정은찬
     */
    public PostDetailResponseDTO getPostDetail(int postId) {
        PostDetailResponseDTO postDetail = mapper.selectContestPostDetailByPostId(postId);

        if (postDetail == null) {
            throw new CustomException(POST_NOT_EXIST);
        }
        return postDetail;
    }

    /**
     * 회원 ID를 통해 코디 목록 조회
     *
     * @author 정은찬
     */
    public List<CoordinationResponseDTO> getCoordinationList(int memberId) {
        List<CoordinationResponseDTO> coordinationList = mapper.selectCoordinationListByMemberId(memberId);

        return coordinationList;
    }

    /**
     * 진행중인 콘테스트 게시글 등록
     *
     * @author 정은찬
     */
    public boolean addContestPost(int memberId, PostRequestDTO postRequestDTO) {
        int coordinationId = postRequestDTO.getCoordinationId();

        // 이미 같은 코디 게시글 존재
        if(mapper.selectContestPostByMemberIdAndCoordinationId(memberId, coordinationId) != null) {
            return false;
        }
        
       return mapper.insertContestPost(memberId, postRequestDTO) == 1;
    }

    /**
     * 콘테스트 게시글 댓글 등록
     *
     * @author 정은찬
     */
    public boolean addReply(int memberId, ReplyRequestDTO replyRequestDTO) {
        return mapper.insertReply(memberId, replyRequestDTO) == 1;
    }
}
