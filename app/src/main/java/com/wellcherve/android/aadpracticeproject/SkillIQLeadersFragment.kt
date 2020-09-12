package com.wellcherve.android.aadpracticeproject

import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.wellcherve.android.aadpracticeproject.adapters.LeadersAdapter
import com.wellcherve.android.aadpracticeproject.models.Leader
import com.wellcherve.android.aadpracticeproject.models.SkillIQLeader
import kotlinx.android.synthetic.main.fragment_learning_leaders.*
import kotlinx.android.synthetic.main.fragment_skill_iq_leaders.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
 const val SKILL_IQ_LEADERS = "skill_iq_leaders"

/**
 * A simple [Fragment] subclass.
 * Use the [SkillIQLeadersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SkillIQLeadersFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var skillIQLeaders: ArrayList<Leader>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_skill_iq_leaders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeSkillIQLeaders()
    }

    private fun initializeSkillIQLeaders() {
       ExecuteSkillIQLeadersTask().execute(RequestHandler.SKILL_IQ_LEADERS_PATH)
    }

    inner class ExecuteSkillIQLeadersTask: AsyncTask<String, Unit, ArrayList<Leader>>() {

        override fun doInBackground(vararg params: String?): ArrayList<Leader> {
            return RequestHandler.makeRequest(params[0]);
        }

        override fun onPostExecute(result: ArrayList<Leader>?) {
            if (result != null) {
                skillIQLeaders = result
                skillIQLeadersList.adapter = LeadersAdapter(skillIQLeaders, R.layout.skill_iq_leader_item)
                skillIQLeadersList.layoutManager = LinearLayoutManager(context)
                skillIQLeadersProgress.visibility = View.GONE

            }

        }
    }

}