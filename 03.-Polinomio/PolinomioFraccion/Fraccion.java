package PolinomioFraccion;

public class Fraccion {
    int nume;
    int deno;

    public Fraccion() {
        this.nume = 0;
        this.deno = 1;
    }

    public Fraccion(int nume, int deno, char signo) {
        this.deno = deno;
        if (signo == '+')
            this.nume = +nume;
        else
            this.nume = -nume;
        Simplificar();
    }

    public int getNume() {
        return Math.abs(nume);
    }

    public int getDeno() {
        return deno;
    }

    public char getSigno() {
        if (nume >= 0) {
            return '+';
        } else {
            return '-';
        }
        // return nume>=0?'+':'-';
    }

    public void setNume(int nume) {
        if (this.nume >= 0)
            this.nume = nume;
        else
            this.nume = -nume;
    }

    public void setDeno(int deno) {
        this.deno = deno;
    }

    public void setSigno(char signo) {
        if (signo == '+')
            nume = Math.abs(nume);
        else
            nume = -Math.abs(nume);
    }

    public void Suma(Fraccion A, Fraccion B) {
        deno = A.getDeno() * B.getDeno();
        nume = (A.nume * B.getDeno()) + (A.getDeno() * B.nume);
        Simplificar();
    }

    public void Resta(Fraccion A, Fraccion B) {
        deno = A.getDeno() * B.getDeno();
        nume = (A.nume * B.getDeno()) - (A.getDeno() * B.nume);
        Simplificar();
    }

    public void Multiplicar(Fraccion A, Fraccion B) {
        deno = A.getDeno() * B.getDeno();
        nume = A.nume * B.nume;
        Simplificar();
    }

    public void Dividir(Fraccion A, Fraccion B) {
        nume = A.nume * B.deno;
        deno = A.deno * B.nume;
        if (deno < 0) {
            nume = nume * -1;
            deno = Math.abs(deno);
        }
        Simplificar();
    }

    public void Simplificar() {
        int x = MCD();
        nume = nume / x;
        deno = deno / x;
    }

    public int MCD() {
        int u = Math.abs(nume);
        int v = Math.abs(deno);
        if (v == 0)
            return u;
        else {
            int z;
            while (v != 0) {
                z = u % v;
                u = v;
                v = z;
            }
            return (u);
        }
    }

    public String YoString() {
        String c = " C= ";
        c = "       " + getNume() + "\n" + c + getSigno() + " --" + "\n      " + getDeno();
        return c;
    }

    public String totring() {
        String c;
        if (getDeno() != 1) {
            c = "" + getSigno() + getNume() + "/" + getDeno();
        } else {
            c = "" + getSigno() + getNume();
        }
        return c;
    }

    public static void main(String[] args) {
        Fraccion j = new Fraccion(5, 8, '+');
        // j.Simplificar();
        System.out.println(j.totring());

    }
}
