package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MateriaBeanDAO {
	Connection conn = null;
	
	 public synchronized boolean doSave (MateriaBean materia) throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 String sql="Insert into Materia (nome) values (?);";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setString(1, materia.getNome());
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
	 	 
	 public synchronized void doDelete (String nome) throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 String sql="Delete from Materia where nome=?";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setString(1, nome);
		 ps.execute();
		 conn.close();
	 }
	 
	 public synchronized MateriaBean doRetrieveByKey(String nome)throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 String sql="Select * from Materia where nome=?";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setString(1, nome);
		 ResultSet rs=ps.executeQuery();
		 
		 while(rs.next()) {
			 MateriaBean materia=new MateriaBean();
			 materia.setNome(rs.getString("nome"));
			 return materia;
		 }
		 conn.close();
		 return null;
	 }
	 
	 public synchronized ArrayList<MateriaBean> doRetrieveAll() throws SQLException{
		 conn = DriverManagerConnectionPool.getConnection();
		 ArrayList<MateriaBean> materie=new ArrayList<>();
		 String sql="select * from Materia";
		 Statement stm=conn.createStatement();
		 ResultSet rs=stm.executeQuery(sql);
		 while(rs.next()) {
			 MateriaBean materia=new MateriaBean();
			 materia.setNome(rs.getString("nomeMateria"));
			 materie.add(materia);
		 }
		 conn.close();
		 return materie;
	 }
	 
	 public synchronized ArrayList<MateriaBean> doRetrieveByNome(String nome) throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 PreparedStatement ps = conn.prepareStatement(
				 "SELECT nomeMateria FROM Materia WHERE nomeMateria like ?");
		 ps.setString(1, nome);
		 ArrayList<MateriaBean> materie = new ArrayList<>();
		 ResultSet rs = ps.executeQuery();
		 while (rs.next()) {
			 MateriaBean m = new MateriaBean();
			 m.setNome(rs.getString(1));
			 materie.add(m);
		 }
		 conn.close();
		 return materie;
	 }
}
