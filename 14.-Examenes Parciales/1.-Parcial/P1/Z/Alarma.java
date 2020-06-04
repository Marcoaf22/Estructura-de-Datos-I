package P1.Z;

/**
 *
 * @author Marcoaf
 */
public class Alarma {

    VectorNbitsZ a;
    String nombre[];
    int dim;

    public Alarma(int cant) {
        a = new VectorNbitsZ(10, new int[] { 23, 59, 1, 1, 1, 1, 1, 1, 1, 1 });
        nombre = new String[10];
        dim = 0;
    }

    public void insertar(int pos, int hora, int min, boolean r, String nom, boolean l, boolean m, boolean mi,
            boolean ju, boolean v, boolean s, boolean d) {
        if (hora <= 23 && min <= 59) {
            a.insertar(pos, 1, hora);
            a.insertar(pos, 2, min);
            a.insertar(pos, 3, r ? 1 : 0);
            a.insertar(pos, 4, l ? 1 : 0);
            a.insertar(pos, 5, m ? 1 : 0);
            a.insertar(pos, 6, mi ? 1 : 0);
            a.insertar(pos, 7, ju ? 1 : 0);
            a.insertar(pos, 8, v ? 1 : 0);
            a.insertar(pos, 9, s ? 1 : 0);
            a.insertar(pos, 10, d ? 1 : 0);
            nombre[pos - 1] = nom;
            dim++;
        } else {
            System.out.println("hora fuera de rango");
        }
    }

    public String getDias(int pos) {
        String x = "";
        if (a.get(pos, 4) == 1) {
            x = x + "lunes,";
        }
        if (a.get(pos, 5) == 1) {
            x = x + "Martes,";
        }
        if (a.get(pos, 6) == 1) {
            x = x + "Miercole,";
        }
        if (a.get(pos, 7) == 1) {
            x = x + "Jueves,";
        }
        if (a.get(pos, 8) == 1) {
            x = x + "Viernes,";
        }
        if (a.get(pos, 9) == 1) {
            x = x + "Sabado,";
        }
        if (a.get(pos, 10) == 1) {
            x = x + "Domingo,";
        }
        return x;
    }

    public int getHora(int pos) {
        return a.get(pos, 1);
    }

    public int getMin(int pos) {
        return a.get(pos, 2);
    }

    public boolean getRepetir(int pos) {
        return a.get(pos, 3) == 1;
    }

    public String getNombre(int pos) {
        return nombre[pos - 1];
    }

    public String toString() {
        String x = "";
        for (int i = 1; i <= dim; i++) {
            x = x + "------Alarma Nr:" + i + "-------\n";
            x = x + "Hora: " + getHora(i) + ":" + getMin(i) + "\n";
            x = x + "Repetir = ";
            if (getRepetir(i)) {
                x = x + "si\n";
            } else {
                x = x + "no\n";
            }
            x = x + "Dias : " + getDias(i) + "\n";
            x = x + "Nombre = " + getNombre(i) + "\n";
        }
        return x;
    }

    public static void main(String args[]) {
        Alarma a = new Alarma(10);
        for (int i = 1; i <= 10; i++) {
            a.insertar(i, 2, 30, false, "Gym", true, true, false, true, true, true, true);
        }
        System.out.println(a.toString());
    }
}
