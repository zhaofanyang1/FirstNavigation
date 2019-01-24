package com.jiyun.firstnavigation.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.jiyun.firstnavigation.beans.MyChannel;

import com.jiyun.firstnavigation.dao.MyChannelDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig myChannelDaoConfig;

    private final MyChannelDao myChannelDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        myChannelDaoConfig = daoConfigMap.get(MyChannelDao.class).clone();
        myChannelDaoConfig.initIdentityScope(type);

        myChannelDao = new MyChannelDao(myChannelDaoConfig, this);

        registerDao(MyChannel.class, myChannelDao);
    }
    
    public void clear() {
        myChannelDaoConfig.clearIdentityScope();
    }

    public MyChannelDao getMyChannelDao() {
        return myChannelDao;
    }

}