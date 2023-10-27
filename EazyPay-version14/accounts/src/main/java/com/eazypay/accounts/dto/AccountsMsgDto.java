package com.eazypay.accounts.dto;

import lombok.Builder;

/**
 *
 * @param accountNumber
 * @param name
 * @param email
 * @param mobileNumber
 */

@Builder
public record AccountsMsgDto(Long accountNumber, String name, String email, String mobileNumber) {
}
