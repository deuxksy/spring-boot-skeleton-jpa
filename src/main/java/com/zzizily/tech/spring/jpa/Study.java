package com.zzizily.tech.spring.jpa;

import com.zzizily.tech.spring.jpa.member.Account;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Study {

  @Id
  @GeneratedValue
  private Long id;
  private String name;

//  @ManyToOne
  @ManyToOne
  private Account owner;
}
