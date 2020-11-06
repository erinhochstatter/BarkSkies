package ekh.challenge.barksky.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ekh.challenge.barksky.databinding.OverviewFragmentBinding


class OverviewFragment : Fragment() {
    private val viewModel: OverviewViewModel by lazy {ViewModelProvider(this).get(OverviewViewModel::class.java)}

    companion object {
        fun newInstance() = OverviewFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = OverviewFragmentBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        arguments?.let {
            val safeArgs = OverviewFragmentArgs.fromBundle(it)
            viewModel.getWeatherForCurrentLocation(safeArgs.latitude, safeArgs.longitude)
        }

        return binding.root
    }
}
