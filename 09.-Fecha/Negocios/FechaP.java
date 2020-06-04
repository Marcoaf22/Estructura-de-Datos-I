package Negocios;

public class FechaP {

    VectorNbitsM f;
    int dim;

    public FechaP(int Cant) {
        f = new VectorNbitsM(Cant, 14);
        dim = 0;
    }

    public void Insertar(int dia, int mes, int año, int pos) {
        if ((dia < 32) && (mes < 13) && (año <= 2019)) {
            int mask = año - 1990;
            mask = mask | (dia << 9);
            mask = mask | (mes << 5);
            f.Insertar(pos, mask);
        }
    }

    public void setDia(int dia, int pos) {
        if (dia < 32 && pos <= f.Cant) {
            int mask = (int) f.get(pos);
            int mask1 = (int) Math.pow(2, 5) - 1;
            mask = mask & ~(mask1 << 9);
            dia = dia << 9;
            mask = mask | dia;
            f.Insertar(pos, mask);
        }
    }

    public void setMes(int mes, int pos) {
        if (mes < 13 && pos <= f.Cant) {
            int mask = (int) f.get(pos);
            int mask1 = (int) Math.pow(2, 4) - 1;
            mask = mask & ~(mask1 << 5);
            mask = mask | (mes << 5);
            f.Insertar(pos, mask);
        }
    }

    public void setAño(int año, int pos) {
        if (año < 2020 && pos <= f.Cant) {
            int mask = (int) f.get(pos);
            int mask1 = (int) Math.pow(2, 5) - 1;
            mask = mask & ~mask1;
            mask = mask | (año - 1990);
            f.Insertar(pos, mask);
        }
    }

    public int getMes(int pos) {
        if (f.get(pos) != 0) {
            int mask = (int) f.get(pos);
            int mask1 = (int) Math.pow(2, 4) - 1;
            mask = mask & (mask1 << 5);
            mask = mask >>> 5;
            return mask;
        } else {
            System.out.println("No hay fecha en la posicion");
            return -1;
        }
    }

    public int getDia(int pos) {
        if (f.get(pos) != 0) {
            int mask = (int) f.get(pos);
            int mask1 = (int) Math.pow(2, 5) - 1;
            mask = mask & (mask1 << 9);
            mask = mask >>> 9;
            return mask;
        } else {
            System.out.println("No hay fecha en la posicion");
            return -1;
        }
    }

    public int getAño(int pos) {
        if (f.get(pos) != 0) {
            int mask = (int) f.get(pos);
            int mask1 = (int) Math.pow(2, 5) - 1;
            mask = mask & mask1;
            mask = mask + 1990;
            return mask;
        } else {
            System.out.println("No hay fecha en la posicion");
            return -1;
        }
    }

    public String toString() {
        String e = "";
        for (int i = 1; i <= f.Cant; i++) {
            if (f.get(i) == 0) {
                e = e + "---,";
            } else {
                e = e + getDia(i) + "/" + getMes(i) + "/" + getAño(i) + ",";
            }
        }
        e = "[" + e.substring(0, e.length() - 1) + "]";
        return e;
    }

    public static void main(String args[]) {
        FechaP a = new FechaP(10);
        a.Insertar(3, 5, 1999, 1);
        a.setDia(31, 2);
        a.setMes(12, 2);
        a.setAño(2019, 2);
        a.setDia(3, 3);
        System.out.println();
        System.out.println(a.toString());
    }

}
