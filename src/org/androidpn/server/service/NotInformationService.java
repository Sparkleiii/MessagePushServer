package org.androidpn.server.service;

import org.androidpn.server.model.NotInformation;

import java.util.List;

public interface NotInformationService {
    public List<NotInformation> findAll();
    //根据关键字获取消息
    public List<NotInformation> findByType(String type);
    //删除消息
    public void delNotInformation(NotInformation notInformation);
    //保存消息
    public void saveNotInformation(NotInformation notInformation);
}
