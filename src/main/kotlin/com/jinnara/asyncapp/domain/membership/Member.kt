package com.jinnara.asyncapp.domain.membership

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
class Member(
  @Id
  val memberId:Long? = null,
  val memberName:String? = null,
  val emailAddress:String? = null,
  val password:String? = null
)
