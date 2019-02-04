package com.zzizily.tech.spring.jpa.member;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.zzizily.tech.spring.jpa.cmmons.BaseObject;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
@NamedQueries({
  @NamedQuery(
    name = "Member.findByName",
    query = "SELECT m FROM Member m where m.name = :name"
  )
})
public class Member extends BaseObject {

  @Id
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "member_sequence")
  private Long id;
  private String name;
  private Integer age;

//  @ManyToOne(fetch = FetchType.LAZY)
  @ManyToOne
  @JoinColumn
  @JsonManagedReference
  private Team team;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private MemberType memberType;
}
