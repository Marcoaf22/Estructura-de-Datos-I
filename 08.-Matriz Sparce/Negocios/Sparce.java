/*una matriz esparcida poco poblada son muy utilizadas cuando se quieren implementar matrices de gran
tamaño donde la "cantidad de elementos predominantes son elementos nulos o repetitivos", en ese caso sera optimo 
implementar una matriz esparcida 
aplicaciones:
-programas de calculo estructural
-los juegos 
-graficadores
-manejo de imagenes
-BMP(mapa de bits) guarda todos los bits excepto los elementos predominantes (jpg)
Este tipo de matrices se utiliza en el ambito de ingeniera civil,juegos,graficadores,manejo de imagenes.
Para implementar este tipo de matris esparcida debemos utilizar una distribucion eficiente que me 
permita manejar esta matriz de forma mas optima*/
package Negocios;

public class Sparce {

    int vfc[];
    float vd[];
    float pe;
    int Nfil;
    int Ncol;
    int dim;

    public Sparce(int filas, int cols, int predo) {
        Nfil = filas;
        Ncol = cols;
        pe = predo;
        vfc = new int[3];
        vd = new float[3];
        dim = -1;
    }

    public float get(int fila, int col) {
        if (fila > Nfil || col > Ncol) {
            System.out.println("Error Dimensiones invalidas");
            System.exit(1);
        } else {
            int fc = (fila - 1) * Ncol + col;
            int i = 0;
            while ((i <= dim) && (vfc[i] != fc)) {
                i++;
            }
            if (i <= dim) {
                return vd[i];
            } else {
                return pe;
            }
        }
        return -1;
    }

    public int pos(int fila, int col) {
        int fc = (fila - 1) * Ncol + col;
        for (int i = 0; i <= dim; i++) {
            if (vfc[i] == fc) {
                return i;
            }
        }
        return -1;
    }

    public void redimensionar() {
        int cfc[] = cfc = new int[vfc.length];
        System.arraycopy(vfc, 0, cfc, 0, cfc.length);
        vfc = new int[cfc.length + 5];
        System.arraycopy(cfc, 0, vfc, 0, cfc.length);
        float cd[] = new float[vd.length];
        System.arraycopy(vd, 0, cd, 0, vd.length);
        vd = new float[cd.length + 5];
        System.arraycopy(cd, 0, vd, 0, cd.length);
    }

    public void Insertar(int fila, int col, float elemento) {
        if (fila <= Nfil && col <= Ncol) {
            int fc = (fila - 1) * Ncol + col;
            int i = pos(fila, col);
            if (elemento != pe) {
                if (i >= 0) {
                    vd[i] = elemento;
                } else {
                    if (dim == vfc.length - 1) {
                        redimensionar();
                    }
                    vfc[dim + 1] = fc;
                    vd[dim + 1] = elemento;
                    dim++;
                }
            } else {
                eliminar(fila, col);   //solo es por las permutaciones      
            }
        }
    }

    public void eliminar(int fila, int col) {
        if (fila <= Nfil && col <= Ncol) {
            int fc = (fila - 1) * Ncol + col;
            int i = pos(fila, col);
            if (i >= 0) {
                for (int j = i; j < dim; j++) {
                    vfc[j] = vfc[j + 1];
                    vd[j] = vd[j + 1];
                }
                dim--;
            }
        }
    }

    public void Permutar(int fila1, int fila2) {
        if (fila1 <= Nfil && fila2 <= Nfil) {
            for (int i = 1; i <= Ncol; i++) {
                float x = get(fila1, i);
                Insertar(fila1, i, get(fila2, i));
                Insertar(fila2, i, x);
            }
        }
    }

    public String toString() {
        String x = "";
        for (int i = 1; i <= Nfil; i++) {
            for (int j = 1; j <= Ncol; j++) {
                x = x + get(i, j) + " ";
            }
            x = x + "\n";
        }
        return x;
    }

    public static void main(String args[]) {
        Sparce a = new Sparce(5, 5, 0);
        a.Insertar(1, 1, 4.3f);
        a.Insertar(1, 3, 7);
        a.Insertar(2, 3, 4);
        a.Insertar(3, 2, 8.4f);
        a.Insertar(4, 2, 54);
        a.Insertar(5, 1, 5.4f);
        a.Insertar(5, 5, 3.1f);

        System.out.println(a.toString());
        a.eliminar(3, 2);
        a.eliminar(5, 5);
        // a.Permutar(1, 5);

        System.out.println(a.toString());
        String p = a.toString();
//    System.out.println(a.toString());
    }

}
