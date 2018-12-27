package me.maiz.ittrainning.simplecrawlerboot.common;

import java.io.Serializable;
import java.util.List;

public class PageResult<T> extends Result implements Serializable {

    private  long totalPages;

    private long totalCount;

    private List<T> content;

    public PageResult() {
    }
    public PageResult(Result result) {
        super(result.isSuccess(),result.getCode(),result.getMessage());
    }


    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> data) {
        this.content = data;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "totalPages=" + totalPages +
                ", totalCount=" + totalCount +
                ", content=" + content +
                "} " + super.toString();
    }
}
