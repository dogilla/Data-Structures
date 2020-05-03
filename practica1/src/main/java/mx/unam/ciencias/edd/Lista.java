package mx.unam.ciencias.edd;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>Clase genérica para listas doblemente ligadas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas no aceptan a <code>null</code> como elemento.</p>
 *
 * @param <T> El tipo de los elementos de la lista.
 */
public class Lista<T> implements Coleccion<T> {

    /* Clase interna privada para nodos. */
    private class Nodo {
        /* El elemento del nodo. */
        public T elemento;
        /* El nodo anterior. */
        public Nodo anterior;
        /* El nodo siguiente. */
        public Nodo siguiente;

        /* Construye un nodo con un elemento. */
        public Nodo(T elemento) {
            this.elemento = elemento;
        }
    }

    /* Clase interna privada para iteradores. */
    private class Iterador implements IteradorLista<T> {
        /* El nodo anterior. */
        public Nodo anterior;
        /* El nodo siguiente. */
        public Nodo siguiente;

        /* Construye un nuevo iterador. */
        public Iterador() {
            start();
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override public boolean hasNext() {
            return siguiente != null;
        }

        /* Nos da el elemento siguiente. */
        @Override public T next() {
          if (siguiente == null)
            throw new NoSuchElementException();
          anterior = siguiente;
          siguiente = siguiente.siguiente;
          return anterior.elemento;
        }

        /* Nos dice si hay un elemento anterior. */
        @Override public boolean hasPrevious() {
            return anterior != null;
        }

        /* Nos da el elemento anterior. */
        @Override public T previous() {
          if (anterior == null)
            throw new NoSuchElementException();
          siguiente = anterior;
          anterior = anterior.anterior;
          return siguiente.elemento;
        }

        /* Mueve el iterador al inicio de la lista. */
        @Override public void start() {
          siguiente = cabeza;
          anterior = null;
        }

        /* Mueve el iterador al final de la lista. */
        @Override public void end() {
          anterior = rabo;
          siguiente = null;
        }
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Número de elementos en la lista. */
    private int longitud;

    /**
     * Regresa la longitud de la lista. El método es idéntico a {@link
     * #getElementos}.
     * @return la longitud de la lista, el número de elementos que contiene.
     */
    public int getLongitud() {
        return longitud;
    }

    /**
     * Regresa el número elementos en la lista. El método es idéntico a {@link
     * #getLongitud}.
     * @return el número elementos en la lista.
     */
    @Override public int getElementos() {
        return longitud;
    }

    /**
     * Nos dice si la lista es vacía.
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    @Override public boolean esVacia() {
        if (cabeza == null){ return true; } else { return false; }
    }

    /**
     * Agrega un elemento a la lista. Si la lista no tiene elementos, el
     * elemento a agregar será el primero y último. El método es idéntico a
     * {@link #agregaFinal}.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    @Override public void agrega(T elemento) {
      if (elemento == null)
      throw new IllegalArgumentException();
      Nodo a = new Nodo(elemento);
      if (cabeza == null) {
        cabeza = rabo = a;
      } else {
        rabo.siguiente = a;
        rabo.siguiente.anterior = rabo;
        rabo = rabo.siguiente;
      }
      longitud ++;
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaFinal(T elemento) {
      if (elemento == null)
      throw new IllegalArgumentException();
      Nodo a = new Nodo(elemento);
      if (cabeza == null) {
        cabeza = rabo = a;
      } else {
        rabo.siguiente = a;
        rabo.siguiente.anterior = rabo;
        rabo = rabo.siguiente;
      }
      longitud ++;
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaInicio(T elemento) {
      if (elemento == null)
      throw new IllegalArgumentException();
      Nodo a = new Nodo(elemento);
      if (cabeza == null) {
        cabeza = rabo = a;
      } else {
        cabeza.anterior = a;
        cabeza.anterior.siguiente = cabeza;
        cabeza = cabeza.anterior;
      }
      longitud ++;
    }

    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor o igual que cero, el elemento se agrega al inicio
     * de la lista. Si el índice es mayor o igual que el número de elementos en
     * la lista, el elemento se agrega al fina de la misma. En otro caso,
     * después de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * @param i el índice dónde insertar el elemento. Si es menor que 0 el
     *          elemento se agrega al inicio de la lista, y si es mayor o igual
     *          que el número de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void inserta(int i, T elemento) {
      if (elemento == null) {
        throw new IllegalArgumentException();
      } else if  (i <= 0) {
        agregaInicio(elemento);
      } else if (longitud <= i) {
        agregaFinal(elemento);
      } else {
        Nodo n = cabeza;
        int a = i;
        while (i > 0) {
          n = n.siguiente;
          i--;
        }
        i = a;
        Nodo insertado = new Nodo(elemento);
        n.anterior.siguiente = insertado;
        insertado.anterior = n.anterior;
        insertado.siguiente = n;
        n.anterior = insertado;
        longitud++;
     }
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica.
     * @param elemento el elemento a eliminar.
     */
    @Override public void elimina(T elemento) {
      Nodo n = cabeza;
      if (n == null)
      return;
      while (n != null){
        if(n.elemento.equals(elemento)){
          if(n.anterior == null){
            eliminaPrimero();
            return;
          }else if(n.siguiente == null){
            eliminaUltimo();
            return;
          }else{
            n.anterior.siguiente = n.siguiente;
            n.siguiente.anterior = n.anterior;
            longitud--;
            return;
          }
        }
        n = n.siguiente;
      }
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaPrimero() {
      if(cabeza == null) {
      throw new NoSuchElementException();
    }
      T e = cabeza.elemento;
      if(cabeza.siguiente == null){
        cabeza = rabo = null;
      } else {
          cabeza = cabeza.siguiente;
          cabeza.anterior = null;
        }
        longitud--;
        return e;
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaUltimo() {
      if(cabeza == null) {
      throw new NoSuchElementException();
    }
      T e = rabo.elemento;
      if(cabeza.siguiente == null){
        cabeza = null;
        rabo = null;
      } else {
          rabo = rabo.anterior;
          rabo.siguiente = null;
        }
        longitud--;
        return e;
    }

    /**
     * Busca un nodo en la lista con un elemento especifico.
     *
     * buscaNodo es un método privado para utilizarlo en elimina() y contiene()
     * @Param un elemento genertico dentro de la lsita
     *
     * @return Un nodo con el elemento a buscar.
     */
    private Nodo buscaNodo(T elemento) {
      Nodo n = cabeza;
      if (n == null)
      return null;
      while (n != null){
        if (n.elemento.equals(elemento))
        return n;
        n = n.siguiente;
        }
        return null;
      }

    /**
     * Nos dice si un elemento está en la lista.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <code>true</code> si <code>elemento</code> está en la lista,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean contiene(T elemento) {
      return (buscaNodo(elemento) != null);
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public Lista<T> reversa() {
      Lista<T> lesta = new Lista<T>();
      for (Nodo n = cabeza; n != null; n = n.siguiente) {
        lesta.agregaInicio(n.elemento);
      }
      return lesta;
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public Lista<T> copia() {
      Lista<T> lesta = new Lista<T>();
      for (Nodo n = cabeza; n != null; n = n.siguiente) {
        lesta.agregaFinal(n.elemento);
      }
      return lesta;
    }

    /**
     * Limpia la lista de elementos, dejándola vacía.
     */
    @Override public void limpia() {
      cabeza = rabo = null;
      longitud = 0;
    }

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getPrimero() {
      if(cabeza == null) {
        throw new NoSuchElementException();
      } else {
        return cabeza.elemento;
      }
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getUltimo() {
      if (cabeza == null) {
        throw new NoSuchElementException();
      } else {
        return rabo.elemento;
      }
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista.
     * @throws ExcepcionIndiceInvalido si <em>i</em> es menor que cero o mayor o
     *         igual que el número de elementos en la lista.
     */
    public T get(int i) {
      if (i < 0 || longitud <= i) {
        throw new ExcepcionIndiceInvalido();
    }
      Nodo t = cabeza;
      while (i > 0) {
        t = t.siguiente;
        i--;
      }
     return t.elemento;
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si el elemento
     *         no está contenido en la lista.
     */
    public int indiceDe(T elemento) {
      int i = 0;
      for(Nodo n = cabeza; n != null; n = n.siguiente){
        if(n.elemento.equals(elemento)) {
          return i;
        }
        i++;
      }
      return -1;
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    @Override public String toString() {
      Nodo n = cabeza;
      String s = "[";
      while(n != null) {
        if(n.siguiente != null)
          s += String.format("%s, ", n.elemento);
          n = n.siguiente;
          }
      if(rabo != null) {
        return s += String.format("%s]", rabo.elemento);
      } else
      return s = s + "]";
    }

    /**
     * Nos dice si la lista es igual al objeto recibido.
     * @param objeto el objeto con el que hay que comparar.
     * @return <code>true</code> si la lista es igual al objeto recibido;
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
      if (objeto instanceof Lista) {
        return objeto.toString().equals(this.toString());
      } else {
        return false;
      }
    }

    /**
     * Regresa un iterador para recorrer la lista en una dirección.
     * @return un iterador para recorrer la lista en una dirección.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador();
    }

    /**
     * Regresa un iterador para recorrer la lista en ambas direcciones.
     * @return un iterador para recorrer la lista en ambas direcciones.
     */
    public IteradorLista<T> iteradorLista() {
        return new Iterador();
    }
}
