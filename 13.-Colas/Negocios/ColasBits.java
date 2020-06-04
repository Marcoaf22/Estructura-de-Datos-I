package Negocios;

public class ColasBits {

    VectorNbits Q;
    int Frente;
    int Atras;

    public ColasBits(int cant, int bits) {
        Q = new VectorNbits(cant, bits);
        Frente = 0;
        Atras = 0;
    }

    public boolean Vacio() {
        return Frente == 0;
    }

    public int Cantidad() {
        if (Vacio()) {
            return 0;
        } else {
            if (Frente > Atras) {
                return Q.Cant - (Frente - (Atras + 1));
            } else {
                return (Atras - Frente)+1;
            }
        }
    }

    public void encolar(int ele) {
        if (lleno()) {
            System.out.println("Error: Cola Llena");
           // System.exit(1);
        } else {
            if (Vacio()) {
                Atras = 1;
                Frente = 1;
                Q.Insertar(Atras, ele);
            } else {
//                if (Atras+1==Q.Cant){
//                    Atras=1;
//                }else{
                Atras=(Atras % Q.Cant)+1;
            //}
              //  Atras = Atras==Q.Cant?1:(Atras+1)&Q.Cant;
               // Atras = (Atras+1) % Q.Cant;
                Q.Insertar(Atras, ele);
            }
        }
    }

    public int decolar() {
        int x = Q.get(Frente);
        if (Frente == Atras) {
            Frente = 0;
            Atras = 0;
        } else {
            Frente = (Frente%Q.Cant)+1;
            //Frente = (Frente + 1) % Q.Cant;
        }
        return x;
    }

    public int get() {
        return Q.get(Frente);
    }

    public boolean lleno() {
        return ((Atras + 1) % Q.Cant == Frente);
    }

    public String toString() {
        String s="Q = ";
        for (int i = 1; i <= Cantidad(); i++) {
            int x=decolar();
            s= s + x +" ";
            encolar(x);
        }
        return s;
    }

    public static void main(String[] args) {
        ColasBits a= new ColasBits(10,6);
        for (int i = 1; i <= 10; i++) {
            a.encolar(i);
        }
        for (int i = 0; i < 5; i++) {
            a.decolar();
        }
        for (int i = 1; i < 4; i++) {
            a.encolar(i);
        }
        System.out.println(a.toString());
        System.out.println(a.Cantidad());
    }
}
