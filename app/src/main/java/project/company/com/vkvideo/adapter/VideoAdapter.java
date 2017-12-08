package project.company.com.vkvideo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import project.company.com.vkvideo.R;
import project.company.com.vkvideo.model.VideoItem;


public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {
    private List<VideoItem> videoItems = new ArrayList<>();
    private PlayVideo playVideo;

    public VideoAdapter(List<VideoItem> videoItems ,PlayVideo playVideo) {
        this.videoItems = videoItems;
        this.playVideo = playVideo;
    }

    @Override
    public VideoAdapter.VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VideoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false));
    }

    @Override
    public void onBindViewHolder(VideoAdapter.VideoViewHolder holder, int position) {
        holder.bind(videoItems.get(position));
    }

    @Override
    public int getItemCount() {
        return videoItems.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title_video)
        TextView title;
        @BindView(R.id.duration_video)
        TextView duration;
        @BindView(R.id.image_video)
        ImageView image;

        public VideoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final VideoItem videoItem) {
            title.setText(videoItem.getTitle());
            int minutes = (videoItem.getDurating() % 3600) / 60;
            int seconds = videoItem.getDurating() % 60;
            duration.setText(minutes  + ":" +seconds);
            Glide
                    .with(itemView.getContext())
                    .load(videoItem.getPhoto())
                    .into(image);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    playVideo.clickItem(videoItem.getPlayer());
                }
            });
        }
    }
    public interface PlayVideo{
        void clickItem(String url);
    }
}
