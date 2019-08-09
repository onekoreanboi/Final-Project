package com.example.finalproject.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.R
import com.example.finalproject.adapters.PlayerRecyclerViewAdapter
import com.example.finalproject.models.PlayerData
import com.example.finalproject.network.Endpoints
import com.example.finalproject.network.RetroFitInstance
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_stats_page.*
import kotlinx.android.synthetic.main.fragment_welcome_page.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FragmentStatsPage : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PlayerRecyclerViewAdapter { player ->
            // 08 Create the Bundle to pass the information to the next Fragment
            val bundle = Bundle()
            bundle.putInt(getString(R.string.playerKills), player.playerKills)
            bundle.putInt(getString(R.string.playerDeaths), player.playerDeaths)
            bundle.putDouble(getString(R.string.playerKD), player.playerKD)
            bundle.putInt(getString(R.string.playerWins), player.playerWins)
            // 08 pass bundle to the Fragment, through Navigation
            // 06
        }
        userStats.apply {
            this.adapter = adapter
            // 06 A Fragment's Context is nullable.  This checks if it is or not.
            context?.let {
                /*
                06 A RecyclerView requires a LayoutManager. A LinearLayoutManager gives
                us vertical scrolling rows. There are LayoutManagers for creating grids and
                going horizontal as well. All require a Context Object, represented by 'it'.
                 */
                this.layoutManager = LinearLayoutManager(it)
            }
        }

        // 06_2 Uncomment the next line during this step for testing the RecyclerView
        //displayTestData(adapter)

        // 07 Comment out displayTestData() above.
        // 07 Performs our network request and displays the data.
        fetchDataFromServer(adapter)
    }

    /**
     * 07
     */
    private fun fetchDataFromServer(adapter: PlayerRecyclerViewAdapter) {
        // Gets our endpoints as defined in the Endpoints interface
        val apiCalls = RetroFitInstance.retrofit
        // Gets the specific call we want
        val request = apiCalls.create(Endpoints::class.java).getPlayerAPIKey(steamInput.editableText.toString())
        /* This line is where the actual request is triggered. It requires a Callback.
        A Callback is a way of telling the app what to do when it gets a response
        back from the API.

        NOTE: Make sure you import the RetroFit2 Callback and that the Callback type
        inside the <> matches what is inside the <> in the declaration of the function
        in the Endpoints interface.
        */
        request.enqueue(object : Callback<List<PlayerData>> {
            // Tell the app what to do if the network call fails for any reason.
            override fun onFailure(call: Call<List<PlayerData>>, t: Throwable) {
                // Logcat Warn
                Log.w(javaClass.name, "getEmployeeList() failed. Error: ${t.message}")
                // Show pop up if Fragment is still in view
                constraintLayout?.let {
                    Snackbar.make(it, "Network request failed.", Snackbar.LENGTH_LONG).show()
                }
            }
            // Tell the app what to do if the network call responds.  This does not mean that it
            // got your data yet.  A 404 from the API is a response.
            override fun onResponse(call: Call<List<PlayerData>>, response: Response<List<PlayerData>>) {
                // Get response code
                when (response.code()) {
                    // 200 equals a successful GET request that will contain the data requested
                    200 -> {
                        // data requested may have succeeded, but there may have been 0 results,
                        // so we check for null first
                        response.body()?.let {
                            // if not null, send the data to the ListAdapter so that it can be
                            // shown in the RecyclerView
                            adapter.submitList(it)
                        }
                    }
                    // If response code is anything but 200, show an error and the error code
                    else -> {
                        constraintLayout?.let {
                            Snackbar.make(it, "Something went wrong. CODE: ${response.code()}", Snackbar.LENGTH_LONG).show()
                        }
                    }
                }
            }
        })
    }

    /** 06_2 This creates a set of test data and submits it to our adapter, to be displayed
     * in the RecyclerView.
     *
     * NOTE: This function is only for testing our RecyclerView. We can remove this code
     * immediately after testing is successful. It will be replaced with our actual data
     * source in a future step.
     */
    private fun displayTestData(adapter: PlayerRecyclerViewAdapter) {
        val testData = listOf(
            PlayerData("OneKoreanBoi", 3, 2, 1.5, 1)
        )
        adapter.submitList(testData)
    }



    }

