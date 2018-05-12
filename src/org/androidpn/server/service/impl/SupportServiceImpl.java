package org.androidpn.server.service.impl;

import org.androidpn.server.dao.CommentDao;
import org.androidpn.server.dao.SupportDao;
import org.androidpn.server.model.Comment;
import org.androidpn.server.model.Support;
import org.androidpn.server.service.CommentService;
import org.androidpn.server.service.SupportService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class SupportServiceImpl implements SupportService {
	protected final Log log = LogFactory.getLog(getClass());

	private SupportDao supportDao;

	public SupportDao getSupportDao() {
		return supportDao;
	}

	public void setSupportDao(SupportDao supportDao) {
		this.supportDao = supportDao;
	}

	@Override
	public Support findById(long id) {
		return supportDao.findById(id);
	}

	@Override
	public void saveSupport(Support support) {
		supportDao.saveSupport(support);
	}

	@Override
	public void deleteSupport(Support support) {
		supportDao.deleteSupport(support);
	}

	@Override
	public boolean isSupport(Support support) {
		return supportDao.isSupport(support);
	}

	@Override
	public int getSupportNumBynotId(Long notId) {
		return supportDao.getSupportNumBynotId(notId);
	}
}
