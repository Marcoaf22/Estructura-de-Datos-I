package Negocios;

public class PilaLista {
    
    Nodo Tope;
    int Cant;
    
    public PilaLista(){
        Tope=null;
        Cant=0;
    }
    public boolean Vacia(){
        return Tope==null;
    }
    //no tiene Lleno porque es dinamico
    public int get(){
        return Tope.getDato();
    }
    public void Push(int ele){
        Nodo p=new Nodo(ele);
        p.setLink(Tope);
        Tope=p;
        Cant++;
    }
    public int Pop(){
        int x=Tope.getDato();
        Cant--;
        Tope=Tope.getLink();
        return x;
    }
    public String toString(){
        return toStringR(Tope);
    }
    public String toStringR(Nodo P){
        String s="";
        if (P==null){
           return s;
        }else{
            s=s+P.getDato()+"\n";
            s=s+toStringR(P.getLink());
            return s;
        }
    }
    public static void main(String[] args) {
        PilaLista a=new PilaLista();
        a.Push(2);
        a.Push(3);
        System.out.println(a.toString());
        a.get();
        System.out.println(a.get());
        System.out.println(a.toString());
    }
}
