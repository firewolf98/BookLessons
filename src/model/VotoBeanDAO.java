package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VotoBeanDAO {
	Connection conn = null;
	
	 public synchronized boolean doSave (VotoBean voto) throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 String sql="Insert into Voto (studente, insegnante, voto, commento, dataVoto) values (?,?,?,?,?);";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setString(1, voto.getStudente().getUsername());
		 ps.setString(2, voto.getInsegnante().getUsername());
		 ps.setInt(3, voto.getVoto());
		 ps.setString(4, voto.getCommento());
		 ps.setDate(5, (Date) voto.getDataVoto());
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
	 
	 public synchronized void doDelete (String studente, String insegnante, Date dataVoto) throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 String sql="Delete from Voto where studente=? and insegnante=? and dataVoto=?";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setString(1, studente);
		 ps.setString(2, insegnante);
		 ps.setDate(3, dataVoto);
		 ps.execute();
		 conn.close();
	 }
	 
	 public synchronized VotoBean doRetrieveByKey(String studente, String insegnante, Date dataVoto)throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 String sql="Select * from Voto where studente=? and insegnante=? and dataVoto=?";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setString(1, studente);
		 ps.setString(2, insegnante);
		 ps.setDate(3, dataVoto); 
		 ResultSet rs=ps.executeQuery();
		
		 while(rs.next()) {
			 VotoBean voto=new VotoBean();
			 StudenteBean s=new StudenteBean();
			 InsegnanteBean ins=new InsegnanteBean();
			 s.setUsername(rs.getString("studente"));
			 ins.setUsername(rs.getString("insegnante"));
			 voto.setStudente(s);
			 voto.setInsegnante(ins);
			 voto.setDataVoto(rs.getDate("dataVoto"));
			 voto.setVoto(rs.getInt("voto"));
			 voto.setCommento(rs.getString("commento"));
			 return voto;
		 }
		 conn.close();
		 return null;
	 }
	 
	 public synchronized ArrayList<VotoBean> doRetrieveAll() throws SQLException{
		 conn = DriverManagerConnectionPool.getConnection();
		 ArrayList<VotoBean> voti=new ArrayList<>();
		 String sql="select * from Voto";
		 Statement stm=conn.createStatement();
		 ResultSet rs=stm.executeQuery(sql);
		 while(rs.next()) {
			 VotoBean voto=new VotoBean();
			 StudenteBean s=new StudenteBean();
			 InsegnanteBean ins=new InsegnanteBean();
			 StudenteBeanDAO studentedao=new StudenteBeanDAO();
			 s=studentedao.doRetrieveByKey(rs.getString("studente"));
			 InsegnanteBeanDAO insegnantedao=new InsegnanteBeanDAO();
			 ins=insegnantedao.doRetrieveByKey(rs.getString("insegnante"));
			 voto.setStudente(s);
			 voto.setInsegnante(ins);
			 voto.setDataVoto(rs.getDate("dataVoto"));
			 voto.setVoto(rs.getInt("voto"));
			 voto.setCommento(rs.getString("commento"));
			 voti.add(voto);
		 }
		 conn.close();
		 return voti;
	 }
	 
	 public synchronized ArrayList<VotoBean> doRetrieveByCondition(InsegnanteBean insegnante)throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 ArrayList<VotoBean> voti=new ArrayList<>();
		 String sql="Select * from Voto where insegnante=?";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setString(1, insegnante.getUsername());
		 ResultSet rs=ps.executeQuery();
			
		 while(rs.next()) {
			 VotoBean voto=new VotoBean();
			 StudenteBean s=new StudenteBean();
			 InsegnanteBean ins=new InsegnanteBean();
			 StudenteBeanDAO studentedao=new StudenteBeanDAO();
			 s=studentedao.doRetrieveByKey(rs.getString("studente"));
			 InsegnanteBeanDAO insegnantedao=new InsegnanteBeanDAO();
			 ins=insegnantedao.doRetrieveByKey(rs.getString("insegnante"));
			 voto.setStudente(s);
			 voto.setInsegnante(ins);
			 voto.setDataVoto(rs.getDate("dataVoto"));
			 voto.setVoto(rs.getInt("voto"));
			 voto.setCommento(rs.getString("commento"));
			 voti.add(voto);
		 }
		 conn.close();
		 return voti;
	 }
}
