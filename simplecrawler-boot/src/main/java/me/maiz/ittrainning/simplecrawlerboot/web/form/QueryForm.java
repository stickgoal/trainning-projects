package me.maiz.ittrainning.simplecrawlerboot.web.form;


public abstract class QueryForm {

    private int pageIdx;
    private int pageSize=10;

    public int getPageIdx() {
        return pageIdx;
    }

    public void setPageIdx(int pageIdx) {
        this.pageIdx = pageIdx;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
