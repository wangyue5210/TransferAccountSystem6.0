package service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import dao.TaccountDao;
import dao.impl.TaccountDaoImpl;
import service.TaccountService;
import util.DBUtil;

public class TaccountServiceImpl implements TaccountService {

	@Override
	public void taccount(String zcAccount, String zrAccount, String zzBalanceStr) {
		int zzBalance = Integer.parseInt(zzBalanceStr);
		// System.out.println(zcAccount+zrAccount+zzBalance);

		TaccountDao taccountDao = new TaccountDaoImpl();

		// 开启事务
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			System.out.println("service:"+conn);
			conn.setAutoCommit(false);
			System.out.println("------开始转账--------");

			if (taccountDao.checkAccount(zcAccount)) {
				if (taccountDao.checkAccount(zrAccount)) {
					// 根据转出账号取得转出账号余额
					int zcBalance = taccountDao.getBalanceByAccount(zcAccount);
					if (zzBalance <= zcBalance) {
						// 扣钱
						taccountDao.updateBalanceByAccount(zcAccount, zcBalance - zzBalance);
						// 通过转入账号取得转入账号余额
						int zrBalance = taccountDao.getBalanceByAccount(zrAccount);
						// 更新转入账号余额
						taccountDao.updateBalanceByAccount(zrAccount, zrBalance + zzBalance);
					}

				}

			}

			// 关闭事务
			conn.commit();

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				DBUtil.DBClose(conn, null, null);
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

	}
}
