package com.synebula.zeus.query.impl

import com.synebula.gaea.data.message.Message
import com.synebula.gaea.data.message.Status
import com.synebula.gaea.extension.toMd5
import com.synebula.gaea.mongo.query.MongoQuery
import com.synebula.zeus.query.contr.IUserQuery
import com.synebula.zeus.query.view.UserView
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo

class UserQuery(template: MongoTemplate)
    : MongoQuery<UserView>("user", UserView::class.java, template), IUserQuery {

    override fun signIn(name: String, password: String): Message<String> {
        this.check()
        val query = Query.query(
                Criteria.where("name").isEqualTo(name)
                        .and("password").isEqualTo(password)
                        .and("alive").isEqualTo(true))
        val user = this.template.findOne(query, this.clazz!!)
        return if (user != null)
            Message(user.id)
        else
            Message(Status.Failure, "用户名或密码错误")
    }
}