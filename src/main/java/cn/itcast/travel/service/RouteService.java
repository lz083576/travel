package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

/**
 * 线路service
 */
public interface RouteService {
    public PageBean<Route> pageQueryFirst(Integer cid, Integer currentPage, Integer pageSize);
    /**
     * 按照类别查询
     * @param cid
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PageBean<Route> pageQuery(Integer cid, Integer currentPage, Integer pageSize, String rname);

    /**
     * 根据id查询
     * @param rid
     * @return
     */
    public Route findOne(String rid);
}
