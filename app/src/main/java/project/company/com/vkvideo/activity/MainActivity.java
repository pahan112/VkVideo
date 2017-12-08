package project.company.com.vkvideo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

import project.company.com.vkvideo.R;
import project.company.com.vkvideo.fragment.LoginFragment;
import project.company.com.vkvideo.fragment.VideoFragment;

public class MainActivity extends AppCompatActivity implements VideoFragment.LogOut{
    private boolean isResumed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, new VideoFragment()).commit();
        }
        VKSdk.wakeUpSession(this, new VKCallback<VKSdk.LoginState>() {
            @Override
            public void onResult(VKSdk.LoginState res) {
                if (isResumed) {
                    switch (res) {
                        case LoggedOut:
                            showLogin();
                            break;
                        case LoggedIn:
                            showLogout();
                            break;
                        case Pending:
                            break;
                        case Unknown:
                            break;
                    }
                }

            }

            @Override
            public void onError(VKError error) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                startActivity(new Intent(getBaseContext(), MainActivity.class));
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

    private void showLogin() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_fragment, new LoginFragment())
                .commit();
    }

    private void showLogout() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_fragment, new VideoFragment())
                .commit();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        isResumed = true;
        if (VKSdk.isLoggedIn()) {
            showLogout();
        } else {
            showLogin();
        }
    }

    @Override
    protected void onPause() {
        isResumed = false;
        super.onPause();
    }

    @Override
    public void clickLogOut() {
        VKSdk.logout();
        if (!VKSdk.isLoggedIn()) {
            showLogin();
        }
    }
}
