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
public class RentVO {
	private Integer rent_key;
	private String user_id;
	private String movie_koficCd;
	private LocalDateTime rent_startdate;
	private LocalDateTime rent_endDate;
	private String rent_status;
}
