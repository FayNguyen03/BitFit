package com.example.bitfit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.processNextEventInCurrentThread
import java.util.Date

class HealthAdapter (private val context: Context, private val entries: List<HealthData>):
    RecyclerView.Adapter<HealthAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.data_entry, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = entries[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return entries.size
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val dateView = itemView.findViewById<TextView>(R.id.date)
        private val noteView = itemView.findViewById<TextView>(R.id.note)
        private val hourView = itemView.findViewById<TextView>(R.id.sleepHour)
        private val moodView = itemView.findViewById<TextView>(R.id.feeling)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(entry: HealthData) {
            dateView.text= entry.date.toString()
            moodView.text = entry.mood.toString()
            noteView.text = entry.note.toString()
            hourView.text = entry.sleepingHour.toString()

        }
        override fun onClick(v: View?) {
            /*
            val entry = entries[absoluteAdapterPosition]
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(ARTICLE_EXTRA, article)
            context.startActivity(intent)
             */
            TODO("I dont know what to do without you")
        }
    }

}