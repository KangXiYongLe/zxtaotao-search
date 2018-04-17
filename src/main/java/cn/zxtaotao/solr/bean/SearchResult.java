package cn.zxtaotao.solr.bean;

import java.util.List;

public class SearchResult {
    private Long total;

    private List<?> list;

    public SearchResult() {
    }

    public SearchResult(Long total, List<?> list) {
        this.total = total;
        this.list = list;
    }

    public Long getTotal() {
        return this.total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getList() {
        return this.list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
