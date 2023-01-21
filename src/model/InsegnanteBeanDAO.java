package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class InsegnanteBeanDAO {
	Connection conn = null;

	public synchronized boolean doSave (InsegnanteBean insegnante) throws SQLException {
		conn = DriverManagerConnectionPool.getConnection();
		String sql="Insert into Insegnante (nome, cognome, email, pw, username, citta, livello, foto, "
				+ "tariffaOraria, sesso) values (?,?,?,?,?,?,?,?,?,?);";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, insegnante.getNome());
		ps.setString(2, insegnante.getCognome());
		ps.setString(3, insegnante.getEmail());
		ps.setString(4, insegnante.getPassword());
		ps.setString(5, insegnante.getUsername());
		ps.setString(6, insegnante.getCitta());
		ps.setString(7, insegnante.getLivello());
		ps.setString(8, insegnante.getFoto());
		ps.setInt(9, insegnante.getTariffaOraria());
		ps.setString(10, String.valueOf(insegnante.getSesso()));
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

	public synchronized boolean doUpdate (InsegnanteBean insegnante) throws SQLException {
		conn = DriverManagerConnectionPool.getConnection();
		String sqlUp="Update Insegnante set pw=?,foto=?,tariffaOraria=?,citta=?, descrizione=?,rifiuti=? where username=?";
		PreparedStatement psUp = conn.prepareStatement(sqlUp);
		psUp.setString(1, insegnante.getPassword());
		psUp.setString(2, insegnante.getFoto());
		psUp.setInt(3, insegnante.getTariffaOraria());
		psUp.setString(4, insegnante.getCitta());
		psUp.setString(5, insegnante.getDescrizione());
		psUp.setInt(6, insegnante.getRifiuti());
		psUp.setString(7, insegnante.getUsername());
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
		String sql="Delete from Insegnante where username=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ps.execute();
		conn.close();
	}

	public synchronized InsegnanteBean doRetrieveByKey(String username)throws SQLException {
		conn = DriverManagerConnectionPool.getConnection();
		String sql="Select * from Insegnante where username=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username); 
		ResultSet rs=ps.executeQuery();

		while(rs.next()) {
			InsegnanteBean insegnante=new InsegnanteBean();
			insegnante.setUsername(rs.getString("username"));
			insegnante.setPassword(rs.getString("pw"));
			insegnante.setNome(rs.getString("nome"));
			insegnante.setCognome(rs.getString("cognome"));
			insegnante.setEmail(rs.getString("email"));
			insegnante.setCitta(rs.getString("citta"));
			insegnante.setFoto(rs.getString("foto"));
			insegnante.setLivello(rs.getString("livello"));
			insegnante.setTariffaOraria(rs.getInt("tariffaOraria"));
			insegnante.setDescrizione(rs.getString("descrizione"));
			insegnante.setSesso(rs.getString("sesso").charAt(0));
			insegnante.setAmministratore(rs.getBoolean("amministratore"));
			return insegnante;
		}
		conn.close();
		return null;
	}

	public synchronized ArrayList<InsegnanteBean> doRetrieveAll() throws SQLException{
		conn = DriverManagerConnectionPool.getConnection();
		ArrayList<InsegnanteBean> insegnanti=new ArrayList<>();
		String sql="select * from Insegnante";
		Statement stm=conn.createStatement();
		ResultSet rs=stm.executeQuery(sql);
		while(rs.next()) {
			InsegnanteBean insegnante=new InsegnanteBean();
			insegnante.setUsername(rs.getString("username"));
			insegnante.setPassword(rs.getString("pw"));
			insegnante.setNome(rs.getString("nome"));
			insegnante.setCognome(rs.getString("cognome"));
			insegnante.setEmail(rs.getString("email"));
			insegnante.setCitta(rs.getString("citta"));
			insegnante.setFoto(rs.getString("foto"));
			insegnante.setLivello(rs.getString("livello"));
			insegnante.setTariffaOraria(rs.getInt("tariffaOraria"));
			insegnante.setDescrizione(rs.getString("descrizione"));
			insegnante.setSesso(rs.getString("sesso").charAt(0));
			insegnanti.add(insegnante);
		}
		conn.close();
		return insegnanti;
	}

	public synchronized ArrayList<InsegnanteBean> doRetrievebyCondition(MateriaBean materia, String livello, String citta) throws SQLException{
		ArrayList<InsegnanteBean> insegnanti=new ArrayList<>();
		conn = DriverManagerConnectionPool.getConnection();
		String sql="select * from Insegnante, InsegnanteMateria where InsegnanteMateria.insegnante=insegnante.username and nomeMateria=? and insegnante.livello=? and citta=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, materia.getNome());
		ps.setString(2, livello);
		ps.setString(3, citta);
		ResultSet rs=ps.executeQuery();

		while(rs.next()) {
			InsegnanteBean insegnante=new InsegnanteBean();
			insegnante.setUsername(rs.getString("username"));
			insegnante.setPassword(rs.getString("pw"));
			insegnante.setNome(rs.getString("nome"));
			insegnante.setCognome(rs.getString("cognome"));
			insegnante.setEmail(rs.getString("email"));
			insegnante.setCitta(rs.getString("citta"));
			insegnante.setFoto(rs.getString("foto"));
			insegnante.setLivello(rs.getString("livello"));
			insegnante.setTariffaOraria(rs.getInt("tariffaOraria"));
			insegnante.setDescrizione(rs.getString("descrizione"));
			insegnante.setSesso(rs.getString("sesso").charAt(0));
			insegnanti.add(insegnante);
		}
		conn.close();
		return insegnanti;
	}
	
	public synchronized ArrayList<InsegnanteBean> doRetrievebyCondition(MateriaBean materia, String livello) throws SQLException{
		ArrayList<InsegnanteBean> insegnanti=new ArrayList<>();
		conn = DriverManagerConnectionPool.getConnection();
		String sql="select * from Insegnante, InsegnanteMateria where InsegnanteMateria.insegnante=insegnante.username and nomeMateria=? and insegnante.livello=? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, materia.getNome());
		ps.setString(2, livello);
		ResultSet rs=ps.executeQuery();

		while(rs.next()) {
			InsegnanteBean insegnante=new InsegnanteBean();
			insegnante.setUsername(rs.getString("username"));
			insegnante.setPassword(rs.getString("pw"));
			insegnante.setNome(rs.getString("nome"));
			insegnante.setCognome(rs.getString("cognome"));
			insegnante.setEmail(rs.getString("email"));
			insegnante.setCitta(rs.getString("citta"));
			insegnante.setFoto(rs.getString("foto"));
			insegnante.setLivello(rs.getString("livello"));
			insegnante.setTariffaOraria(rs.getInt("tariffaOraria"));
			insegnante.setDescrizione(rs.getString("descrizione"));
			insegnante.setSesso(rs.getString("sesso").charAt(0));
			insegnanti.add(insegnante);
		}
		conn.close();
		return insegnanti;
	}


	public synchronized ArrayList<InsegnanteBean> doRetrievebyCondition(ArrayList<InsegnanteBean> i,int minPrezzo, int maxPrezzo){
		ArrayList<InsegnanteBean> insegnanti=new ArrayList<>();
		for (InsegnanteBean x:i) {
			if (minPrezzo!=-1&&maxPrezzo!=-1) { //ricerca per prezzo minimo e massimo
				if (x.getTariffaOraria()>=minPrezzo&&x.getTariffaOraria()<=maxPrezzo)
					insegnanti.add(x);
			}
			else if (minPrezzo==-1) { //prezzo massimo non inserito, ricerca per prezzo minimo
				if (x.getTariffaOraria()<=maxPrezzo)
					insegnanti.add(x);
			}
			else if (maxPrezzo==-1) { //prezzo minimo non inserito, ricerca per prezzo massimo
				if (x.getTariffaOraria()>=minPrezzo)
					insegnanti.add(x);
			}
		}
		return insegnanti;	 
	}


	public synchronized ArrayList<InsegnanteBean> doRetrievebyCondition(ArrayList<InsegnanteBean> i,char sesso){
		ArrayList<InsegnanteBean> insegnanti=new ArrayList<>();
		for (InsegnanteBean x:i) {
			if (x.getSesso()==sesso)
				insegnanti.add(x);
		}
		return insegnanti;	 
	}

}
