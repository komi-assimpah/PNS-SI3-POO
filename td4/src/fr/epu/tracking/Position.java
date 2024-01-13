package fr.epu.tracking;

public class Position {
    private double x;
    private double y;

    // -------------------------- getters ------------
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    //-------------- constructors ----------------
    public Position(double ix, double iy) {
        this.x=ix;
        this.y=iy;
    }

    /**
     * default constructor
     */
    public Position(){
        this(0,0);
    }


    @Override
    public String toString() {
        return "("+x+" ; "+y+")";
    }




// ---- Non demandées dans le TD
    /**
     * calcule le point projeté de ce point sur l'axe des abscisses
     * @return un nouveau Point

    public Position projX() {
        return new Position(x,0);
    }*/

    /**
     * calcule le point projeté de ce point sur l'axe des ordonnées
     * @return un nouveau Point

    public Position projY() {
        return new Position(0,y);
    }*/
    //------------------------------------




}
