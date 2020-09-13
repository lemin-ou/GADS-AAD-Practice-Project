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
import com.wellcherve.android.aadpracticeproject.models.EMAIL_ADDRESS
import com.wellcherve.android.aadpracticeproject.models.FIRST_NAME
import com.wellcherve.android.aadpracticeproject.models.GITHUB_LINK
import com.wellcherve.android.aadpracticeproject.models.LAST_NAME
import kotlinx.android.synthetic.main.dialog_lyout.view.*
import kotlinx.android.synthetic.main.fragment_dialog.*
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

    private fun goToFormFragment() {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.fragment_container, FormFragment())
        transaction?.addToBackStack(null)
        transaction?.commit()
        activity?.supportFragmentManager?.executePendingTransactions()
    }

    private fun dialog(): Dialog {
        val inflater = requireActivity().layoutInflater
        val builder = AlertDialog.Builder(activity)
        val layout = inflater.inflate(R.layout.dialog_lyout, null)
        builder.setView(layout)
        val dialog = builder.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        var areDismissedByButton = false
        // when the user just dismiss this dialog without pressing one of the buttons
        dialog.setOnDismissListener{
            if (areDismissedByButton)
                goToFormFragment()
        }
        layout.cancel.setOnClickListener {
            areDismissedByButton = true
            dialog.dismiss()

        }
        layout.yes.setOnClickListener {
            dialog.dismiss()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://docs.google.com/form/d/e/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service: SendSubmission =
                retrofit.create(SendSubmission::class.java)
            val dialogConfirmationBuilder = AlertDialog.Builder(activity)
            arguments.let {
                val email = it?.getString(EMAIL_ADDRESS)
                val firstName = it?.getString(FIRST_NAME)
                val lastName = it?.getString(LAST_NAME)
                val link = it?.getString(GITHUB_LINK)
                if (email != null && firstName != null && lastName != null && link != null) {
                    val sendFormData: Call<Void> =
                        service.sendFormData(email, firstName, lastName, link)
                    sendFormData.enqueue(object : Callback<Void> {
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
            dialogConfirmation.setOnDismissListener {
                  goToFormFragment()
            }
        }

      return  dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.removeAllViews();
        return inflater.inflate(R.layout.fragment_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
          dialog().show()
       }

}