package P2;

public class VectorNbitsM {

    int v[];
    int Cant;
    int Nbits;
    int dim;
//elemento maximo = Math.pow(2,Nbits)-1

    public VectorNbitsM(int cant, int nbit) {
        Cant = cant;
        int Nbit = cant * nbit;
        int NE = (Nbit % 32 == 0) ? Nbit / 32 : (Nbit / 32) + 1;
        v = new int[NE];
        Nbits = nbit;
        dim = 0;
    }

    public void redimensionar(int cant) {
        int cv[] = new int[v.length];
        System.arraycopy(v, 0, cv, 0, v.length);
        Cant = cant;
        int Nbit = Cant * Nbits;
        int NE = (Nbit % 32 == 0) ? Nbit / 32 : (Nbit / 32) + 1;
        v = new int[NE];
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
            v[Nent] = v[Nent] & (int) mask;
            elemento = elemento << Nbit;
            v[Nent] = v[Nent] | (int) elemento;
            if (Nbit + Nbits > 32) {
                int Nudbitfal = Nbit + Nbits - 32;
                long mask1 = (long) Math.pow(2, Nbits) - 1;
                mask1 = mask1 >>> (Nbits - Nudbitfal);
                mask1 = ~mask1;
                v[Nent + 1] = v[Nent + 1] & (int) mask1;
                elemento1 = elemento1 >>> (Nbits - Nudbitfal);
                v[Nent + 1] = v[Nent + 1] | (int) elemento1;
            }
        }
    }

    public int Nent() {
        return v.length;
    }

    public long get(int pos) {
        if (pos <= Cant) {
            long mask = (long) (Math.pow(2, Nbits) - 1);//Casteaba primero luego restaba por eso error
            int Nbit = Fnbit(pos);
            int Nent = Fnint(pos);
            mask = mask << Nbit;
            mask = mask & v[Nent];
            mask = mask >>> Nbit;
            if (Nbit + Nbits > 32) {
                int Nbf = Nbit + Nbits - 32;
                long mask3 = (long) Math.pow(2, Nbits - Nbf) - 1;//son errores por 
                mask = mask & mask3;                       //usar el long uff
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
        return (((pos - 1) * Nbits) % 32);
    }

    public int Fnint(int pos) {
        return (((pos - 1) * Nbits) / 32);
    }

    public String toString() {
        String s = "";
        for (int i = 1; i <= Cant; i++) {
            s = s + get(i) + ",";
        }
        s = s.length() > 0 ? "[" + s.substring(0, s.length() - 1) + "]" : "[]";
        return s;

    }

    public int getBit(int pos, int ent) {//retorna si el bit en la pos es 0,1
        if (pos <= 32) {
            int mascara = 1;
            mascara = mascara << pos - 1;//llevamos ala posicion deseada la mascara
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
            for (int j = 1; j <= 32; j++) {
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
        VectorNbitsM a = new VectorNbitsM(10, 5);
        System.out.println((long) (Math.pow(2, 33) - 1));
        a.Insertar(1, 33);
        a.Insertar(2, 31);
        a.Insertar(7, 10);
        a.Insertar(8, 4);
        System.out.println(a.toStringBit());
        System.out.println(a.toString());
        a.redimensionar(11);
    }
}
