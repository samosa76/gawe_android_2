package gawe.imb.karya.model.objects.others;

/**
 * Created by korneliussendy on 1/30/18.
 */

public class UploadResponse {

    private int requestCode = 0;
    private long total = 0L;
    private long progress = 0L;
    private boolean finish = false;
    private String downloadUrl = "";

    public UploadResponse(int requestCode) {
        this.requestCode = requestCode;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getProgress() {
        return progress;
    }

    public void setProgress(long progress) {
        this.progress = progress;
    }

    public UploadResponse updateProgress(long progress, long total) {
        this.progress = progress;
        this.total = total;
        return this;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
