package instastellar.com.instastellar.feature.api.v1
import instastellar.com.instastellar.feature.models.Models
import io.reactivex.Observable
import retrofit2.http.GET

interface Instagram {

    @GET("/accounts/login/ajax/")
    fun loginToInstagram():
            Observable<Models.HealthCheck>

}