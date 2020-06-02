package PolinomioEntero;

public class Monomio {

    int coeficiente;
    int grado;

    public Monomio() {
        coeficiente = 0;
        grado = -1;
    }

    public Monomio(int coeficiente, int grado, char signo) {
        this.coeficiente = signo == '+' ? coeficiente : -coeficiente;
        this.grado = grado;
    }

    public int getCoeficiente() {
        return Math.abs(coeficiente);
    }

    public void setCoeficiente(final int coe) {
        this.coeficiente = coe;
    }

    public int getGrado() {
        return grado;
    }

    public void setGrado(final int grado) {
        this.grado = grado;
    }

    public char getSigno() {
        return (coeficiente >= 0) ? '+' : '-';
    }

    public void setSigno(final char signo) {
        if (signo == '+') {
            coeficiente = Math.abs(coeficiente);
        } else {
            coeficiente = -Math.abs(coeficiente);
        }
    }

    public String toStringP() {
        String f = "";
        String s = "⁰¹²³⁴⁵⁶⁷⁸⁹";
        String e = "";
        // para conseguir en numeros pequeños el grado
        if (getGrado() > 9) {
            e = e + s.charAt(getGrado() / 10);
            e = e + s.charAt(getGrado() % 10);
        } else {
            e = e + s.charAt(getGrado());
        }
        // dependiendo del grado ponemos los numero o no
        if (getGrado() > 0) {
            if (getGrado() == 1) {
                s = coeficiente + "x ";
            } else {
                s = "" + coeficiente + "x" + e + " ";
            }
        } else {
            s = coeficiente + " ";
        }
        // Listo
        return s;
    }

    public static void main(String args[]) {
        Monomio a = new Monomio(2, 3, '-');
        System.out.println(a.toStringP());
    }

}
