package examples.edwin.android.mac.com.org.mysimpledagger;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import dagger.Module;
import dagger.Provides;

/**
 * Created by User on 11/23/2016.
 */

@Module
public class SharedModule {
    public static final String TAG = SharedModule.class.getSimpleName();
    private Context context;

    public SharedModule(Context context) {
        this.context = context;
    }

    //Every method that create an instance needs the @Provides
    @Provides
    public SharedPreferences provideSharedPreferences(){
        Log.d(TAG, "provideSharedPreferences");
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

}
