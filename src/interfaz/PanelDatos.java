package interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class PanelDatos extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public final static String DESTINO = "Destino";
	public final static String FUENTE = "Fuente";
	public final static String ORGANIZAR = "Organizar";
	public final static String CARPETAS = "Carpetas";
	public final static String CANCIONES = "Canciones";
	
	private InterfazPrincipal interfaz;
	private JButton butFuente, butDestino, butEjecutar;
	private JTextField txtNumCarpetas, txtNumCanciones, txtFuente, txtDestino;
	private JRadioButton radNumCarpetas, radNumCanciones;
	
	public PanelDatos(InterfazPrincipal principal){
		interfaz = principal;
		
		TitledBorder border = 
				BorderFactory.createTitledBorder("Canciones aleatorias");
		setBorder(border);
		setLayout(new GridLayout(6, 2));
		
		init();
		
		add(radNumCanciones);
		add(txtNumCanciones);
		add(radNumCarpetas);
		add(txtNumCarpetas);
		add(butDestino);
		add(txtDestino);
		add(butFuente);
		add(txtFuente);
		add(butEjecutar);
		add(new JLabel(""));
	}
	
	private void init() {
		//Botones
		butFuente = new JButton("Seleccionar ubicación de las canciones");
		butFuente.setActionCommand(FUENTE);
		butFuente.addActionListener(this);
		
		butDestino = new JButton("Seleccionar ubicación destino");
		butDestino.setActionCommand(DESTINO);
		butDestino.addActionListener(this);
		
		butEjecutar = new JButton("Organizar");
		butEjecutar.setActionCommand(ORGANIZAR);
		butEjecutar.addActionListener(this);
		
		//Radio bottons
		radNumCanciones = new JRadioButton("Número de canciones por carpeta");
		radNumCanciones.addActionListener(this);
		radNumCanciones.setActionCommand(CANCIONES);
		radNumCanciones.setSelected(true);
		radNumCarpetas = new JRadioButton("Número de carpetas que desea");
		radNumCarpetas.addActionListener(this);
		radNumCarpetas.setActionCommand(CARPETAS);
		
		//Button group
		ButtonGroup grupo = new ButtonGroup();
		grupo.add(radNumCanciones);
		grupo.add(radNumCarpetas);
		
		//TxtFields
		txtNumCarpetas = new JTextField();
		txtNumCanciones = new JTextField();
		txtFuente = new JTextField("");
		txtFuente.setEditable(false);
		txtDestino = new JTextField("");
		txtDestino.setEditable(false);
		
		activarDesactivar(txtNumCanciones, txtNumCarpetas);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String evento = arg0.getActionCommand();
		if (evento.equals(DESTINO)) {
			String ruta = interfaz.destino();
			txtDestino.setText(ruta);
		}else if (evento.equals(FUENTE)) {
			String ruta = interfaz.fuente();
			txtFuente.setText(ruta);
		}else if(evento.equals(CARPETAS)){
			activarDesactivar(txtNumCarpetas, txtNumCanciones);
		}else if(evento.equals(CANCIONES)){
			activarDesactivar(txtNumCanciones, txtNumCarpetas);
		}else if (evento.equals(ORGANIZAR)) {
			if (!eligioRutas()) {
				JOptionPane.showMessageDialog(this, "Por favor, ingrese la ruta de copiar y de guardar");
				return;
			}
			if (radNumCanciones.isSelected()) {
				if (verificarNumero(txtNumCanciones)) {
					interfaz.organizar(null, Integer.parseInt(txtNumCanciones.getText()));
				}else{
					JOptionPane.showMessageDialog(this, "Recuerde que debe ingresar un número positivo");
				}
			}else if (radNumCarpetas.isSelected()) {
				if (verificarNumero(txtNumCarpetas)) {
					interfaz.organizar(Integer.parseInt(txtNumCarpetas.getText()), null);
				}else{
					JOptionPane.showMessageDialog(this, "Recuerde que debe ingresar un número positivo");
				}
			}
		}

	}
	
	public boolean eligioRutas() {
		if (txtDestino.getText().equals("-0-") || txtDestino.getText().equals("") || 
				txtFuente.getText().equals("") || txtFuente.getText().equals("-0-")) {
			return false;
		}
		return true;
	}

	public boolean verificarNumero(JTextField txt){
		String texto = txt.getText();
		if (!texto.equals("") && texto!=null) {
			try {
				int prueba = Integer.parseInt(texto);
				if (prueba>0) {
					return true;
				}
				return false;
			} catch (Exception e) {
				return false;
			}
		}else{
			return false;
		}
	}
	
	public void activarDesactivar(JTextField txt1, JTextField txt2){
		txt1.setEnabled(true);
		txt2.setEnabled(false);
		txt2.setText("");
	}
	
}
