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

public class SparceN {

    VectorNbitsM vfc;
    float vd[];
    float pe;
    int Nfil;
    int Ncol;
    int dim;
    int Nele;

    public SparceN(int filas, int cols, float predo) {
        Nele = 5;
        Nfil = filas;
        Ncol = cols;
        pe = predo;
        vfc = new VectorNbitsM(Nele, Nbits(Nfil, Ncol));//
        vd = new float[5];
        dim = 0;
    }

    public int getf() {
        return Nfil;
    }

    public int getc() {
        return Ncol;
    }

    private int Nbits(int fila, int col) {//nos calcula los bits que necesitaremos para la matriz
        int i = 1;
        while (Math.pow(2, i) - 1 < (fila * col)) {
            i++;
        }
        return i;//eje: 10*10 nos da 7bits  ,la posicion maximas es 100 se representa en 7 bits
    }

    public float get(int fila, int col) {
        if (fila > Nfil || col > Ncol) {
            System.out.println("Error Dimensiones invalidas");
            System.exit(1);//1 para salir del metodo ,0 para salir del programa
        } else {
            int fc = (fila - 1) * Ncol + col;
            int i = 1;
            while ((i <= dim) && (vfc.get(i) != fc)) {
                i++;
            }
            if (i <= dim) {
                return vd[i - 1];
            } else {
                return pe;
            }

        }
        return -1;
    }

    public void elminar(int fila, int col) {
        if (fila <= Nfil && col <= Ncol) {
            int i = pos(fila, col);
            if (i >= 1) {
                for (int j = i; j < vd.length; j++) {
                    vd[j - 1] = vd[j];
                    vfc.Insertar(j, vfc.get(j + 1));
                }
                dim--;

            }
        }
    }

    public int pos(int fila, int col) {
        int fc = (fila - 1) * Ncol + col;
        for (int i = 1; i <= dim; i++) {
            if (fc == vfc.get(i)) {
                return i;
            }
        }
        return -1;
    }

    public void redimensionar() {
        VectorNbitsM cfc = new VectorNbitsM(Nele + 5, Nbits(Nfil, Ncol));
        for (int i = 0; i < vfc.v.length; i++) {
            cfc.v[i] = vfc.v[i];
        }
        vfc = new VectorNbitsM(cfc.Cant, Nbits(Nfil, Ncol));
        for (int i = 0; i < cfc.Nent(); i++) {
            vfc.v[i] = cfc.v[i];
        }
        float cd[] = new float[vd.length + 5];
        System.arraycopy(vd, 0, cd, 0, vd.length);
        vd = new float[cd.length + 5];
        System.arraycopy(cd, 0, vd, 0, cd.length);
        Nele = Nele + 5;
    }

    public void Permutar(int fila1, int fila2) {
        if (fila1 <= Nfil && fila2 <= Nfil) {
            for (int i = 1; i <= Ncol; i++) {
                float x = get(fila1, i);
                insertar(fila1, i, get(fila2, i));
                insertar(fila2, i, x);
            }
        }
    }

    public void insertar(int fila, int col, float elemento) {
        if (fila <= Nfil && col <= Ncol) {
            int i = pos(fila, col);
            int fc = (fila - 1) * Ncol + col;
            if (elemento != pe) {//solo por el metodo permutar
                if (i >= 1) {
                    vfc.Insertar(i, fc);
                    vd[i - 1] = elemento;
                } else {
                    if (Nele == dim) {
                        redimensionar();
                    }

                    dim++;
                    vfc.Insertar(dim, fc);
                    vd[dim - 1] = elemento;
                }
            } else {
                elminar(fila, col);
            }
        }
    }

    public String toString() {
        String x = "";
        String z = "             ";
        for (int i = 1; i <= Nfil; i++) {
            for (int j = 1; j <= Ncol; j++) {
                String f = Float.toString(get(i, j));
                //   x=x + get(i,j)+" \t";
                x = x + get(i, j) + z.substring(0, 9 - f.length());
            }
            x = x + "\n";
        }
        return x;
    }

    public static void main(String args[]) {
        SparceN matriz = new SparceN(5, 5, 0);
        matriz.insertar(1, 4, 21);
        matriz.insertar(2, 2, 9.45f);
//    matriz.insertar(5, 5, 5.4f);
//    matriz.insertar(3, 2, 10);
//    matriz.insertar(4, 3, 8);   
//    matriz.insertar(1, 2, 12);   
        System.out.println(matriz.toString());
        matriz.Permutar(1, 5);
        System.out.println(matriz.toString());
    }
}
