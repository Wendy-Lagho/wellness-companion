package com.wcapp.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.wcapp.Therapist

class TherapistListFragment : Fragment() {

    private var listener: OnTherapistSelectedListener? = null

    interface OnTherapistSelectedListener {
        fun onTherapistSelected(therapist: Therapist)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnTherapistSelectedListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnTherapistSelectedListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                TherapistListScreen(onTherapistClick = { therapist ->
                    listener?.onTherapistSelected(therapist)
                })
            }
        }
    }
}
