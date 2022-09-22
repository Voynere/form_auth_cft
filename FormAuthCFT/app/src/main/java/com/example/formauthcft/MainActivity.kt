package com.example.formauthcft

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.formauthcft.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var model: DataMVVM

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this)[DataMVVM::class.java]
        if (model.date.isNotEmpty()) {
            binding.textView.text = model.date
        }

        binding.textView.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(this, { _, myyear, mmonth, mdayOfMonth ->
                binding.textView.text = "$mdayOfMonth/$mmonth/$myyear"
                model.date = "$mdayOfMonth/$mmonth/$myyear"

            }, year, month, day)
            datePickerDialog.show()
        }

        binding.button.setOnClickListener {
            val intent = Intent(this, ActivityGreeting::class.java)
            val user: String =
                binding.edName.text.toString() + " " + binding.edLastName.text.toString()
            intent.putExtra("User", user)
            startActivity(intent)
        }

        binding.edName.addTextChangedListener {
            check()
        }
        binding.edLastName.addTextChangedListener {
            check()
        }
        binding.edPass.addTextChangedListener {
            check()
        }
        binding.edPassRepeat.addTextChangedListener {
            check()
        }
        check()
    }

    private fun check() {
        val name = binding.edName.text.toString()
        val lastName = binding.edLastName.text.toString()
        val pass = binding.edPass.text.toString()
        val passRepeat = binding.edPassRepeat.text.toString()

        binding.button.isEnabled = name.length > 2 && name.none { it in "0123456789" }
                && lastName.length > 2 && lastName.none { it in "0123456789" }
                && pass == passRepeat
                && model.date.isNotEmpty()
                && pass.any { it in "0123456789" } && pass.any { it in "abcdefghijklmnopqrstuvwxyz" }
                && pass.any { it in "abcdefghijklmnopqrstuvwxyz".uppercase() }
                && pass.any { it !in ("0123456789abcdefghijklmnopqrstuvwxyz" + "abcdefghijklmnopqrstuvwxyz".uppercase()) }
    }
}
