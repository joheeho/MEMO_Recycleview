package com.example.memo_recycleview

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.memo_recycleview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var getResultText: ActivityResultLauncher<Intent>
    private lateinit var adapter: DataAdapter

    private val dataList: ArrayList<Data> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getResultText =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val mString = result.data?.getStringExtra("data") ?: ""
                    Log.d(TAG, "onCreate: $mString")

                    dataList.apply {
                        add(Data("$mString"))
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        binding.btnAdd.setOnClickListener {
            val mintent = Intent(this@MainActivity, MemoActivity::class.java)
            getResultText.launch(mintent)
        }
        binding.rvData.layoutManager = LinearLayoutManager(this) // 레이아웃 매니저 연결
        adapter = DataAdapter(dataList)
        binding.rvData.adapter = adapter



    }
}