package cz.mendelu.pef.spatialhub.kiwitask.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import cz.mendelu.pef.spatialhub.kiwitask.R
import cz.mendelu.pef.spatialhub.kiwitask.databinding.FragmentOnBoardingBinding
import cz.mendelu.pef.spatialhub.kiwitask.others.UIUtils
import org.koin.androidx.viewmodel.ext.android.viewModel

class OnBoardingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardingBinding
    private val viewModel: OnBoardingViewModel by viewModel()

    private val onBoardingAdapter = OnBoardingViewPagerAdapter(
        listOf(
            OnBoardingSlide(
                R.string.on_boarding_part_one,
                R.raw.homeoffice_animation_light
            ),
            OnBoardingSlide(
                R.string.on_boarding_part_two,
                R.raw.fly_animation_light
            ),
            OnBoardingSlide(
                R.string.on_boarding_part_three,
                R.raw.ticket_animation
            )
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_on_boarding, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        UIUtils.showStatusBar(requireActivity().window)
        binding.viewPager.adapter = onBoardingAdapter
        binding.indicator.setViewPager(binding.viewPager)
        binding.viewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    if (position == onBoardingAdapter.itemCount - 1) {
                        binding.buttonNext.text = getString(R.string.finish)
                        binding.buttonNext.setOnClickListener {
                            viewModel.showOnBoarding(false)
                            requireView().findNavController()
                                .navigate(R.id.action_onBoardingFragment_to_topLocationsFragment)
                        }
                    } else {
                        binding.buttonNext.text = getString(R.string.next)
                        binding.buttonNext.setOnClickListener {
                            binding.viewPager.currentItem.let {
                                binding.viewPager.setCurrentItem(it + 1, true)
                            }
                        }
                    }
                }
            }
        )
    }
}