package com.synebula.zeus.domain.service.contr.component

interface IUserNotifier {
    fun added(id: String, name: String, token: String)

    fun forgot(id: String, name: String, token: String)
}