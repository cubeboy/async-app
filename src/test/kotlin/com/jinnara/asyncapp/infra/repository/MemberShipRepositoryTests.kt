package com.jinnara.asyncapp.infra.repository

import com.infobip.spring.data.r2dbc.EnableQuerydslR2dbcRepositories
import com.jinnara.asyncapp.config.TestDbConfig
import com.jinnara.asyncapp.domain.membership.MemberServiceImpl
import com.jinnara.asyncapp.domain.membership.command.Member
import com.jinnara.asyncapp.domain.membership.command.QMember.member
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.ContextConfiguration
import reactor.kotlin.test.test

@DataR2dbcTest
@EnableQuerydslR2dbcRepositories
@ContextConfiguration(classes = [TestDbConfig::class])
class MemberShipRepositoryTests {
  @Autowired
  lateinit var repository: MemberRepository

  lateinit var impl: MemberServiceImpl

  @BeforeEach
  fun setup() {
    impl = MemberServiceImpl(repository)
  }

  @Test
  fun objectBindTest() {
    assertNotNull(repository)
    assertNotNull(impl)
  }

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

  @Test
  fun findPageableQMembersTest() {
    val pageable = PageRequest.of(0, 5)
    val members = impl.getPageableMember(pageable)
    members.test()
      .expectSubscription()
      .assertNext {
        assertEquals(3, it.totalElements)
        assertEquals(1, it.totalPages)
        assertEquals(3, it.content.size)
      }
      .verifyComplete()
  }
}
