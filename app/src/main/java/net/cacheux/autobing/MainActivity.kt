package net.cacheux.autobing

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val MAX_SEARCHES = 20
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countText.setText(MAX_SEARCHES.toString())

        countText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(e: Editable) {}
            override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                try {
                    val count = Integer.parseInt(text.toString())
                    launchButton.isEnabled = count in 1..20
                } catch (e: java.lang.NumberFormatException) {
                    logError("Wrong input", e)
                    launchButton.isEnabled = false
                }
            }
        })

        launchButton.setOnClickListener {
            val count = try {
                Integer.parseInt(countText.text.toString())
            } catch (e: NumberFormatException) {
                1
            }
            val intent = Intent(this, StartBingService::class.java).apply {
                action = ACTION_GO
                putExtra(EXTRA_COUNT, count)
            }
            startService(intent)
        }
    }
}
