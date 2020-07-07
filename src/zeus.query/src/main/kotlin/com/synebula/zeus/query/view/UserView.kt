package com.synebula.zeus.query.view

import com.synebula.gaea.query.annotation.Where
import com.synebula.gaea.query.type.Operator

class UserView {
    var id: String = ""

    var name: String = ""

    var password: String = ""

    @Where(Operator.like)
    var realName: String? = null

    var phone: String? = null

    var role: String? = null

    var group: String? = null

    var token: String? = null

    var alive: Boolean? = null
}