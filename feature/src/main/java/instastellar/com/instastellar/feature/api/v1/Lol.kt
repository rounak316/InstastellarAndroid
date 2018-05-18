

import android.annotation .SuppressLint
import android.content.Context
import instastellar.com.instastellar.feature.R

import java.io.IOException
import java.io.InputStream
import java.security.GeneralSecurityException
import java.security.KeyManagementException
import java.security.KeyStore
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.cert.Certificate
import java.security.cert.CertificateException
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate

import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSession
import javax.net.ssl.TrustManager
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

import okhttp3.OkHttpClient

object SelfSigningClientBuilder {

    fun createClient(context: Context): OkHttpClient? {




        var client: OkHttpClient? = null

        var cf: CertificateFactory? = null
        var cert: InputStream? = null
        var ca: Certificate? = null
        var sslContext: SSLContext? = null
        try {
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                }

                override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                    return arrayOf()
                }
            })
            cf = CertificateFactory.getInstance("X.509")
            cert = context.getResources().openRawResource(R.raw.crt) // Place your 'my_cert.crt' file in `res/raw`

            ca = cf!!.generateCertificate(cert)
            cert!!.close()

            val keyStoreType = KeyStore.getDefaultType()
            val keyStore = KeyStore.getInstance(keyStoreType)
            keyStore.load(null, null)
            keyStore.setCertificateEntry("ca", ca)

            val tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm()
            val tmf = TrustManagerFactory.getInstance(tmfAlgorithm)
            tmf.init(keyStore)

            sslContext = SSLContext.getInstance("TLS")
            sslContext!!.init(null, tmf.getTrustManagers(), null)

            client = OkHttpClient.Builder()
                    .hostnameVerifier(HostnameVerifier { hostname, session -> true })
                    .sslSocketFactory(sslContext!!.getSocketFactory() , trustAllCerts[0] as X509TrustManager?)
                    .build()

        } catch (e: KeyStoreException) {
            e.printStackTrace()
        } catch (e: CertificateException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: KeyManagementException) {
            e.printStackTrace()
        }

        return client
    }

}