package Negocios;

public class VectorNbits {

    int v[];
    int Cant;
    int Nbits;
//elemento maximo = Math.pow(2,Nbits)-1

    public VectorNbits(int cant, int nbit) {
        Cant = cant;
        int Nbit = cant * nbit;
        int NE = (Nbit % 32 == 0) ? Nbit / 32 : (Nbit / 32) + 1;
        v = new int[NE];
        Nbits = nbit;
    }

    public void redimensionar() {
        int cv[] = new int[v.length];
        System.arraycopy(v, 0, cv, 0, v.length);
        Cant = Cant + 5;
        int Nbit = Cant * Nbits;
        int NE = (Nbit % 32 == 0) ? Nbit / 32 : (Nbit / 32) + 1;
        v = new int[NE];
        System.arraycopy(cv, 0, v, 0, cv.length);
    }

    public void Insertar(int pos, int elemento) {
        if (pos <= Cant && elemento < Math.pow(2, Nbits)) {
            int elemento1 = elemento;
            int mask = (int) (Math.pow(2, Nbits) - 1);
            int Nbit = Fnbit(pos);
            int Nent = Fnint(pos);
            mask = mask << Nbit;
            mask = ~mask;
            v[Nent] = v[Nent] & mask;
            elemento = elemento << Nbit;
            v[Nent] = v[Nent] | elemento;
            if (Nbit + Nbits > 32) {
                int Nudbitfal = Nbit + Nbits - 32;
                int mask1 = (int) Math.pow(2, Nbits) - 1;
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

    public int get(int pos) {
        if (pos <= Cant) {
            int mask = (int) (Math.pow(2, Nbits) - 1);//Casteaba primero luego restaba por eso error
            int Nbit = Fnbit(pos);
            int Nent = Fnint(pos);
            mask = mask << Nbit;
            mask = mask & v[Nent];
            mask = mask >>> Nbit;
            if (Nbit + Nbits > 32) {
                int Nbf = Nbit + Nbits - 32;
                int mask1 = (int) Math.pow(2, Nbits) - 1;
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
            int t = Integer.toBinaryString(get(i)).length();
            String n = Integer.toBinaryString(get(i));
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
                if (Fnbit(p) == j) {
//               if (p>Cant){
//                  return s=s+getBit(j,i)+"]"; }              
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
        VectorNbits a = new VectorNbits(10, 5);
        a.Insertar(1, 3);
        a.Insertar(3, 5);
        a.Insertar(7, 10);
        a.Insertar(8, 4);
        System.out.println(a.toStringBit());
        System.out.println(a.toString());
        System.out.println(a.toStringP());
        int x = Integer.parseUnsignedInt("4294967295");
        long z = 4294967295l;
        System.out.println(x);
        System.out.println(z);
//    System.out.println(Long.toString((long)Math.pow(2,32)-1));
//    System.out.println(a.toString());
//    System.out.println(a.toStringBit());
//    int t=2147483647;
//    String w=Integer.toBinaryString(t)+"1";
//    System.out.println(w);
//    System.out.println(Integer.parseInt("2147483647", 10));
    }
}
