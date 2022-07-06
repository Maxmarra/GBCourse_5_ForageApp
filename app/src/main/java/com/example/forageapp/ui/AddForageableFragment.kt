package com.example.forageapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.forageapp.R
import com.example.forageapp.databinding.FragmentAddForageableBinding
import com.example.forageapp.model.Item
import com.example.forageapp.ui.viewmodel.ForageableViewModel

/**
 * A fragment to enter data for a new [Item] or edit data for an existing [Item].
 * [Item]s can be saved or deleted from this fragment.
 */
class AddForageableFragment : Fragment() {

    private val navigationArgs: AddForageableFragmentArgs by navArgs()

    private var _binding: FragmentAddForageableBinding? = null

    private lateinit var forageable: Item

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: ForageableViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddForageableBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = navigationArgs.id
        if (id > 0) {

            // TODO: Observe a Forageable that is retrieved by id, set the forageable variable,
            //  and call the bindForageable method
            viewModel.retrieveForageable(id).observe(this.viewLifecycleOwner){
                selectedForagable -> forageable = selectedForagable
                bindForageable(forageable)
            }

            binding.deleteBtn.visibility = View.VISIBLE
            binding.deleteBtn.setOnClickListener {
                deleteForageable(forageable)
            }
        } else {
            binding.saveBtn.setOnClickListener {
                addForageable()
            }
        }
    }

    private fun deleteForageable(forageable: Item) {
        viewModel.deleteForageable(forageable)
        findNavController().navigate(
            R.id.action_addForageableFragment_to_forageableListFragment
        )
    }

    private fun addForageable() {
        if (isValidEntry()) {
            viewModel.addForageable(
                binding.nameInput.text.toString(),
                binding.locationAddressInput.text.toString(),
                binding.inSeasonCheckbox.isChecked,
                binding.notesInput.text.toString()
            )
            findNavController().navigate(
                R.id.action_addForageableFragment_to_forageableListFragment
            )
        }
    }

    private fun updateForageable() {
        if (isValidEntry()) {
            viewModel.updateForageable(
                id = navigationArgs.id,
                name = binding.nameInput.text.toString(),
                address = binding.locationAddressInput.text.toString(),
                inSeason = binding.inSeasonCheckbox.isChecked,
                notes = binding.notesInput.text.toString()
            )
            findNavController().navigate(
                R.id.action_addForageableFragment_to_forageableListFragment
            )
        }
    }

    private fun bindForageable(forageable: Item) {
        binding.apply{
            nameInput.setText(forageable.name, TextView.BufferType.SPANNABLE)
            locationAddressInput.setText(forageable.address, TextView.BufferType.SPANNABLE)
            inSeasonCheckbox.isChecked = forageable.inSeason
            notesInput.setText(forageable.notes, TextView.BufferType.SPANNABLE)
            saveBtn.setOnClickListener {
                updateForageable()
            }
        }

    }

    private fun isValidEntry() = viewModel.isValidEntry(
        binding.nameInput.text.toString(),
        binding.locationAddressInput.text.toString()
    )

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
