package com.example.mymatch.model

import com.example.mymatch.beans.MatchBean
import com.example.mymatch.model.MatchAPI.add1Point
import com.example.mymatch.model.MatchAPI.createMatch
import com.example.mymatch.model.MatchAPI.load7DayzMatch
import com.google.gson.Gson
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody


fun main() {
    //Création de la date du jour
    val currentTimeMillis = System.currentTimeMillis()
    //Créer un match
    createMatch("MHB","Nantes",currentTimeMillis)
    //lister les match
    val listest = load7DayzMatch()

    //ajouter un point
    listest[0].id?.let { add1Point(it, 1) }
    println(listest)

}

object MatchAPI {

    val MEDIA_TYPE_JSON = "application/json; charset=utf-8".toMediaType()
    val gson = Gson()
    val client = OkHttpClient()
    private const val URL_SERVER = "http://localhost:8080"
//"http://2.9.163.31:8080"   http://localhost:8080
    fun createMatch(equipe1 : String, equipe2 : String, date: Long) {
        val res = sendPost("$URL_SERVER/mymatch/creatematch", MatchBean(null, date, equipe1, equipe2, status = true))
        println(res)

    }

    fun add1Point(idMatch: Long, equipe: Int): MatchBean {
        val responseJson = sendPostaddPoint("$URL_SERVER/mymatch/score", idMatch, equipe)
        return gson.fromJson(responseJson, MatchBean::class.java)
    }

    fun load7DayzMatch(): List<MatchBean> {
        var json = sendGet("$URL_SERVER/mymatch/7dayz")
        val test = gson.fromJson(json, Array<MatchBean>::class.java).toList()
        println(test)
        return test

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

    fun sendPostaddPoint(url: String, idMatch: Long, equipe: Int): String {
        println("URL : $url")

        val requestUrl = url.toHttpUrlOrNull()!!.newBuilder()
            .addQueryParameter("idMatch", idMatch.toString())
            .addQueryParameter("equipe", equipe.toString())
            .build()

        val request = Request.Builder()
            .url(requestUrl)
            .post(RequestBody.create(null, ""))
            .addHeader("Content-Type", "application/json")
            .build()

        val response = client.newCall(request).execute()
        return response.use {
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect : ${it.code}")
            }
            it.body!!.string()
        }
    }

}
