package com.synebula.zeus.query.view

import com.synebula.gaea.query.Operator
import com.synebula.gaea.query.Table
import com.synebula.gaea.query.Where


@Table("group")
class GroupView {
    var id: String? = null

    @Where(Operator.like)
    var name = ""

    var desc = ""
}
