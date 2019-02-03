package com.zzizily.tech.spring.jpa.member;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManagerFactory;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MemberTest {
  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private EntityManagerFactory entityManagerFactory;

  @Test
  public void testMember() {

    Team teamBackEnd = Team.builder()
            .name("백엔드팀")
            .build();
    entityManager.persist(teamBackEnd);

    Team teamService = Team.builder()
            .name("서비스팀")
            .build();
    entityManager.persist(teamService);

    Member lss = Member.builder()
            .age(38)
            .name("이승식")
            .team(teamService)
            .memberType(MemberType.ADMIN)
            .build();

    Member ksy = Member.builder()
            .age(38)
            .name("김석영")
            .team(teamBackEnd)
            .memberType(MemberType.ADMIN)
            .build();

    entityManager.persist(ksy);
    entityManager.persist(lss);

    entityManager.flush();
    entityManager.clear();

    assertThat(entityManager.find(Member.class, lss.getId()).getName()).isEqualTo("이승식");
    assertThat(entityManager.find(Member.class, ksy.getId()).getName()).isEqualTo("김석영");
    assertThat(entityManager.find(Member.class, ksy.getId()).getTeam().getName()).isEqualTo("백엔드팀");

    Member findKsy = entityManager.find(Member.class, ksy.getId());
    findKsy.setTeam(teamService);

    assertThat(findKsy.getTeam().getName()).isEqualTo("서비스팀");
  }

  @Test
  public void testPaging() {

  }
}