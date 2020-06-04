package Negocio;

public class VectorNbitsL {

    long v[];
    int Cant;
    int Nbits;
//elemento maximo = Math.pow(2,Nbits)-1

    public VectorNbitsL(int cant, int nbit) {
        Cant = cant;
        int Nbit = cant * nbit;
        int NE = (Nbit % 64 == 0) ? Nbit / 64 : (Nbit / 64) + 1;
        v = new long[NE];
        Nbits = nbit;
    }

    public void redimensionar(int cant) {
        long cv[] = new long[v.length];
        System.arraycopy(v, 0, cv, 0, v.length);
        Cant = cant;
        int Nbit = Cant * Nbits;
        int NE = (Nbit % 32 == 0) ? Nbit / 32 : (Nbit / 32) + 1;
        v = new long[NE];
        System.arraycopy(cv, 0, v, 0, cv.length);
    }

    public void Insertar(int pos, long elemento) {
        if (pos <= Cant && elemento < Math.pow(2, Nbits)) {
            long elemento1 = elemento;
            long mask = (long) (Math.pow(2, Nbits) - 1);
            int Nbit = Fnbit(pos);
            int Nent = Fnint(pos);
            mask = mask << Nbit;
            mask = ~mask;
            v[Nent] = v[Nent] & mask;
            elemento = elemento << Nbit;
            v[Nent] = v[Nent] | elemento;
            if (Nbit + Nbits > 64) {
                int Nudbitfal = Nbit + Nbits - 64;
                long mask1 = (long) Math.pow(2, Nbits) - 1;
                mask1 = mask1 >>> (Nbits - Nudbitfal);
                mask1 = ~mask1;
                v[Nent + 1] = v[Nent + 1] & mask1;
                elemento1 = elemento1 >>> (Nbits - Nudbitfal);
                v[Nent + 1] = v[Nent + 1] | elemento1;
            }
        }
    }

    public int Nent() {
        return v.length;
    }

    public int getCantidad() {
        return Cant;
    }

    public long get(int pos) {
        if (pos <= Cant) {
            long mask = (long) Math.pow(2, Nbits) - 1;//(Math.pow(2,Nbits)-1);//Casteaba primero luego restaba por eso error
            int Nbit = Fnbit(pos);                  //Eso creo pero a partir del 55 no da
            int Nent = Fnint(pos);
            mask = mask << Nbit;
            mask = mask & v[Nent];
            mask = mask >>> Nbit;
            if (Nbit + Nbits > 64) {
                int Nbf = Nbit + Nbits - 64;
//        long mask3=(long)Math.pow(2,Nbits-Nbf)-1;//Son para evitar errores  
//        mask=mask & mask3;                       //por usar long. 
                long mask1 = (long) Math.pow(2, Nbits) - 1;
                mask1 = mask1 >>> (Nbits - Nbf);
                mask1 = mask1 & v[Nent + 1];
                mask1 = mask1 << (Nbits - Nbf);
                mask = mask | mask1;
            }
            return (mask);
        }
        return -1;
    }

    public int Fnbit(int pos) {
        return (((pos - 1) * Nbits) % 64);
    }

    public int Fnint(int pos) {
        return (((pos - 1) * Nbits) / 64);
    }

    public String toString() {
        String s = "";
        for (int i = 1; i <= Cant; i++) {
            s = s + get(i) + ",";
        }
        s = s.length() > 0 ? "[" + s.substring(0, s.length() - 1) + "]" : "[]";
        return s;
    }

    public long getBit(int pos, int ent) {//retorna si el bit en la pos es 0,1
        if (pos <= 64) {
            long mascara = 1;
            mascara = mascara << pos - 1;  //llevamos ala posicion deseada la mascara
            mascara = mascara & v[ent];// y aplicamos un and
            mascara = mascara >>> pos - 1; //despues de aplicar el and sabremos si el bit en la pos es 0,1 lo llevamos de nuevo al principio
            return mascara;
        }
        return 0;
    }

    public String toStringP() {
        String s = "[";
        for (int i = 1; i <= Cant; i++) {
            long t = Long.toBinaryString(get(i)).length();
            String n = Long.toBinaryString(get(i));
            while (t < Nbits) {
                n = "0" + n;
                t++;
            }
            s = s + n + ",";
        }
        return s;
    }

    public String toStringBit() {
        String s = "[";
        int p = 2;
        for (int i = 0; i < v.length; i++) {
            for (int j = 1; j <= 64; j++) {
                if (Fnbit(p) == j && Fnint(p) == i) {
                    if (p > Cant) {
                        return s = s + getBit(j, i) + "]";
                    }
                    s = s + getBit(j, i) + ",";
                    p++;
                    p = Fnbit(p) == 0 ? p + 1 : p;
                } else {
                    s = s + getBit(j, i);
                }
            }
            s = s + "][";
        }
        s = s.substring(0, s.length() - 1);
        return s;
    }

    public static void main(String args[]) {
        VectorNbitsL a = new VectorNbitsL(10, 25);
        a.Insertar(1, 31);
        a.Insertar(3, 5);
        a.Insertar(7, 10);
        a.Insertar(8, 4);
        System.out.println(a.toStringBit());
        System.out.println(a.toString());
//    System.out.println((long)Math.pow(2,55));
//    System.out.println((long)(Math.pow(2,54)-1));
//    System.out.println((long)Math.pow(2,54)-1);
//    System.out.println((int)(Math.pow(2, 30)-1));
//    System.out.println((int)Math.pow(2, 30)-1);    

//    System.out.println(Long.toString((long)Math.pow(2,32)-1));
//    System.out.println(a.toString());
//    System.out.println(a.toStringBit());
//    int t=2147483647;
//    String w=Integer.toBinaryString(t)+"1";
//    System.out.println(w);
//    System.out.println(Integer.parseInt("2147483647", 10));
    }
}
