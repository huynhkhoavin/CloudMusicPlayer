package technologies.pa.cloudmediaplayer.DaggerDI.Network;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import technologies.pa.cloudmediaplayer.Object.Album;

/**
 * Created by Dev02 on 3/1/2017.
 */
@Module
public class NetworkModule {
    @Provides
    @Singleton
    Album provideAlbum(){
        return new Album();
    }
}
