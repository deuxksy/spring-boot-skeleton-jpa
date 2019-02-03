package com.zzizily.tech.spring.jpa.member;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
  List<Member> findByName(String name);

  List<Member> findByAge(Integer age);
}
