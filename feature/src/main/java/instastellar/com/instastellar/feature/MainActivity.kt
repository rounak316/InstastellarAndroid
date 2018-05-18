package instastellar.com.instastellar.feature

import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.system.Os.bind
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import instastellar.com.instastellar.feature.api.v1.NetworkHandler.Retrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_main.*
import android.view.animation.Animation.RELATIVE_TO_SELF
import android.view.animation.ScaleAnimation
import android.view.KeyEvent.KEYCODE_VOLUME_UP
import android.view.KeyEvent.KEYCODE_VOLUME_DOWN
import android.widget.Button
import instastellar.com.instastellar.feature.api.v1.Instagram


class MainActivity : AppCompatActivity() {

     lateinit var tv: TextView
    lateinit var animView: LottieAnimationView


    val retro by lazy {
        Retrofit.getRetroClient(application)
    }


    fun scaleView(v: View, startScale: Float, endScale: Float) {
        val anim = ScaleAnimation(
                1f, 1f, // Start and end values for the X axis scaling
                startScale, endScale, // Start and end values for the Y axis scaling
                RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                RELATIVE_TO_SELF, 0.5f) // Pivot point of Y scaling
        anim.setFillAfter(true) // Needed to keep the result of the animation
        anim.setDuration(300)
        v.startAnimation(anim)
    }


    fun staartShit(){
        animView.playAnimation()

        Handler().postDelayed( Runnable {
            tv.setText("48+5=53")
            scaleView(tv, 0.0f , 1.0f)
        }  , 1000)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_main)
        setSupportActionBar(toolbar)


        Instagram()




    }

  override  fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN || keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            // Do your thing
            staartShit()
            true
        } else {
            super.onKeyDown(keyCode, event)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
