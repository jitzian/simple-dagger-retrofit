package examples.edwin.android.mac.com.org.mysimpledagger;

import dagger.Component;

/**
 * Created by User on 11/23/2016.
 * Components: Those classes that are going to create a bridge (interface between the one that create and uses the instances)
 *
 */

@Component(modules = {SharedModule.class, NetModule.class})
public interface MainComponent {
    //What Modules do I want to link
    //Where do I want to inject the module
    void inject(MainActivity mainActivity);
}
