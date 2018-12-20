package maiz.me.toyapplication.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import maiz.me.toyapplication.R;
import maiz.me.toyapplication.common.SwitchToFragementEvent;
import maiz.me.toyapplication.integration.RetrofitHelper;
import maiz.me.toyapplication.integration.api.ConfigService;
import maiz.me.toyapplication.integration.api.dto.Result;
import me.yokeyword.eventbusactivityscope.EventBusActivityScope;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrawlConfigureFragment extends FragmentBase {

    private static final String T = "RecylerViewDemoActivity";

    public static CrawlConfigureFragment newInstance() {
        Bundle args = new Bundle();
        CrawlConfigureFragment fragment = new CrawlConfigureFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crawl_config,container,false);
/*

        AppCompatButton addButton = v.findViewById(R.id.addConfig);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //获取到数据并提交到后台
                EditText configNameEdit = v.findViewById(R.id.configNameEdit);
                String configName = configNameEdit.getText().toString();
                EditText titleSelectorNameEdit = v.findViewById(R.id.titleSelectorEdit);
                String titleSelector = titleSelectorNameEdit.getText().toString();
                EditText seedsUrlEdit = v.findViewById(R.id.seedsUrlEdit);
                String seedsUrl = seedsUrlEdit.getText().toString();


                ConfigService configService = new RetrofitHelper().getService(ConfigService.class);
                Call<Result> call = configService.addConfig(1,configName,titleSelector,seedsUrl);

                call.enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        Log.w(T,response.raw().toString());
                        if(response.isSuccessful()){
                            Toast.makeText(getActivity(),"添加配置成功",Toast.LENGTH_SHORT).show();
                            EventBusActivityScope.getDefault(_mActivity).post(new SwitchToFragementEvent(1));
                            hideSoftInput();
                        }else{
                            Toast.makeText(getActivity(),"添加配置失败["+response.errorBody()+"]",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                        Log.w(T, "onFailure: ",t );
                    }
                });

            }
        });
*/

        return v;

    }
}
