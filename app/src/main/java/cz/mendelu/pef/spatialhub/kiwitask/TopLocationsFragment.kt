package cz.mendelu.pef.spatialhub.kiwitask

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import cz.mendelu.pef.spatialhub.kiwitask.databinding.FragmentTopLocationsBinding
import cz.mendelu.pef.spatialhub.kiwitask.ui.TopLocationViewModel

class TopLocationsFragment: Fragment() {

    private lateinit var binding: FragmentTopLocationsBinding

    // private val viewModel: TopLocationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_top_locations, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}