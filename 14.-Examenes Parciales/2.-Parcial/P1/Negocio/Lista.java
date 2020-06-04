package P1.Negocio;

public class Lista {
    
    Nodo L;
    int Cant;

    public Lista() {
        L = null;
        Cant = 0;
    }

    public void Insertar(int ele) {
        L = InsertarR(L, ele);
    }
  
    public Nodo InsertarR(Nodo P, int dato) {
        if (P == null) {
            P = new Nodo(dato);
            Cant++;
        } else {
            if (dato <= P.getDato()) {
                if (P.getDato() != dato) {
                    Nodo aux = new Nodo(dato);
                    aux.setLink(P);
                    P = aux;
                    Cant++;
                }
            } else {
                P.setLink(InsertarR(P.getLink(), dato));
            }
        }
        return P;
    }
    public void Intercala() {
        L = Intercalar(L);
    }

    public Nodo Intercalar(Nodo P) {
        if (P != null) {
            if (P.getLink() != null) {
                if (P.getLink().getLink() != null) {
                    Nodo h = Intercalar(P.getLink().getLink().getLink());
                    Nodo aux = P.getLink();
                    Nodo aux2 = P.getLink().getLink();
                    aux.setLink(h);
                    aux2.setLink(aux);
                    P.setLink(aux2);
                }
            }
        }
        return P;
    }
    @Override
    public String toString(){
        return toStringR(L);
    }
    public String toStringR(Nodo P) {
        String s = "";
        if (P == null) {
            return " Null";
        } else {
            s = s + " [ " + P.getDato() + " | ]" + " ->";
            s = s + toStringR(P.getLink());
            return s;
        }
    }
    public static void main(String[] args) {
        Lista a=new Lista();
        for (int i = 1; i <= 10; i++) {
            a.Insertar(i);
        }
        System.out.println(a.toString());
        a.Intercala();
        System.out.println(a.toString());
    }
}
