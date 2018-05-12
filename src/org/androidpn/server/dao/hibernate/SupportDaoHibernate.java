package org.androidpn.server.dao.hibernate;

import org.androidpn.server.dao.SupportDao;
import org.androidpn.server.model.Support;
import org.mortbay.log.Log;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class SupportDaoHibernate extends HibernateDaoSupport implements SupportDao {

	@Override
	public Support findById(long id) {
		return (Support) getHibernateTemplate().get(Support.class,id);
	}

	@Override
	public void saveSupport(Support support) {
		getHibernateTemplate().save(support);
		getHibernateTemplate().flush();
	}

	@Override
	public void deleteSupport(Support support) {
        Support support1 = getSupportByAccountNotId(support.getAccount(),support.getNotId());
		getHibernateTemplate().delete(support1);
		getHibernateTemplate().flush();
	}

	@Override
	public boolean isSupport(Support support) {
		boolean flag = false;
		Support support1 = getSupportByAccountNotId(support.getAccount(),support.getNotId());
		if (support1!=null){
			flag = true;
		}
		System.out.println(flag);
		return flag;
	}

	public Support getSupportByAccountNotId(String account,long notId){
        String hql = "from Support where account = '"+account+"' and notId ="+notId+" ";
        List<Support> supportList = getHibernateTemplate().find(hql);
        if (supportList.size()>0 && supportList!=null){
            return supportList.get(0);
        }else{
            return  null;
        }
    }

	@Override
	public int getSupportNumBynotId(Long notId) {
		String hql = "from Support where notId = ?";
		int count = getHibernateTemplate().find(hql).size();
		return count;
	}

}
