 
package P1.Negocio;

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
