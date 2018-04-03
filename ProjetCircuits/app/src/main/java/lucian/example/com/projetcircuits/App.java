package lucian.example.com.projetcircuits;

import android.app.Application;
import android.content.Context;

/**
 * Created by lucian on 2018-04-03.
 */

public class App extends Application {
    private static Context appContext;
    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }
    public static Context getAppContext() {
        return appContext;
    }
}
