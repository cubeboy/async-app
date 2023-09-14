package com.jinnara.asyncapp.domain.membership.command

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table(name = "member")
data class Member(
  @Id
  @Column("member_id")
  val memberId:Long? = null,
  @Column("member_name")
  val memberName:String? = null,
  @Column("email_address")
  val emailAddress:String? = null,
  @Column("password")
  val password:String? = null
)
