package Negocios;

public class Conjunto {

    int C[];
    int dim;

    public Conjunto() {
        C = new int[3];
        dim = 0;
    }

    public void Insertar(int elemento) {//profe
        if (!Pertenece(elemento)) {
            if (dim == C.length) //preguntamos si el numero de elementos es igual al delimitado
            {
                Redimensionar();  //volvemos a aumenar el tamaño del conjunto
            }
            C[dim++] = elemento;  //si no solo colocamos
            //dim++;
        }
    }

    public void Redimensionar() {
        int[] D = new int[dim];
        System.arraycopy(C, 0, D, 0, dim);
        C = new int[dim + 10];
        System.arraycopy(D, 0, C, 0, dim);
    }

    public boolean Pertenece(int elemento) {
        for (int i = 0; i < dim; i++) {
            if (C[i] == elemento) {
                return true;
            }
        }
        return false;
    }

    public void Eliminar(int elemento) {
        int s = Posicion(elemento);
        if (s >= 0) {
            if (s + 1 == dim) {//podia habernos dado error
                dim--;
            } else {
                for (int i = s; i < dim; i++) {
                    C[i] = C[i + 1];
                }
                dim--;
            }
        }
    }

    public int Posicion(int elemento) {
        for (int i = 0; i < dim; i++) {
            if (C[i] == elemento) {
                return i;
            }
        }
        return -1;
    }

    public boolean pertenece(int ele) {//profe /*Error cuando busca un elemento no existente cuando el vector esta lleno*/
        int i = 0;
        while ((i <= dim) && (C[i] != ele)) {
            i++;
        }
        return (i <= dim);
    }

    public void eliminar(int ele) {//profe no sirve
        if (!Vacio()) {
            if (Pertenece(ele)) {
                int i = 0;
                while (C[i] != ele) {
                    for (int j = i; j < dim; j++) {
                        C[j] = C[j + 1];
                    }
                    dim--;
                }
            }
        }
    }

    public int posicion(int elemento) {//profe sorpresa no sirve
        int i = 0;
        while (i < dim && C[i] != elemento) {
            i++;
        }
        if (C[i] == elemento) {
            return i;
        }
        return 0;
    }

    public boolean Vacio() {
        return dim == 0;
    }

    public String toString() {
        String f = "{";
        for (int i = 0; i < dim; i++) {
            if (i + 1 == dim) {
                f = f + Integer.toString(C[i]);
                //System.out.print(+C[i]+"}");   
            } else {
                f = f + Integer.toString(C[i]) + ",";
                // System.out.print(C[i]+",");
            }
        }
        return f + "}";
    }

    public void Union(Conjunto a, Conjunto b) {//profe
        for (int i = 0; i < a.dim; i++) {
            Insertar(a.C[i]);//inserta a en el nuevo conjunto
        }
        for (int j = 0; j < b.dim; j++) {
            Insertar(b.C[j]);//inserta solo si no hay, caso contraio no
        }
    }

    public void Interseccion(Conjunto a, Conjunto b) {
        for (int i = 0; i < a.dim; i++) {
            if (b.Pertenece(a.C[i])) {
                Insertar(a.C[i]);
            }
        }
    }

    public void Diferencia(Conjunto a, Conjunto b) {
        for (int i = 0; i < a.dim; i++) {
            if (!b.Pertenece(a.C[i])) {
                Insertar(a.C[i]);
            }
        }
    }

    public void DiferenciaS(Conjunto a, Conjunto b) {
        for (int i = 0; i < a.dim; i++) {
            if (!b.Pertenece(a.C[i])) {
                Insertar(a.C[i]);
            }
        }
        for (int i = 0; i < b.dim; i++) {
            if (!a.Pertenece(b.C[i])) {
                Insertar(b.C[i]);
            }
        }
    }

    public boolean Subconjunto(Conjunto b) {
        if (b.dim > 0) {
            for (int i = 0; i < dim; i++) {
                if (!b.Pertenece(C[i])) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public void Cvacio() {
        dim = 0;
    }

    public static void main(String[] args) {
        Conjunto a;
        Conjunto b;
        Conjunto c;
        c = new Conjunto();
        b = new Conjunto();
        a = new Conjunto();
        a.Insertar(2);
        a.Insertar(5);
        a.Insertar(8);
//        a.Insertar(4);
//        a.Insertar(5);
//        a.Insertar(6);
//        a.Insertar(7);
//        a.Insertar(8);
//        a.Insertar(9);
//        a.Insertar(10);
//        a.Insertar(11);
//        a.Insertar(12);
//        a.Insertar(13);
//        a.Insertar(14);
//        a.Insertar(15);
        System.out.println(a.toString());
//        System.out.println(a.pertenece(4));  no sirve
        System.out.println(a.Pertenece(4));
//        a.eliminar(3); no sirve
        a.Eliminar(3);
        System.out.println(a.Posicion(3));
        System.out.println(a.toString());
//        System.out.println(a.C.length);        
        b.Insertar(1);
        b.Insertar(5);
        b.Insertar(3);
        b.Insertar(9);
//        b.Insertar(3);
//        a.eliminar(2);
        System.out.println(b.toString());
//        a.Insertar(2);
//        System.out.println(a.toString());
//        System.out.println(b.toString());
//        System.out.println(b.Vacio());
//        System.out.println(a.Vacio());
        c.Union(a, b);
        System.out.println(c.toString());
        // System.out.println(c.toString());
//        c.Interseccion(a, b);
//        System.out.println(c.toString());

    }
}
