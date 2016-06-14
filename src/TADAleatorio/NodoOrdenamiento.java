package TADAleatorio;

public class NodoOrdenamiento<T, R> {
	
	private NodoOrdenamiento<T, R> hijo;
	private T elemento;
	private R comparacion;
	
	private int altura;
	
	NodoOrdenamiento(T nuevoElemento, R comparador){
		hijo = null;
		elemento = nuevoElemento;
		comparacion = comparador;
		altura = 1;
	}
	
	public void agregarHijo(T nuevoHijo, R comparadorHijo){
		if (hijo==null) {
			hijo = new NodoOrdenamiento<T, R>(nuevoHijo, comparadorHijo);
		}else{
			hijo.agregarHijo(nuevoHijo, comparadorHijo);
		}
		altura++;
	}
	
	public T darElemento(){
		return elemento;
	}
	
	public boolean esIgual(R comparar){
		return comparacion.equals(comparar);
	}
	
	public int darAltura(){
		return altura;
	}

	public boolean tieneHijo() {
		return hijo!=null;
	}

	public NodoOrdenamiento<T, R> darHijo() {
		return hijo;
	}
	
}
