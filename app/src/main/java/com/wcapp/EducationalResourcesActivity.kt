package com.wcapp

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import android.text.Html
import android.text.Spanned

class EducationalResourcesActivity : AppCompatActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_educational_resources)

        firebaseAnalytics = FirebaseAnalytics.getInstance(this)

        // Log an analytics event
        logAnalyticsEvent("educational_resources_viewed", null)

        // Initialize views and set data
        val textTitle = findViewById<TextView>(R.id.textTitle)
        val textDescription = findViewById<TextView>(R.id.textDescription)
        val textContent = findViewById<TextView>(R.id.textContent)

        textTitle.text = "Educational Resources"
        textDescription.text = "Explore the following links for more information on mental health."

        // Setting up HTML content for textContent
        val mentalHealthLinks: Spanned = Html.fromHtml("""
            <ul>
                <li><a href="https://www.mentalhealth.gov/">MentalHealth.gov - U.S. Government Mental Health Resources</a></li>
                <li><a href="https://www.who.int/health-topics/mental-health">WHO - Mental Health</a></li>
                <li><a href="https://www.nami.org/Home">NAMI - National Alliance on Mental Illness</a></li>
                <li><a href="https://www.mind.org.uk/">Mind - For Better Mental Health (UK)</a></li>
                <li><a href="https://www.mentalhealth.org.nz/">Mental Health Foundation of New Zealand</a></li>
            </ul>
            <p>These resources provide valuable information on maintaining mental health, seeking help, and understanding mental health conditions.</p>
        """.trimIndent(), Html.FROM_HTML_MODE_COMPACT)

        textContent.text = mentalHealthLinks
        textContent.movementMethod = LinkMovementMethod.getInstance() // Enable clickable links
    }

    private fun logAnalyticsEvent(eventName: String, bundle: Bundle?) {
        firebaseAnalytics.logEvent(eventName, bundle)
    }
}
