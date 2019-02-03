package com.zzizily.tech.spring.jpa.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class MemberDTO {
  private String name;
  private Integer age;
  private String teamName;
}
