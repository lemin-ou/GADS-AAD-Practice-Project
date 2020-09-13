package com.wellcherve.android.aadpracticeproject

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wellcherve.android.aadpracticeproject.models.EMAIL_ADDRESS
import com.wellcherve.android.aadpracticeproject.models.FIRST_NAME
import com.wellcherve.android.aadpracticeproject.models.GITHUB_LINK
import com.wellcherve.android.aadpracticeproject.models.LAST_NAME
import kotlinx.android.synthetic.main.fragment_form.*
import java.lang.ClassCastException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FormFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FormFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    interface OnSubmitButtonClickedListener {
        fun onSubmitButtonClickedListener(bundle: Bundle?)
    }

    var listener :OnSubmitButtonClickedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? OnSubmitButtonClickedListener
        if (listener == null)
            throw ClassCastException("$context must implement OnSubmitButtonClickedListener")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        showDialog()
    }

    private fun showDialog() {
        submit_button.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(FIRST_NAME, firstName.text.toString())
            bundle.putString(LAST_NAME, lastName.text.toString())
            bundle.putString(EMAIL_ADDRESS, email.text.toString())
            bundle.putString(GITHUB_LINK, githubLink.text.toString())
            listener?.onSubmitButtonClickedListener(bundle)
        }
    }
}