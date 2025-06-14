package Clases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;



import Conexion.Conexion;

public class CategoriaDAO {
	Connection con;
    Conexion cn = new Conexion();
    ResultSet rs;
    PreparedStatement ps = null;
    
    
    public void cargarCategorias(JComboBox<Categoria> comboBox) {
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        
        
        try {
        	String sql = "SELECT * FROM categoria ORDER BY nombre";
            con = cn.getconectar();
            ps1 = con.prepareStatement(sql);
            rs = ps1.executeQuery();
            
            comboBox.removeAllItems();
            
            while (rs.next()) {
                Categoria cat = new Categoria();
                cat.setId_categoria(rs.getInt("id_categoria"));
                cat.setNombre(rs.getString("nombre"));
                
                comboBox.addItem(cat);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar categorías: " + e.getMessage());
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

        // Insertar categoría
        public boolean insertarCategoria(Categoria categoria) {
            try {
                String sql = "INSERT INTO categoria (nombre) VALUES (?)";
                con = cn.getconectar();
                ps = con.prepareStatement(sql);
                ps.setString(1, categoria.getNombre());

                return ps.executeUpdate() > 0;

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al insertar categoría: " + e.getMessage());
                return false;
            } 
        }

        // Modificar categoría
        public boolean modificarCategoria(Categoria categoria) {
            try {
                String sql = "UPDATE categoria SET nombre = ? WHERE id_categoria = ?";
                con = cn.getconectar();
                ps = con.prepareStatement(sql);
                ps.setString(1, categoria.getNombre());
                ps.setInt(2, categoria.getId_categoria());

                return ps.executeUpdate() > 0;

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al modificar categoría: " + e.getMessage());
                return false;
            } 
        }

        // Eliminar categoría
        public boolean eliminarCategoria(int idCategoria) {
            try {
                String sql = "DELETE FROM categoria WHERE id_categoria = ?";
                con = cn.getconectar();
                ps = con.prepareStatement(sql);
                ps.setInt(1, idCategoria);

                return ps.executeUpdate() > 0;

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al eliminar categoría: " + e.getMessage());
                return false;
            } 
        }

     // Listar todas las categorías
        public List<Categoria> listarCategorias() {
            List<Categoria> lista = new ArrayList<>();

            try {
                String sql = "SELECT * FROM categoria";
                con = cn.getconectar();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Categoria cat = new Categoria();
                    cat.setId_categoria(rs.getInt("id_categoria"));
                    cat.setNombre(rs.getString("nombre"));
                    lista.add(cat);
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al listar categorías: " + e.getMessage());
            } 

            return lista;
        }
 }

    

