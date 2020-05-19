package com.synebula.zeus.domain.service.cmd

import com.synebula.gaea.domain.service.Command

class UserCmd : Command() {
    var id: String? = null
    var name = ""
    var password = ""
    var realName: String? = null
    var phone: String? = null
}
