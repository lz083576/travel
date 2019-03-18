package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int findTotalCount(Integer cid, String rname) {
        //String sql = "select count(*) from tab_route where cid = ? ";
        String sql = "select count(*) from tab_route where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        //判断是否有值
        List<Object> params = new ArrayList<>();//拼接的条件
        if (cid != 0) {
            sb.append(" and cid = ? ");
            params.add(cid);
        }
        if (rname != null && rname.length() > 0) {
            sb.append(" and rname like ? ");
            params.add("%" + rname + "%");
        }
        sql = sb.toString();
        Integer num = template.queryForObject(sql, Integer.class, params.toArray());
        return num;
    }

    @Override
    public List<Route> findByPage(Integer cid, Integer start, Integer pageSize, String rname) {
        //String sql = "select * from tab_route where cid = ? limit ?, ?";
        String sql = "select * from tab_route where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        //判断是否有值
        List<Object> params = new ArrayList<>();//拼接的条件
        if (cid != 0) {
            sb.append(" and cid = ? ");
            params.add(cid);
        }
        if (cid == 0) {
            sb.append(" and cid = 5 ");
            params.add(cid);
        }
        if (rname != null && rname.length() > 0) {
            sb.append(" and rname like ? ");
            params.add("%" + rname + "%");
        }
        sb.append(" limit ?, ? ");//完成分页条件
        sql = sb.toString();
        params.add(start);
        params.add(pageSize);


        List<Route> list = template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), params.toArray());
        return list;
    }

    @Override
    public Route findOne(int rid) {
        String sql = "select * from tab_route where rid = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class), rid);
    }

    @Override
    public int findTotalCountFirst(Integer cid) {
        String sql = "select count(*) from tab_route where cid = ?";
        return template.queryForObject(sql,Integer.class,cid);
    }

    @Override
    public List<Route> findByPageFirst(Integer cid, int start, Integer pageSize) {
        String sql = "select * from tab_route where cid = ? limit ? , ?";

        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class),cid,start,pageSize);
    }

    /*public static void main(String[] args) {
//        System.out.println(new RouteDaoImpl().findTotalCount(5));
        RouteDaoImpl routeDao = new RouteDaoImpl();
        List<Route> list = routeDao.findByPage(5, 0, 10);
        for (Route route : list) {
            System.out.println(route);
        }
    }*/
}
