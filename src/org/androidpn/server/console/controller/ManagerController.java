package org.androidpn.server.console.controller;

import org.androidpn.server.service.ManagerService;
import org.androidpn.server.service.ServiceLocator;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class ManagerController extends MultiActionController {
    private ManagerService managerService;

    private ManagerController(){
        managerService = ServiceLocator.getManagerService();
    }


}
