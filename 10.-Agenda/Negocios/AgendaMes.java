package Negocios;

public class AgendaMes {

    BitwiseInt A;
    String evento[];
    String Mes;
    int dias;

    public AgendaMes(int mes) {
        if (mes > 0 && mes < 13) {
            dias = NDias(mes);
            A = new BitwiseInt();
            evento = new String[dias];
        }
    }

    public int NDias(int mes) {
        int x[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String y[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        Mes = y[mes - 1];
        return x[mes - 1];
    }

    public void Agendar(int dia, String eve) {
        if (dia <= dias) {
            A.Set1(dia);
            evento[dia - 1] = eve;
        } else {
            System.out.println("Error: Sobrepasa los dias del mes");
        }
    }

    public String getMes() {
        return Mes;
    }

    public int getDias() {
        return dias;
    }

    public String getDia(int dia) {
        if (dia <= dias) {
            return A.getBit(dia) == 1 ? "●" : "" + dia;
        }
        return "";
    }

    public String MostrarEvento(int dia) {
        if (A.getBit(dia) != 0) {
            return evento[dia - 1].toString();
        } else {
            return "No hay ningun evento este dia";
        }
    }

    public void EliminarEve(int dia) {
        A.Set0(dia);
        evento[dia - 1] = null;
    }

    public String toString() {
        String x = Mes + "\n";
        String g = "   ";
        for (int i = 1; i <= dias; i++) {
            x = A.getBit(i) != 1 ? x + i + "\t" : x + "●\t";
            if (i % 7 == 0) {
                x = x + "\n";
            }
        }
        return x;
    }

    public static void main(String args[]) {
        AgendaMes a = new AgendaMes(1);
        a.Agendar(29, "hola como estas");
        System.out.println(a.toString());
        System.out.println(a.MostrarEvento(4));
        System.out.println(a.MostrarEvento(6));
        System.out.println(a.toString());
    }
}
