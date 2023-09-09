package com.jinnara.asyncapp.infra.repository

import com.jinnara.asyncapp.domain.membership.Member
import org.springframework.data.r2dbc.repository.R2dbcRepository

interface MemberRepository: R2dbcRepository<Member, Long>{
}
