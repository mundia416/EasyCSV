package com.nosetrap.easycsvlib

import android.content.Context
import com.snatik.storage.Storage
import java.io.File

/**
 */
class CsvWriter(context: Context) {
    private val storage = Storage(context)
    private var stringBuilder = StringBuilder()
    //the rows in the csv,each arraylist represents a row of values
    private var rows = ArrayList<ArrayList<String>>()
    private val comma = ","
    private val trueString = "True"
    private val falseString = "False"

    val externalStorageDirectory = storage.externalStorageDirectory

    init {
        //add the first row
        rows.add(ArrayList())
    }

    /**
     * goes to a new line  and writes the values
     */
    fun writeNextRow(values: Array<String>){
        newRow()
        writeRow(values)
    }

    fun writeNextRow(values: List<String>){
        newRow()
        writeRow(values)
    }



    /**
     * writes a value to a specific cell identified by @param index in the current row
     * @param the index of the cell counting from left to right
     */
    fun writeToCell(index: Int,value: String){
        val lastRow = rows[rows.lastIndex]
        if(index > lastRow.lastIndex){
            for(i in 0..(index-lastRow.lastIndex)){
                lastRow.add("")
            }
        }
        lastRow[index] = value
    }

    fun writeToCell(index:Int, value: Int){
        writeToCell(index,value.toString())
    }

    fun writeToCell(index:Int, value: Double){
        writeToCell(index,value.toString())
    }

    fun writeToCell(index:Int, value: Boolean){
        val bool = if(value) trueString else falseString
        writeToCell(index,bool)
    }

    fun writeToCell(index:Int, value: Long){
        writeToCell(index,value.toString())
    }

    fun writeToCell(index:Int, value: Float){
        writeToCell(index,value.toString())
    }

    /**
     * @return the current number of rows that will be in the csv when it gets exported
     */
    var numRows: Int = 0
        get() = rows.count()
    private set

    /**
     * write to the last cell of the current row
     */
    fun writeCell(value:String){
        val lastIndex = rows.lastIndex
        rows[lastIndex].add(value)
    }

    fun writeCell(value: Boolean){
        val bool = if(value) trueString else falseString
        writeCell(bool)
    }

    fun writeCell(value: Double) {
        writeCell(value.toString())
    }

    fun writeCell(value: Int) {
        writeCell(value.toString())
    }

    fun writeCell(value: Float) {
        writeCell(value.toString())
    }

        /**
     * writes the values to the current row
     */
    fun writeRow(values: Array<String>){
        rows[rows.lastIndex].addAll(values)
    }

    fun writeRow(values: List<String>){
        rows[rows.lastIndex].addAll(values)
    }

    /**
     * goes to a new row
     */
    fun newRow(){
        val list = ArrayList<String>()
        rows.add(list)
    }

    /**
     * gets rid of all the content currently written
     */
    fun clear(){
        rows.clear()
        rows.add(ArrayList())
        stringBuilder = StringBuilder()
    }

    /**
     * export to storage
     * filename should not have .csv at the end of it
     * dont add a sepearatore "/" at the end of the directory name
     * @return the full path to the created file
     */
    fun export(dir: String,filename: String): String{
        for (row in rows){
            for(cell in row){
                stringBuilder.append(cell+comma)
            }
                stringBuilder.append(System.lineSeparator())
        }

        val path = dir+ File.separator+filename+".csv"

        storage.createDirectory(dir,false)
        storage.createFile(path,stringBuilder.toString())
        return path
    }
}