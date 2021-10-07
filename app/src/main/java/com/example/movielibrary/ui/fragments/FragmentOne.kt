package com.example.movielibrary.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movielibrary.R
import com.example.movielibrary.databinding.FragmentOneBinding
import com.example.movielibrary.model.Film
import com.example.movielibrary.viewModel.AppState
import com.example.movielibrary.viewModel.FragmentOneViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentOne : Fragment() {
    private val viewModel: FragmentOneViewModel by viewModel()
    private var _binding: FragmentOneBinding? = null
    private val binding get() = _binding!!
    private lateinit var customView: View
    private lateinit var container: ViewGroup

    private val listGenre: ArrayList<String> = ArrayList(16)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOneBinding.inflate(inflater, container, false)

        if (container != null) {
            this.container = container
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycle.addObserver(viewModel)

        val observer = Observer<AppState> { renderData(it) }
        viewModel.getLiveData().observe(viewLifecycleOwner, observer)

        viewModel.getListFilm()
        createCustomMenu()
    }

    private fun createRecyclerView(listFilm: ArrayList<Film>) {
        val adapterView = FragmentOneRecyclerAdapter(listFilm)

        val recycler = binding.recycler
        recycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recycler.adapter = adapterView
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Success -> {
                val listFilm = appState.listFilm
                createRecyclerView(listFilm)
            }
            is AppState.Loading -> {

            }
            is AppState.Error -> {

            }
        }
    }

    private fun createCustomMenu() = with(binding) {
        val linearLayout = binding.linearLayout

        listGenre.add("Фантастика")
        listGenre.add("Драма")
        listGenre.add("Комедия")
        listGenre.add("Боевик")
        listGenre.add("Детский")
        listGenre.add("Детектив")
        listGenre.add("Триллер")
        listGenre.add("Хоррор")

        for (i in 0 until listGenre.size) {
            customView = layoutInflater.inflate(R.layout.custom_menu, container, false)

            val textView = customView.findViewById<TextView>(R.id.customMenuText)
            textView.text = listGenre[i]

            linearLayout.addView(customView)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = FragmentOne()
    }

}