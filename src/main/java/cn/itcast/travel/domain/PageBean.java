package cn.itcast.travel.domain;

import java.util.List;

/**
 * 分页对象
 */
public class PageBean<T> {
    private Integer totalCount;//总记录数
    private Integer totalPage;//总页数
    private Integer currentPage;//当前页码
    private Integer pageSize;//每页显示条数
    private List<T> list;//每页显示的数据集合

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
