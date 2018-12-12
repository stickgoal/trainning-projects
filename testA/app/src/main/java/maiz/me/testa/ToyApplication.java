package maiz.me.testa;

import android.app.Application;
import android.util.Log;

import com.blankj.utilcode.utils.ToastUtils;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.joanzapata.iconify.fonts.MaterialCommunityModule;
import com.joanzapata.iconify.fonts.MaterialModule;

public class ToyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("ToyApplication","应用初始化...");
        Iconify.with(new FontAwesomeModule())
                .with(new MaterialModule())
                .with(new MaterialCommunityModule());

    }
}
