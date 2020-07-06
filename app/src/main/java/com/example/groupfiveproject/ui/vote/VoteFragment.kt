package com.example.groupfiveproject.ui.vote

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.groupfiveproject.R
import com.example.groupfiveproject.api.KingInterface
import com.example.groupfiveproject.model.Vote
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_vote.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class VoteFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root = inflater.inflate(R.layout.fragment_vote, container, false)
        return root
    }

    val BASE_URL = "https://ucsmonywaonlinevote.000webhostapp.com/api/"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var retrofitService = retrofit.create(KingInterface::class.java)

        var voteDetail = arguments?.let { VoteFragmentArgs.fromBundle(it) }
        var voteId = voteDetail?.voteID
        var name  = voteDetail?.voteName
        //Log.d(" Name", name)
        var voteImage: String? = voteDetail?.voteImage

       // Log.d("image",voteImage.toString())

        Picasso.get()
            .load(voteImage)
            .placeholder(R.drawable.kingandqueen)
            .into(vote_image)
       vote_name.text = name
        vote_id.text = voteDetail?.voteID
        vote_time.text = voteDetail?.voteTimeStatus.toString()
        vote_class.text = voteDetail?.voteClass
        vote_count.text = voteDetail?.voteCount.toString()

        vote_button.setOnClickListener{
            val code = edit_txt_code.text.toString()
            val apiCallKing = retrofitService.voteKing(code,voteId)
            val apiCallQueen = retrofitService.voteQueen(code,voteId)
            if (voteId == "K1" && voteId == "K2" && voteId == "K3" && voteId == "K4" && voteId == "K5" && voteId == "K6" && voteId == "K7" && voteId == "K8" && voteId == "K9" && voteId == "K10"){
                apiCallKing.enqueue(object : Callback<Vote>{
                    override fun onFailure(call: Call<Vote>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                    override fun onResponse(call: Call<Vote>, response: Response<Vote>) {
                        Toast.makeText(context,response.body().toString(),Toast.LENGTH_LONG).show()
                    }

                })
            }else{
                apiCallQueen.enqueue(object :Callback<Vote>{
                    override fun onFailure(call: Call<Vote>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                    override fun onResponse(call: Call<Vote>, response: Response<Vote>) {
                        Toast.makeText(context,response.body().toString(),Toast.LENGTH_LONG).show()
                    }

                })
            }
            if (TextUtils.isEmpty(code)){
                edit_txt_code.setError("Require Code")
            }
        }
    }

}