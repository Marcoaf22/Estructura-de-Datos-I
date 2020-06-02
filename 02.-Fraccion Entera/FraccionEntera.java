public class FraccionEntera {

    int entero;// El entero es el que tendra el signo
    int num;
    int den;

    public FraccionEntera() {
        entero = 0;
        num = 0;
        den = 1;
    }

    public void setEntero(int en) {
        entero = en;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setDen(int den) {
        this.den = den;
    }

    public void setSigno(char s) {
        if (s == '-') {
            entero = (-1) * Math.abs(entero);
        } else {
            entero = Math.abs(entero);
        }
    }

    public int getNum() {
        return num;
    }

    public int getEntero() {
        return Math.abs(entero);
    }

    public int getDen() {
        return den;
    }

    public char getSigno() {
        return (entero >= 0) ? '+' : '-';
    }

    public void aFraccion() {
        num = entero >= 0 ? entero * den + num : entero * den - num; // por que "-" se restaria
        entero = 0;
    }

    public void aMixto() {
        entero = num / den;
        num = Math.abs(num % den);
    }

    public void Suma(FraccionEntera A, FraccionEntera B) {
        A.aFraccion();
        B.aFraccion();
        den = A.getDen() * B.getDen();
        num = (A.num * B.getDen()) + (A.getDen() * B.num);
        Simplificar();
        aMixto();
    }

    public int MCD() {
        int u = Math.abs(num);
        int v = Math.abs(den);
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

    public void Simplificar() {
        int x = MCD();
        num = num / x;
        den = den / x;
    }

    public void Resta(FraccionEntera A, FraccionEntera B) {
        A.aFraccion();
        B.aFraccion();
        den = A.getDen() * B.getDen();
        num = (A.num * B.getDen()) - (A.getDen() * B.num);
        Simplificar();
        aMixto();
    }

    public void Multiplicar(FraccionEntera A, FraccionEntera B) {
        A.aFraccion();
        B.aFraccion();
        den = A.getDen() * B.getDen();
        num = A.num * B.num;
        Simplificar();
        aMixto();
    }

    public void Dividir(FraccionEntera A, FraccionEntera B) {
        A.aFraccion();
        B.aFraccion();
        num = A.num * B.den;
        den = A.den * B.num;
        if (den < 0) {
            num = num * -1;
            den = Math.abs(den);
        }
        Simplificar();
        aMixto();
    }

    public float aDecimal() {
        return (float) (entero * den + num) / den;
    }

    public String toString() {
        String c = "C= " + getSigno() + getEntero() + " " + getNum() + "/" + getDen();
        return c;
    }

    public static void main(String[] args) {
        FraccionEntera f = new FraccionEntera();
        FraccionEntera q = new FraccionEntera();
        FraccionEntera d = new FraccionEntera();
        f.setDen(2);
        f.setNum(2);
        f.setEntero(0);
        f.setSigno('-');
        System.out.println(f.getSigno());
        q.setDen(2);
        q.setNum(2);
        q.setEntero(5);
        q.setSigno('-');
        System.out.println(f.toString());
        System.out.println(q.toString());
        f.aFraccion();
        q.aFraccion();
        System.out.println(f.toString());
        System.out.println(q.toString());
        d.Multiplicar(f, q);
        System.out.println(d.toString());
    }
}
