package com.example.wordlegame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wordlegame.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var binding: ActivityMainBinding

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Starts a game
        binding.startButton.setOnClickListener{
            val secondIntent = Intent(this, SecondActivity::class.java)
            secondIntent.putExtra("chosenWord", chooseWord())
            startActivity(secondIntent)
        }

        // Goes to the help page
        binding.helpButton.setOnClickListener{
            val helpIntent = Intent(this, HelpActivity::class.java)
            startActivity(helpIntent)
        }

        // Goes to the preferences page
        binding.preferencesButton.setOnClickListener{
            val preferencesIntent = Intent(this, PreferencesActivity::class.java)
            startActivity(preferencesIntent)
        }
    }

    // Randomly chooses a word based on selected word length
    // Default is a random word length
    fun chooseWord(): String{
        var wordLength = intent.getIntExtra("wordLength", Random.nextInt(3, 8))

        val wordListThree = listOf<String>("bad", "bag", "box", "can", "cap", "dad", "ear", "eat", "ham", "ice",
            "lab", "mad", "man", "nap", "oar", "sat", "sag", "six", "vow", "zip")
        val wordListFour = listOf<String>("baby", "cafe", "earn", "ease", "face", "goat", "head", "lake", "lock", "mace",
            "more", "oath", "pact", "race", "rock", "safe", "swim", "twin", "ugly", "weld")
        val wordListFive = listOf<String>("angel", "bears", "burnt", "claim", "dogma", "exams", "false", "guess", "known", "lying",
            "maker", "minus", "nurse", "panic", "plate", "quiet", "scope", "start", "taboo", "venom")
        val wordListSix = listOf<String>("adores", "around", "bishop", "branch", "cattle", "crayon", "drying", "erased", "facade", "future",
            "helium", "ignite", "karate", "lyrics", "mirror", "object", "pirate", "refuse", "skills", "tyrant")
        val wordListSeven = listOf<String>("academy", "annoyed", "burnout", "cameras", "cluster", "deleted", "develop", "ellipse", "foolish", "genetic",
            "heavier", "inflict", "kingdom", "loyalty", "message", "mystery", "payback", "quality", "shocker", "trinity")
        val wordListList = listOf<List<String>>(wordListThree, wordListFour, wordListFive, wordListSix, wordListSeven)
        when(wordLength){
            3 -> return wordListThree.random()
            4 -> return wordListFour.random()
            5 -> return wordListFive.random()
            6 -> return wordListSix.random()
            7 -> return wordListSeven.random()
            else -> {
                return wordListList.random().random()
            }
        }
    }
}