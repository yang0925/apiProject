package com.example.apiproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.apiproject.utils.Constants.TAG
import com.example.apiproject.utils.SEARCH_TYPE
import com.example.apiproject.utils.onMyTextChanged
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_button_search.*

class MainActivity : AppCompatActivity() {

    private var currentSearchType: SEARCH_TYPE = SEARCH_TYPE.PHOTO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "MainActivity - onCreate() called")

        search_term_radio_group.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.photo_search_radio_button -> {
                    Log.d(TAG, "사진 검색 버튼 클릭")
                    search_term_text_layout.hint = "사진 검색"
                    search_term_text_layout.startIconDrawable = resources.getDrawable(R.drawable.ic_baseline_photo_library_24, resources.newTheme())
                    this.currentSearchType = SEARCH_TYPE.PHOTO

                }

                R.id.user_search_radio_button -> {
                    Log.d(TAG, "사용자 검색 버튼 클릭")
                    search_term_text_layout.hint = "사용자 검색"
                    search_term_text_layout.startIconDrawable = resources.getDrawable(R.drawable.ic_baseline_person_24, resources.newTheme())
                    this.currentSearchType = SEARCH_TYPE.USER

                }

            }
            Log.d(TAG, "MainActivity - onCheckedChanged() called / currentSearchType : $currentSearchType")
        }

        //텍스트가 변경되었을 때
        search_term_edit_text.onMyTextChanged {
            //입력된 글자가 0보다 크다면
            if(it.toString().count() > 0) {
                //검색 버튼을 보여준다.
                frame_search_button.visibility = View.VISIBLE
                search_term_text_layout.helperText = " "
                //스크롤뷰 올린다.
                main_scrollview.scrollTo(0,200)
            } else {
                frame_search_button.visibility = View.INVISIBLE
            }

            if(it.toString().count() == 12) {
                Log.d(TAG, "MainActivity - 에러 띄우기")
                Toast.makeText(this,"검색어는 12자까지만 입력이 가능합니다.", Toast.LENGTH_SHORT).show()
            }
        }

        search_button.setOnClickListener {
            Log.d(TAG,"MainActivity - 검색 버튼 클릭")

            this.handleSearchButtonUi()
        }
    }

    @Suppress("DEPRECATION")
    private fun handleSearchButtonUi() {
        progress_button.visibility = View.VISIBLE

        search_button.text = " "

        Handler().postDelayed({
            progress_button.visibility = View.INVISIBLE
            search_button.text = "검색"
        },1500)
    }
}