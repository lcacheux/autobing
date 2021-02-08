package net.cacheux.autobing

import android.app.IntentService
import android.content.Intent
import android.net.Uri
import android.os.Handler

const val ACTION_GO = "net.cacheux.autobing.action.GO"
const val EXTRA_COUNT = "count"

class StartBingService : IntentService("StartBingService") {

    private val charPool : List<Char> = ('a'..'z') + ('A'..'Z')

    private val handler = Handler()

    override fun onHandleIntent(intent: Intent?) {
        when (intent?.action) {
            ACTION_GO -> {
                val count = if (intent.extras != null) {
                    intent.extras!!.getInt(EXTRA_COUNT, 1)
                } else 1

                logDebug { "GO action received, count: $count"}

                for (i in 1 .. count) {
                    handler.postDelayed({
                        val query = getRandomString()
                        logDebug { "Starting Bing search $query" }
                        startActivity(Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.bing.com/search?q=$query")).apply {
                            addCategory(Intent.CATEGORY_BROWSABLE)
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        })
                    }, (i * 2000).toLong())
                }
            }
        }
    }

    private fun getRandomString() = (1..10)
        .map { kotlin.random.Random.nextInt(0, charPool.size) }
        .map(charPool::get)
        .joinToString("")
}
