package cz.mendelu.pef.spatialhub.kiwitask

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import cz.mendelu.pef.spatialhub.kiwitask.databinding.FragmentTopLocationsBinding
import cz.mendelu.pef.spatialhub.kiwitask.models.Result
import cz.mendelu.pef.spatialhub.kiwitask.ui.FlightsAdapter
import cz.mendelu.pef.spatialhub.kiwitask.ui.TopLocationViewModel
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopLocationsFragment : Fragment() {

    private lateinit var binding: FragmentTopLocationsBinding

    private val viewModel: TopLocationViewModel by viewModel()

    private lateinit var flightAdapter: FlightsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_top_locations, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViews()

        lifecycleScope.launchWhenStarted {
            viewModel.flights.collect { result ->
                when (result) {
                    is Result.Empty -> {
                        Log.d("TopLocationsFragmentLog", "Empty")
                    }
                    is Result.Error -> {
                        Log.d("TopLocationsFragmentLog", "Error")
                    }
                    Result.Loading -> {
                        Log.d("TopLocationsFragmentLog", "Loading")
                    }
                    is Result.Success -> {
                        flightAdapter.apply {
                            submitList(result.data)
                        }
                        Log.d("TopLocationsFragmentLog", result.data.toString())
                    }
                }
            }
        }
    }

    private fun setUpViews() {
        with(binding) {
            recyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            circleIndicator.attachToRecyclerView(recyclerView, recyclerView.snapHelper)
            flightAdapter = FlightsAdapter()
            recyclerView.adapter = flightAdapter.apply {
                registerAdapterDataObserver(circleIndicator.adapterDataObserver)
            }
        }
    }
}