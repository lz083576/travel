package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.Route;

import java.util.List;

public interface FavoriteDao {
    /**
     * 根据uid和rid查看收藏
     *
     * @param rid
     * @param uid
     * @return
     */
    public Favorite findByRidAndUid(int rid, int uid);

    /**
     * 根据rid查询收藏次数
     * @param rid
     * @return
     */
    public int findCount(int rid);

    /**
     * 添加收藏
     * @param i
     * @param uid
     */
    public void add(int i, int uid);

    /**
     * 通过uid查询出favorite表返回一个属性
     * @param uid
     * @return
     */
    public List<Favorite> findMyFavoriteByUid(int uid);
}
