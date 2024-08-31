package com.oasis.hworld.cart.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 장바구니 상품 RequestDTO
 * @author 조영욱
 * @since 2024.08.31
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.31  	조영욱        최초 생성
 * </pre>
 */
@Getter
@Setter
public class CartItemRequestDTO {
    // 상품 ID
    private int itemId;
}
