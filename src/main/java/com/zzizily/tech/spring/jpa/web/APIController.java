package com.zzizily.tech.spring.jpa.web;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.zzizily.tech.spring.jpa.member.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class APIController {

  @PersistenceContext
  private EntityManager entityManager;
  private MemberRepository memberRepository;
  private TeamRepository teamRepository;
  private ModelMapper modelMapper;

  @PostConstruct
  private void postConstruct() {
    Team service = Team.builder().name("서비스팀").build();
    Team backend = Team.builder().name("백엔드팀").build();

    teamRepository.save(service);
    teamRepository.save(backend);

    memberRepository.save(Member.builder().name("이승식").age(38).team(service).memberType(MemberType.ADMIN).build());
    memberRepository.save(Member.builder().name("김석영").age(38).team(backend).memberType(MemberType.USER).build());
  }

  @GetMapping("/api/fetch")
  public ResponseEntity fetch() {
    String jpql = "SELECT m FROM Member m JOIN fetch m.team";
    List<Member> members = entityManager.createQuery(jpql, Member.class).getResultList();
    java.lang.reflect.Type targetListType = new TypeToken<List<MemberDTO>>() {}.getType();
    List<MemberDTO> membersDTO = modelMapper.map(members, targetListType);
    return ResponseEntity.ok().body(membersDTO);
  }

  @GetMapping("/api/named/{name}")
  public ResponseEntity named(@PathVariable String name) {
    List<Member> members = entityManager.createNamedQuery("Member.findByName", Member.class)
            .setParameter("name", name)
            .getResultList();

    java.lang.reflect.Type targetListType = new TypeToken<List<MemberDTO>>() {}.getType();
    List<MemberDTO> membersDTO = modelMapper.map(members, targetListType);
    return ResponseEntity.ok().body(membersDTO);
  }

  @GetMapping("/api/team/{id}")
  public ResponseEntity team(@PathVariable Long id) {
    return ResponseEntity.ok().body(teamRepository.findById(id));
  }

  @GetMapping("/api/member/id/{id}")
  public ResponseEntity member(@PathVariable Long id) {
    Member member = memberRepository.findById(id).get();

    MemberDTO memberDTO = modelMapper.map(member, MemberDTO.class);
    return ResponseEntity.ok().body(memberDTO);
  }

  @GetMapping("/api/member/name/{name}")
  public ResponseEntity member(@PathVariable String name) {

    Member member = memberRepository.findByName(name);

    MemberDTO memberDTO = modelMapper.map(member, MemberDTO.class);
    return ResponseEntity.ok().body(memberDTO);
  }
}
