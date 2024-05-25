package com.example.liveintutions.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.liveintutions.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.signUpButton.setOnClickListener {
            val email = binding.mailID.text.toString()
            val password = binding.password.text.toString()
            val repassword = binding.rePassword.text.toString()
            val name = binding.name.text.toString()


            if (password == repassword) {
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(
                                    this,
                                    "SuccessFully Registered!!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                val intent = Intent(this, LogIn::class.java)
                                intent.putExtra("name", name)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(this, "SignUp Failed!", Toast.LENGTH_SHORT).show()

                            }
                        }
                } else {
                    Toast.makeText(this, "Enter Email and Password", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(this, "Incorrect Password Match!", Toast.LENGTH_SHORT).show()
            }

        }

    }
}