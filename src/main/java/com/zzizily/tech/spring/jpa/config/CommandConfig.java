package com.zzizily.tech.spring.jpa.config;

import com.zzizily.tech.spring.jpa.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
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

    log.info("{},{}",ksy.getName(), ksy.getTeam().getName());

    Member findKsy = entityManager.find(Member.class, ksy.getId());
    log.info("{},{}",findKsy.getName(), findKsy.getTeam().getName());

    findKsy.setTeam(teamService);

    log.info("{},{}",findKsy.getName(), findKsy.getTeam().getName());
  }
}