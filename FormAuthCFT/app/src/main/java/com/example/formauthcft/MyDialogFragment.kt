package com.example.formauthcft

import android.os.Bundle
import android.view.LayoutInflater
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.formauthcft.databinding.MyDialogFragmentBinding


class MyDialogFragment : DialogFragment() {
    private lateinit var binding: MyDialogFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MyDialogFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (getString(R.string.hello) + "," + " " + requireActivity().intent.extras?.get("User").toString()).also { binding.tvResultGreeting.text = it }

    }
}