package com.synebula.zeus.query.impl.resouce

import com.synebula.gaea.mongo.query.MongoQuery
import com.synebula.zeus.query.contr.resouce.ISystemQuery
import org.springframework.data.mongodb.core.MongoTemplate

class SystemQuery(template: MongoTemplate) : MongoQuery(template), ISystemQuery