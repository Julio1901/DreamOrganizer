package com.example.dreamorganizer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dreamorganizer.R
import com.example.dreamorganizer.features.dreams.model.DreamDTO
import com.example.dreamorganizer.presentation.container.interact.HomeNavigationEvent
import com.example.dreamorganizer.presentation.viewModel.NavigationViewModel
import com.example.dreamorganizer.util.ImageManager
import com.google.android.material.imageview.ShapeableImageView

class DreamAdapter (private val dreamList : List<DreamDTO>,
                    private val navigationViewModel: NavigationViewModel
) : RecyclerView.Adapter<DreamAdapter.DreamViewHolder>() {

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

        //TODO: REPLACE IT
        if(item.image != null){
            holder.dreamImage.setImageBitmap(imageManager.convertBankImageToDisplay(item.image))
        }

        holder.dreamTitle.text = item.name
        holder.itemView.setOnClickListener {
            //TODO: Check if the holder.Id type can be changed to Int
            navigationViewModel.interpretNavigation(HomeNavigationEvent.OnNavigateToDreamDetail(item.id))
        }


    }

    override fun getItemCount() = dreamList.size


}