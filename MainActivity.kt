package com.example.memo

import android.content.ContentValues.TAG
import android.content.DialogInterface
import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.AutoText
import android.util.Log
import android.widget.Adapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.memo.databinding.ActivityMainBinding
import org.w3c.dom.Text
class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var getText: ActivityResultLauncher<Intent>
    private val dataList = ArrayList<Data>()
    private lateinit var dataAdapter: DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        dataAdapter = DataAdapter(dataList)

        Toast.makeText(this.applicationContext,"onCreate",Toast.LENGTH_SHORT).show()

        viewBinding.btnAdd.setOnClickListener {
            val intent = Intent(this,SecondActivity::class.java)
            getText.launch(intent)
        }

        viewBinding.rvData.adapter = dataAdapter
        viewBinding.rvData.layoutManager = LinearLayoutManager(this)

       /** 오류 발생
        viewBinding.root.findViewById<ImageView>(R.id.iv_trash).setOnClickListener {
            showDeleteDialog()
        }
        **/

        getText = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){   // 문자열 받아오기
                result -> if(result.resultCode== RESULT_OK){
            val mString = result.data?.getStringExtra("data")
            Log.d(TAG,"onCreate: good To go: $mString")
            if (mString != null) {
                dataList.add(Data(R.drawable.trash, mString))
                dataAdapter.notifyDataSetChanged()
                Toast.makeText(this, "메모가 추가되었습니다.", Toast.LENGTH_SHORT).show()
            }
          }
        }
    }
    override fun onResume() { //
        super.onResume()
        Toast.makeText(this,"onResume",Toast.LENGTH_SHORT).show()
    }
    override fun onPause() {   // 정지 상태에 있을 떄
        super.onPause()
        Toast.makeText(this,"onPause",Toast.LENGTH_SHORT).show()
    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(this,"onRestart",Toast.LENGTH_SHORT).show()
    }
    /** 오류
    private fun showDeleteDialog() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("메모 삭제")
        alertDialogBuilder.setMessage("메모를 삭제하시겠습니까?")
        alertDialogBuilder.setPositiveButton("삭제") { dialog, which ->
            // RecyclerView의 목록을 삭제하는 코드
            dataList.clear()
            dataAdapter.notifyDataSetChanged()
            Toast.makeText(this, "메모가 삭제되었습니다.", Toast.LENGTH_SHORT).show()
        }
        alertDialogBuilder.setNegativeButton("취소", null)
        alertDialogBuilder.show()ㅂㄷ
    }
    **/
}