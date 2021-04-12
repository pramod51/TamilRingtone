package best.tamil.ringtonetamilbest.ui.Latest;

public class LatestModel {
    String title;
    String duration;
    String ringtoneUrl;
    String rating;
    String childKay;
    String downloadCount;
    String size;
    boolean isPlaying,isFavourite;

    public LatestModel(String title, String duration, String ringtoneUrl, String rating, String childKay, String downloadCount, String size, boolean isPlaying, boolean isFavourite) {
        this.title = title;
        this.duration = duration;
        this.ringtoneUrl = ringtoneUrl;
        this.rating = rating;
        this.childKay = childKay;
        this.downloadCount = downloadCount;
        this.size = size;
        this.isPlaying = isPlaying;
        this.isFavourite = isFavourite;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getChildKay() {
        return childKay;
    }

    public void setChildKay(String childKay) {
        this.childKay = childKay;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRingtoneUrl() {
        return ringtoneUrl;
    }

    public void setRingtoneUrl(String ringtoneUrl) {
        this.ringtoneUrl = ringtoneUrl;
    }

    public String getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(String downloadCount) {
        this.downloadCount = downloadCount;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }
}
