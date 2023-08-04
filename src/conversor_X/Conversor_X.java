/*
@author CARLA ROMINA SOLEDAD ROSSETTI
*/
package conversor_X;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JSplitPane;
import javax.swing.border.Border;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonObject;
import com.google.gson.Gson;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.Graphics2D;

public class Conversor_X extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	//VARIABLES GLOBALES
	JButton boton1, boton2, boton3, boton4, convertir1, salir1, resetear1, convertir2, salir2, resetear2, convertir3, salir3, resetear3, salir4;
	JPanel panel1, panel2, panel3, panel4;
	JComboBox<Items1> desplegable1;
	JComboBox<Items2> desplegable2;
	JComboBox<Items3> desplegable3;
	JFormattedTextField campoTexto1, campoTexto2, campoTexto3;
	JLabel resultado1, resultado2, resultado3;
	double valorEntrada1= 0.00;
	int valorEntrada2;
	double ars, usd, eur, gbp, jpy, krw, ars_u, ars_e, ars_g, ars_j, ars_k;
	
	//CONSTRUCTOR
	public Conversor_X() {
		setSize(600, 400);
		setUndecorated(true);
		getContentPane().setBackground(Color.BLACK);
		setOpacity(0.9f);
		setLocationRelativeTo(null);
		setTitle("Conversor X");
		IniciarPrincipal();
	}
	//VENTANA PRINCIPAL
	private void IniciarPrincipal() {
		setLayout(null);
		
		//TÍTULO
		Font fuenteTituloPrincipal= new Font("Calibri", Font.BOLD, 30);
		Color colorTituloPrincipal= new Color(0x515151);
		
		JLabel titulo= new JLabel();
		titulo.setText("Conversor X");
		titulo.setBounds(220, 20, 160, 40);
		titulo.setFont(fuenteTituloPrincipal);
		titulo.setForeground(colorTituloPrincipal);
		add(titulo);
		
		//MENÚ BOTONES
		Font fuenteBotones= new Font("Calibri", Font.BOLD, 12);
		Color colorFondoBotones= new Color(0x191919);
		Color colorBordeBotones= new Color(0xCF3476);
		Border bordeBotones= BorderFactory.createLineBorder(colorBordeBotones, 2);
		
		boton1= new JButton();
		boton1.setText("Divisas");
		boton1.setBounds(50, 110, 100, 40);
		boton1.setFont(fuenteBotones);
		boton1.setBackground(colorFondoBotones);
		boton1.setForeground(Color.LIGHT_GRAY);
		boton1.setBorder(bordeBotones);
		boton1.addActionListener(this);
		add(boton1);
		
		boton2= new JButton();
		boton2.setText("Temperatura");
		boton2.setBounds(50, 170, 100, 40);
		boton2.setFont(fuenteBotones);
		boton2.setBackground(colorFondoBotones);
		boton2.setForeground(Color.LIGHT_GRAY);
		boton2.setBorder(bordeBotones);
		boton2.addActionListener(this);
		add(boton2);
		
		boton3= new JButton();
		boton3.setText("Numeración");
		boton3.setBounds(50, 230, 100, 40);
		boton3.setFont(fuenteBotones);
		boton3.setBackground(colorFondoBotones);
		boton3.setForeground(Color.LIGHT_GRAY);
		boton3.setBorder(bordeBotones);
		boton3.addActionListener(this);
		add(boton3);
		
		boton4= new JButton();
		boton4.setText("Acerca de");
		boton4.setBounds(50, 290, 100, 40);
		boton4.setFont(fuenteBotones);
		boton4.setBackground(colorFondoBotones);
		boton4.setForeground(Color.LIGHT_GRAY);
		boton4.setBorder(bordeBotones);
		boton4.addActionListener(this);
		add(boton4);
		
		//PANEL PRINCIPAL DERECHO
		Color colorPanel= new Color(0x191919);
		
		panel1= new JPanel();
		panel1.setBounds(180, 80, 380, 280);
		panel1.setBackground(colorPanel);
		panel1.setLayout(null);
		panel1.setVisible(true);
		add(panel1);
		
		panel2= new JPanel();
		panel2.setBounds(180, 80, 380, 280);
		panel2.setBackground(colorPanel);
		panel2.setLayout(null);
		panel1.setVisible(false);
		add(panel2);
		
		panel3= new JPanel();
		panel3.setBounds(180, 80, 380, 280);
		panel3.setBackground(colorPanel);
		panel3.setLayout(null);
		panel1.setVisible(false);
		add(panel3);
		
		panel4= new JPanel();
		panel4.setBounds(180, 80, 380, 280);
		panel4.setBackground(colorPanel);
		panel4.setLayout(null);
		panel4.setVisible(false);
		add(panel4);
		
		//PANEL PRINCIPAL
		JPanel panelIzq= new JPanel();
		panelIzq.setBackground(Color.BLACK);
		JPanel panelDer= new JPanel();
		panelDer.setBackground(Color.BLACK);
		
		Color colorBordePanelPrincipal= new Color(0xCF3476);
		Border bordePanelPrincipal= BorderFactory.createLineBorder(colorBordePanelPrincipal, 2);
		
		JSplitPane panelPrincipal= new JSplitPane();
		panelPrincipal.setBounds(40, 70, 530, 300);
		panelPrincipal.setDividerLocation(120);
		panelPrincipal.setEnabled(false);
		panelPrincipal.setLeftComponent(panelIzq);
		panelPrincipal.setRightComponent(panelDer);
		panelPrincipal.setBorder(bordePanelPrincipal);
		add(panelPrincipal);
	}
	//JCOMBOBOX PERSONALIZADO
	class JComboBoxPersonalizado extends DefaultListCellRenderer {
		private static final long serialVersionUID = 1L;
		
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			Component componente= super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			if(isSelected) {
				Color colorSeleccionado= new Color(0xCF3476);
				componente.setBackground(colorSeleccionado);
			}
			else {
				Color colorNoSeleccionado= new Color(0x191919);
				componente.setBackground(colorNoSeleccionado);
			}
			return componente;
		}
	}
	//ITEMS CONVERSIÓN JCOMBOBOX 1
	public enum Items1 {
		Seleccione_Opción,
		PESOS_a_DÓLARES,
		PESOS_a_EUROS,
		PESOS_a_LIBRAS,
		PESOS_a_YENES,
		PESOS_a_WONES,
		DÓLARES_a_PESOS,
		EUROS_a_PESOS,
		LIBRAS_a_PESOS,
		YENES_a_PESOS,
		WONES_a_PESOS
	}
	//PANEL DERECHO 1: DIVISAS
	private void cargarDivisas() {
		Color colorFondo= new Color(0x191919);
		Color colorBorde= new Color(0xCF3476);
		Border borde= BorderFactory.createLineBorder(colorBorde, 2);
		
		//MENÚ DESPLEGABLE
		desplegable1= new JComboBox<Items1>();
		desplegable1.setModel(new DefaultComboBoxModel<Items1>(Items1.values()));
		desplegable1.setBounds(20, 60, 150, 30);
		desplegable1.setBackground(colorFondo);
		desplegable1.setForeground(Color.LIGHT_GRAY);
		desplegable1.setBorder(borde);
		desplegable1.setRenderer(new JComboBoxPersonalizado());
		panel1.add(desplegable1);
		
		//CAMPO DE TEXTO
		campoTexto1= new JFormattedTextField();
		campoTexto1.setBounds(210, 60, 150, 30);
		campoTexto1.setBackground(colorFondo);
		campoTexto1.setForeground(Color.LIGHT_GRAY);
		campoTexto1.setBorder(borde);
		campoTexto1.setHorizontalAlignment(JFormattedTextField.CENTER);
		panel1.add(campoTexto1);
		
		Color colorFondoBotones= new Color(0xCF3476);
		
		//BOTÓN CONVERTIR
		convertir1= new JButton();
		convertir1.setText("Convertir");
		convertir1.setBounds(20, 140, 150, 30);
		convertir1.setBackground(colorFondoBotones);
		convertir1.setForeground(Color.LIGHT_GRAY);
		convertir1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				SeleccionarDivisas();
			}
		});
		panel1.add(convertir1);
		
		//BOTÓN SALIR
		salir1= new JButton();
		salir1.setText("Salir");
		salir1.setBounds(210, 140, 150, 30);
		salir1.setBackground(colorFondoBotones);
		salir1.setForeground(Color.LIGHT_GRAY);
		salir1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panel1.add(salir1);
		
		//BOTÓN RESETEAR
		resetear1= new JButton();
		resetear1.setText("R");
		resetear1.setBounds(315, 230, 45, 30);
		resetear1.setBackground(colorFondoBotones);
		resetear1.setForeground(Color.LIGHT_GRAY);
		resetear1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desplegable1.setSelectedIndex(0);
				campoTexto1.setValue("");
				resultado1.setText("");
				valorEntrada1= 0.00;
				valorEntrada2= 0;
			}
		});
		panel1.add(resetear1);
		
		//ETIQUETA RESULTADO
		Font fuenteResultado= new Font("Calibri", Font.BOLD, 16);
		Color colorResultado= new Color(0x515151);
		
		resultado1= new JLabel();
		resultado1.setText("");
		resultado1.setBounds(20, 210, 340, 30);
		resultado1.setForeground(colorResultado);
		resultado1.setFont(fuenteResultado);
		panel1.add(resultado1);
	}
	//VALIDACIÓN ENTRADA 1
	public boolean Validar1(String texto) {
		try {
			double valor= Double.parseDouble(texto);
			if(valor > 0);
			valorEntrada1= valor;
			return true;
		}
		catch(NumberFormatException e) {
			resultado1.setText("La conversión solo permite números!");
			return false;
		}
	}	
	//REDONDEO RESULTADO
	public String Redondear(double valor) {
		DecimalFormat formatoDecimal= new DecimalFormat("0.00");
		formatoDecimal.setRoundingMode(RoundingMode.HALF_UP);
		return formatoDecimal.format(valor);
	}	
	//CONVERSIÓN PESO ARGENTINO A MONEDA EXTRANJERA
	public void PesoAMoneda(double moneda) {
		double resu= valorEntrada1 * moneda;
		resultado1.setText("Resultado de la conversión: " + Redondear(resu));
	}
	//CONVERSIÓN MONEDA EXTRANJERA A PESO ARGENTINO
	public void MonedaAPeso(double moneda) {
		double resu= valorEntrada1 * moneda;
		resultado1.setText("Resultado de la conversión: " + Redondear(resu));
	}
	//SELECCIÓN DIVISAS
	private void SeleccionarDivisas() {
		if(Validar1(campoTexto1.getText())) {
			Items1 items= (Items1) desplegable1.getSelectedItem();
			switch(items) {
			case Seleccione_Opción:
				resultado1.setText("La conversión solo permite números!");
				break;
			case PESOS_a_DÓLARES:
				ConexionApi("https://api.exchangerate.host/convert?from=ARS&to=USD");
				PesoAMoneda(usd);
				break;
			case PESOS_a_EUROS:
				ConexionApi("https://api.exchangerate.host/convert?from=ARS&to=EUR");
				PesoAMoneda(eur);
				break;
			case PESOS_a_LIBRAS:
				ConexionApi("https://api.exchangerate.host/convert?from=ARS&to=GBP");
				PesoAMoneda(gbp);
				break;
			case PESOS_a_YENES:
				ConexionApi("https://api.exchangerate.host/convert?from=ARS&to=JPY");
				PesoAMoneda(jpy);
				break;
			case PESOS_a_WONES:
				ConexionApi("https://api.exchangerate.host/convert?from=ARS&to=KRW");
				PesoAMoneda(krw);
				break;
			case DÓLARES_a_PESOS:
				ConexionApi("https://api.exchangerate.host/convert?from=USD&to=ARS");
				MonedaAPeso(ars_u);
				break;
			case EUROS_a_PESOS:
				ConexionApi("https://api.exchangerate.host/convert?from=EUR&to=ARS");
				MonedaAPeso(ars_e);
				break;
			case LIBRAS_a_PESOS:
				ConexionApi("https://api.exchangerate.host/convert?from=GBP&to=ARS");
				MonedaAPeso(ars_g);
				break;
			case YENES_a_PESOS:
				ConexionApi("https://api.exchangerate.host/convert?from=JPY&to=ARS");
				MonedaAPeso(ars_j);
				break;
			case WONES_a_PESOS:
				ConexionApi("https://api.exchangerate.host/convert?from=KRW&to=ARS");
				MonedaAPeso(ars_k);
				break;
			default:
				throw new IllegalArgumentException("Valor inesperado: " + items);
			}
		}
	}
	//ITEMS CONVERSIÓN JCOMBOBOX 2
	public enum Items2 {
		Seleccione_Opción,
		CELSIUS_a_FAHRENHEIT,
		CELSIUS_a_KELVIN,
		FAHRENHEIT_a_CELSIUS,
		FAHRENHEIT_a_KELVIN,
		KELVIN_a_CELSIUS,
		KELVIN_a_FAHRENHEIT,
	}
	//PANEL DERECHO 2: TEMPERATURA
	private void cargarTemperatura() {
		Color colorFondo= new Color(0x191919);
		Color colorBorde= new Color(0xCF3476);
		Border borde= BorderFactory.createLineBorder(colorBorde, 2);
		
		//MENÚ DESPLEGABLE
		desplegable2= new JComboBox<Items2>();
		desplegable2.setModel(new DefaultComboBoxModel<Items2>(Items2.values()));
		desplegable2.setBounds(20, 60, 150, 30);
		desplegable2.setBackground(colorFondo);
		desplegable2.setForeground(Color.LIGHT_GRAY);
		desplegable2.setBorder(borde);
		desplegable2.setRenderer(new JComboBoxPersonalizado());
		panel2.add(desplegable2);
		
		//CAMPO DE TEXTO
		campoTexto2= new JFormattedTextField();
		campoTexto2.setBounds(210, 60, 150, 30);
		campoTexto2.setBackground(colorFondo);
		campoTexto2.setForeground(Color.LIGHT_GRAY);
		campoTexto2.setHorizontalAlignment(JFormattedTextField.CENTER);
		campoTexto2.setBorder(borde);
		panel2.add(campoTexto2);
		
		Color colorFondoBotones= new Color(0xCF3476);
		
		//BOTÓN CONVERTIR
		convertir2= new JButton();
		convertir2.setText("Convertir");
		convertir2.setBounds(20, 140, 150, 30);
		convertir2.setBackground(colorFondoBotones);
		convertir2.setForeground(Color.LIGHT_GRAY);
		convertir2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				SeleccionarTemperatura();
			}
		});
		panel2.add(convertir2);
		
		//BOTÓN SALIR
		salir2= new JButton();
		salir2.setText("Salir");
		salir2.setBounds(210, 140, 150, 30);
		salir2.setBackground(colorFondoBotones);
		salir2.setForeground(Color.LIGHT_GRAY);
		salir2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panel2.add(salir2);
		
		//BOTÓN RESETEAR
		resetear2= new JButton();
		resetear2.setText("R");
		resetear2.setBounds(315, 230, 45, 30);
		resetear2.setBackground(colorFondoBotones);
		resetear2.setForeground(Color.LIGHT_GRAY);
		resetear2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desplegable2.setSelectedIndex(0);
				campoTexto2.setValue("");
				resultado2.setText("");
				valorEntrada1= 0.00;
				valorEntrada2= 0;
			}
		});
		panel2.add(resetear2);
		
		//ETIQUETA RESULTADO
		Font fuenteResultado= new Font("Calibri", Font.BOLD, 16);
		Color colorResultado= new Color(0x515151);
		
		resultado2= new JLabel();
		resultado2.setText("");
		resultado2.setBounds(20, 210, 340, 30);
		resultado2.setForeground(colorResultado);
		resultado2.setFont(fuenteResultado);
		panel2.add(resultado2);
	}
	//VALIDACIÓN ENTRADA 2
	public boolean Validar2(String texto) {
		try {
			double valor= Double.parseDouble(texto);
			if(valor > 0);
			valorEntrada1= valor;
			return true;
		}
		catch(NumberFormatException e) {
			resultado2.setText("La conversión solo permite números!");
			return false;
		}
	}
	//CONVERSIÓN CELSIUS A FAHRENHEIT
	public void CelsiusAFahrenheit() {
		double fahrenheit= (valorEntrada1 * 1.8) + 32;
		resultado2.setText("Resultado de la conversión: " + Redondear(fahrenheit) + " °F");
	}
	//CONVERSIÓN CELSIUS A KELVIN
	public void CelsiusAKelvin() {
		double kelvin= valorEntrada1 + 273.15;
		resultado2.setText("Resultado de la conversión: " + Redondear(kelvin) + " K");
	}
	//CONVERSIÓN FAHRENHEIT A CELSIUS
	public void FahrenheitACelsius() {
		double celsius= (valorEntrada1 - 32) / 1.8;
		resultado2.setText("Resultado de la conversión: " + Redondear(celsius) + " °C");
	}
	//CONVERSIÓN FAHRENHEIT A KELVIN
	public void FahrenheitAKelvin() {
		double kelvin= ((valorEntrada1 - 32) * 5/9) + 273.15;
		resultado2.setText("Resultado de la conversión: " + Redondear(kelvin) + " K");
	}
	//CONVERSIÓN KELVIN A CELSIUS
	public void KelvinACelsius() {
		double celsius= valorEntrada1 - 273.15;
		resultado2.setText("Resultado de la conversión: " + Redondear(celsius) + " °C");
	}
	//CONVERSIÓN KELVIN A FAHRENHEIT
	public void KelvinAFahrenheit() {
		double fahrenheit= ((valorEntrada1 - 273.15) * 1.8) + 32;
		resultado2.setText("Resultado de la conversión: " + Redondear(fahrenheit) + " °F");
	}
	//SELECCIÓN TEMPERATURA
	private void SeleccionarTemperatura() {
		if(Validar2(campoTexto2.getText())) {
			Items2 items= (Items2) desplegable2.getSelectedItem();
			switch(items) {
			case Seleccione_Opción:
				resultado2.setText("La conversión solo permite números!");
				break;
			case CELSIUS_a_FAHRENHEIT:
				CelsiusAFahrenheit();
				break;
			case CELSIUS_a_KELVIN:
				CelsiusAKelvin();
				break;
			case FAHRENHEIT_a_CELSIUS:
				FahrenheitACelsius();
				break;
			case FAHRENHEIT_a_KELVIN:
				FahrenheitAKelvin();
				break;
			case KELVIN_a_CELSIUS:
				KelvinACelsius();
				break;
			case KELVIN_a_FAHRENHEIT:
				KelvinAFahrenheit();
				break;
			default:
				throw new IllegalArgumentException("Valor inesperado: " + items);
			}
		}
	}
	//ITEMS CONVERSIÓN JCOMBOBOX 3
	public enum Items3 {
		Seleccione_Opción,
		DECIMAL_a_BINARIO,
		DECIMAL_a_HEXADECI,
		DECIMAL_a_OCTAL,
		BINARIO_a_DECIMAL,
		OCTAL_a_DECIMAL
	}
	//PANEL DERECHO 3: NUMERACIÓN
	private void cargarNumeracion() {
		Color colorFondo= new Color(0x191919);
		Color colorBorde= new Color(0xCF3476);
		Border borde= BorderFactory.createLineBorder(colorBorde, 2);
		
		//MENÚ DESPLEGABLE
		desplegable3= new JComboBox<Items3>();
		desplegable3.setModel(new DefaultComboBoxModel<Items3>(Items3.values()));
		desplegable3.setBounds(20, 60, 150, 30);
		desplegable3.setBackground(colorFondo);
		desplegable3.setForeground(Color.LIGHT_GRAY);
		desplegable3.setBorder(borde);
		desplegable3.setRenderer(new JComboBoxPersonalizado());
		panel3.add(desplegable3);
		
		//CAMPO DE TEXTO
		campoTexto3= new JFormattedTextField();
		campoTexto3.setBounds(210, 60, 150, 30);
		campoTexto3.setBackground(colorFondo);
		campoTexto3.setForeground(Color.LIGHT_GRAY);
		campoTexto3.setHorizontalAlignment(JFormattedTextField.CENTER);
		campoTexto3.setBorder(borde);
		panel3.add(campoTexto3);
		
		Color colorFondoBotones= new Color(0xCF3476);
		
		//BOTÓN CONVERTIR
		convertir3= new JButton();
		convertir3.setText("Convertir");
		convertir3.setBounds(20, 140, 150, 30);
		convertir3.setBackground(colorFondoBotones);
		convertir3.setForeground(Color.LIGHT_GRAY);
		convertir3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				SeleccionarNumeracion();
			}
		});
		panel3.add(convertir3);
		
		//BOTÓN SALIR
		salir3= new JButton();
		salir3.setText("Salir");
		salir3.setBounds(210, 140, 150, 30);
		salir3.setBackground(colorFondoBotones);
		salir3.setForeground(Color.LIGHT_GRAY);
		salir3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panel3.add(salir3);
		
		//BOTÓN RESETEAR
		resetear3= new JButton();
		resetear3.setText("R");
		resetear3.setBounds(315, 230, 45, 30);
		resetear3.setBackground(colorFondoBotones);
		resetear3.setForeground(Color.LIGHT_GRAY);
		resetear3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desplegable3.setSelectedIndex(0);
				campoTexto3.setValue("");
				resultado3.setText("");
				valorEntrada1= 0.00;
				valorEntrada2= 0;
			}
		});
		panel3.add(resetear3);
		
		//ETIQUETA RESULTADO
		Font fuenteResultado= new Font("Calibri", Font.BOLD, 16);
		Color colorResultado= new Color(0x515151);
		
		resultado3= new JLabel();
		resultado3.setText("");
		resultado3.setBounds(20, 210, 340, 30);
		resultado3.setForeground(colorResultado);
		resultado3.setFont(fuenteResultado);
		panel3.add(resultado3);
	}
	//VALIDACIÓN ENTRADA 3
	public boolean Validar3(String texto) {
		try {
			int valor= Integer.parseInt(texto);
			if(valor > 0);
			valorEntrada2= valor;
			return true;
		}
		catch(NumberFormatException e) {
			resultado3.setText("La conversión solo permite números!");
			return false;
		}
	}
	//CONVERSIÓN DECIMAL A BINARIO
	public void DecimalABinario(int valorEntrada2) {
		int decimal= valorEntrada2;
		int resto;
		String binario= "";
		
		while(decimal > 0) {
			resto= decimal % 2;
			binario= resto + binario;
			decimal= decimal / 2;
		}
		resultado3.setText("Resultado de la conversión: " + binario);
	}
	//CONVERSIÓN DECIMAL A HEXADECIMAL
	public void DecimalAHexadecimal(int valorEntrada2) {
		int decimal= valorEntrada2;
		int resto;
		char[] arregloHexadecimales= {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
		String hexadecimal= "";
		
		while(decimal > 0) {
			resto= decimal % 16;
			char caracter= arregloHexadecimales[resto];
			hexadecimal= caracter + hexadecimal;
			decimal= decimal / 16;
		}
		resultado3.setText("Resultado de la conversión: " + hexadecimal);
	}
	//CONVERSIÓN DECIMAL A OCTAL
	public void DecimalAOctal(int valorEntrada2) {
		int decimal= valorEntrada2;
		int resto;
		char[] arregloOctal= {'0', '1', '2', '3', '4', '5', '6', '7'};
		String octal= "";
		
		while(decimal > 0) {
			resto= decimal % 8;
			char caracter= arregloOctal[resto];
			octal= caracter + octal;
			decimal= decimal / 8;
		}
		resultado3.setText("Resultado de la conversión: " + octal);
	}
	//CONVERSIÓN BINARIO A DECIMAL
	public void BinarioADecimal(int valorEntrada2) {
		int binario= valorEntrada2;
		int resto;
		int potencia= 0;
		int decimal= 0;
		
		while(binario > 0) {
			resto= binario % 10;
			decimal+= resto * Math.pow(2, potencia);
			binario= binario / 10;
			potencia++;
		}
		resultado3.setText("Resultado de la conversión: " + decimal);
	}
	//CONVERSIÓN HEXADECIMAL A DECIMAL
	public void HexadecimalADecimal(String valorEntrada3) {
		String hexadecimal= valorEntrada3.toUpperCase();
		String cadenaHexadecimales= "0123456789ABCDEF";
		int decimal= 0;
		
		for(int i= 0; i < hexadecimal.length(); i++) {
			char caracter= hexadecimal.charAt(i);
			int posicionCaracter= cadenaHexadecimales.indexOf(caracter);
			decimal= (decimal * 16) + posicionCaracter;
		}
		resultado3.setText("Resultado de la conversión: " + decimal);
	}
	//CONVERSIÓN OCTAL A DECIMAL
	public void OctalADecimal(int valorEntrada2) {
		int octal= valorEntrada2;
		int resto;
		int potencia= 0;
		int decimal= 0;
		
		while(octal > 0) {
			resto= octal % 10;
			decimal+= resto * Math.pow(8, potencia);
			octal= octal / 10;
			potencia++;
		}
		resultado3.setText("Resultado de la conversión: " + decimal);
	}
	//SELECCIÓN NUMERACIÓN
	private void SeleccionarNumeracion() {
		if(Validar3(campoTexto3.getText())) {
			Items3 items= (Items3) desplegable3.getSelectedItem();
			switch(items) {
			case Seleccione_Opción:
				resultado3.setText("La conversión solo permite números!");
				break;
			case DECIMAL_a_BINARIO:
				DecimalABinario(valorEntrada2);
				break;
			case DECIMAL_a_HEXADECI:
				DecimalAHexadecimal(valorEntrada2);
				break;
			case DECIMAL_a_OCTAL:
				DecimalAOctal(valorEntrada2);
				break;
			case BINARIO_a_DECIMAL:
				BinarioADecimal(valorEntrada2);
				break;
			case OCTAL_a_DECIMAL:
				OctalADecimal(valorEntrada2);
				break;
			default:
				throw new IllegalArgumentException("Valor inesperado: " + items);
			}
		}
	}
	//PANEL DERECHO 4: ACERCA DE
	private void cargarAcercaDe() {
		//PANEL EDITOR
		Font fuenteAcercaDe= new Font("Calibri", Font.BOLD, 13);
		Color colorLetraAcercaDe= new Color(0x515151);
		Color colorFondoAcercaDe= new Color(0x191919);
		
		JEditorPane acercaDe= new JEditorPane();
		acercaDe.setText("\r\n"
				+ "Conversor X v1.0 creado por Carla Romina Soledad Rossetti para un challenge de la institución educativa Alura Latam que forma   parte del programa One-Oracle Next Education impartido por    Oracle Corporation.\r\n"
				+ "\r\n"
				+ "Conversor X tiene tres apartados:\r\n"
				+ "\r\n"
				+ "1 Conversión de divisas, para el que se utilizó una biblioteca de    código abierto de Google conocida como GSON, ubicada en el       repositorio central Maven de Sonatype. Para la comunicación     mediante API se utilizó el servicio host gratuito Exchange rates.\r\n"
				+ "\r\n"
				+ "2 Conversión de temperatura, para el que se utilizó el sistema     internacional de unidades de medida de temperatura.\r\n"
				+ "\r\n"
				+ "3 Conversión de numeración, para el que se utilizó el sistema       internacional de numeración decimal, binario, hexadecimal y       octal.\r\n"
				+ "\r\n"
				+ "© copyright 2023 Carla Romina Soledad Rossetti & Alura Latam.\r\n"
				+ "\r\n"
				+ "Todos los derechos reservados.\r\n"
				+ "\r\n"
				+ "Queda prohibida la reproducción total o parcial, distribución,       publicación o cualquier otro uso comercial o público sin el              consentimiento expreso por escrito del titular del copyright.\r\n"
				+ "\r\n"
				+ "Sin embargo, se permite el uso privado y personal del proyecto,  siempre y cuando no implique la reproducción o distribución       pública, y se reconozca adecuadamente la autoría del mismo.\r\n"
				+ "\r\n"
				+ "Cualquier infracción de los derechos de autor será perseguida       legalmente de acuerdo con las leyes vigentes.\r\n"
				+ "\r\n"
				+ "Este copyright protege la integridad y originalidad del proyecto,  con el propósito de garantizar su respeto y evitar el plagio o uso no autorizado.\r\n"
				+ "\r\n"
				+ "No nos hacemos responsables por los daños que puedan               ocasionar el uso no autorizado o inadecuado del proyecto.\r\n"
				+ "\r\n"
				+ "Todas las consultas y solicitudes de uso deben dirigirse al titular  del copyright a través del correo electrónico:\r\n"
				+ "\r\n"
				+ "carlarominasrossetti@gmail.com\r\n");
		acercaDe.setBounds(10, 10, 360, 220);
		acercaDe.setBackground(colorFondoAcercaDe);
		acercaDe.setForeground(colorLetraAcercaDe);
		acercaDe.setFont(fuenteAcercaDe);
		
		//PANEL DESPLAZAMIENTO
		class BarraDesplazamientoPersonalizada extends BasicScrollBarUI {
			@Override
			protected void configureScrollBarColors() {
				Color colorPista= new Color(0x202020);
				Color colorPulgar= new Color(0xCF3476);
				this.trackColor= colorPista;
				this.thumbColor= colorPulgar;
			}
			@Override
			protected JButton createIncreaseButton(int orientation) {
				return new JButton() {
					private static final long serialVersionUID = 1L;
					
					@Override
					public void paint(Graphics g) {
						Color colorFlecha= new Color(0xCF3476);
						
						Graphics2D g2= (Graphics2D) g;
						g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
						g2.setColor(colorFlecha);
						int x= getWidth() / 2;
						int y= getHeight() / 2;
						int size= 4;
						
						int[] xp= {x - size, x, x + size};
						int[] yp= {y - size, y + size, y - size};
						g2.fillPolygon(xp, yp, 3);
					}
				};
			}
			@Override
			protected JButton createDecreaseButton(int orientation) {
				return new JButton() {
					private static final long serialVersionUID = 1L;
					
					@Override
					public void paint(Graphics g) {
						Color colorFlecha= new Color(0xCF3476);
						
						Graphics2D g2= (Graphics2D) g;
						g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
						g2.setColor(colorFlecha);
						int x= getWidth() / 2;
						int y= getHeight() / 2;
						int size= 4;
						
						int[] xp= {x - size, x, x + size};
						int[] yp= {y + size, y - size, y + size};
						g2.fillPolygon(xp, yp, 3);
					}
				};
			}
		}
		
		JScrollPane desplazamiento= new JScrollPane();
		desplazamiento.setBounds(0, 0, 380, 220);
		desplazamiento.setViewportView(acercaDe);
		desplazamiento.getVerticalScrollBar().setUI(new BarraDesplazamientoPersonalizada());
		panel4.add(desplazamiento);
		
		//BOTÓN SALIR
		Color colorFondoBoton= new Color(0xCF3476);
		
		salir4= new JButton();
		salir4.setText("Salir");
		salir4.setBounds(115, 235, 150, 30);
		salir4.setBackground(colorFondoBoton);
		salir4.setForeground(Color.LIGHT_GRAY);
		salir4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panel4.add(salir4);
	}
	//EVENTOS MENÚ BOTONES
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == boton1) {
			panel1.setVisible(true);
			panel2.setVisible(false);
			panel3.setVisible(false);
			panel4.setVisible(false);
			cargarDivisas();
		}
		if(e.getSource() == boton2) {
			panel1.setVisible(false);
			panel2.setVisible(true);
			panel3.setVisible(false);
			panel4.setVisible(false);
			cargarTemperatura();
		}
		if(e.getSource() == boton3) {
			panel1.setVisible(false);
			panel2.setVisible(false);
			panel3.setVisible(true);
			panel4.setVisible(false);
			cargarNumeracion();
		}
		if(e.getSource() == boton4) {
			panel1.setVisible(false);
			panel2.setVisible(false);
			panel3.setVisible(false);
			panel4.setVisible(true);
			cargarAcercaDe();
		}
	}
	//CONEXIÓN API
	private void ConexionApi(String enlaceURL) {
		try {
			//SOLICITAR PETICIÓN
			URL url= new URL(enlaceURL);
			HttpURLConnection conexion= (HttpURLConnection) url.openConnection();
			conexion.setRequestMethod("GET");
			conexion.connect();
			
			//COMPROBAR PETICIÓN CORRECTA
			int codigoRespuesta= conexion.getResponseCode();
			if(codigoRespuesta != 200) {
				throw new RuntimeException("Ocurrió un error: " + codigoRespuesta);
			}
			else {
				//LEER FLUJO DATOS
				Gson gson= new Gson();
				InputStreamReader lectorFlujoEntrada= new InputStreamReader((InputStream) conexion.getContent());
				
				//CONVERTIR DATOS JSON A JSON OBJECT
				JsonObject objetoJson= gson.fromJson(lectorFlujoEntrada, JsonObject.class);
				String resultado= objetoJson.get("result").getAsString();
				Double valor= Double.valueOf(resultado);
				
				ars= (valor);
				usd= (valor);
				eur= (valor);
				gbp= (valor);
				jpy= (valor);
				krw= (valor);
				ars_u= (valor);
				ars_e= (valor);
				ars_g= (valor);
				ars_j= (valor);
				ars_k= (valor);
				
				//MOSTRAR DATOS
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}

/*
Página librería gson:
https://search.maven.org/artifact/com.google.code.gson/gson/2.10.1/jar?eh=
Página consumo api:
https://exchangerate.host/#/
*/
