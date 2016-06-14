package interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import mundo.MueveCanciones;

public class InterfazPrincipal extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MueveCanciones mueveCanciones;
	private PanelDatos panelDatos;
	
	public InterfazPrincipal(){
		setTitle("Ordenar canciones aleatorias");
		setSize(700, 200);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		panelDatos = new PanelDatos(this);
		mueveCanciones = new MueveCanciones();
		add(panelDatos, BorderLayout.CENTER);
	}
	
	public static void main(String[] args){
		InterfazPrincipal interfaz = new InterfazPrincipal();
		interfaz.setVisible(true);
	}

	public String destino() {
		mueveCanciones.setRutaDestino(mueveCanciones.darRuta());
		return mueveCanciones.getRutaDestino();
	}

	public String fuente() {
		mueveCanciones.setRutaCopiar(mueveCanciones.darRuta());
		return mueveCanciones.getRutaCopiar();
	}

	public void organizar(Integer carpetas, Integer canciones) {
		JOptionPane.showMessageDialog(this, "Se va a empezar el proceso de copiado, este proceso puede tardar unos minutos");
		long tiempoInicial = System.currentTimeMillis();
		mueveCanciones.cambiarCancionesPorCarpeta(carpetas, canciones);
		if (carpetas==null) {
			//se organizará por número de canciones por carpeta
			mueveCanciones.moverCanciones();
		}else{
			//se organizará por el número de carpetas que se desea
			mueveCanciones.moverCanciones();
		}
		long tiempoTotal = System.currentTimeMillis() - tiempoInicial;
		double temp = ((double)tiempoTotal)/1000;
		JOptionPane.showMessageDialog(this, "Acción completada en "+temp+" segundos");
	}
	
}
