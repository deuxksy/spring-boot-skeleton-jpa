package com.zzizily.tech.spring.jpa.web;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.zzizily.tech.spring.jpa.member.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.parser.Entity;

@RestController
@AllArgsConstructor
public class APIController {

  private EntityManager entityManager;
  private MemberRepository memberRepository;
  private TeamRepository teamRepository;

  @PostConstruct
  private void postConstruct() {
    Team service = Team.builder().name("서비스팀").build();
    Team backend = Team.builder().name("백엔드팀").build();

    teamRepository.save(service);
    teamRepository.save(backend);

    memberRepository.save(Member.builder().name("이승식").age(38).team(service).memberType(MemberType.ADMIN).build());
    memberRepository.save(Member.builder().name("김석영").age(38).team(backend).memberType(MemberType.USER).build());
  }

  @GetMapping("/api/test")
  public ResponseEntity test() {
//    entityManager.createQuery("");
    return ResponseEntity.ok().body(null);
  }

  @GetMapping("/api/team/{id}")
  public ResponseEntity team(@PathVariable Long id) {
    return ResponseEntity.ok().body(teamRepository.findById(id));
  }

  @GetMapping("/api/member/{id}")
  public ResponseEntity member(@PathVariable Long id) {
    Member member = memberRepository.findById(id).get();
    ModelMapper modelMapper = new ModelMapper();
    MemberDTO memberDTO = modelMapper.map(member, MemberDTO.class);
    return ResponseEntity.ok().body(memberDTO);
  }
}
