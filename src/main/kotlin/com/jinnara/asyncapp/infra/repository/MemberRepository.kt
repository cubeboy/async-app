package com.jinnara.asyncapp.infra.repository

import com.jinnara.asyncapp.domain.membership.Member
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.data.repository.query.QueryByExampleExecutor
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface MemberRepository: R2dbcRepository<Member, Long>{
}
