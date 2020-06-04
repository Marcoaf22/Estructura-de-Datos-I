package P1;

public class Alarma {

    VectorNbitsM a;
    Bitwise[] dias;
    String nombre[];
    int dim;

    public Alarma() {
        a = new VectorNbitsM(1, 11);
        dias = new Bitwise[10];
        for (int i = 0; i < 10; i++) {
            dias[i] = new Bitwise();
        }
        nombre = new String[10];
        dim = 0;
    }

    public void insertar(int hora, int min, boolean r, String nom) {
        if (hora <= 23 && min <= 59) {
            nombre[dim] = nom;
            dim++;
            int mask = 1;//5,6,1
            int n = 0;
            if (r) {
                n = n | (mask << 10);
            } else {
                n = 0;
            }
            n |= (hora << 6);
            n |= min;
            a.Insertar(dim, n);
        } else {
            System.out.println("hora fuera de rango");
        }
    }

    public void setdias(boolean l, boolean m, boolean mi, boolean ju, boolean v, boolean s, boolean d) {
        if (l) {
            dias[dim - 1].Set1(1);
        }
        if (m) {
            dias[dim - 1].Set1(2);
        }
        if (mi) {
            dias[dim - 1].Set1(3);
        }
        if (ju) {
            dias[dim - 1].Set1(4);
        }
        if (v) {
            dias[dim - 1].Set1(5);
        }
        if (s) {
            dias[dim - 1].Set1(6);
        }
        if (d) {
            dias[dim - 1].Set1(7);
        }

    }

    public String getDias(int pos) {
        String x = "";
        if (dias[pos - 1].getBit(1) == 1) {
            x = x + "lunes,";
        }
        if (dias[pos - 1].getBit(2) == 1) {
            x = x + "Martes,";
        }
        if (dias[pos - 1].getBit(3) == 1) {
            x = x + "Jueves,";
        }
        if (dias[pos - 1].getBit(4) == 1) {
            x = x + "Viernes,";
        }
        if (dias[pos - 1].getBit(5) == 1) {
            x = x + "Sabado,";
        }
        if (dias[pos - 1].getBit(6) == 1) {
            x = x + "Domingo,";
        }
        return x;
    }

    public int getHora(int pos) {
        int g = (int) a.get(pos);
        int b = (int) (Math.pow(2, 5) - 1);
        g = g & (b << 6);
        g = g >>> 6;
        return g;
    }

    public int getMin(int pos) {
        int g = (int) a.get(pos);
        int b = (int) (Math.pow(2, 6) - 1);
        g = g & b;
        return g;
    }

    public boolean getRepetir(int pos) {
        int g = (int) a.get(pos);
        int b = 1;
        g = g & (b << 10);
        g = g >>> 10;
        return g == 1;
    }

    public String getNombre(int pos) {
        return nombre[pos - 1];
    }

    public String toString() {
        String x = "";
        for (int i = 1; i <= dim; i++) {
            x = x + "Hora: " + getHora(i) + ":" + getMin(i) + "\n";
            x = x + "Repetir=";
            if (getRepetir(i)) {
                x = x + "si\n";
            } else {
                x = x + "no\n";
            }
            x = x + "Dias:" + getDias(i) + "\n";
            x = x + "nombre=" + getNombre(i);
        }
        return x;
    }

    public static void main(String args[]) {
        Alarma a = new Alarma();
        a.insertar(22, 55, false, "nom");
        String x = a.toString();
        a.getHora(1);
        a.getRepetir(1);
        System.out.println(a.getRepetir(1));
        System.out.println(a.getHora(1));
        System.out.println(a.toString());
    }
}
