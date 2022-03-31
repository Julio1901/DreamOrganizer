package com.example.dreamorganizer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dreamorganizer.R
import com.example.dreamorganizer.model.DreamVO
import com.example.dreamorganizer.util.ImageManager
import com.google.android.material.imageview.ShapeableImageView

//TODO: Replace any with the data model class
class DreamAdapter (private val dreamList : List<DreamVO>) : RecyclerView.Adapter<DreamAdapter.DreamViewHolder>() {

    private val imageManager  = ImageManager()

    class DreamViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val dreamImage : ShapeableImageView
        val dreamTitle : TextView


        init {
            dreamImage = view.findViewById(R.id.siv_card_view_repository_list_author_picture)
            dreamTitle = view.findViewById(R.id.tv_car_view_repository_list_dream_title)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DreamViewHolder {
        return DreamViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_card_item, parent, false))
    }

    override fun onBindViewHolder(holder: DreamViewHolder, position: Int) {
        val item = dreamList[position]

        holder.dreamImage.setImageBitmap(imageManager.convertBankImageToDisplay(item.dreamImage))
        holder.dreamTitle.text = item.dreamTitle


    }

    override fun getItemCount() = dreamList.size


}