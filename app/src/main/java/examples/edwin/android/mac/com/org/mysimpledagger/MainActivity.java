package examples.edwin.android.mac.com.org.mysimpledagger;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.util.Map;

import javax.inject.Inject;

import examples.edwin.android.mac.com.org.mysimpledagger.model.ResultApi;
import retrofit2.Retrofit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String API_URL = "https://randomuser.me/";
    private Button mButton1, mButton2, mButton3;
    private EditText mEditText1, mEditText2, mEditText3, mEditText4, mEditText5, mEditText6;

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");

        DaggerMainComponent.builder()
                .sharedModule(new SharedModule(this))//
                .netModule(new NetModule(API_URL))
                .build()
                .inject(this);

        Log.d(TAG, sharedPreferences.getAll().toString());

        mButton1 = (Button) findViewById(R.id.mButton1);
        mButton2 = (Button) findViewById(R.id.mButton2);
        mButton3 = (Button) findViewById(R.id.mButton3);

        mEditText1 = (EditText) findViewById(R.id.mEditText1);
        mEditText2 = (EditText) findViewById(R.id.mEditText2);
        mEditText3 = (EditText) findViewById(R.id.mEditText3);

        mEditText4 = (EditText) findViewById(R.id.mEditText4);
        mEditText5 = (EditText) findViewById(R.id.mEditText5);
        mEditText6 = (EditText) findViewById(R.id.mEditText6);

        mButton1.setOnClickListener(view -> {
            Log.d(TAG, "Clicked Button");
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("nameKey", mEditText1.getText().toString());
            editor.putString("phoneKey", mEditText2.getText().toString());
            editor.putString("emailKey", mEditText3.getText().toString());
            editor.apply();

        });

        mButton2.setOnClickListener(view -> {
            Log.d(TAG, "Get Preferences");
            Map<String, ?> map = sharedPreferences.getAll();
            mEditText4.setText(map.get("nameKey").toString());
            mEditText5.setText(map.get("phoneKey").toString());
            mEditText6.setText(map.get("emailKey").toString());
        });

        //Retrofit
        mButton3.setOnClickListener(view -> {
            RestService restService = retrofit.create(RestService.class);
//            Observable<ResultApi>resultApiObservable = restService.getRandomUser();
            restService.getRandomUser()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<ResultApi>() {
                        @Override
                        public void call(ResultApi resultApi) {
                            Log.d(TAG, "call::" + resultApi.getInfo().getSeed());
                            mEditText4.setText(resultApi.getInfo().getSeed());
                            mEditText5.setText(String.valueOf(resultApi.getInfo().getPage()));
                            mEditText6.setText(resultApi.getInfo().getVersion());
                        }
                    });
        });

    }
}
