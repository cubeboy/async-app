package com.jinnara.asyncapp.infra.repository

import com.infobip.spring.data.r2dbc.EnableQuerydslR2dbcRepositories
import com.jinnara.asyncapp.config.TestDbConfig
import com.jinnara.asyncapp.domain.membership.Member
import com.jinnara.asyncapp.domain.membership.QMember.member
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.test.context.ContextConfiguration
import reactor.kotlin.test.test

@DataR2dbcTest
@EnableQuerydslR2dbcRepositories
@ContextConfiguration(classes = [TestDbConfig::class])
class MemberShipRepositoryTests {
  @Autowired
  lateinit var repository: MemberRepository

  @Test
  fun objectInitTest() {
    val member = Member(
      memberName = "user04",
      emailAddress = "user@async.com",
      password = "password123"
    )
    repository.save(member)
  }

  @Test
  fun findMembersTest() {
    val members = repository.findAll()
    members.test().expectSubscription()
      .expectNextCount(3)
      .verifyComplete()

  }

  @Test
  fun findQMembersTest() {
    val members = repository.query {
      it.select(member)
        .from(member)
        .orderBy(member.memberName.asc())
    }.all()

    members.test()
      .expectNextCount(3)
      .verifyComplete()
  }
}
