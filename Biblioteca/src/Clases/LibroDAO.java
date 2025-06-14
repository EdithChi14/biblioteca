package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.mysql.jdbc.Statement;


import Conexion.Conexion;


public class LibroDAO {
	
	Connection con;
	Conexion cn=new Conexion();
	ResultSet rs;
	
	public int obtenerStockPorISBN(long isbn) {
	    int stock = 0;
	    String sql = "SELECT stock FROM libro WHERE ISBN = ?";
	    try (Connection con = cn.getconectar();
	         PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setLong(1, isbn);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            stock = rs.getInt("stock");
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al obtener stock: " + e.getMessage());
	    }
	    return stock;
	}
	
	public List<Libro> buscarLibros(String tituloParcial) {
	    List<Libro> libros = new ArrayList<>();
	    String sql = "SELECT ISBN, titulo FROM libro WHERE LOWER(titulo) LIKE ? ORDER BY titulo LIMIT 10";

	    try (Connection conn = cn.getconectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, "%" + tituloParcial.toLowerCase() + "%");

	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                Libro libro = new Libro();
	                libro.setIsbn(rs.getLong("ISBN"));
	                libro.setTitulo(rs.getString("titulo"));
	                libros.add(libro);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return libros;
	}
	public Libro buscarLibroPorTituloExacto(String tituloExacto) {
	    Libro libro = null;
	    String sql = "SELECT ISBN, titulo FROM libro WHERE LOWER(titulo) = ? LIMIT 1";

	    try (Connection conn = cn.getconectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, tituloExacto.toLowerCase());
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                libro = new Libro();
	                libro.setIsbn(rs.getLong("ISBN"));
	                libro.setTitulo(rs.getString("titulo"));
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return libro;
	}




	
	
	

	
	public boolean existeLibro(long isbn) throws SQLException {
	    String sql = "SELECT COUNT(*) FROM libro WHERE ISBN = ?";
	    try (Connection conn = cn.getconectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setLong(1, isbn);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt(1) > 0;
	            }
	        }
	    }
	    return false;
	}
	
	// Valida si un libro está asociado a préstamos o reservas activas (evita eliminación)
	public boolean estaEnUso(long isbn) throws SQLException {
	    String sql = """
	        SELECT EXISTS (
	            SELECT 1 FROM prestamo WHERE ISBN = ?
	            UNION
	            SELECT 1 FROM reserva WHERE ISBN = ?
	        ) AS enUso
	        """;

	    try (Connection conn = cn.getconectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setLong(1, isbn);
	        ps.setLong(2, isbn);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                return rs.getBoolean("enUso");
	            }
	        }
	    }
	    return false;
	}

	
	public boolean LibroConCategoria(int idCategoria) throws SQLException {
	    String sql = "SELECT COUNT(*) FROM libro WHERE id_categoria = ?";
	    try (Connection con = cn.getconectar(); PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setInt(1, idCategoria);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt(1) > 0;
	            }
	        }
	    }
	    return false;
	}
	public boolean LibroConEditorial(int idEditorial) throws SQLException {
	    String sql = "SELECT COUNT(*) FROM libro WHERE id_editorial = ?";
	    try (Connection con = cn.getconectar(); PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setInt(1, idEditorial);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt(1) > 0;
	            }
	        }
	    }
	    return false;
	}
	private int buscarIdCategoria(String nombreCat) throws SQLException {
	    String sql = "SELECT id_categoria FROM categoria WHERE nombre = ?";
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setString(1, nombreCat);
	    ResultSet rs = ps.executeQuery();
	    if (rs.next()) {
	        return rs.getInt("id_categoria");
	    }
	    throw new SQLException("Categoría no encontrada: " + nombreCat);
	}
	
	private int buscarIdEditorial(String nombreEdit) throws SQLException {
	    String sql = "SELECT id_editorial FROM editorial WHERE nombre = ?";
	    PreparedStatement ps = con.prepareStatement(sql);
	    ps.setString(1, nombreEdit);
	    ResultSet rs = ps.executeQuery();
	    if (rs.next()) {
	        return rs.getInt("id_editorial");
	    }
	    throw new SQLException("Editorial no encontrada: " + nombreEdit);
	}
	
	public boolean insertar(Libro lib) {
	    PreparedStatement ps1 = null;
	    String sql;

	    try {
	        con = cn.getconectar();

	        int idCat = buscarIdCategoria(lib.getCategoria());
	        int idEdit = buscarIdEditorial(lib.getEditorial());

	        sql = "INSERT INTO libro(ISBN, titulo, autor, id_categoria, id_editorial, Stock) VALUES (?, ?, ?, ?, ?, ?)";
	        ps1 = con.prepareStatement(sql);
	        ps1.setLong(1, lib.getIsbn());
	        ps1.setString(2, lib.getTitulo());
	        ps1.setString(3, lib.getAutor());
	        ps1.setInt(4, idCat);
	        ps1.setInt(5, idEdit);
	        ps1.setInt(6, lib.getStock());

	        return ps1.executeUpdate() > 0;

	    } catch (SQLException e) {
	        throw new RuntimeException("Error al insertar libro: " + e.getMessage(), e);
	    } finally {
	        try {
	            if (ps1 != null) ps1.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            throw new RuntimeException("Error al cerrar conexión: " + e.getMessage(), e);
	        }
	    }
	}



	
	public boolean modificar(long ISBN, String libro, String autor, int id_categoria, int id_editorial, int stock) {
		PreparedStatement ps1 = null;
	    String sql;

	    try {
	        con = cn.getconectar();

	        if (estaEnUso(ISBN)) return false;

	        sql = "UPDATE libro SET titulo=?, autor=?, id_categoria=?, id_editorial=?, Stock=? WHERE ISBN=?";
	        ps1 = con.prepareStatement(sql);
	        ps1.setString(1, libro);
	        ps1.setString(2, autor);
	        ps1.setInt(3, id_categoria);
	        ps1.setInt(4, id_editorial);
	        ps1.setInt(5, stock);
	        ps1.setLong(6, ISBN);

	        return ps1.executeUpdate() > 0;

	    } catch (SQLException e) {
	        throw new RuntimeException("Error al modificar libro: " + e.getMessage(), e);
	    } finally {
	        try {
	            if (ps1 != null) ps1.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            throw new RuntimeException("Error al cerrar conexión: " + e.getMessage(), e);
	        }
	    }
	}



	public boolean eliminar(long isbn) {
	    PreparedStatement ps1 = null;
	    String sql;

	    try {
	        con = cn.getconectar();

	        if (estaEnUso(isbn)) return false;

	        sql = "DELETE FROM libro WHERE ISBN=?";
	        ps1 = con.prepareStatement(sql);
	        ps1.setLong(1, isbn);

	        return ps1.executeUpdate() > 0;

	    } catch (SQLException e) {
	        throw new RuntimeException("Error al eliminar libro: " + e.getMessage(), e);
	    } finally {
	        try {
	            if (ps1 != null) ps1.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            throw new RuntimeException("Error al cerrar conexión: " + e.getMessage(), e);
	        }
	    }
	}

 
	 
	public List<Libro> ListarLib() {
	    List<Libro> ListaLib = new ArrayList<>();
	    String sql = "SELECT l.ISBN, l.titulo, l.autor,c.nombre as categoria_nombre, " +
                " e.nombre as editorial_nombre, l.Stock " +
                "FROM libro l " +
                "JOIN categoria c ON l.id_categoria = c.id_categoria " +
                "JOIN editorial e ON l.id_editorial = e.id_editorial";
	    PreparedStatement ps1;

	    try {
	        con = cn.getconectar();
	        ps1 = con.prepareStatement(sql);
	        rs = ps1.executeQuery();
	        while (rs.next()) {
	            Libro lib = new Libro();
	            lib.setIsbn(rs.getLong("ISBN"));  
	            lib.setTitulo(rs.getString("titulo"));
	            lib.setAutor(rs.getString("autor"));
	            lib.setCategoria(rs.getString("categoria_nombre"));
	            lib.setEditorial(rs.getString("editorial_nombre"));
	            lib.setStock(rs.getInt("Stock"));
	            ListaLib.add(lib);
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage());
	    }
	    return ListaLib;
	}
	public List<String> obtenerISBN() {
	    List<String> lista = new ArrayList<>();
	    try (Connection cn = Conexion.getconectar();
	         PreparedStatement ps = (PreparedStatement) cn.prepareStatement("SELECT isbn FROM libro");
	         ResultSet rs = ps.executeQuery()) {
	        while (rs.next()) {
	            lista.add(rs.getString("isbn").replaceAll("[\\s-]", ""));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return lista;
	}
	
	   
}
