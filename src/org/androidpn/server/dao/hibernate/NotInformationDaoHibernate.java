package org.androidpn.server.dao.hibernate;

import org.androidpn.server.dao.NotInformationDao;
import org.androidpn.server.model.NotInformation;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class NotInformationDaoHibernate extends HibernateDaoSupport implements NotInformationDao {
    @Override
    public List<NotInformation> findAll() {
        String hql = "from NotInformation";
        return getHibernateTemplate().find(hql);
    }

    @Override
    public List<NotInformation> findByType(String type) {
        String hql = "from NotInformation where tag1 like '%"+type+"%' " +
                "or tag2 like '%"+type+"%' or tag3 like '%"+type+"%'";
        return getHibernateTemplate().find(hql);
    }

    @Override
    public void delNotInformation(NotInformation notInformation) {
        getHibernateTemplate().delete(notInformation);
    }

    @Override
    public void saveNotInformation(NotInformation notInformation) {
        getHibernateTemplate().save(notInformation);
        getHibernateTemplate().flush();
    }

}
