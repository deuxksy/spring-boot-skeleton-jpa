package com.zzizily.tech.spring.jpa.config;

import com.zzizily.tech.spring.jpa.member.Account;
import com.zzizily.tech.spring.jpa.member.Member;
import com.zzizily.tech.spring.jpa.member.MemberType;
import com.zzizily.tech.spring.jpa.member.Team;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@Slf4j
@Transactional
public class CommandConfig implements ApplicationRunner, CommandLineRunner {

  @Value("${spring.profiles.active}")
  private String profiles;

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public void run(String... args) throws Exception {
    Account account = Account.builder()
            .username("deuxksy")
            .password("qwe123")
            .build();
    entityManager.persist(account);
    entityManager.flush();
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    /*Team teamService = Team.builder()
            .name("서비스팀")
            .build();
    entityManager.persist(teamService);

    Member lss = Member.builder()
            .age(38)
            .name("이승식")
            .team(teamService)
            .memberType(MemberType.ADMIN)
            .build();
    entityManager.persist(lss);

    String jpql = "SELECT m FROM Member m JOIN fetch m.team";

    List<Member> members = entityManager.createQuery(jpql, Member.class).getResultList();
    log.info("{}", members.size());
    members.forEach(member -> {
      log.info("{}", member);
    });
    Member queryKsy = entityManager.createQuery("Member.findByName", Member.class)
            .setParameter("name", "김석영")
            .getSingleResult();
    log.info("{}", queryKsy);*/
  }
}