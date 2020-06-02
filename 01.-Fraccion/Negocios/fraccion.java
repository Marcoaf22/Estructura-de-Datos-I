package Negocios;

public class fraccion {

    int nume;
    int deno;

    public fraccion() {
        this.nume = 0;
        this.deno = 1;
    }

    public fraccion(int nume, int deno, char signo) {
        this.deno = deno;
        if (signo == '+') {
            this.nume = +nume;
        } else {
            this.nume = -nume;
        }
    }

    public void CSigno() {
        if (nume >= 0) {
            nume = -Math.abs(nume);
        } else {
            nume = Math.abs(nume);
        }
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
    }

    public void setNume(int nume) {
        if (this.nume >= 0) {
            this.nume = nume;
        } else {
            this.nume = -nume;
        }
    }

    public void setDeno(int deno) {
        this.deno = deno;
    }

    public void setSigno(char signo) {
        if (signo == '+') {
            nume = Math.abs(nume);
        } else {
            nume = -Math.abs(nume);
        }
    }

    public void Suma(fraccion A, fraccion B) {
        deno = A.getDeno() * B.getDeno();
        nume = (A.nume * B.getDeno()) + (A.getDeno() * B.nume);
        Simplificar();
    }

    public void Resta(fraccion A, fraccion B) {
        deno = A.getDeno() * B.getDeno();
        nume = (A.nume * B.getDeno()) - (A.getDeno() * B.nume);
        Simplificar();
    }

    public void Multiplicar(fraccion A, fraccion B) {
        deno = A.getDeno() * B.getDeno();
        nume = A.nume * B.nume;
        Simplificar();
    }

    public void Dividir(fraccion A, fraccion B) {
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
        if (v == 0) {
            return u;
        } else {
            int z;
            while (v != 0) {
                z = u % v;
                u = v;
                v = z;
            }
            return (u);
        }
    }

    @Override
    public String toString() {
        String c = "C= " + getSigno() + getNume() + "/" + getDeno();
        return c;
    }

    public String toStringB() {
        String c = "" + getSigno() + getNume() + "/" + getDeno() + "  ";
        return nume == 0 ? "0" : deno == 1 ? "" + nume + "" : c;
    }

    public void copy(fraccion a) {
        nume = a.nume;
        deno = a.deno;
    }

    public static void main(String[] args) {
        fraccion A = new fraccion(2, 1, '-');
        fraccion B = new fraccion(5, 1, '-');
        fraccion C = new fraccion();
        C.Suma(A, B);
        System.out.println(C.toStringB());
    }
}
