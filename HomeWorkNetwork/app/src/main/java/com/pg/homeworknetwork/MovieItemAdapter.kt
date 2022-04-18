package com.pg.homeworknetwork

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation

internal class MovieItemAdapter : ListAdapter<Movie, MovieItemAdapter.ViewHolder>(MOVIE_COMPARATOR) {
    private var clickListener: IOnItemClick? = null

    fun setClickListener(listener: IOnItemClick?) {
        clickListener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.parentItem.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.recycler_anim)
    }


    interface IOnItemClick {
        fun onItemClick(movie: Movie)
    }

    internal inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var parentItem: LinearLayout = itemView.findViewById(R.id.parentItem)
        private var image: ImageView = itemView.findViewById(R.id.image)
        private var itemTitle: TextView = itemView.findViewById(R.id.itemTitle)

        fun bind(movie: Movie) {
            itemView.setOnClickListener {
                clickListener?.onItemClick(movie)
            }
            image.requestLayout()
            itemTitle.text = movie.title

            image.load(movie.image) {
                transformations(RoundedCornersTransformation(16f))
            }
        }
    }

    companion object {
        val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun getChangePayload(oldItem: Movie, newItem: Movie): Any? {
                return null
            }
        }
    }
}