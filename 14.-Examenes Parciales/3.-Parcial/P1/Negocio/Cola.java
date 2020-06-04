package P1.Negocio;

public class Cola {

    int Q[];
    int Frente;
    int Atras;

    public Cola(int Cant) {
        Q = new int[Cant];
        Frente = -1;
        Atras = -1;
    }

    public boolean Vacio() {
        return Frente == -1;
    }

    public int get() {
        return (Q[Frente]);
    }

    public boolean llena() {
        return ((Atras + 1) % Q.length == Frente);
    }

    public int Cantidad() {
        if (!Vacio()) {
            int m = Atras + 1 - Frente;
            if ((Frente >= Atras) && (Frente != 0)) {
                m = Q.length + m;
            }
            return m;
        } else {
            return 0;
        }
    }

    public int CantidadMe() {
        if (Vacio()) {
            return 0;
        } else if (Frente > Atras) {
            return Q.length - (Frente - (Atras + 1));
        } else {
            return (Atras + 1) - Frente;
        }
    }

    public void encolar(int elemento) {
        if (llena()) {
            System.out.println("Error: Cola llena");
            // System.exit(1);
        } else if (Vacio()) {
            Atras = 0;
            Frente = 0;
            Q[Atras] = elemento;
        } else {
            Atras = (Atras + 1) % Q.length;
            Q[Atras] = elemento;
        }
    }

    public int decolar() {
        int x = Q[Frente];
        if (Frente == Atras) {
            Frente = -1;
            Atras = -1;
        } else {
            Frente = (Frente + 1) % Q.length;
        }
        return (x);
    }

    @Override
    public String toString() {
        String S = "Q = ";
        for (int i = 1; i <= CantidadMe(); i++) {
            int x = decolar();
            S = S + x + " ";
            encolar(x);
        }
        return S;
    }

    public void IntercambiarPregunta1() {
        if (!Vacio()) {
            int cb = Cantidad();
            if (Cantidad() % 2 == 0) {
                for (int i = 0; i < cb / 2; i++) {
                    encolar(decolar());
                }
            } else {
                int x = 0;
                for (int i = 1; i <= cb; i++) {
                    if (i == (cb + 1) / 2) {
                        x = decolar();
                    } else {
                        encolar(decolar());
                    }
                }
                encolar(x);
                for (int i = 0; i < cb / 2; i++) {
                    encolar(decolar());
                }
            }
        }
    }

    public static void main(String[] args) {
        Cola a = new Cola(10);
        for (int i = 1; i <= 5; i++) {
            a.encolar(i);
        }
        System.out.println(a.toString());
        a.IntercambiarPregunta1();
        System.out.println(a.toString());

    }
}
