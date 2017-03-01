package technologies.pa.cloudmediaplayer.DaggerDI.Network;

import javax.inject.Singleton;

import dagger.Component;
import technologies.pa.cloudmediaplayer.Function.Home.NaviagationActivity;

/**
 * Created by Dev02 on 3/1/2017.
 */
@Singleton
@Component(modules = {NetworkModule.class})
public interface NetworkComponent {
    void inject(NaviagationActivity naviagationActivity);
}