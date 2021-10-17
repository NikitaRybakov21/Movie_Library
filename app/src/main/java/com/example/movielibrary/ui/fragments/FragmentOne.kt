package com.example.movielibrary.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movielibrary.R
import com.example.movielibrary.databinding.FragmentOneBinding
import com.example.movielibrary.model.Film
import com.example.movielibrary.model.InfoFilm
import com.example.movielibrary.ui.main.MainActivity
import com.example.movielibrary.ui.main.setToast
import com.example.movielibrary.viewModel.AppState
import com.example.movielibrary.viewModel.FragmentOneViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentOne : Fragment() {
    private val viewModel: FragmentOneViewModel by viewModel()
    private var _binding: FragmentOneBinding? = null
    private val binding get() = _binding!!

    private lateinit var customView: View
    private lateinit var container: ViewGroup
    private lateinit var mainActivity: MainActivity

    private val listGenre: ArrayList<String> = ArrayList(16)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentOneBinding.inflate(inflater, container, false)

        if (container != null) {
            this.container = container
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding)  {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycle.addObserver(viewModel)

        val observer = Observer<AppState> { renderData(it) }
        viewModel.getLiveData().observe(viewLifecycleOwner, observer)

        viewModel.createListFilm()
        viewModel.getListFilm()
        createCustomMenu()
    }

    private fun createRecyclerView(listFilm: ArrayList<Film>) {
        val adapterView = FragmentOneRecyclerAdapter(listFilm,this)

        val recycler = binding.recycler
        recycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recycler.adapter = adapterView
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.SuccessRecyclerFilm -> {

                progressBarRecycler.visibility = View.GONE
                val listFilm = appState.listFilm
                createRecyclerView(listFilm)
            }
            is AppState.SuccessInfoDetails -> {

                val infoFilm = appState.infoFilm
                addFragmentDetails(infoFilm)
                setScreenLoading(View.GONE)
            }
            is AppState.Loading -> {
                progressBarRecycler.visibility = View.VISIBLE
            }
            is AppState.LoadingInfo -> {
                setScreenLoading(View.VISIBLE)
            }
            is AppState.Error -> {

            }
        }
    }

    private fun setScreenLoading(visibility: Int) = with(binding){
        progressBar.visibility = visibility
        viewLoading.visibility = visibility
    }

    private fun createCustomMenu() = with(binding) {
        val linearLayout = binding.linearLayout

        listGenre.clear()

        listGenre.add(getString(R.string.genre1))
        listGenre.add(getString(R.string.genre2))
        listGenre.add(getString(R.string.genre3))
        listGenre.add(getString(R.string.genre4))
        listGenre.add(getString(R.string.genre5))
        listGenre.add(getString(R.string.genre6))
        listGenre.add(getString(R.string.genre7))
        listGenre.add(getString(R.string.genre8))
        listGenre.add(getString(R.string.genre9))

        for (i in 0 until listGenre.size) {
            customView = layoutInflater.inflate(R.layout.custom_menu, container, false)

            val textView = customView.findViewById<TextView>(R.id.customMenuText)
            textView.text = listGenre[i]

            customView.findViewById<View>(R.id.genreCard).setOnClickListener {
                setToast(listGenre[i])
            }

            linearLayout.addView(customView)
        }
    }

    fun clickedRecycler(positions: Int) {
        viewModel.getInfoFilm(positions)
        setToast(getString(R.string.Details))
    }

    private fun addFragmentDetails(infoFilm: InfoFilm){
        mainActivity.addFragmentDetails(infoFilm)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setMainActivity(mainActivity: MainActivity){
        this.mainActivity = mainActivity
    }

    companion object {
        fun newInstance() = FragmentOne()
    }

}