package com.synebula.zeus.query.impl.resouce

import com.synebula.gaea.mongo.query.MongoQuery
import com.synebula.zeus.query.contr.resouce.IInterfaceQuery
import org.springframework.data.mongodb.core.MongoTemplate

class InterfaceQuery(template: MongoTemplate) : MongoQuery(template), IInterfaceQuery {
}