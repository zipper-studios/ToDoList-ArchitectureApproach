package com.example.kotlinpractice.roomtodolist.utils

import com.example.kotlinpractice.roomtodolist.model.Task
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Converter
import retrofit2.Retrofit
import java.io.IOException
import java.lang.reflect.Type


class CustomJsonConverterFactory : Converter.Factory() {

    override fun responseBodyConverter(type: Type,
                                       annotations: Array<Annotation>,
                                       retrofit: Retrofit): Converter<ResponseBody, *> {
        return JsonConverter.INSTANCE
    }

    internal class JsonConverter : Converter<ResponseBody, List<Task>> {

        @Throws(IOException::class)
        override fun convert(responseBody: ResponseBody): List<Task> {
            try {

                val listOfTasks = arrayListOf<Task>()
                val response = JSONObject(responseBody.string())
                val taskList = response.getJSONObject("taskList")
                val keys = taskList.keys()

                while (keys.hasNext()) {
                    val keyValue = keys.next() as String
                    val task = taskList.getJSONObject(keyValue)
                    listOfTasks.add(Task(task.getString("title"), task.getString("priority")))
                }
                return listOfTasks
            } catch (e: JSONException) {
                throw IOException("Failed to parse JSON", e)
            }

        }

        companion object {
            val INSTANCE = JsonConverter()
        }
    }
}