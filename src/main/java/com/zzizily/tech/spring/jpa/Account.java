package com.zzizily.tech.spring.jpa;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

@Entity
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "account_sequence")
  private Long id;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  private String password;

  @Temporal(TemporalType.TIMESTAMP)
  private Date created = new Date();

  private String yes;

  @Transient
  private String no;
}
