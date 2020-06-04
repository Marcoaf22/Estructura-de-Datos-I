/*
Un nodo es una Estructura de datos cuyo elementos fundamentales es estar constituido por
un espacio que representa al "dato" el cual va a contener y la otra parte representara
al "enlace" o referencia de la memoria al cual este hara referencia.

La parte reservada dato puede ser un tipo primitivo (Ej: integer,char,String,float,boolean)
o tambien un tipo definido por el usuario (Ej: TDA,class,proceso,objeto)

para implementar este tipo de dato abstracto lo haremos en java mediante una clase
 */
package Negocios;

public class Nodo {

    Integer Dato; //para hacerlo generico seria "Object Dato;"
    Nodo Link; 

    public Nodo() {
        Link = null;  //Tenemos que inicializar siempre los objetos-
        //Object=null;
    }          //Object 
    public Nodo(int dato){
        Dato=dato;
        Link=null;
    }
    public Nodo(Integer dato, Nodo link) {
        this.Link = link;
        Dato = dato;
    }

    public Integer getDato() {
        return Dato;
    }

    public void setDato(int dato) {
        this.Dato = dato;
    }

    public Nodo getLink() {
        return Link;
    }

    public void setLink(Nodo link) {
        Link = link;
    }

    @Override
    public String toString() {
        String s = "N = ";
        s = s + getDato();
        return s;
    }

    public static void main(String args[]) {
//Enlazar 3 Nodos p,q,r, de forma Ordenada
        Nodo p = new Nodo();
        p.setDato(3);
        Nodo q = new Nodo(5, null);
        q.setDato(1);
        Nodo r = new Nodo();
        r.setDato(2);
        System.out.println(p.toString());
        System.out.println(q.toString());
        System.out.println(r.toString());
        q.setLink(r);
        r.setLink(p);
        System.out.println(q.toString());
    }
}
