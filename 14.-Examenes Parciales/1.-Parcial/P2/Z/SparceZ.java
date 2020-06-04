package P2.Z;

/**
 *
 * @author Marcoaf
 */
public class SparceZ {

    private VectorNbitsZ vfc;
    private Fraccion datos;
    private final float PE;
    private int Nfil;
    private int Ncol;
    private int dim;

    public SparceZ(int filas, int cols, float predo) {
        Nfil = filas;
        Ncol = cols;
        PE = predo;
        vfc = new VectorNbitsZ(10, new int[] { filas * cols });
        datos = new Fraccion(10);
        dim = 0;
    }

    public void redimensionar() {
        if (dim == vfc.cant) {
            vfc.redimensionar(dim + 5);
            datos.Redimensionar(dim + 5);
        }
    }

    public int sacarIndice(int fc) {
        for (int i = 1; i <= dim; i++) {
            if (vfc.get(i) == fc) {
                return i;
            }
        }
        return -1;
    }

    public void set(int fila, int col, int nume, int deno, char s) {
        if (fila <= Nfil && col <= Ncol) {
            int fc = (fila - 1) * Ncol + col;
            int indice = sacarIndice(fc);
            if (indice > 0) {
                datos.insertar(indice, nume, deno, s);
            } else {
                redimensionar();
                datos.insertar(++dim, nume, deno, s);
                vfc.insertar(dim, fc);
            }
        }
    }

    public void eliminar(int fila, int col) {
        if (fila <= Nfil && col <= Ncol) {
            int i = sacarIndice((fila - 1) * Ncol + col);
            if (i >= 1) {
                for (int j = i; j < vfc.cant; j++) {
                    datos.insertar(j, datos.getNume(j + 1), datos.getDeno(j + 1), datos.getSigno(j + 1));
                    vfc.insertar(j, vfc.get(j + 1));
                }
                dim--;
            }
        }
    }

    public int getFila() {
        return Nfil;
    }

    public int getCol() {
        return Ncol;
    }

    public String get(int fila, int col) {
        if (fila <= Nfil && col <= Ncol) {
            int fc = (fila - 1) * Ncol + col;
            int i = sacarIndice(fc);
            if (i > 0) {
                return datos.get(i);
            } else {
                return Float.toString(PE);
            }
        }
        System.exit(1);// 1 para salir del metodo ,0 para salir del programa
        return "Error de Dimension";
    }

    public String toString() {
        String x = "";
        for (int i = 1; i <= Nfil; i++) {
            for (int j = 1; j <= Ncol; j++) {
                x = x + get(i, j) + "\t";
            }
            x = x + "\n";
        }
        return x;
    }

    public static void main(String[] args) {
        SparceZ matriz = new SparceZ(5, 5, 0);
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                matriz.set(i, j, i + j - 1, 1, '+');
            }
        }
        System.out.println(matriz.toString());
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                matriz.eliminar(i, j);
            }
        }
        System.out.println(matriz.toString());
        System.out.println(matriz.toString());
    }
}
