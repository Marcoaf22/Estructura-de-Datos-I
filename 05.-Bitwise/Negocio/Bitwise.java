package Negocio;

public class Bitwise {

    long x;

    public Bitwise() {
        x = 0;
    }

    public void Set1(int pos) { // Enciende un bit en el bitwise
        if (pos <= 64) {
            long mascara = 1;
            mascara <<= pos - 1;
            x |= mascara;
        }
    }

    public long getvalor() {
        return x;
    }

    public void Set0(int pos) {// apaga el bit en el entero x
        if (pos <= 64) {
            long mascara = 1;
            mascara <<= pos - 1;
            x &= ~mascara; // una vez llevado hacemos not ala mascara y despues un and al x
        }
    }

    public int getBit(int pos) {// retorna si el bit en la pos es 0,1
        if (pos <= 64) {
            long mascara = 1;
            mascara <<= pos - 1;// llevamos ala posicion deseada la mascara
            mascara &= x;// y aplicamos un and
            mascara >>>= pos - 1; // despues de aplicar el and sabremos si el bit en la pos es 0,1 lo llevamos de
                                  // nuevo al principio
            return (int) mascara;
        }
        return 0;
    }

    public void setX(long n) {
        x = n;
    }

    public String toString() {
        String s = " X =";
        for (int i = 64; i >= 1; i--) {
            s = s + " " + getBit(i);
        }
        return s + " = " + x;
    }

    public int Cant1() {
        int c = 0;
        for (int i = 1; i <= 64; i++) {
            if (getBit(i) == 1) {
                c++;
            }
        }
        return c;
    }

    public static void main(String[] args) {
        Bitwise x = new Bitwise();
        x.Set1(2);
        x.Set1(4);
        x.Set1(8);
        x.Set0(8);
        x.Set1(64);
        System.out.println(x.toString());
        System.out.println(x.Cant1());
        System.out.println("Esto sale en pantalla\nbuenos dias");
    }
}
