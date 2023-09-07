package com.jinnara.asyncapp.domain.membership

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
class Member(
  @Id
  var memberId:Long? = null,
  var memberName:String = "",
  var emailAddress:String = "",
  var password:String = ""
)
