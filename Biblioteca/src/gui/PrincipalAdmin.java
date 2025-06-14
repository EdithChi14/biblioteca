package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.Insets;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JTabbedPane;
import java.awt.event.MouseMotionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import Conexion.Conexion;
import Validaciones.ValidarCatEdi;
import Validaciones.ValidarLibro;
import Validaciones.ValidarPrestamo;
import Validaciones.ValidarReserva;
import Validaciones.ValidarUsuario;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import com.toedter.components.JSpinField;
import java.awt.event.KeyAdapter;

import Clases.Categoria;
import Clases.CategoriaDAO;
import Clases.Editorial;
import Clases.EditorialDAO;
import Clases.Libro;
import Clases.LibroDAO;
import Clases.Prestamo;
import Clases.PrestamoDAO;
import Clases.Reserva;
import Clases.ReservaDAO;
import Clases.Usuario;
import Clases.UsuarioDAO;

import com.toedter.components.JLocaleChooser;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.toedter.calendar.JCalendar;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import com.toedter.calendar.JDayChooser;
import java.awt.Panel;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import javax.swing.JMenu;

public class PrincipalAdmin extends JFrame implements MouseMotionListener, MouseListener, ActionListener, KeyListener {
 
	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblNewLabel;
	private JLabel lblFecha;
	private JLabel lblNewLabel_2;
	private JTabbedPane tablaContenido;
	private JPanel Principal;
	private JPanel Prestamos;
	private JPanel Reservas;
	private JPanel Usuarios;
	private JPanel Libros;
	int xmouse,ymouse;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_11;
	private JLabel lblNewLabel_12;
	private JLabel lblNewLabel_13;
	private JLabel lblNewLabel_14;
	private JLabel lblPrestamosDeLibros;
	private JLabel lblNombreDeLibro;
	private JButton btnInsertarP;
	private JLabel lblFechaDeDevolucin;
	private JScrollPane scrollPane;
	private JLabel lblNombreDeUsuario;
	private JButton btnEliminarP;
	private JButton btnModificarP;
	private JLabel lblReservaDeLibros;
	private JLabel lblNombreDeLibro_1;
	private JLabel lblPrestamoDeLibro_1;
	private JLabel lblNombreDeUsuario_1;
	private JButton btnModificarR;
	private JButton btnInsertarR;
	private JButton btnEliminarR;
	private JScrollPane scrollPane_1;
	private JTextPane txtpnSistemaDeGestin;
	private JTextPane txtpnEstaHerramientaLe;
	private JLabel lblDatosDeLibros;
	private JTextField txtAutor;
	private JLabel lblNombreDeUsuario_2;
	private JButton btnModificarL;
	private JButton btnInsertarL;
	private JButton btnEliminarL;
	private JScrollPane scrollPane_2;
	private JLabel lblNombreDeUsuario_3;
	private JTextField txtIsbn;
	private JLabel lblNombreDeUsuario_4;
	private JTextField txtTitulo;
	private JLabel lblNombreDeUsuario_6;
	private JLabel lblDatosDeUsuario;
	private JLabel lblNombreDeUsuario_8;
	private JTextField txtNombreU;
	private JLabel lblPrestamoDeLibro_3;
	private JButton btnModificarU;
	private JButton btnInsertarU;
	private JScrollPane scrollPane_3;
	private JLabel lblNombreDeUsuario_10;
	private JTextField txtIdU;
	private JLabel lblNombreDeUsuario_12;
	private JTextField txtDirecU;
	private JLabel lblNombreDeUsuario_13;
	private JTextField txtTelefU;
	private JPanel pnlPrincipal;
	private JPanel pnlPrestamo;
	private JPanel pnlUsuarios;
	private JPanel pnlReserva;
	private JPanel pnlLibros;
	private JLabel lblPrincipal;
	private JLabel lblPrestamo;
	private JLabel lblReserva;
	private JLabel lblUsuarios;
	private JLabel lblLibros;
	private JPanel pnlCerrar;
	private JLabel lblCerrar;
	private JTable tablaUsuarios;
	private JTable tablaPrestamo;
	private JTable tablaReserva;
	private JTable tablaLibro;
	private JLabel lblNombreDeUsuario_5;
	private JTextField txtCorreoU;
	
	UsuarioDAO usuDAO=new UsuarioDAO();
	Usuario usu=new Usuario();
	Libro lib=new Libro();
	LibroDAO libDAO=new LibroDAO();
	Prestamo pre=new Prestamo();
	PrestamoDAO preDAO=new PrestamoDAO();
	Reserva res=new Reserva();
	ReservaDAO resDAO=new ReservaDAO();
	CategoriaDAO catDAO=new CategoriaDAO();
    EditorialDAO editDAO = new EditorialDAO();
	DefaultTableModel modelo=new DefaultTableModel();
	
	private JComboBox<String> comboBoxRol;
	private JTextField txtStock;
	private JTextField txtPinNumerico;
	private JLabel lblNombreDeUsuario_7;
	private JComboBox<Categoria> cboCategoria;
    private JComboBox<Editorial> cboEditorial;
    private JComboBox cboCate;
    private JComboBox cboEdit;
    private JPanel Cate_Edit;
    private JLabel lblDatosCategoria;
    private JLabel lblNombreDeUsuario_9;
    private JTextField txtNomCate;
    private JButton btnModiCate;
    private JButton btnInserCate;
    private JButton btnElimCate;
    private JButton btnElimEdito;
    private JButton btnInserEdito;
    private JButton btnModifiEdito;
    private JLabel lblDatosDeLibros_2;
    private JLabel lblNombreDeUsuario_11;
    private JTextField txtNomEdito;
    private JScrollPane scrollPane_4;
    private JTable tablaCategoria;
    private JScrollPane scrollPane_5;
    private JTable tablaEditorial;
    private JPanel pnlCat;
    private JLabel lblCategoria;
    private JDateChooser dateReserva;
    private JDateChooser dateFechaP;
    private JDateChooser dateFechaDev;
    private JRadioButton rbtnActivo;
    private JRadioButton rbtnInactivo;
    private JTextField txtIsbnR;
    private JPopupMenu popupSugerencias = new JPopupMenu();
    private JMenu mnNewMenu;
    private JTextField txtUsuR;
    private JPopupMenu popupMenu;
    private JMenu mnNewMenu_1;
    private JTextField txtIsbnP;
    private JTextField txtUsuP;
    private JPopupMenu popupMenu_1;
    private JPopupMenu popupMenu_2;
    private JMenu mnNewMenu_2;
    private JMenu mnNewMenu_3;
    private long isbnReservaResaltada = -1;
    private String usuarioReservaResaltada = "";
    private JLabel lblFechaDevP;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalAdmin frame = new PrincipalAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public PrincipalAdmin()  {
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 873, 702);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			lblNewLabel_2 = new JLabel("");
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2.setIcon(new ImageIcon(PrincipalAdmin.class.getResource("/imagen/Logo BPN.png")));
			lblNewLabel_2.setBounds(559, 11, 314, 97);
			contentPane.add(lblNewLabel_2);
		}
		{
			panel = new JPanel();
			panel.setBackground(new Color(128, 128, 255));
			panel.setBounds(0, 0, 216, 793);
			contentPane.add(panel);
			panel.setLayout(null);
			{
				lblNewLabel = new JLabel("MENÚ");
				lblNewLabel.setBounds(0, 64, 216, 61);
				lblNewLabel.setForeground(Color.WHITE);
				lblNewLabel.setFont(new Font("Century Gothic", Font.PLAIN, 40));
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblNewLabel);
			}
			{
				pnlPrincipal = new JPanel();
				pnlPrincipal.setBounds(0, 187, 216, 54);
				pnlPrincipal.setBorder(null);
				pnlPrincipal.setBackground(new Color(128, 128, 255));
				panel.add(pnlPrincipal);
				pnlPrincipal.setLayout(null);
				{
					lblPrincipal = new JLabel("PRINCIPAL");
					lblPrincipal.addMouseListener(this);
					lblPrincipal.setBorder(new MatteBorder(1, 10, 1, 1, (Color) null));
					lblPrincipal.setIcon(new ImageIcon(PrincipalAdmin.class.getResource("/imagen/home_logo.png")));
					lblPrincipal.setForeground(new Color(255, 255, 255));
					lblPrincipal.setFont(new Font("Century Gothic", Font.PLAIN, 20));
					lblPrincipal.setBounds(0, 0, 216, 54);
					pnlPrincipal.add(lblPrincipal);
				}
			}
			{
				pnlPrestamo = new JPanel();
				pnlPrestamo.setBounds(0, 241, 216, 54);
				pnlPrestamo.setBorder(null);
				pnlPrestamo.setBackground(new Color(128, 128, 255));
				panel.add(pnlPrestamo);
				pnlPrestamo.setLayout(null);
				{
					lblPrestamo = new JLabel("PRÉSTAMO");
					lblPrestamo.addMouseListener(this);
					lblPrestamo.setBorder(new MatteBorder(1, 10, 1, 1, (Color) null));
					lblPrestamo.setIcon(new ImageIcon(PrincipalAdmin.class.getResource("/imagen/prestamo_logo.png")));
					lblPrestamo.setForeground(new Color(255, 255, 255));
					lblPrestamo.setFont(new Font("Century Gothic", Font.PLAIN, 20));
					lblPrestamo.setBounds(0, 0, 216, 54);
					pnlPrestamo.add(lblPrestamo);
				}
			}
			{
				pnlUsuarios = new JPanel();
				pnlUsuarios.setBounds(0, 349, 216, 54);
				pnlUsuarios.setBorder(null);
				pnlUsuarios.setBackground(new Color(128, 128, 255));
				panel.add(pnlUsuarios);
				pnlUsuarios.setLayout(null);
				{
					lblUsuarios = new JLabel("USUARIOS");
					lblUsuarios.addMouseListener(this);
					lblUsuarios.setBorder(new MatteBorder(1, 10, 1, 1, (Color) null));
					lblUsuarios.setIcon(new ImageIcon(PrincipalAdmin.class.getResource("/imagen/usuario_logo.png")));
					lblUsuarios.setForeground(new Color(255, 255, 255));
					lblUsuarios.setFont(new Font("Century Gothic", Font.PLAIN, 20));
					lblUsuarios.setBounds(0, 0, 216, 54);
					pnlUsuarios.add(lblUsuarios);
				}
			}
			{
				pnlReserva = new JPanel();
				pnlReserva.setBounds(0, 295, 216, 54);
				pnlReserva.setBorder(null);
				pnlReserva.setBackground(new Color(128, 128, 255));
				panel.add(pnlReserva);
				pnlReserva.setLayout(null);
				{
					lblReserva = new JLabel("RESERVA");
					lblReserva.addMouseListener(this);
					lblReserva.setBorder(new MatteBorder(1, 10, 1, 1, (Color) null));
					lblReserva.setIcon(new ImageIcon(PrincipalAdmin.class.getResource("/imagen/reservas_logo.png")));
					lblReserva.setForeground(new Color(255, 255, 255));
					lblReserva.setFont(new Font("Century Gothic", Font.PLAIN, 20));
					lblReserva.setBounds(0, 0, 216, 54);
					pnlReserva.add(lblReserva);
				}
			}
			{
				pnlLibros = new JPanel();
				pnlLibros.setBounds(0, 402, 216, 54);
				pnlLibros.setBorder(null);
				pnlLibros.setBackground(new Color(128, 128, 255));
				panel.add(pnlLibros);
				pnlLibros.setLayout(null);
				{
					lblLibros = new JLabel("LIBROS");
					lblLibros.addMouseListener(this);
					lblLibros.setBorder(new MatteBorder(1, 10, 1, 1, (Color) null));
					lblLibros.setIcon(new ImageIcon(PrincipalAdmin.class.getResource("/imagen/LibroLogo.png")));
					lblLibros.setForeground(new Color(255, 255, 255));
					lblLibros.setFont(new Font("Century Gothic", Font.PLAIN, 20));
					lblLibros.setBounds(0, 0, 216, 54);
					pnlLibros.add(lblLibros);
				}
			}
			{
				pnlCerrar = new JPanel();
				pnlCerrar.setLayout(null);
				pnlCerrar.setBorder(null);
				pnlCerrar.setBackground(new Color(128, 128, 255));
				pnlCerrar.setBounds(0, 623, 216, 54);
				panel.add(pnlCerrar);
				{
					lblCerrar = new JLabel("CERRAR SESION");
					lblCerrar.addMouseListener(this);
					lblCerrar.setIcon(new ImageIcon(PrincipalAdmin.class.getResource("/imagen/CerrarSesion.png")));
					lblCerrar.setForeground(Color.WHITE);
					lblCerrar.setFont(new Font("Century Gothic", Font.PLAIN, 16));
					lblCerrar.setBorder(new MatteBorder(1, 10, 1, 1, (Color) null));
					lblCerrar.setBounds(0, 0, 216, 54);
					pnlCerrar.add(lblCerrar);
				}
			}
			{
				pnlCat = new JPanel();
				pnlCat.setBackground(new Color(128, 128, 255));
				pnlCat.setBounds(0, 455, 216, 54);
				panel.add(pnlCat);
				pnlCat.setLayout(null);
				{
					lblCategoria = new JLabel("CATEGORIA / EDITORIAL");
					lblCategoria.addMouseListener(this);
					lblCategoria.setBounds(0, 0, 216, 54);
					lblCategoria.setForeground(Color.WHITE);
					lblCategoria.setFont(new Font("Century Gothic", Font.PLAIN, 15));
					lblCategoria.setBorder(new MatteBorder(1, 10, 1, 1, (Color) null));
					pnlCat.add(lblCategoria);
				}
			}
		}
		{
			panel_1 = new JPanel();
			panel_1.setBackground(new Color(128, 128, 255));
			panel_1.setBounds(215, 119, 658, 54);
			contentPane.add(panel_1);
			panel_1.setLayout(null);
			{
				lblFecha = new JLabel("Hoy es {dia} de {mes} de {año}");
				lblFecha.setForeground(Color.WHITE);
				lblFecha.setFont(new Font("Century Gothic", Font.PLAIN, 25));
				lblFecha.setBounds(10, 11, 466, 36);
				panel_1.add(lblFecha);
			}
		}
		{
			panel_2 = new JPanel();
			panel_2.addMouseListener(this);
			panel_2.addMouseMotionListener(this);
			panel_2.setBackground(new Color(255, 255, 255));
			panel_2.setBounds(0, 0, 873, 35);
			contentPane.add(panel_2);
			panel_2.setLayout(null);
		}
		{
			tablaContenido = new JTabbedPane(JTabbedPane.LEFT);
			tablaContenido.setBackground(new Color(255, 255, 255));
			tablaContenido.setBounds(177, 159, 711, 555);
			contentPane.add(tablaContenido);
			{
				Principal = new JPanel();
				Principal.setBackground(new Color(255, 255, 255));
				tablaContenido.addTab("", null, Principal, null);
				Principal.setLayout(null);
				{
					lblNewLabel_1 = new JLabel("Bienvenido");
					lblNewLabel_1.setFont(new Font("Century Gothic", Font.PLAIN, 15));
					lblNewLabel_1.setBounds(43, 50, 210, 29);
					Principal.add(lblNewLabel_1);
				}
				{
					lblNewLabel_9 = new JLabel("Registro de usuarios y libros nuevos");
					lblNewLabel_9.setIcon(new ImageIcon(PrincipalAdmin.class.getResource("/imagen/CIRCULO NEGRO.png")));
					lblNewLabel_9.setFont(new Font("Century Gothic", Font.PLAIN, 15));
					lblNewLabel_9.setBounds(43, 382, 446, 29);
					Principal.add(lblNewLabel_9);
				}
				{
					lblNewLabel_10 = new JLabel("Reservas");
					lblNewLabel_10.setIcon(new ImageIcon(PrincipalAdmin.class.getResource("/imagen/CIRCULO NEGRO.png")));
					lblNewLabel_10.setFont(new Font("Century Gothic", Font.PLAIN, 15));
					lblNewLabel_10.setBounds(43, 353, 446, 29);
					Principal.add(lblNewLabel_10);
				}
				{
					lblNewLabel_11 = new JLabel("Préstamos");
					lblNewLabel_11.setIcon(new ImageIcon(PrincipalAdmin.class.getResource("/imagen/CIRCULO NEGRO.png")));
					lblNewLabel_11.setFont(new Font("Century Gothic", Font.PLAIN, 15));
					lblNewLabel_11.setBounds(43, 324, 446, 29);
					Principal.add(lblNewLabel_11);
				}
				{
					lblNewLabel_12 = new JLabel("Edición de usuarios y libros existentes");
					lblNewLabel_12.setIcon(new ImageIcon(PrincipalAdmin.class.getResource("/imagen/CIRCULO NEGRO.png")));
					lblNewLabel_12.setFont(new Font("Century Gothic", Font.PLAIN, 15));
					lblNewLabel_12.setBounds(43, 410, 446, 29);
					Principal.add(lblNewLabel_12);
				}
				{
					lblNewLabel_13 = new JLabel("Eliminar todo tipo de registro");
					lblNewLabel_13.setIcon(new ImageIcon(PrincipalAdmin.class.getResource("/imagen/CIRCULO NEGRO.png")));
					lblNewLabel_13.setFont(new Font("Century Gothic", Font.PLAIN, 15));
					lblNewLabel_13.setBounds(43, 439, 446, 29);
					Principal.add(lblNewLabel_13);
				}
				{
					lblNewLabel_14 = new JLabel("");
					lblNewLabel_14.setIcon(new ImageIcon(PrincipalAdmin.class.getResource("/imagen/LibrosPrin.png")));
					lblNewLabel_14.setBounds(255, 237, 425, 325);
					Principal.add(lblNewLabel_14);
				}
				{
					txtpnSistemaDeGestin = new JTextPane();
					txtpnSistemaDeGestin.setText("Al sistema de Gestión para la Biblioteca Nacional del Perú.\r\nControl y administracion de forma óptima y fácil el flujo de préstamos y reservas de libros.");
					txtpnSistemaDeGestin.setFont(new Font("Century Gothic", Font.PLAIN, 15));
					txtpnSistemaDeGestin.setBounds(43, 90, 446, 60);
					Principal.add(txtpnSistemaDeGestin);
				}
				{
					txtpnEstaHerramientaLe = new JTextPane();
					txtpnEstaHerramientaLe.setText("Esta herramienta le permitirá llevar un control completo y detallado de su Biblioteca, tendrá acceso a herramientas especiales para tareas específicas, como lo son:");
					txtpnEstaHerramientaLe.setFont(new Font("Century Gothic", Font.PLAIN, 15));
					txtpnEstaHerramientaLe.setBounds(43, 190, 446, 60);
					Principal.add(txtpnEstaHerramientaLe);
				}
			}
			{
				Libros = new JPanel();
				Libros.setBackground(new Color(255, 255, 255));
				tablaContenido.addTab("", null, Libros, null);
				Libros.setLayout(null);
				{
					lblDatosDeLibros = new JLabel("Datos de Libros");
					lblDatosDeLibros.setFont(new Font("Century Gothic", Font.PLAIN, 20));
					lblDatosDeLibros.setBounds(39, 23, 253, 32);
					Libros.add(lblDatosDeLibros);
				}
				{
					txtAutor = new JTextField();
					txtAutor.setColumns(10);
					txtAutor.setBounds(464, 80, 193, 26);
					Libros.add(txtAutor);
				}
				{
					lblNombreDeUsuario_2 = new JLabel("Autor");
					lblNombreDeUsuario_2.setFont(new Font("Century Gothic", Font.PLAIN, 14));
					lblNombreDeUsuario_2.setBounds(464, 50, 193, 26);
					Libros.add(lblNombreDeUsuario_2);
				}
				{
					btnModificarL = new JButton("Modificar");
					btnModificarL.addActionListener(this);
					btnModificarL.addMouseListener(this);
					btnModificarL.setForeground(Color.WHITE);
					btnModificarL.setFont(new Font("Century Gothic", Font.PLAIN, 20));
					btnModificarL.setBorder(null);
					btnModificarL.setBackground(new Color(128, 128, 255));
					btnModificarL.setBounds(136, 191, 151, 32);
					Libros.add(btnModificarL);
				}
				{
					btnInsertarL = new JButton("Insertar");
					btnInsertarL.addActionListener(this);
					btnInsertarL.addMouseListener(this);
					btnInsertarL.setForeground(Color.WHITE);
					btnInsertarL.setFont(new Font("Century Gothic", Font.PLAIN, 20));
					btnInsertarL.setBorder(null);
					btnInsertarL.setBackground(new Color(128, 128, 255));
					btnInsertarL.setBounds(297, 191, 151, 32);
					Libros.add(btnInsertarL);
				}
				{
					btnEliminarL = new JButton("Eliminar");
					btnEliminarL.addActionListener(this);
					btnEliminarL.addMouseListener(this);
					btnEliminarL.setForeground(Color.WHITE);
					btnEliminarL.setFont(new Font("Century Gothic", Font.PLAIN, 20));
					btnEliminarL.setBorder(null);
					btnEliminarL.setBackground(new Color(128, 128, 255));
					btnEliminarL.setBounds(458, 191, 151, 32);
					Libros.add(btnEliminarL);
				}
				{
					scrollPane_2 = new JScrollPane();
					scrollPane_2.setBounds(50, 246, 616, 262);
					Libros.add(scrollPane_2);
					{
						tablaLibro = new JTable();
						tablaLibro.addMouseListener(this);
						tablaLibro.setModel(new DefaultTableModel(
							new Object[][] {
							},
							new String[] {
								"ISBN", "T\u00CDTULO", "AUTOR", "CATEGOR\u00CDA", "EDITORIAL", "STOCK"
							}
						));
						tablaLibro.getColumnModel().getColumn(0).setPreferredWidth(85);
						tablaLibro.getColumnModel().getColumn(1).setPreferredWidth(85);
						tablaLibro.getColumnModel().getColumn(2).setPreferredWidth(85);
						tablaLibro.getColumnModel().getColumn(3).setPreferredWidth(85);
						tablaLibro.getColumnModel().getColumn(4).setPreferredWidth(85);
						scrollPane_2.setViewportView(tablaLibro);
					}
				}
				{
					lblNombreDeUsuario_3 = new JLabel("ISBN");
					lblNombreDeUsuario_3.setFont(new Font("Century Gothic", Font.PLAIN, 14));
					lblNombreDeUsuario_3.setBounds(39, 50, 193, 26);
					Libros.add(lblNombreDeUsuario_3);
				}
				{
					txtIsbn = new JTextField();
					txtIsbn.setColumns(10);
					txtIsbn.setBounds(39, 80, 193, 26);
					Libros.add(txtIsbn);
				}
				{
					lblNombreDeUsuario_4 = new JLabel("Título");
					lblNombreDeUsuario_4.setFont(new Font("Century Gothic", Font.PLAIN, 14));
					lblNombreDeUsuario_4.setBounds(251, 50, 193, 26);
					Libros.add(lblNombreDeUsuario_4);
				}
				{
					txtTitulo = new JTextField();
					txtTitulo.setColumns(10);
					txtTitulo.setBounds(251, 80, 193, 26);
					Libros.add(txtTitulo);
				}
				{
					lblNombreDeUsuario_6 = new JLabel("Editorial");
					lblNombreDeUsuario_6.setFont(new Font("Century Gothic", Font.PLAIN, 14));
					lblNombreDeUsuario_6.setBounds(251, 117, 193, 26);
					Libros.add(lblNombreDeUsuario_6);
				}
				{
					lblNombreDeUsuario_10 = new JLabel("Categoria");
					lblNombreDeUsuario_10.setFont(new Font("Century Gothic", Font.PLAIN, 14));
					lblNombreDeUsuario_10.setBounds(39, 117, 193, 26);
					Libros.add(lblNombreDeUsuario_10);
				}
				
				txtStock = new JTextField();
				txtStock.setColumns(10);
				txtStock.setBounds(464, 154, 193, 26);
				Libros.add(txtStock);
				
				JLabel lblNombreDeUsuario_2_1 = new JLabel("Stock");
				lblNombreDeUsuario_2_1.setFont(new Font("Century Gothic", Font.PLAIN, 14));
				lblNombreDeUsuario_2_1.setBounds(464, 117, 193, 26);
				Libros.add(lblNombreDeUsuario_2_1);
				{
					cboCate = new JComboBox();
					cboCate.setBounds(39, 154, 193, 26);
					Libros.add(cboCate);
				}
				{
					cboEdit = new JComboBox();
					cboEdit.setBounds(251, 154, 193, 26);
					Libros.add(cboEdit);
					
				}
				
				
				
				
			}
			{
				Prestamos = new JPanel();
				Prestamos.setBackground(new Color(255, 255, 255));
				tablaContenido.addTab("", null, Prestamos, null);
				Prestamos.setLayout(null);
				
				{
					lblPrestamosDeLibros = new JLabel("Préstamos de Libros");
					lblPrestamosDeLibros.setFont(new Font("Century Gothic", Font.PLAIN, 20));
					lblPrestamosDeLibros.setBounds(41, 31, 253, 32);
					Prestamos.add(lblPrestamosDeLibros);
				}
				{
					lblNombreDeLibro = new JLabel("ISBN");
					lblNombreDeLibro.setFont(new Font("Century Gothic", Font.PLAIN, 14));
					lblNombreDeLibro.setBounds(41, 74, 193, 26);
					Prestamos.add(lblNombreDeLibro);
				}
				{
					btnInsertarP = new JButton("Insertar");
					btnInsertarP.addActionListener(this);
					btnInsertarP.addMouseListener(this);
					btnInsertarP.setForeground(Color.WHITE);
					btnInsertarP.setFont(new Font("Century Gothic", Font.PLAIN, 20));
					btnInsertarP.setBorder(null);
					btnInsertarP.setBackground(new Color(128, 128, 255));
					btnInsertarP.setBounds(517, 86, 151, 32);
					Prestamos.add(btnInsertarP);
				}
				{
					lblFechaDeDevolucin = new JLabel("Fecha de devolución:");
					lblFechaDeDevolucin.setFont(new Font("Century Gothic", Font.PLAIN, 14));
					lblFechaDeDevolucin.setBounds(296, 150, 200, 26);
					Prestamos.add(lblFechaDeDevolucin);
				}
				{
					scrollPane = new JScrollPane();
					scrollPane.setBounds(41, 234, 616, 280);
					Prestamos.add(scrollPane);
					{
						tablaPrestamo = new JTable();
						tablaPrestamo.addMouseListener(this);
						tablaPrestamo.setModel(new DefaultTableModel(
							new Object[][] {
							},
							new String[] {
								"ISBN", "IDUSUARIO", "N\u00B0", "LIBRO", "USUARIO", "FECHA PR\u00C9STAMO", "FECHA DEVOLUCI\u00D3N","ESTADO"
							}
						));
						tablaPrestamo.getColumnModel().getColumn(0).setPreferredWidth(0);
						tablaPrestamo.getColumnModel().getColumn(0).setMinWidth(0);
						tablaPrestamo.getColumnModel().getColumn(0).setMaxWidth(0);
						tablaPrestamo.getColumnModel().getColumn(1).setPreferredWidth(0);
						tablaPrestamo.getColumnModel().getColumn(1).setMinWidth(0);
						tablaPrestamo.getColumnModel().getColumn(1).setMaxWidth(0);
						tablaPrestamo.getColumnModel().getColumn(4).setPreferredWidth(100);
						tablaPrestamo.getColumnModel().getColumn(5).setPreferredWidth(120);
						tablaPrestamo.getColumnModel().getColumn(6).setPreferredWidth(117);
						tablaPrestamo.getColumnModel().getColumn(6).setMinWidth(20);
						scrollPane.setViewportView(tablaPrestamo);
					}
				}
				{
					lblNombreDeUsuario = new JLabel("Id Usuario");
					lblNombreDeUsuario.setFont(new Font("Century Gothic", Font.PLAIN, 14));
					lblNombreDeUsuario.setBounds(296, 81, 193, 26);
					Prestamos.add(lblNombreDeUsuario);
				}
				{
					btnEliminarP = new JButton("Eliminar");
					btnEliminarP.addActionListener(this);
					btnEliminarP.addMouseListener(this);
					btnEliminarP.setForeground(Color.WHITE);
					btnEliminarP.setFont(new Font("Century Gothic", Font.PLAIN, 20));
					btnEliminarP.setBorder(null);
					btnEliminarP.setBackground(new Color(128, 128, 255));
					btnEliminarP.setBounds(517, 129, 151, 32);
					Prestamos.add(btnEliminarP);
				}
				{
					btnModificarP = new JButton("Modificar");
					btnModificarP.addActionListener(this);
					btnModificarP.addMouseListener(this);
					btnModificarP.setForeground(Color.WHITE);
					btnModificarP.setFont(new Font("Century Gothic", Font.PLAIN, 20));
					btnModificarP.setBorder(null);
					btnModificarP.setBackground(new Color(128, 128, 255));
					btnModificarP.setBounds(517, 44, 151, 32);
					Prestamos.add(btnModificarP);
				}
				
				JLabel lblFechaDePrestamo = new JLabel("Fecha de prestamo:");
				lblFechaDePrestamo.setFont(new Font("Century Gothic", Font.PLAIN, 14));
				lblFechaDePrestamo.setBounds(41, 148, 200, 26);
				Prestamos.add(lblFechaDePrestamo);
				
				JButton btnDevuelto = new JButton("Devuelto");
				btnDevuelto.setForeground(Color.WHITE);
				btnDevuelto.setFont(new Font("Century Gothic", Font.PLAIN, 20));
				btnDevuelto.setBorder(null);
				btnDevuelto.setBackground(new Color(128, 128, 255));
				btnDevuelto.setBounds(517, 172, 151, 32);
				Prestamos.add(btnDevuelto);
				{
					dateFechaP = new JDateChooser();
					dateFechaP.setBounds(41, 185, 193, 24);
					Prestamos.add(dateFechaP);
				}
				{
					dateFechaDev = new JDateChooser();
					dateFechaDev.setBounds(296, 185, 193, 24);
					Prestamos.add(dateFechaDev);
				}
				{
					txtIsbnP = new JTextField();
					txtIsbnP.setBounds(41, 111, 193, 26);
					Prestamos.add(txtIsbnP);
					txtIsbnP.setColumns(10);
					{
						popupMenu_1 = new JPopupMenu();
						addPopup(txtIsbnP, popupMenu_1);
						{
							mnNewMenu_3 = new JMenu("New menu");
							popupMenu_1.add(mnNewMenu_3);
						}
					}
				}
				{
					txtUsuP = new JTextField();
					txtUsuP.setColumns(10);
					txtUsuP.setBounds(296, 113, 193, 26);
					Prestamos.add(txtUsuP);
					{
						popupMenu_2 = new JPopupMenu();
						addPopup(txtUsuP, popupMenu_2);
						{
							mnNewMenu_2 = new JMenu("New menu");
							popupMenu_2.add(mnNewMenu_2);
						}
					}
				}
				
				btnDevuelto.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				        int fila = tablaPrestamo.getSelectedRow();
				        if (fila != -1) {
				            try {
				                long isbn = Long.parseLong(tablaPrestamo.getValueAt(fila, 0).toString());
				                String idUsuario = tablaPrestamo.getValueAt(fila, 1).toString();

				                preDAO.marcarDevuelto(isbn, idUsuario);

				                LimpiarTabla();
				                Limpiar();
				                ListaPre();

				            } catch (Exception ex) {
				                JOptionPane.showMessageDialog(null, "Error al marcar devolución: " + ex.getMessage());
				            }
				        } else {
				            JOptionPane.showMessageDialog(null, "Seleccione una fila primero.");
				        }
				    }
				});

								
			}
			{
				Usuarios = new JPanel();
				Usuarios.setBackground(new Color(255, 255, 255));
				tablaContenido.addTab("", null, Usuarios, null);
				Usuarios.setLayout(null);
				{
					lblDatosDeUsuario = new JLabel("Datos de Usuario");
					lblDatosDeUsuario.setFont(new Font("Century Gothic", Font.PLAIN, 20));
					lblDatosDeUsuario.setBounds(39, 21, 253, 32);
					Usuarios.add(lblDatosDeUsuario);
				}
				{
					lblNombreDeUsuario_8 = new JLabel("Nombre");
					lblNombreDeUsuario_8.setFont(new Font("Century Gothic", Font.PLAIN, 14));
					lblNombreDeUsuario_8.setBounds(261, 64, 193, 26);
					Usuarios.add(lblNombreDeUsuario_8);
				}
				{
					txtNombreU = new JTextField();
					txtNombreU.setColumns(10);
					txtNombreU.setBounds(261, 94, 193, 26);
					Usuarios.add(txtNombreU);
				}
				{
					lblPrestamoDeLibro_3 = new JLabel("ID");
					lblPrestamoDeLibro_3.setFont(new Font("Century Gothic", Font.PLAIN, 14));
					lblPrestamoDeLibro_3.setBounds(39, 64, 215, 29);
					Usuarios.add(lblPrestamoDeLibro_3);
				}
				{
					btnModificarU = new JButton("Modificar");
					btnModificarU.addActionListener(this);
					btnModificarU.addMouseListener(this);
					btnModificarU.setForeground(Color.WHITE);
					btnModificarU.setFont(new Font("Century Gothic", Font.PLAIN, 20));
					btnModificarU.setBorder(null);
					btnModificarU.setBackground(new Color(128, 128, 255));
					btnModificarU.setBounds(261, 211, 130, 32);
					Usuarios.add(btnModificarU);
				}
				{
					btnInsertarU = new JButton("Insertar");
					btnInsertarU.addMouseListener(this);
					btnInsertarU.addActionListener(this);
					btnInsertarU.setForeground(Color.WHITE);
					btnInsertarU.setFont(new Font("Century Gothic", Font.PLAIN, 20));
					btnInsertarU.setBorder(null);
					btnInsertarU.setBackground(new Color(128, 128, 255));
					btnInsertarU.setBounds(401, 211, 114, 32);
					Usuarios.add(btnInsertarU);
				}
				{
					scrollPane_3 = new JScrollPane();
					scrollPane_3.setBounds(50, 264, 616, 242);
					Usuarios.add(scrollPane_3);
					{
						tablaUsuarios = new JTable();
						tablaUsuarios.setBackground(new Color(255, 255, 255));
						tablaUsuarios.addMouseListener(this);
						tablaUsuarios.setModel(new DefaultTableModel(
							new Object[][] {
							},
							new String[] {
								"ID", "Nombre", "Correo", "Direcci\u00F3n", "Tel\u00E9fono","Rol","Contraseña","Estado"	
							}
						));
						
						tablaUsuarios.getColumnModel().getColumn(7).setMinWidth(0);
						tablaUsuarios.getColumnModel().getColumn(7).setMaxWidth(0);
						tablaUsuarios.getColumnModel().getColumn(7).setWidth(0);
						
						tablaUsuarios.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
						    @Override
						    public Component getTableCellRendererComponent(JTable table, Object value,
						            boolean isSelected, boolean hasFocus, int row, int column) {

						        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

						        String estado = table.getValueAt(row, 7).toString().toLowerCase();

						        if ("inactivo".equals(estado)) {
						            c.setForeground(new Color(128,128,128));
						        } else {
						            c.setForeground(Color.BLACK);
						        }

						        c.setBackground(isSelected ? table.getSelectionBackground() : Color.WHITE);
						        return c;
						    }
						});

						
						scrollPane_3.setViewportView(tablaUsuarios);
					}
				}
				{
					txtIdU = new JTextField();
					txtIdU.addKeyListener(this);
					txtIdU.setColumns(10);
					txtIdU.setBounds(39, 94, 193, 26);
					Usuarios.add(txtIdU);
				}
				{
					lblNombreDeUsuario_12 = new JLabel("Dirección");
					lblNombreDeUsuario_12.setFont(new Font("Century Gothic", Font.PLAIN, 14));
					lblNombreDeUsuario_12.setBounds(39, 131, 193, 26);
					Usuarios.add(lblNombreDeUsuario_12);
				}
				{
					txtDirecU = new JTextField();
					txtDirecU.setColumns(10);
					txtDirecU.setBounds(39, 161, 193, 26);
					Usuarios.add(txtDirecU);
				}
				{
					lblNombreDeUsuario_13 = new JLabel("Teléfono");
					lblNombreDeUsuario_13.setFont(new Font("Century Gothic", Font.PLAIN, 14));
					lblNombreDeUsuario_13.setBounds(261, 131, 193, 26);
					Usuarios.add(lblNombreDeUsuario_13);
				}
				{
					txtTelefU = new JTextField();
					txtTelefU.setColumns(10);
					txtTelefU.setBounds(261, 161, 193, 26);
					Usuarios.add(txtTelefU);
				}
				{
					lblNombreDeUsuario_5 = new JLabel("Correo");
					lblNombreDeUsuario_5.setFont(new Font("Century Gothic", Font.PLAIN, 14));
					lblNombreDeUsuario_5.setBounds(464, 64, 193, 26);
					Usuarios.add(lblNombreDeUsuario_5);
				}
				{
					txtCorreoU = new JTextField();
					txtCorreoU.setColumns(10);
					txtCorreoU.setBounds(464, 94, 193, 26);
					Usuarios.add(txtCorreoU);
				}
				
				JLabel lblNombreDeUsuario_13_1 = new JLabel("Rol");
				lblNombreDeUsuario_13_1.setFont(new Font("Century Gothic", Font.PLAIN, 14));
				lblNombreDeUsuario_13_1.setBounds(464, 131, 193, 26);
				Usuarios.add(lblNombreDeUsuario_13_1);
				
				comboBoxRol = new JComboBox<>();
				comboBoxRol.setModel(new DefaultComboBoxModel(new String[] {"Usuario", "Administrador"}));
				comboBoxRol.setBounds(464, 159, 193, 26);
				comboBoxRol.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String rolSeleccionado = (String) comboBoxRol.getSelectedItem();
				        if ( "Administrador".equals(rolSeleccionado)) {
				            txtPinNumerico.setEnabled(true);
				        } else {
				            txtPinNumerico.setEnabled(false);
				        }
					}
				});
				
				Usuarios.add(comboBoxRol);
				
				
				
				JLabel lblNombreDeUsuario_12_1 = new JLabel("Contraseña");
				lblNombreDeUsuario_12_1.setFont(new Font("Century Gothic", Font.PLAIN, 14));
				lblNombreDeUsuario_12_1.setBounds(39, 198, 193, 26);
				Usuarios.add(lblNombreDeUsuario_12_1);
				
				txtPinNumerico = new JTextField();
				txtPinNumerico.setColumns(10);
				txtPinNumerico.setBounds(39, 228, 193, 26);
				txtPinNumerico.setEnabled(false);
				Usuarios.add(txtPinNumerico);
				{
					lblNombreDeUsuario_7 = new JLabel("Rol");
					lblNombreDeUsuario_7.setFont(new Font("Century Gothic", Font.PLAIN, 14));
					lblNombreDeUsuario_7.setBounds(464, 131, 193, 26);
					Usuarios.add(lblNombreDeUsuario_7);
				}
				{
					rbtnActivo = new JRadioButton("Activar");
					rbtnActivo.addActionListener(this);
					rbtnActivo.setBackground(new Color(255, 255, 255));
					rbtnActivo.setBounds(534, 202, 109, 23);
					Usuarios.add(rbtnActivo);
				}
				{
					rbtnInactivo = new JRadioButton("Desactivar");
					rbtnInactivo.addActionListener(this);
					rbtnInactivo.setBackground(Color.WHITE);
					rbtnInactivo.setBounds(534, 234, 109, 23);
					Usuarios.add(rbtnInactivo);
				}
				ButtonGroup grupoEstado = new ButtonGroup();
				grupoEstado.add(rbtnActivo);
				grupoEstado.add(rbtnInactivo);
				
			}
			{
				Cate_Edit = new JPanel();
				Cate_Edit.setBackground(Color.WHITE);
				tablaContenido.addTab("\r\n", null, Cate_Edit, null);
				Cate_Edit.setLayout(null);
				{
					lblDatosCategoria = new JLabel("Datos Categoria");
					lblDatosCategoria.setFont(new Font("Century Gothic", Font.PLAIN, 20));
					lblDatosCategoria.setBounds(55, 45, 253, 32);
					Cate_Edit.add(lblDatosCategoria);
				}
				{
					lblNombreDeUsuario_9 = new JLabel("Nombre");
					lblNombreDeUsuario_9.setFont(new Font("Century Gothic", Font.PLAIN, 14));
					lblNombreDeUsuario_9.setBounds(55, 72, 193, 26);
					Cate_Edit.add(lblNombreDeUsuario_9);
				}
				{
					txtNomCate = new JTextField();
					txtNomCate.setColumns(10);
					txtNomCate.setBounds(55, 102, 193, 26);
					Cate_Edit.add(txtNomCate);
				}
				{
					btnModiCate = new JButton("Modificar");
					btnModiCate.addActionListener(this);
					btnModiCate.setForeground(Color.WHITE);
					btnModiCate.setFont(new Font("Century Gothic", Font.PLAIN, 20));
					btnModiCate.setBorder(null);
					btnModiCate.setBackground(new Color(128, 128, 255));
					btnModiCate.setBounds(191, 139, 130, 32);
					Cate_Edit.add(btnModiCate);
				}
				{
					btnInserCate = new JButton("Insertar");
					btnInserCate.addActionListener(this);
					btnInserCate.setForeground(Color.WHITE);
					btnInserCate.setFont(new Font("Century Gothic", Font.PLAIN, 20));
					btnInserCate.setBorder(null);
					btnInserCate.setBackground(new Color(128, 128, 255));
					btnInserCate.setBounds(51, 139, 130, 32);
					Cate_Edit.add(btnInserCate);
				}
				{
					btnElimCate = new JButton("Eliminar");
					btnElimCate.addActionListener(this);
					btnElimCate.addMouseListener(this);
					btnElimCate.setForeground(Color.WHITE);
					btnElimCate.setFont(new Font("Century Gothic", Font.PLAIN, 20));
					btnElimCate.setBorder(null);
					btnElimCate.setBackground(new Color(128, 128, 255));
					btnElimCate.setBounds(117, 182, 130, 32);
					Cate_Edit.add(btnElimCate);
				}
				{
					btnElimEdito = new JButton("Eliminar");
					btnElimEdito.addActionListener(this);
					btnElimEdito.addMouseListener(this);
					btnElimEdito.setForeground(Color.WHITE);
					btnElimEdito.setFont(new Font("Century Gothic", Font.PLAIN, 20));
					btnElimEdito.setBorder(null);
					btnElimEdito.setBackground(new Color(128, 128, 255));
					btnElimEdito.setBounds(435, 182, 130, 32);
					Cate_Edit.add(btnElimEdito);
				}
				{
					btnInserEdito = new JButton("Insertar");
					btnInserEdito.addActionListener(this);
					btnInserEdito.addMouseListener(this);
					btnInserEdito.setForeground(Color.WHITE);
					btnInserEdito.setFont(new Font("Century Gothic", Font.PLAIN, 20));
					btnInserEdito.setBorder(null);
					btnInserEdito.setBackground(new Color(128, 128, 255));
					btnInserEdito.setBounds(369, 139, 130, 32);
					Cate_Edit.add(btnInserEdito);
				}
				{
					btnModifiEdito = new JButton("Modificar");
					btnModifiEdito.addActionListener(this);
					btnModifiEdito.setForeground(Color.WHITE);
					btnModifiEdito.setFont(new Font("Century Gothic", Font.PLAIN, 20));
					btnModifiEdito.setBorder(null);
					btnModifiEdito.setBackground(new Color(128, 128, 255));
					btnModifiEdito.setBounds(509, 139, 130, 32);
					Cate_Edit.add(btnModifiEdito);
				}
				{
					lblDatosDeLibros_2 = new JLabel("Datos Editorial");
					lblDatosDeLibros_2.setFont(new Font("Century Gothic", Font.PLAIN, 20));
					lblDatosDeLibros_2.setBounds(369, 45, 253, 32);
					Cate_Edit.add(lblDatosDeLibros_2);
				}
				{
					lblNombreDeUsuario_11 = new JLabel("Nombre");
					lblNombreDeUsuario_11.setFont(new Font("Century Gothic", Font.PLAIN, 14));
					lblNombreDeUsuario_11.setBounds(369, 72, 193, 26);
					Cate_Edit.add(lblNombreDeUsuario_11);
				}
				{
					txtNomEdito = new JTextField();
					txtNomEdito.setColumns(10);
					txtNomEdito.setBounds(369, 102, 193, 26);
					Cate_Edit.add(txtNomEdito);
				}
				{
					scrollPane_4 = new JScrollPane();
					scrollPane_4.setBounds(55, 233, 287, 288);
					Cate_Edit.add(scrollPane_4);
					{
						tablaCategoria = new JTable();
						tablaCategoria.addMouseListener(this);
						tablaCategoria.setModel(new DefaultTableModel(
							new Object[][] {
							},
							new String[] {
								"N\u00B0", "Nombre"
							}
						));
						tablaCategoria.getColumnModel().getColumn(0).setPreferredWidth(43);
						tablaCategoria.getColumnModel().getColumn(1).setPreferredWidth(197);
						tablaCategoria.getColumnModel().getColumn(1).setMinWidth(17);
						scrollPane_4.setViewportView(tablaCategoria);
					}
				}
				{
					scrollPane_5 = new JScrollPane();
					scrollPane_5.setBounds(373, 233, 287, 288);
					Cate_Edit.add(scrollPane_5);
					{
						tablaEditorial = new JTable();
						tablaEditorial.addMouseListener(this);
						tablaEditorial.setModel(new DefaultTableModel(
							new Object[][] {
							},
							new String[] {
								"N\u00B0", "Nombre"
							}
						));
						tablaEditorial.getColumnModel().getColumn(1).setPreferredWidth(244);
						scrollPane_5.setViewportView(tablaEditorial);
					}
				}
			}
			{
				Reservas = new JPanel();
				Reservas.setBackground(new Color(255, 255, 255));
				tablaContenido.addTab("", null, Reservas, null);
				Reservas.setLayout(null);
				{
					lblReservaDeLibros = new JLabel("Reserva de Libros");
					lblReservaDeLibros.setFont(new Font("Century Gothic", Font.PLAIN, 20));
					lblReservaDeLibros.setBounds(37, 27, 253, 32);
					Reservas.add(lblReservaDeLibros);
				}
				{
					lblNombreDeLibro_1 = new JLabel("ISBN:");
					lblNombreDeLibro_1.setFont(new Font("Century Gothic", Font.PLAIN, 14));
					lblNombreDeLibro_1.setBounds(37, 70, 193, 26);
					Reservas.add(lblNombreDeLibro_1);
				}
				{
					lblPrestamoDeLibro_1 = new JLabel("Fecha de reserva");
					lblPrestamoDeLibro_1.setFont(new Font("Century Gothic", Font.PLAIN, 14));
					lblPrestamoDeLibro_1.setBounds(37, 144, 215, 29);
					Reservas.add(lblPrestamoDeLibro_1);
				}
				{
					lblNombreDeUsuario_1 = new JLabel("Id Usuario");
					lblNombreDeUsuario_1.setFont(new Font("Century Gothic", Font.PLAIN, 14));
					lblNombreDeUsuario_1.setBounds(296, 77, 193, 26);
					Reservas.add(lblNombreDeUsuario_1);
				}
				{
					btnModificarR = new JButton("Modificar");
					btnModificarR.addActionListener(this);
					btnModificarR.addMouseListener(this);
					btnModificarR.setForeground(Color.WHITE);
					btnModificarR.setFont(new Font("Century Gothic", Font.PLAIN, 20));
					btnModificarR.setBorder(null);
					btnModificarR.setBackground(new Color(128, 128, 255));
					btnModificarR.setBounds(513, 43, 151, 32);
					Reservas.add(btnModificarR);
				}
				{
					btnInsertarR = new JButton("Insertar");
					btnInsertarR.addActionListener(this);
					btnInsertarR.addMouseListener(this);
					btnInsertarR.setForeground(Color.WHITE);
					btnInsertarR.setFont(new Font("Century Gothic", Font.PLAIN, 20));
					btnInsertarR.setBorder(null);
					btnInsertarR.setBackground(new Color(128, 128, 255));
					btnInsertarR.setBounds(513, 85, 151, 32);
					Reservas.add(btnInsertarR);
				}
				{
					btnEliminarR = new JButton("Eliminar");
					btnEliminarR.addActionListener(this);
					btnEliminarR.addMouseListener(this);
					btnEliminarR.setForeground(Color.WHITE);
					btnEliminarR.setFont(new Font("Century Gothic", Font.PLAIN, 20));
					btnEliminarR.setBorder(null);
					btnEliminarR.setBackground(new Color(128, 128, 255));
					btnEliminarR.setBounds(513, 128, 151, 32);
					Reservas.add(btnEliminarR);
				}
				{
					scrollPane_1 = new JScrollPane();
					scrollPane_1.setBounds(48, 225, 616, 287);
					Reservas.add(scrollPane_1);
					{
						tablaReserva = new JTable();
						tablaReserva.addMouseListener(this);
						tablaReserva.setModel(new DefaultTableModel(
							new Object[][] {
							},
							new String[] {
								"IDUSU", "ISBN", "N\u00B0", "LIBRO", "USUARIO", "FECHA RESERVA", "ESTADO", "DISPONIBLE"
							}
						));
						tablaReserva.getColumnModel().getColumn(0).setPreferredWidth(0);
						tablaReserva.getColumnModel().getColumn(0).setMinWidth(0);
						tablaReserva.getColumnModel().getColumn(0).setMaxWidth(0);
						tablaReserva.getColumnModel().getColumn(1).setPreferredWidth(0);
						tablaReserva.getColumnModel().getColumn(1).setMinWidth(0);
						tablaReserva.getColumnModel().getColumn(1).setMaxWidth(0);
						tablaReserva.getColumnModel().getColumn(5).setPreferredWidth(100);
						scrollPane_1.setViewportView(tablaReserva);
					}
				}
				
				JButton btnEstado = new JButton("Atendido");
					btnEstado.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				        int fila = tablaReserva.getSelectedRow();
				        if (fila != -1) {
				        	 try {
				        		// 1. Obtener datos desde la tabla
				                 String nroResStr = tablaReserva.getValueAt(fila, 2).toString().trim();
				                 String idUsuario = tablaReserva.getValueAt(fila, 1).toString().trim();
				                 String rawIsbn = tablaReserva.getValueAt(fila, 0).toString().trim();
				              
				                 // Validaciones robustas
				                 int nroReserva = Integer.parseInt(nroResStr);
				                 String cleanIsbn = rawIsbn.replaceAll("[^0-13]", "");
				                 long isbn = Long.parseLong(cleanIsbn);

				                 // 2. Fecha actual como préstamo
				                 LocalDate fechaPres = LocalDate.now();
				                 
				              // 3. Solicitar fecha de devolución
				                 String inputFechaDev = JOptionPane.showInputDialog(
				                     null,
				                     "Ingrese la fecha de devolución (YYYY-MM-DD):",
				                     "Fecha de Devolución",
				                     JOptionPane.QUESTION_MESSAGE
				                 );

				                 // Validar entrada del usuario
				                 if (inputFechaDev == null || inputFechaDev.trim().isEmpty()) {
				                     JOptionPane.showMessageDialog(null, "Debe ingresar una fecha de devolución.");
				                     return;
				                 }
				                 
				                 

				                 // Validar formato de la fecha 
				                 LocalDate fechaDevo;
				                 try {
				                     fechaDevo = LocalDate.parse(inputFechaDev.trim());
				                 } catch (Exception ex) {
				                     JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Use YYYY-MM-DD.");
				                     return;
				                 }
				                 
				                 // 4. Crear el préstamo
				                 Prestamo nuevoPre = new Prestamo();
				                 nuevoPre.setUsuario(idUsuario);
				                 nuevoPre.setIsbn(isbn);
				                 nuevoPre.setFechaPrestamo(fechaPres.toString());
				                 nuevoPre.setFechaDevolucion(fechaDevo.toString());

				                 // 5. Insertar en préstamo y marcar reserva como atendida
				                 if (preDAO.insertar(nuevoPre)) {
				                     resDAO.ReservaAtendida(nroReserva);
				                     Limpiar();
				                     LimpiarTabla();
				                     ListaPre();
				                     ListaRes();
				                     
				                     JOptionPane.showMessageDialog(null, "Reserva convertida a préstamo.");
				                 }

				             } catch (NumberFormatException ex) {
				                 JOptionPane.showMessageDialog(null, "Error al convertir número (nroreserva o ISBN).");
				                 ex.printStackTrace();
				             } catch (Exception ex) {
				                 JOptionPane.showMessageDialog(null, "Error al atender la reserva: " + ex.getMessage());
				                 ex.printStackTrace();
				             
				             }
				         } else {
				             JOptionPane.showMessageDialog(null, "Seleccione una reserva primero.");
				         }
				        }
				    }
				);
					btnEstado.setForeground(Color.WHITE);
					btnEstado.setFont(new Font("Century Gothic", Font.PLAIN, 20));
					btnEstado.setBorder(null);
					btnEstado.setBackground(new Color(128, 128, 255));
					btnEstado.setBounds(513, 171, 151, 32);
					Reservas.add(btnEstado);
					{
						dateReserva = new JDateChooser();
						dateReserva.setBounds(37, 177, 193, 26);
						Reservas.add(dateReserva);
					}
					{
						txtIsbnR = new JTextField();
						txtIsbnR.setBounds(37, 106, 193, 27);
						Reservas.add(txtIsbnR);
						txtIsbnR.setColumns(10);
						{
							popupSugerencias = new JPopupMenu();
							addPopup(txtIsbnR, popupSugerencias);
							{
								mnNewMenu = new JMenu("New menu");
								popupSugerencias.add(mnNewMenu);
							}
						}
					}
					{
						txtUsuR = new JTextField();
						txtUsuR.setBounds(296, 107, 193, 27);
						Reservas.add(txtUsuR);
						txtUsuR.setColumns(10);
						{
							popupMenu = new JPopupMenu();
							addPopup(txtUsuR, popupMenu);
							{
								mnNewMenu_1 = new JMenu("New menu");
								popupMenu.add(mnNewMenu_1);
							}
						}
					}
					{
						lblFechaDevP = new JLabel("");
						lblFechaDevP.setBounds(37, 128, 193, 26);
						Reservas.add(lblFechaDevP);
					}

			}
		}
		setDate();
		this.setLocationRelativeTo(null);
		llenarComboBox();
		AutocompletadoISBNR();
		AutocompletadoUsuR();
		AutocompletadoISBNP();
		AutocompletadoUsuP();
		txtIsbnR.getDocument().addDocumentListener(new DocumentListener() {
		    public void insertUpdate(DocumentEvent e) {
		        mostrarFechasDevolucionPendientes(txtIsbnR.getText().trim());
		    }

		    public void removeUpdate(DocumentEvent e) {
		        mostrarFechasDevolucionPendientes(txtIsbnR.getText().trim());
		    }

		    public void changedUpdate(DocumentEvent e) {
		        mostrarFechasDevolucionPendientes(txtIsbnR.getText().trim());
		    }
		});

	}
	//PARA GENERAR FECHA
		private void setDate() {
			LocalDate now=LocalDate.now();
			int año=now.getYear();
			int dia=now.getDayOfMonth();
			int mes=now.getMonthValue();
			String[] meses={"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Setiembre","Octubre","Noviembre","Diciembre"};
			lblFecha.setText("Hoy es "+dia+" de "+meses[mes-1]+" de "+año);
		}
		

	public void mouseDragged(MouseEvent e) {
		if (e.getSource() == panel_2) {
			do_panel_2_mouseDragged(e);
		}
	}
	public void mouseMoved(MouseEvent e) {
	}
	protected void do_panel_2_mouseDragged(MouseEvent e) {
		int x=e.getXOnScreen();
		int y=e.getYOnScreen();
		this.setLocation(x-xmouse, y-ymouse);
	}
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == panel_2) {
			do_panel_2_mousePressed(e);
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == btnElimEdito) {
			do_btnElimEdito_mouseClicked(e);
		}
		if (e.getSource() == btnElimCate) {
			do_btnElimCate_mouseClicked(e);
		}
		if (e.getSource() == btnInserEdito) {
			do_btnInserEdito_mouseClicked(e);
		}
		if (e.getSource() == lblCategoria) {
			do_lblCategoria_mouseClicked(e);
		}
		if (e.getSource() == tablaEditorial) {
			do_tablaEditorial_mouseClicked(e);
		}
		if (e.getSource() == tablaCategoria) {
			do_tablaCategoria_mouseClicked(e);
		}
		if (e.getSource() == tablaLibro) {
			do_tablaLibro_mouseClicked(e);
		}
		if (e.getSource() == tablaPrestamo) {
			do_tablaPrestamo_mouseClicked(e);
		}
		if (e.getSource() == tablaReserva) {
			do_tablaReserva_mouseClicked(e);
		}
		if (e.getSource() == tablaUsuarios) {
			do_tablaUsuarios_mouseClicked(e);
		}
		
		if (e.getSource() == lblCerrar) {
			do_lblCerrar_mouseClicked(e);
		}
		if (e.getSource() == lblLibros) {
			do_lblLibros_mouseClicked(e);
		}
		if (e.getSource() == lblUsuarios) {
			do_lblUsuarios_mouseClicked(e);
		}
		if (e.getSource() == lblReserva) {
			do_lblReserva_mouseClicked(e);
		}
		if (e.getSource() == lblPrestamo) {
			do_lblPrestamo_mouseClicked(e);
		}
		if (e.getSource() == lblPrincipal) {
			do_lblPrincipal_mouseClicked(e);
		}
	}
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == lblCerrar) {
			do_lblCerrar_mouseEntered(e);
		}
		if (e.getSource() == lblCategoria) {
			do_lblCategoria_mouseEntered(e);
		}
		if (e.getSource() == lblLibros) {
			do_lblLibros_mouseEntered(e);
		}
		if (e.getSource() == lblUsuarios) {
			do_lblUsuarios_mouseEntered(e);
		}
		if (e.getSource() == lblReserva) {
			do_lblReserva_mouseEntered(e);
		}
		if (e.getSource() == lblPrestamo) {
			do_lblPrestamo_mouseEntered(e);
		}
		if (e.getSource() == lblPrincipal) {
			do_lblPrincipal_mouseEntered(e);
		}
		if (e.getSource() == lblPrincipal) {
			do_lblPrincipal_mouseEntered(e);
		}
		if (e.getSource() == btnEliminarL) {
			do_btnEliminarL_mouseEntered(e);
		}
		if (e.getSource() == btnInsertarL) {
			do_btnInsertarL_mouseEntered(e);
		}
		if (e.getSource() == btnModificarL) {
			do_btnModificarL_mouseEntered(e);
		}
		if (e.getSource() == btnInsertarU) {
			do_btnInsertarU_mouseEntered(e);
		}
		if (e.getSource() == btnModificarU) {
			do_btnModificarU_mouseEntered(e);
		}
		if (e.getSource() == btnEliminarR) {
			do_btnEliminarR_mouseEntered(e);
		}
		if (e.getSource() == btnInsertarR) {
			do_btnInsertarR_mouseEntered(e);
		}
		if (e.getSource() == btnModificarR) {
			do_btnModificarR_mouseEntered(e);
		}
		if (e.getSource() == btnEliminarP) {
			do_btnEliminarP_mouseEntered(e);
		}
		if (e.getSource() == btnInsertarP) {
			do_btnInsertarP_mouseEntered(e);
		}
		if (e.getSource() == btnModificarP) {
			do_btnModificarP_mouseEntered(e);
		}

	}
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == lblCerrar) {
			do_lblCerrar_mouseExited(e);
		}
		if (e.getSource() == lblCategoria) {
			do_lblCategoria_mouseExited(e);
		}
		if (e.getSource() == lblLibros) {
			do_lblLibros_mouseExited(e);
		}
		if (e.getSource() == lblUsuarios) {
			do_lblUsuarios_mouseExited(e);
		}
		if (e.getSource() == lblReserva) {
			do_lblReserva_mouseExited(e);
		}
		if (e.getSource() == lblPrestamo) {
			do_lblPrestamo_mouseExited(e);
		}
		if (e.getSource() == lblPrincipal) {
			do_lblPrincipal_mouseExited(e);
		}
		if (e.getSource() == btnEliminarL) {
			do_btnEliminarL_mouseExited(e);
		}
		if (e.getSource() == btnInsertarL) {
			do_btnInsertarL_mouseExited(e);
		}
		if (e.getSource() == btnModificarL) {
			do_btnModificarL_mouseExited(e);
		}
		if (e.getSource() == btnInsertarU) {
			do_btnInsertarU_mouseExited(e);
		}
		if (e.getSource() == btnModificarU) {
			do_btnModificarU_mouseExited(e);
		}
		if (e.getSource() == btnEliminarR) {
			do_btnEliminarR_mouseExited(e);
		}
		if (e.getSource() == btnInsertarR) {
			do_btnInsertarR_mouseExited(e);
		}
		if (e.getSource() == btnModificarR) {
			do_btnModificarR_mouseExited(e);
		}
		if (e.getSource() == btnEliminarP) {
			do_btnEliminarP_mouseExited(e);
		}
		if (e.getSource() == btnInsertarP) {
			do_btnInsertarP_mouseExited(e);
		}
		if (e.getSource() == btnModificarP) {
			do_btnModificarP_mouseExited(e);
		}
	}
	
	public void mouseReleased(MouseEvent e) {
	}
	protected void do_panel_2_mousePressed(MouseEvent e) {
		xmouse=e.getX();
		ymouse=e.getY();
	}
	//PANEL PRESTAMOS
	protected void do_btnModificarP_mouseEntered(MouseEvent e) {
		btnModificarP.setBackground(new Color(0,0,255));	
		Font fuente = new Font("Century Gothic", Font.BOLD, 20);
		btnModificarP.setFont(fuente);
	}
	protected void do_btnModificarP_mouseExited(MouseEvent e) {
		btnModificarP.setBackground(new Color(128,128,255));	
		Font fuente = new Font("Century Gothic", Font.PLAIN, 20);
		btnModificarP.setFont(fuente);
	}
	protected void do_btnInsertarP_mouseEntered(MouseEvent e) {
		btnInsertarP.setBackground(new Color(0,0,255));	
		Font fuente = new Font("Century Gothic", Font.BOLD, 20);
		btnInsertarP.setFont(fuente);
	}
	protected void do_btnInsertarP_mouseExited(MouseEvent e) {
		btnInsertarP.setBackground(new Color(128,128,255));	
		Font fuente = new Font("Century Gothic", Font.PLAIN, 20);
		btnInsertarP.setFont(fuente);
	}
	protected void do_btnEliminarP_mouseEntered(MouseEvent e) {
		btnEliminarP.setBackground(new Color(0,0,255));	
		Font fuente = new Font("Century Gothic", Font.BOLD, 20);
		btnEliminarP.setFont(fuente);
	}
	protected void do_btnEliminarP_mouseExited(MouseEvent e) {
		btnEliminarP.setBackground(new Color(128,128,255));	
		Font fuente = new Font("Century Gothic", Font.PLAIN, 20);
		btnEliminarP.setFont(fuente);
	}
	//PANEL RESERVA
	protected void do_btnModificarR_mouseEntered(MouseEvent e) {
		btnModificarR.setBackground(new Color(0,0,255));	
		Font fuente = new Font("Century Gothic", Font.BOLD, 20);
		btnModificarR.setFont(fuente);
	}
	protected void do_btnModificarR_mouseExited(MouseEvent e) {
		btnModificarR.setBackground(new Color(128,128,255));	
		Font fuente = new Font("Century Gothic", Font.PLAIN, 20);
		btnModificarR.setFont(fuente);
	}
	protected void do_btnInsertarR_mouseEntered(MouseEvent e) {
		btnInsertarR.setBackground(new Color(0,0,255));	
		Font fuente = new Font("Century Gothic", Font.BOLD, 20);
		btnInsertarR.setFont(fuente);
	}
	protected void do_btnInsertarR_mouseExited(MouseEvent e) {
		btnInsertarR.setBackground(new Color(128,128,255));	
		Font fuente = new Font("Century Gothic", Font.PLAIN, 20);
		btnInsertarR.setFont(fuente);
	}
	protected void do_btnEliminarR_mouseEntered(MouseEvent e) {
		btnEliminarR.setBackground(new Color(0,0,255));	
		Font fuente = new Font("Century Gothic", Font.BOLD, 20);
		btnEliminarR.setFont(fuente);
	}
	protected void do_btnEliminarR_mouseExited(MouseEvent e) {
		btnEliminarR.setBackground(new Color(128,128,255));	
		Font fuente = new Font("Century Gothic", Font.PLAIN, 20);
		btnEliminarR.setFont(fuente);
	}
	//PANEL USUARIO
	protected void do_btnModificarU_mouseEntered(MouseEvent e) {
		btnModificarU.setBackground(new Color(0,0,255));	
		Font fuente = new Font("Century Gothic", Font.BOLD, 20);
		btnModificarU.setFont(fuente);
	}
	protected void do_btnModificarU_mouseExited(MouseEvent e) {
		btnModificarU.setBackground(new Color(128,128,255));	
		Font fuente = new Font("Century Gothic", Font.PLAIN, 20);
		btnModificarU.setFont(fuente);
	}
	protected void do_btnInsertarU_mouseEntered(MouseEvent e) {
		btnInsertarU.setBackground(new Color(0,0,255));	
		Font fuente = new Font("Century Gothic", Font.BOLD, 20);
		btnInsertarU.setFont(fuente);
	}
	protected void do_btnInsertarU_mouseExited(MouseEvent e) {
		btnInsertarU.setBackground(new Color(128,128,255));	
		Font fuente = new Font("Century Gothic", Font.PLAIN, 20);
		btnInsertarU.setFont(fuente);
	}
	//PANEL LIBROS
	protected void do_btnModificarL_mouseEntered(MouseEvent e) {
		btnModificarL.setBackground(new Color(0,0,255));	
		Font fuente = new Font("Century Gothic", Font.BOLD, 20);
		btnModificarL.setFont(fuente);
	}
	protected void do_btnModificarL_mouseExited(MouseEvent e) {
		btnModificarL.setBackground(new Color(128,128,255));	
		Font fuente = new Font("Century Gothic", Font.PLAIN, 20);
		btnModificarL.setFont(fuente);
	}
	protected void do_btnInsertarL_mouseEntered(MouseEvent e) {
		btnInsertarL.setBackground(new Color(0,0,255));	
		Font fuente = new Font("Century Gothic", Font.BOLD, 20);
		btnInsertarL.setFont(fuente);
	}
	protected void do_btnInsertarL_mouseExited(MouseEvent e) {
		btnInsertarL.setBackground(new Color(128,128,255));	
		Font fuente = new Font("Century Gothic", Font.PLAIN, 20);
		btnInsertarL.setFont(fuente);
	}
	protected void do_btnEliminarL_mouseEntered(MouseEvent e) {
		btnEliminarL.setBackground(new Color(0,0,255));	
		Font fuente = new Font("Century Gothic", Font.BOLD, 20);
		btnEliminarL.setFont(fuente);
	}
	protected void do_btnEliminarL_mouseExited(MouseEvent e) {
		btnEliminarL.setBackground(new Color(128,128,255));	
		Font fuente = new Font("Century Gothic", Font.PLAIN, 20);
		btnEliminarL.setFont(fuente);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == rbtnActivo) {
			do_rbtnActivo_actionPerformed(e);
		}
		if (e.getSource() == rbtnInactivo) {
			do_rdbtnNewRadioButton_1_actionPerformed(e);
		}
		if (e.getSource() == btnModifiEdito) {
			do_btnModifiEdito_actionPerformed(e);
		}
		if (e.getSource() == btnInserEdito) {
			do_btnInserEdito_actionPerformed(e);
		}
		if (e.getSource() == btnElimEdito) {
			do_btnElimEdito_actionPerformed(e);
		}
		if (e.getSource() == btnElimCate) {
			do_btnElimCate_actionPerformed(e);
		}
		if (e.getSource() == btnModiCate) {
			do_btnModiCate_actionPerformed(e);
		}
		if (e.getSource() == btnInserCate) {
			do_btnInserCate_actionPerformed(e);
		}
		
		if (e.getSource() == btnEliminarP) {
			do_btnEliminarP_actionPerformed(e);
		}
		if (e.getSource() == btnModificarP) {
			do_btnModificarP_actionPerformed(e);
		}
		if (e.getSource() == btnInsertarP) {
			do_btnInsertarP_actionPerformed(e);
		}
		if (e.getSource() == btnEliminarR) {
			do_btnEliminarR_actionPerformed(e);
		}
		if (e.getSource() == btnModificarR) {
			do_btnModificarR_actionPerformed(e);
		}
		if (e.getSource() == btnInsertarR) {
			do_btnInsertarR_actionPerformed(e);
		}
		if (e.getSource() == btnEliminarL) {
			do_btnEliminarL_actionPerformed(e);
		}
		if (e.getSource() == btnModificarL) {
			do_btnModificarL_actionPerformed(e);
		}
		if (e.getSource() == btnInsertarL) {
			do_btnInsertarL_actionPerformed(e);
		}
		if (e.getSource() == btnModificarU) {
			do_btnModificarU_actionPerformed(e);
		}
		if (e.getSource() == btnInsertarU) {
			do_btnInsertarU_actionPerformed(e);
		}
		// TODO Auto-generated method stub
		
	}
	
	//PANELES 
	protected void do_lblPrincipal_mouseEntered(MouseEvent e) {
		pnlPrincipal.setBackground(new Color(0,0,255));	
		Font fuente = new Font("Century Gothic", Font.BOLD, 20);
		lblPrincipal.setFont(fuente);
	}
	protected void do_lblPrincipal_mouseExited(MouseEvent e) {
		pnlPrincipal.setBackground(new Color(128,128,255));	
		Font fuente = new Font("Century Gothic", Font.PLAIN, 20);
		lblPrincipal.setFont(fuente);
	}
	protected void do_lblPrincipal_mouseClicked(MouseEvent e) {
		tablaContenido.setSelectedIndex(0);		
	}
	protected void do_lblPrestamo_mouseEntered(MouseEvent e) {
		pnlPrestamo.setBackground(new Color(0,0,255));	
		Font fuente = new Font("Century Gothic", Font.BOLD, 20);
		lblPrestamo.setFont(fuente);
	}
	protected void do_lblPrestamo_mouseExited(MouseEvent e) {
		pnlPrestamo.setBackground(new Color(128,128,255));	
		Font fuente = new Font("Century Gothic", Font.PLAIN, 20);
		lblPrestamo.setFont(fuente);
	}
	protected void do_lblPrestamo_mouseClicked(MouseEvent e) {
	tablaContenido.setSelectedIndex(2);
	LimpiarTabla();
	Limpiar();
	ListaPre();
	}
	protected void do_lblReserva_mouseEntered(MouseEvent e) {
		pnlReserva.setBackground(new Color(0,0,255));	
		Font fuente = new Font("Century Gothic", Font.BOLD, 20);
		lblReserva.setFont(fuente);
	}
	protected void do_lblReserva_mouseExited(MouseEvent e) {
		pnlReserva.setBackground(new Color(128,128,255));	
		Font fuente = new Font("Century Gothic", Font.PLAIN, 20);
		lblReserva.setFont(fuente);
	}
	protected void do_lblReserva_mouseClicked(MouseEvent e) {
	tablaContenido.setSelectedIndex(5);
	LimpiarTabla();
	Limpiar();
	ListaRes();
	}
	protected void do_lblUsuarios_mouseEntered(MouseEvent e) {
		pnlUsuarios.setBackground(new Color(0,0,255));	
		Font fuente = new Font("Century Gothic", Font.BOLD, 20);
		lblUsuarios.setFont(fuente);
	}
	protected void do_lblUsuarios_mouseExited(MouseEvent e) {
		pnlUsuarios.setBackground(new Color(128,128,255));	
		Font fuente = new Font("Century Gothic", Font.PLAIN, 20);
		lblUsuarios.setFont(fuente);
	}
	protected void do_lblUsuarios_mouseClicked(MouseEvent e) {
	tablaContenido.setSelectedIndex(3);
	LimpiarTabla();
	Limpiar();
	ListaUs();
	}
	protected void do_lblLibros_mouseEntered(MouseEvent e) {
		pnlLibros.setBackground(new Color(0,0,255));	
		Font fuente = new Font("Century Gothic", Font.BOLD, 20);
		lblLibros.setFont(fuente);
	}
	protected void do_lblLibros_mouseExited(MouseEvent e) {
		pnlLibros.setBackground(new Color(128,128,255));	
		Font fuente = new Font("Century Gothic", Font.PLAIN, 20);
		lblLibros.setFont(fuente);
	}
	protected void do_lblLibros_mouseClicked(MouseEvent e) {
	tablaContenido.setSelectedIndex(1);
	Limpiar();
	LimpiarTabla();
	ListaLib();
	}
	protected void do_lblCerrar_mouseEntered(MouseEvent e) {
		pnlCerrar.setBackground(new Color(0,0,255));	
		Font fuente = new Font("Century Gothic", Font.BOLD, 16);
		lblCerrar.setFont(fuente);
	}
	protected void do_lblCategoria_mouseEntered(MouseEvent e) {
		pnlCat.setBackground(new Color(0,0,255));	
		Font fuente = new Font("Century Gothic", Font.BOLD, 16);
		lblCategoria.setFont(fuente);
	}
	protected void do_lblCerrar_mouseExited(MouseEvent e) {
		pnlCerrar.setBackground(new Color(128,128,255));	
		Font fuente = new Font("Century Gothic", Font.PLAIN, 16);
		lblCerrar.setFont(fuente);
	}
	protected void do_lblCategoria_mouseExited(MouseEvent e) {
		pnlCat.setBackground(new Color(128,128,255));	
		Font fuente = new Font("Century Gothic", Font.PLAIN, 16);
		lblCategoria.setFont(fuente);
	}
	protected void do_lblCerrar_mouseClicked(MouseEvent e) {
	System.exit(0);
	}
	protected void do_btnInsertarU_actionPerformed(ActionEvent e) {
		String id = txtIdU.getText().trim();
	    String nombre = txtNombreU.getText().trim();
	    String correo = txtCorreoU.getText().trim();
	    String direccion = txtDirecU.getText().trim();
	    String telefono = txtTelefU.getText().trim();
	    String rolCombo = (String) comboBoxRol.getSelectedItem();
	    String rolBD = "Administrador".equalsIgnoreCase(rolCombo) ? "admin" : "usuario";
	    String pin = txtPinNumerico.getText().trim();

	    // Validaciones
	    String r1 = ValidarUsuario.validarID(id);
	    if (!"OK".equals(r1)) { JOptionPane.showMessageDialog(null, r1); return; }

	    String r2 = ValidarUsuario.validarNombre(nombre);
	    if (!"OK".equals(r2)) { JOptionPane.showMessageDialog(null, r2); return; }

	    String r3 = ValidarUsuario.validarCorreo(correo);
	    if (!"OK".equals(r3)) { JOptionPane.showMessageDialog(null, r3); return; }

	    String r4 = ValidarUsuario.validarTelefono(telefono);
	    if (!"OK".equals(r4)) { JOptionPane.showMessageDialog(null, r4); return; }

	    if ("admin".equals(rolBD)) {
	        String r5 = ValidarUsuario.validarPin(pin);
	        if (!"OK".equals(r5)) { JOptionPane.showMessageDialog(null, r5); return; }
	    }

	    // Crear y registrar el usuario
	    usu.setIdUsuario(id);
	    usu.setNombre(nombre);
	    usu.setCorreo(correo);
	    usu.setDireccion(direccion);
	    usu.setTelefono(telefono);
	    usu.setRol(rolBD);
	    if ("admin".equals(rolBD)) {
	        usu.setContra(pin);
	    }

	    if (usuDAO.insertar(usu)) {
	        JOptionPane.showMessageDialog(null, "Usuario registrado con éxito.");
	    } else {
	        JOptionPane.showMessageDialog(null, "Error al registrar el usuario.");
	    }

	    LimpiarTabla();
	    Limpiar();
	    ListaUs();
	}
	
	protected void do_btnModificarU_actionPerformed(ActionEvent e) {
		 String id = txtIdU.getText().trim();
		    String nombre = txtNombreU.getText().trim();
		    String correo = txtCorreoU.getText().trim();
		    String direccion = txtDirecU.getText().trim();
		    String telefono = txtTelefU.getText().trim();
		    String rolCombo = (String) comboBoxRol.getSelectedItem();
		    String rolBD = "Administrador".equalsIgnoreCase(rolCombo) ? "admin" : "usuario";
		    String pin = txtPinNumerico.getText().trim();

		    // Validaciones
		    String v1 = ValidarUsuario.validarID(id);
		    if (!"OK".equals(v1)) { JOptionPane.showMessageDialog(null, v1); return; }

		    String v2 = ValidarUsuario.validarNombre(nombre);
		    if (!"OK".equals(v2)) { JOptionPane.showMessageDialog(null, v2); return; }

		    String v3 = ValidarUsuario.validarCorreo(correo);
		    if (!"OK".equals(v3)) { JOptionPane.showMessageDialog(null, v3); return; }

		    String v4 = ValidarUsuario.validarTelefono(telefono);
		    if (!"OK".equals(v4)) { JOptionPane.showMessageDialog(null, v4); return; }

		    if ("admin".equals(rolBD)) {
		        String v5 = ValidarUsuario.validarPin(pin);
		        if (!"OK".equals(v5)) { JOptionPane.showMessageDialog(null, v5); return; }
		    }

		    // Crear objeto
		    usu.setIdUsuario(id);
		    usu.setNombre(nombre);
		    usu.setCorreo(correo);
		    usu.setDireccion(direccion);
		    usu.setTelefono(telefono);
		    usu.setRol(rolBD);
		    if ("admin".equals(rolBD)) {
		        usu.setContra(pin);
		    }

		    usuDAO.modificar(usu);

		    LimpiarTabla();
		    Limpiar();
		    ListaUs();
	}
	
	void Limpiar() {
		
		txtCorreoU.setText("");
		txtDirecU.setText("");
		txtIdU.setText("");
		txtNombreU.setText("");
		txtTelefU.setText("");
		txtAutor.setText("");
		txtIsbn.setText("");
		txtTitulo.setText("");
		txtStock.setText("");
		txtPinNumerico.setText("");
		txtNomCate.setText("");
		txtNomEdito.setText("");
		txtIsbnR.setText("");
		txtUsuR.setText("");
		txtIsbnP.setText("");
		txtUsuP.setText("");
		lblFechaDevP.setText("");
	}
	protected void do_btnInsertarL_actionPerformed(ActionEvent e) {
		String isbnIngresado = txtIsbn.getText().trim();
	    String tituloIngresado = txtTitulo.getText().trim();
	    String autorIngresado = txtAutor.getText().trim();
	    String stockIngresado = txtStock.getText().trim();

	    String categoriaIngresada = cboCate.getSelectedItem() != null ? cboCate.getSelectedItem().toString() : "";
	    String editorialIngresada = cboEdit.getSelectedItem() != null ? cboEdit.getSelectedItem().toString() : "";

	    List<String> isbnsExistentes = libDAO.obtenerISBN();

	    String resultadoISBN = ValidarLibro.validarISBN(isbnIngresado, isbnsExistentes);
	    if (!resultadoISBN.equals("OK")) {
	        JOptionPane.showMessageDialog(null, resultadoISBN);
	        return;
	    }

	    String resultadoTitulo = ValidarLibro.validarTitulo(tituloIngresado);
	    if (!resultadoTitulo.equals("OK")) {
	        JOptionPane.showMessageDialog(null, resultadoTitulo);
	        return;
	    }

	    String resultadoAutor = ValidarLibro.validarAutor(autorIngresado);
	    if (!resultadoAutor.equals("OK")) {
	        JOptionPane.showMessageDialog(null, resultadoAutor);
	        return;
	    }

	    String resultadoStock = ValidarLibro.validarStock(stockIngresado);
	    if (!resultadoStock.equals("OK")) {
	        JOptionPane.showMessageDialog(null, resultadoStock);
	        return;
	    }

	    Libro lib = new Libro();
	    lib.setIsbn(Long.parseLong(isbnIngresado.replaceAll("[\\s-]", "")));
	    lib.setTitulo(tituloIngresado);
	    lib.setAutor(autorIngresado);
	    lib.setCategoria(categoriaIngresada);
	    lib.setEditorial(editorialIngresada);
	    lib.setStock(Integer.parseInt(stockIngresado));

	    try {
	        if (libDAO.insertar(lib)) {
	            JOptionPane.showMessageDialog(null, "Libro registrado correctamente.");
	        } else {
	            JOptionPane.showMessageDialog(null, "No se pudo registrar el libro.");
	        }
	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(null, "Error al insertar el libro: " + ex.getMessage());
	    }

	    Limpiar();
	    LimpiarTabla();
	    ListaLib();
	}
	protected void do_btnModificarL_actionPerformed(ActionEvent e) {
		try {
	        String isbnIngresado = txtIsbn.getText().trim();
	        String tituloIngresado = txtTitulo.getText().trim();
	        String autorIngresado = txtAutor.getText().trim();
	        String stockIngresado = txtStock.getText().trim();

	        Categoria categoria = (Categoria) cboCate.getSelectedItem();
	        Editorial editorial = (Editorial) cboEdit.getSelectedItem();

	        String resultadoISBN = ValidarLibro.validarISBN(isbnIngresado, new ArrayList<>());
	        if (!resultadoISBN.equals("OK")) {
	            JOptionPane.showMessageDialog(null, resultadoISBN);
	            return;
	        }

	        String resultadoTitulo = ValidarLibro.validarTitulo(tituloIngresado);
	        if (!resultadoTitulo.equals("OK")) {
	            JOptionPane.showMessageDialog(null, resultadoTitulo);
	            return;
	        }

	        String resultadoAutor = ValidarLibro.validarAutor(autorIngresado);
	        if (!resultadoAutor.equals("OK")) {
	            JOptionPane.showMessageDialog(null, resultadoAutor);
	            return;
	        }

	        String resultadoStock = ValidarLibro.validarStock(stockIngresado);
	        if (!resultadoStock.equals("OK")) {
	            JOptionPane.showMessageDialog(null, resultadoStock);
	            return;
	        }

	        long isbn = Long.parseLong(isbnIngresado);
	        int idCategoria = categoria.getId_categoria();
	        int idEditorial = editorial.getId_edit();
	        int stock = Integer.parseInt(stockIngresado);

	        boolean modificado = libDAO.modificar(isbn, tituloIngresado, autorIngresado, idCategoria, idEditorial, stock);
	        if (modificado) {
	            JOptionPane.showMessageDialog(null, "Libro modificado correctamente.");
	        } else {
	            JOptionPane.showMessageDialog(null, "No se pudo modificar. El libro está en uso o no existe.");
	        }

	    } catch (NumberFormatException ex) {
	        JOptionPane.showMessageDialog(null, "Error: Asegúrese de que el ISBN y el stock sean numéricos válidos.");
	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(null, "Error al modificar el libro: " + ex.getMessage());
	    }

	    LimpiarTabla();
	    Limpiar();
	    ListaLib();
	}

	protected void do_btnEliminarL_actionPerformed(ActionEvent e) {
		try {
	        String isbnText = txtIsbn.getText().trim();
	        if (isbnText.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Por favor, ingrese el ISBN del libro.");
	            return;
	        }

	        long isbn = Long.parseLong(isbnText);

	        boolean eliminado = libDAO.eliminar(isbn);
	        if (eliminado) {
	            JOptionPane.showMessageDialog(null, "Libro eliminado correctamente.");
	        } else {
	            JOptionPane.showMessageDialog(null, "No se puede eliminar. El libro está en uso o no existe.");
	        }

	    } catch (NumberFormatException ex) {
	        JOptionPane.showMessageDialog(null, "El ISBN ingresado no es válido.");
	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(null, "Error al eliminar el libro: " + ex.getMessage());
	    }

	    LimpiarTabla();
	    Limpiar();
	    ListaLib();
	}

	
	
	protected void do_btnInsertarR_actionPerformed(ActionEvent e) {
		
		
		 try {
		        Date fechaSeleccionada = dateReserva.getDate();
		        String Usuario = txtUsuR.getText().trim();
		        String titulo = txtIsbnR.getText().trim();

		        if (fechaSeleccionada == null || usu == null || lib == null) {
		            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
		            return;
		        }
		        
		        Usuario usu = usuDAO.buscarUsuarioPorNombreExacto(Usuario);
		        if (usu == null) {
		            JOptionPane.showMessageDialog(null, "Usuario no válido o no encontrado (debe estar activo y ser de rol usuario).");
		            return;
		        }
		        
		        // Buscar el libro por título exacto
		        Libro lib = libDAO.buscarLibroPorTituloExacto(titulo);

		        if (lib == null) {
		            JOptionPane.showMessageDialog(null, "Libro no encontrado.");
		            return;
		        }
		        

		        // Formatear fecha como String (por ejemplo: "2025-05-28")
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		        String fecha = sdf.format(fechaSeleccionada);

		     // 1. Fecha válida
		        String r1 = ValidarReserva.validarFecha(fecha);
		        if (!r1.equals("OK")) {
		            JOptionPane.showMessageDialog(null, r1);
		            return;
		        }

		        // 2. No duplicado
		        String r2 = ValidarReserva.validarReservaDuplicada(usu.getIdUsuario(), lib.getIsbn() );
		        if (!r2.equals("OK")) {
		            JOptionPane.showMessageDialog(null, r2);
		            return;
		        }

		        
		        
		        
		        Reserva re = new Reserva();
		        re.setIdusu(usu.getIdUsuario());
		        re.setIsbn(lib.getIsbn());
		        re.setFechaReserva(fecha);

		        if (resDAO.insertar(re)) {
		            JOptionPane.showMessageDialog(null, "Reserva registrada correctamente.");
		            Limpiar();
		            LimpiarTabla();
		            ListaRes();		        
		        }

		    } catch (SQLException ex) {
		        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		    }
	}

	protected void do_btnModificarR_actionPerformed(ActionEvent e) {
		try {
	        // Obtener fecha desde JDateChooser
	        Date fechaSeleccionada = dateReserva.getDate();
	        String Usuario = txtUsuR.getText().trim();
	        String titulo = txtIsbnR.getText().trim();

	        if (fechaSeleccionada == null) {
	            JOptionPane.showMessageDialog(null, "Seleccione una fecha de reserva.");
	            return;
	        }
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        String fecha = sdf.format(fechaSeleccionada);

	        Usuario usu = usuDAO.buscarUsuarioPorNombreExacto(Usuario);
	        if (usu == null) {
	            JOptionPane.showMessageDialog(null, "Usuario no válido o no encontrado.");
	            return;
	        }
	        
	     // Buscar el libro por título exacto
	        Libro lib = libDAO.buscarLibroPorTituloExacto(titulo);
	        if (lib == null) {
	            JOptionPane.showMessageDialog(null, "Libro no encontrado.");
	            return;
	        }

	        String r1 = ValidarReserva.validarFecha(fecha);
	        if (!r1.equals("OK")) {
	            JOptionPane.showMessageDialog(null, r1);
	            return;
	        }

	        // 2. No duplicado
	        String r2 = ValidarReserva.validarReservaDuplicada(usu.getIdUsuario(), lib.getIsbn() );
	        if (!r2.equals("OK")) {
	            JOptionPane.showMessageDialog(null, r2);
	            return;
	        }
	       

	        boolean actualizado = resDAO.modificar(lib.getIsbn(), usu.getIdUsuario(), fecha);

	        if (actualizado) {
	            JOptionPane.showMessageDialog(null, "Reserva modificada correctamente.");
	        } else {
	            JOptionPane.showMessageDialog(null, "No se encontró ninguna reserva para modificar.");
	        }

	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(null, "Error al modificar: " + ex.getMessage());
	    }

	    Limpiar();
	    LimpiarTabla();
	    ListaRes();
	    llenarComboBox();
	}


	protected void do_btnEliminarR_actionPerformed(ActionEvent e) {
		  try {
		        int fila = tablaReserva.getSelectedRow();

		        if (fila == -1) {
		            JOptionPane.showMessageDialog(null, "Seleccione una reserva para eliminar.");
		            return;
		        }

		        // Obtener el número de reserva desde la tabla
		        int nroReserva = Integer.parseInt(tablaReserva.getValueAt(fila, 2).toString());

		        // Confirmación (opcional)
		        int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar esta reserva?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
		        if (opcion != JOptionPane.YES_OPTION) {
		            return;
		        }

		        // Llamar a DAO con el nroreserva
		        boolean eliminado = resDAO.eliminar(nroReserva);

		        if (eliminado) {
		            JOptionPane.showMessageDialog(null, "Reserva eliminada correctamente.");
		        } else {
		            JOptionPane.showMessageDialog(null, "No se encontró ninguna reserva para eliminar.");
		        }

		    } catch (SQLException ex) {
		        JOptionPane.showMessageDialog(null, "Error al eliminar la reserva: " + ex.getMessage());
		    }

		    Limpiar();
		    LimpiarTabla();
		    ListaRes();
		    llenarComboBox();
	}

	
	protected void do_btnInsertarP_actionPerformed(ActionEvent e) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Formato que usarás

	    Date fechaPresDate = dateFechaP.getDate(); // txtFechaPres es un JDateChooser
	    Date fechaDevoDate = dateFechaDev.getDate(); // txtFechaDevo también

	    String nombre = txtUsuP.getText().trim();
	    String titulo = txtIsbnP.getText().trim();

	    if (nombre.isEmpty() || titulo.isEmpty() || fechaPresDate == null || fechaDevoDate == null) {
	        JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
	        return;
	    }

	    Usuario usu = usuDAO.buscarUsuarioPorNombreExacto(nombre);
	    if (usu == null) {
	        JOptionPane.showMessageDialog(null, "Usuario no válido o no encontrado (debe estar activo y ser de rol usuario).");
	        return;
	    }

	    // Buscar el libro por título exacto
	    Libro lib = libDAO.buscarLibroPorTituloExacto(titulo);
        if (lib == null) {
            JOptionPane.showMessageDialog(null, "Libro no encontrado.");
            return;
        }
	    
	    // Convertir fechas a String
	    String fechaPrestamo = sdf.format(fechaPresDate);
	    String fechaDevolucion = sdf.format(fechaDevoDate);

	    // VALIDACIONES:
	    String resultado1 = ValidarPrestamo.validarFechasNoAnteriores(fechaPrestamo, fechaDevolucion);
	    if (!resultado1.equals("OK")) {
	        JOptionPane.showMessageDialog(null, resultado1);
	        return;
	    }

	    String resultado2 = ValidarPrestamo.validarRangoMaximo5Dias(fechaPrestamo, fechaDevolucion);
	    if (!resultado2.equals("OK")) {
	        JOptionPane.showMessageDialog(null, resultado2);
	        return;
	    }

	    String resultado3 = ValidarPrestamo.validarPrestamoDuplicado(lib.getIsbn(), usu.getIdUsuario());
	    if (!resultado3.equals("OK")) {
	        JOptionPane.showMessageDialog(null, resultado3);
	        return;
	    }
	    
	    Prestamo pre = new Prestamo();
	    pre.setIsbn(lib.getIsbn());
	    pre.setUsuario(usu.getIdUsuario()); 
	    pre.setFechaPrestamo(fechaPrestamo);
	    pre.setFechaDevolucion(fechaDevolucion);

	    try {
	        boolean exito = preDAO.insertar(pre);
	        if (exito) {
	            JOptionPane.showMessageDialog(null, "Préstamo registrado con éxito.");
	            LimpiarTabla();
	            ListaPre();
	            Limpiar();
	            llenarComboBox();
	        }
	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
	    }
	}

	protected void do_btnModificarP_actionPerformed(ActionEvent e) {
		try {
	        // Formateador de fecha
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	        // Obtener fechas desde JDateChooser
	        Date fechaPresDate = dateFechaP.getDate();
	        Date fechaDevDate = dateFechaDev.getDate();

	        String nombre = txtUsuP.getText().trim();
	        String titulo = txtIsbnP.getText().trim();

	        if (nombre.isEmpty() || titulo.isEmpty() || fechaPresDate == null || fechaDevDate == null) {
	            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
	            return;
	        }
	        
	        Usuario usu = usuDAO.buscarUsuarioPorNombreExacto(nombre);
	        if (usu == null) {
	            JOptionPane.showMessageDialog(null, "Usuario no válido o no encontrado.");
	            return;
	        }
	        
	     // Obtener el libro por título exacto
	        Libro lib = libDAO.buscarLibroPorTituloExacto(titulo);
	        if (lib == null) {
	            JOptionPane.showMessageDialog(null, "Libro no encontrado.");
	            return;
	        }

	        // Convertir fechas a String
	        String fechaPres = sdf.format(fechaPresDate);
	        String fechaDev = sdf.format(fechaDevDate);

	     // VALIDACIONES:
		    String resultado1 = ValidarPrestamo.validarFechasNoAnteriores(fechaPres, fechaDev);
		    if (!resultado1.equals("OK")) {
		        JOptionPane.showMessageDialog(null, resultado1);
		        return;
		    }

		    String resultado2 = ValidarPrestamo.validarRangoMaximo5Dias(fechaPres, fechaDev);
		    if (!resultado2.equals("OK")) {
		        JOptionPane.showMessageDialog(null, resultado2);
		        return;
		    }

		    String resultado3 = ValidarPrestamo.validarPrestamoDuplicado(lib.getIsbn(), usu.getIdUsuario());
		    if (!resultado3.equals("OK")) {
		        JOptionPane.showMessageDialog(null, resultado3);
		        return;
		    }

	        boolean actualizado = preDAO.modificar(lib.getIsbn(), usu.getIdUsuario(), fechaPres, fechaDev);
	        if (actualizado) {
	            JOptionPane.showMessageDialog(null, "Préstamo modificado correctamente.");
	        } else {
	            JOptionPane.showMessageDialog(null, "No se pudo modificar el préstamo.");
	        }

	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
	    }

	    LimpiarTabla();
	    ListaPre();
	    Limpiar();
	    llenarComboBox();
	}
	protected void do_btnEliminarP_actionPerformed(ActionEvent e) {
		try {
	        int fila = tablaPrestamo.getSelectedRow();

	        if (fila == -1) {
	            JOptionPane.showMessageDialog(null, "Seleccione un prestamo para eliminar.");
	            return;
	        }

	        // Obtener el número de reserva desde la tabla
	        int nroprestamo = Integer.parseInt(tablaPrestamo.getValueAt(fila, 2).toString());

	        // Confirmación (opcional)
	        int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar este prestamo?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
	        if (opcion != JOptionPane.YES_OPTION) {
	            return;
	        }

	        // Llamar a DAO con el nroreserva
	        boolean eliminado = preDAO.eliminar(nroprestamo);

	        if (eliminado) {
	            JOptionPane.showMessageDialog(null, "Prestamo eliminado correctamente.");
	        } else {
	            JOptionPane.showMessageDialog(null, "No se encontró ningun prestamo para eliminar.");
	        }

	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(null, "Error al eliminar prestamo: " + ex.getMessage());
	    }

	    Limpiar();
	    LimpiarTabla();
	    ListaPre();
	    llenarComboBox();
	}
	
	public void ListaUs() {
		List<Usuario> ListarU=usuDAO.listarUsu();
		modelo=(DefaultTableModel) tablaUsuarios.getModel();
		Object[] ob=new Object[8];
		for (int i = 0; i < ListarU.size(); i++) {
			Usuario usu = ListarU.get(i);
			ob[0]=usu.getIdUsuario();
			ob[1]=usu.getNombre();
			ob[2]=usu.getCorreo();
			ob[3]=usu.getDireccion();
			ob[4]=usu.getTelefono();
			ob[5]=usu.getRol();
			ob[6]="Administrador".equals(usu.getRol()) ? usu.getContra() : "";
			ob[7]=usu.getEstado();
			System.out.println("PIN para " + usu.getIdUsuario() + ": " + usu.getContra());
			modelo.addRow(ob);
		}
		tablaUsuarios.setModel(modelo);
	}
	public void ListaLib() {
		List<Libro> ListarL=libDAO.ListarLib();
		modelo=(DefaultTableModel) tablaLibro.getModel();
		Object[] ob=new Object[6];
		for (int i = 0; i < ListarL.size(); i++) {
			ob[0]=ListarL.get(i).getIsbn();
			ob[1]=ListarL.get(i).getTitulo();
			ob[2]=ListarL.get(i).getAutor();
			ob[3]=ListarL.get(i).getCategoria();
			ob[4]=ListarL.get(i).getEditorial();
			ob[5]=ListarL.get(i).getStock();
			modelo.addRow(ob);
		}
		tablaLibro.setModel(modelo);
	}
	public void ListaPre() {
		List<Prestamo> ListarP=preDAO.listarPrestamos();
		
		modelo=(DefaultTableModel) tablaPrestamo.getModel();
		Object[] ob=new Object[8];
		for (int i = 0; i < ListarP.size(); i++) {
			ob[1]=ListarP.get(i).getIdusu();
			ob[0]=ListarP.get(i).getIsbn();
			ob[2]=ListarP.get(i).getNroprestamo();
			ob[3]=ListarP.get(i).getNombreLibro();
			ob[4]=ListarP.get(i).getUsuario();
			ob[5]=ListarP.get(i).getFechaPrestamo();
			ob[6]=ListarP.get(i).getFechaDevolucion();
			ob[7]=ListarP.get(i).getEstado();
			modelo.addRow(ob);
		}
		tablaPrestamo.setModel(modelo);
	}
	public void ListaRes() {
		List<Reserva> ListarR=resDAO.listarReserva();
		modelo=(DefaultTableModel) tablaReserva.getModel();
		Object[] ob=new Object[8];
		for (int i = 0; i < ListarR.size(); i++) {
			ob[1]=ListarR.get(i).getIdusu();
			ob[0]=ListarR.get(i).getIsbn();
			ob[2]=ListarR.get(i).getNroreserva();
			ob[3]=ListarR.get(i).getNombreLibro();
			ob[4]=ListarR.get(i).getUsuario();
			ob[5]=ListarR.get(i).getFechaReserva();
			ob[6]=ListarR.get(i).getEstado();
			
			int stock = libDAO.obtenerStockPorISBN(ListarR.get(i).getIsbn());
	        // Solo mostrar "Sí" si la reserva está en espera Y hay stock
	        ob[7] = (ListarR.get(i).getEstado().equalsIgnoreCase("En Espera") && stock > 0) ? "✅ Sí" : "❌ No";
			modelo.addRow(ob);
		}
		tablaReserva.setModel(modelo);
	}
	public void ListarCat() {
	    List<Categoria> ListarC = catDAO.listarCategorias();
	    modelo = (DefaultTableModel) tablaCategoria.getModel();
	    modelo.setRowCount(0); 
	    Object[] ob=new Object[2];
		for (int i = 0; i < ListarC.size(); i++) {
			ob[0]=ListarC.get(i).getId_categoria();
			ob[1]=ListarC.get(i).getNombre();
			modelo.addRow(ob);
		}
		tablaCategoria.setModel(modelo);
	}
	public void ListarEdit() {
	    List<Editorial> ListarE = editDAO.listarEditoriales();
	    modelo = (DefaultTableModel) tablaEditorial.getModel();
	    modelo.setRowCount(0); 
	    Object[] ob=new Object[2];
		for (int i = 0; i < ListarE.size(); i++) {
			ob[0]=ListarE.get(i).getId_edit();
			ob[1]=ListarE.get(i).getNombre();
			modelo.addRow(ob);
		}
		tablaEditorial.setModel(modelo);
	}
	public void LimpiarTabla() {
		for (int i = 0; i < modelo.getRowCount(); i++) {
			modelo.removeRow(i);
			i=i-1;
		}
	}
	
	protected void do_tablaUsuarios_mouseClicked(MouseEvent e) {
		int fila=tablaUsuarios.rowAtPoint(e.getPoint());
		txtIdU.setText(tablaUsuarios.getValueAt(fila, 0).toString());
		txtNombreU.setText(tablaUsuarios.getValueAt(fila, 1).toString());
		txtCorreoU.setText(tablaUsuarios.getValueAt(fila, 2).toString());
		txtDirecU.setText(tablaUsuarios.getValueAt(fila, 3).toString());
		txtTelefU.setText(tablaUsuarios.getValueAt(fila, 4).toString());

	}
	protected void do_tablaLibro_mouseClicked(MouseEvent e) {
		 int fila = tablaLibro.rowAtPoint(e.getPoint());

		    if (fila >= 0) {
		        txtIsbn.setText(tablaLibro.getValueAt(fila, 0).toString());
		        txtTitulo.setText(tablaLibro.getValueAt(fila, 1).toString());
		        txtAutor.setText(tablaLibro.getValueAt(fila, 2).toString());
		        txtStock.setText(tablaLibro.getValueAt(fila, 5).toString());

		        String nombreCategoria = tablaLibro.getValueAt(fila, 3).toString();
		        String nombreEditorial = tablaLibro.getValueAt(fila, 4).toString();

		        // Selecciona la categoría correspondiente en el combo
		        for (int i = 0; i < cboCate.getItemCount(); i++) {
		            Categoria cat = (Categoria) cboCate.getItemAt(i);
		            if (cat.getNombre().equals(nombreCategoria)) {
		                cboCate.setSelectedIndex(i);
		                break;
		            }
		        }

		        // Selecciona la editorial correspondiente en el combo
		        for (int i = 0; i < cboEdit.getItemCount(); i++) {
		            Editorial edi = (Editorial) cboEdit.getItemAt(i);
		            if (edi.getNombre().equals(nombreEditorial)) {
		                cboEdit.setSelectedIndex(i);
		                break;
		            }
		        }
		    }
		
	}
	
	
	protected void do_tablaPrestamo_mouseClicked(MouseEvent e) {
		
		  int fila = tablaPrestamo.rowAtPoint(e.getPoint());

		    if (fila >= 0) {
		        String isbn = tablaPrestamo.getValueAt(fila, 0).toString(); // Columna ISBN
		        String idUsuario = tablaPrestamo.getValueAt(fila, 1).toString(); // Columna Usuario
		        String fechaPres = tablaPrestamo.getValueAt(fila, 5).toString(); // Fecha préstamo
		        String fechaDev = tablaPrestamo.getValueAt(fila, 6).toString(); // Fecha devolución
		        String estado = tablaPrestamo.getValueAt(fila, 7).toString();

		     // Buscar título del libro por ISBN
		        try {
		            List<Libro> libros = libDAO.ListarLib();
		            for (Libro libro : libros) {
		                if (String.valueOf(libro.getIsbn()).equals(isbn)) {
		                    txtIsbnP.setText(libro.getTitulo());
		                    break;
		                }
		            }
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, "Error al cargar el libro: " + ex.getMessage());
		        }

		        // Buscar nombre del usuario por ID
		        try {
		            List<Usuario> usuarios = usuDAO.listarUsu();
		            for (Usuario usuario : usuarios) {
		                if (usuario.getIdUsuario().equals(idUsuario)) {
		                    txtUsuP.setText(usuario.getNombre());
		                    break;
		                }
		            }
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, "Error al cargar el usuario: " + ex.getMessage());
		        }
		        
		        // Asignar fechas al JDateChooser
		        try {
		            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		            Date fechaPrestamoDate = sdf.parse(fechaPres);
		            Date fechaDevolucionDate = sdf.parse(fechaDev);

		            dateFechaP.setDate(fechaPrestamoDate);
		            dateFechaDev.setDate(fechaDevolucionDate);
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, "Error al convertir fechas: " + ex.getMessage());
		        }
		    }
		
	}
	protected void do_tablaReserva_mouseClicked(MouseEvent e) {
		int fila = tablaReserva.rowAtPoint(e.getPoint());

	    if (fila >= 0) {
	        String isbn = tablaReserva.getValueAt(fila, 0).toString(); // ISBN
	        String idUsuario = tablaReserva.getValueAt(fila, 1).toString(); // ID Usuario
	        String fechaRes = tablaReserva.getValueAt(fila, 5).toString(); // Fecha Reserva

	        // Convertir la fecha (String) a Date y establecerla en el JDateChooser
	        try {
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	            Date fecha = sdf.parse(fechaRes);
	            dateReserva.setDate(fecha); // ← Aquí usas tu JDateChooser
	        } catch (ParseException ex) {
	            JOptionPane.showMessageDialog(null, "Error al convertir la fecha: " + ex.getMessage());
	        }

	     // Buscar título por ISBN y colocarlo en txtIsbnR
	        List<Libro> libros = libDAO.ListarLib();
            for (Libro libro : libros) {
                if (String.valueOf(libro.getIsbn()).equals(isbn)) {
                    txtIsbnR.setText(libro.getTitulo());
                    break;
                }
            }

	     // Buscar nombre por ID Usuario y colocarlo en txtUsuarioR
	        List<Usuario> usuarios = usuDAO.listarUsu();
            for (Usuario usuario : usuarios) {
                if (usuario.getIdUsuario().equals(idUsuario)) {
                    txtUsuR.setText(usuario.getNombre());
                    break;
                }
            }
	    }
		
	}
	
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txtIdU) {
			do_txtIdU_keyTyped(e);
		}
	}
	protected void do_txtIdU_keyTyped(KeyEvent e) {
		char validar=e.getKeyChar();
		if(Character.isLetter(validar)) {
			getToolkit().beep();
			e.consume();
			JOptionPane.showMessageDialog(contentPane, "Solo se permite números");
		}
	}
	private void llenarComboBox() {
	    
	    catDAO.cargarCategorias(cboCate);   
	    editDAO.cargarEditoriales(cboEdit);
	}
	protected void do_btnInserCate_actionPerformed(ActionEvent e) {
		 String nombre = txtNomCate.getText().trim();

		    String mensaje = ValidarCatEdi.validarNomC(nombre);
		    if (!mensaje.equals("OK")) {
		        JOptionPane.showMessageDialog(null, mensaje);
		        return;
		    }

		    Categoria nuevaCategoria = new Categoria();
		    nuevaCategoria.setNombre(nombre);

		    CategoriaDAO dao = new CategoriaDAO();
		    try {
		        boolean exito = dao.insertarCategoria(nuevaCategoria);
		        if (exito) {
		            JOptionPane.showMessageDialog(null, "Categoría insertada exitosamente.");
		        } else {
		            JOptionPane.showMessageDialog(null, "No se pudo insertar la categoría.");
		        }
		    } catch (Exception ex) {
		        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		    }

		    LimpiarTabla();
		    ListarCat();
		    Limpiar();
		    ListarEdit();
		    llenarComboBox();
		    
	}
	protected void do_btnModiCate_actionPerformed(ActionEvent e) {
		int filaSeleccionada = tablaCategoria.getSelectedRow();

	    if (filaSeleccionada == -1) {
	        JOptionPane.showMessageDialog(null, "Seleccione una categoría de la tabla.");
	        return;
	    }

	    int idCategoria = Integer.parseInt(tablaCategoria.getValueAt(filaSeleccionada, 0).toString());
	    String nuevoNombre = txtNomCate.getText().trim();

	    String mensaje = ValidarCatEdi.validarNomC(nuevoNombre);
	    if (nuevoNombre.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "El nombre de la categoría no puede estar vacío.");
	        return;
	    }
	    if (!mensaje.equals("OK")) {
	        JOptionPane.showMessageDialog(null, mensaje);
	        return;
	    }

	    Categoria categoriaModi = new Categoria();
	    categoriaModi.setId_categoria(idCategoria);
	    categoriaModi.setNombre(nuevoNombre);
	    
	    CategoriaDAO dao = new CategoriaDAO();
	    try {
	        boolean exito = dao.modificarCategoria(categoriaModi);
	        if (exito) {
	            JOptionPane.showMessageDialog(null, "Categoría modificada exitosamente.");
	        } else {
	            JOptionPane.showMessageDialog(null, "No se pudo modificar la categoría.");
	        }
	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
	    }

	    LimpiarTabla();
	    ListarCat();
	    Limpiar();
	    ListarEdit();
	    llenarComboBox();
	}
	protected void do_tablaCategoria_mouseClicked(MouseEvent e) {
		int fila=tablaCategoria.rowAtPoint(e.getPoint());
		txtNomCate.setText(tablaCategoria.getValueAt(fila, 1).toString());
		
	}
	protected void do_tablaEditorial_mouseClicked(MouseEvent e) {
		int fila=tablaEditorial.rowAtPoint(e.getPoint());
		txtNomEdito.setText(tablaEditorial.getValueAt(fila, 1).toString());
	}
	protected void do_lblCategoria_mouseClicked(MouseEvent e) {
		tablaContenido.setSelectedIndex(4);
		LimpiarTabla();
		Limpiar();
		ListarCat();
		ListarEdit();
	}
	protected void do_btnInserEdito_mouseClicked(MouseEvent e) {
		
	}
	protected void do_btnElimCate_mouseClicked(MouseEvent e) {
		
	}
	protected void do_btnElimEdito_mouseClicked(MouseEvent e) {
		
	}
	protected void do_btnElimCate_actionPerformed(ActionEvent e) {
		int filaSeleccionada = tablaCategoria.getSelectedRow();

	    if (filaSeleccionada == -1) {
	        JOptionPane.showMessageDialog(null, "Seleccione una categoría de la tabla.");
	        return;
	    }

	    int idCategoria = Integer.parseInt(tablaCategoria.getValueAt(filaSeleccionada, 0).toString());

	    try {
	        if (libDAO.LibroConCategoria(idCategoria)) {
	            JOptionPane.showMessageDialog(null, "No se puede eliminar: la categoría está en uso por uno o más libros.");
	            return;
	        }
	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(null, "Error al verificar uso de categoría: " + ex.getMessage());
	        return;
	    }

	    int confirmacion = JOptionPane.showConfirmDialog(
	        null, "¿Está seguro de eliminar esta categoría?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION
	    );

	    if (confirmacion == JOptionPane.YES_OPTION) {
	        CategoriaDAO dao = new CategoriaDAO();
	        try {
	            boolean exito = dao.eliminarCategoria(idCategoria);
	            if (exito) {
	                JOptionPane.showMessageDialog(null, "Categoría eliminada exitosamente.");
	            } else {
	                JOptionPane.showMessageDialog(null, "No se pudo eliminar la categoría.");
	            }
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
	        }

	        LimpiarTabla();
	        ListarCat();
	        Limpiar();
	        llenarComboBox();
	    }
	}
	protected void do_btnElimEdito_actionPerformed(ActionEvent e) {
		int filaSeleccionada = tablaEditorial.getSelectedRow();

	    if (filaSeleccionada == -1) {
	        JOptionPane.showMessageDialog(null, "Seleccione una editorial de la tabla.");
	        return;
	    }

	    int idEditorial = Integer.parseInt(tablaEditorial.getValueAt(filaSeleccionada, 0).toString());

	    try {
	        if (libDAO.LibroConEditorial(idEditorial)) {
	            JOptionPane.showMessageDialog(null, "No se puede eliminar: la editorial está en uso por uno o más libros.");
	            return;
	        }
	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(null, "Error al verificar uso de editorial: " + ex.getMessage());
	        return;
	    }

	    int confirmacion = JOptionPane.showConfirmDialog(
	        null, "¿Está seguro de eliminar esta editorial?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION
	    );

	    if (confirmacion == JOptionPane.YES_OPTION) {
	        EditorialDAO dao = new EditorialDAO();
	        try {
	            boolean exito = dao.eliminarEditorial(idEditorial);
	            if (exito) {
	                JOptionPane.showMessageDialog(null, "Editorial eliminada exitosamente.");
	            } else {
	                JOptionPane.showMessageDialog(null, "No se pudo eliminar la editorial.");
	            }
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
	        }

	        LimpiarTabla();
	        ListarEdit();
	        Limpiar();
	        llenarComboBox();
	    }
	}
	protected void do_btnInserEdito_actionPerformed(ActionEvent e) {
		String nombre = txtNomEdito.getText().trim();

	    String mensaje = ValidarCatEdi.validarNomE(nombre);
	    if (!mensaje.equals("OK")) {
	        JOptionPane.showMessageDialog(null, mensaje);
	        return;
	    }

	    Editorial editorial = new Editorial();
	    editorial.setNombre(nombre);

	    EditorialDAO dao = new EditorialDAO();
	    try {
	        boolean exito = dao.insertarEditorial(editorial);
	        if (exito) {
	            JOptionPane.showMessageDialog(null, "Editorial insertada exitosamente.");
	        } else {
	            JOptionPane.showMessageDialog(null, "No se pudo insertar la editorial.");
	        }
	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
	    }

	    LimpiarTabla();
	    ListarEdit();
	    Limpiar();
	    ListarCat();
	    llenarComboBox();
	}
	protected void do_btnModifiEdito_actionPerformed(ActionEvent e) {
		int filaSeleccionada = tablaEditorial.getSelectedRow();

	    if (filaSeleccionada == -1) {
	        JOptionPane.showMessageDialog(null, "Seleccione una editorial de la tabla.");
	        return;
	    }

	    int idEditorial = Integer.parseInt(tablaEditorial.getValueAt(filaSeleccionada, 0).toString());
	    String nuevoNombre = txtNomEdito.getText().trim();

	    String mensaje = ValidarCatEdi.validarNomE(nuevoNombre);
	    if (!mensaje.equals("OK")) {
	        JOptionPane.showMessageDialog(null, mensaje);
	        return;
	    }
	    Editorial editorial = new Editorial();
	    editorial.setId_edit(idEditorial);
	    editorial.setNombre(nuevoNombre);

	    EditorialDAO dao = new EditorialDAO();
	    try {
	        boolean exito = dao.modificarEditorial(editorial);
	        if (exito) {
	            JOptionPane.showMessageDialog(null, "Editorial modificada exitosamente.");
	        } else {
	            JOptionPane.showMessageDialog(null, "No se pudo modificar la editorial.");
	        }
	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
	    }

	    LimpiarTabla();
	    ListarEdit();
	    Limpiar();
	    ListarCat();
	    llenarComboBox();
	}
	protected void do_rdbtnNewRadioButton_1_actionPerformed(ActionEvent e) {
		String idUsuario = txtIdU.getText().trim();

	    // Validar formato del ID
	    String validacion = ValidarUsuario.validarID(idUsuario);
	    if (!"OK".equals(validacion)) {
	        JOptionPane.showMessageDialog(null, validacion);
	        return;
	    }

	    // Intentar eliminar el usuario
	    boolean inactivo = usuDAO.desactivar(idUsuario);

	    if (inactivo) {
	        JOptionPane.showMessageDialog(null, "Usuario desactivado correctamente.");
	    } else {
	        JOptionPane.showMessageDialog(null, "No se pudo desactivar el usuario. Verifique que exista y no tenga reservas o prestamos activas.");
	    }

	    // Refrescar la interfaz
	    LimpiarTabla();
	    llenarComboBox();
	    Limpiar();
	    ListaUs();
	}
	protected void do_rbtnActivo_actionPerformed(ActionEvent e) {
		String idUsuario = txtIdU.getText().trim();

	    // Validar formato del ID
	    String validacion = ValidarUsuario.validarID(idUsuario);
	    if (!"OK".equals(validacion)) {
	        JOptionPane.showMessageDialog(null, validacion);
	        return;
	    }

	    // Intentar eliminar el usuario
	    boolean activar = usuDAO.activar(idUsuario);

	    if (activar) {
	        JOptionPane.showMessageDialog(null, "Usuario activado correctamente.");
	    } 

	    // Refrescar la interfaz
	    LimpiarTabla();
	    llenarComboBox();
	    Limpiar();
	    ListaUs();
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	private void AutocompletadoISBNR() {
	    txtIsbnR.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyReleased(KeyEvent e) {
	            String texto = txtIsbnR.getText().trim();
	            popupSugerencias.setVisible(false);
	            popupSugerencias.removeAll();

	            if (texto.length() < 2) return;

	            List<Libro> sugerencias = libDAO.buscarLibros(texto); 
	            
	            if (!sugerencias.isEmpty()) {
	                for (Libro libro : sugerencias) {
	                    JMenuItem item = new JMenuItem(libro.getTitulo());
	                    item.addActionListener(ae -> {
	                        txtIsbnR.setText(libro.getTitulo()); // dejamos el título
	                        popupSugerencias.setVisible(false);
	                    });
	                    popupSugerencias.add(item);
	                }
	                popupSugerencias.show(txtIsbnR, 0, txtIsbnR.getHeight());
	                txtIsbnR.requestFocus();
	            }
	        }
	    });
	}
	private void AutocompletadoUsuR() {
	    txtUsuR.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyReleased(KeyEvent e) {
	            String texto = txtUsuR.getText().trim();
	            popupSugerencias.setVisible(false);
	            popupSugerencias.removeAll();

	            if (texto.length() < 2) return;

	            List<Usuario> sugerencias = usuDAO.listarUsu().stream()
	                .filter(u -> u.getNombre().toLowerCase().contains(texto.toLowerCase()))
	                .filter(u -> "Usuario".equalsIgnoreCase(u.getRol()))
	                .filter(u -> "activo".equalsIgnoreCase(u.getEstado()))
	                .limit(10)
	                .toList();

	            if (!sugerencias.isEmpty()) {
	                for (Usuario usuario : sugerencias) {
	                    JMenuItem item = new JMenuItem(usuario.getNombre());
	                    item.addActionListener(ae -> {
	                        txtUsuR.setText(usuario.getNombre()); // dejamos el nombre
	                        popupSugerencias.setVisible(false);
	                    });
	                    popupSugerencias.add(item);
	                }
	                popupSugerencias.show(txtUsuR, 0, txtUsuR.getHeight());
	                txtUsuR.requestFocus();
	            }
	        }
	    });
	}
	private void AutocompletadoISBNP() {
	    txtIsbnP.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyReleased(KeyEvent e) {
	            String texto = txtIsbnP.getText().trim();
	            popupSugerencias.setVisible(false);
	            popupSugerencias.removeAll();

	            if (texto.length() < 2) return;

	            List<Libro> sugerencias = libDAO.buscarLibros(texto);

	            if (!sugerencias.isEmpty()) {
	                for (Libro libro : sugerencias) {
	                    JMenuItem item = new JMenuItem(libro.getTitulo());
	                    item.addActionListener(ae -> {
	                        txtIsbnP.setText(libro.getTitulo()); // dejamos el título
	                        popupSugerencias.setVisible(false);
	                    });
	                    popupSugerencias.add(item);
	                }
	                popupSugerencias.show(txtIsbnP, 0, txtIsbnP.getHeight());
	                txtIsbnP.requestFocus();
	            }
	        }
	    });
	}
	private void AutocompletadoUsuP() {
	    txtUsuP.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyReleased(KeyEvent e) {
	            String texto = txtUsuP.getText().trim();
	            popupSugerencias.setVisible(false);
	            popupSugerencias.removeAll();

	            if (texto.length() < 2) return;

	            List<Usuario> sugerencias = usuDAO.listarUsu().stream()
	                .filter(u -> u.getNombre().toLowerCase().contains(texto.toLowerCase()))
	                .filter(u -> "Usuario".equalsIgnoreCase(u.getRol()))
	                .filter(u -> "activo".equalsIgnoreCase(u.getEstado()))
	                .limit(10)
	                .toList();

	            if (!sugerencias.isEmpty()) {
	                for (Usuario usuario : sugerencias) {
	                    JMenuItem item = new JMenuItem(usuario.getNombre());
	                    item.addActionListener(ae -> {
	                        txtUsuP.setText(usuario.getNombre()); // dejamos el nombre
	                        popupSugerencias.setVisible(false);
	                    });
	                    popupSugerencias.add(item);
	                }
	                popupSugerencias.show(txtUsuP, 0, txtUsuP.getHeight());
	                txtUsuP.requestFocus();
	            }
	        }
	    });
	}
	
	private void mostrarFechasDevolucionPendientes(String tituloLibro) {
	    Libro libro = libDAO.buscarLibroPorTituloExacto(tituloLibro);
	    if (libro == null) {
	        lblFechaDevP.setText("Libro no encontrado.");
	        return;
	    }

	    long isbn = libro.getIsbn();
	    List<String> fechas = preDAO.obtenerFechasDevolucionPorISBN(isbn);
	    
	    if (fechas.isEmpty()) {
	        lblFechaDevP.setText("No hay préstamos activos.");
	    } else {
	        StringBuilder mensaje = new StringBuilder("");
	        for (String f : fechas) {
	            mensaje.append(f).append("  ");
	        }
	        lblFechaDevP.setText(mensaje.toString());
	    }
	}

}
