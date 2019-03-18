package cn.itcast.travel.dao;

import cn.itcast.travel.domain.RouteImg;

import java.util.List;

public interface RouteImageDao {
    /**
     * 根据doute的id查询图片
     * @param rid
     * @return
     */
    public List<RouteImg> findByRid(int rid);
}
