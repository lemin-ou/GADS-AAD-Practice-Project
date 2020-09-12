package com.wellcherve.android.aadpracticeproject

import SendSubmission
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.wellcherve.android.aadpracticeproject.models.EMAIL_ADDRESS
import com.wellcherve.android.aadpracticeproject.models.FIRST_NAME
import com.wellcherve.android.aadpracticeproject.models.GITHUB_LINK
import com.wellcherve.android.aadpracticeproject.models.LAST_NAME
import kotlinx.android.synthetic.main.dialog_lyout.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Void as Void


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val TAG = "SubmissionDialogFragment"

/**
 * A simple [Fragment] subclass.
 * Use the [DialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
 class SubmissionDialogFragment : DialogFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val inflater = requireActivity().layoutInflater
            val builder = AlertDialog.Builder(it)
            val layout = inflater.inflate(R.layout.dialog_lyout, null)
            builder.setView(layout)
        val dialog = builder.create()
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            layout.cancel.setOnClickListener{
               dialog.dismiss()
                findNavController().navigate(R.id.action_dialog_submission_to_formFragment)
            }
            layout.yes.setOnClickListener {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://docs.google.com/forms/d/e/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val service: SendSubmission =
                    retrofit.create(SendSubmission::class.java)
                val dialogConfirmationBuilder = AlertDialog.Builder(activity)
                Log.i(TAG, "yes button clicked")
                arguments.let {
                   val email = it?.getString(EMAIL_ADDRESS)
                   val firstName = it?.getString(FIRST_NAME)
                   val lastName = it?.getString(LAST_NAME)
                   val link = it?.getString(GITHUB_LINK)
                    if (email != null && firstName != null && lastName != null && link != null) {
                        val sendFormData: Call<Void> = service.sendFormData(email, firstName, lastName, link)
                        sendFormData.enqueue(object : Callback<Void>{
                            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                                dialogConfirmationBuilder.setView(
                                    inflater.inflate(
                                        R.layout.submission_successfull,
                                        null
                                    )
                                )
                            }

                            override fun onFailure(call: Call<Void>, t: Throwable) {
                                dialogConfirmationBuilder.setView(
                                    inflater.inflate(
                                        R.layout.submission_not_successfull,
                                        null
                                    )
                                )
                            }
                        })
                    }
                }
                val dialogConfirmation = dialogConfirmationBuilder.create()
                dialogConfirmation.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialogConfirmation.show()
                findNavController().navigate(R.id.action_dialog_submission_to_formFragment)
            }
            dialog
        }?: throw IllegalStateException("Activity cannot be null")

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_dialog, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        show(requireActivity().supportFragmentManager, null)
    }
}