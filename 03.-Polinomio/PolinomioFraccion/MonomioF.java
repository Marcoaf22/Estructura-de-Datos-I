package PolinomioFraccion;

public class MonomioF {
    Fraccion coeficiente;
    int grado;

    public MonomioF() {
        coeficiente = new Fraccion();
        grado = -1;
    }

    public Fraccion getCoeficiente() {
        Fraccion aux = new Fraccion();
        aux.setNume((int) Math.abs(coeficiente.getNume()));
        aux.setDeno(coeficiente.getDeno());
        return aux;
    }

    public void setCoeficiente(Fraccion coeficiente) {
        this.coeficiente = coeficiente;
    }

    public int getGrado() {
        return grado;
    }

    public void setGrado(int grado) {
        this.grado = grado;
    }

    public char getSigno() {
        if (coeficiente.getSigno() != '-')
            return '+';
        else
            return '-';
    }

    public void setSigno(char signo) {
        if (signo == '+')
            coeficiente.setSigno('+');
        else
            coeficiente.setSigno('-');
    }

    public String toString() {
        String f = "";
        String s = "⁰¹²³⁴⁵⁶⁷⁸⁹";
        String e = "";
        // para conseguir en numeros pequeños el grado
        if (getGrado() > 9) {
            e = e + s.charAt(getGrado() / 10);
            e = e + s.charAt(getGrado() % 10);
        } else
            e = e + s.charAt(getGrado());
        // dependiendo del grado ponemos los numero o no
        if (getGrado() > 0) {
            if (getGrado() == 1) {
                s = coeficiente.totring() + "x ";
            } else
                s = "" + coeficiente.totring() + "x" + e + " ";
        } else
            s = coeficiente.totring() + " ";
        // Listo
        return s;
    }

    public static void main(String args[]) {
        MonomioF f = new MonomioF();
        Fraccion j = new Fraccion(2, 5, '-');
        f.setCoeficiente(j);
        f.setGrado(12);
        System.out.println(f.toString());
    }
}
