package P2;

public class Fraccion {

    VectorNbitsM coeficiente;
    int dim;

    public Fraccion() {
        coeficiente = new VectorNbitsM(10, 21);
        dim = 0;
    }

    public void insertar(int nume, int deno, char s) {
        if (deno != 0 && deno < (int) Math.pow(2, 10) && nume < (int) Math.pow(2, 10)) {
            dim++;
            int mask = 1;
            int n = 0;
            n = (s == '+') ? n | (mask << 20) : 0;
            n |= (nume << 10);
            n |= deno;
            coeficiente.Insertar(dim, n);
        } else {
            System.out.println("Numerado o denominar fuera de rango");
        }

    }

    public void SetNume(int pos, int elemento) {
        int g = (int) coeficiente.get(pos);
        int mask = (int) (Math.pow(2, 10) - 1);
        g = g & ~(mask << 10);
        g = g | (elemento << 10);
        coeficiente.Insertar(pos, g);
    }

    public void SetDeno(int pos, int elemento) {
        int g = (int) coeficiente.get(pos);
        int mask = (int) (Math.pow(2, 10) - 1);
        g = g & ~(mask);
        g = g | (elemento);
        coeficiente.Insertar(pos, g);
    }

    public void SetSigno(int pos, char s) {
        int g = (int) coeficiente.get(pos);
        int mask = 1;
        g = g & ~(mask << 20);
        if (s == '+') {
            g = g & mask;
        }
        coeficiente.Insertar(pos, g);
    }

    public int getDeno(int pos) {
        int g = (int) coeficiente.get(pos);
        g &= (int) (Math.pow(2, 10) - 1);
        return g;
    }

    public int getNume(int pos) {
        int g = (int) coeficiente.get(pos);
        int mask = (int) (Math.pow(2, 10) - 1);
        g = g & (mask << 10);
        g = g >>> 10;
        return g;
    }

    public char getSigno(int pos) {
        int g = (int) coeficiente.get(pos);
        int mask = 1;
        g = g & (mask << 20);
        g = g >>> 20;
        return g == 1 ? '+' : '-';
    }

    public String toString() {
        String x = "";
        for (int i = 1; i <= dim; i++) {
            x = x + getSigno(i) + getNume(i) + "/" + getDeno(i) + " ";
        }
        return x;
    }

    public static void main(String args[]) {
        Fraccion a = new Fraccion();
        a.insertar(1, 15, '+');
        a.insertar(2, 15, '-');
        a.insertar(3, 15, '+');
        a.insertar(4, 15, '-');
        System.out.println(a.toString());

    }
}
