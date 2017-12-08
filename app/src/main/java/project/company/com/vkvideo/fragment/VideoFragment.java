package project.company.com.vkvideo.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiVideo;
import com.vk.sdk.api.model.VKList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import project.company.com.vkvideo.R;
import project.company.com.vkvideo.activity.WebActivity;
import project.company.com.vkvideo.adapter.VideoAdapter;
import project.company.com.vkvideo.model.VideoItem;


public class VideoFragment extends Fragment implements VideoAdapter.PlayVideo {
    @BindView(R.id.rv_video)
    RecyclerView mRecyclerView;
    private VideoAdapter videoAdapter;
    private List<VideoItem> mVideoItems = new ArrayList<>();
    LogOut logOut;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_video, container, false);
        ButterKnife.bind(this, rootView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        videoAdapter = new VideoAdapter(mVideoItems, this);
        mRecyclerView.setAdapter(videoAdapter);

        final VKRequest video = VKApi.video().get(VKParameters.from());
        video.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                VKList<VKApiVideo> list = (VKList) response.parsedModel;
                for (VKApiVideo video1 : list) {
                    mVideoItems.add(new VideoItem(video1.title, video1.duration, video1.photo_320, video1.player));
                }
                videoAdapter.notifyDataSetChanged();
            }
        });
        return rootView;
    }


    @Override
    public void clickItem(String url) {
        Intent intent = new Intent(getContext(), WebActivity.class);
        intent.putExtra("EXTRA_SESSION_ID", url);
        startActivity(intent);
    }

    @OnClick(R.id.btn_logOut)
    void clickLogOut() {
        logOut.clickLogOut();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        logOut = (LogOut) context;
    }

    public interface LogOut {
        void clickLogOut();
    }
}
