package org.thechance.service_identity.di


import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

@Module
@ComponentScan("org.thechance.service_identity")
class IdentityModule

val kmongoModule = module {
    val cluster = System.getenv("cluster")
    val username = System.getenv("username")
    val password = System.getenv("password")
    single { KMongo.createClient("mongodb+srv://$username:$password@$cluster.mongodb.net/").coroutine }
}