package com.example.wordlegame

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.wordlegame.databinding.ActivityPreferencesBinding
import kotlin.random.Random

class PreferencesActivity : AppCompatActivity() {
    lateinit var binding: ActivityPreferencesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreferencesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var wordLength: Int = Random.nextInt(3, 8)

        // Selects word length
        binding.letterLength.setOnCheckedChangeListener { group, checkedId ->
            // If a specific number
            try{
                wordLength = findViewById<RadioButton>(checkedId).text.toString().toInt()
            }
            // If random
            catch(e: Exception){
                wordLength = Random.nextInt(3, 8)
            }
        }

        // Goes back
        binding.backButton.setOnClickListener{
            val backIntent = Intent(this, MainActivity::class.java)
            backIntent.putExtra("wordLength", wordLength)
            startActivity(backIntent)
        }


    }
}