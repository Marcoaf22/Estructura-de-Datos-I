/*implementar el tda Conjunto optimizando su estructura mediante el uso de bitwise
El elemento 0 nos devuelve la posicion del 32, Cuando creamos un ConuuntoBit de un determinado
numero de elementos, solo podremos insertar elemento hasta ese numero sin repetir
*/

package Negocios;

public class ConjuntoBit {

    Bitwise[] C;
    int Cant;

    public ConjuntoBit(int cant) {
    //div 32 y vemos la cantidad de int que ocuparemos  
    //Mod si nos sobra aumentamos uno
        C = new Bitwise[(cant % 32) != 0 ? (cant / 32) + 1 : cant / 32];
        for (int i = 0; i < C.length; i++) {
            C[i] = new Bitwise();
        }
        Cant = cant;   //Limite maximo de nuestro conjunto
    }

    public void Insertar(int elemento) {
        if (elemento > Cant) {
            Redimensionar(elemento);
        }
        if (elemento > 0) {
            C[Fnbw(elemento)].Set1(Fnbit(elemento));
        }
    }

    public void Vacio() {
        for (int i = 0; i < C.length; i++) {
            C[i].x = 0;
        }
    }

    public void Redimensionar(int Nlimite) {
        ConjuntoBit d = new ConjuntoBit(Cant);
        for (int i = 0; i < d.C.length; i++) {
            d.C[i] = C[i];
        }
        C = new Bitwise[(Nlimite % 32) != 0 ? (Nlimite / 32) + 1 : Nlimite / 32];
        for (int i = 0; i < C.length; i++) {
            C[i] = new Bitwise();
            C[i] = i < d.C.length ? d.C[i] : C[i];
        }
        Cant = Nlimite;
    }

    public void Eliminar(int elemento) {
        if (elemento <= Cant && elemento > 0) //si aceptamos 0 es igual a 32
        {
            C[Fnbw(elemento)].Set0(Fnbit(elemento));
        }
    }

    public boolean pertenece(int elemento) { //si elemento pertenece al conjunto
        return (C[Fnbw(elemento)].getBit(Fnbit(elemento)) == 1);
    }

    public int Fnbit(int pos) {  //devuelve la pos del bit en el bitwise
        return ((pos - 1) % 32) + 1;  //forzamos
    }

    public int Fnbw(int pos) {   //Devuelve el int del elemento al que corresponde
        return ((pos - 1) / 32);
    }

    public int getDim() {
        return C.length;
    }

    public int getCant() {
        return Cant;
    }

    public boolean Subconjunto(ConjuntoBit b) {
        int i = 1;
        while (i <= Cant) {
            if (C[Fnbw(i)].getBit(Fnbit(i)) == 1) {
                if (!b.pertenece(i)) {
                    return false;
                }
            }
            i++;
        }
        return (i > Cant);
    }

    public String toString() {
        String s = "";
        for (int i = 1; i <= Cant; i++) {
            if (pertenece(i)) {
                s = s + i + ",";
            }
        }
        s = s.length() > 0 ? "{" + s.substring(0, s.length() - 1) + "}" : "{}";
        return s;
    }
    //c debe tener la dimension del mayor entre a y b

    public void union(ConjuntoBit a, ConjuntoBit b) {
        if (a.Cant > b.Cant) {
            b.Redimensionar(Cant);
        } else {
            a.Redimensionar(Cant);
        }
        // a.dim()>b.dim():a.Redimensionar(Cant);
        int nbw = a.getDim() > b.getDim() ? a.getDim() : b.getDim();
        for (int i = 0; i < C.length; i++) {
            C[i].x = a.C[i].x | b.C[i].x;
        }
    }

    public void Union(ConjuntoBit a, ConjuntoBit b) {
        if (a.Cant > b.Cant) {
            b.Redimensionar(a.Cant);
        } else {
            a.Redimensionar(b.Cant);
        }
        Redimensionar(a.Cant);
        for (int i = 0; i < C.length; i++) {
            C[i].x = a.C[i].x | b.C[i].x;
        }
    }

    public void Interseccion(ConjuntoBit a, ConjuntoBit b) {
        if (a.Cant > b.Cant) {
            b.Redimensionar(Cant);
        } else {
            a.Redimensionar(Cant);
        }
        Redimensionar(a.Cant);
        for (int i = 0; i < C.length; i++) {
            C[i].x = a.C[i].x & b.C[i].x;
        }
    }

    public void Diferencia(ConjuntoBit a, ConjuntoBit b) {
        if (a.Cant > b.Cant) {
            b.Redimensionar(Cant);
        } else {
            a.Redimensionar(Cant);
        }
        Redimensionar(a.Cant);
        for (int i = 0; i < C.length; i++) {
            C[i].x = a.C[i].x & ~b.C[i].x;
        }
    }

    public void DiferenciaS(ConjuntoBit a, ConjuntoBit b) {
        if (a.Cant > b.Cant) {
            b.Redimensionar(Cant);
        } else {
            a.Redimensionar(Cant);
        }
        Redimensionar(a.Cant);
        for (int i = 0; i < C.length; i++) {
            C[i].x = a.C[i].x ^ b.C[i].x;
        }
    }

    public static void main(String[] args) {
        ConjuntoBit a = new ConjuntoBit(60);
        a.Insertar(33);
        a.Insertar(1);
        a.Insertar(34);
        a.Insertar(32);
        ConjuntoBit b = new ConjuntoBit(60);
        b.Insertar(35);
        b.Insertar(2);
        ConjuntoBit c = new ConjuntoBit(10);
        c.union(a, b);
        System.out.println(a.toString());
        System.out.println(b.toString());
        System.out.println(c.toString());

    }
}

//public void interseccion(ConjuntoBit a ,ConjuntoBit b){
//    //c debe tener la dimension del mayor entre a y b
//    int NE=a.Cant/32;
//    if (a.Cant%32!=0){
//        NE++;
//    }
//    for (int i=0;i<NE;i++){
//     C[i].x=a.C[i].x & b.C[i].x;
//    }
//}
//     ConjuntoBit n[]=new ConjuntoBit[3];
//     for (int i=0;i<n.length;i++){
//         n[i]=new ConjuntoBit(32);
//     } 
//     ConjuntoBit e[]=new ConjuntoBit[3];
//     for (int i=0;i<e.length;i++){
//         e[i]=new ConjuntoBit(32);
//     }
//     System.arraycopy(n, 0, e, 0,2);
 //    System.out.println(a.toString());
