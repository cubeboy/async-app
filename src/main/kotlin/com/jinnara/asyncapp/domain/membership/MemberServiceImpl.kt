package com.jinnara.asyncapp.domain.membership

import com.jinnara.asyncapp.domain.membership.command.Member
import com.jinnara.asyncapp.domain.membership.command.QMember
import com.jinnara.asyncapp.infra.repository.MemberRepository
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Service
class MemberServiceImpl(private val repository: MemberRepository) {
  fun getPageableMember(pageable: Pageable): Mono<PageImpl<Member>> {
    var count = 0L
    return repository.query {
      it.select(QMember.member.memberId.count())
        .from(QMember.member)
    }.one().doOnNext {count = it}
      .flatMap {
        this.repository.query {
          it.select(QMember.member)
            .from(QMember.member)
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
        }.all().collectList()
      }.flatMap {
        PageImpl(it, pageable, count).toMono()
      }
  }
}
