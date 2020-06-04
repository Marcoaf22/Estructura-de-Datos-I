package P2.Negocio;

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
        } else if (dato <= P.getDato()) {
            if (P.getDato() != dato) {
                Nodo aux = new Nodo(dato);
                aux.setLink(P);
                P = aux;
                Cant++;
            }
        } else {
            P.setLink(InsertarR(P.getLink(), dato));
        }
        return P;
    }

    public void insert(int pri, int seg) {
        if (L == null) {
            Nodo a = new Nodo(pri, seg);
            L = a;
        } else {
            boolean sw = true;
            Nodo ult = L;
            Nodo aux = L;
            while (ult.getLink() != null) {
                ult = ult.getLink();
            }
            if (ult != null) {
                if (pri == L.getDato() || pri == ult.getDato2()) {
                    if (pri == L.getDato()) {
                        Nodo n = new Nodo(seg, pri);
                        n.setLink(L);
                        L = n;
                    } else {
                        Nodo n = new Nodo(pri, seg);
                        ult.setLink(n);
                    }
                    sw = false;
                }
                if (seg == L.getDato() || seg == ult.getDato2()) {
                    if (sw) {
                        if (seg == L.getDato()) {
                            Nodo n = new Nodo(pri, seg);
                            n.setLink(L);
                            L = n;
                        } else {
                            Nodo n = new Nodo(seg, pri);
                            ult.setLink(n);
                        }
                    }
                }
            }

        }
    }

    @Override
    public String toString() {
        return "L->"+toStringR(L);
    }

    public String toStringR(Nodo P) {
        String s = "";
        if (P == null) {
            return " Null";
        } else {
            s = s + " [ " + P.getDato() + " , " + P.getDato2() + " | ]" + " ->";
            s = s + toStringR(P.getLink());
            return s;
        }
    }

    public static void main(String[] args) {
        Lista a = new Lista();
//        for (int i = 1; i <= 10; i++) {
//            a.Insertar(i);
//        }
        a.insert(1, 5);
        a.insert(5, 1);
        a.insert(6, 1);
        System.out.println(a.toString());

        System.out.println(a.toString());
    }
}
