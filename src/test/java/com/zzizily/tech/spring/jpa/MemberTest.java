package com.zzizily.tech.spring.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MemberTest {
  @Autowired
  private TestEntityManager entityManager;

  @Test
  public void testMember () {

    Team team = Team.builder()
            .name("서비스팀")
            .build();
    Member ksy = Member.builder()
            .name("김석영")
            .age(38)
            .team(team)
            .build();
    Member lss = Member.builder()
            .name("이승식")
            .age(38)
            .team(team)
            .build();
    entityManager.persist(ksy);
    entityManager.persist(lss);
    assertThat(entityManager.find(Member.class, ksy.getId()).getName()).isEqualTo("김석영");
    assertThat(entityManager.find(Member.class, lss.getId()).getName()).isEqualTo("이승식");
    entityManager.flush();
  }
}