package daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexion.Conexion;
import model.Marca;

public class DAOMarca {
	
	public ArrayList<Marca> getMarcas() {
		
		ArrayList<Marca> marcas = new ArrayList<Marca>();
		
		Connection con = Conexion.conecta();
		Statement st;
		ResultSet rs;
		
		try {
				
			String sql = "select * from marca";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			while (rs.next()) {	
				Marca marca = new Marca();
				marca.setId(rs.getInt("id"));
				marca.setNombre(rs.getString("nombre"));
				
				marcas.add(marca);
			}
			
			rs.close();
			st.close();
			con.close();
			
		} catch (SQLException e) {
			System.out.println("Error al acceder a la BDs: " + e.getMessage());
		}
		
		return marcas;
	}
}
