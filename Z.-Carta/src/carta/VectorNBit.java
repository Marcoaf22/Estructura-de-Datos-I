package carta;

import java.*;

/**
 *
 * @author Marcoaf
 */
public class VectorNBit {
    int v[];
    int cant;
    int nBits;

    public VectorNBit(int cant, int nBits) {
        v = new int[(cant * nBits) / 32];
        if ((cant * nBits) % 32 != 0) {
            v = new int[(cant * nBits) / 32 + 1];
        }
        this.cant = cant;
        this.nBits = nBits;
    }

    private int sacarEntero(int pos) {
        int nEnt = ((pos - 1) * nBits) / 32;

        return nEnt;
    }

    private int sacarNBit(int pos) {
        int nBit = ((pos - 1) * nBits) % 32 + 1;
        return nBit;

    }

    public void insertar(int elem, int pos) {// empieza desde 1
        if (pos <= cant) {
            int mask = (int) Math.pow(2, nBits) - 1;
            int nbit = sacarNBit(pos);
            int ent = sacarEntero(pos);
            mask = mask << (nbit - 1);
            v[ent] = v[ent] & ~mask;
            mask = mask & (elem << (nbit - 1));
            v[ent] = v[ent] | mask;
            if (nbit + nBits - 1 > 32) {
                ent++;
                mask = (int) Math.pow(2, nBits) - 1;
                mask = mask >>> (32 - (nbit - 1));
                v[ent] = v[ent] & ~mask;
                mask = mask & (elem >>> (32 - (nbit - 1)));
                v[ent] = v[ent] | mask;
            }
        }
    }

    public int sacarElemento(int pos) {
        if (pos <= cant) {
            int mask = (int) Math.pow(2, nBits) - 1;
            int nbit = sacarNBit(pos);
            int ent = sacarEntero(pos);
            mask = mask << (nbit - 1);
            mask = mask & v[ent];
            mask = mask >>> (nbit - 1);
            if (nbit + nBits - 1 > 32) {
                int mask2 = (int) Math.pow(2, nBits) - 1;
                mask2 = mask2 >>> (32 - (nbit - 1));
                mask2 = mask2 & v[ent + 1];
                mask2 = mask2 << (32 - (nbit - 1));
                mask = mask2 | mask;
            }
            return mask;
        }
        return 0;

    }

    @Override
    public String toString() {
        String x = "[";
        int aux;
        for (int i = 1; i <= cant; i++) {
            x = x + sacarElemento(i) + ",";
        }
        return x + "]";
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        VectorNBit a = new VectorNBit(2, 7);
        a.insertar(64, 1);
        a.insertar(78, 2);
        a.insertar(124, 3);
        int x = 116;
        System.out.println(String.valueOf(x));
        System.out.println(a.toString());
        System.out.println(a.sacarElemento(1));
        System.out.println(a.sacarElemento(2));
    }

}
