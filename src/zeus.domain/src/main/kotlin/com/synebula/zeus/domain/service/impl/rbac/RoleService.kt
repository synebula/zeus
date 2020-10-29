package com.synebula.zeus.domain.service.impl.rbac

import com.synebula.gaea.data.IObjectConverter
import com.synebula.gaea.domain.repository.IRepository
import com.synebula.gaea.domain.service.Service
import com.synebula.gaea.log.ILogger
import com.synebula.zeus.domain.model.rbac.Role
import com.synebula.zeus.domain.service.ctr.rbac.IRoleService

class RoleService(
    repository: IRepository,
    converter: IObjectConverter, logger: ILogger
) : Service<Role, String>(Role::class.java, repository, converter, logger), IRoleService