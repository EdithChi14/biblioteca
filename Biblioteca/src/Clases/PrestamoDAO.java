package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

import Conexion.Conexion;

public class PrestamoDAO {
	Connection con;
	Conexion cn=new Conexion();
	ResultSet rs;
	 
	
	public List<String> obtenerFechasDevolucionPorISBN(long isbn) {
	    List<String> fechas = new ArrayList<>();
	    String sql = "SELECT fechadevo FROM prestamo WHERE ISBN = ? AND estado = 'En préstamo'";
	    
	    try (Connection con = cn.getconectar();
	         PreparedStatement ps = con.prepareStatement(sql)) {
	        
	        ps.setLong(1, isbn);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            fechas.add(rs.getString("fechadevo"));
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al obtener fechas de devolución: " + e.getMessage());
	    }
	    
	    return fechas;
	}


	public boolean PrestamoActivo(long isbn, String idUsuario) {
	    String sql = "SELECT COUNT(*) FROM prestamo WHERE ISBN = ? AND IdUsuario = ? AND estado = 'En préstamo'";
	    try (Connection con = cn.getconectar();
	         PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setLong(1, isbn);
	        ps.setString(2, idUsuario);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            return rs.getInt(1) > 0;
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al verificar préstamo activo: " + e.getMessage());
	    }
	    return false;
	}

	
	public void marcarDevuelto(long isbn, String idUsuario) {
	    try {
	        Connection con = cn.getconectar();
	     // Actualiza el préstamo
	        String sql = "UPDATE prestamo SET estado = 'Devuelto' WHERE ISBN = ? AND IdUsuario = ? AND LOWER(estado) = 'En préstamo' LIMIT 1";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setLong(1, isbn);
	        ps.setString(2, idUsuario);
	        int afectados = ps.executeUpdate();

	        if (afectados == 0) {
	            JOptionPane.showMessageDialog(null, "No se encontró un préstamo activo para este usuario y libro.");
	            return;
	        }
	        // Aumentar stock del libro
	        String stockSql = "UPDATE libro SET stock = stock + 1 WHERE ISBN = ?";
	        PreparedStatement psStock = con.prepareStatement(stockSql);
	        psStock.setLong(1, isbn);
	        psStock.executeUpdate();
	        
	     // 3. Verificar si hay reservas en espera
	        verificarReservasPendientes(isbn);
	        
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al devolver libro: " + e.getMessage());
	    }
	}
	
	public void verificarReservasPendientes(long isbn) {
	    String sql = "SELECT r.nroreserva, r.usuario, p.nombre FROM reserva r " +
	                 "JOIN persona p ON r.usuario = p.idUsuario " +
	                 "WHERE r.estado = 'En Espera' AND r.ISBN = ?";

	    try (Connection con = cn.getconectar();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setLong(1, isbn);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            String nombreUsuario = rs.getString("nombre");
	            String mensaje = "🔔 ¡Notificación de reserva!\n"
	                    + "El libro con ISBN " + isbn + " fue devuelto.\n"
	                    + "Usuario: " + nombreUsuario + " tiene una reserva pendiente.";
	            JOptionPane.showMessageDialog(null, mensaje);
	        }

	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al verificar reservas: " + e.getMessage());
	    }
	}

	
	public boolean insertar(Prestamo pre) throws SQLException {
        UsuarioDAO usuDAO = new UsuarioDAO();
        LibroDAO libDAO = new LibroDAO();

        if (!usuDAO.existeUsuario(pre.getUsuario())) {
            throw new SQLException("El usuario ingresado no existe.");
        }

        String rol = usuDAO.obtenerRol(pre.getUsuario());
        if (!"usuario".equalsIgnoreCase(rol)) {
            throw new SQLException("Solo los usuarios con rol 'usuario' pueden hacer préstamos.");
        }

        if (!libDAO.existeLibro(pre.getIsbn())) {
            throw new SQLException("El libro con ese ISBN no existe.");
        }

        String sqlInsert = "INSERT INTO prestamo(IdUsuario, ISBN, fechapres, fechadevo) VALUES (?, ?, ?, ?)";
        String sqlUpdateStock = "UPDATE libro SET stock = stock - 1 WHERE ISBN = ? AND stock > 0";

        try (Connection con = cn.getconectar()) {
            con.setAutoCommit(false);

            try (PreparedStatement psStock = con.prepareStatement(sqlUpdateStock)) {
                psStock.setLong(1, pre.getIsbn());
                if (psStock.executeUpdate() == 0) {
                    con.rollback();
                    throw new SQLException("El libro no tiene stock disponible.");
                }
            }

            try (PreparedStatement psInsert = con.prepareStatement(sqlInsert)) {
                psInsert.setString(1, pre.getUsuario());
                psInsert.setLong(2, pre.getIsbn());
                psInsert.setString(3, pre.getFechaPrestamo());
                psInsert.setString(4, pre.getFechaDevolucion());

                if (psInsert.executeUpdate() == 0) {
                    con.rollback();
                    throw new SQLException("No se pudo registrar el préstamo.");
                }
            }

            con.commit();
            return true;

        } catch (SQLException e) {
            throw new SQLException("Error al insertar préstamo: " + e.getMessage(), e);
        }
    }
	
	public boolean modificar(long ISBN, String usu, String fechapre, String fechadev) throws SQLException {
        UsuarioDAO usuDAO = new UsuarioDAO();
        LibroDAO libDAO = new LibroDAO();

        if (!usuDAO.existeUsuario(usu)) {
            throw new SQLException("El usuario no existe.");
        }

        if (!libDAO.existeLibro(ISBN)) {
            throw new SQLException("El libro no existe.");
        }

        if (!PrestamoActivo(ISBN, usu)) {
            throw new SQLException("No hay préstamo activo para este usuario y libro.");
        }

        String sql = "UPDATE prestamo SET fechapres = ?, fechadevo = ? WHERE IdUsuario = ? AND ISBN = ?";
        try (Connection con = cn.getconectar();
             PreparedStatement ps1 = con.prepareStatement(sql)) {

            ps1.setString(1, fechapre);
            ps1.setString(2, fechadev);
            ps1.setString(3, usu);
            ps1.setLong(4, ISBN);
            return ps1.executeUpdate() > 0;
        }
    }
	
	
	
	public boolean eliminar(int nroprestamo) throws SQLException {
        String sql = "DELETE FROM prestamo WHERE nroprestamo = ?";
        try (Connection con = cn.getconectar();
             PreparedStatement ps1 = con.prepareStatement(sql)) {
            ps1.setInt(1, nroprestamo);
            return ps1.executeUpdate() > 0;
        }
    }
	   
	   
	   public List<Prestamo> listarPrestamos() {
		    List<Prestamo> listaPrestamos = new ArrayList<>();
		    String sql = "SELECT p.nroprestamo, u.nombre AS nombre_usuario, l.titulo AS nombre_libro, p.fechapres, p.fechadevo, p.ISBN, p.IdUsuario, p.estado " +
		                 "FROM prestamo p " +
		                 "JOIN libro l ON p.ISBN = l.ISBN " +
		                 "JOIN persona u ON p.IdUsuario = u.idUsuario";
		    PreparedStatement ps1;

		    try {
		        con = cn.getconectar();
		        ps1 = con.prepareStatement(sql);
		        rs = ps1.executeQuery();

		        while (rs.next()) {
		            
		            Prestamo prestamo = new Prestamo(
		 
		            	rs.getInt("nroprestamo"),
		                rs.getString("nombre_usuario"),  
		                rs.getLong("ISBN"),              
		                rs.getString("fechapres"),
		                rs.getString("fechadevo")
		            );
 
		            
		            prestamo.setNombreLibro(rs.getString("nombre_libro"));
		            prestamo.setIdusu(rs.getString("IdUsuario"));
		            prestamo.setEstado(rs.getString("estado"));
		            listaPrestamos.add(prestamo);
		        }

		    } catch (SQLException e) {
		        JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage());
		    }

		    return listaPrestamos;
		}



}
