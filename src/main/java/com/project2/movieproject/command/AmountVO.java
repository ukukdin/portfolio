package com.project2.movieproject.command;

import lombok.Data;

@Data
public class AmountVO {
	private Integer total, tax_free, vat, point, discount; // 전체 결제 금액, 비과세 금액, 부과세 금액, 사용한 포인트 금액, 할인 금액
}
