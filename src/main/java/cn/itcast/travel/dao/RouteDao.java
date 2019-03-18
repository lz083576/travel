package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

public interface RouteDao {
    /**
     * 根据cid查询总记录数
     */
    public int findTotalCount(Integer cid, String rname);

    /**
     * 根据cid ，start ，pagesize查询当前页码的数据集合
     */
    public List<Route> findByPage(Integer cid, Integer start, Integer pageSize, String rname);

    public Route findOne(int rid);

    public int findTotalCountFirst(Integer cid);

    public List<Route> findByPageFirst(Integer cid, int start, Integer pageSize);
}
