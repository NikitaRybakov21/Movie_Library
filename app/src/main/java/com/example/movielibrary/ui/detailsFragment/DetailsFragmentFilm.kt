package com.example.movielibrary.ui.detailsFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.movielibrary.R
import com.example.movielibrary.model.InfoFilm

class DetailsFragmentFilm : Fragment() {

    companion object{
        fun newInstance(infoFilm: InfoFilm) : DetailsFragmentFilm {
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

        val infoFilm = arguments?.getParcelable<InfoFilm>("KEY")

        val posterView = view.findViewById<ImageView>(R.id.posterViewDetails)
        val textView = view.findViewById<TextView>(R.id.filmNameDetails)

        val year = view.findViewById<TextView>(R.id.yearIntDetails)
        val country = view.findViewById<TextView>(R.id.countryStringDetails)
        val director = view.findViewById<TextView>(R.id.directorStringDetails)
        val budget = view.findViewById<TextView>(R.id.budgetInt)
        val imdb = view.findViewById<TextView>(R.id.imdb)
        val kp = view.findViewById<TextView>(R.id.kp)
        val description = view.findViewById<TextView>(R.id.textStringDescriptions)

        infoFilm!!
        posterView.setImageResource(infoFilm.imagePoster)
        textView.text = infoFilm.filmName

    /*  year.text     =
        country.text  =
        director.text =
        budget.text   =
        imdb.text     =
        kp.text       =
        description   = */

        setRatingFragment(90)
    }

    private fun setRatingFragment(ratingPercent: Int){
        childFragmentManager
            .beginTransaction()
            .replace(R.id.containerRating, FragmentRating.newInstance(ratingPercent))
            .commit()
    }

}