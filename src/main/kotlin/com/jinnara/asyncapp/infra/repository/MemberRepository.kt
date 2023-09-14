package com.jinnara.asyncapp.infra.repository

import com.infobip.spring.data.r2dbc.QuerydslR2dbcRepository
import com.jinnara.asyncapp.domain.membership.command.Member

interface MemberRepository: QuerydslR2dbcRepository<Member, Long>{

}
