package com.synebula.zeus.query.view

import com.synebula.gaea.db.query.Operator
import com.synebula.gaea.db.query.Table
import com.synebula.gaea.db.query.Where


@Table("group")
class GroupView {
    var id: String? = null

    @Where(Operator.Like)
    var name = ""

    var desc = ""
}
