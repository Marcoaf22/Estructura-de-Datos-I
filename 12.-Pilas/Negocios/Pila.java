/*Una pila es una estructur de datos lineal cuyo comportamiento de operaciones de insercion
y eliminacion que utiliza la tecnica "lifo" puede ser repetitivo los datos esta estructura es 
muy trenzada cuando el comportamiento de la estructura esta determinada por la tecnica lifo y 
su representacion se la puede representar de la siguiente forma una columna el ultimo en entra 
es el primero en salir la recursion es una forma de programacion basada en pilas

*cuando se conoce el numero de elementos que se quiere guardar es mejor usar un array*

este tipo de estructuras tienen metodos especificos para su tratamiento como ser: 
push.-es insertar un elemento en la pila
Pop.-Eliminar y devuelve el ultimo elemento ingresado en la pila, 
Get.-solo Devuelve el ultimo elemento de la pila),
Vaciar*/
package Negocios;

/**
 *
 * @author Marcoaf
 */
public class Pila {

    int p[];
    int ultimo;

    public Pila(int cant) {
        p = new int[cant];
        ultimo = -1;
    }

    public boolean Vacio() {
        return ultimo == -1;
    }

    public boolean lleno() {
        return ultimo == p.length - 1;
    }

    public int get() {
        return p[ultimo];
    }

    public int size() {
        return ultimo + 1;
    }

    public void push(int ele) {
        if (lleno()) {
            System.out.println("Pila llena");
            System.exit(1);
        } else {
            p[++ultimo] = ele;
        }
    }

    public int pop() { //asumimos que el usuario lo usara correctamente 
        return p[ultimo--];
    }

    public Pila copiarPila() {
        return copiarPila(new Pila(size()));
    }

    public Pila copiarPila(Pila a) {
        if (!Vacio()) {
            int x = pop();
            a.insertarAlFinal(x);
            copiarPila(a);
            push(x);
        }
        return a;
    }

    public Pila copiar() {
        return copiarinv(new Pila(size()));
    }

    public Pila copiarinv(Pila p) {
        if (Vacio()) {
        } else {
            int x = pop();
            p.push(x);
            copiarinv(p);
            push(x);
        }
        return p;
    }

    public void intercambiarDe2() {//Intercambia de 2, sobrante abajo "ITERATIVO"
        Pila aux = new Pila(ultimo + 1);
        while (!Vacio()) {
            int x = pop();
            if (!Vacio()) {
                int y = pop();
                aux.push(y);
            }
            aux.push(x);
        }
        while (!aux.Vacio()) {
            push(aux.pop());
        }
    }

    public void intercambiarDe2Down() {//Intercambia de 2 , sobrante abajo
        if (size() > 1) {
            int x = pop();
            int y = pop();
            intercambiarDe2Down();
            push(x);
            push(y);
        }
    }

    public void intercambiarDe2Up() {//Intercambia de 2, sobrante arriba
        if ((ultimo + 1) % 2 == 1) {
            int aux = pop();
            intercambiarDe2Down();
            push(aux);
        } else {
            intercambiarDe2Down();
        }
    }

    //preguntar simetrico
    private boolean simetrico() {
        return simetrico(copiar());
    }

    public boolean simetrico(Pila p) {
        boolean sw = true;
        if (size() != p.size()) {
            return false;
        }
        if (Vacio()) {
            return sw;
        } else {
            int x = pop();
            int y = p.pop();
            if (x != y) {
                push(x);
                p.push(y);
                sw = false;
            } else {
                sw = simetrico(p);
                push(x);
                p.push(y);
            }
        }
        return sw;
    }

    public boolean Simetrico(Pila b) {
        int x;
        if (!simetrico(b)) {
            return false;
        } else {
            x = pop();
            int cb = b.size();
            for (int i = 0; i < cb - 1; i++) {
                for (int j = 0; j < cb; j++) {
                    push(b.pop());
                }
                if (x != get()) {
                    return false;
                } else {
                    for (int j = 0; j < cb; j++) {
                        b.push(pop());
                    }
                    b.push(x);
                    x = pop();
                }
            }
        }
        return true;
    }

    //elimina los elemento pares de la pila
    public void eliminarPares() {
        if (size() > 0) {
            int d = pop();
            eliminarPares();
            if ((d % 2) != 0) {
                push(d);
            }
        }
    }

    //Invierte la pila
    public void invertir() {
        if (size() > 1) {
            int x = pop();
            invertir();
            insertarAlFinal(x);
        }
    }

    //inserta al final osea abajo de la pila
    public void insertarAlFinal(int x) {
        if (Vacio()) {
            push(x);
        } else {
            int y = pop();
            insertarAlFinal(x);
            push(y);
        }
    }

//    Pila A     Pila B      Pila A   Pila B
//      |8|	|16|        |9|     |1|
//      |7|	|15|        |7|     |15|
//      |6|	|14|        |6|     |14|
//      |5|	|13|  ==>   |5|     |13|
//      |4|	|12|        |4|     |12|
//      |3|     |11|        |3|     |11|
//      |2| 	|10|        |2|     |10|
//      |1|      |9|        |16|     |8|
    
    //intercambia el principio de la pila 
    //por el final de la otra pila y viceversa
    public void intercambiarPU(Pila b) {
        int ai = pop();
        int bi = b.pop();
        int ca = size();
        while (!b.Vacio()) {
            push(b.pop());
        }
        b.push(ai);
        int bf = pop();
        while (!Vacio()) {
            b.push(pop());
        }
        int af = b.pop();
        push(bi);
        while (ca != 1) {
            push(b.pop());
            ca--;
        }
        push(bf);
        b.push(af);
    }

//    Pila A    Pila B      Pila A   Pila B
//      |8|	|16|        |16|     |8|
//      |7|	|15|        |15|     |7|
//      |6|	|14|        |14|     |6|
//      |5|	|13|  ==>   |13|     |5|
//      |4|	|12|        |4|      |12|
//      |3|     |11|        |3|      |11|
//      |2| 	|10|        |2|      |10|
//      |1|      |9|        |1|      |9|
    //Intercambia la mitad, Con una cantidad de elemento Par
    public void IntercambiarMitad() {
        for (int i = 0; i < size() / 2; i++) {
            insertarAlFinal(pop());
        }
    }

    //Intercambia la mitad de una pila con la mitad de otra cola
    public void IntercambiarMitadPC(Cola b) {
        int cb = b.cantidad();
        int ca = size();
        for (int i = 0; i < ca / 2; i++) {
            b.encolar(pop());
        }
        for (int i = 0; i < cb / 2; i++) {
            push(b.decolar());
        }
        for (int i = 0; i < b.cantidad() / 2; i++) {
            b.encolar(b.decolar());
        }
    }

    //tendria que eliminar los extremos pero solo elimina el final 
    public void EliminarExtremos() {
        if (!Vacio()) {
            if (size() == 1) {
                pop();
            } else {
                int x = pop();
                EliminarExtremos();
                push(x);
            }
        }
    }

    //elimina los extremos "ITERATIVO"
    public void eliminarExtremos() {
        Pila b = new Pila(size());
        pop();
        while (!Vacio()) {
            b.push(pop());
        }
        b.pop();
        while (!b.Vacio()) {
            push(b.pop());
        }
    }

    //Eliminar Repetidos
    public void eliminarRepetidos() {
        if (size() > 1) {
            for (int i = 1; i <= size(); i++) {
                int x = pop();
                int c = size();
                for (int j = 0; j < c; j++) {
                    if (x != get()) {
                        insertarAlFinal(pop());
                    } else {
                        pop();
                    }
                }
                insertarAlFinal(x);
            }
        }
    }

    /* A   B    A   B     
       4   8    8   4
       3   7 => 7   3
       2   6    2   6
       1   5    1   5 */
    //Invierte la mitad para arriba de 2 Pilas          
    public void invertirMitadDe2Pilas(Pila b) {
        int cb = b.size();
        int ca = size();
        int x;
        for (int i = 0; i < cb / 2; i++) {
            x = b.pop();
            insertarAlFinal(x);
        }
        for (int i = 0; i < ca / 2; i++) {
            x = pop();
            b.insertarAlFinal(x);
        }
        for (int i = 0; i < cb / 2; i++) {
            insertarAlFinal(pop());
            b.insertarAlFinal(b.pop());
        }
    }

    @Override
    public String toString() {
        String s = " Pila\n  |";
        for (int i = ultimo; i >= 0; i--) {
            s = s + p[i] + "|" + "\n  |";
        }
        return s.substring(0, s.length() - 1) + " °";
    }

    //Muestra 2 pilas
    public static void toString(Pila a, Pila b) {
        String s = "Pila A\tPila B\n  ";
        int ca = a.ultimo > b.ultimo ? a.ultimo : b.ultimo;
        for (int i = ca; i >= 0; i--) {
            if (i <= a.ultimo) {
                s = s + "|" + a.p[i] + "|\t";
            } else {
                s = s + "\t";
            }
            if (i <= b.ultimo) {
                s = s + "|" + b.p[i] + "|\n  ";
            } else {
                s = s + "\n  ";
            }
        }
        System.out.println(s);
    }

    public static void main(String[] args) {
        Pila a = new Pila(20);
        Pila b = new Pila(20);
        Cola c = new Cola(20);
        int n = 8;
        for (int i = 1; i <= n; i++) {
            a.push(i);
            b.push(i + n);
            c.encolar(i + n);
        }
        
//        System.out.println(a.toString());
//        System.out.println(c.toString());

//        a.intercambiarDe2Down();

        
//        --------------------------------
//        A tiene que tener capacidad de almacenar a todos 
//        los elementos de B y viceversa
        Pila.toString(a, b);
        a.intercambiarPU(b);
        Pila.toString(a, b);
//        --------------------------------

        
//        -----------------------------
//        A tiene que tener capacidad de almacenar a todos 
//        los elementos de B y viceversa, para Nro de elementos PareS=
//        Pila.toString(a, b);
//        a.invertirMitadDe2Pilas(b);
//        Pila.toString(a, b);
//        --------------------------------


//        System.out.println(a.toString());
//        System.out.println(c.toString());
    }
}
