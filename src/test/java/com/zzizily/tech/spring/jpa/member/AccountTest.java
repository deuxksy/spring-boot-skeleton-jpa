package com.zzizily.tech.spring.jpa.member;

import com.zzizily.tech.spring.jpa.member.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AccountTest {

  @Autowired
  private TestEntityManager entityManager;

  @Test
  public void tsetAccount() {
    Account account = Account.builder()
            .username("deuxksy")
            .password("qwe123")
            .build();
    entityManager.persistAndFlush(account);
  }
}