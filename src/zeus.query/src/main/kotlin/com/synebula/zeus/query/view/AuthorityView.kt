package com.synebula.zeus.query.view

import com.synebula.zeus.env.AuthorityType
import com.synebula.zeus.env.ResourceType

class AuthorityView() {
    var id: String? = null
    var role = ""
    var resource = ""
    var type: ResourceType? = null
    var authority = AuthorityType.Allow
}