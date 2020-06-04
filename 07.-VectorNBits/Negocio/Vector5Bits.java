package Negocio;

public class Vector5Bits {

    int v[];
    int Cant;
    int Nbits;
//5bits=Elemento Max 31

    public Vector5Bits(int cant) {//numero elemento de 5 bits
        Cant = cant;
        int Nbits = cant * 5;//numero de bits que ocuparemos 
        int NE = Nbits / 32;//numero de int que necesitaremos
        if (Nbits % 32 != 0) {
            NE++;
        }
        v = new int[NE];
    }

    public void Insertar(int pos, int elemento) {//No insertamos en pos masyores a la inicializada
        if (pos <= Cant && elemento < Math.pow(2, 5)) {//ni elementos mayores ala maximo del nbits
            int elemento1 = elemento; //copia por si la posicion esta entre 2 int
            int mask = (int) Math.pow(2, 5) - 1;
            int Nbit = Fnbit(pos);
            int Nent = Fnint(pos);
            mask = mask << Nbit;
            mask = ~mask;
            v[Nent] = v[Nent] & mask;//limipiamos lo que habia
            elemento = elemento << Nbit;//llevamos el elemento a colocar (max 31) hasta la posicion
            v[Nent] = v[Nent] | elemento;//y colocamos el elemento en su posiccion
            if (Nbit + 5 > 32) {//sumamos  5 mas Fnit y si es mayor que 32 "falta"
                int Nudbitfal = Nbit + 5 - 32;//sacamos los bit faltante
                int mask1 = (int) Math.pow(2, 5) - 1;
                mask1 = mask1 >>> (5 - Nudbitfal);//llevamos la mascara a los bit faltantes
                mask1 = ~mask1;//preparamos la mascara para limpiar
                v[Nent + 1] = v[Nent + 1] & mask1;//y limpiamos
                elemento1 = elemento1 >>> (5 - Nudbitfal);//la copia lo movemos los bit faltantes
                v[Nent + 1] = v[Nent + 1] | elemento1;//y procedemos a pegar
            }
        }
    }

    public int get(int pos) {
        if (pos <= Cant) {
            int mask = (int) Math.pow(2, 5) - 1;
            int Nbit = Fnbit(pos);
            int Nent = Fnint(pos);
            mask = mask << Nbit;//llevamos hasta la pos 
            mask = mask & v[Nent];//y solo obtenemos su valor
            mask = mask >>> Nbit;//lo llevamos al principio para obetener su valor real
            if (Nbit + 5 > 32) {//si el elemento estaba entre 2 int
                int Nbf = Nbit + 5 - 32;//tenemos lo que falta
                int mask1 = (int) Math.pow(2, 5) - 1;//creamos los unos del tamaño
                mask1 = mask1 >>> (5 - Nbf);//lo recorremos para obtener los bit faltante
                mask1 = mask1 & v[Nent + 1];//y obtenemos los bit faltantes del sgt int
                mask1 = mask1 << (5 - Nbf);//lo recorremos para juntarlo con la primera mask donde esta los otros
                mask = mask | mask1;//y juntamos los bits que estaban separados
            }
            return (mask);
        }
        return -1;
    }

    public int Fnbit(int pos) {//nos devolvera la posicion osea su bit del int(desde 0) 
        return (((pos - 1) * 5) % 32);
    }

    public int Fnint(int pos) {//el valor de la pos de (5bit)  
        return (((pos - 1) * 5) / 32);
    }

    public String toString() {
        String s = "";
        for (int i = 1; i <= Cant; i++) {
            s = s + get(i) + ",";
        }
        s = s.length() > 0 ? "[" + s.substring(0, s.length() - 1) + "]" : "[]";
        return s;

    }

    public static void main(String args[]) {
        Vector5Bits a = new Vector5Bits(9);
        // a.Insertar(3, 17);   
        a.Insertar(0, 32);
        a.Insertar(4, 13);
        a.Insertar(7, 10);
        int s = a.get(7);
        System.out.println(a.toString());

    }
}
