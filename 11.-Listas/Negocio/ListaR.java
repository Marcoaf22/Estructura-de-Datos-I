/*Metodos Recursivos.- para implementar un metodo recursivo en una estructura de datos deberemos utilizar una plantilla
que sera basado en 2 metodos uno publico y otro privado debido a los parametros que es necesario
para realizar la recursion*/
package Negocio;

/**
 *
 * @author Marcoaf
 */
public class ListaR {

    Nodo L;
    int Cant;

    public ListaR() {
        L = null;
        Cant = 0;
    }

    public boolean vacion() {
        return L == null;
    }

    public boolean existe(int ele) {
        if (!vacion()) {
            if (L.getDato() == ele) {
                return true;
            } else {
                Nodo aux = L;
                while (aux.getLink() != null) {
                    if (aux.getDato() == ele) {
                        return true;
                    }
                    aux = aux.getLink();
                }
            }
        }
        return false;
    }

    public void insertar(int ele) {
        L = insertarRV(L, ele);
    }

    private Nodo insertarRV(Nodo P, int dato) {
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
                P.setLink(insertarRV(P.getLink(), dato));
            }
        }
        return P;
    }

    public void invertir() {
        invertirRV(L);
    }

    private void invertirRV(Nodo P) {
        if (P == null) {
            //Nada
        } else {
            if (P.getLink() == null) {
                L = P;
            } else {
                invertirRV(P.getLink());  //  1  3 <- 5 <- 7
                P.getLink().setLink(P);  //  1<-3 <- 5 <- 7
                P.setLink(null);
            }
        }
    }

    //elimina un elemento , como proceso
    public void eliminar(int ele) {
        eliminarRV(L, ele);
    }

    private void eliminarRV(Nodo P, int ele) {
        if (P == null || (P.getDato() != ele && P.getLink() == null)) {
            //Nada
        } else if ((L == P) && (P.getDato() == ele)) {
            L = P.getLink();
            Cant--;
        } else {
            if (P.getLink().getDato() == ele) {
                P.setLink(P.getLink().getLink());
                Cant--;
            } else {
                eliminarRV(P.getLink(), ele);
            }
        }
    }

    //Elimina un elemento , como funcion
    public void eliminarF(int ele) {
        L = eliminarFRV(L, ele);
    }

    private Nodo eliminarFRV(Nodo P, int dato) {
        if (P == null || (P.getDato() != dato && P.getLink() == null)) {
            return P;
        } else {
            if (P.getDato() == dato) {
                P = P.getLink();
                Cant--;
            } else {
                P.setLink(eliminarFRV(P.getLink(), dato));
            }
        }
        return P;
    }

    //Inserta cualquier elemento sin restriccion, L -> al nuevo elemento
    public void InsertarCualquiera(int ele) {
        if (L == null) {
            Nodo aux = new Nodo(ele);
            L = aux;
            Cant++;
        } else {
            Nodo aux = new Nodo(ele);
            aux.setLink(L);
            L = aux;
            Cant++;
        }
    }

    // L->1 ->2 ->3 ->4 ->5 ->6 ->7 ->8 -> Null 
    // L->3 ->2 ->1 ->6 ->5 ->4 ->7 ->8 -> Null  Sobrante al final
    public void intercalarDe3() {
        L = intercalarDe3RV(L);
    }

    private Nodo intercalarDe3RV(Nodo P) {
        if (P != null) {
            if (P.getLink() != null) {
                if (P.getLink().getLink() != null) {
                    Nodo h = intercalarDe3RV(P.getLink().getLink().getLink());
                    Nodo aux = P.getLink();
                    P.setLink(h);
                    aux.getLink().setLink(P);
                    P = aux.getLink();
                    aux.setLink((P.getLink()));
                    P.setLink(aux);
                }
            }
        }
        return P;
    }

    // L ->1 ->2 ->3 ->4 ->5 ->6 ->7 -> Null 
    // L ->1 ->4 ->3 ->2 ->7 ->6 ->5 -> Null   Sobrante al principio
    public void intercalarDe3P() {
        switch (Cant % 3) {
            case 0:
                L = intercalarDe3RV(L);
                break;
            case 1:
                L.setLink(intercalarDe3RV(L.getLink()));
                break;
            case 2:
                L.getLink().setLink(intercalarDe3RV(L.getLink().getLink()));
                break;
        }
    }

    // L ->1 ->2 ->3 ->4 ->5 ->6 ->7 -> Null : Cant: 7
    // L ->2 ->1 ->4 ->3 ->6 ->5 ->7 -> Null : Cant: 7  Sobrante al final
    public void intercalarDe2() {
        L = intercalarDe2RV(L);
    }

    private Nodo intercalarDe2RV(Nodo P) {
        if (P != null) {
            if (P.getLink() != null) {
                Nodo h = intercalarDe2RV(P.getLink().getLink());
                Nodo aux = P.getLink();
                P.setLink(h);
                aux.setLink(P);
                P = aux;
            }
        }
        return P;
    }

    // L->1 ->2 ->3 ->4 ->5 ->6 ->7 -> Null 
    // L->1 ->3 ->2 ->5 ->4 ->7 ->6 -> Null   Sobrante al principio
    public void intercalarDe2P() {
        if (Cant % 2 == 0) {
            L = intercalarDe2RV(L);
        } else {
            L.setLink(intercalarDe2RV(L.getLink()));
        }
    }

    // L ->1 ->2 ->3 ->4 ->5 ->6 ->7 ->8 -> Null 
    // L ->2 ->3 ->1 ->5 ->6 ->4 ->7 ->8 -> Null 
    public void moverDe2() {
        L = moverDe2RV(L);
    }

    private Nodo moverDe2RV(Nodo P) {
        if (P != null) {
            if (P.getLink() != null) {
                if (P.getLink().getLink() != null) {
                    Nodo h = moverDe2RV(P.getLink().getLink().getLink());
                    Nodo aux = P.getLink();
                    P.setLink(h);
                    aux.getLink().setLink(P);
                    P = aux;
                }
            }
        }
        return P;
    }

    //elimina repetidos solo si la lista permite repetidos pero "ordenados", como "Proceso"
    public void eliminarRepetidos() {
        eliminarRepetidosRV(L);
    }

    private void eliminarRepetidosRV(Nodo P) {
        if (P != null && P.getLink() != null) {
            eliminarRepetidosRV(P.getLink());
            if (P.getDato() == P.getLink().getDato()) {
                P.setLink(P.getLink().getLink());
                Cant--;
            }
        }
    }

    //elimina repetidos solo si la lista permite repetidos pero "ordenados", como "Funcion"
    public void eliminarRepetidos2R() {
        L = eliminarRepetidos2RV(L);
    }

    private Nodo eliminarRepetidos2RV(Nodo P) {
        if (P != null) {
            if (P.getLink() != null) {
                P.setLink(eliminarRepetidos2RV(P.getLink()));
                if (P.getDato() == P.getLink().getDato()) {
                    P = P.getLink();//o P.setlink(P.getlink.getlink);
                    Cant--;
                }
            }
        }
        return P;
    }

    public float promedio() {
        return promedioRV(L) / Cant;
    }

    private float promedioRV(Nodo P) {
        if (P == null) {
            return 0;
        } else {
            float s = promedioRV(P.getLink());
            s = s + P.getDato();
            return s;
        }
    }

    //Ordena una lista
    public void ordenar() {
        L = ordenarRV(L);
    }

    private Nodo ordenarRV(Nodo P) {
        if (P != null) {
            P = OrdenarRBP(P);//yo me encargo del primero, usa RBP o RBF 
            P.setLink(ordenarRV(P.getLink())); //ya hay un proceso que te ordena todo menos el primero
        }
        return P;
    }

    // L -> 3 -> 9 -> 5 -> 7 -> 1 -> 6 -> 4 -> Null  Lleva ya sea el mayor o menor ,pero "Al Final"
    // L -> 3 -> 5 -> 7 -> 1 -> 6 -> 4 -> 9 -> Null 
    private Nodo OrdenarRBF(Nodo P) {
        if (P != null) {
            if (P.getLink() != null) {
                if (P.getDato() > P.getLink().getDato()) {
                    Nodo a = P.getLink();
                    P.setLink(a.getLink());
                    a.setLink(P);
                    P = a;
                }
                P.setLink(OrdenarRBF(P.getLink()));  //<-------
            }
        }
        return P;
    }

    // L ->2 ->5 ->1 ->6 ->0 ->4 -> Null 
    // L ->0 ->2 ->5 ->1 ->6 ->4 -> Null Lleva ya sea "el mayor o menor" al "Principio"
    private Nodo OrdenarRBP(Nodo P) {
        if (P != null) {
            if (P.getLink() != null) {
                P.setLink(OrdenarRBP(P.getLink()));   //<---------
                if (P.getDato() > P.getLink().getDato()) {
                    Nodo a = P.getLink();
                    P.setLink(a.getLink());
                    a.setLink(P);
                    P = a;
                }
            }
        }
        return P;
    }

    @Override
    public String toString() {
        return "L ->" + (toStringR(L)) + " : Cant: " + Cant;
    }

    private String toStringR(Nodo P) {
        String s = "";
        if (P == null) {
            return "Null";
        } else {
            s = s + "[ " + P.getDato() + " |]" + "-> ";
            s = s + toStringR(P.getLink());
            return s;
        }
    }

    public void insertarIP(int x) {
        L = insertarIP(L, x, (x % 2) == 1);
    }

    private Nodo insertarIP(Nodo p, int dato, boolean esImpar) {
        if (p == null) {
            p = new Nodo(dato);
            Cant++;
        } else {
            if (esImpar) {
                if ((p.getDato() % 2) == 1) {
                    if (dato <= p.getDato()) {
                        Nodo aux = new Nodo(dato);
                        aux.setLink(p);
                        p = aux;
                        Cant++;
                    } else {
                        p.setLink(insertarIP(p.getLink(), dato, esImpar));
                    }
                } else {
                    Nodo aux = new Nodo(dato);
                    aux.setLink(p);
                    p = aux;
                    Cant++;
                }
            } else {
                if ((p.getDato() % 2) == 0) {
                    if (dato <= p.getDato()) {
                        Nodo aux = new Nodo(dato);
                        aux.setLink(p);
                        p = aux;
                        Cant++;
                    } else {
                        p.setLink(insertarIP(p.getLink(), dato, esImpar));
                    }
                } else {
                    p.setLink(insertarIP(p.getLink(), dato, esImpar));
                }
            }
        }
        return p;
    }

    public void insertarP(int ele) {
        insertarPR(L, ele);
    }

    private void insertarPR(Nodo P, int dato) {
        if (P == null) {
            L = new Nodo(dato);
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
                if (P.getLink() == null) {
                    P.setLink(new Nodo(dato));
                    Cant++;
                } else {
                    insertarPR(P.getLink(), dato);
                }
            }
        }
    }

    public static void main(String[] args) {
        ListaR a = new ListaR();

        for (int i = 1; i <= 10; i++) {
            a.insertar(i);
        }

        System.out.println("Lista Original\n" + a.toString() + "\n--Procesos Recursivos--");
        a.intercalarDe3();
        System.out.println("Intercalar de 3\n" + a.toString());
        a.intercalarDe3();
        a.intercalarDe2();
        System.out.println("Intercalar de 2\n" + a.toString());
        a.intercalarDe2();
        a.moverDe2();
        System.out.println("Mover\n" + a.toString());
        a.moverDe2();
        a.moverDe2();
        System.out.println("Lista Original\n" + a.toString());
        a.invertir();
        System.out.println("Invertir\n" + a.toString());
        a.invertir();
        System.out.println("Lista Original\n" + a.toString());
    }

}
