package cz.mendelu.pef.spatialhub.kiwitask.ui

import androidx.annotation.RawRes
import androidx.annotation.StringRes

data class OnBoardingSlide(
    @StringRes val onBoardingTextResId: Int,
    @RawRes val onBoardingAnimationResId: Int
)
