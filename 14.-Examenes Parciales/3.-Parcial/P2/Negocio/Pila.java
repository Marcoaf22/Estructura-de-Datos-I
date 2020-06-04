package P2.Negocio;

import P1.Negocio.Cola;

public class Pila {

    int p[];
    int ultimo;

    public Pila(int cant) {
        p = new int[cant];
        ultimo = -1;
    }

    public boolean Vacio() {
        return ultimo == -1;
    }

    public boolean lleno() {
        return ultimo == p.length - 1;
    }

    public int get() {
        return p[ultimo];
    }

    public int Cantidad() {
        return ultimo + 1;
    }

    public void Push(int ele) {
        if (lleno()) {
            System.out.println("Pila llena");
            System.exit(1);
        } else {
            p[++ultimo] = ele;
        }
    }

    public int Pop() { //asumimos que el usuario lo usara correctamente 
        return p[ultimo--];
    }

    @Override
    public String toString() {
        String s = " Pila\n  |";
        for (int i = ultimo; i >= 0; i--) {
            s = s + p[i] + "|" + "\n  |";
        }
        return s.substring(0, s.length() - 1) + " °";
    }

    public String toString(Pila b) {
        String s = "Pila A   Pila B\n  ";
        int ca = ultimo > b.ultimo ? ultimo : b.ultimo;
        for (int i = ca; i >= 0; i--) {
            if (i <= ultimo) {
                s = s + p[i] + "        ";
            } else {
                s = s + "         ";
            }
            if (i <= b.ultimo) {
                s = s + b.p[i] + "\n  ";
            } else {
                s = s + "\n  ";
            }
        }
        return s;
    }

    public void separaPregunta2(Cola b) {
        while (!Vacio()) {
            b.encolar(Pop());
        }
        for (int i = 0; i < b.CantidadMe(); i++) {
            boolean bandera = true;
            int x = b.decolar();
            int c = b.CantidadMe();
            for (int j = 1; j <= c; j++) {
                if (x != b.get()) {
                    b.encolar(b.decolar());
                } else {
                    bandera = false;
                    Push(b.decolar());
                }
            }
            if (!bandera) {
                Push(x);
            } else {
                b.encolar(x);
            }
        }
    }

    public static void main(String[] args) {
        Pila a = new Pila(15);
        Cola c = new Cola(15);

//        a.Push(1);
//        a.Push(2);
//        a.Push(1);
//        a.Push(3);
//        a.Push(4);
//        c.encolar(5);
//        c.encolar(1);
//        c.encolar(8);
//        c.encolar(10);
//        c.encolar(5);
//        c.encolar(7);
//        c.encolar(3);
        c.encolar(1);
        c.encolar(5);
        c.encolar(6);
        c.encolar(8);
        c.encolar(1);
        c.encolar(6);

        System.out.println(a.toString());
        System.out.println(c.toString());
        a.separaPregunta2(c);
        System.out.println(a.toString());
        System.out.println(c.toString());
    }
}
