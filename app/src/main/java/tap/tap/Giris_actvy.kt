package tap.tap

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import kotlinx.android.synthetic.main.giris_actvy.*
import java.util.*
import android.transition.Slide
import android.transition.Fade
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*


class Giris_actvy : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private var verificationInProgress = false
    private var storedVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        barlarisakla()
        setContentView(R.layout.giris_actvy)
        baslat()
        auth=FirebaseAuth.getInstance()
        callbacks=object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(p0: PhoneAuthCredential?) {
                verificationInProgress=false
                updateUI(STATE_VERIFY_SUCCESS)
                SignInwithphonecrendetal()
            }

            override fun onVerificationFailed(e: FirebaseException?) {
                verificationInProgress=false
                if (e is FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                } else if (e is FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded

                }

                // Show a message and update the UI
                // [START_EXCLUDE]
                updateUI(STATE_VERIFY_FAILED)
                // [END_EXCLUDE]
            }

        }
    }

    private fun SignInwithphonecrendetal(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential).addOnCompleteListener {

            if (it.isSuccessful) {
                val user = it.result?.user
                updateUI(STATE_SIGNIN_SUCCESS, user)
            } else {
                if (it.exception is FirebaseAuthInvalidCredentialsException) {
                }
                updateUI(STATE_SIGNIN_FAILED)
            }
        }

    }

    private fun updateUI(UiDurum:Int,user:FirebaseUser?=auth.currentUser,cred:PhoneAuthCredential?) {
        when(UiDurum){
            STATE_INITIALIZED->{}

        }

    }

    private fun baslat() {
        signinkaydir()
        focuslistener()
        signuplistener()
        sayfagecisayarlari()
    }

    private fun signuplistener() {
        Rg_phone_mail.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                Email_radio.id -> {
                    Email_radio.setTextColor(resources.getColor(R.color.m_dark))
                    Phone_radio.setTextColor(resources.getColor(R.color.colorAccent))
                    Email_lay.visibility = View.VISIBLE
                    Phone_lay.visibility = View.GONE
                    emailkaydir()
                }
                Phone_radio.id -> {
                    Email_lay.visibility = View.GONE
                    Phone_lay.visibility = View.VISIBLE
                    Email_radio.setTextColor(resources.getColor(R.color.colorAccent))
                    Phone_radio.setTextColor(resources.getColor(R.color.m_dark))
                    phonekaydir()
                }
            }
        }
    }

    private fun signinkaydir() {
        val param1 = kaydir_5.layoutParams
        param1.width = 2000
        kaydir_5.layoutParams = param1
        val param2 = kaydir_6.layoutParams
        param2.width = 3000
        kaydir_6.layoutParams = param2
        val zamanlayici = Timer("MYtimer")
        zamanlayici.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    var d1 = false
                    var d2 = false
                    val param1a = kaydir_5.layoutParams
                    val param2a = kaydir_6.layoutParams
                    if (param1a.width > 0) {
                        param1a.width = param1a.width - 10
                        kaydir_5.layoutParams = param1a
                    } else {
                        param1a.width = 0
                        kaydir_5.layoutParams = param1a
                        d1 = true
                    }
                    if (param2a.width > 0) {
                        param2a.width = param2a.width - 10
                        kaydir_6.layoutParams = param2a
                    } else {
                        param2a.width = 0
                        kaydir_6.layoutParams = param2a
                        d2 = true
                    }

                    if (d1 && d2) {
                        zamanlayici.cancel()
                    }

                }
            }

        }, 0, 1)
    }

    private fun phonekaydir() {
        val param1 = kaydir_4.layoutParams
        param1.width = 1000
        kaydir_4.layoutParams = param1
        val param2 = kaydir_2.layoutParams
        param2.width = 800
        kaydir_2.layoutParams = param2
        val param3 = kaydir_3.layoutParams
        param3.width = 600
        kaydir_3.layoutParams = param3
        val zamanlayici = Timer("MYtimer")
        zamanlayici.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    var d1 = false
                    var d2 = false
                    var d3 = false
                    val param1a = kaydir_4.layoutParams
                    val param2a = kaydir_2.layoutParams
                    val param3a = kaydir_3.layoutParams
                    if (param1a.width > 0) {
                        param1a.width = param1a.width - 3
                        kaydir_4.layoutParams = param1a
                    } else {
                        param1a.width = 0
                        kaydir_4.layoutParams = param1a
                        d1 = true
                    }
                    if (param2a.width > 0) {
                        param2a.width = param2a.width - 3
                        kaydir_2.layoutParams = param2a
                    } else {
                        param2a.width = 0
                        kaydir_2.layoutParams = param2a
                        d2 = true
                    }
                    if (param3a.width > 0) {
                        param3a.width = param3a.width - 3
                        kaydir_3.layoutParams = param3a
                    } else {
                        param3a.width = 0
                        kaydir_3.layoutParams = param3a
                        d3 = true
                    }
                    if (d1 && d2 && d3) {
                        zamanlayici.cancel()
                    }

                }
            }

        }, 0, 1)

    }

    private fun emailkaydir() {
        val param1 = kaydir_1.layoutParams
        param1.width = 600
        kaydir_1.layoutParams = param1
        val param2 = kaydir_2.layoutParams
        param2.width = 800
        kaydir_2.layoutParams = param2
        val param3 = kaydir_3.layoutParams
        param3.width = 1000
        kaydir_3.layoutParams = param3
        val zamanlayici = Timer("MYtimer")
        zamanlayici.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    var d1 = false
                    var d2 = false
                    var d3 = false
                    val param1a = kaydir_1.layoutParams
                    val param2a = kaydir_2.layoutParams
                    val param3a = kaydir_3.layoutParams
                    if (param1a.width > 0) {
                        param1a.width = param1a.width - 3
                        kaydir_1.layoutParams = param1a
                    } else {
                        param1a.width = 0
                        kaydir_1.layoutParams = param1a
                        d1 = true
                    }
                    if (param2a.width > 0) {
                        param2a.width = param2a.width - 3
                        kaydir_2.layoutParams = param2a
                    } else {
                        param2a.width = 0
                        kaydir_2.layoutParams = param2a
                        d2 = true
                    }
                    if (param3a.width > 0) {
                        param3a.width = param3a.width - 3
                        kaydir_3.layoutParams = param3a
                    } else {
                        param3a.width = 0
                        kaydir_3.layoutParams = param3a
                        d3 = true
                    }
                    if (d1 && d2 && d3) {
                        zamanlayici.cancel()
                    }

                }
            }

        }, 0, 1)

    }

    private fun sayfagecisayarlari() {
        Signin_1.setOnClickListener { Sigin() }
        Signup_1.setOnClickListener {
            Sigup()
            if (Email_radio.isChecked) {
                emailkaydir()
            } else {
                phonekaydir()
            }
        }
        Signin_2.setOnClickListener {
            Sigin()
            signinkaydir()
        }
        Signup_2.setOnClickListener { Sigup() }
        Get_start.setOnClickListener {
            val intt = Intent(this, baslarken::class.java)
            startActivity(intt)
        }
    }

    private fun Sigup() {
        SIGNIN.visibility = View.GONE
        SINGUP.visibility = View.VISIBLE
    }

    private fun Sigin() {
        SINGUP.visibility = View.GONE
        SIGNIN.visibility = View.VISIBLE
    }

    private fun focuslistener() {
        editText.setOnFocusChangeListener { v, hasFocus ->
            barlarisakla()
        }
        editText_.setOnFocusChangeListener { v, hasFocus ->
            barlarisakla()
        }
        object : CountDownTimer(2000, 1000) {
            override fun onFinish() {
                barlarisakla()
                focuslistener()
            }

            override fun onTick(millisUntilFinished: Long) {

            }

        }.start()

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            barlarisakla()
        }
    }

    private fun barlarisakla() {
        val decorvw = this.window.decorView
        decorvw.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View
            .SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    }

    companion object {
        private const val TAG = "PhoneAuthActivity"
        private const val KEY_VERIFY_IN_PROGRESS = "key_verify_in_progress"
        private const val STATE_INITIALIZED = 1
        private const val STATE_VERIFY_FAILED = 3
        private const val STATE_VERIFY_SUCCESS = 4
        private const val STATE_CODE_SENT = 2
        private const val STATE_SIGNIN_FAILED = 5
        private const val STATE_SIGNIN_SUCCESS = 6
    }
}
