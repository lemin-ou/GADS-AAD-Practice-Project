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
import com.wellcherve.android.aadpracticeproject.models.LearningLeader
import kotlinx.android.synthetic.main.fragment_learning_leaders.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
 const val LEARNING_LEADERS = "learning_leaders"

/**
 * A simple [Fragment] subclass.
 * Use the [LearningLeadersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LearningLeadersFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var learningLeaders: ArrayList<Leader>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            learningLeaders = it.getParcelableArrayList<Leader>(LEARNING_LEADERS)
//            learningLeaders?.sortWith(compareByDescending{it.hours})}
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_learning_leaders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeLearningLeadersList()
    }

    private fun initializeLearningLeadersList() {
        ExecuteLearningLeadersTask().execute(RequestHandler.LEARNING_LEADERS_PATH)
    }

    inner class ExecuteLearningLeadersTask: AsyncTask<String, Unit, ArrayList<Leader>>() {

        override fun doInBackground(vararg params: String?): ArrayList<Leader> {
            return RequestHandler.makeRequest(params[0]);
        }

        override fun onPostExecute(result: ArrayList<Leader>?) {
            if (result != null) {
                learningLeaders = result
                learningLeadersList.adapter =  LeadersAdapter(learningLeaders, R.layout.learning_leader_item)
                learningLeadersList.layoutManager = LinearLayoutManager(context)
                learning_leaders_progress.visibility = View.GONE

            }

        }
    }
}