package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class InsegnanteMateriaBeanDAO {
	Connection conn = null;
	
	 public synchronized boolean doSave (InsegnanteMateriaBean insegnanteMateria) throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 String sql="Insert into InsegnanteMateria (nomeMateria,insegnante) values (?,?);";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setString(1, insegnanteMateria.getNomeMateria().getNome());
		 ps.setString(2, insegnanteMateria.getInsegnante().getUsername());
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
		 return inserito;
	 }
	 
	 public synchronized void doDelete (String nomeMateria,String insegnante) throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 String sql="Delete from InsegnanteMateria where nomeMateria=? and insegnante=?";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setString(1, nomeMateria);
		 ps.setString(2, insegnante);
		 ps.execute();
	 }
	 
	 public synchronized InsegnanteMateriaBean doRetrieveByKey(String nomeMateria, String insegnante)throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 String sql="Select * from InsegnanteMateria where nomeMateria=? and insegnante=?";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setString(1, nomeMateria);
		 ps.setString(2, insegnante);
		 ResultSet rs=ps.executeQuery();
		
		 while(rs.next()) {
			 InsegnanteMateriaBean insm=new InsegnanteMateriaBean();
			 MateriaBean m=new MateriaBean();
			 InsegnanteBean ins=new InsegnanteBean();
			 m.setNome(rs.getString("nomeMateria"));
			 ins.setUsername(rs.getString("insegnante"));
			 insm.setNomeMateria(m);
			 insm.setInsegnante(ins);
			 return insm;
		 }
		 return null;
	 }
	 
	 public synchronized ArrayList<InsegnanteMateriaBean> doRetrieveAll() throws SQLException{
		 conn = DriverManagerConnectionPool.getConnection();
		 ArrayList<InsegnanteMateriaBean> insmvet=new ArrayList<>();
		 String sql="select * from InsegnanteMateria";
		 Statement stm=conn.createStatement();
		 ResultSet rs=stm.executeQuery(sql);
		 while(rs.next()) {
			 InsegnanteMateriaBean insm=new InsegnanteMateriaBean();
			 MateriaBean m=new MateriaBean();
			 InsegnanteBean ins=new InsegnanteBean();
			 m.setNome(rs.getString("nomeMateria"));
			 ins.setUsername(rs.getString("insegnante"));
			 insm.setNomeMateria(m);
			 insm.setInsegnante(ins);
			 insmvet.add(insm);
		 }
		 return insmvet;
	 }
	 
	 public synchronized ArrayList<MateriaBean> doRetrieveByCondition(InsegnanteBean insegnante) throws SQLException{
		 conn = DriverManagerConnectionPool.getConnection();
		 ArrayList<MateriaBean> materie=new ArrayList<>();
		 String sql="select nomeMateria from InsegnanteMateria where insegnante=?";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setString(1, insegnante.getUsername());
		 ResultSet rs=ps.executeQuery();
		 while(rs.next()) {
			 MateriaBean m=new MateriaBean(rs.getString("nomeMateria"));
			 materie.add(m);
		 }
		 return materie;
	 }
}
