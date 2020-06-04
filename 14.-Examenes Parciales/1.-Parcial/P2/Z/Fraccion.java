package P2.Z;

/**
 *
 * @author Marcoaf
 */
public class Fraccion {

    VectorNbitsZ coeficiente;
    int dim;

    public Fraccion(int cant) {
        coeficiente = new VectorNbitsZ(cant, new byte[] { 1, 10, 10 });
        dim = 0;
    }

    public void Redimensionar(int cant) {
        coeficiente.redimensionar(cant);
    }

    public void insertar(int pos, int nume, int deno, char s) {
        if (deno != 0 && deno < (int) Math.pow(2, 10) && nume < (int) Math.pow(2, 10)) {
            coeficiente.insertar(pos, 1, s == '+' ? 1 : 0);
            coeficiente.insertar(pos, 2, nume);
            coeficiente.insertar(pos, 3, deno);
            dim++;
        } else {
            System.out.println("Numerado o denominar fuera de rango");
        }
    }

    public String get(int pos) {
        String x = "" + getSigno(pos) + getNume(pos) + "/" + getDeno(pos) + " ";
        return x;
    }

    public void SetSigno(int pos, char s) {
        coeficiente.insertar(pos, 1, s == '+' ? 1 : 0);
    }

    public void SetNume(int pos, int elemento) {
        coeficiente.insertar(pos, 2, elemento);
    }

    public void SetDeno(int pos, int elemento) {
        coeficiente.insertar(pos, 3, elemento);
    }

    public char getSigno(int pos) {
        return coeficiente.get(pos, 1) == 1 ? '+' : '-';
    }

    public int getNume(int pos) {
        return coeficiente.get(pos, 2);
    }

    public int getDeno(int pos) {
        return coeficiente.get(pos, 3);
    }

    public String toString() {
        String x = "";
        for (int i = 1; i <= dim; i++) {
            x = x + getSigno(i) + getNume(i) + "/" + getDeno(i) + " ";
        }
        return x;
    }

    public static void main(String args[]) {
        Fraccion a = new Fraccion(10);
        for (int i = 1; i <= 10; i++) {
            a.insertar(i, i, 1, '+');

        }
        System.out.println(a.get(2));
        System.out.println(a.toString());
    }
}
