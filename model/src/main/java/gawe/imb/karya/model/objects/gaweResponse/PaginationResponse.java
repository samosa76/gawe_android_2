package gawe.imb.karya.model.objects.gaweResponse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaginationResponse<L> {

    @SerializedName("limit")
    private int limit;
    @SerializedName("page")
    private int page;
    @SerializedName("status")
    private String status;
    @SerializedName("status_code")
    private int statusCode;
    @SerializedName("total")
    private Long total;

    private List<L> results;

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

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

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<L> getResults() {
        return results;
    }

    public void setResults(List<L> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return
                "PaginationResponse{" +
                        "limit = '" + limit + '\'' +
                        ",page = '" + page + '\'' +
                        ",results = '" + results + '\'' +
                        "}";
    }
}