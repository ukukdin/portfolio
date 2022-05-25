package com.project2.movieproject.command;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuyVO {
	private Integer buy_key;
	private String user_id;
	private String movie_koficCd;
	private LocalDateTime buy_date;
}
