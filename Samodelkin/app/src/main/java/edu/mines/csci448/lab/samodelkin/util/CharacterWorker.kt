package edu.mines.csci448.lab.samodelkin.util

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import java.net.URL

private const val CHARACTER_DATA_API = "https://chargen-api.herokuapp.com/"
private const val CHARACTER_API_KEY = "apiCharacterData"
private val logTag = "448.CharWorker"

// TODO Step II.3
class CharacterWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    companion object {
       fun getApiData(outputData: Data) = outputData.getString(CHARACTER_API_KEY)
    }

    override fun doWork(): Result {
        Log.d(logTag, "Work request triggered" )

        URL(CHARACTER_DATA_API).readText()

        val outputData = workDataOf(CHARACTER_API_KEY to urlStringResult)

        return Result.success(outputData)
    }



}