package cz.mendelu.pef.spatialhub.kiwitask.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import cz.mendelu.pef.spatialhub.kiwitask.R
import cz.mendelu.pef.spatialhub.kiwitask.databinding.FragmentSplashScreenBinding
import cz.mendelu.pef.spatialhub.kiwitask.others.UIUtils
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashScreenFragment : Fragment() {

    private lateinit var binding: FragmentSplashScreenBinding
    private val viewModel: OnBoardingViewModel by viewModel()
    private val handler: Handler = Handler(Looper.getMainLooper())
    private val runnable = Runnable {
        viewModel.showOnBoarding().observe(requireActivity(), {
            if (it) {
                view?.findNavController()
                    ?.navigate(R.id.action_splashFragment_to_onBoardingFragment)
            } else {
                view?.findNavController()
                    ?.navigate(R.id.action_splashScreenFragment_to_topLocationsFragment)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_splash_screen, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        UIUtils.hideStatusBar(requireActivity().window)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, 1500)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }
}