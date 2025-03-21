package com.example.member;

import com.example.member.domain.Member;
import com.example.member.repository.JdbcMemberRepository;
import com.example.member.repository.JpaMemberRepository;
import com.example.member.repository.MemberRepository;
import com.example.member.repository.MemoryMemberRepository;
import com.example.member.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

@Configuration
public class SpringConfig {

//    private final DataSource dataSource;
    private EntityManager em;

    private final MemberRepository memberRepository;


//    @Autowired
//    public SpringConfig(DataSource dataSource){
////        this.dataSource = dataSource;
////        this.em = em;
//    }

    @Autowired
    public SpringConfig(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    @Bean
    public MemberService memberService() {
//        return new MemberService(memberRepository());
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
////        return new MemoryMemberRepository();
////        return new JdbcMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
}
