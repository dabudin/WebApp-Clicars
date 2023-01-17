package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexion.Conexion;
import model.Coche;

public class DAOCoche {
	
	public ArrayList<Coche> getCoches(String search, String orden) {
		
		ArrayList<Coche> coches = new ArrayList<Coche>();
		
		Connection con = Conexion.conecta();
		ResultSet rs;
		Statement st;
		
		try {
					
			if (orden.equals("") || orden == null) {
				orden = "null";
			}
				
			String sql = "select * from coche where lower(nombre) like lower('%"+search+"%')"
					+  "or lower(modelo) like lower('%"+search+"%') or lower(anio) like lower('%"+search+"%') ORDER BY "+orden+"";
			
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			while (rs.next()) {	
				Coche coche = new Coche();
				coche.setId(rs.getInt("id"));
				coche.setMarca(rs.getInt("marca"));
				coche.setFoto(rs.getString("foto"));
				coche.setNombre(rs.getString("nombre"));
				coche.setModelo(rs.getString("modelo"));
				coche.setPrecio(rs.getInt("precio"));
				coche.setPrecioantes(rs.getInt("precioantes"));
				coche.setAnio(rs.getInt("anio"));
				coche.setKm(rs.getInt("km"));
				coche.setCv(rs.getInt("cv"));
				coche.setFav(rs.getInt("fav"));
				
				coches.add(coche);
			}
			
			rs.close();
			st.close();
			con.close();
			
		} catch (SQLException e) {
			System.out.println("Error al acceder a la BDs: " + e.getMessage());
		}
		
		return coches;
	}
	
	public ArrayList<Coche> getCochesByMarca(String marca, String orden) {
		
		ArrayList<Coche> coches = new ArrayList<Coche>();
		
		Connection con = Conexion.conecta();
		Statement st;
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			
			if (orden.equals("")  || orden == null) {
				orden = "null";
			}
			
			String sql;
			int marcaid = 0;	
			
			st = con.createStatement();	
			sql = "select * from coche where marca=? ORDER BY "+orden+"";
			ps = con.prepareStatement(sql);
			
			if (marca.equals("") || marca == null) {			
				
				sql = "select * from coche ORDER BY "+orden+"";
				rs = st.executeQuery(sql);
				
			} else {
				
				marcaid = Integer.valueOf(marca);
				
				ps.setInt(1, marcaid);
				rs = ps.executeQuery();		
				
			}
			
			while (rs.next()) {	
				Coche coche = new Coche();
				coche.setId(rs.getInt("id"));
				coche.setMarca(rs.getInt("marca"));
				coche.setFoto(rs.getString("foto"));
				coche.setNombre(rs.getString("nombre"));
				coche.setModelo(rs.getString("modelo"));
				coche.setPrecio(rs.getInt("precio"));
				coche.setPrecioantes(rs.getInt("precioantes"));
				coche.setAnio(rs.getInt("anio"));
				coche.setKm(rs.getInt("km"));
				coche.setCv(rs.getInt("cv"));
				coche.setFav(rs.getInt("fav"));
				
				coches.add(coche);
			}
			
			rs.close();
			st.close();
			ps.close();
			con.close();
			
		} catch (SQLException e) {
			System.out.println("Error al acceder a la BDs: " + e.getMessage());
		}
		
		return coches;
	}
	
	public void updateFav(int fav, int id) {
		
		Connection con = Conexion.conecta();
		PreparedStatement ps;
		
		try {
			
			String sql = "update coche set fav=? where id=?";
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, fav);
			ps.setInt(2, id);
			ps.executeUpdate();
			
			ps.close();
			con.commit();
			con.close();
			
		} catch (SQLException e) {
			
			System.out.println("Error al acceder a la BDs: " + e.getMessage());
			
		}
		
	}

}
