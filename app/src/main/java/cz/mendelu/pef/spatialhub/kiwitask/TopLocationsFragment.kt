package cz.mendelu.pef.spatialhub.kiwitask

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import cz.mendelu.pef.spatialhub.kiwitask.databinding.FragmentTopLocationsBinding
import cz.mendelu.pef.spatialhub.kiwitask.models.Result
import cz.mendelu.pef.spatialhub.kiwitask.others.UIUtils
import cz.mendelu.pef.spatialhub.kiwitask.ui.FlightsAdapter
import cz.mendelu.pef.spatialhub.kiwitask.ui.TopLocationViewModel
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.bind
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
        UIUtils.showStatusBar(requireActivity().window)
        setUpViews()

        lifecycleScope.launchWhenStarted {
            viewModel.flights.collect { result ->

                if (binding.swipeRefresh.isRefreshing) {
                    binding.swipeRefresh.isRefreshing = false
                }

                when (result) {
                    is Result.Empty -> {
                        showContent(false)
                        showLoading(false)
                        showPlaceholder(true)
                        setPlaceholderContent(R.string.placeholder_text_empty, R.drawable.empty_placeholder)
                    }
                    is Result.Error -> {
                        showContent(false)
                        showLoading(false)
                        showPlaceholder(true)
                        setPlaceholderContent(R.string.placeholder_text_error, R.drawable.error_placeholder)
                    }
                    Result.Loading -> {
                        showContent(false)
                        showPlaceholder(false)
                        showLoading(true)
                    }
                    is Result.Success -> {
                        showLoading(false)
                        showPlaceholder(false)
                        showContent(true)
                        flightAdapter.apply {
                            submitList(result.data)
                        }
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
            swipeRefresh.setOnRefreshListener {
                viewModel.fetchFlights()
            }
        }
    }

    private fun showContent(show: Boolean) {
        with(binding) {
            if (show) {
                recyclerView.visibility = View.VISIBLE
                circleIndicator.visibility = View.VISIBLE
            } else {
                recyclerView.visibility = View.GONE
                circleIndicator.visibility = View.GONE
            }
        }
    }

    private fun showPlaceholder(show: Boolean) {
        with(binding) {
            if (show) {
                imagePlaceholder.visibility = View.VISIBLE
                placeholderDescription.visibility = View.VISIBLE
            } else {
                imagePlaceholder.visibility = View.GONE
                placeholderDescription.visibility = View.GONE
            }
        }
    }

    private fun setPlaceholderContent(@StringRes descriptionResId: Int, @DrawableRes drawableResId: Int) {
        with(binding) {
            placeholderDescription.text = getString(descriptionResId)
            imagePlaceholder.setImageDrawable(ContextCompat.getDrawable(requireContext(), drawableResId))
        }
    }

    private fun showLoading(show: Boolean) {
        with(binding) {
            if (show) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }
    }
}