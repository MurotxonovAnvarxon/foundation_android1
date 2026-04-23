package com.devuz.network

import android.util.Log
import com.devuz.network.models.domain.Root
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class KtorClient {
    private val client = HttpClient(OkHttp) {
        defaultRequest {
            url("https://rickandmortyapi.com/api/")
        }
        install(Logging) {
            logger = Logger.SIMPLE
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    //    suspend fun getCharacters(page: Int) {
//
//        try {
//            val response = client.get("character") {
//                parameter("page", page)
//            }
//
//            Log.d("TTT", "getCharacters: ${response.body<Root>()}")
//            return response.body()
//        } catch (e: Exception) {
//            return e.message
//        }
//    }
    suspend fun getCharacters(page: Int) : HttpResponse{
        val response = client.get("character") {
            parameter("page", page)
        }
        Log.d("TTT", "getCharacters: ${response.body<Root>()}")
        return response.body()

    }
}
