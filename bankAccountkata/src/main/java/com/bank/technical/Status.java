package com.bank.technical;

import com.bank.technical.staticContent.StatusBasicEum;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class Status {
	StatusBasicEum status;
}
