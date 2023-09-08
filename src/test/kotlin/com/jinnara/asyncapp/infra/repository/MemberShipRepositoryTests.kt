package com.jinnara.asyncapp.infra.repository

import com.jinnara.asyncapp.config.TestDbConfig
import com.jinnara.asyncapp.domain.membership.Member
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest
import org.springframework.data.domain.*
import org.springframework.test.context.ContextConfiguration
import reactor.kotlin.test.test

@DataR2dbcTest
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
    val savedMember = repository.save(member)
    savedMember.test().expectSubscription().assertNext {
      assertEquals(4, it.memberId)
      assertEquals("user04", it.memberName)
    }.verifyComplete()
  }

  @Test
  fun findMembersTest() {
    val members = repository.findAll()
    members.test().expectSubscription()
      .expectNextCount(3)
      .verifyComplete()
  }

  @Test
  fun exampleExecutorTest() {
    val member = Member(memberName = "user")
    val matcher = ExampleMatcher.matching()
      .withIgnoreNullValues()
      .withStringMatcher(ExampleMatcher.StringMatcher.STARTING)
    val sort = Sort.by(
      Sort.Order.asc("memberName")
    )
    val pageable = PageRequest.of(0, 5, sort)
    val example = Example.of(member, matcher)

    val members = repository.findBy(
      example
    ) { query -> query.sortBy(sort).page(pageable) }

    members.test()
      .expectSubscription()
      .assertNext {
        assertTrue(it.totalElements > 0)
        assertEquals(1, it.content[0].memberId)
        assertEquals(2, it.content[1].memberId)
        assertEquals(3, it.content[2].memberId)
      }
      .verifyComplete()
  }
}
