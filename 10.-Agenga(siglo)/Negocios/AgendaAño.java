package Negocios;

public class AgendaAño {

    AgendaMes B[];

    public AgendaAño() {
        B = new AgendaMes[200];
        for (int i = 0; i < 200; i++) {
            B[i] = new AgendaMes(i + 1900);
        }
    }

    public int getDias(int año, int mes) {
        return B[año - 1900].NDias(mes);
    }

    public int Empiezo(int años, int mes) {
        int s = 0;
        for (int i = 0; i < años - 1900; i++) {
            s = s + 365;
            s = B[i].Esbisiesto() ? s + 1 : s;
        }
        for (int j = 1; j < mes; j++) {
            s = s + B[j - 1].getDias(j);
        }
        return s == 0 ? 1 : (s % 7) + 1;
    }

    public String getDia(int año, int mes, int dia) {
        if (dia <= B[año - 1900].getDias(mes)) {
            return B[año - 1900].getDia(mes, dia);
        } else {
            return "";
        }
    }

    public void Agendar(int año, int mes, int dia, String eve) {
        B[año - 1900].Agendar(mes, dia, eve);
    }

    public void EliminarEvento(int año, int mes, int dia) {
        B[año - 1900].EliminarEve(mes, dia);
    }

    public String EventodelMes(int año, int mes, int dia) {
        return B[año - 1900].MostrarEvento(mes, dia);
    }

    public String toString() {
        String a = "";
        for (int i = 0; i < 12; i++) {
            a = a + B[i].toString() + "\n";
        }
        return a;
    }

    public static void main(String[] args) {
        AgendaAño a = new AgendaAño();
        System.out.println("" + a.Empiezo(2019, 5));
        // a.Agendar(1, 1, "cumpleaños feliz");
        // System.out.println(a.getDias(1));
//    System.out.println(a.EventodelMes(1, 1));
//    int g=a.Empiezo(5);
//    System.out.println(a.Empiezo(5));
//    System.out.println(a.getDias(2));
    }
}
