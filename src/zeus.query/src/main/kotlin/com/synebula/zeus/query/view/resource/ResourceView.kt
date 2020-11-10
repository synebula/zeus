package com.synebula.zeus.query.view.resource


abstract class ResourceView(var id: String? = null) {
    var name = ""
    //资源定位符，唯一标识。可以是uil，也可以是别名
    var uri = ""
    var order = 0
    var desc: String? = null
}