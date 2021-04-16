package com.synebula.zeus.query.view

import com.synebula.gaea.query.annotation.Table

@Table("role")
class RoleView {
    var id: String? = null
    var name = ""
    var desc = ""
}