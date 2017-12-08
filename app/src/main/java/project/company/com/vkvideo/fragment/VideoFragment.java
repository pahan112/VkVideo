package project.company.com.vkvideo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import project.company.com.vkvideo.R;
import project.company.com.vkvideo.adapter.VideoAdapter;
import project.company.com.vkvideo.model.VideoItem;


public class VideoFragment extends Fragment {
    @BindView(R.id.rv_video)
    RecyclerView mRecyclerView;
    private VideoAdapter videoAdapter;
    private List<VideoItem> mVideoItems = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_video, container, false);
        ButterKnife.bind(this, rootView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        final VKRequest video = VKApi.video().get(VKParameters.from());
        video.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                VKList<VKApiVideo> list = (VKList) response.parsedModel;
                for (VKApiVideo video1:list){
                    mVideoItems.add(new VideoItem(video1.title,video1.duration,video1.photo_320,video1.player));
                }
                Log.d("myLog",mVideoItems.toString());
                videoAdapter =new VideoAdapter(mVideoItems);
                mRecyclerView.setAdapter(videoAdapter);
            }
        });
        return rootView;
    }

   
}
