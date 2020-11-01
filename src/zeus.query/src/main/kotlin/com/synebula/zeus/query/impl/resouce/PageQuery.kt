package com.synebula.zeus.query.impl.resouce

import com.synebula.gaea.mongo.query.MongoQuery
import com.synebula.zeus.query.contr.resouce.IPageQuery
import org.springframework.data.mongodb.core.MongoTemplate

class PageQuery(template: MongoTemplate) : MongoQuery(template), IPageQuery {
}