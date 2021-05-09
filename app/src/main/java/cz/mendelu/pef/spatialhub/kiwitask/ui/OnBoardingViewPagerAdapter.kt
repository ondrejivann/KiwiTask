package cz.mendelu.pef.spatialhub.kiwitask.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cz.mendelu.pef.spatialhub.kiwitask.R
import cz.mendelu.pef.spatialhub.kiwitask.databinding.OnBoardingSlideLayoutBinding

class OnBoardingViewPagerAdapter(
    private val onBoardingSlides: List<OnBoardingSlide>
) : RecyclerView.Adapter<OnBoardingViewPagerAdapter.OnBoardingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(OnBoardingSlideLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(onBoardingSlides[position])
    }

    override fun getItemCount(): Int = onBoardingSlides.size

    inner class OnBoardingViewHolder(private val binding: OnBoardingSlideLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(onBoardingSlide: OnBoardingSlide) {
            with(binding) {
                onBoardingText = onBoardingSlide.onBoardingText
                //onBoardingAnimation = onBoardingSlide.onBoardingAnimation
                //binding.animView.imageAssetsFolder = "raw"
                binding.animView.setAnimation(onBoardingSlide.onBoardingAnimationResId)
            }
        }
    }
}