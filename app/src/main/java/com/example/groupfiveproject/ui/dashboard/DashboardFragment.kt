package com.example.groupfiveproject.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.groupfiveproject.R
import com.example.groupfiveproject.adapter.KingAdapter
import com.example.groupfiveproject.model.KingAndQueenItem
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_home.*

class DashboardFragment : Fragment(),KingAdapter.KingClickListener {

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var kingAdapter: KingAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        queen_recycler.layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
        kingAdapter = KingAdapter()
        queen_recycler.adapter = kingAdapter
        kingAdapter.setOnClickListener(this)

        observeViewModel()
    }

    private fun observeViewModel(){
        dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        dashboardViewModel.getVoting().observe(viewLifecycleOwner, Observer {
            kingAdapter.updateKing(it)
        })
    }

    override fun onResume() {
        super.onResume()
        dashboardViewModel.loadQueen()
    }

    override fun onClick(king: KingAndQueenItem) {
        var voteID = king.id
        var voteName = king.name
        Log.d("Queen Name", voteName)
        var voteImage = king.img_url
        var voteCount = king.vote_count
        var voteTimeStatus = king.vote_time_status
        var voteClass = king.`class`
        var action = DashboardFragmentDirections.actionNavigationDashboardToVoteFragment(voteID,voteImage,voteName,voteCount,voteTimeStatus,voteClass)
        findNavController().navigate(action)
    }
}