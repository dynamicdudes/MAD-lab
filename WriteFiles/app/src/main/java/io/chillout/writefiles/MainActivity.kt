package io.chillout.writefiles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import android.Manifest
import android.content.pm.PackageManager
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception

class MainActivity : AppCompatActivity() {
private var permissionGranted = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // TO display Dialog for asking permission
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE),
                20)
        }
        save_btn.setOnClickListener {
                val filename = file_name_edittext.text.toString()
                val fileContent = file_content_edittext.text.toString()
                writeFilesToStorage(filename,fileContent)
        }
    }

    private fun writeFilesToStorage(fileName:String,fileContent:String) {
        val state = Environment.getExternalStorageState()
        if(Environment.MEDIA_MOUNTED != state){
            Log.wtf("WTF","Storage not mounted ?? What a puck")
            return
        }
        val path = "$fileName.txt"
        val file = File(getExternalFilesDir(null),path)
        try {
            file.createNewFile()
            var fileOutputStream = FileOutputStream(file,true)
            fileOutputStream.write(fileContent.toByteArray())
            fileOutputStream.flush()
            fileOutputStream.close()
            Toast.makeText(applicationContext,"stored in ${file.absolutePath}",Toast.LENGTH_SHORT).show()
        }catch (e:Exception){
            e.printStackTrace()
            Log.e("FILE_ERROR",e.message,e)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            20 -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // permission was granted!
                    permissionGranted = true
                } else {
                    // permission denied, boo!
                    Toast.makeText(applicationContext,"GO to Settings and Grant Permissions",Toast.LENGTH_SHORT).show()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}
