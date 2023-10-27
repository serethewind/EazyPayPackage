package com.eazypay.accounts.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "accounts")
@Getter
@Setter
@Slf4j
public class AccountsContactInfoDto{

   private String message;
   private Map<String, String> contactDetails;
   private List<String> onCallSupport;

}
