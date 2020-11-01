package com.synebula.zeus.app.config

import com.google.gson.Gson
import com.synebula.gaea.data.serialization.json.IJsonSerializer
import com.synebula.gaea.domain.model.IAggregateRoot
import com.synebula.gaea.domain.repository.IRepository
import com.synebula.gaea.log.ILogger
import com.synebula.gaea.mongo.query.MongoQuery
import com.synebula.gaea.mongo.repository.MongoRepository
import com.synebula.gaea.query.IQuery
import com.synebula.zeus.domain.service.contr.component.IUserNotifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.mongodb.core.MongoTemplate


@Configuration
open class ZeusBeans {
    @Bean
    @Primary
    open fun <T : IAggregateRoot<String>> repository(template: MongoTemplate)
            : IRepository = MongoRepository(template)

    @Bean
    @Primary
    open fun <T> query(template: MongoTemplate, logger: ILogger? = null)
            : IQuery = MongoQuery(template, logger)

    @Bean
    open fun gson(): Gson = Gson()

    @Bean
    open fun userNotifier(): IUserNotifier {
        return object : IUserNotifier {
            override fun added(id: String, name: String, token: String) {

            }

            override fun forgot(id: String, name: String, token: String) {
            }

        }
    }

    @Bean
    open fun serializer(gson: Gson): IJsonSerializer {
        return object : IJsonSerializer {
            override fun <S> serialize(src: S): String {
                return gson.toJson(src)
            }
        }
    }
}