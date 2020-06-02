/*
Una lista es una estructura de dato lineal
se puede almacenar un tipo de dato primitivo
una lista es una en la memoria (en la estructura de datos) solo se puede hacer manipulaciones 

Propiedades 
*Lineal
*Dinamica
*Elementos ordenados
*Elementos Unicos

Se pueden guardas objetos pero tiene que tener un criterio de orden y no repetidos
Cada elemento de la lista tiene un elemento llamado nodo el cual es el elemento basico
de una lista

Crear una lista en funcion de un nodo */
package Negocio;

/**
 *
 * @author Marcoaf
 */
public class Lista {

    Nodo L;
    int Cant;

    public Lista() {
        L = null;
        Cant = 0;
    }

    public boolean Vacio() {
        return Cant == 0; // L==null
    }

    public int getCantidad() {
        return Cant;
    }

    public void insertar(final int ele) {
        if (Vacio()) { // si es para el primer elemento en ser insertado
            final Nodo p = new Nodo(ele);
            L = p;
            Cant++;
        } else { // si ya insertamos el primer elemento
            Nodo ant = null;
            Nodo aux = L;// forma correcta de un puntero
            while ((aux != null) && (aux.getDato() <= ele)) {
                ant = aux;
                aux = aux.getLink();
            }
            final Nodo p = new Nodo(ele); // creamos la cajita
            if (ant == null) { // por si acaba en la cabezera
                p.setLink(aux);
                ;
                L = p;
                Cant++;
            } else { // si es para el cuerpo
                if (ant.getDato() != ele) {
                    ant.setLink(p);
                    p.setLink(aux);
                    ;
                    Cant++;
                }
            }
        }
    }

    public int getElemento(final int pos) {
        Nodo aux = L;
        for (int i = 1; i < pos; i++) {
            aux = aux.getLink();
        }
        return aux.getDato();
    }

    public Nodo getNodo(final int pos) {
        Nodo aux = L;
        for (int i = 1; i < pos; i++) {
            aux = aux.getLink();
        }
        return aux;
    }

    public void eliminar(final int ele) {
        if (!Vacio()) {
            Nodo aux = L;
            Nodo ant = null;
            while ((aux != null) && (aux.getDato() != ele)) {
                ant = aux;
                aux = aux.getLink();
            }
            if (ant == null) {
                L = aux.getLink();
                Cant--;
            } else {
                if (aux != null) {
                    ant.setLink(aux.getLink());
                    Cant--;
                }
            }
        }
    }

    public void Eliminar(final int ele) {
        if (!Vacio()) {
            final Nodo aux = Existe(ele);
            if (aux == null) {
                L = L.getLink();
                Cant--;
            } else {
                if (aux.getLink() != null) {
                    aux.setLink(aux.getLink().getLink());
                    Cant--;
                }
            }
        }
    }

    public Nodo Existe(final int ele) {
        Nodo aux = L;
        if (aux.getDato() == ele) {
            return null;
        } else {
            while ((aux.getLink() != null) && (aux.getLink().getDato() != ele)) {
                aux = aux.getLink();
            }
        }
        return aux;
    }

    public int dirrecion(final int ele) {
        Nodo aux = L;
        int i = 1;
        if (Vacio()) {
            return -1;
        } else if (aux.getDato() == ele) {
            return i;
        } else {
            while (aux.getLink() != null) {
                if (aux.getLink().getDato() == ele) {
                    return i + 1;
                }
                aux = aux.getLink();
                i++;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        String s = "L -> ";
        Nodo aux = L;
        while (aux != null) {
            s = s + "[ " + aux.getDato() + " | ]->";
            aux = aux.getLink();
        }
        return s + "null";
    }

    public float Promedio() {
        if (Cant != 0) {
            int suma = 0;
            Nodo aux = L;
            while (aux != null) {
                suma = suma + aux.getDato();
                aux = aux.getLink();
            }
            return (suma / Cant);
        } else {
            return 0;
        }
    }

    public void Invert() { // iterativo
        if (L != null) {// usamos 3 apuntadores para no perder la dirrecion si es null no hace nada
            Nodo ant = null;
            Nodo act = L;
            Nodo post = L.getLink();
            while (post != null) {
                act.setLink(ant);
                ant = act;
                act = post;
                post = post.getLink();
            } // para un elemento tambien sirve
            act.setLink(ant);
            L = act;
        }
    }

    public void Ordena() {
        if (L != null) {
            Nodo act = L;
            Nodo ant = null;
            int i = 1;
            while (i < Cant) {
                while (act.getLink() != null) {
                    if (act.getDato() > act.getLink().getDato()) {
                        if (L == act) {
                            L = L.getLink();
                            act.setLink(L.getLink());
                            L.setLink(act);
                            ant = L;
                        } else {
                            final Nodo m = act.getLink();
                            act.setLink(m.getLink());
                            m.setLink(act);
                            ant.setLink(m);
                            ant = ant.getLink();
                        }
                    } else {
                        ant = act;
                        act = act.getLink();
                    }
                }
                i++;
                ant = null;
                act = L;
            }
        }
    }

    public void InsertarCualquiera(final int ele) {
        if (L == null) {
            final Nodo aux = new Nodo(ele);
            L = aux;
            Cant++;
        } else {
            final Nodo aux = new Nodo(ele);
            aux.setLink(L);
            L = aux;
            Cant++;
        }
    }

    public static void main(final String args[]) {
        final Lista p = new Lista();
        p.insertar(2);
        p.insertar(1);
        p.insertar(5);
        p.eliminar(2);
        System.out.println(p.toString());

    }

}
