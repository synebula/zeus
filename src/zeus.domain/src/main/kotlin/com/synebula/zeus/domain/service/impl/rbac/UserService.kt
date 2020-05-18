package com.synebula.zeus.domain.service.impl.rbac

import com.synebula.gaea.data.IObjectConverter
import com.synebula.gaea.domain.repository.IRepository
import com.synebula.gaea.domain.service.Service
import com.synebula.gaea.log.ILogger
import com.synebula.zeus.domain.model.rbac.User
import com.synebula.zeus.domain.service.contr.rbac.IUserService

class UserService(repository: IRepository<User, String>, converter: IObjectConverter, logger: ILogger) :
    Service<User, String>(User::class.java, repository, converter, logger), IUserService {

}