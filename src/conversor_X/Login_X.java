/*
@author CARLA ROMINA SOLEDAD ROSSETTI
*/
package conversor_X;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JDialog;

public class Login_X extends JPanel {
	private static final long serialVersionUID = 1L;
	
	//VARIABLES GLOBALES
	JTextField campoTextoUsuario;
	JPasswordField campoTextoContraseña;
	JDialog dialogo;
	
	//CONSTRUCTOR
	public Login_X() {
		iniciarLogin();
	}
	//VENTANA LOGIN
	private void iniciarLogin() {
		setLayout(null);
		
		Font fuenteEtiqueta= new Font("Calibri", Font.BOLD, 14);
		
		//ETIQUETA INGRESE USUARIO
		JLabel ingreseUsuario= new JLabel();
		ingreseUsuario.setText("     Ingrese Usuario");
		ingreseUsuario.setBounds(240, 100, 120, 30);
		ingreseUsuario.setForeground(Color.LIGHT_GRAY);
		ingreseUsuario.setFont(fuenteEtiqueta);
		add(ingreseUsuario);
		
		//ETIQUETA INGRESE CONTRASEÑA
		JLabel ingreseContraseña= new JLabel();
		ingreseContraseña.setText(" Ingrese Contraseña");
		ingreseContraseña.setBounds(240, 180, 120, 30);
		ingreseContraseña.setForeground(Color.LIGHT_GRAY);
		ingreseContraseña.setFont(fuenteEtiqueta);
		add(ingreseContraseña);
		
		Color colorCampoTexto= new Color(0x503459);
		
		//CAMPO DE TEXTO
		campoTextoUsuario= new JTextField();
		campoTextoUsuario.setBounds(240, 140, 120, 30);
		campoTextoUsuario.setBackground(colorCampoTexto);
		campoTextoUsuario.setForeground(Color.LIGHT_GRAY);
		campoTextoUsuario.setHorizontalAlignment(JTextField.CENTER);
		add(campoTextoUsuario);
		
		//CAMPO DE TEXTO
		campoTextoContraseña= new JPasswordField();
		campoTextoContraseña.setBounds(240, 220, 120, 30);
		campoTextoContraseña.setBackground(colorCampoTexto);
		campoTextoContraseña.setForeground(Color.LIGHT_GRAY);
		campoTextoContraseña.setHorizontalAlignment(JPasswordField.CENTER);
		add(campoTextoContraseña);
		
		Color colorBoton= new Color(0x81638B);
		
		//BOTÓN ENTRAR
		JButton entrar= new JButton();
		entrar.setText("Entrar");
		entrar.setBounds(240, 280, 120, 30);
		entrar.setBackground(colorBoton);
		entrar.setForeground(Color.LIGHT_GRAY);
		entrar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String usuario= "admin";
				String contraseña= "1234";
				
				String entradaUsuario= campoTextoUsuario.getText().toLowerCase();
				String entradaContraseña= new String(campoTextoContraseña.getPassword());
				
				if(entradaUsuario.equals(usuario) && entradaContraseña.equals(contraseña)) {
					//CONVERSOR
					Conversor_X principal= new Conversor_X();
					principal.setVisible(true);
				}
				else {
					VentanaDialogo();
				}
			}
		});
		add(entrar);
	}
	//VENTANA DIÁLOGO
	public void VentanaDialogo() {
		Color colorFondoDialogo= new Color(0x503459);
		
		dialogo= new JDialog();
		dialogo.setSize(300, 220);
		dialogo.setUndecorated(true);
		dialogo.setLocationRelativeTo(null);
		dialogo.getContentPane().setBackground(colorFondoDialogo);
		dialogo.setVisible(true);
		componentesVentanaDialogo();
	}
	//COMPONENTES VENTANA DIÁLOGO
	private void componentesVentanaDialogo() {
		Font fuenteMensaje= new Font("Calibri", Font.BOLD, 14);
		
		//ETIQUETA MENSAJE
		JLabel mensaje= new JLabel();
		mensaje.setText("Usuario o Contraseña Incorrectos");
		mensaje.setBounds(50, 65, 200, 30);
		mensaje.setForeground(Color.LIGHT_GRAY);
		mensaje.setFont(fuenteMensaje);
		mensaje.setVisible(true);
		dialogo.add(mensaje);
		
		//BOTÓN ACEPTAR
		Color colorFondoBoton= new Color(0x81638B);
		
		JButton aceptar= new JButton();
		aceptar.setText("Aceptar");
		aceptar.setBounds(90, 125, 120, 30);
		aceptar.setBackground(colorFondoBoton);
		aceptar.setForeground(Color.LIGHT_GRAY);
		aceptar.setVisible(true);
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialogo.dispose();
			}
		});
		dialogo.add(aceptar);
	}
	//PINTAR
	@Override
	public void paint(Graphics g) {
		ImageIcon imagenFondo= new ImageIcon(getClass().getResource("/imagenes_X/Login_X.png"));
		
		g.drawImage(imagenFondo.getImage(), 0, 0, getWidth(), getHeight(), this);
		setOpaque(false);
		super.paint(g);
	}
	//PRINCIPAL
	public static void main(String[] args) {
		Login_X fondo= new Login_X();
		
		JFrame ventana= new JFrame();
		ventana.setContentPane(fondo);
		ventana.setSize(600, 400);
		ventana.setUndecorated(true);
		ventana.setLocationRelativeTo(null);
		ventana.setOpacity(0.9f);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setVisible(true);
	}
}