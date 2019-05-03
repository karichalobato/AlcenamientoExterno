package com.example.alcenamientoexterno

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*

class MainActivity : AppCompatActivity() {

   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_save.setOnClickListener {
            savee(it)
        }

        button_see.setOnClickListener {
            sshow(it)
        }
    }

    fun savee(view: View){
        var name = fileTitle.text.toString()
        var content = fileContent.text.toString()

        try {
            var sdCard: File = Environment.getExternalStorageDirectory()
            Toast.makeText(this, sdCard.path, Toast.LENGTH_SHORT).show()
            val fileRoute: File = File(sdCard.path, name)
            val openFile: OutputStreamWriter = OutputStreamWriter(openFileOutput(name, Activity.MODE_PRIVATE))
            openFile.write(content)
            openFile.flush()
            openFile.close()
            Toast.makeText(this, "Saved correctly", Toast.LENGTH_SHORT).show()
            fileTitle.setText("")
            fileContent.setText("")
        } catch (e: IOException){
            Toast.makeText(this, "Could not save", Toast.LENGTH_SHORT).show()
        }
    }

    fun sshow(view: View){
        val name = fileTitle.text.toString()
        try {
            val sdCard: File = Environment.getExternalStorageDirectory()
            val fileRoute: File = File(sdCard.path, name)
            val openFile = InputStreamReader(openFileInput(name))
            val readFile: BufferedReader = BufferedReader(openFile)
            var line = readFile.readLine()
            var fullContent = ""

            while (line!=null){
                fullContent = fullContent + line + "\n"
                line = readFile.readLine()
            }

            readFile.close()
            openFile.close()
            fileContent.setText(fullContent)
        } catch (e: IOException){
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }
    }

}
