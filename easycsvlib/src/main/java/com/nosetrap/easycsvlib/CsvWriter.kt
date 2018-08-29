package com.nosetrap.easycsvlib

import android.content.Context
import com.snatik.storage.Storage
import java.io.File

class CsvWriter(context: Context) {
    private val storage = Storage(context)
    private var stringBuilder = StringBuilder()
    private val comma = ","

    val externalStorageDirectory = storage.externalStorageDirectory

    /**
     * goes to a new line  and writes the values
     */
    fun writeNextRow(values: Array<String>){
        newRow()
        writeRow(values)
    }

    /**
     * writes the values to the current row
     */
    fun writeRow(values: Array<String>){
        for(value in values){
            stringBuilder.append(value+comma)
        }
    }
    /**
     * goes to a new row
     */
    fun newRow(){
        stringBuilder.append(System.lineSeparator())
    }

    /**
     * gets rid of all the content currently written
     */
    fun clear(){
        stringBuilder = StringBuilder()
    }

    /**
     * export to storage
     * filename should not have .csv at the end of it
     * dont add a sepearatore "/" at the end of the directory name
     * @return the full path to the created file
     */
    fun export(dir: String,filename: String): String{
        val path = dir+ File.separator+filename+".csv"

        storage.createFile(path,stringBuilder.toString())
        return path
    }
}