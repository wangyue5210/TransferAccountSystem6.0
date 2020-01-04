package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TaccountDao;
import dao.impl.TaccountDaoImpl;

import util.DBUtil;

/**
 * Servlet implementation class TaccountController
 */
public class TaccountController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("进入到转账操作");
		String zcAccount=request.getParameter("zcAccount");
		String zrAccount=request.getParameter("zrAccount");
		String zzBalanceStr=request.getParameter("zzBalance");
		int zzBalance=Integer.parseInt(zzBalanceStr);
		//System.out.println(zcAccount+zrAccount+zzBalance);
		
		TaccountDao taccountDao=new TaccountDaoImpl();
		
		if(taccountDao.checkAccount(zcAccount)) {
			if(taccountDao.checkAccount(zrAccount)) {
				//根据转出账号取得转出账号余额
				int zcBalance=taccountDao.getBalanceByAccount(zcAccount);
				if(zzBalance<=zcBalance) {
					//扣钱
					taccountDao.updateBalanceByAccount(zcAccount, zcBalance-zzBalance);
					//通过转入账号取得转入账号余额
					int zrBalance=taccountDao.getBalanceByAccount(zrAccount);
					//更新转入账号余额
					taccountDao.updateBalanceByAccount(zrAccount, zrBalance+zzBalance);
				}
				
			
			
			}
			
			
		}
		
		response.sendRedirect(request.getContextPath()+"/success.jsp");
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doGet(request, response);
	}

}
