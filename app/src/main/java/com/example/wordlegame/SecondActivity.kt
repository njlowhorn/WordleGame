package com.example.wordlegame

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.view.allViews
import androidx.core.view.children
import androidx.core.view.contains
import androidx.core.view.descendants
import androidx.core.view.iterator
import com.example.wordlegame.databinding.ActivitySecondBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import java.util.Collections
import kotlin.random.Random

class SecondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding

    var rows = ArrayList<ViewGroup>()
    var otherRows  = ArrayList<ViewGroup>()
    var row: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val word = intent.getStringExtra("chosenWord")
        val char = word?.toCharArray()
        var letterId: Int = 0
        var attempt = 1

        // Adds each row to an array
        for(r in binding.root.allViews){
            if(r.isClickable == false){
                val layoutRow = findViewById<ViewGroup>(r.id)
                rows.add(layoutRow)
            }
        }

        // Creates text inputs for each row
        for(r in rows){
            if(r != null){
                var wordLetter: Int = 0
                while(wordLetter < word?.length!!){
                    CreateTextInput(r, word.length, letterId)
                    wordLetter += 1
                    letterId += 1
                }
            }
        }

        otherRows = rows
        Update()

        binding.checkButton.setOnClickListener{
            val letterArray = ArrayList<String>()
            for(l in rows[row].allViews){
                try{
                    // Gets the letter
                    val letterView = findViewById<TextInputEditText>(l.id)
                    val letter = letterView.text.toString().lowercase()
                    letterArray.add(letter)

                    // If the letter is in the correct spot
                    if(letter == char?.get(rows[row].allViews.indexOf(l) - 1).toString().lowercase()){
                        // Change background color to green
                        letterView.setBackgroundResource(R.color.green)
                    }
                    // If the letter isn't in the correct spot but is in the word
                    else if(letter[0] in char!!){
                        // Change background color to yellow
                        letterView.setBackgroundResource(R.color.yellow)
                    }
                }
                catch (e: Exception){}
            }
            // If the word is guessed correctly
            if(attempt <= 6 && word == letterArray.joinToString("")){
                Snackbar.make(binding.root, "Congratualtions, you found the word.", Snackbar.LENGTH_LONG).show()
                binding.checkButton.isEnabled = false
            }
            // If the word isn't found by the end
            else if(attempt == 6 && word != letterArray.joinToString("")){
                Snackbar.make(binding.root, "Unfortunately, you didn't find the word. It was ${word}.", Snackbar.LENGTH_LONG).show()
                for(l in rows[row].allViews) {
                    l.isEnabled = false
                }
                binding.checkButton.isEnabled = false
            }
            // If the word isn't found before the end
            else{
                // Goes to the next row
                otherRows.add(row, rows[row])
                row += 1
                Update()
            }
            attempt += 1
        }
    }

    // Creates a text input
    fun CreateTextInput(row: ViewGroup, wordLength: Int, letterId: Int){
        val textEdit = TextInputEditText(this)
        row.addView(textEdit)
        textEdit.id = letterId
        textEdit.height = 220

        // Converts the width of the row from dp to pixels and divides it by the amount of letters in the chosen word
        textEdit.width = (370 * resources.displayMetrics.density + 0.5f).toInt() / wordLength

        textEdit.setTextColor(Color.BLACK)
        textEdit.textAlignment = View.TEXT_ALIGNMENT_CENTER
        textEdit.textSize = 40F

        // Max length of text input is one
        textEdit.filters = arrayOf(InputFilter.LengthFilter(1))

        textEdit.setBackgroundResource(android.R.drawable.edit_text)
    }

    // Enables the current row and disables the others
    fun Update(){
        otherRows.remove(rows[row])

        for(r in otherRows){
            for(l in r.allViews) {
                l.isEnabled = false
            }
        }

        for(l in rows[row].allViews){
            l.isEnabled = true
        }
    }


}