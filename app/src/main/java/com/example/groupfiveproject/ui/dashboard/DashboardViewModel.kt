package com.example.groupfiveproject.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.groupfiveproject.api.KingApi
import com.example.groupfiveproject.model.KingAndQueen
import com.example.groupfiveproject.model.KingAndQueenItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardViewModel : ViewModel() {
    var voting: MutableLiveData<List<KingAndQueenItem>> = MutableLiveData()

    fun getVoting(): LiveData<List<KingAndQueenItem>> = voting

    private val queenApi : KingApi = KingApi()

    fun loadQueen(){
        var queenVoteApiCall=queenApi.getQueen()
        queenVoteApiCall.enqueue(object :Callback<KingAndQueen>{
            override fun onFailure(call: Call<KingAndQueen>, t: Throwable) {

            }

            override fun onResponse(call: Call<KingAndQueen>, response: Response<KingAndQueen>) {
               var queenVoteList = response.body()
                Log.d("Response>>>>",queenVoteList.toString())
                response.isSuccessful.let {
                    var voteList:List<KingAndQueenItem> = response.body() ?: emptyList()
                    voting.value = voteList
                }
            }

        })
    }
}