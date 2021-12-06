package com.example.movielibrary.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movielibrary.databinding.FragmentTwoBinding
import com.example.movielibrary.model.Film
import com.example.movielibrary.viewModel.AppState
import com.example.movielibrary.viewModel.FragmentTwoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer
import com.example.movielibrary.dataBase.SqLiteDatabase
import com.example.movielibrary.ui.main.MainActivity

class FragmentTwo : Fragment() {
    private val viewModel: FragmentTwoViewModel by viewModel()
    private var _binding: FragmentTwoBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainActivity: MainActivity
    private lateinit var db : SqLiteDatabase

    private val cinemaID = listOf(326,325,327,111543,329)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycle.addObserver(viewModel)

        val observer = Observer<AppState> { renderData(it) }
        viewModel.getLiveData().observe(viewLifecycleOwner, observer)

        viewModel.getListFilm(cinemaID)

        db = SqLiteDatabase(requireContext())
    }

    private fun createRecyclerView(listFilm: ArrayList<Film>) {
        val adapterView = FragmentTwoRecyclerAdapter(listFilm,this)

        val recycler = binding.recyclerTwo
        recycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recycler.adapter = adapterView
    }

    fun clickedRecycler(positions: Int) {
        viewModel.getInfoFilm(positions)
    }

    private fun addFragmentDetails(infoFilm: Film){
        mainActivity.addFragmentDetails(infoFilm)
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.SuccessRecyclerFilm -> {
                progressBarRecyclerTwo.visibility = View.GONE

                val listFilm = appState.listFilm
                createRecyclerView(listFilm)
            }
            is AppState.SuccessInfoDetails -> {
                val infoFilm = appState.infoFilm
                addFragmentDetails(infoFilm)

                setScreenLoading(View.GONE)
                addHistoryFilm(infoFilm)
            }
            is AppState.Loading -> {
                progressBarRecyclerTwo.visibility = View.VISIBLE

            }
            is AppState.LoadingInfo -> {
                setScreenLoading(View.VISIBLE)
            }
            is AppState.Error -> {

            }
        }
    }

    private fun setScreenLoading(visibility: Int) = with(binding){
        progressBarTwo.visibility = visibility
        viewLoadingTwo.visibility = visibility
    }

    private fun addHistoryFilm(infoFilm: Film){
        db.insertFilm(infoFilm.filmName,"10" ,infoFilm.id_kp)
    }

    fun setMainActivity(mainActivity: MainActivity){
        this.mainActivity = mainActivity
    }

    companion object {
        fun newInstance() = FragmentTwo()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}