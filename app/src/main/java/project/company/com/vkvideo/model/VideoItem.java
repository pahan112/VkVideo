package project.company.com.vkvideo.model;


public class VideoItem {
    private String title;
    private int durating;
    private String photo;
    private String player;

    public VideoItem(String title, int durating, String photo, String player) {
        this.title = title;
        this.durating = durating;
        this.photo = photo;
        this.player = player;
    }


    public int getDurating() {
        return durating;
    }

    public String getPhoto() {
        return photo;
    }

    public String getPlayer() {
        return player;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "VideoItem{" +
                "title='" + title + '\'' +
                ", durating=" + durating +
                ", photo='" + photo + '\'' +
                ", player='" + player + '\'' +
                '}';
    }
}
