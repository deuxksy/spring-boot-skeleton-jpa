package com.zzizily.tech.spring.jpa;

import com.zzizily.tech.spring.jpa.cmmons.BaseObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
public class Team extends BaseObject {

  @Id
  @GeneratedValue
  private Long id;
  private String name;

  @OneToMany(mappedBy = "team") //조회만 한다
  private List<Member> members = new ArrayList<>();
}
