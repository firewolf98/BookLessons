package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DisponibilitaBeanDAO {
	Connection conn = null;
	
	 public synchronized boolean doSave (DisponibilitaBean disponibilità) throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 String sql="Insert into Disponibilità (giorno,insegnante,oraInizio,oraFine) values (?,?,?,?);";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setString(1, disponibilità.getGiorno());
		 ps.setString(2, disponibilità.getInsegnante().getUsername());
		 ps.setInt(3, disponibilità.getOraInizio());
		 ps.setInt(4, disponibilità.getOraFine());
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
	 	 
	 public synchronized void doDelete (String giorno,String insegnante) throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 String sql="Delete from Disponibilità where giorno=? and insegnante=?";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setString(1, giorno);
		 ps.setString(2, insegnante);
		 ps.execute();
		 conn.close();
	 }
	 
	 public synchronized DisponibilitaBean doRetrieveByKey(String giorno,String insegnante)throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 String sql="Select * from Disponibilità where giorno=? and insegnante=?";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setString(1, giorno);
		 ps.setString(2,insegnante);
		 ResultSet rs=ps.executeQuery();
		 
		 while(rs.next()) {
			 DisponibilitaBean disponibilità=new DisponibilitaBean();
			 InsegnanteBean ins=new InsegnanteBean();
			 disponibilità.setGiorno(rs.getString("giorno"));
			 disponibilità.setInsegnante(ins);
			 disponibilità.setOraInizio(rs.getInt("oraInizio"));
			 disponibilità.setOraFine(rs.getInt("oraFine"));
			 return disponibilità;
		 }
		 conn.close();
		 return null;
	 }
	 
	 public synchronized ArrayList<DisponibilitaBean> doRetrieveAll() throws SQLException{
		 conn = DriverManagerConnectionPool.getConnection();
		 ArrayList<DisponibilitaBean> disponibili=new ArrayList<>();
		 String sql="select * from Disponibilità";
		 Statement stm=conn.createStatement();
		 ResultSet rs=stm.executeQuery(sql);
		 while(rs.next()) {
			 DisponibilitaBean disponibilità=new DisponibilitaBean();
			 InsegnanteBean ins=new InsegnanteBean();
			 disponibilità.setGiorno("giorno");
			 ins.setUsername(rs.getString("insegnante"));
			 disponibilità.setOraInizio(rs.getInt("oraInizio"));
			 disponibilità.setOraFine(rs.getInt("oraFine"));
			 disponibili.add(disponibilità);
		 }
		 conn.close();
		 return disponibili;
	 }
	 
	 public synchronized boolean doRetrieveByCondition(String giorno,String insegnante)throws SQLException {
		boolean presente=false;
		conn = DriverManagerConnectionPool.getConnection();
		String sql="select * from Disponibilità where giorno=? and insegnante=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, giorno);
		ps.setString(2, insegnante);
		ResultSet rs=ps.executeQuery();
		
		while(rs.next()) {
			presente=true;
		}
		conn.close();
		return presente;
	 }
	 
	 public synchronized ArrayList<DisponibilitaBean> doRetrieveByCondition(InsegnanteBean insegnante)throws SQLException {
		 ArrayList<DisponibilitaBean> disponibili=new ArrayList<>();
		 conn = DriverManagerConnectionPool.getConnection();
		 String sql="select * from Disponibilità where insegnante=?";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setString(1, insegnante.getUsername());
		 ResultSet rs=ps.executeQuery();
		 
		 while(rs.next()) {
			 DisponibilitaBean d=new DisponibilitaBean();
			 d.setGiorno(rs.getString("giorno"));
			 d.setInsegnante(insegnante);
			 d.setOraInizio(rs.getInt("oraInizio"));
			 d.setOraFine(rs.getInt("oraFine"));
			 disponibili.add(d);
		 }
		 conn.close();
		 return disponibili;
	 }
}
