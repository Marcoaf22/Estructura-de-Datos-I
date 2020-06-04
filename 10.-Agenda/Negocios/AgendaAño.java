package Negocios;

public class AgendaAño {

    AgendaMes B[];

    public AgendaAño() {
        B = new AgendaMes[12];
        for (int i = 0; i < 12; i++) {
            B[i] = new AgendaMes(i + 1);
        }
    }

    public int getDias(int mes) {
        return B[mes - 1].getDias();
    }

    public int Empiezo(int mes) {//no devuelve en uno menos si empieza martes da 1;
        int s = 1;
        for (int i = 1; i < mes; i++) {
            s = s + B[i - 1].getDias();
        }
        return s % 7;
    }

    public String getDia(int mes, int dia) {
        if (mes <= 12) {
            return B[mes - 1].getDia(dia);
        } else {
            return "";
        }
    }

    public void Agendar(int mes, int dia, String eve) {
        B[mes - 1].Agendar(dia, eve);
    }

    public void EliminarEvento(int mes, int dia) {
        B[mes - 1].EliminarEve(dia);
    }

    public String EventodelMes(int mes, int dia) {
        return B[mes - 1].MostrarEvento(dia);
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
        a.Agendar(1, 1, "cumpleaños feliz");
        System.out.println(a.getDias(1));
        System.out.println(a.EventodelMes(1, 1));
        int g = a.Empiezo(5);
        System.out.println(a.Empiezo(5));
    }
}
