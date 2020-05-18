package com.synebula.zeus.app.config

import com.synebula.gaea.domain.model.IAggregateRoot
import com.synebula.gaea.domain.repository.IRepository
import com.synebula.gaea.domain.repository.IRepositoryTyped
import com.synebula.gaea.log.ILogger
import com.synebula.gaea.query.IQuery
import com.synebula.gaea.query.IQueryTyped
import com.synebula.gaea.query.mongo.MongoQuery
import com.synebula.gaea.query.mongo.MongoQueryTyped
import com.synebula.gaea.repository.mongo.MongoRepository
import com.synebula.gaea.repository.mongo.MongoRepositoryTyped
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate

@Configuration
open class ZeusBeans {
    @Bean
    open fun <T : IAggregateRoot<String>> repository(template: MongoTemplate)
            : IRepository<T, String> = MongoRepository(template)

    @Bean
    open fun <T : IAggregateRoot<String>> typedRepository(template: MongoTemplate)
            : IRepositoryTyped = MongoRepositoryTyped(template)

    @Bean
    open fun <T> typedQuery(template: MongoTemplate, logger: ILogger? = null)
            : IQueryTyped = MongoQueryTyped(template, logger)

    @Bean
    open fun <T> query(template: MongoTemplate)
            : IQuery<T, String> = MongoQuery(template)

    @Bean
    open fun <T> mongoQuery(template: MongoTemplate)
            : MongoQuery<T> = MongoQuery(template)

    @Bean
    open fun <T> mongoTypedQuery(template: MongoTemplate, logger: ILogger? = null)
            : MongoQueryTyped = MongoQueryTyped(template, logger)

}