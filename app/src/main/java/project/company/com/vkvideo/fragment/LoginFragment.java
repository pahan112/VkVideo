package project.company.com.vkvideo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;

import butterknife.ButterKnife;
import butterknife.OnClick;
import project.company.com.vkvideo.R;

/**
 * Created by Pahan on 08.12.2017.
 */

public class LoginFragment extends Fragment {
    private String[] strings = new String[]{VKScope.VIDEO};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
    @OnClick(R.id.btn_login)
    void clickLogin(){
        VKSdk.login(getActivity(), strings);
    }
}
