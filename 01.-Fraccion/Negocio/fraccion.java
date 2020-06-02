
public class fraccion {

    int nume;
    int deno;

    public fraccion() {
        this.nume = 0;
        this.deno = 1;
    }

    public fraccion(final int nume, final int deno, final char signo) {
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

    public void setNume(final int nume) {
        if (this.nume >= 0) {
            this.nume = nume;
        } else {
            this.nume = -nume;
        }
    }

    public void setDeno(final int deno) {
        this.deno = deno;
    }

    public void setSigno(final char signo) {
        if (signo == '+') {
            nume = Math.abs(nume);
        } else {
            nume = -Math.abs(nume);
        }
    }

    public void Suma(final fraccion A, final fraccion B) {
        deno = A.getDeno() * B.getDeno();
        nume = (A.nume * B.getDeno()) + (A.getDeno() * B.nume);
        Simplificar();
    }

    public void Resta(final fraccion A, final fraccion B) {
        deno = A.getDeno() * B.getDeno();
        nume = (A.nume * B.getDeno()) - (A.getDeno() * B.nume);
        Simplificar();
    }

    public void Multiplicar(final fraccion A, final fraccion B) {
        deno = A.getDeno() * B.getDeno();
        nume = A.nume * B.nume;
        Simplificar();
    }

    public void Dividir(final fraccion A, final fraccion B) {
        nume = A.nume * B.deno;
        deno = A.deno * B.nume;
        if (deno < 0) {
            nume = nume * -1;
            deno = Math.abs(deno);
        }
        Simplificar();
    }

    public void Simplificar() {
        final int x = MCD();
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

    public String ToString() {
        String c = " C= ";
        c = "       " + getNume() + "\n" + c + getSigno() + " ---" + "\n       " + getDeno();
        return c;
    }

    public String AString() {
        final String c = "C= " + getSigno() + getNume() + "/" + getDeno();
        return c;
    }

    public String MString() {
        final String c = "" + getSigno() + getNume() + "/" + getDeno() + "  ";
        return nume == 0 ? "0     " : deno == 1 ? "" + nume + "     " : c;
    }

    public void copy(final fraccion a) {
        nume = a.nume;
        deno = a.deno;
    }

    public static void main(final String[] args) {
        final fraccion B = new fraccion(2, 1, '+');
        final fraccion A = new fraccion(4, 1, '+');
        final fraccion C = new fraccion();
        C.Suma(A, B);
        System.out.println(C.AString());
    }
}
