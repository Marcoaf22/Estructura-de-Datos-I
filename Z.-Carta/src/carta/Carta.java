package carta;

import carta.VectorNBit;

/**
 *
 * @author Marcoaf
 */
public class Carta {

    private VectorNBit v;
    int cant;
    int dim;

    public Carta(int cant) {
        v = new VectorNBit(cant, 7);
        this.cant = cant;
        dim = 0;
    }

    public int getDim() {
        return dim;
    }

    public void insertarCarta(int color, int simbolo, int numero) {
        if (dim < cant) {
            dim++;
            int aux = (color << 6) | (simbolo << 4) | numero;
            v.insertar(aux, dim);
        }
    }

    public int getNumber(int pos) {
        return v.sacarElemento(pos) & 0xf;
    }
    
    public int getSymbol(int pos) {
        return (v.sacarElemento(pos) & ~0x4F) >>> 4;
    }

    public String getSymbolo(int pos) {
        String s = "";
        switch (getSymbol(pos)) {
            case 0:
                return "♣";

            case 1:
                return "♠";

            case 2:
                return "♥";

            case 3:
                return "♦";

            default:
                return "";

        }
    }

    public int getCarta(int pos) {
        return v.sacarElemento(pos);
    }

    public String toString() {
        String h = "";
        for (int i = 1; i <= dim; i++) {
            h = h + getSymbolo(i) + (getNumber(i) + 1) + ",";
        }
        h = h.length() > 0 ? h.substring(0, h.length() - 1) : "";
        h = "{" + h + "}";
        return h;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Carta a = new Carta(8);
        a.insertarCarta(1, 3, 4);
        a.insertarCarta(0, 2, 3);
        System.out.println(a.getNumber(2));
        System.out.println(a.getSymbol(2));
        System.out.println(a.toString());
        // TODO code application logic here
    }

}
