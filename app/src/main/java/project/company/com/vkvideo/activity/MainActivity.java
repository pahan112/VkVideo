package project.company.com.vkvideo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

import butterknife.OnClick;
import project.company.com.vkvideo.R;

public class MainActivity extends AppCompatActivity {
    private String[] strings = new String[]{VKScope.VIDEO};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VKSdk.login(this, strings);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                Log.d("myLog", "good");
            }

            @Override
            public void onError(VKError error) {
                Log.d("myLog", "error");
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    @OnClick(R.id.btn_logOut)
    void clickLogout(){
        VKSdk.logout();
        finish();
    }
}
