package dao;



public interface TaccountDao {
	//验证账号是否存在
	public boolean checkAccount(String account);
	
	//通过账号查询余额
	public int getBalanceByAccount(String account);
	
	//通过账号更新余额
	public void updateBalanceByAccount(String account,int balance);
}
