package com.zzizily.tech.spring.jpa.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepositorySupport extends QuerydslRepositorySupport {
  private final JPAQueryFactory queryFactory;

  public MemberRepositorySupport(JPAQueryFactory queryFactory) {
    super(Member.class);
    this.queryFactory = queryFactory;
  }

  public List<Member> findByName(String name) {
    return queryFactory.selectFrom(member);
  }
}
