package com.synebula.zeus.query.view.resource


abstract class ResourceView(var id: String? = null) {
    var name = ""
    var signature = ""
    val uri: String? = null
    var order = 0
    var desc = ""
}