package com.example.movielibrary.ui.fragments

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.movielibrary.R
import com.example.movielibrary.model.Film

class FragmentOneRecyclerAdapter(private val listFilm: ArrayList<Film>) :
    RecyclerView.Adapter<FragmentOneRecyclerAdapter.NewViewHolder>() {
    private val imdbString: String = "imdb "

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fillm_poster_view, parent, false)
        return NewViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: NewViewHolder, position: Int) {

        holder.posterView.setImageResource(listFilm[position].imagePoster)
        holder.textView.text = listFilm[position].filmName
        holder.textViewRating.text = imdbString + listFilm[position].rating.toString()

        holder.cardView.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return listFilm.size
    }

    class NewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val posterView: ImageView = itemView.findViewById(R.id.posterView)
        val textView: TextView = itemView.findViewById(R.id.filmName)
        val textViewRating: TextView = itemView.findViewById(R.id.imdb)
        val cardView: CardView = itemView.findViewById(R.id.cardPoster)
    }
}
