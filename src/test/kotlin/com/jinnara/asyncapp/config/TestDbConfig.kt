package com.jinnara.asyncapp.config

import io.r2dbc.spi.ConnectionFactory
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.core.io.ClassPathResource
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator

@TestConfiguration
class TestDbConfig {
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
