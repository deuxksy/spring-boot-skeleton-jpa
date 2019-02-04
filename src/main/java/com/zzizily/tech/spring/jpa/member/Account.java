package com.zzizily.tech.spring.jpa.member;

import com.zzizily.tech.spring.jpa.Address;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "account_sequence")
  private Long id;

  @Column(nullable = false, unique = true, length = 50)
  private String username;

  @Column(nullable = false)
  private String password;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(insertable = false, updatable = false)
  @CreationTimestamp
  private Date created;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(insertable = false, updatable = false)
  @UpdateTimestamp
  private Date updated;

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
