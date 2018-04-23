package org.androidpn.server.service.impl;

import org.androidpn.server.dao.NotInformationDao;
import org.androidpn.server.model.NotInformation;
import org.androidpn.server.service.NotInformationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class NotInformationServiceImpl implements NotInformationService {
	protected final Log log = LogFactory.getLog(getClass());

	private NotInformationDao notInformationDao;

	public NotInformationDao getNotInformationDao() {
		return notInformationDao;
	}

	public void setNotInformationDao(NotInformationDao notInformationDao) {
		this.notInformationDao = notInformationDao;
	}

	@Override
	public List<NotInformation> findAll() {
		return notInformationDao.findAll();
	}

	@Override
	public List<NotInformation> findByType(String type) {
		return notInformationDao.findByType(type);
	}

	@Override
	public void delNotInformation(NotInformation notInformation) {
		notInformationDao.delNotInformation(notInformation);
	}

	@Override
	public void saveNotInformation(NotInformation notInformation) {
		notInformationDao.saveNotInformation(notInformation);
	}
}
