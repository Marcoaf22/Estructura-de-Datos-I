
package P2.Negocio;
public class Nodo {

    int Dato; //para hacerlo generico seria "Object Dato;"
    int Dato1;
    Nodo Link; 

    public Nodo() {
        Link = null;  //Tenemos que inicializar siempre los objetos-
        //Object=null;
    }          //Object 
    public Nodo(int dato){
        Dato=dato;
        Link=null;
    }
    public Nodo(int dato,int dato2) {
        Link = null;
        Dato = dato;
        Dato1 = dato2;
    }

    public Integer getDato() {
        return Dato;
    }
    public Integer getDato2() {
        return Dato1;
    }
    public void setDato(int dato) {
        this.Dato = dato;
    }
    public void setDato2(int dato) {
        this.Dato1 = dato;
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

    }
}
