package com.synebula.zeus.query.view

import com.synebula.gaea.data.permission.PermissionType
import com.synebula.gaea.query.Table

@Table("role")
class RoleView {
    var id: String? = null
    var name = ""
    var desc = ""
    var permissionType = PermissionType.Minimum
}