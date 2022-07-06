package com.example.forageapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.forageapp.BaseApplication
import com.example.forageapp.R
import com.example.forageapp.databinding.FragmentForageableListBinding
import com.example.forageapp.ui.adapter.ForageableListAdapter
import com.example.forageapp.ui.viewmodel.ForageableViewModel
import com.example.forageapp.ui.viewmodel.ForageableViewModelFactory

/**
 * A fragment to view the list of [Forageable]s stored in the database.
 * Clicking on a [Forageable] list item launches the [ForageableDetailFragment].
 * Clicking the [FloatingActionButton] launched the [AddForageableFragment]
 */
class ForageableListFragment : Fragment() {

    private val viewModel: ForageableViewModel by activityViewModels{
        ForageableViewModelFactory(
            (activity?.application as BaseApplication).database.foragableDao()
        )
    }

    private var _binding: FragmentForageableListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentForageableListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ForageableListAdapter { forageable ->
            val action = ForageableListFragmentDirections
                .actionForageableListFragmentToForageableDetailFragment(forageable.id)
            findNavController().navigate(action)
        }

        // TODO+: observe the list of forageables from the view model and submit it the adapter
        viewModel.allData.observe(this.viewLifecycleOwner){
            items -> items.let {
                adapter.submitList(it)
        }
        }

        binding.apply {
            recyclerView.adapter = adapter
            addForageableFab.setOnClickListener {
                findNavController().navigate(
                    R.id.action_forageableListFragment_to_addForageableFragment
                )
            }
        }
    }
}
