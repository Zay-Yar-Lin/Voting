package com.example.groupfiveproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.groupfiveproject.R
import com.example.groupfiveproject.model.KingAndQueenItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.kingandqueen_item_list.view.*

class KingAdapter (var kingList: List<KingAndQueenItem> = ArrayList()) : RecyclerView.Adapter<KingAdapter.KingViewHolder>(){
    private  var clickListener: KingClickListener? = null
    fun setOnClickListener(clickListener: KingClickListener){
    this.clickListener=clickListener
    }
    inner class KingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener{
        lateinit var king: KingAndQueenItem

        init {
            itemView.setOnClickListener(this)
        }
        fun bindKing(king: KingAndQueenItem){
            this.king = king
            itemView.txt_name.text = king.name
            Picasso.get()
                .load(king.img_url)
                .placeholder(R.drawable.kingandqueen)
                .into(itemView.kingandqueen_Image)
        }
        override fun onClick(v: View?) {
            clickListener?.onClick(king)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KingAdapter.KingViewHolder {
        var viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.kingandqueen_item_list,parent,false)
        return KingViewHolder(viewHolder)
    }

    override fun getItemCount(): Int {
        return kingList.size
    }

    override fun onBindViewHolder(holder: KingAdapter.KingViewHolder, position: Int) {
        holder.bindKing(kingList[position])
    }

    fun updateKing(kingList:List<KingAndQueenItem>){
        this.kingList = kingList
        notifyDataSetChanged()
    }

    interface KingClickListener{
        fun onClick(king:KingAndQueenItem)
    }
}