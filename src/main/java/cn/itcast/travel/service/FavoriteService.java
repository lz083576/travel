package cn.itcast.travel.service;

import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.Route;

import java.util.List;

public interface FavoriteService {
    /**
     * 判断是否是收藏
     * @param rid
     * @param uid
     * @return
     */
    public boolean isFavorite(int rid, int uid);

    /**
     * 没有收藏的话添加
     * @param rid
     * @param uid
     */
    public void add(String rid, int uid);

    /**
     * 查询打包一个favorite
     * @param uid
     * @return
     */
    public List<Favorite> searchMyFavorite(int uid);
}
