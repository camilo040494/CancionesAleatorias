package TADAleatorio;

import java.util.ArrayList;

public class TablaOrdenamiento<T, R> {
	
	//TODO metodo que pueda pasar los de mayor altura al inicio
	
	//T será el elemento
	private ArrayList<NodoOrdenamiento<T, R>> hijos;
	//numero total de elementos en toda la tabla
	private int numeroElementos;//numero de elementos en total
	private int posActual;//posicion que apunta en el arraylist de hijos
	public TablaOrdenamiento(){
		hijos = new ArrayList<>();
		volverAIniciar();
	}
	
	
	//organiza pseudo-aleatoriamente de mayor a menor, donde grupos será el grupo de carpetas que habrá
	//entonces se usará para 
	//grupos debería ser mayor que el número de hijos (no de elementos)
	public void organizar(int grupos){
		//el método buscará los que sean más pequeños y los pasará al final
		
		//deberá preocuparse mucho por que el orden sea algo estricto?
		int numeroMayores = buscarCantidadMayores(grupos);
		if (numeroMayores>grupos) {
			//no cabrán en los grupos, entonces se debería 
		}else if(numeroMayores==grupos){
			//deberían quedar al principio
		}else{
			//no es realmente importante
		}
		
	}
	
	private int buscarCantidadMayores(int altura) {
		int retorno = 0;
		for (NodoOrdenamiento<T, R> hijo : hijos) {
			if (hijo.darAltura()>=altura) {
				retorno++;
			}
		}
		return retorno;
	}


	//puede haber canciones repetidas
	public void agregarElemento(T elemento, R comparar){
		boolean agrego = false;
		for (NodoOrdenamiento<T, R> nodo : hijos) {
			if (nodo.esIgual(comparar)) {
				nodo.agregarHijo(elemento, comparar);
				agrego = true;
				break;
			}
		}
		if (!agrego) {
			hijos.add(new NodoOrdenamiento<T, R>(elemento, comparar));
		}
		numeroElementos++;
	}
	
	
	public T darSiguiente(){
		if (hijos.size()<=posActual) {//si posActual es mayor al numero de ramas
			if (numeroElementos==0) {//si numeroElementos es 0 es porque es vacio
				return null;
			}else{
				volverAIniciar();//es porque simplemente siguio de posicion
				return darSiguiente();
			}
		}
		
		NodoOrdenamiento<T, R> actual = hijos.get(posActual);
		T retorno = actual.darElemento();
		//puede ser que sea hoja y no tenga hijos
		if (actual.tieneHijo()) {
			//intercambia el hijo por el nieto en el arraylist en la misma posicion
			hijos.set(posActual, actual.darHijo());
			posActual++;
			//es necesario cambiar la posicion actual
		}else{
			//si no tiene hijos lo borra
			hijos.remove(posActual);
			//la posicion actual sera la siguiente posicion
		}
		numeroElementos--;
		
		return retorno;
	}
	
	public void volverAIniciar(){
		posActual = 0;
	}
	
	public boolean esVacio(){
		return hijos.isEmpty();
	}
	
	//si no tiene elementos retornará 0, ademas la posicion en que está
	public int darMayorAltura(){
		int mayorAltura = 0;
		//int posicion = -1;
		for (int i = 0; i < hijos.size(); i++) {
			int alturaTemp = hijos.get(i).darAltura();
			if (alturaTemp>mayorAltura) {
				mayorAltura = alturaTemp;
				//posicion = i;
			}
		}
		return mayorAltura;
	}


	public int darNumeroElementos() {
		return numeroElementos;
	}
	
}
