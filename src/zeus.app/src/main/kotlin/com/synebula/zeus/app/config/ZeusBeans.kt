package com.synebula.zeus.app.config

import com.google.gson.Gson
import com.synebula.gaea.data.serialization.json.IJsonSerializer
import com.synebula.gaea.domain.model.IAggregateRoot
import com.synebula.gaea.domain.repository.IRepository
import com.synebula.gaea.domain.repository.IRepositoryTyped
import com.synebula.gaea.log.ILogger
import com.synebula.gaea.mongo.query.MongoQuery
import com.synebula.gaea.mongo.query.MongoQueryTyped
import com.synebula.gaea.mongo.repository.MongoRepository
import com.synebula.gaea.mongo.repository.MongoRepositoryTyped
import com.synebula.gaea.query.IQuery
import com.synebula.gaea.query.IQueryTyped
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
    open fun <T> mongoQuery(template: MongoTemplate)
            : IQuery<T, String> = MongoQuery(template)

    @Bean
    open fun <T> mongoTypedQuery(template: MongoTemplate, logger: ILogger? = null)
            : IQueryTyped = MongoQueryTyped(template, logger)

    @Bean
    open fun gson(): Gson = Gson()

    @Bean
    open fun serializer(gson: Gson): IJsonSerializer {
        return object : IJsonSerializer {
            override fun <S> serialize(src: S): String {
                return gson.toJson(src)
            }
        }
    }

}