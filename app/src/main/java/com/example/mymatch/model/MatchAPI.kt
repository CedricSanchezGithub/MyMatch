package com.example.mymatch.model

import com.example.mymatch.beans.MatchBean
import com.example.mymatch.model.MatchAPI.client
import com.example.mymatch.model.MatchAPI.createMatch
import com.example.mymatch.model.MatchAPI.gson
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

fun main() {

    createMatch("MHB","Nantes",51984651)

}
object MatchAPI {

    val MEDIA_TYPE_JSON = "application/json; charset=utf-8".toMediaType()
    val gson = Gson()
    val client = OkHttpClient()
    private const val URL_SERVER = "http://2.9.163.31:8080"

    fun createMatch(equipe1 : String, equipe2 : String = "equipe2", date: Long) {

        val res = sendPost("$URL_SERVER/creatematch", MatchBean(null, date, equipe1, equipe2))

        println(res)

    }

    fun sendGet(url: String): String {
        println("url : $url")
        val request = Request.Builder().url(url).get().build()

        return client.newCall(request).execute().use { //it:Response
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code}")
            }
            it.body.string()
        }
    }
        fun sendPost(url: String, toSend: Any?): String {
            println("url : $url")

            val json = gson.toJson(toSend)

            val body = json.toRequestBody(MEDIA_TYPE_JSON)
            val request = Request.Builder().url(url).post(body).build()

            return client.newCall(request).execute().use {
                if (!it.isSuccessful) {
                    throw Exception("Réponse du serveur incorrect :${it.code}")
                }
                it.body.string()
            }
        }

    }
