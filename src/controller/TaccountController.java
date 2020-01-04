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
import service.TaccountService;
import service.impl.TaccountServiceImpl;
import util.DBUtil;


public class TaccountController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("进入到转账操作");
		String zcAccount=request.getParameter("zcAccount");
		String zrAccount=request.getParameter("zrAccount");
		String zzBalanceStr=request.getParameter("zzBalance");
		TaccountService taccountService=new TaccountServiceImpl();
		taccountService.taccount(zcAccount, zrAccount, zzBalanceStr);
		
		
		
		
		
		
		

			
			
		
		
		response.sendRedirect(request.getContextPath()+"/success.jsp");
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doGet(request, response);
	}

}
