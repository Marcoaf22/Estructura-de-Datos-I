/*
una cola es una estructura lineal cuyo comportamiento de operaciones
utiliza la tecnica fifo (que significa el primero en entrar es el primero en salir)
esta estructura de datos puede ser implementadas basados en arrays, basados en listas
o a nivel de bits,su representacion es la siguiente

Q -> |1|2|3|4|5|6| <-entrada
      *frente   *atras

first input first output

Operaciones Basicas
*Encolar (Inserta un elento en el cola por la parte de atras)
*Decolar (Devuelve y elimina un elemento de la cola por adelante)
*Get (devuelve el 1° elemento de la cola(consulta))
*vacia (Devuelve true si la cola esta vacia)
*llena (devuelve true si esta llena)

ejemplos
-Cola de procesos del S.O.
-Cola de Impresiones
-Fichaje de Banco

Representacion.-para representar una cola de forma optima mediante su proceso
lo haremos mediante una bicola circular la cual tiene la siguiente representacion
 */
package Negocios;

/**
 *
 * @author Marcoaf
 */
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

    public int cantidad() {
        if (Vacio()) {
            return 0;
        } else {
            if (Frente > Atras) {
                return Q.length - (Frente - (Atras + 1));
            } else {
                return (Atras + 1) - Frente;
            }
        }
    }

    public void encolar(int elemento) {
        if (llena()) {
            System.out.println("Error: Cola llena");
            // System.exit(1);
        } else {
            if (Vacio()) {
                Atras = 0;
                Frente = 0;
                Q[Atras] = elemento;
            } else {
                Atras = (Atras + 1) % Q.length;
                Q[Atras] = elemento;
            }
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

    //Intercambia el primero con el ultimo  Q = 1 2 3 4 5 6 -> Q = 6 2 3 4 5 1
    public void Intercambiar() {
        int x = decolar();
        for (int i = 1; i < cantidad(); i++) {
            int y = decolar();
            encolar(y);
        }
        encolar(x);
    }

    //Intercambiar mitades
    public void Invertir() {
        for (int i = 1; i <= cantidad() / 2; i++) {
            int x = decolar();
            encolar(x);
        }
    }

//Intercambiar el primero con el ultimo de 2 colas
    public void IntercambiarUP2(Cola a, Cola b) {
        int f2 = a.decolar();
        int f1 = b.decolar();
        a.encolar(f1);
        b.encolar(f2);
        for (int i = 1; i < a.cantidad() - 1; i++) {
            int x = a.decolar();
            a.encolar(x);
        }
        for (int i = 1; i < b.cantidad() - 1; i++) {
            int y = b.decolar();
            b.encolar(y);
        }
        int p1 = b.decolar();
        int p2 = a.decolar();
        a.encolar(p1);
        b.encolar(p2);
        a.Intercambiar();
        b.Intercambiar();
    }

    //guia ejercicio de arriba   
    public void invExtremosDosC(Cola p, Cola q) {
        int y = p.decolar(); // p=2/3/4/5  y=1
        int x = q.decolar(); //q= 7/8/9/10   x=6
        p.encolar(x);   //p=2/3/4/5/6
        for (int i = 2; i < p.cantidad(); i++) {
            int z = p.decolar();
            p.encolar(z);
        } //p=5/6/2/3/4 supuestamente
        for (int j = 1; j < q.cantidad(); j++) {
            int z = q.decolar();
            q.encolar(z);
        }//q=10/7/8/9
        int o = p.decolar(); //p=6/2/3/4/   o=5
        int x1 = q.decolar(); //q=7/8/9   x1=10
        p.encolar(x1); // p=6/2/3/4/10
        p.Intercambiar(); //p=10/2/3/4/6
        q.encolar(o); // q=7/8/9/5
        for (int j = 1; j < q.cantidad(); j++) {
            int y1 = q.decolar();
            q.encolar(y1);
        }//q=5/7/8/9
        q.encolar(y);//q=5/7/8/9/1
    }

    //Invertir una cola recursivo
    public void InvertirR() {
        if (Vacio()) {
            //nada
        } else {
            int x = decolar();
            InvertirR();
            encolar(x);
        }
    }

    //invertir iterativo
    public void invertir1() {
        Cola P = new Cola(cantidad());
        int x = 1;
        P.encolar(decolar());
        while (!Vacio()) {
            P.encolar(decolar());
            for (int i = 1; i <= x; i++) {
                P.encolar(P.decolar());
            }
            x++;
        }
        while (!P.Vacio()) {
            encolar(P.decolar());
        }
    }

    @Override
    public String toString() {
        String S = "Q = ";
        for (int i = 1; i <= cantidad(); i++) {
            int x = decolar();
            S = S + x + " ";
            encolar(x);
        }
        return S;
    }

    //un intercambiar primero y ultimo a medias
    public void intercambiarCruzado(Cola Q, Cola Q1) {
        int x = Q.decolar();
        int y = Q1.decolar();
        for (int i = 1; i < Q.cantidad(); i++) {
            int z = Q.decolar();
            Q.encolar(z);
        }
        Q1.encolar(x);
        for (int i = 1; i < Q1.cantidad(); i++) {
            int c = Q1.decolar();
            Q1.encolar(c);
        }
        Q.encolar(y);
    }

    //invertir 2 a la vez colas
    public void Invertir2alavez(Cola Q, Cola Q1) {
        int x = Q.decolar();
        int y = Q1.decolar();
        for (int i = 1; i < Q.cantidad(); i++) {
            int z = Q.decolar();
            Q.encolar(z);
        }
        for (int i = 1; i < Q1.cantidad() - 1; i++) {
            int c = Q1.decolar();
            Q1.encolar(c);
        }
        for (int i = 1; i < Q.cantidad(); i++) {
            int z = Q.decolar();
            Q.encolar(z);
        }
        Q.encolar(x);
        Q1.encolar(y);
    }

    public static void main(String[] args) {
        Cola a = new Cola(10);
        Cola b = new Cola(10);
        Cola c = new Cola(10);
        for (int i = 1; i <= 5; i++) {
            a.encolar(i);
            b.encolar(i + 5);
        }
        System.out.println(a.toString());
        System.out.println(b.toString());

        a.invertir1();
        System.out.println(a.toString());
        System.out.println(b.toString());

    }
}
