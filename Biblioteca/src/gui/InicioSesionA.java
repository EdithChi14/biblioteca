package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.PreparedStatement;

import Clases.UsuarioDAO;
import Conexion.Conexion;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JPasswordField;
import java.awt.event.MouseMotionListener;
import java.sql.Connection;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Cursor;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class InicioSesionA extends JFrame implements MouseMotionListener, MouseListener, KeyListener {

	private JPanel contentPane;
	private JPanel panel;
	private JPanel pnlSalir;
	private JLabel lblNewLabel_1;
	private JPanel pnlInicio;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JTextField txtUsuario;
	private JSeparator separator;
	private JSeparator separator_2;
	private JPasswordField txtContraseña;
	private JPanel pnlEntrar;
	private JLabel lblEntrar;
	int xmouse,ymouse;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicioSesionA frame = new InicioSesionA();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InicioSesionA() {
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 624, 440);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			pnlInicio = new JPanel();
			pnlInicio.setBackground(new Color(255, 255, 255));
			pnlInicio.setBounds(0, 30, 276, 410);
			contentPane.add(pnlInicio);
			pnlInicio.setLayout(null);
			{
				separator = new JSeparator();
				separator.setBackground(Color.BLACK);
				separator.setBounds(20, 224, 233, 2);
				pnlInicio.add(separator);
			}
			{
				lblNewLabel_2 = new JLabel("");
				lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_2.setIcon(new ImageIcon(InicioSesionA.class.getResource("/imagen/Logo BPN.png")));
				lblNewLabel_2.setBounds(0, 11, 263, 97);
				pnlInicio.add(lblNewLabel_2);
			}
			{
				lblNewLabel_3 = new JLabel("INICIAR SESION ");
				lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_3.setFont(new Font("Century Gothic", Font.PLAIN, 20));
				lblNewLabel_3.setBounds(0, 115, 263, 30);
				pnlInicio.add(lblNewLabel_3);
			}
			{
				lblNewLabel_4 = new JLabel("USUARIO");
				lblNewLabel_4.setHorizontalAlignment(SwingConstants.LEFT);
				lblNewLabel_4.setFont(new Font("Century Gothic", Font.PLAIN, 20));
				lblNewLabel_4.setBounds(23, 161, 253, 30);
				pnlInicio.add(lblNewLabel_4);
			}
			{
				lblNewLabel_5 = new JLabel("CONTRASEÑA");
				lblNewLabel_5.setHorizontalAlignment(SwingConstants.LEFT);
				lblNewLabel_5.setFont(new Font("Century Gothic", Font.PLAIN, 20));
				lblNewLabel_5.setBounds(20, 247, 256, 30);
				pnlInicio.add(lblNewLabel_5);
			}
			{
				txtUsuario = new JTextField();
				txtUsuario.addMouseListener(this);
				txtUsuario.setForeground(Color.LIGHT_GRAY);
				txtUsuario.setText("Ingresar su nombre de usuario");
				txtUsuario.setBorder(null);
				txtUsuario.setBounds(20, 193, 233, 32);
				pnlInicio.add(txtUsuario);
				txtUsuario.setColumns(10);
			}
			{
				separator_2 = new JSeparator();
				separator_2.setBackground(Color.BLACK);
				separator_2.setBounds(20, 309, 233, 2);
				pnlInicio.add(separator_2);
			}
			{
				txtContraseña = new JPasswordField();
				txtContraseña.addKeyListener(this);
				txtContraseña.addMouseListener(this);
				txtContraseña.setText("********");
				txtContraseña.setForeground(Color.LIGHT_GRAY);
				txtContraseña.setColumns(10);
				txtContraseña.setBorder(null);
				txtContraseña.setBounds(20, 278, 233, 32);
				pnlInicio.add(txtContraseña);
			}
			{
				pnlEntrar = new JPanel();
				pnlEntrar.setBackground(new Color(128, 128, 255));
				pnlEntrar.setForeground(new Color(128, 128, 255));
				pnlEntrar.setBounds(60, 344, 146, 30);
				pnlInicio.add(pnlEntrar);
				pnlEntrar.setLayout(null);
				{
					lblEntrar = new JLabel("Entrar");
					lblEntrar.addMouseListener(this);
					lblEntrar.setForeground(Color.WHITE);
					lblEntrar.setHorizontalAlignment(SwingConstants.CENTER);
					lblEntrar.setFont(new Font("Century Gothic", Font.PLAIN, 20));
					lblEntrar.setBounds(0, 0, 146, 30);
					pnlEntrar.add(lblEntrar);
				}
			}
		}
		{
			panel = new JPanel();
			panel.addMouseListener(this);
			panel.addMouseMotionListener(this);
			panel.setBackground(new Color(255, 255, 255));
			panel.setBounds(0, 0, 586, 30);
			contentPane.add(panel);
			panel.setLayout(null);
			{
				pnlSalir = new JPanel();
				pnlSalir.setBackground(new Color(255, 255, 255));
				pnlSalir.setBounds(0, 0, 36, 30);
				panel.add(pnlSalir);
				pnlSalir.setLayout(null);
				{
					lblX = new JLabel("X");
					lblX.addMouseListener(this);
					lblX.setFont(new Font("Tahoma", Font.PLAIN, 15));
					lblX.setHorizontalAlignment(SwingConstants.CENTER);
					lblX.setBounds(0, 0, 36, 30);
					pnlSalir.add(lblX);
				}
			}
		}
		{
			lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setIcon(new ImageIcon(InicioSesionA.class.getResource("/imagen/librosAdministrador.jpg")));
			lblNewLabel_1.setBounds(205, 30, 419, 408);
			contentPane.add(lblNewLabel_1);
		}
		this.setLocationRelativeTo(null);
	}
	public void mouseDragged(MouseEvent e) {
		if (e.getSource() == panel) {
			do_panel_mouseDragged(e);
		}
	}
	public void mouseMoved(MouseEvent e) {
	}
	protected void do_panel_mouseDragged(MouseEvent e) {
		int x=e.getXOnScreen();
		int y=e.getYOnScreen();
		this.setLocation(x-xmouse, y-ymouse);
	}
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == lblX) {
			do_lblNewLabel_mouseClicked(e);
		}
		if (e.getSource() == lblEntrar) {
			do_lblEntrar_mouseClicked(e);
		}
	}
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == lblX) {
			do_lblNewLabel_mouseEntered(e);
		}
		if (e.getSource() == lblEntrar) {
			do_lblEntrar_mouseEntered(e);
		}
	}
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == lblX) {
			do_lblNewLabel_mouseExited(e);
		}
		if (e.getSource() == lblEntrar) {
			do_lblEntrar_mouseExited(e);
		}
	}
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == txtContraseña) {
			do_txtContraseña_mousePressed(e);
		}
		if (e.getSource() == txtUsuario) {
			do_txtUsuario_mousePressed(e);
		}
		if (e.getSource() == panel) {
			do_panel_mousePressed(e);
		}
	}
	public void mouseReleased(MouseEvent e) {
	}
	protected void do_panel_mousePressed(MouseEvent e) {
		xmouse=e.getX();
		ymouse=e.getY();
	}
	
	protected void do_txtUsuario_mousePressed(MouseEvent e) {
		if(txtUsuario.getText().equals("Ingresar su nombre de usuario")) {
			txtUsuario.setText("");
			txtUsuario.setForeground(Color.black);
		}
		if(String.valueOf(txtContraseña.getPassword()).isEmpty()) {
			txtContraseña.setText("********");
			txtContraseña.setForeground(Color.gray);
		}
	}
	
	protected void do_txtContraseña_mousePressed(MouseEvent e) {
		if(String.valueOf(txtContraseña.getPassword()).equals("********")) {
			txtContraseña.setText("");
			txtContraseña.setForeground(Color.black);
		}
		if(txtUsuario.getText().isEmpty()) {
			txtUsuario.setText("Ingresar su nombre de usuario");
			txtUsuario.setForeground(Color.gray);
		}

	}
	int error=0; 
	private JLabel lblX;
	
	protected void do_lblEntrar_mouseClicked(MouseEvent e) {
		String idUsuario = txtUsuario.getText().trim();
	    String contra = txtContraseña.getText().trim();

	    if (idUsuario.isEmpty() || contra.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Debe completar ambos campos.");
	        return;
	    }

	    UsuarioDAO dao = new UsuarioDAO();
	    String rol = dao.autenticarUsuario(idUsuario, contra);

	    if (rol != null) {
	        JOptionPane.showMessageDialog(this, "BIENVENIDO " + rol.toUpperCase());

	        if ("admin".equals(rol)) {
	            new PrincipalAdmin().setVisible(true);
	        } 

	        dispose(); // Cierra login
	    } else {
	        JOptionPane.showMessageDialog(this, "Usuario y/o contraseña incorrectos.");
	    }

	}
	
	protected void do_lblEntrar_mouseEntered(MouseEvent e) {
		pnlEntrar.setBackground(new Color(0,0,234));
		Font fuente = new Font("Century Gothic", Font.BOLD, 20);
		lblEntrar.setFont(fuente);
	}
	protected void do_lblEntrar_mouseExited(MouseEvent e) {
		pnlEntrar.setBackground(new Color(128, 128, 255));
		Font fuente = new Font("Century Gothic", Font.PLAIN, 20);
		lblEntrar.setFont(fuente);
	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txtContraseña) {
			do_txtContraseña_keyTyped(e);
		}
	}
	protected void do_txtContraseña_keyTyped(KeyEvent e) {
		char validar=e.getKeyChar();
		if(Character.isLetter(validar)) {
			getToolkit().beep();
			e.consume();
			JOptionPane.showMessageDialog(contentPane, "Solo se permite números");
		}
	}
	protected void do_lblNewLabel_mouseEntered(MouseEvent e) {
		pnlSalir.setBackground(Color.red);
		lblX.setForeground(Color.white);
	}
	protected void do_lblNewLabel_mouseExited(MouseEvent e) {
		pnlSalir.setBackground(Color.white);
		lblX.setForeground(Color.black);
	}
	protected void do_lblNewLabel_mouseClicked(MouseEvent e) {
		System.exit(0);
	}
}
