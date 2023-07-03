package com.example.memo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.memo.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.storeButton.setOnClickListener {
            val data = viewBinding.editText.text.toString()
            val intent = Intent()
            intent.putExtra("data",data)
            setResult(RESULT_OK,intent)
            finish()
        }
    }
}