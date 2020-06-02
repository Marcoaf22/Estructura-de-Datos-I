package PolinomioFraccion;

public class PolinomioF {

    MonomioF[] m;
    int dim;

    public PolinomioF() {
        m = new MonomioF[10];
        dim = 0;
        for (int i = 0; i < 9; i++) {
            m[i] = new MonomioF();
        }
    }

    public void insertar(int grado, Fraccion coeficiente) {
        if (coeficiente.deno != 0) {
            if (!existeGrado(grado)) {
                m[dim].setCoeficiente(coeficiente);
                m[dim].setGrado(grado);
                dim++;
            } else {
                m[posGrado(grado)].coeficiente = coeficiente;
            }
            ordenar();
        }
    }

    public void redimensionar() {
        MonomioF n[] = new MonomioF[m.length];
        System.arraycopy(m, 0, n, 0, m.length);
        MonomioF m[] = new MonomioF[n.length + 5];
        System.arraycopy(n, 0, m, 0, n.length);
    }

    public int getCantidad() {
        return dim;
    }

    public void eliminar(int grado) {
        if (existeGrado(grado)) {
            int p = posGrado(grado);
            if (p == dim) {
                dim--;
            } else {
                for (int i = p; i < dim; i++) {
                    m[i] = m[i + 1];
                }
                dim--;
            }
        }
    }

    private boolean existeGrado(int grado) {
        for (int i = 0; i < dim; i++) {
            if (m[i].getGrado() == grado) {
                return true;
            }
        }
        return false;
    }

    public void ordenar() {
        int pos, n, aux;
        MonomioF aux2;
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

    public void Coeficiente0() {
        int i = 0;
        while ((m[i].coeficiente.nume != 0) && (i < dim)) {
            i++;
        }
        eliminar(m[i].grado);
    }

    private int posGrado(int x) {
        int i = 0;
        while (m[i].getGrado() != x && i < dim) {
            i++;
        }
        return m[i].getGrado() == x ? i : -1;
//      if(m[i].grado==x){
//          return i;
//      }else{
//          return -1;
//      }
    }

    public int valorX(int x) {
        int aux = 0;
        for (int i = 0; i < dim; i++) {
            //    aux=aux+m[i].coeficiente*(int)Math.pow(x,m[i].getGrado());
        }
        return aux;
    }

    public void suma(PolinomioF a, PolinomioF b) {
        dim = 0;
        int pos, i = 0;
        while (i < a.dim) {
            pos = b.posGrado(a.m[i].grado);
            if (pos > -1) {
                m[dim].coeficiente.Suma(a.m[i].coeficiente, b.m[pos].coeficiente);
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
                m[dim].setCoeficiente(b.m[i].getCoeficiente());
                dim++;
            }
            i++;
        }
        ordenar();

    }

    public void resta(PolinomioF a, PolinomioF b) {
        dim = 0;
        int pos, i = 0;
        while (i < a.dim) {
            pos = b.posGrado(a.m[i].grado);
            if (pos > -1) {
                m[dim].coeficiente.Resta(a.m[i].coeficiente, b.m[pos].coeficiente);
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
                m[dim].setCoeficiente(b.m[i].getCoeficiente());
                m[dim].setGrado(b.m[i].getGrado());
                m[dim].setSigno(b.m[i].getSigno() == '+' ? '-' : '+');
                dim++;
            }
            i++;
        }
        Coeficiente0();
        ordenar();

    }

    public void multiplicar(PolinomioF a, PolinomioF b) {
        dim = 0;
        int c;
        int g;
        for (int i = 0; i < a.dim; i++) {
            for (int j = 0; j < b.dim; j++) {
                Fraccion aux = new Fraccion();
                aux.Multiplicar(a.m[i].getCoeficiente(), b.m[j].getCoeficiente());
                g = a.m[i].getGrado() + b.m[j].grado;
                if (existeGrado(g)) {
                    m[posGrado(g)].coeficiente.Suma(aux, m[posGrado(g)].coeficiente);
                } else {
                    m[dim].coeficiente = aux;
                    m[dim].grado = g;
                    dim++;
                }
            }
        }
        ordenar();
    }

    public void copia(PolinomioF a, PolinomioF b) {
        b.dim = 0;
        for (int i = 0; i < a.dim; i++) {
            b.m[i] = a.m[i];
            b.dim++;
        }
    }

    public void dividir(PolinomioF a, PolinomioF b) {
        dim = 0;
        PolinomioF sa = new PolinomioF();
        for (int i = 0; i < a.dim; i++) {
            sa.m[i] = a.m[i];
            sa.dim++;
        }
        int ya = 2;
        if (a.m[0].grado >= b.m[0].grado) {
            while (sa.getCantidad() > 0 && (sa.m[0].getGrado() >= b.m[0].grado)) {
                Fraccion mul = new Fraccion();
                mul.Dividir(sa.m[0].coeficiente, b.m[0].coeficiente);
                PolinomioF w = new PolinomioF();
                w.m[0].coeficiente = mul;
                w.m[0].grado = sa.m[0].grado - b.m[0].grado;
                w.dim++;
                m[dim] = w.m[0];
                dim++;
                System.out.println(w.toSstring() + "El cociente");
                PolinomioF Operacion = new PolinomioF();
                Operacion.multiplicar(w, b);
                System.out.println("dividendo=" + sa.toSstring());
                System.out.println("las resta=" + Operacion.toSstring());
                PolinomioF resultado = new PolinomioF();
                resultado.resta(sa, Operacion);
                System.out.println("resultado=" + resultado.toSstring());
                copia(resultado, sa);
            }
        } else {
            System.out.println("No se puede dividir, el divisor es de mayor grado que el dividendo");
            System.exit(1);
        }
    }

    public String toSstring() {
        String f = "";
        for (int i = 0; i < dim; i++) {
            f = f + m[i].toString();
        }
        return f;
    }

    public static void main(String[] args) {
        Fraccion a = new Fraccion(14, 2, '+');
        Fraccion b = new Fraccion(10, 2, '+');
        Fraccion c = new Fraccion(8, 3, '-');
        PolinomioF x = new PolinomioF();
        PolinomioF y = new PolinomioF();
        PolinomioF z = new PolinomioF();
        y.insertar(2, b);
        y.insertar(4, c);
        x.insertar(3, a);
        x.insertar(2, c);
        x.insertar(8, b);
        System.out.println("polinomio x: " + x.toSstring());
        System.out.println("polinomio y: " + y.toSstring());
        System.out.println();
        z.resta(x, y);
        System.out.println("Resta x-y : " + z.toSstring() + "\n");
    }

}
