package com.synebula.zeus.app.controller

import com.synebula.gaea.app.CommandApp
import com.synebula.gaea.app.QueryApp
import com.synebula.gaea.app.UnionApp
import com.synebula.gaea.app.UnionTypedApp
import com.synebula.gaea.app.component.HttpMessage
import com.synebula.gaea.domain.service.IService
import com.synebula.gaea.log.ILogger
import com.synebula.gaea.query.mongo.MongoQuery
import com.synebula.gaea.query.mongo.MongoQueryTyped
import com.synebula.zeus.domain.service.cmd.AccountCmd
import com.synebula.zeus.domain.service.contr.rbac.IAccountService
import com.synebula.zeus.query.view.AccountView
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/accounts")
class AccountApp(
        service: IAccountService,
        query: MongoQueryTyped,
        logger: ILogger
) : UnionTypedApp<AccountCmd, AccountView, String>("账户", AccountView::class.java,
        service, query, logger)