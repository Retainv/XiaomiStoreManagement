package top.retain.xmsc.service.impl;

import top.retain.xmsc.dao.IManagerDao;
import top.retain.xmsc.dao.impl.ManagerDaoImpl;
import top.retain.xmsc.dto.Manager;
import top.retain.xmsc.service.IManagerService;
import top.retain.xmsc.util.Base64Encoder;

/**
 * @author Retain
 * @date 2021/1/12 19:01
 */
public class ManagerServiceImpl implements IManagerService {
    IManagerDao managerDao=new ManagerDaoImpl();


    @Override
    public boolean login(Manager manager) {
        try {
            // BASE64密码加密
            manager.setPwd(Base64Encoder.encryptByBASE64(manager.getPwd()));
            return managerDao.login(manager);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
