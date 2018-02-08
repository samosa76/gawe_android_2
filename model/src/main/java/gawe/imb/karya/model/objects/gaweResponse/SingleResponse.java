package gawe.imb.karya.model.objects.gaweResponse;

import com.google.gson.annotations.SerializedName;

/**
 * Created by korneliussendy on 2/8/18.
 *
 */

public class SingleResponse<T> {

    @SerializedName("status")
    private String status;
    @SerializedName("status_code")
    private int statusCode;
    @SerializedName("result")
    private T result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
