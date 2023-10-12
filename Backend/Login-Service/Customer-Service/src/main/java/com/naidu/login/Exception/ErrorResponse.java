package com.naidu.login.Exception;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

	private LocalDate timestamp;
	private String message;
	private String httpCodeMessage;

}
