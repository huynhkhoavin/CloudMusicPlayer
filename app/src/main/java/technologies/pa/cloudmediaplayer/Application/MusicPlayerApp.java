package technologies.pa.cloudmediaplayer.Application;


import android.app.Application;

import javax.inject.Inject;

import technologies.pa.cloudmediaplayer.DaggerDI.Network.DaggerNetworkComponent;
import technologies.pa.cloudmediaplayer.DaggerDI.Network.NetworkComponent;
import technologies.pa.cloudmediaplayer.DaggerDI.Network.NetworkModule;

/**
 * Created by Dev02 on 3/1/2017.
 */

public class MusicPlayerApp extends Application {
    @Inject
    NetworkComponent networkComponent;
    @Override
    public void onCreate() {
        super.onCreate();

        networkComponent = DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule()).build();
    }
    public NetworkComponent getNetworkComponent(){
        return networkComponent;
    }

}
