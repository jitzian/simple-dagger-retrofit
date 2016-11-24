package examples.edwin.android.mac.com.org.mysimpledagger;

import examples.edwin.android.mac.com.org.mysimpledagger.model.ResultApi;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by User on 11/23/2016.
 */

public interface RestService {
    @GET("/api")
    Observable<ResultApi>getRandomUser();
}
