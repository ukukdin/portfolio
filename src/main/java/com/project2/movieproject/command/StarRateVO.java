package com.project2.movieproject.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class StarRateVO {
	
	private int sr_key;
	private int user_key;
	private String movie_koficCd;
	private int sr_rate;

}
