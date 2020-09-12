package com.wellcherve.android.aadpracticeproject.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wellcherve.android.aadpracticeproject.models.Leader
import com.wellcherve.android.aadpracticeproject.models.LearningLeader
import com.wellcherve.android.aadpracticeproject.models.SkillIQLeader
import kotlinx.android.synthetic.main.learning_leader_item.view.*

class LeadersAdapter(
    private val leaders: ArrayList<Leader>?,
    private val layout: Int
) : RecyclerView.Adapter<LeadersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        leaders?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {

        if (leaders != null) {
            return leaders.size
        }
        return 0
    }

    class ViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(leader: Leader) {
            Glide.with(itemView).load(Uri.parse(leader.badgeURl))
                .into( itemView.badgeImage)
            itemView.learnerName.setText(leader.name)
            val description = leader.toString()
            itemView.description.text = description
        }
    }
}