package tap.tap

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_mesaj.*

class Mesaj : AppCompatActivity() {

    var durum=true;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mesaj)
        buttons()

    }

    private fun buttons() {
        Send_button.setOnClickListener {
            if (Mesaj.text.isNullOrEmpty()){
                Toast.makeText(this,"Message box cannot be empty",Toast.LENGTH_LONG).show()
            }else if(durum){
                val mesaj =Mesaj.text.toString()
                val yazacak=MorseCode().alphaToMorse(mesaj)
                degisecekmesaj.setText(yazacak)
                visible()
                durum=false
            }
        }
    translate1.setOnClickListener { cevir1.setText(MorseCode().morseToAlpha(cevir1.text.toString())) }
    translate2.setOnClickListener { cevir2.setText(MorseCode().morseToAlpha(cevir2.text.toString())) }
    translate3.setOnClickListener { degisecekmesaj.setText(MorseCode().morseToAlpha(degisecekmesaj.text.toString())) }
        Profil2.setOnClickListener { val intt= Intent(this,profil::class.java)
            startActivity(intt) }
        Geri.setOnClickListener { onBackPressed() }

    }

    private fun visible() {
        textView34.visibility=View.VISIBLE
        degisecekmesaj.visibility=View.VISIBLE
        translate3.visibility=View.VISIBLE
        circularImageView6.visibility=View.VISIBLE
    }

}
