package com.oasis.hworld.contest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 게시글 상세 Response DTO
 * @author 조영욱
 * @since 2024.09.01
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.01  	정은찬        최초 생성
 * </pre>
 */
@Setter
@Getter
@ToString
@Builder
public class PostResponseDTO {
    // 게시글 ID
    private int postId;
    // 게시글 제목
    private String title;
    // 게시글 작성일
    private String createdAt;
    // 코디 이미지
    private String imageUrl;
    // 작성자 닉네임
    private String nickname;
    // 추천수
    private int likeCount;
    // 코디 착용 아이템
    private ItemsDTO items;
    // 댓글 리스트
    private List<ReplyDTO> replyList;

    public static PostResponseDTO from(PostDetailDTO postDetailDTO, ItemsDTO itemsDTO, List<ReplyDTO> replyDTOList) {
        return PostResponseDTO.builder()
                .postId(postDetailDTO.getPostId())
                .title(postDetailDTO.getTitle())
                .createdAt(postDetailDTO.getCreatedAt())
                .imageUrl(postDetailDTO.getImageUrl())
                .nickname(postDetailDTO.getNickname())
                .likeCount(postDetailDTO.getLikeCount())
                .items(itemsDTO)
                .replyList(replyDTOList)
                .build();
    }
}
