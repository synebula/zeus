package com.synebula.zeus.query.view

import com.synebula.gaea.db.query.Operator
import com.synebula.gaea.db.query.Table
import com.synebula.gaea.db.query.Where

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

    var avalible: Boolean? = null
}