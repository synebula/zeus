package com.synebula.zeus.domain.service.impl.rbac

import com.synebula.gaea.data.IObjectConverter
import com.synebula.gaea.domain.repository.IRepository
import com.synebula.gaea.domain.service.Service
import com.synebula.gaea.log.ILogger
import com.synebula.zeus.domain.model.rbac.Account
import com.synebula.zeus.domain.service.contr.rbac.IAccountService

class AccountService(repository: IRepository<Account, String>, converter: IObjectConverter, logger: ILogger) :
    Service<Account, String>(Account::class.java, repository, converter, logger), IAccountService {

}