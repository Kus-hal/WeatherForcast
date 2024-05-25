package com.example.liveintutions.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.liveintutions.databinding.ActivityLogInBinding
import com.example.liveintutions.model.MainActivity
import com.google.firebase.auth.FirebaseAuth

class LogIn : AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()


        val name = intent.getStringExtra("name").toString()

        binding.loginButton.setOnClickListener {
            val email = binding.mailId.text.toString()
            val password = binding.password.text.toString()


            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if(task.isSuccessful){
                            Toast.makeText(this,"Login Successful", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        else{
                            Toast.makeText(this,"Login UnSuccessful", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
            else{
                Toast.makeText(this,"Enter Mail-Id and Password", Toast.LENGTH_SHORT).show()
            }

        }
        binding.forgetPass.setOnClickListener{
            val intent = Intent(this, SignUp::class.java)
            intent.putExtra("name",name)
            startActivity(intent)
        }


    }
}