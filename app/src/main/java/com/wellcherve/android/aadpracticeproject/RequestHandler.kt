package com.wellcherve.android.aadpracticeproject

import android.net.Uri
import android.os.AsyncTask
import com.wellcherve.android.aadpracticeproject.models.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList

object RequestHandler {
    /**
     * class all request may by this application , handling all details
     * from requesting to delivering the appropriate response
     *
     */
    const val BASE_URL = "https://gadsapi.herokuapp.com"
    const val LEARNING_LEADERS_PATH = "api/hours"
    const val SKILL_IQ_LEADERS_PATH = "api/skilliq"
    private var leaders = ArrayList<Leader>()

    @JvmStatic
    @Throws(MalformedURLException::class)
    fun buildingURL(path: String?): URL {
        val uri = Uri.withAppendedPath(Uri.parse(BASE_URL), path).toString()
        return URL(uri)
    }

    @Throws(IOException::class, JSONException::class)
    fun makeRequest(path: String?): ArrayList<Leader> {
        val url = buildingURL(path)
        val connection = url.openConnection() as HttpURLConnection
        val inputStream = connection.inputStream
        val scanner = Scanner(inputStream)
        scanner.useDelimiter("\\A")
        val data = scanner.next()
        return getLeaders(JSONArray(data), path)
    }

    private fun getLeaders(data: JSONArray, path: String?): ArrayList<Leader> {
        var idx = 0
        val leaders = ArrayList<Leader>()
        var leader: Leader?
        while (idx < data.length()) {
            val jsonObject = data.getJSONObject(idx)
            if (path == LEARNING_LEADERS_PATH) {
                 leader = LearningLeader(
                    jsonObject.getString(NAME),
                    jsonObject.getString(COUNTRY), jsonObject.getString(BADGE_URL), jsonObject.getInt(HOURS)
                )
            } else {
                leader = LearningLeader(
                    jsonObject.getString(NAME),
                    jsonObject.getString(COUNTRY), jsonObject.getString(BADGE_URL), jsonObject.getInt(SCORE)
                )
            }
            leaders.add(leader)
            idx++
        }
        return leaders
    }



    object ExecuteSkillIQLeadersTask: AsyncTask<String, Unit, ArrayList<Leader>>() {
        override fun doInBackground(vararg params: String?): ArrayList<Leader> {
            return makeRequest(params[0]);
        }

        override fun onPostExecute(result: ArrayList<Leader>?) {
            if (result != null) {
                leaders = result
            }
        }
    }
}