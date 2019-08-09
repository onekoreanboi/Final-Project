package com.example.finalproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.example.finalproject.models.PlayerData

class PlayerRecyclerViewAdapter(private val onClick: (PlayerData) -> Unit) : ListAdapter<PlayerData, PlayerViewHolder>(PlayerDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        /*
        06 Creates the View and links the XML layout file to the Kotlin, similar to how Fragments
        are created.  This gets a LayoutInflater from the Fragment that the Adapter is instantiated
        in, then inflates the XML file we created to display the Employee name in the RecyclerView.
        */
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_player, parent, false)
        /* 06 We then link that View to our ViewHolder. This ViewHolder will then be passed on to
        onBindViewHolder to have an Object from the list bound to it for display. We also pass
        the onClick lambda to the ViewHolder, to be used in the onClickListener.
        */
        return PlayerViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        /*
        06 Binds the data to the ViewHolder. The holder comes from the ViewHolder
        created in the onCreateViewHolder method. position comes from the index
        of where a specific ViewHolder is in the overall RecyclerView.

        The getItem function gets the list of data that was passed to the
        Adapter in the Fragment.
        */
        holder.player = getItem(position)
    }
}

class PlayerViewHolder(itemView: View, private val onClick: (PlayerData) -> Unit) : RecyclerView.ViewHolder(itemView) {
    // 06 holds the data that is bound to this ViewHolder
    var player: PlayerData? = null
        /*
        06 Custom setter is used to ensure that if a null value is somehow, bound
        to a ViewHolder, it is not displayed, preventing a NullPointerException,
        which prevents the app from crashing.

        value is the Object that you are attempting to set employee to.
        */
        set(value) {
            // checks if value is null, if not executes the code inside the {}
            value?.let {
                // field represents the variable employee.  This actually sets the value of employee
                field = it
                // Finds the TextView from the XML inflated in our ViewHolder, and sets the text
                // to the employeeName.
                itemView.findViewById<TextView>(R.id.textViewVHPlayerName).text = it.playerName
                itemView.findViewById<TextView>(R.id.textViewVHPlayerKills).text = it.playerKills.toString()
                itemView.findViewById<TextView>(R.id.textViewVHPlayerDeaths).text = it.playerDeaths.toString()
                itemView.findViewById<TextView>(R.id.textViewVHPlayerKD).text = it.playerKD.toString()
                itemView.findViewById<TextView>(R.id.textViewVHPlayerWins).text = it.playerWins.toString()
                /* This makes the entire ViewHolder clickable. It does this by using the lambda
                we passed in when we instantiated our adapter.  The onClick takes an Employee as an
                argument.
                */
                itemView.setOnClickListener { _ -> onClick(it) }
                /* Further reading and an explanation of 'it' and '_' can be found at the following
                link. https://kotlinlang.org/docs/reference/lambdas.html
                */
            }
        }
}

/**
 * 06 This class is responsible for handling changes in your data.
 *
 * It calls to methods that compare the Objects in your list to each
 * other, and animates changes in the RecyclerView, instead of things
 * just abruptly resetting and jumping around the screen.
 */
class PlayerDiffCallback : DiffUtil.ItemCallback<PlayerData>() {
    override fun areItemsTheSame(oldItem: PlayerData, newItem: PlayerData): Boolean {
        // 06 Items are the same if they are saved in the same location in memory.
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: PlayerData, newItem: PlayerData): Boolean {
        /*
        06 Because we used a 'data' class for our Employee Object, we get an implementation
        of the equals function for us, that compares the content of two Objects.
         */
        return oldItem == newItem
    }
}