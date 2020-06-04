package SparceFraccion;

public class SparceF {

    fraccion f[];
    int dim;
    int Nfila;
    int Ncol;
    VectorNbitsM v;
    int pe;

    public SparceF(int fila, int col, int predominante) {
        Nfila = fila;
        Ncol = col;
        pe = predominante;
        v = new VectorNbitsM(10, Nbits(Nfila, Ncol));//
        f = new fraccion[10];
        for (int i = 0; i < 10; i++) {
            f[i] = new fraccion();
        }
        dim = 0;
    }

    public int Nbits(int fila, int col) {
        int i = 1;
        while ((Math.pow(2, i) - 1) < (fila * col)) {
            i++;
        }
        return i;
    }

    public int Posicion(int fc) {
        for (int i = 1; i <= dim; i++) {
            if (v.get(i) == fc) {
                return i;
            }
        }
        return -1;
    }

    public void insertar(int fila, int col, char signo, int nume, int deno) {
        if (fila <= Nfila && col <= Ncol) {
            int fc = (fila - 1) * Ncol + col;
            int i = Posicion(fc);
            if (i > 0) {
                if (i > dim) //  redimensiona();
                {
                    f[i].insertar(nume, deno, signo);
                }
            } else {
                dim++;
                v.Insertar(dim, fc);
                f[dim - 1].insertar(nume, deno, signo);
            }
        } else {
            System.out.println("Error posicion del elemento fuera de rango");
        }
    }

    public String get(int fila, int col) {
        int fc = (fila - 1) * Ncol + col;
        int i = Posicion(fc);
        if (i > 0) {
            return f[i - 1].AString();
        } else {
            return "" + pe;
        }
    }

    public String toString() {
        String s = "";
        for (int i = 1; i <= Nfila; i++) {
            for (int j = 1; j <= Ncol; j++) {
                s = s + get(i, j) + "\t";
            }
            s = s + "\n";
        }
        return s;
    }

    public static void main(String args[]) {

        SparceF matriz = new SparceF(5, 5, 0);
        matriz.insertar(2, 3, '+', 2, 3);
        System.out.println(matriz.toString());
    }
}
