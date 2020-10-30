package com.synebula.zeus.domain.service.impl.rbac.resource

import com.synebula.gaea.data.IObjectConverter
import com.synebula.gaea.domain.repository.IRepository
import com.synebula.gaea.domain.service.Service
import com.synebula.gaea.log.ILogger
import com.synebula.zeus.domain.model.rbac.resource.Interface
import com.synebula.zeus.domain.service.contr.rbac.resource.IInterfaceService

class InterfaceService(
        repository: IRepository,
        converter: IObjectConverter, logger: ILogger
) : Service<Interface, String>(Interface::class.java, repository, converter, logger), IInterfaceService