package Classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Banco_de_Dados.Conecta;

public class Situa��esDAO {
	
	//GETTERS E SETTERS
	private int id;
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	
	private String descricao;	
	public String getDescricao() {return descricao;}
	public void setDescricao(String descricao) {this.descricao = descricao;}
	
	//M�TODO CADASTRAR
	public void Cadastrar() {

		try {
			Connection string_conexao = new Banco_de_Dados.Conecta().getConnection();
			Statement st = string_conexao.createStatement();
			st.execute("INSERT INTO TB_SITUA��ES VALUES ('"+getDescricao()+"')");
			st.close();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, getDescricao()+" N�o Foi Cadastrado(a) No Banco de Dados! =(");
		}
	}
	
	//M�TODO ALTERAR
	public void Alterar(){
		
		try{
			Connection string_conexao = new Conecta().getConnection();
			Statement st = string_conexao.createStatement();
			st.execute("UPDATE TB_SITUA��ES SET DESCRI��O = '"+getDescricao()+"' WHERE ID = "+getId()+"");			
			st.close();
		}
		catch (SQLException e){
			JOptionPane.showMessageDialog(null, getDescricao()+" N�o Foi Alterado(a) No Banco de Dados! =(");
		}		
	}

	//M�TODO EXCLUIR
	public void Excluir(){
		
		try{
			Connection string_conexao = new Conecta().getConnection();
			Statement st = string_conexao.createStatement();
			st.execute("DELETE FROM TB_SITUA��ES WHERE ID = '"+getId()+"'");
			st.close();
		}
		catch (SQLException e){
			JOptionPane.showMessageDialog(null, getDescricao()+" N�o Foi Exclu�do(a) No Banco de Dados! =(");
		}		
	}
	
	//M�TODO LISTAR	
	public TableModel Listar(){
		
		try {
			Statement stmt = null;
			ResultSet rs = null;
			Connection conexao = new Banco_de_Dados.Conecta().getConnection();
			String sql = "SELECT * FROM TB_SITUA��ES";
			stmt = conexao.createStatement();
			rs = stmt.executeQuery(sql);
			return resutSetToTableModel(rs);
		} catch (Exception e) {
			return null;
		}		
	}
	public static TableModel resutSetToTableModel(ResultSet rs) {
		
		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int numerocolunas = metaData.getColumnCount();
			Vector columname = new Vector();
			for (int column = 0; column < numerocolunas; column++) {
				columname.addElement(metaData.getColumnLabel(column + 1));
			}
			Vector linhas = new Vector();
			while (rs.next()) {
				Vector novalinha = new Vector();
				for (int i = 1; i <= numerocolunas; i++) {
					novalinha.addElement(rs.getObject(i));
				}
				linhas.addElement(novalinha);
			}
			return new DefaultTableModel(linhas, columname);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}	
}