package me.maiz.ittrainning.simplecrawlerboot.web.form;


public abstract class QueryForm {

    private int index;
    private int pageSize=10;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
