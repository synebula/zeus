package com.synebula.zeus.domain.service.cmd

import com.synebula.gaea.domain.service.ICommand

class AccountCmd : ICommand {
    var id: String? = null
    var name = ""
    var password = ""
    override var timestamp = 0L
}
