package com.part2.myapplication

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.part2.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.button1.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .apply { // transaction -> 작업의 단위 beginTransaction() -> 트랜잭션 시작, commit() -> 트랜잭션 완료
                    replace(R.id.FragmentContainer, WebViewFragment()) // FragmentContainer -> activity_main.xml의 fragmentContainerView
                    commit()
                }

        }
        binding.button2.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .apply { // transaction -> 작업의 단위 beginTransaction() -> 트랜잭션 시작, commit() -> 트랜잭션 완료
                    replace(R.id.FragmentContainer, BFragment()) // FragmentContainer -> activity_main.xml의 fragmentContainerView
                    commit()
                }
        }

    }
}