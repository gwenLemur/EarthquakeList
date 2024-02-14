package com.example.earthquakelist
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.example.earthquakelist.models.Features
import kotlin.math.roundToInt


class EarthquakeAdapter(private var earthquakeList: List<Features>) :
    RecyclerView.Adapter<EarthquakeAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textMag: TextView
        val textPlace: TextView
        val textTitle: TextView
        val textUrl: TextView
        var image: ImageView
        val layout: ConstraintLayout

        init {
            textMag = view.findViewById(R.id.magnitude)
            textPlace = view.findViewById(R.id.place)
            textTitle = view.findViewById(R.id.titles)
            textUrl = view.findViewById(R.id.url)
            layout = view.findViewById(R.id.item_layout)
            image = view.findViewById(R.id.imageView)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_earthquake, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val quake = earthquakeList[position]


        val context = viewHolder.layout.context
        viewHolder.textMag.text = ((quake.properties.mag * 10).roundToInt()/10.0).toString()

        if(quake.properties.mag < 2){
            viewHolder.textMag.setTextColor(getColor(viewHolder.textMag.context, R.color.green))
        }else if(quake.properties.mag < 2.5){
            viewHolder.textMag.setTextColor(getColor(viewHolder.textMag.context, R.color.yellow))
        }else if(quake.properties.mag < 3){
            viewHolder.textMag.setTextColor(getColor(viewHolder.textMag.context, R.color.orange))
        } else if(quake.properties.mag < 7){
            viewHolder.textMag.setTextColor(getColor(viewHolder.textMag.context, R.color.red))
            Log.d("crash","CRASHING")
            viewHolder.image.setImageResource(R.drawable.warning);

        }
        viewHolder.textPlace.text = quake.properties.place
        viewHolder.textTitle.text = quake.properties.title
        viewHolder.textUrl.text = quake.properties.url


        viewHolder.layout.setOnClickListener {

            //Intent here
            val heroIntent = Intent(context, MapDisplayActivity::class.java)
            heroIntent.putExtra("currQuake", quake)
            context.startActivity(heroIntent)

        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = earthquakeList.size

}
