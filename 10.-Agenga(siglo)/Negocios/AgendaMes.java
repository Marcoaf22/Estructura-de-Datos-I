package Negocios;

public class AgendaMes {

    BitwiseInt A[];
    String evento[][];
    int año;

    public AgendaMes(int año) {
        this.año = año;
        A = new BitwiseInt[12];
        for (int i = 0; i < 12; i++) {
            A[i] = new BitwiseInt();
        }
        evento = new String[12][31];
    }

    public boolean Esbisiesto() {
        if (año != 2000 && año % 100 == 0) {
            return false;
        } else {
            return (año % 4 == 0) ? true : false;
        }
    }

    public int NDias(int mes) {
        int x[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        x[1] = Esbisiesto() ? 29 : 28;
        return x[mes - 1];
    }

    public String getMes(int mes) {
        String y[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        return y[mes - 1];
    }

    public void Agendar(int mes, int dia, String eve) {
        A[mes - 1].Set1(dia);
        evento[mes - 1][dia - 1] = eve;
    }

    public int getDias(int mes) {
        return NDias(mes);
    }

    public String getDia(int mes, int dia) {
        return A[mes - 1].getBit(dia) == 1 ? "●" : "" + dia;
    }

    public String MostrarEvento(int mes, int dia) {
        if (A[mes - 1].getBit(dia) != 0) {
            return evento[mes - 1][dia - 1].toString();
        } else {
            return "No hay ningun evento este dia";
        }
    }

    public void EliminarEve(int mes, int dia) {
        A[mes].Set0(dia);
        evento[mes][dia - 1] = null;
    }

    public String toString() {
        String x = "";
        String g = "   ";
        for (int i = 1; i <= 12; i++) {
            x = x + getMes(i) + "\n";
            for (int j = 1; j <= getDias(i); j++) {
                x = x + getDia(i, j) + "\t";
                if (j % 7 == 0) {
                    x = x + "\n";
                }
            }
            x = x + "\n";
        }
        return x;
    }

    public static void main(String args[]) {
        AgendaMes a = new AgendaMes(2004);
        String x = a.toString();
        a.Agendar(2, 28, "Cumpleaños de Valerim");
        System.out.println(a.toString());
        System.out.println(a.MostrarEvento(2, 28));
        System.out.println(a.Esbisiesto());

    }
}
