package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sound.sampled.ReverbType;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

import Conexion.Conexion;

public class ReservaDAO {
	Connection con;
	Conexion cn=new Conexion();
	ResultSet rs;
	
	public boolean existeReservaActiva(String idUsuario, long isbn) {
	    String sql = "SELECT COUNT(*) FROM reserva WHERE usuario = ? AND ISBN = ? AND estado = 'En Espera'";
	    try (Connection con = cn.getconectar();
	         PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setString(1, idUsuario);
	        ps.setLong(2, isbn);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            return rs.getInt(1) > 0;
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al verificar reserva duplicada: " + e.getMessage());
	    }
	    return false;
	}

	
	public void ReservaAtendida(int nroReserva) throws SQLException {
	    String sql = "UPDATE reserva SET estado = 'Atendida' WHERE nroreserva = ?";
	    try (Connection con = cn.getconectar();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setInt(1, nroReserva);
	        ps.executeUpdate();
	    }catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al actualizar estado de reserva: " + e.getMessage());
	    }
	}
	
	
	public boolean insertar(Reserva re) throws SQLException {
		 LibroDAO libDAO = new LibroDAO();

		    // Validación: el libro debe existir
		    if (!libDAO.existeLibro(re.getIsbn())) {
		        throw new SQLException("El libro con ese ISBN no existe.");
		    }

		    // Validación: si el libro tiene stock, no se puede reservar
		    String sqlStock = "SELECT stock FROM libro WHERE ISBN = ?";
		    String sqlInsert = "INSERT INTO reserva (usuario, ISBN, fechareserva) VALUES (?, ?, ?)";

		    try (Connection con = cn.getconectar();
		         PreparedStatement psStock = con.prepareStatement(sqlStock)) {

		        psStock.setLong(1, re.getIsbn());
		        try (ResultSet rs = psStock.executeQuery()) {
		            if (rs.next()) {
		                int stock = rs.getInt("stock");
		                if (stock > 0) {
		                    throw new SQLException("Este libro tiene stock disponible. No se puede reservar.");
		                }
		            } else {
		                throw new SQLException("El libro no existe en la base de datos.");
		            }
		        }

		        try (PreparedStatement psInsert = con.prepareStatement(sqlInsert)) {
		            psInsert.setString(1, re.getIdusu());  // Se usa ID directamente desde el combo
		            psInsert.setLong(2, re.getIsbn());
		            psInsert.setString(3, re.getFechaReserva());
		            return psInsert.executeUpdate() > 0;
		        }

		    } catch (SQLException e) {
		        throw new SQLException(e.getMessage(), e);
		    }
    }
	
	
	 public boolean modificar(long isbn, String usuario, String fecha) throws SQLException {
	        String sql = "UPDATE reserva SET fechareserva = ? WHERE usuario = ? AND ISBN = ?";
	        try (Connection con = cn.getconectar();
	             PreparedStatement ps1 = con.prepareStatement(sql)) {
	            ps1.setString(1, fecha);
	            ps1.setString(2, usuario);
	            ps1.setLong(3, isbn);
	            return ps1.executeUpdate() > 0;
	        }
	    }

	    public boolean eliminar(int nroreserva) throws SQLException {
	        String sql = "DELETE FROM reserva WHERE nroreserva = ?";
	        try (Connection con = cn.getconectar();
	             PreparedStatement ps1 = con.prepareStatement(sql)) {
	            ps1.setInt(1, nroreserva);
	            return ps1.executeUpdate() > 0;
	        }
	    }

	
	
	
	
	
	
	public List<Reserva> listarReserva() {
	    List<Reserva> listaReservas = new ArrayList<>();
	    String sql = "SELECT r.nroreserva, p.nombre AS nombre_usuario, l.titulo AS nombre_libro, r.fechareserva, r.ISBN, r.usuario, r.estado " +
	                 "FROM reserva r " +
	                 "JOIN libro l ON r.ISBN = l.ISBN " +
	                 "JOIN persona p ON r.usuario = p.idUsuario";
	    PreparedStatement ps1;

	    try {
	        con = cn.getconectar();
	        ps1 = con.prepareStatement(sql);
	        rs = ps1.executeQuery();

	        while (rs.next()) {
	            
	           
	            Reserva reserva=new Reserva(
	            		rs.getInt("nroreserva"),
		                rs.getString("nombre_usuario"),  
		                rs.getLong("ISBN"),              
		                rs.getString("fechareserva")
	            		);

	            
	            reserva.setNombreLibro(rs.getString("nombre_libro"));
	            reserva.setIdusu(rs.getString("usuario"));
	            reserva.setEstado(rs.getString("estado"));
	            listaReservas.add(reserva);
	        }

	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage());
	    }

	    return listaReservas;
	}
	
	
	 
	   

}
