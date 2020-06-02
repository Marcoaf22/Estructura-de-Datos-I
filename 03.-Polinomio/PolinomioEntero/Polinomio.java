package PolinomioEntero;

public class Polinomio {

    int dim;
    Monomio m[];

    public Polinomio() {
        m = new Monomio[10];
        for (int i = 0; i <= 9; i++) {
            m[i] = new Monomio();
        }
        dim = 0;
    }

    public void redimensionar() {
        Monomio n[] = new Monomio[m.length];
        System.arraycopy(m, 0, n, 0, m.length);
        Monomio m[] = new Monomio[n.length + 3];
        System.arraycopy(n, 0, m, 0, n.length);
    }

    public void insertar(int grado, int coeficiente, char signo) {
        if (coeficiente > 0) {
            if (m.length == dim + 1) {
                redimensionar();
            }

            if (!existeGrado(grado)) {
                m[dim].setGrado(grado);
                m[dim].setCoeficiente(coeficiente);
                m[dim].setSigno(signo);
                dim++;
                ordenar();
            } else {
                int pos = posGrado(grado);
                m[pos].setCoeficiente(coeficiente);
                m[pos].setSigno(signo);
            }
        }
    }

    public int getCantidad() {
        return dim;
    }

    ;
public void eliminar(int grado) {
        if (existeGrado(grado)) {
            int p = posGrado(grado);
            if (p == dim) {
                dim--;
            } else {
                for (int i = p; i <= dim; i++) {
                    m[i] = m[i + 1];
                }
                dim--;
            }
        }

    }

    private boolean existeGrado(int grado) {
        for (int i = 0; i <= dim; i++) {
            if (m[i].getGrado() == grado) {
                return true;
            }
        }
        return false;
    }

    public void ordenar() {
        int pos, n, aux;
        Monomio aux2;
        n = dim;
        while (n > 0) {
            aux = n - 1;
            pos = aux;
            while (aux > 0) {
                if (m[pos].grado > m[aux - 1].grado) {
                    pos = aux - 1;
                }
                aux--;
            }
            aux2 = m[pos];
            m[pos] = m[n - 1];
            m[n - 1] = aux2;
            n--;
        }

    }

    public void multiplicar(Polinomio a, Polinomio b) {
        dim = 0;
        int c;
        int g;
        for (int i = 0; i < a.dim; i++) {
            for (int j = 0; j < b.dim; j++) {
                c = a.m[i].coeficiente * b.m[j].coeficiente;
                g = a.m[i].getGrado() + b.m[j].grado;
                if (existeGrado(g)) {
                    m[posGrado(g)].coeficiente = m[posGrado(g)].coeficiente + c;
                } else {
                    m[dim].coeficiente = c;
                    m[dim].grado = g;
                    dim++;
                }
            }
        }
        ordenar();
    }

    private int posGrado(int x) {
        int i = 0;
        while (m[i].grado != x && i < dim) {
            i++;
        }
        if (m[i].grado == x) {
            return i;
        } else {
            return -1;
        }
    }

    public int valorX(int x) {
        int aux = 0;
        for (int i = 0; i < dim; i++) {
            aux = aux + m[i].coeficiente * (int) Math.pow(x, m[i].getGrado());
        }
        return aux;
    }

    public void suma(Polinomio a, Polinomio b) {
        dim = 0;
        int pos, i = 0;
        while (i < a.dim) {
            pos = b.posGrado(a.m[i].grado);
            if (pos > -1) {
                m[dim].coeficiente = b.m[pos].coeficiente + a.m[i].coeficiente;
                m[dim].grado = a.m[i].grado;
                dim++;
            } else {
                m[dim] = a.m[i];
                dim++;
            }
            i++;
        }
        i = 0;
        while (i < b.dim) {
            if (!a.existeGrado(b.m[i].grado)) {
                m[dim] = b.m[i];
                dim++;
            }
            i++;
        }
        ordenar();

    }

    public void resta(Polinomio a, Polinomio b) {
        dim = 0;
        int pos, i = 0;
        while (i < a.dim) {
            pos = b.posGrado(a.m[i].grado);
            if (pos > -1) {
                m[dim].coeficiente = a.m[i].coeficiente - b.m[pos].coeficiente;
                m[dim].grado = a.m[i].grado;
                dim++;
            } else {
                m[dim] = a.m[i];
                dim++;
            }
            i++;
        }
        i = 0;
        while (i < b.dim) {
            if (!a.existeGrado(b.m[i].grado)) {
                b.m[i].setSigno(b.m[i].coeficiente >= 0 ? '-' : '+');
                m[dim] = b.m[i];
                dim++;
            }
            i++;
        }
        ordenar();
    }

    public void Coeficiente0() {
        int i = 0;
        while (m[i].coeficiente != 0 && i < dim) {
            i++;
        }
        eliminar(m[i].grado);
    }

    public String toString() {
        String f = "";
        String s = "⁰¹²³⁴⁵⁶⁷⁸⁹";
        for (int i = 0; i <= dim - 1; i++) {
            String e = "";
            if (m[i].getGrado() > 9) {
                e = e + s.charAt(m[i].getGrado() / 10);
                e = e + s.charAt(m[i].getGrado() % 10);
            } else {
                e = e + s.charAt(m[i].getGrado());
            }
            if (m[i].grado > 1) {
                if (m[i].coeficiente == 1) {
                    f = f + m[i].getSigno() + "x" + e + " ";
                } else {
                    f = f + m[i].getSigno() + m[i].getCoeficiente() + "x" + e + " ";
                }
            } else if (m[i].grado == 0) {
                f = f + m[i].getSigno() + m[i].getCoeficiente() + " ";
            } else {
                f = f + m[i].getSigno() + m[i].getCoeficiente() + "x ";
            }
        }
        return f;
    }

    /*public String toString(){
    String f="";
    for (int i=0;i<dim;i++){
        if(m[i].getCoeficiente()!=0){
            if(m[i].getGrado()!=0){
                if (m[i].getGrado()!=1){
                f=f+m[i].getSigno()+m[i].getCoeficiente()+"X^"+m[i].getGrado()+" ";
                }
                else{
                    f=f+m[i].getSigno()+m[i].getCoeficiente()+"X ";
                }
            }else{
            f=f+m[i].getSigno()+m[i].getCoeficiente()+" ";
            }
        }
    }
    return f;
}
     */
    public static void main(String[] args) {
        Polinomio p = new Polinomio();
        p.insertar(0, 9, '+');
        p.insertar(6, 9, '+');
        p.insertar(3, 4, '+');
        p.insertar(4, 2, '-');
        Polinomio q = new Polinomio();
        q.insertar(5, 5, '-');
        q.insertar(6, 5, '+');
        q.insertar(3, 9, '-');

        Polinomio r = new Polinomio();
        r.resta(p, q);
        System.out.println("Polinomio p : " + p.toString());
        System.out.println("Polinomio q: " + q.toString());
        System.out.println("Resta de p - q : " + r);
    }

}
