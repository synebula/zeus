package com.synebula.zeus.domain.service.impl.rbac.resource

import com.synebula.gaea.data.IObjectConverter
import com.synebula.gaea.domain.repository.IRepository
import com.synebula.gaea.domain.service.Service
import com.synebula.gaea.log.ILogger
import com.synebula.zeus.domain.model.rbac.resource.Permission
import com.synebula.zeus.domain.service.contr.rbac.resource.IPermissionService

class PermissionService(
        repository: IRepository,
        converter: IObjectConverter, logger: ILogger
) : Service<Permission, String>(Permission::class.java, repository, converter, logger), IPermissionService