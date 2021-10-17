package com.example.movielibrary.ui.detailsFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.movielibrary.R
import com.example.movielibrary.model.Film

class DetailsFragmentFilm : Fragment() {

    companion object{
        fun newInstance(infoFilm: Film) : DetailsFragmentFilm {
            val detailsFragmentFilm = DetailsFragmentFilm()
            val bundle = Bundle()
            bundle.putParcelable("KEY",infoFilm)
            detailsFragmentFilm.arguments = bundle
            return detailsFragmentFilm
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.details_fragment_film,container,false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val infoFilm = arguments?.getParcelable<Film>("KEY")

        val posterView = view.findViewById<ImageView>(R.id.posterViewDetails)
        val textView = view.findViewById<TextView>(R.id.filmNameDetails)

        val year = view.findViewById<TextView>(R.id.yearIntDetails)
        val country = view.findViewById<TextView>(R.id.countryStringDetails)
        val director = view.findViewById<TextView>(R.id.directorStringDetails)
        val budget = view.findViewById<TextView>(R.id.budgetInt)
        val imdb = view.findViewById<TextView>(R.id.imdb)
        val kp = view.findViewById<TextView>(R.id.kp)
        val description = view.findViewById<TextView>(R.id.textStringDescriptions)

        if(infoFilm != null) {
            Glide.with(posterView).load(infoFilm.imagePoster).into(posterView)
            textView.text = infoFilm.filmName

            year.text = infoFilm.year.toString()
            imdb.text = "imdb " + infoFilm.rating.toString()
            description.text = infoFilm.description
            budget.text = infoFilm.budget.toString() + "$"

            country.text = "_"
            director.text = "_"
            kp.text = "kp " + "_"

            setRatingFragment(infoFilm.rating.toInt() * 10)
        }
    }

    private fun setRatingFragment(ratingPercent: Int){
        childFragmentManager
            .beginTransaction()
            .replace(R.id.containerRating, FragmentRating.newInstance(ratingPercent))
            .commit()
    }

}