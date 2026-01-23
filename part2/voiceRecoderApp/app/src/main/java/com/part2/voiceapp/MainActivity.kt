package com.part2.voiceapp

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.part2.voiceapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    companion object {
        private const val REQUEST_CODE_AUDIO_CODE = 101
    }
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
        binding.recordButton.setOnClickListener {
            when {
                ContextCompat.checkSelfPermission( // 권한이 허용되어 있는지 확인
                    this,
                    Manifest.permission.RECORD_AUDIO
                ) == PackageManager.PERMISSION_GRANTED -> {
                    // 권한이 있으니 실제로 녹음 시작하면 됨
                }
                ActivityCompat.shouldShowRequestPermissionRationale( // 권한이 거부된 적이 있는지 확인
                    this, Manifest.permission.RECORD_AUDIO) -> {
                    // 권한이 거부된적이 있으므로 알림차을 떠서 권한이 필요한 이유 설명
                    showPermissionRatioRationalDialog()
                }
                else -> { // 권한 요청을 처음하는 경우
                    // 권한 요청
                    ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.RECORD_AUDIO),
                        REQUEST_CODE_AUDIO_CODE)
                }
            }
        }
    }
    private fun showPermissionRatioRationalDialog() { // 권한을 받아야하는 이유를 설명하는 다이얼로그
        AlertDialog.Builder(this)
            .setMessage("녹음 권한을 켜주셔야지 앱을 정상적으로 사용할 수 있습니다.")
            .setPositiveButton("권한 허용하기"){
                    _,_ ->
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.RECORD_AUDIO),
                        REQUEST_CODE_AUDIO_CODE
                    )
                }.setNegativeButton("취소") {
                    dialogInterface, _ -> dialogInterface.cancel()
            }.show()

    }

    private fun showPermissionDisallowDialog() { // 권한이 다시 묻지 않음으로 설정된 경우
        AlertDialog.Builder(this)
            .setMessage("녹음 권한이 켜주셔야지 앱을 정상적으로 사용할 수 있습니다 앱 설정 화면으로 진입하셔서 권한을 켜주세요.")
            .setPositiveButton("권한 변경하러 가기"){
                    _,_ ->
                    // todo 앱 설정 화면으로 이동
                navigateToAppSetting()
            }.setNegativeButton("취소") {
                    dialogInterface, _ -> dialogInterface.cancel()
            }.show()

    }

    private fun navigateToAppSetting() {
        // todo 앱 설정 화면으로 이동하는 기능 구현
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply{
            data = Uri.fromParts("package", packageName, null)
        }
        startActivity(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        val audioRecordPermissionGranted = requestCode == REQUEST_CODE_AUDIO_CODE
                && grantResults.firstOrNull() == PackageManager.PERMISSION_GRANTED

        if(audioRecordPermissionGranted) {
            // todo 녹음 작업을 시작함
        } else {
            if(ActivityCompat.shouldShowRequestPermissionRationale( // 권한이 거부된 적이 있는지 확인
                this, Manifest.permission.RECORD_AUDIO) ) {
                // 권한이 거부된적이 있으므로 알림차을 떠서 권한이 필요한 이유 설명
                showPermissionRatioRationalDialog()
            } else {
                showPermissionDisallowDialog()
            }
        }

    }

}