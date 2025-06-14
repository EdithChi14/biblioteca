package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import Conexion.Conexion;

public class EditorialDAO {
	Connection con;
    Conexion cn = new Conexion();
    ResultSet rs;
    PreparedStatement ps = null;
    
    /**
     * Carga todas las editoriales en un JComboBox
     * @param comboBox El JComboBox donde se cargarán las editoriales
     */
    public void cargarEditoriales(JComboBox<Editorial> comboBox) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        
        try {
        	String sql = "SELECT * FROM editorial ORDER BY nombre";
            con = cn.getconectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            
            comboBox.removeAllItems();
                                        
           
            while (rs.next()) {
                Editorial ed = new Editorial();
                ed.setId_edit(rs.getInt("id_editorial"));
                ed.setNombre(rs.getString("nombre"));
                
                
                comboBox.addItem(ed);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar editoriales: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar recursos: " + e.getMessage());
            }
        }
    }
    
    
 // Insertar editorial
    public boolean insertarEditorial(Editorial editorial) {
    	String sql = "INSERT INTO editorial (nombre) VALUES (?)";

        try (Connection con = cn.getconectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, editorial.getNombre());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar editorial: " + e.getMessage(), e);
        }
    }

    // Modificar editorial
    public boolean modificarEditorial(Editorial editorial) {
    	 String sql = "UPDATE editorial SET nombre = ? WHERE id_editorial = ?";

         try (Connection con = cn.getconectar();
              PreparedStatement ps = con.prepareStatement(sql)) {

             ps.setString(1, editorial.getNombre());
             ps.setInt(2, editorial.getId_edit());
             return ps.executeUpdate() > 0;

         } catch (SQLException e) {
             throw new RuntimeException("Error al modificar editorial: " + e.getMessage(), e);
         }
    }

    // Eliminar editorial
    public boolean eliminarEditorial(int idEditorial) {
    	 String sql = "DELETE FROM editorial WHERE id_editorial = ?";

         try (Connection con = cn.getconectar();
              PreparedStatement ps = con.prepareStatement(sql)) {

             ps.setInt(1, idEditorial);
             return ps.executeUpdate() > 0;

         } catch (SQLException e) {
             throw new RuntimeException("Error al eliminar editorial: " + e.getMessage(), e);
         }
     
    }

    // Listar todas las editoriales
    public List<Editorial> listarEditoriales() {
        List<Editorial> lista = new ArrayList<>();

        try {
            String sql = "SELECT * FROM editorial";
            con = cn.getconectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Editorial ed = new Editorial();
                ed.setId_edit(rs.getInt("id_editorial"));
                ed.setNombre(rs.getString("nombre"));
                lista.add(ed);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar editoriales: " + e.getMessage());
        } 

        return lista;
    }

}
