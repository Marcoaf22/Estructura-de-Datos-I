/*VectorNbits Mejorado*/
package Presentacion;

public class VectorNbitsZ {

    long v[];
    int cant;
    int NroDeBitsTotal;
    byte[] lugarDeBits;

    /**
     * Inicializa el Vector
     *
     * @param cant Numero de elementos que tendra el vector
     * @param nbits Numero de bits que ocupara cada elemento del vector
     */
    public VectorNbitsZ(int cant, int nbits) {
        this(cant, new byte[]{(byte) nbits});
    }

    /**
     * Inicializa el Vector
     *
     * @param cant Numero de elementos que tendra el vector
     * @param bits Vector de byte indicando cada elemento como y en cuantos
     * lugares estara dividido Ej: VectorNbitsZPro(10,[4,8,3]).- tendremos 10
     * elementos de 15 bits cada uno que a su vez estara dividido en Lugares
     * 1°lugar=4bits ,2°lugar=8bits ,3°lugar=3bits Cada lugar empieza desde 0
     */
    public VectorNbitsZ(int cant, byte[] bits) {
        this.cant = cant;
        this.lugarDeBits = bits;
        NroDeBitsTotal = 0;
        for (int i = 0; i < bits.length; i++) {
            NroDeBitsTotal += bits[i];
            System.out.print(bits[i] + ", ");
        }
        int Nb = this.cant * NroDeBitsTotal;
        v = new long[Nb % 64 == 0 ? Nb / 64 : Nb / 64 + 1];
        System.out.print("= " + NroDeBitsTotal + "\n");
    }

    /**
     * Inicializa el Vector
     *
     * @param cant Numero de elementos que tendra el vector
     * @param bits Vector de int indicando el elemento maximo que requeriere
     * para cada lugar solo que lo calculara los bits requeridos para
     * representarlos Ej: VectorNbitsZPro(10,[59,100,1]).-tendremos 10 elementos
     * de 14 bits cada uno. <br/>
     * 1°lugar.-6 bits, ya que 59 solo requiere 6 bits para representarlo
     * 2°lugar.-7 bits, ya que 100 solo requiere 7 bits para representarlo
     * 3°lugar.-1 bits, ya que 1 solo requiere 1 bits para representarlo Cada
     * lugar empieza desde 0
     */
    public VectorNbitsZ(int cant, int[] bits) {
        this.cant = cant;
        NroDeBitsTotal = 0;
        lugarDeBits = new byte[bits.length];
        for (int i = 0; i < bits.length; i++) {
            lugarDeBits[i] = (byte) calcularBits(bits[i]);
            NroDeBitsTotal += lugarDeBits[i];
        }
        int Nb = this.cant * NroDeBitsTotal;
        v = new long[Nb % 64 == 0 ? Nb / 64 : Nb / 64 + 1];
    }

    /**
     * Calcula los bits requeridos para representar un numero positivo
     *
     * @param e numero a calcular
     * @return total de bits requeridos
     */
    private int calcularBits(int e) {
        int i = 1;
        while (Math.pow(2, i) - 1 < e) {
            i++;
        }
        return i;
    }

    /**
     * Devuelve la posicion de bit que tiene que ir un lugar
     *
     * @param lugar
     * @return
     */
    private int pos(int lugar) {
        int p = 0;
        for (int i = lugarDeBits.length - 1; i >= lugar; i--) {
            p += lugarDeBits[i];
        }
        return p;
    }

    public void redimensionar(int cant) {
        long cv[] = new long[v.length];
        System.arraycopy(v, 0, cv, 0, v.length);
        this.cant = cant;
        int Nbit = this.cant * NroDeBitsTotal;
        int NE = (Nbit % 32 == 0) ? Nbit / 32 : (Nbit / 32) + 1;
        v = new long[NE];
        System.arraycopy(cv, 0, v, 0, cv.length);
    }

    /**
     *
     * @param pos
     * @param elemento
     */
    public void insertar(int pos, long elemento) {
        if (pos <= cant && elemento < Math.pow(2, NroDeBitsTotal)) {
            long copia = elemento;
            long mask = (long) (Math.pow(2, NroDeBitsTotal) - 1);//Error a partir de usar con 55 bits en adelante ,quitar el parentesis
            int Nbit = ((pos - 1) * NroDeBitsTotal) % 64;
            int Nent = ((pos - 1) * NroDeBitsTotal) / 64;
            mask <<= Nbit;
            v[Nent] &= ~mask;
            v[Nent] |= elemento << Nbit;
            if (Nbit + NroDeBitsTotal > 64) {
                int NroDeBitsFaltante = Nbit + NroDeBitsTotal - 64;
                long mask1 = (long) (Math.pow(2, NroDeBitsTotal) - 1);
                mask1 = mask1 >>> (NroDeBitsTotal - NroDeBitsFaltante);
                v[Nent + 1] &= ~mask1;
                v[Nent + 1] |= copia >>> (NroDeBitsTotal - NroDeBitsFaltante);
            }
        } else {
            System.out.println("Error Posicion: " + pos + " fuera de rango");
        }
    }

    public void insertar(int pos, int lugar, int elemento) {//Insertamos el elemento en la posicion
        if (lugar <= lugarDeBits.length && pos <= cant && elemento < Math.pow(2, lugarDeBits[lugar - 1])) {
            long copia = elemento;
            long mask = (long) Math.pow(2, lugarDeBits[lugar - 1]) - 1;
            int Nbit = Fnbit(pos, lugar);
            int Nent = Fnint(pos, lugar);
            mask <<= Nbit;
            v[Nent] &= ~mask;
            v[Nent] |= copia << Nbit;
            if (Nbit + lugarDeBits[lugar - 1] > 64) {
                int Nudbitsfal = Nbit + lugarDeBits[lugar - 1] - 64;
                long mask1 = (long) Math.pow(2, lugarDeBits[lugar - 1]) - 1;
                mask1 = mask1 >>> (lugarDeBits[lugar - 1] - Nudbitsfal);
                v[Nent + 1] &= ~mask1;
                v[Nent + 1] |= elemento >>> (lugarDeBits[lugar - 1] - Nudbitsfal);
            }
        } else {
            System.out.println("Error: Lugar " + lugar + " fuera de Rango o " + elemento + " no se puede insertar la posicion " + pos + " del " + lugar + "° lugar de " + lugarDeBits[lugar - 1] + " Bits solo acepta : [0," + (int) (Math.pow(2, lugarDeBits[lugar - 1]) - 1) + "]");
        }
    }

    public long get(int pos) {
        if (pos <= cant) {
            long mask = (long) Math.pow(2, NroDeBitsTotal) - 1;//(int)(Math.pow(2,Nbits)-1); //Casteaba primero luego restaba por eso error en 32Bits
            int Nbit = ((pos - 1) * NroDeBitsTotal) % 64;
            int Nent = ((pos - 1) * NroDeBitsTotal) / 64;
            mask = v[Nent] & (mask << Nbit);
            mask = mask >>> Nbit;
            if (Nbit + NroDeBitsTotal > 64) {
                int Nbf = Nbit + NroDeBitsTotal - 64;
                long mask1 = (long) (Math.pow(2, NroDeBitsTotal) - 1);
                mask1 = mask1 >>> (NroDeBitsTotal - Nbf);
                mask1 = mask1 & v[Nent + 1];
                mask1 = mask1 << (NroDeBitsTotal - Nbf);
                mask = mask | mask1;
            }
            return (mask);
        }
        return -1;
    }

    public int get(int pos, int lugar) {
        if (pos <= cant) {
            long mask = (long) Math.pow(2, lugarDeBits[lugar - 1]) - 1;//(int)(Math.pow(2,Nbits)-1); //Casteaba primero luego restaba por eso error en 32Bits
            int Nbit = Fnbit(pos, lugar);                 //Eso crei pero a partir del 55 no sirve
            int Nent = Fnint(pos, lugar);
            mask = v[Nent] & (mask << Nbit);
            mask = mask >>> Nbit;
            if (Nbit + lugarDeBits[lugar - 1] > 64) {
                int Nbf = Nbit + lugarDeBits[lugar - 1] - 64;
                long mask1 = (long) Math.pow(2, lugarDeBits[lugar - 1]) - 1;
                mask1 = mask1 >>> (lugarDeBits[lugar - 1] - Nbf);
                mask1 = mask1 & v[Nent + 1];
                mask1 = mask1 << (lugarDeBits[lugar - 1] - Nbf);
                mask = mask | mask1;
            }
            return (int) mask;
        }
        return -1;
    }

    /**
     * Posicion de bit a la cula insertaremos el elemento
     *
     * @param pos
     * @param lugar
     * @return
     */
    public int Fnbit(int pos, int lugar) {
        return (((pos - 1) * NroDeBitsTotal) + pos(lugar)) % 64;
    }

    /**
     * Posicion del entero a la cual insertaremos el elemento
     *
     * @param pos
     * @param lugar
     * @return
     */
    public int Fnint(int pos, int lugar) {
        return (((pos - 1) * NroDeBitsTotal) + pos(lugar)) / 64;
    }

    public String toString(int pos) {
        String s = "";
        for (int i = 1; i <= lugarDeBits.length; i++) {
            s = s + get(pos, i) + ",";
        }
        s = s.length() > 0 ? "[" + s.substring(0, s.length() - 1) + "]" : "[]";
        return s;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 1; i <= cant; i++) {
            s = s + toString(i) + ",";
        }
        return s.length() > 0 ? "[" + s.substring(0, s.length() - 1) + "]" : "[]";
    }

    /*A partir de aqui es solo para mostrar*/
    public int getBit(int pos, int ent) {
        if (pos <= 64) {
            long mascara = 1;
            mascara = mascara << pos - 1;
            mascara = mascara & v[ent];
            mascara = mascara >>> pos - 1;
            return (int) mascara;
        }
        return 0;
    }

    public String toStringBit(int pos) {
        int n = 0;
        String s = "";
        int b = ((pos - 1) * NroDeBitsTotal) % 64;
        int e = ((pos - 1) * NroDeBitsTotal) / 64;
        for (int i = 1; i <= lugarDeBits.length; i++) {
            for (int j = 1; j <= lugarDeBits[lugarDeBits.length - i]; j++) {
                s = getBit(j + n + b, e) + s;
                if (j + n + b == 64) {
                    e++;
                    n -= 64;
                }
            }
            n = lugarDeBits[lugarDeBits.length - i] + n;
            s = i + 1 > lugarDeBits.length ? s : ":" + s;
        }
        return "[" + s + "]";
    }

    public String toStringBit() {
        String s = "";
        for (int i = 1; i <= cant; i++) {
            s = s + i + " " + toStringBit(i) + ", ";
        }
        return s.length() > 0 ? "[ " + s.substring(0, s.length() - 2) + "]" : "[]";
    }

    public static void main(String args[]) {
        VectorNbitsZ a = new VectorNbitsZ(10, new byte[]{6, 7, 4, 3, 7});
        VectorNbitsZ b = new VectorNbitsZ(10, new byte[]{6, 7, 4, 3, 7});
        for (int i = 1; i <= 1; i++) {
            a.insertar(i, 1, 10);
            a.insertar(i, 2, 20);
            a.insertar(i, 3, 15);
            b.insertar(i, 1, 10);
            b.insertar(i, 2, 20);
            b.insertar(i, 3, 15);
        }
        System.out.println(a.toString());
        a.insertar(1, 3, 3);
        a.insertar(1, 5, 100);
        a.insertar(1, 1, 3);
        a.insertar(1, 2, 15);
        a.insertar(1, 3, 4);
        a.insertar(4, 4, 7);
        a.insertar(3, 128);
        a.insertar(3, 5, 63);
        a.insertar(7, 10);
        a.insertar(8, 1, 4);
        a.insertar(10, 1, 1);
        a.insertar(10, 2, 12);
        a.insertar(10, 3, 2);
        System.out.println(a.toStringBit());
        System.out.println(a.toString());
        a.toString();
        a.toStringBit();
        //System.out.println(b.toString());
    }
}


//String n=Long.toBinaryString(get(i));
