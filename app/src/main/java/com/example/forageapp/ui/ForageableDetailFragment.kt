package com.example.forageapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.forageapp.R
import com.example.forageapp.databinding.FragmentForageableDetailBinding
import com.example.forageapp.model.Item
import com.example.forageapp.ui.viewmodel.ForageableViewModel

/**
 * A fragment to display the details of a [Item] currently stored in the database.
 * The [AddForageableFragment] can be launched from this fragment to edit the [Item]
 */
class ForageableDetailFragment : Fragment() {

    private val navigationArgs: ForageableDetailFragmentArgs by navArgs()

    private val viewModel: ForageableViewModel by activityViewModels()

    private lateinit var forageable: Item

    private var _binding: FragmentForageableDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentForageableDetailBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = navigationArgs.id
        viewModel.retrieveForageable(id).observe(this.viewLifecycleOwner){
                selectedForagable->
            forageable = selectedForagable
            bindForageable()
        }
    }

    private fun bindForageable() {
        binding.apply {
            name.text = forageable.name
            location.text = forageable.address
            notes.text = forageable.notes
            if (forageable.inSeason) {
                season.text = getString(R.string.in_season)
            } else {
                season.text = getString(R.string.out_of_season)
            }
            editForageableFab.setOnClickListener {
                val action = ForageableDetailFragmentDirections
                    .actionForageableDetailFragmentToAddForageableFragment(forageable.id)
                findNavController().navigate(action)
            }

            location.setOnClickListener {
                launchMap()
            }
        }
    }

    private fun launchMap() {
        val address = forageable.address.let {
            it.replace(", ", ",")
            it.replace(". ", " ")
            it.replace(" ", "+")
        }
        val gmmIntentUri = Uri.parse("geo:0,0?q=$address")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }
}
