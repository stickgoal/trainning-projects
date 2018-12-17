package maiz.me.toyapplication;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;

/**
 * 在AndroidManifest中的<Application>标签关联，以实现在应用启动时初始化一些资源
 */
public class ToyApplication extends Application {

    public static final String T = "ToyApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(T, "应用启动");
        Fragmentation.builder().debug(true)
                .stackViewMode(Fragmentation.BUBBLE)
                .handleException(e -> Log.e(T, "Fragmentation使用出现异常", e))
                .install();
    }
}
