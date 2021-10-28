package com.example.movielibrary.ui.fragments

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movielibrary.R
import com.example.movielibrary.model.Film

class FragmentTwoRecyclerAdapter(private val listFilm: ArrayList<Film>, private val fragment: FragmentTwo) :
    RecyclerView.Adapter<FragmentTwoRecyclerAdapter.NewViewHolder>() {

    private val imdbString: String = "imdb "
    private val genreString: String = "Жанр "
    private val yearString: String = "год "

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fillm_poster_view, parent, false)
        return NewViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: NewViewHolder, position: Int) {

        Glide.with(holder.posterView).load(listFilm[position].imagePoster).into(holder.posterView)
        holder.textView.text = listFilm[position].filmName
        holder.textViewRating.text = imdbString + listFilm[position].rating.toString()

        holder.textViewGenre.text = genreString + listFilm[position].genre
        holder.textViewYear.text = yearString + listFilm[position].year

        holder.textPositions.text = (position + 1).toString()
        holder.textPositions.visibility = View.VISIBLE

        holder.cardView.setOnClickListener {
            fragment.clickedRecycler(position)
        }
    }

    override fun getItemCount(): Int {
        return listFilm.size
    }

    inner class NewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val posterView: ImageView = itemView.findViewById(R.id.posterView)
        val textView: TextView = itemView.findViewById(R.id.filmName)
        val textViewRating: TextView = itemView.findViewById(R.id.imdb)
        val cardView: CardView = itemView.findViewById(R.id.cardPoster)

        val textViewGenre: TextView = itemView.findViewById(R.id.genre)
        val textViewYear: TextView = itemView.findViewById(R.id.year)
        val textPositions: TextView = itemView.findViewById(R.id.positions)
    }
}
