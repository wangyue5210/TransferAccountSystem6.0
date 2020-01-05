package service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import service.TaccountService;
import util.DBUtil;

public class TaccountServiceProxy implements TaccountService {

	private TaccountServiceImpl tsi; 
	public TaccountServiceProxy(TaccountServiceImpl tsi) {
		this.tsi=tsi;
	}
	
	
	@Override
	public void taccount(String zcAccount, String zrAccount, String zzBalanceStr) {
		
		//代码增强
		// 开启事务
				Connection conn = null;
				try {
					conn = DBUtil.getConnection();
					System.out.println("service:"+conn);
					conn.setAutoCommit(false);
					System.out.println("------开始转账--------");

					//业务逻辑
					tsi.taccount(zcAccount, zrAccount, zzBalanceStr);
					

					// 提交事务
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
