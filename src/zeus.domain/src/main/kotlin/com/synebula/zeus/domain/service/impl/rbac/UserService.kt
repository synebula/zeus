package com.synebula.zeus.domain.service.impl.rbac

import com.synebula.gaea.data.IObjectConverter
import com.synebula.gaea.data.message.Message
import com.synebula.gaea.domain.repository.IRepository
import com.synebula.gaea.domain.service.ICommand
import com.synebula.gaea.domain.service.Service
import com.synebula.gaea.extension.*
import com.synebula.gaea.log.ILogger
import com.synebula.zeus.domain.model.rbac.User
import com.synebula.zeus.domain.service.contr.rbac.IUserService

class UserService(repository: IRepository, converter: IObjectConverter, logger: ILogger) :
    Service<User, String>(User::class.java, repository, converter, logger), IUserService {
    override fun add(command: ICommand): Message<String> {
        val user = this.convert(command)
        user.password = user.password.toMd5()
        user.alive = false
        this.repository.add(user, this.clazz)
        return Message(user.id!!)
    }

    /**
     * 激活用户
     *
     * @param key 用户ID
     */
    override fun active(key: String) {
        val user = this.repository.get(key, this.clazz)
        if (!user.alive) {
            user.alive = true
            this.repository.update(user, this.clazz)
        }
    }
}