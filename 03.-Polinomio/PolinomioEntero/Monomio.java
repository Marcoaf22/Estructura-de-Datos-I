package PolinomioEntero;

public class Monomio {

    int coeficiente;
    int grado;

    public Monomio() {
        coeficiente = 0;
        grado = -1;
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
        if (signo == '+')
            coeficiente = Math.abs(coeficiente);
        else
            coeficiente = -Math.abs(coeficiente);
    }

    @Override
    public String toString() {
        return "" + this.coeficiente + "^" + this.grado;

    }

}
