package com.zzizily.tech.spring.jpa.member;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

  @Autowired
  MemberRepository memberRepository;

  @Autowired
  TeamRepository teamRepository;

  @Test
  public void testMemberRepository() {
    Team team = Team.builder().name("서비스팀").build();
    Member member = Member.builder().name("김석영").age(38).team(team).memberType(MemberType.ADMIN).build();

    teamRepository.save(team);
    memberRepository.save(member);

    assertThat(memberRepository.findByAge(38).get(1)).isEqualTo(1);
  }
}