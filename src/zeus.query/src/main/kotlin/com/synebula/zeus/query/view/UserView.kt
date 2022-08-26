package com.synebula.zeus.query.view

import com.synebula.gaea.query.Operator
import com.synebula.gaea.query.Table
import com.synebula.gaea.query.Where

@Table("user")
class UserView {
    var id: String = ""

    var name: String = ""

    var password: String = ""

    @Where(Operator.Like)
    var realName: String? = null

    var phone: String? = null

    var role: String? = null

    var group: String? = null

    var token: String? = null

    var alive: Boolean? = null
}