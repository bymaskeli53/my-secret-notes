package com.example.mysecretnotes


//import android.hardware.biometrics.BiometricPrompt
import android.content.Context
import android.content.Intent
import android.hardware.fingerprint.FingerprintManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import androidx.lifecycle.lifecycleScope
import com.example.mysecretnotes.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

import java.util.concurrent.Executor

//This app will show you how to use fingerprint in your apps

class MainActivity : AppCompatActivity() {
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    private lateinit var fingerprintManager: FingerprintManager

    private lateinit var binding: ActivityMainBinding

    private lateinit var dataStore: DataStore<Preferences>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkFirstRun()


        dataStore = createDataStore(name = "password")


//        if (hasFingerprintHardware()) {
//
//        }

        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this , executor ,
            object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationError(errorCode: Int , errString: CharSequence) {
                    super.onAuthenticationError(errorCode , errString)
                    notifyUser("Authentication error $errString")
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    notifyUser("Authentication Succeeded!!")
                    goToNoteActivity()

                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    notifyUser("Authentication Failed")
                }

            })



        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Fingerprint App")
            .setSubtitle("Authentication is required")
            .setDescription("You can use your fingerprint to authenticate")
            .setNegativeButtonText("Cancel")
            .build()

        biometricPrompt.authenticate(promptInfo)
//        buttonShowWelcome.setOnClickListener {
//            biometricPrompt.authenticate(promptInfo)
//            buttonShowWelcome.visibility = View.INVISIBLE
//        }


    }

    private fun goToNoteActivity() {
        val intent = Intent(this@MainActivity , NoteActivity::class.java)
        startActivity(intent)
        finish()
    }

    // if you have many toasts in your app you can write a function to make it easier.
    private fun notifyUser(message: String) {
        Toast.makeText(this , message , Toast.LENGTH_LONG).show()
    }

    private fun hasFingerprintHardware(): Boolean {
        // Check if the device has a fingerprint sensor
        fingerprintManager = getSystemService(Context.FINGERPRINT_SERVICE) as FingerprintManager
        return fingerprintManager.isHardwareDetected
    }

//    private fun hasFingerprintPermission(): Boolean {
//        // Check if the app has the required permission to use fingerprint
//        return ContextCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) == PackageManager.PERMISSION_GRANTED
//    }


    /**
     * @see https://stackoverflow.com/a/7563895/15805169
     * This function checks if the app started first time.
     */
    private fun checkFirstRun() {

        val isFirstRun =
            getSharedPreferences("PREFERENCE" , MODE_PRIVATE).getBoolean("isFirstRun" , true)
        if (isFirstRun) {
            binding.etPasswordLayout.hint = getString(R.string.set_password)

            binding.saveButton.setOnClickListener {
                lifecycleScope.launch {
                    save("Password" , binding.etPassword.value)
                }

            }
            getSharedPreferences("PREFERENCE" , MODE_PRIVATE)
                .edit()
                .putBoolean("isFirstRun" , false)
                .apply()
        } else {
            binding.saveButton.setOnClickListener {
                lifecycleScope.launch {
                    val value = read("Password")
                    if (value == binding.etPassword.value) {
                        goToNoteActivity()
                    }
                }
            }
        }


    }

    private suspend fun save(key: String , value: String) {
        val dataStoreKey = preferencesKey<String>(key)
        dataStore.edit { password ->
            password[dataStoreKey] = value
        }
    }

    private suspend fun read(key: String): String? {
        val dataStoreKey = preferencesKey<String>(key)
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey]
    }


}