package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.RouteImageDao;
import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.RouteImageDaoIml;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    private RouteDao routeDao = new RouteDaoImpl();
    private RouteImageDao routeImageDao = new RouteImageDaoIml();
    private SellerDao sellerDao = new SellerDaoImpl();
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public PageBean<Route> pageQueryFirst(Integer cid, Integer currentPage, Integer pageSize) {
        //封装PageBean
        PageBean<Route> pb = new PageBean<Route>();
        //设置当前页码
        pb.setCurrentPage(currentPage);
        //设置每页显示条数
        pb.setPageSize(pageSize);

        //设置总记录数
        int totalCount = routeDao.findTotalCountFirst(cid);
        pb.setTotalCount(totalCount);
        //设置当前页显示的数据集合
        int start = (currentPage - 1) * pageSize;//开始的记录数
        List<Route> list = routeDao.findByPageFirst(cid,start,pageSize);
        pb.setList(list);

        //设置总页数 = 总记录数/每页显示条数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize :(totalCount / pageSize) + 1 ;
        pb.setTotalPage(totalPage);


        return pb;
    }

    @Override
    public PageBean<Route> pageQuery(Integer cid, Integer currentPage, Integer pageSize,String rname) {
        //封装pagebean
        PageBean<Route> pb = new PageBean<Route>();
        pb.setCurrentPage(currentPage);//当前页码
        pb.setPageSize(pageSize);//每页页码

        int totalCount = routeDao.findTotalCount(cid,rname);
        pb.setTotalCount(totalCount);//总记录数

        int start = (currentPage - 1) * pageSize;
        List<Route> list = routeDao.findByPage(cid, start, pageSize,rname);
        pb.setList(list);

        //设置总页数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
        pb.setTotalPage(totalPage);

        return pb;
    }

    @Override
    public Route findOne(String rid) {
        //根据id去route表中查询route对象
        Route route = routeDao.findOne(Integer.parseInt(rid));
        //根据route 的id 查询图片的集合信息
        List<RouteImg> routeImgList = routeImageDao.findByRid(route.getRid());
        //将集合设置到route对象
        route.setRouteImgList(routeImgList);
        //根据route的id查询卖家id
        Seller seller = sellerDao.findById(route.getSid());
        route.setSeller(seller);
        int count = favoriteDao.findCount(route.getRid());
        route.setCount(count);
        return route;
    }

    public static void main(String[] args) {
        PageBean<Route> routePageBean = new RouteServiceImpl().pageQueryFirst(5, 2, 5);
        System.out.println(routePageBean);
    }
}
