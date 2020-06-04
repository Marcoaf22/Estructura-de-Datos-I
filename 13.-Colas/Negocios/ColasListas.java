package Negocios;

public class ColasListas {

    Nodo Frente;
    Nodo Atras;
    int Cant;

    public ColasListas() {
        Frente = null;
        Atras = null;
        Cant = 0;
    }

    public boolean Vacio() {
        return Frente == null;
    }

    public int get() {
        return Frente.getDato();
    }

    public void Encolar(int ele) {//cada nuevo elemento se inserta apuntando a la derecha
        if (Vacio()) {
            Nodo p = new Nodo(ele);
            Frente = p;
            Atras = p;
        } else {
            Nodo p = new Nodo(ele);
            Atras.setLink(p);
            Atras = p;
        }
        Cant++;
    }

    public void add(int ele) {//cada nuevo elemento se inserta apuntando a la derecha
        addR(Frente, ele);
    }

    private void addR(Nodo p, int ele) {
        if (p == null) {
            Nodo q = new Nodo(ele);
            Frente = q;
            Atras = q;
        } else {
            if (Frente.Dato != ele) {
                if (p.getLink() != null && p.getLink().getDato() == ele) {
                    int x = p.getLink().getDato();
                    p.getLink().setDato(p.getDato());
                    p.setDato(x);
                } else if (p.getLink() == null) {
                    Nodo q=new Nodo(ele);
                    p.setLink(q);
                    Atras=q;
                } else {
                    addR(p.getLink(), ele);
                }
            }
        }
    }

    public int Decolar() {
        int x = Frente.getDato();
        if (Frente == Atras) {
            Frente = null;
            Atras = null;
            Cant = 0;
        } else {
            Frente = Frente.getLink();
            Cant--;
        }
        return x;
    }
    

    @Override
    public String toString(){
        String s="";
        Nodo p=Frente;
        while (p!=null){
            s=s+"["+p.Dato+"]->";
            p=p.getLink();
        }
        return s;
    }
    
    public static void main(String[] args) {
        ColasListas a=new ColasListas();
        a.add(9);
        a.add(8);
        a.add(4);
        System.out.println(a.toString());
        a.add(4);
        System.out.println(a.toString());
        a.add(9);
        System.out.println(a.toString());
        a.add(6);
        System.out.println(a.toString());        
        a.add(8);
        System.out.println(a.toString());
    }

}
