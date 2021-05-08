package cz.mendelu.pef.spatialhub.kiwitask.ui

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import cz.mendelu.pef.spatialhub.kiwitask.R
import cz.mendelu.pef.spatialhub.kiwitask.databinding.FlightListItemLayoutBinding
import cz.mendelu.pef.spatialhub.kiwitask.databinding.StopoverListItemBinding
import cz.mendelu.pef.spatialhub.kiwitask.models.Flight
import cz.mendelu.pef.spatialhub.kiwitask.models.Route
import cz.mendelu.pef.spatialhub.kiwitask.models.Stopover
import cz.mendelu.pef.spatialhub.kiwitask.others.DateTimeUtils
import cz.mendelu.pef.spatialhub.kiwitask.others.NumberUtils

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
                destination = flightBinding.root.context.getString(
                    R.string.destination_city_country,
                    flight.destinationCity,
                    flight.countryDestination?.name
                )
                flight.price?.let {
                    price = NumberUtils.getCurrencyString(it)
                }
                cityFrom = flight.cityCodeFrom
                cityTo = flight.cityCodeTo
                duration = flight.flyDuration
                flight.dTime?.let {
                    date = DateTimeUtils.getDateFromUnix(it)
                    departure = DateTimeUtils.getTimeFromUnix(it)
                }
                flight.aTime?.let {
                    arrival = DateTimeUtils.getTimeFromUnix(it)
                }
                flight.airlines?.let { airlinesList ->
                    airlines = TextUtils.join(", ", airlinesList)
                }
                seats = flight.availability?.seats?.toString()
                imageView.load("https://cdn.londonandpartners.com/-/media/images/london/visit/things-to-do/sightseeing/london-attractions/tower-bridge/thames_copyright_visitlondon_antoinebuchet640x360.jpg?mw=640&hash=27AEBE2D1B7279A196CC1B4548638A9679BE107A") {
                    crossfade(true)
                    crossfade(250)
                    scale(Scale.FIT)
                }
                flight.routes?.let { routes ->
                    if (routes.size < 2) {
                        stopoverRecyclerView.visibility = View.GONE
                        noStopoverContent.visibility = View.VISIBLE
                    } else {
                        stopoverRecyclerView.visibility = View.VISIBLE
                        noStopoverContent.visibility = View.GONE
                        stopoverRecyclerView.layoutManager = LinearLayoutManager(
                            flightBinding.root.context,
                            LinearLayoutManager.HORIZONTAL,
                            false
                        )
                        stopoverRecyclerView.setHasFixedSize(true)
                        stopoverRecyclerView.adapter = StopoverAdapter(getStopovers(routes))
                    }
                }
            }
        }
    }

    fun getStopovers(routes: List<Route>): List<Stopover> {
        val mutableStopovers = mutableListOf<Stopover>()
        for (i in 0.until(routes.size - 1)) {
            mutableStopovers.add(Stopover(routes[i].cityCodeTo ?: ""))
        }
        return mutableStopovers
    }

    inner class StopoverAdapter(private val stopovers: List<Stopover>) :
        RecyclerView.Adapter<StopoverAdapter.StopoverViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StopoverViewHolder {
            val itemBinding =
                StopoverListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return StopoverViewHolder(itemBinding)
        }

        override fun onBindViewHolder(holder: StopoverViewHolder, position: Int) {}

        override fun getItemCount(): Int = stopovers.size

        inner class StopoverViewHolder constructor(private val stopoverBinding: StopoverListItemBinding) :
            RecyclerView.ViewHolder(stopoverBinding.root) {
        }
    }
}