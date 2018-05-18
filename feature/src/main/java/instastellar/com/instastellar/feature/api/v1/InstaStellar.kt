package instastellar.com.instastellar.feature.api.v1
import instastellar.com.instastellar.feature.models.Models
import io.reactivex.Observable
import retrofit2.http.GET

interface InstaStellar {

    @GET("/api/v1/health")
    fun healthCheck():
            Observable<Models.HealthCheck>


    @GET("/api/v1/login")
    fun login():
            Observable<Models.HealthCheck>


    @GET("/api/v1/createuser")
    fun createuser():
            Observable<Models.HealthCheck>


    @GET("/api/v1/signup")
    fun signup():
            Observable<Models.HealthCheck>


    @GET("/api/v1/transaction/<from>/<to>/<amt>")
    fun transaction():
            Observable<Models.HealthCheck>



}