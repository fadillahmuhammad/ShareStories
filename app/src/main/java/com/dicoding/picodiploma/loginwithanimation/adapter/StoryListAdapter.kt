package com.dicoding.picodiploma.loginwithanimation.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.loginwithanimation.data.remote.response.ListStoryItem
import com.dicoding.picodiploma.loginwithanimation.databinding.ItemRowStoryBinding
import com.dicoding.picodiploma.loginwithanimation.view.detail.DetailActivity
import java.text.SimpleDateFormat
import java.util.Locale

class StoryListAdapter :
    PagingDataAdapter<ListStoryItem, StoryListAdapter.MyViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemRowStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val story = getItem(position)
        if (story != null) {
            holder.bind(story)
        }
    }

    class MyViewHolder(private val binding: ItemRowStoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(story: ListStoryItem) {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date = story.createdAt?.let { dateFormat.parse(it) }
            val formattedDate = date?.let { dateFormat.format(it) }
            binding.tvDate.text = formattedDate
            Glide.with(binding.photoContent.context)
                .load(story.photoUrl)
                .into(binding.photoContent)
            binding.tvItemUsername.text = "${story.name}"
            binding.tvDescription.text = "${story.description}"

            binding.root.setOnClickListener {
                openDetailPage(story)
            }
        }

        private fun openDetailPage(story: ListStoryItem) {
            val intent = Intent(binding.root.context, DetailActivity::class.java)
            intent.putExtra(NAME_KEY, story.name)
            intent.putExtra(PHOTO_KEY, story.photoUrl)
            intent.putExtra(DATE_KEY, story.createdAt)
            intent.putExtra(DESC_KEY, story.description)

            val optionsCompat: ActivityOptionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                    itemView.context as Activity,
                    Pair(binding.tvItemUsername, "name"),
                    Pair(binding.photoContent, "photo"),
                    Pair(binding.tvDate, "date"),
                    Pair(binding.tvDescription, "desc"),
                )
            itemView.context.startActivity(intent, optionsCompat.toBundle())
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListStoryItem>() {
            override fun areItemsTheSame(oldItem: ListStoryItem, newItem: ListStoryItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ListStoryItem,
                newItem: ListStoryItem
            ): Boolean {
                return oldItem == newItem
            }
        }

        const val NAME_KEY = "name_data"
        const val PHOTO_KEY = "photo_data"
        const val DATE_KEY = "date_data"
        const val DESC_KEY = "desc_data"
    }
}