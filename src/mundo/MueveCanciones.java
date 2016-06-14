package mundo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import TADAleatorio.TablaOrdenamiento;

public class MueveCanciones {

	private File directorio;
	private int EntraIncial;

	private String rutaDestino, rutaCopiar;
	private int cancionesXCarpeta;
	
	public MueveCanciones(){
		
	}
	
	public void cambiarCancionesPorCarpeta(Integer carpetas, Integer canciones){
		if (canciones==null) {
			cancionesXCarpeta = -carpetas;
		}else{
			cancionesXCarpeta = canciones;
		}
	}
	
	public String darRuta(){
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(directorio);
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.showOpenDialog(null);
		File temp2 = fc.getSelectedFile();
		if (temp2 == null) {
			return "-0-";
		}
		String direction = temp2.getAbsolutePath();
		return direction;
	}
	
	public void moverCanciones() {
		//String direccion = "C:\\Users\\Camilo P\\Music";
		//<int cancionesXCarpeta = 15;
		//String rutaDestino = "C:\\Users\\Camilo P\\Music\\CarpetaCanciones\\";//ruta donde
		// se pega
		//String rutaCopiar = "C:\\Users\\Camilo P\\Music\\";// ruta desde donde se copia
		// parte de copiar
		
		//String direction = darRuta();
		//if (direction != null) {
		//	System.out.println(direction);
		//}
		
		
//		long tiempoInicial = System.currentTimeMillis();
		
		directorio = new File(rutaCopiar);// debe haber doble slash despues del
		rutaCopiar = rutaCopiar+"\\";
		rutaDestino = rutaDestino+"\\";
		// aqui obtengo todos los elementos de un directorio
		String[] ficheros = directorio.list();

		// Se va a hacer un filtro de las canciones, es decir, extension .mp3
		ArrayList<String> archivos = new ArrayList<String>();
		for (String string : ficheros) {
			if (string.endsWith(".mp3")) {
				archivos.add(string);
			}
		}
		
		int numeroCanciones = archivos.size();
////////System.out.println(numeroCanciones);
		// definir el número de carpetas
		int numCarpetas = 0;
		if (cancionesXCarpeta<0) {
			numCarpetas = -cancionesXCarpeta;
			cancionesXCarpeta = -cancionesXCarpeta;
			if (numeroCanciones%cancionesXCarpeta==0) {
				cancionesXCarpeta = (int) numeroCanciones/numCarpetas;
			}else{
				cancionesXCarpeta = ((int) numeroCanciones/numCarpetas)+1;
			}
		}else{
			numCarpetas = ((int) numeroCanciones / cancionesXCarpeta) + 1;
		}
		
		archivos = ordenAleatorio(archivos);
		TablaOrdenamiento<String, String> aleatorio = new TablaOrdenamiento<>();

		for (String string : archivos) {
			String cancion[] = string.split(" - ");
			if (cancion.length != 2) {
				aleatorio.agregarElemento(string, cancion[0]);
			} else {
				aleatorio.agregarElemento(string, cancion[0]);
			}
		}

		// un recorrido crea el numero de carpetas y el otro copia los elementos
		for (int i = 0; i < numCarpetas; i++) {
			aleatorio.volverAIniciar();
			// asigna el nombre de la nueva carpeta
			String rutaCarpeta = rutaDestino + "Lista " + (i + 1) + "\\";
			// crea la nueva carpeta
			File directorioTemp = new File(rutaCarpeta);
			directorioTemp.mkdir();
			for (int j = 0; j < cancionesXCarpeta; j++) {
				if (aleatorio.esVacio()) {// archivos.isEmpty()
					break;
				}
				// Fichero desde el cual se van a copiar los datos
				// String nom = archivos.remove(0);
				String nom = aleatorio.darSiguiente();
				File FOrigen = new File(rutaCopiar + nom);
				File FDestino = new File(rutaCarpeta + nom);
				Copiar(FOrigen, FDestino);
				EntraIncial = 0;
				if (archivos.isEmpty()) {
					break;
				}
			}
////////////System.out.println("Completado " + (i + 1) + " de " + numCarpetas);
		}
////////System.out.println("Se ha terminado de copiar las " + numeroCanciones
//				+ " canciones exitosamente");

//		long tiempoTotal = System.currentTimeMillis() - tiempoInicial;
//		double temp = tiempoTotal;
////////System.out.println("Accion tomó " + temp + " segundos");
	}
	
	public static ArrayList<String> ordenAleatorio(ArrayList<String> lista) {
		ArrayList<String> retorno = new ArrayList<>();
		while (!lista.isEmpty()) {
			int elem = (int) ((lista.size() * Math.random()));
			retorno.add(lista.remove(elem));
		}
		return retorno;
	}

	/*
	 * Método que copia completamente una carpeta usando recursividad
	 * PARAMETRO1:FOrigen:Fichero o carpeta que se desea copiar
	 * PARAMETRO2:FDestino:Carpeta destino
	 */
	public void Copiar(File FOrigen, File FDestino) {
		// si el origen no es una carpeta
		if (!FOrigen.isDirectory()) {
			// Llamo la funcion que lo copia
			CopiarFichero(FOrigen, FDestino);
		} else {
			// incremento el contador de entradas a esta función
			EntraIncial++;
			// solo se entra acá cuando se quiera copiar una carpeta y
			// sea la primera es decir la carpeta padre
			if (EntraIncial == 1) {
				// Cambio la ruta destino por el nombre que tenia mas el nombre
				// de
				// la carpeta padre
				FDestino = new File(FDestino.getAbsolutePath() + "/"
						+ FOrigen.getName());
				// si la carpeta no existe la creo
				if (!FDestino.exists()) {
					FDestino.mkdir();
				}
			}
			// obtengo el nombre de todos los archivos y carpetas que
			// pertenecen a este fichero(FOrigen)
			String[] Rutas = FOrigen.list();
			// recorro uno a uno el contenido de la carpeta

			/*
			 * IMPORTANTE:EL HML SE DESCONFIGURA Y NO ME DEJA PORNE LA LINEA FOR
			 * SIGUIENTE BIEN, TENGO PROBLEMA CON EL SIGNO MENOR.SI UD LE PASA
			 * LO MISMO DESCARGE EL PROGRAMA CON EL LINK DE ABAJO Y VÉALO DE
			 * FORMA SEGURA
			 */
			for (int i = 0; i < Rutas.length; i++) {
				// establezco el nombre del nuevo archivo origen
				File FnueOri = new File(FOrigen.getAbsolutePath() + "/"
						+ Rutas[i]);
				// establezco el nombre del nuevo archivo destino
				File FnueDest = new File(FDestino.getAbsolutePath() + "/"
						+ Rutas[i]);
				// si no existe el archivo destino lo creo
				if (FnueOri.isDirectory() && !FnueDest.exists()) {
					FnueDest.mkdir();
				}
				// uso recursividad y llamo a esta misma funcion has llegar
				// al ultimo elemento
				Copiar(FnueOri, FnueDest);
			}
		}
	}

	/*
	 * Funcio que copia un ficheroPARAMETRO1:FOrigen:Fichero o carpeta que se
	 * desea copiarPARAMETRO2:FDestino:Carpeta destino
	 */
	public static void CopiarFichero(File FOrigen, File FDestino) {
		try {
			// Si el archivo a copiar existe
			if (FOrigen.exists()) {
				// Flujo de lectura al fichero origen(que se va a copiar)
				FileInputStream LeeOrigen = new FileInputStream(FOrigen);
				// Flujo de lectura al fichero destino(donde se va a copiar)
				OutputStream Salida = new FileOutputStream(FDestino);
				// separo un buffer de 1MB de lectura
				byte[] buffer = new byte[1024];
				int tamaño;
				// leo el fichero a copiar cada 1MB
				while ((tamaño = LeeOrigen.read(buffer)) > 0) {
					Salida.write(buffer, 0, tamaño);
				}
				// cierra los flujos de lectura y escritura
				Salida.close();
				LeeOrigen.close();

			} else {// el fichero a copiar no existe
				JOptionPane.showMessageDialog(null, "El fichero a copiar no existe..."
						+ FOrigen.getAbsolutePath());
//				System.out.println("El fichero a copiar no existe..."
//						+ FOrigen.getAbsolutePath());
			}

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
			//System.out.println(ex.getMessage());
		}
	}
	

	public String getRutaDestino() {
		return rutaDestino;
	}

	public void setRutaDestino(String rutaDestino) {
		this.rutaDestino = rutaDestino;
	}

	public String getRutaCopiar() {
		return rutaCopiar;
	}

	public void setRutaCopiar(String rutaCopiar) {
		this.rutaCopiar = rutaCopiar;
	}


}
