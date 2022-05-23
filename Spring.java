import java.util.ArrayList;

public class Spring {

    //TODO:  implement
    // double[] move(double t, double dt, double x0, double v0) – a body of unit mass
    //oscillates during a period double t starting from t = 0 with initial conditions x(0) =
    //x0 and v(0) = v0. The coordinate is computed per each double dt time step;
    //- double[] move(double t, double dt, double x0) – a body of unit mass oscillates
    //during a period double t starting from t = 0 with initial conditions x(0) = x0 and
    //v(0) = 0. The coordinate is computed per each double dt time step;
    //- double[] move(double t0, double t1, double dt, double x0, double v0) – a body of
    //unit mass oscillates from t = t0 till t = t1 with initial conditions x(t0) = x0 and v(t0)
    //= v0. The coordinate is computed per each double dt time step;
    //- double[] move(double t0, double t1, double dt, double x0, double v0, double m)
    //– a body of a specified mass double m oscillates from t = t0 till t = t1 with initial
    //conditions x(t0) = x0 and v(t0) = v0. The coordinate is computed per each double
    //dt time step;

    Integer k_stiffness = 1;
    Integer mass = 1;

    ArrayList<Double> coord = new ArrayList<>();

    public Spring(Integer k_stiffness) {
        this.k_stiffness = k_stiffness;
    }

    public Spring() {
    }

    public Integer getK_stiffness() {
        return k_stiffness;
    }

    public void setK_stiffness(Integer k_stiffness) {
        this.k_stiffness = k_stiffness;
    }

    public Integer getMass() {
        return mass;
    }

    public void setMass(Integer mass) {
        this.mass = mass;
    }


    public Double getW() {
        return Math.sqrt(getK_stiffness()/getMass());
    }

    public Spring inSeries(Spring spring1, Spring spring2){
        int k_stiffness = (spring1.getK_stiffness() * spring2.getK_stiffness()) / (spring1.getK_stiffness() + spring2.getK_stiffness());
        return new Spring(k_stiffness);
    }

    public Spring inParallel(Spring spring1, Spring spring2){
        int k_stiffness = spring1.getK_stiffness() + spring2.getK_stiffness();
        return new Spring(k_stiffness);
    }


    public Double[] move(double t, double dt, double x0, double v0) {
        return coord(0, x0,t, v0, dt);

    }

    public Double[] move(double t, double dt, double x0) {
        return coord(0, x0, 0, 0, dt);

    }

    public Double[] move(double t0, double tl, double dt, double x0, double v0) {
        return coord(t0, x0, tl, v0, dt);

    }

    public Double[] move(double t0, double tl, double dt, double x0, double v0, double m) {
        return coord(t0, x0, tl, v0, dt);
    }



    private Double[] coord(double t0, double x0, double tl, double v0, double dt) {
        do{
            double val = x0* Math.cos(getW()*t0) + (v0/getW()) * Math.sin(getW()*t0);
            this.coord.add(val);
            t0 += dt;
        }
        while (t0<tl);

        return (Double[]) coord.toArray();
    }
}
