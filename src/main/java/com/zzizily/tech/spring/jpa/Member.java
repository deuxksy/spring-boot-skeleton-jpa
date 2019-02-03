package com.zzizily.tech.spring.jpa;

import com.zzizily.tech.spring.jpa.cmmons.BaseObject;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseObject {

  @Id
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "member_sequence")
  private Long id;
  private String name;
  private int age;

//  @ManyToOne(fetch = FetchType.LAZY)
  @ManyToOne
  @JoinColumn
  private Team team;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private MemberType memberType;
}
