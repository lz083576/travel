package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.temporal.Temporal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FavoriteDaoImpl implements FavoriteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Favorite findByRidAndUid(int rid, int uid) {
        Favorite favorite = null;
        try {
            String sql = "select * from tab_favorite where rid = ? and uid = ?";
            favorite = template.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), rid, uid);
        } catch (DataAccessException e) {

        }
        return favorite;
    }

    @Override
    public int findCount(int rid) {
        String sql = "select count(*) from tab_favorite where rid=?";
        return template.queryForObject(sql,Integer.class,rid );
    }

    @Override
    public void add(int rid, int uid) {
        String sql = "insert into tab_favorite values(?,?,?)";
        template.update(sql,rid,new Date(),uid);
    }

    @Override
    public List<Favorite> findMyFavoriteByUid(int uid) {
        String sql = "select * from tab_favorite where uid = ?";

        List<Favorite> query = null;
        try {
            query = template.query(sql, new Object[]{uid}, new RowMapper<Favorite>() {
                public Favorite mapRow(ResultSet rs, int index) throws SQLException {
                    Favorite favorite = new Favorite();
                    favorite.setDate(rs.getString("date"));
                    User user = new UserDaoImpl().findOne(rs.getInt("uid"));
                    favorite.setUser(user);
                    //User user = new User();
                    //user.setUid(rs.getInt("uid"));
                    List<RouteImg> routeImgList = new RouteImageDaoIml().findByRid(rs.getInt("rid"));
                    Route route;
                    route = new RouteDaoImpl().findOne(rs.getInt("rid"));
                    route.setRouteImgList(routeImgList);
                    favorite.setRoute(route);
                    return favorite;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return query;

    }

    public static void main(String[] args) {
        List<Favorite> list = new FavoriteDaoImpl().findMyFavoriteByUid(23);
        System.out.println(Arrays.toString(list.toArray(new Favorite[0])));

        //System.out.println(list);
    }

}
