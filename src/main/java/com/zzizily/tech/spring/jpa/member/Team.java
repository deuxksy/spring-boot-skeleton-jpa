package com.zzizily.tech.spring.jpa.member;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zzizily.tech.spring.jpa.cmmons.BaseObject;
import com.zzizily.tech.spring.jpa.member.Member;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
public class Team extends BaseObject {

  @Id
  @GeneratedValue
  private Long id;
  private String name;

  @OneToMany(mappedBy = "team") //조회만 한다
  @JsonBackReference
  private List<Member> members = new ArrayList<>();
}
