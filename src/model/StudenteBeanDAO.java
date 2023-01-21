package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class StudenteBeanDAO {
	 Connection conn = null;
	
	 public synchronized boolean doSave (StudenteBean studente) throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 String sql="Insert into Studente (nome, cognome, email, pw, username, citta) values (?,?,?,?,?,?);";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setString(1, studente.getNome());
		 ps.setString(2, studente.getCognome());
		 ps.setString(3, studente.getEmail());
		 ps.setString(4, studente.getPassword());
		 ps.setString(5, studente.getUsername());
		 ps.setString(6, studente.getCitta());
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
	 
	 public synchronized boolean doUpdate (StudenteBean studente) throws SQLException {
			conn = DriverManagerConnectionPool.getConnection();
			String sqlUp="Update Studente set pw=?,citta=? where username=?";
			PreparedStatement psUp = conn.prepareStatement(sqlUp);
			psUp.setString(1, studente.getPassword());
			psUp.setString(2, studente.getCitta());
			psUp.setString(3, studente.getUsername());
			boolean modificato=false;
			int ris;
			try {
				ris=psUp.executeUpdate();
				if (ris==1)
					modificato=true;
			}
			catch(SQLException ex) {
				ex.printStackTrace();
			}
			conn.close();
			return modificato;
		}
	 
	 public synchronized void doDelete (String username) throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 String sql="Delete from Studente where username=?";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setString(1, username);
		 ps.execute();
		 conn.close();
	 }
	 
	 public synchronized StudenteBean doRetrieveByKey(String username)throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 String sql="Select * from Studente where username=?";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setString(1, username); 
		 ResultSet rs=ps.executeQuery();
		
		 while(rs.next()) {
			 StudenteBean studente=new StudenteBean();
			 studente.setUsername(rs.getString("username"));
			 studente.setPassword(rs.getString("pw"));
			 studente.setEmail(rs.getString("email"));
			 studente.setNome(rs.getString("nome"));
			 studente.setCognome(rs.getString("cognome"));
			 studente.setCitta(rs.getString("citta"));
			 studente.setAmministratore(rs.getBoolean("amministratore"));
			 return studente;
		 }
		 conn.close();
		 return null;
	 }
	 
	 public synchronized ArrayList<StudenteBean> doRetrieveAll() throws SQLException{
		 conn = DriverManagerConnectionPool.getConnection();
		 ArrayList<StudenteBean> studenti=new ArrayList<>();
		 String sql="select * from Studente";
		 Statement stm=conn.createStatement();
		 ResultSet rs=stm.executeQuery(sql);
		 while(rs.next()) {
			 StudenteBean studente=new StudenteBean();
			 studente.setUsername(rs.getString("username"));
			 studente.setPassword(rs.getString("pw"));
			 studente.setEmail(rs.getString("email"));
			 studente.setNome(rs.getString("nome"));
			 studente.setCognome(rs.getString("cognome"));
			 studente.setCitta(rs.getString("citta"));
			 studenti.add(studente);
		 }
		 conn.close();
		 return studenti;
	 }
	
}
