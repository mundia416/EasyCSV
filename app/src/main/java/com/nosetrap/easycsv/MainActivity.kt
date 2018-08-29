package com.nosetrap.easycsv

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.github.florent37.runtimepermission.RuntimePermission
import com.nosetrap.easycsvlib.CsvWriter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RuntimePermission(this)
                .onAccepted {
                    permissionGranted()
                }
                .ask()
    }


    fun permissionGranted(){
        val writer = CsvWriter(this)
        writer.writeRow(arrayOf("cellA1","cellA2","cellA3","cellA4","cellA5","cellA6"))
        writer.writeNextRow(arrayOf("cellB1","cellB2","cellB3","cellB4","cellB5","cellB6"))
        writer.writeNextRow(arrayOf("cellC1","cellC2","cellC3","cellC4","cellC5","cellC6"))
        writer.writeNextRow(arrayOf("cellD1","cellD2","cellD3","cellD4","cellD5","cellD6"))
        writer.writeNextRow(arrayOf("cellE1","cellE2","cellE3","cellE4","cellE5","cellE6"))
        writer.writeNextRow(arrayOf("cellF1","cellF2","cellF3","cellF4","cellF5","cellF6"))
        writer.export(writer.externalStorageDirectory+"/kjnj","test")
    }

}
