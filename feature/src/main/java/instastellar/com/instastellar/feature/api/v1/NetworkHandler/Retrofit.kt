package instastellar.com.instastellar.feature.api.v1.NetworkHandler


import android.app.Application
import android.provider.Settings.Global.getString
import instastellar.com.instastellar.feature.R
import instastellar.com.instastellar.feature.api.v1.InstaStellar
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.CertificatePinner





class Retrofit {
    companion object {
        fun getRetroClient(application : Application): InstaStellar {
            val BASE_URL: String =     application.resources.getString(R.string.BASE_URL)



            val retrofit = Retrofit.Builder()
                    .client(SelfSigningClientBuilder.createClient(application))
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                            GsonConverterFactory.create())

                    .baseUrl(BASE_URL)




            .build()

            return retrofit.create(InstaStellar::class.java)
        }
    }

}