package Clases;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import Conexion.Conexion;

public class UsuarioDAO {
	Connection con;
	Conexion cn=new Conexion(); 
	ResultSet rs;
	
	// Verificar si el ID ya existe
	public boolean existeID(String idUsuario) throws SQLException {
	    String sql = "SELECT COUNT(*) FROM persona WHERE idUsuario = ?";
	    try (Connection con = cn.getconectar(); PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setString(1, idUsuario);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) return rs.getInt(1) > 0;
	    }
	    return false;
	}

	// Verificar si el correo ya existe
	public boolean existeCorreo(String correo) throws SQLException {
	    String sql = "SELECT COUNT(*) FROM persona WHERE correo = ?";
	    try (Connection con = cn.getconectar(); PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setString(1, correo);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) return rs.getInt(1) > 0;
	    }
	    return false;
	}

	// Verificar si el teléfono ya existe
	public boolean existeTelefono(String telefono) throws SQLException {
	    String sql = "SELECT COUNT(*) FROM persona WHERE telefono = ?";
	    try (Connection con = cn.getconectar(); PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setString(1, telefono);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) return rs.getInt(1) > 0;
	    }
	    return false;
	}

	
	public Usuario buscarUsuarioPorNombreExacto(String nombreExacto) {
	    Usuario usu = null;
	    String sql = """
	        SELECT p.idUsuario, p.nombre
	        FROM persona p
	        INNER JOIN usuario u ON p.idUsuario = u.idUsuario
	        WHERE p.nombre = ? AND p.estado = 'activo' AND p.rol = 'usuario'
	        LIMIT 1
	    """;

	    try (Connection conn = cn.getconectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, nombreExacto);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                usu = new Usuario();
	                usu.setIdUsuario(rs.getString("idUsuario"));
	                usu.setNombre(rs.getString("nombre"));
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return usu;
	}

	
	public void cargarUsuario(JComboBox<Usuario> comboBox) {
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        
        
        try {
        	String sql = """
                    SELECT p.idUsuario AS idUsuario, p.nombre
                    FROM persona p
                    INNER JOIN usuario u ON p.idUsuario = u.idUsuario
                    WHERE p.estado = 'activo'
                    ORDER BY p.nombre
                """;
            con = cn.getconectar();
            ps1 = con.prepareStatement(sql);
            rs = ps1.executeQuery();
            
            comboBox.removeAllItems();
            
            while (rs.next()) {
            	Usuario usu=new Usuario();
            	usu.setIdUsuario(rs.getString("idUsuario"));
            	usu.setNombre(rs.getString("nombre"));
                             
                comboBox.addItem(usu);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar usuario: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps1 != null) ps1.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar recursos: " + e.getMessage());
            }
        }
    }
	
	public boolean existeUsuario(String idusuario) throws SQLException {
	    if (!idusuario.matches("\\d{8}")) {
	        return false; 
	    }
	    String sql = "SELECT COUNT(*) FROM usuario WHERE idUsuario = ?";
	    try (Connection conn = cn.getconectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, idusuario);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt(1) > 0;
	            }
	        }
	    }
	    return false;
	}
	
	public String obtenerRol(String idUsuario) {
		if (!idUsuario.matches("\\d{8}")) {
		    return null;
		}
	    String sql = "SELECT rol FROM persona WHERE idUsuario = ?";
	    try (PreparedStatement ps = cn.getconectar().prepareStatement(sql)) {
	        ps.setString(1, idUsuario);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) return rs.getString("rol");
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al obtener rol: " + e.getMessage());
	    }
	    return null;
	} 
	
	public String autenticarUsuario(String idUsuario, String contrasena) {
	    String rol = null;
	    if (!idUsuario.matches("\\d{8}")) {
	        return null;
	    }
	    try (Connection con = cn.getconectar()) {
	        // Verificar si el usuario existe en persona
	        String sql = "SELECT rol FROM persona WHERE idUsuario = ?";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, idUsuario);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            rol = rs.getString("rol");

	            // Si es administrador, verificar pin
	            if ("admin".equals(rol)) {
	                String sqlAdmin = "SELECT pin_numerico FROM administrador WHERE idUsuario = ?";
	                PreparedStatement psAdm = con.prepareStatement(sqlAdmin);
	                psAdm.setString(1, idUsuario);
	                ResultSet rsAdm = psAdm.executeQuery();

	                if (rsAdm.next()) {
	                    String pin = rsAdm.getString("pin_numerico");
	                    if (!contrasena.equals(pin)) {
	                        return null; // PIN incorrecto
	                    }
	                } else {
	                    return null; // No tiene registro en tabla administrador
	                }
	            }
	        } else {
	            return null; // Usuario no existe
	        }

	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al autenticar: " + e.getMessage());
	        return null;
	    }

	    return rol; // Devuelve "admin" o "usuario" si todo salió bien
	}

	
	public boolean insertar(Usuario usu) {
	    try (Connection con = cn.getconectar()) {

	        // Validar ID
	        if (!usu.getIdUsuario().matches("\\d{8}")) {
	            JOptionPane.showMessageDialog(null, "El ID debe tener 8 dígitos numéricos.");
	            return false;
	        }

	        // Validar correo
	        if (!usu.getCorreo().matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
	            JOptionPane.showMessageDialog(null, "Formato de correo inválido.");
	            return false;
	        }

	        // Validar PIN si es admin
	        if ("admin".equals(usu.getRol())) {
	            if (usu.getContra() == null || !usu.getContra().matches("\\d{6}")) {
	                JOptionPane.showMessageDialog(null, "El PIN debe tener 6 dígitos numéricos.");
	                return false;
	            }
	        }

	        // Validar existencia de ID
	        if (existeID(usu.getIdUsuario())) {
	            JOptionPane.showMessageDialog(null, "El ID ya existe.");
	            return false;
	        }

	        // Validar correo duplicado
	        if (existeCorreo(usu.getCorreo())) {
	            JOptionPane.showMessageDialog(null, "El correo ya está en uso.");
	            return false;
	        }

	        // Validar teléfono duplicado
	        if (existeTelefono(usu.getTelefono())) {
	            JOptionPane.showMessageDialog(null, "El teléfono ya está en uso.");
	            return false;
	        }

	        // Insertar en tabla persona
	        String sqlPersona = "INSERT INTO persona (idUsuario, nombre, correo, direccion, telefono, rol) VALUES (?, ?, ?, ?, ?, ?)";
	        PreparedStatement ps = con.prepareStatement(sqlPersona);
	        ps.setString(1, usu.getIdUsuario());
	        ps.setString(2, usu.getNombre());
	        ps.setString(3, usu.getCorreo());
	        ps.setString(4, usu.getDireccion());
	        ps.setString(5, usu.getTelefono());
	        ps.setString(6, usu.getRol());
	        ps.executeUpdate();

	        // Insertar en tabla correspondiente según el rol
	        if ("admin".equals(usu.getRol())) {
	            String sqlAdmin = "INSERT INTO administrador (idUsuario, pin_numerico) VALUES (?, ?)";
	            PreparedStatement psAdmin = con.prepareStatement(sqlAdmin);
	            psAdmin.setString(1, usu.getIdUsuario());
	            psAdmin.setString(2, usu.getContra());
	            psAdmin.executeUpdate();
	        } else {
	            String sqlUsu = "INSERT INTO usuario (idUsuario) VALUES (?)";
	            PreparedStatement psUsu = con.prepareStatement(sqlUsu);
	            psUsu.setString(1, usu.getIdUsuario());
	            psUsu.executeUpdate();
	        }

	        return true;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error en la base de datos: " + e.getMessage());
	        return false;
	    }
	}

	
	public boolean modificar(Usuario usu) {
	    try (Connection con = cn.getconectar()) {

	        // Validar formato del ID
	        if (!usu.getIdUsuario().matches("\\d{8}")) {
	            JOptionPane.showMessageDialog(null, "El ID debe tener 8 dígitos.");
	            return false;
	        }

	        // Validar formato del correo
	        if (!usu.getCorreo().matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
	            JOptionPane.showMessageDialog(null, "Formato de correo inválido.");
	            return false;
	        }

	        // Validar formato del PIN si es admin
	        if ("admin".equals(usu.getRol())) {
	            if (usu.getContra() == null || !usu.getContra().matches("\\d{6}")) {
	                JOptionPane.showMessageDialog(null, "El PIN debe tener 6 dígitos.");
	                return false;
	            }
	        }

	        if (existeID(usu.getIdUsuario())) {
	            JOptionPane.showMessageDialog(null, "El ID ya existe.");
	            return false;
	        }

	        if (existeCorreo(usu.getCorreo())) {
	            JOptionPane.showMessageDialog(null, "El correo ya está en uso.");
	            return false;
	        }

	        if (existeTelefono(usu.getTelefono())) {
	            JOptionPane.showMessageDialog(null, "El teléfono ya está en uso.");
	            return false;
	        }

	        // Actualizar datos de persona
	        String sql = "UPDATE persona SET nombre=?, correo=?, direccion=?, telefono=? WHERE idUsuario=?";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, usu.getNombre());
	        ps.setString(2, usu.getCorreo());
	        ps.setString(3, usu.getDireccion());
	        ps.setString(4, usu.getTelefono());
	        ps.setString(5, usu.getIdUsuario());

	        int res = ps.executeUpdate();
	        if (res == 0) {
	            JOptionPane.showMessageDialog(null, "No se pudo modificar el usuario.");
	            return false;
	        }

	        // Actualizar PIN si es administrador
	        if ("admin".equals(usu.getRol())) {
	            String sqlPin = "UPDATE administrador SET pin_numerico = ? WHERE idUsuario = ?";
	            PreparedStatement psPin = con.prepareStatement(sqlPin);
	            psPin.setString(1, usu.getContra());
	            psPin.setString(2, usu.getIdUsuario());
	            psPin.executeUpdate();
	        }

	        return true;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error en la base de datos: " + e.getMessage());
	        return false;
	    }
	}






	public boolean desactivar(String idUsuario) {
	   

	    try (Connection con = cn.getconectar()) {
	        // 1. Verificar si tiene reservas activas
	        String sqlCheckReserva = "SELECT COUNT(*) FROM reserva WHERE usuario = ?";
	        try (PreparedStatement psReserva = con.prepareStatement(sqlCheckReserva)) {
	            psReserva.setString(1, idUsuario);
	            ResultSet rs = psReserva.executeQuery();
	            if (rs.next() && rs.getInt(1) > 0) return false;
	        }

	        // 2. Verificar si tiene préstamos activos
	        String sqlCheckPrestamo = "SELECT COUNT(*) FROM prestamo WHERE IdUsuario = ?";
	        try (PreparedStatement psPrestamo = con.prepareStatement(sqlCheckPrestamo)) {
	            psPrestamo.setString(1, idUsuario);
	            ResultSet rs = psPrestamo.executeQuery();
	            if (rs.next() && rs.getInt(1) > 0) return false;
	        }

	        // 3. Verificar si existe y obtener rol
	        String rol = null;
	        String sqlRol = "SELECT rol FROM persona WHERE idUsuario = ?";
	        try (PreparedStatement psRol = con.prepareStatement(sqlRol)) {
	            psRol.setString(1, idUsuario);
	            ResultSet rs = psRol.executeQuery();
	            if (rs.next()) {
	                rol = rs.getString("rol");
	            } else {
	                return false; // No existe
	            }
	        }

	        // 4. Actualizar estado en tabla persona
	        String sqlDesactivar = "UPDATE persona SET estado = 'inactivo' WHERE idUsuario = ?";
	        try (PreparedStatement psUpdate = con.prepareStatement(sqlDesactivar)) {
	            psUpdate.setString(1, idUsuario);
	            return psUpdate.executeUpdate() > 0;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public boolean activar(String idUsuario) {

	    try (Connection con = cn.getconectar()) {
	        // 1. Verificar si el usuario existe
	        String sqlExiste = "SELECT COUNT(*) FROM persona WHERE idUsuario = ?";
	        try (PreparedStatement psExiste = con.prepareStatement(sqlExiste)) {
	            psExiste.setString(1, idUsuario);
	            ResultSet rs = psExiste.executeQuery();
	            if (rs.next() && rs.getInt(1) == 0) {
	                return false; // No existe
	            }
	        }

	        // 2. Activar usuario (cambiar estado)
	        String sqlActivar = "UPDATE persona SET estado = 'activo' WHERE idUsuario = ?";
	        try (PreparedStatement psUpdate = con.prepareStatement(sqlActivar)) {
	            psUpdate.setString(1, idUsuario);
	            return psUpdate.executeUpdate() > 0;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}


    // Método para listar todos los usuarios
	public List<Usuario> listarUsu() {
	    List<Usuario> listaUsu = new ArrayList<>();
	    String sql = "SELECT * FROM persona";

	    try (Connection con = cn.getconectar();
	         PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            Usuario usu = new Usuario();
	            usu.setIdUsuario(rs.getString("idUsuario"));
	            usu.setNombre(rs.getString("nombre"));
	            usu.setCorreo(rs.getString("correo"));
	            usu.setDireccion(rs.getString("direccion"));
	            usu.setTelefono(rs.getString("telefono"));
	            usu.setEstado(rs.getString("estado"));

	            String rolDB = rs.getString("rol");
	            usu.setRol("admin".equals(rolDB) ? "Administrador" : "Usuario");

	            // Si es administrador, buscar su pin
	            if ("admin".equals(rolDB)) {
	                String sqlPin = "SELECT pin_numerico FROM administrador WHERE idUsuario = ?";
	                PreparedStatement psPin = con.prepareStatement(sqlPin);
	                psPin.setString(1, usu.getIdUsuario());
	                ResultSet rsPin = psPin.executeQuery();
	                if (rsPin.next()) {
	                	
	                    usu.setContra(rsPin.getString("pin_numerico"));
	                }
	                rsPin.close();
	                psPin.close();
	            }

	            listaUsu.add(usu);
	        }

	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage());
	    }

	    return listaUsu;
	}
}