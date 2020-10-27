package com.synebula.zeus.query.view

import com.synebula.gaea.query.annotation.Table
import com.synebula.gaea.query.annotation.Where
import com.synebula.gaea.query.type.Operator

@Table("group")
class GroupView {
    var id: String? = null

    @Where(Operator.like)
    var name = ""
}
