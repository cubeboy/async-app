package com.jinnara.asyncapp.infra.repository

import com.jinnara.asyncapp.domain.membership.Member
import io.r2dbc.spi.ConnectionFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.core.io.ClassPathResource
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator
import org.springframework.test.context.junit.jupiter.SpringExtension
import reactor.kotlin.test.test


@DataR2dbcTest
@ExtendWith(SpringExtension::class)
class MemberShipRepositoryTests {
  @TestConfiguration
  class Config {
    @Bean
    fun initializer(connectionFactory: ConnectionFactory?): ConnectionFactoryInitializer {
      val initializer = ConnectionFactoryInitializer()
      initializer.setConnectionFactory(connectionFactory!!)
      val populator = CompositeDatabasePopulator()
      populator.addPopulators(ResourceDatabasePopulator(ClassPathResource("createTable.sql")))
      initializer.setDatabasePopulator(populator)
      return initializer
    }
  }

  @Autowired
  lateinit var repository: MemberRepository

  @Test
  fun objectInitTest() {
    val member = Member(
      memberName = "user",
      emailAddress = "user@async.com",
      password = "password123"
    )
    val savedMember = repository.save(member)
    savedMember.test().expectSubscription().assertNext {
      assertEquals(4, it.memberId)
      assertEquals("user", it.memberName)
    }.verifyComplete()
  }

  @Test
  fun findMembersTest() {
    val members = repository.findAll()
    members.test().expectSubscription()
      .expectNextCount(3)
      .verifyComplete()
  }
}