package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OrdineBeanDAO {
	Connection conn = null;
	
	 public synchronized boolean doSave (OrdineBean ordine) throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 String sql="Insert into Ordine (costoTot, dataOrdine) values (?,?);";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setInt(1, ordine.getCostoTot());
		 ps.setDate(2, (Date)ordine.getDataOrdine());
		 
		 int ris;
		 boolean inserito=false;
		 try {
			 ris=ps.executeUpdate();
			 if (ris==1)
				inserito=true;
		 }
		 catch(SQLException ex) {
			 ex.printStackTrace();
		 }
		 conn.close();
		 return inserito;
	 }
	 
	 public synchronized void doDelete (int id) throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 String sql="Delete from ordine where id=?";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setInt(1, id);
		 ps.execute();
		 conn.close();
	 }
	 
	 public synchronized OrdineBean doRetrieveByKey(String id)throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 String sql="Select * from Ordine where id=?";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setString(1, id); 
		 ResultSet rs=ps.executeQuery();
		
		 while(rs.next()) {
			 OrdineBean ordine=new OrdineBean();
			 ordine.setIdOrdine(rs.getInt("idOrdine"));
			 ordine.setCostoTot(rs.getInt("costoTot"));
			 ordine.setDataOrdine(rs.getDate("dataOrdine"));
			 return ordine;
		 }
		 conn.close();
		 return null;
	 }
	 
	 public synchronized ArrayList<OrdineBean> doRetrieveAll() throws SQLException{
		 conn = DriverManagerConnectionPool.getConnection();
		 ArrayList<OrdineBean> ordini=new ArrayList<>();
		 String sql="select * from Ordine";
		 Statement stm=conn.createStatement();
		 ResultSet rs=stm.executeQuery(sql);
		 while(rs.next()) {
			 OrdineBean ordine=new OrdineBean();
			 ordine.setIdOrdine(rs.getInt("idOrdine"));
			 ordine.setCostoTot(rs.getInt("costoTot"));
			 ordine.setDataOrdine(rs.getDate("dataOrdine"));
			 ordini.add(ordine);
		 }
		 conn.close();
		 return ordini;
	 }
	 
	 public synchronized int doRetrieveByCondition() throws SQLException{
		 conn = DriverManagerConnectionPool.getConnection();
		 String sql2="Select idOrdine from Ordine";
		 Statement stm=conn.createStatement();
		 int id=0;
		 ResultSet rs2=stm.executeQuery(sql2);
		 while(rs2.next()) {
			 id=rs2.getInt("idOrdine");
		 }
		 conn.close();
		 return id;
	 }
}
