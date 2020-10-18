package tap.tap

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_anamenu.*

class anamenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        barlarisakla()
        setContentView(R.layout.activity_anamenu)
        buttonayarlari()
    }

    private fun buttonayarlari() {
        Profil.setOnClickListener { val intt=Intent(this,profil::class.java)
            startActivity(intt) }
        Translate_button.setOnClickListener { Mesaj_anamenu.setText(MorseCode().morseToAlpha(Mesaj_anamenu.text.toString())) }
        Replybutton.setOnClickListener { val intt=Intent(this,Mesaj::class.java)
        startActivity(intt)}
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if(hasFocus){
            barlarisakla()
        }
    }
    private fun barlarisakla() {
        val decorvw = this.window.decorView
        decorvw.systemUiVisibility= View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View
            .SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    }
}
