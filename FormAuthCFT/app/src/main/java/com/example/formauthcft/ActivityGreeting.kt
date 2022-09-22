package com.example.formauthcft

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.formauthcft.databinding.ActivityGreetingBinding

class ActivityGreeting : AppCompatActivity() {
    private lateinit var binding: ActivityGreetingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGreetingBinding.inflate(layoutInflater)
        setContentView(binding.root)

            binding.button2.setOnClickListener {
                MyDialogFragment().show(supportFragmentManager, "dialogfragment")            }

    }
}