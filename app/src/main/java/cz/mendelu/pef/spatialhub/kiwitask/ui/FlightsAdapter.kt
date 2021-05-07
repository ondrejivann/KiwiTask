package cz.mendelu.pef.spatialhub.kiwitask.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import cz.mendelu.pef.spatialhub.kiwitask.databinding.FlightListItemLayoutBinding
import cz.mendelu.pef.spatialhub.kiwitask.models.Flight

class FlightsAdapter : ListAdapter<Flight, FlightsAdapter.FlightViewHolder>(FlightDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightViewHolder {
        val itemBinding =
            FlightListItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FlightViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: FlightViewHolder, position: Int) {
        val flight = getItem(position)
        holder.bind(flight)
    }

    object FlightDiffCallback : DiffUtil.ItemCallback<Flight>() {
        override fun areItemsTheSame(oldItem: Flight, newItem: Flight): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Flight, newItem: Flight): Boolean {
            return oldItem == newItem
        }
    }

    inner class FlightViewHolder constructor(private val flightBinding: FlightListItemLayoutBinding) :
        RecyclerView.ViewHolder(flightBinding.root) {
            fun bind(flight: Flight) {
                with(flightBinding) {
                    destination = flight.cityTo
                    price = flight.price.toString()
                    imageView.load("https://cdn.londonandpartners.com/-/media/images/london/visit/things-to-do/sightseeing/london-attractions/tower-bridge/thames_copyright_visitlondon_antoinebuchet640x360.jpg?mw=640&hash=27AEBE2D1B7279A196CC1B4548638A9679BE107A") {
                        crossfade(true)
                        crossfade(500)
                        scale(Scale.FIT)
                    }
                }
            }
        }
}