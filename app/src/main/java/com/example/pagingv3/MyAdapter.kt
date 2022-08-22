package com.example.pagingv3

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pagingv3.databinding.RecyclerRowBinding


class MyAdapter() : PagingDataAdapter<Item, MyAdapter.MyViewHolder>(MyDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            RecyclerRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item!!)
    }

    inner class MyViewHolder(
        private val binding: RecyclerRowBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: Item) {
            binding.fullNameText.text = item.first_name + " " + item.last_name
            binding.emailText.text = item.email

            Glide
                .with(binding.root.context)
                .load(item.avatar)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.imageImageView)

        }
    }
}
