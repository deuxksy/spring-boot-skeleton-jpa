package com.zzizily.tech.spring.jpa;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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
  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  private Date created;

  // composite type 의 column 생성
  @Embedded
  private Address address;

  @AttributeOverrides({
    @AttributeOverride(name="street", column=@Column(name="home_street")),
    @AttributeOverride(name="city", column=@Column(name="home_city")),
    @AttributeOverride(name="state", column=@Column(name="home_state")),
    @AttributeOverride(name="zipCode", column=@Column(name="home_zipCode")),
  })
  private Address homeAddress;

  @AttributeOverrides({
    @AttributeOverride(name="street", column=@Column(name="office_street")),
    @AttributeOverride(name="city", column=@Column(name="office_city")),
    @AttributeOverride(name="state", column=@Column(name="office_state")),
    @AttributeOverride(name="zipCode", column=@Column(name="office_zipCode")),
  })
  private Address officeAddress;

  // db column 생성
  private String yes;

  // db column 안생성
  @Transient
  private String no;
}
