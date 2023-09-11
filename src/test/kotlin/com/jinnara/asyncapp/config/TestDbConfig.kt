package com.jinnara.asyncapp.config

import com.infobip.spring.data.r2dbc.EnableQuerydslR2dbcRepositories
import com.querydsl.sql.H2Templates
import com.querydsl.sql.SQLTemplates
import io.r2dbc.spi.ConnectionFactory
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.core.io.ClassPathResource
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator

@TestConfiguration
class TestDbConfig {

  @Bean
  fun sqlTemplates(): SQLTemplates {
    return H2Templates()
  }

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
