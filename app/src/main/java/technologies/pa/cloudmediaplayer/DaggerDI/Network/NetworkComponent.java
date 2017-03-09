package technologies.pa.cloudmediaplayer.DaggerDI.Network;

import android.app.Activity;
import android.app.Service;

import javax.inject.Singleton;

import dagger.Component;
import technologies.pa.cloudmediaplayer.Function.Home.NaviagationActivity;

/**
 * Created by Dev02 on 3/1/2017.
 */
@Singleton
@Component(modules = {NetworkModule.class})
public interface NetworkComponent {

    void inject(Activity activity);
}