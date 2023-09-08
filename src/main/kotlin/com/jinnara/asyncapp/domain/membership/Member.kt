package com.jinnara.asyncapp.domain.membership

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
class Member(
  @Id
  var memberId:Long? = null,
  var memberName:String? = null,
  var emailAddress:String? = null,
  var password:String? = null
)
