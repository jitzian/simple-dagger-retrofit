package examples.edwin.android.mac.com.org.mysimpledagger;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by User on 11/23/2016.
 *
 * Module: Classes that are going to create the instances
 * Every method that create an instance needs the @Provides
 *
 */

@Module
class NetModule {
    private final String baseUrl;

    NetModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    Retrofit provideRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
}
