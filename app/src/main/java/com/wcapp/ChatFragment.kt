package com.wcapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wcapp.R
import com.wcapp.Therapist

class ChatFragment : Fragment() {

    companion object {
        private const val ARG_THERAPIST = "therapist"

        fun newInstance(therapist: Therapist) = ChatFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_THERAPIST, therapist) // Assuming Therapist implements Parcelable
            }
        }

        private fun putParcelable(argTherapist: String, therapist: Therapist) {
            TODO("Not yet implemented")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }
}
