package ekh.challenge.barksky.overview

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import ekh.challenge.barksky.R
import ekh.challenge.barksky.network.OpenWeatherApi
import kotlinx.android.synthetic.main.overview_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OverviewFragment : Fragment() {
    var response: String = ""

    companion object {
        fun newInstance() = OverviewFragment()
    }

    private lateinit var viewModel: OverviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.overview_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OverviewViewModel::class.java)
//        TODO: Bind response data to view
    }
}
