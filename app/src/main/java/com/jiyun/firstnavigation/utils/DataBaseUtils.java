package com.jiyun.firstnavigation.utils;

import com.jiyun.firstnavigation.app.App;
import com.jiyun.firstnavigation.beans.DownBean;
import com.jiyun.firstnavigation.beans.MyChannel;
import com.jiyun.firstnavigation.dao.DaoMaster;
import com.jiyun.firstnavigation.dao.DaoSession;
import com.jiyun.firstnavigation.dao.MyChannelDao;

import java.util.List;

public class DataBaseUtils {
    private static DataBaseUtils dataBaseUtils;
    private final MyChannelDao dao;

    private DataBaseUtils() {
        //数据库的初始化
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(App.app, "tonghang.db");
        //获取可读写数据库
        DaoMaster daoMaster = new DaoMaster(openHelper.getWritableDatabase());
        //获取表管理器
        DaoSession daoSession = daoMaster.newSession();
        //获取我们要操作表的工具类
        dao = daoSession.getMyChannelDao();
    }

    public static DataBaseUtils getInstance() {
        if (dataBaseUtils == null) {
            synchronized (DataBaseUtils.class) {
                if (dataBaseUtils == null) {
                    dataBaseUtils = new DataBaseUtils();
                }
            }
        }
        return dataBaseUtils;
    }

    /**
     * 插入集合数据
     *
     * @param beans
     */
    public void insert(List<MyChannel> beans) {
        dao.insertInTx(beans);
    }

    /**
     * 删除
     *
     * @param beans
     */
    public void delete(List<MyChannel> beans) {
        dao.deleteInTx(beans);
    }

    /**
     * @param beans
     */
    public void update(MyChannel beans) {
        dao.update(beans);
    }

    /**
     * 查询所有的数据
     *
     * @return
     */

    public List<MyChannel> selectAll() {
        return dao.queryBuilder().list();
    }

    /**
     * 条件查询单条数据
     *
     * @param address
     * @return
     */
    public MyChannelDao selectSingle(String address, int id) {
        return null;
    }

    /**
     * 分页查询
     *
     * @param page
     * @param count
     * @return
     */
    public List<MyChannel> selectPage(int page, int count) {
        return dao.queryBuilder().offset(page * count).limit(count).list();
    }
}
