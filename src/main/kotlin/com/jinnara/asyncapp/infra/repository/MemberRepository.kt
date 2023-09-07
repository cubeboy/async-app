package com.jinnara.asyncapp.infra.repository

import com.jinnara.asyncapp.domain.membership.Member
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface MemberRepository: ReactiveCrudRepository<Member, Long> {
}