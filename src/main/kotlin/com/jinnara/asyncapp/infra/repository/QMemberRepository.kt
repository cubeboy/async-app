package com.jinnara.asyncapp.infra.repository

import com.infobip.spring.data.r2dbc.QuerydslR2dbcRepository
import com.jinnara.asyncapp.domain.membership.Member

interface QMemberRepository: QuerydslR2dbcRepository<Member, Long> {
}
