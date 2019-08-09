package com.example.finalproject.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.finalproject.R
import kotlinx.android.synthetic.main.fragment_welcome_page.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FragmentWelcomePage : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_welcome_page, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        buttonTutorial.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentWelcomePage_to_fragmentTutorialPage)
        }
        buttonSubmit.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentWelcomePage_to_fragmentStatsPage)
        }
    }


}
