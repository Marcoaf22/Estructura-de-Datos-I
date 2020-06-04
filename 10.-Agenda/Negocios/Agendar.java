package Negocios;

public class Agendar {

    BitwiseInt A;
    String evento[];

    public Agendar() {
        A = new BitwiseInt();
        evento = new String[31];

    }

    public void Agendar(int dia, String eve) {
        A.Set1(dia);
        evento[dia - 1] = eve;
    }

    public String MostrarEvento(int dia) {
        if (A.getBit(dia) != 0) {
            return evento[dia - 1];
        } else {
            return "No hay ningun evento este dia";
        }
    }

    public void Eliminar(int dia) {
        A.Set0(dia);
        evento[dia - 1] = null;
    }

    public String toString() {
        String x = "";
        for (int i = 1; i <= 31; i++) {
            x = A.getBit(i) != 1 ? x + i + "\t" : x + "x\t";
            if (i % 7 == 0) {
                x = x + "\n";
            }
        }
        return x;
    }

    public static void main(String args[]) {
        Agendar a = new Agendar();
        a.Agendar(31, "Ir al Gym");
        System.out.println(a.toString());
        System.out.println(a.MostrarEvento(4));
        System.out.println(a.MostrarEvento(31));
        a.Eliminar(31);
        System.out.println(a.toString());
    }
}
