package com.synebula.zeus.query.view

import com.synebula.gaea.query.Operator
import com.synebula.gaea.query.Where

class UserView {
    var id: String = ""

    var name: String = ""

    var password: String = ""

    @Where(Operator.like)
    var realName: String? = null

    var phone: String? = null

    var role: String? = null

    var group: String? = null
}