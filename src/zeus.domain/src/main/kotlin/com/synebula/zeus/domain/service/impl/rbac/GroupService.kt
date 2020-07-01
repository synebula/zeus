package com.synebula.zeus.domain.service.impl.rbac

import com.synebula.gaea.data.IObjectConverter
import com.synebula.gaea.domain.repository.IRepository
import com.synebula.gaea.domain.service.Service
import com.synebula.gaea.log.ILogger
import com.synebula.zeus.domain.model.rbac.Group
import com.synebula.zeus.domain.service.contr.rbac.IGroupService
import com.synebula.zeus.domain.service.contr.rbac.IRoleService

class GroupService(
    repository: IRepository,
    converter: IObjectConverter, logger: ILogger
) : Service<Group, String>(Group::class.java, repository, converter, logger), IGroupService