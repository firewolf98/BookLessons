package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LezioneBeanDAO {
	Connection conn = null;
	
	 public synchronized boolean doSave (LezioneBean lezione) throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 String sql="Insert into Lezione (oraInizio,oraFine,dataLezione,costo,studente,insegnante,materia,luogo,idOrdine) values (?,?,?,?,?,?,?,?,?);";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 //ps.setInt(1, lezione.getCodice());
		 ps.setInt(1, lezione.getOraInizio());
		 ps.setInt(2, lezione.getOraFine());
		 ps.setDate(3, (Date)lezione.getDataLezione());
		 ps.setInt(4, lezione.getCosto());
		 ps.setString(5, lezione.getStudente().getUsername());
		 ps.setString(6, lezione.getInsegnante().getUsername());
		 ps.setString(7, lezione.getMateria().getNome());
		 ps.setString(8, lezione.getLuogo());
		 ps.setInt(9, lezione.getOrdine().getIdOrdine());
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
	 
	 public synchronized boolean doUpdate (LezioneBean lezione) throws SQLException {
			conn = DriverManagerConnectionPool.getConnection();
			String sqlUp="Update Lezione set accreditata=?,fatturata=? where codice=?";
			PreparedStatement psUp = conn.prepareStatement(sqlUp);
			psUp.setBoolean(1, lezione.isAccreditata());
			psUp.setBoolean(2, lezione.isFatturata());
			psUp.setInt(3, lezione.getCodice());
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
	 	 
	 public synchronized void doDelete (int codice) throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 String sql="Delete from Lezione where codice=?";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setInt(1, codice);
		 ps.execute();
		 conn.close();
	 }
	 
	 public synchronized LezioneBean doRetrieveByKey(int codice)throws SQLException {
		 conn = DriverManagerConnectionPool.getConnection();
		 String sql="Select * from Lezione where codice=?";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setInt(1, codice);
		 ResultSet rs=ps.executeQuery();
		 
		 while(rs.next()) {
			 LezioneBean lezione=new LezioneBean();
			 StudenteBean s=new StudenteBean();
			 InsegnanteBean ins=new InsegnanteBean();
			 MateriaBean mat=new MateriaBean();
			 OrdineBean ord=new OrdineBean();
			 lezione.setCodice(rs.getInt("codice"));
			 lezione.setOraInizio(rs.getInt("oraInizio"));
			 lezione.setOraFine(rs.getInt("oraFine"));
			 lezione.setDataLezione(rs.getDate("dataLezione"));
			 lezione.setCosto(rs.getInt("costo"));
			 StudenteBeanDAO sDao=new StudenteBeanDAO();
			 s=sDao.doRetrieveByKey(rs.getString("studente"));
			 ins.setUsername(rs.getString("insegnante"));
			 mat.setNome(rs.getString("materia"));
			 ord.setIdOrdine(rs.getInt("idOrdine"));
			 lezione.setLuogo(rs.getString("luogo"));
			 lezione.setAccreditata(rs.getBoolean("accreditata"));
			 lezione.setFatturata(rs.getBoolean("fatturata"));
			 lezione.setInsegnante(ins);
			 lezione.setMateria(mat);
			 lezione.setOrdine(ord);
			 lezione.setStudente(s);
			 return lezione;
		 }
		 conn.close();
		 return null;
	 }
	 
	 public synchronized ArrayList<LezioneBean> doRetrieveAll() throws SQLException{
		 conn = DriverManagerConnectionPool.getConnection();
		 ArrayList<LezioneBean> lezioni=new ArrayList<>();
		 String sql="select * from Lezione";
		 Statement stm=conn.createStatement();
		 ResultSet rs=stm.executeQuery(sql);
		 while(rs.next()) {
			 LezioneBean lezione=new LezioneBean();
			 StudenteBean s=new StudenteBean();
			 InsegnanteBean ins=new InsegnanteBean();
			 MateriaBean mat=new MateriaBean();
			 OrdineBean ord=new OrdineBean();
			 lezione.setCodice(rs.getInt("codice"));
			 lezione.setOraInizio(rs.getInt("oraInizio"));
			 lezione.setOraFine(rs.getInt("oraFine"));
			 lezione.setDataLezione(rs.getDate("dataLezione"));
			 lezione.setCosto(rs.getInt("costo"));
			 StudenteBeanDAO sDao=new StudenteBeanDAO();
			 s=sDao.doRetrieveByKey(rs.getString("studente"));
			 ins.setUsername(rs.getString("insegnante"));
			 mat.setNome(rs.getString("nome"));
			 ord.setIdOrdine(rs.getInt("idOrdine"));
			 lezione.setLuogo(rs.getString("luogo"));
			 lezione.setAccreditata(rs.getBoolean("accreditata"));
			 lezione.setFatturata(rs.getBoolean("fatturata"));
			 lezione.setInsegnante(ins);
			 lezione.setMateria(mat);
			 lezione.setOrdine(ord);
			 lezione.setStudente(s);
			 lezioni.add(lezione);
		 }
		 conn.close();
		 return lezioni;
	 }
	 
	 public synchronized ArrayList<LezioneBean> doRetrieveByCondition(InsegnanteBean insegnante) throws SQLException{
		 conn = DriverManagerConnectionPool.getConnection();
		 ArrayList<LezioneBean> lezioni=new ArrayList<>();
		 String sql="select * from Lezione where insegnante=?";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setString(1, insegnante.getUsername());
		 ResultSet rs=ps.executeQuery();
		 
		 while(rs.next()) {
			 LezioneBean lezione=new LezioneBean();
			 StudenteBean s=new StudenteBean();
			 InsegnanteBean ins=new InsegnanteBean();
			 MateriaBean mat=new MateriaBean();
			 OrdineBean ord=new OrdineBean();
			 lezione.setCodice(rs.getInt("codice"));
			 lezione.setOraInizio(rs.getInt("oraInizio"));
			 lezione.setOraFine(rs.getInt("oraFine"));
			 lezione.setDataLezione(rs.getDate("dataLezione"));
			 lezione.setCosto(rs.getInt("costo"));
			 StudenteBeanDAO sDao=new StudenteBeanDAO();
			 s=sDao.doRetrieveByKey(rs.getString("studente"));
			 InsegnanteBeanDAO iDao=new InsegnanteBeanDAO();
			 ins=iDao.doRetrieveByKey(rs.getString("insegnante"));
			 mat.setNome(rs.getString("materia"));
			 ord.setIdOrdine(rs.getInt("idOrdine"));
			 lezione.setLuogo(rs.getString("luogo"));
			 lezione.setAccreditata(rs.getBoolean("accreditata"));
			 lezione.setFatturata(rs.getBoolean("fatturata"));
			 lezione.setInsegnante(ins);
			 lezione.setMateria(mat);
			 lezione.setOrdine(ord);
			 lezione.setStudente(s);
			 lezioni.add(lezione);
		 }
		 conn.close();
		 return lezioni;
	 }
	 
	 public synchronized ArrayList<LezioneBean> doRetrieveByCondition(StudenteBean studente) throws SQLException{
		 conn = DriverManagerConnectionPool.getConnection();
		 ArrayList<LezioneBean> lezioni=new ArrayList<>();
		 String sql="select * from Lezione where studente=?";
		 PreparedStatement ps = conn.prepareStatement(sql);
		 ps.setString(1, studente.getUsername());
		 ResultSet rs=ps.executeQuery();
		 
		 while(rs.next()) {
			 LezioneBean lezione=new LezioneBean();
			 StudenteBean s=new StudenteBean();
			 InsegnanteBean ins=new InsegnanteBean();
			 MateriaBean mat=new MateriaBean();
			 OrdineBean ord=new OrdineBean();
			 lezione.setCodice(rs.getInt("codice"));
			 lezione.setOraInizio(rs.getInt("oraInizio"));
			 lezione.setOraFine(rs.getInt("oraFine"));
			 lezione.setDataLezione(rs.getDate("dataLezione"));
			 lezione.setCosto(rs.getInt("costo"));
			 InsegnanteBeanDAO iDAO=new InsegnanteBeanDAO();
			 ins=iDAO.doRetrieveByKey(rs.getString("insegnante"));
			 StudenteBeanDAO sDAO=new StudenteBeanDAO();
			 s=sDAO.doRetrieveByKey(rs.getString("studente"));
			 mat.setNome(rs.getString("materia"));
			 ord.setIdOrdine(rs.getInt("idOrdine"));
			 lezione.setStudente(s);
			 lezione.setInsegnante(ins);
			 lezione.setMateria(mat);
			 lezione.setOrdine(ord);
			 lezione.setLuogo(rs.getString("luogo"));
			 lezione.setAccreditata(rs.getBoolean("accreditata"));
			 lezione.setFatturata(rs.getBoolean("fatturata"));
			 lezioni.add(lezione);
		 }
		 conn.close();
		 return lezioni;
	 }
	 
	 
	 
	 public synchronized int doRetrieveByCondition() throws SQLException{
		 conn = DriverManagerConnectionPool.getConnection();
		 String sql2="Select codice from Lezione";
		 Statement stm=conn.createStatement();
		 int id=0;
		 ResultSet rs2=stm.executeQuery(sql2);
		 while(rs2.next()) {
			 id=rs2.getInt("codice");
		 }
		 conn.close();
		 return id;
	 }

}
