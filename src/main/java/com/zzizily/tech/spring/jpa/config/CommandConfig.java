package com.zzizily.tech.spring.jpa.config;

import com.zzizily.tech.spring.jpa.Account;
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
//    log.error("{},{}", profiles, args);
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
//    log.error("{},{}", profiles, args);
//    log.error("{},{},{}", args.getNonOptionArgs(), args.getOptionNames(), args.getSourceArgs());
    Account account = Account.builder()
            .username("deuxksy")
            .password("qwe123")
            .build();

    Session session = entityManager.unwrap(Session.class);
    session.save(account);
//    entityManager.persist(account);
  }
}