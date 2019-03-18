package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.FavoriteService;

import java.util.List;

public class FavoriteServiceImpl implements FavoriteService{
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();
    @Override
    public boolean isFavorite(int rid, int uid) {
        Favorite favorite = favoriteDao.findByRidAndUid(rid, uid);
        return favorite!=null;//如果对象有值，为true，反为false
    }

    @Override
    public void add(String rid, int uid) {
        favoriteDao.add(Integer.parseInt(rid),uid);
    }

    @Override
    public List<Favorite> searchMyFavorite(int uid) {
        List<Favorite> favorite = favoriteDao.findMyFavoriteByUid(uid);


        return favorite;
    }


}
