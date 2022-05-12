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

    public Spring inSeries(Spring spring1, Spring spring2){
        int k_stiffness = (spring1.getK_stiffness() * spring2.getK_stiffness()) / (spring1.getK_stiffness() + spring2.getK_stiffness());
        return new Spring(k_stiffness);
    }

    public Spring inParallel(Spring spring1, Spring spring2){
        int k_stiffness = spring1.getK_stiffness() + spring2.getK_stiffness();
        return new Spring(k_stiffness);
    }


    public Double[] move(double t, double dt, double x0, double v0) {
        double e = (Math.pow(x0, 2) * this.k_stiffness)/2 + Math.pow(v0, 2)/2;
        return coord(e, t, dt);
    }

    public Double[] move(double t, double dt, double x0) {
        double e = (Math.pow(x0, 2) / 2);
        return coord(e, t, dt);
    }

    public Double[] move(double t0, double tl, double dt, double x0, double v0) {
        double e = (Math.pow(x0, 2) * this.k_stiffness)/2 + Math.pow(v0, 2)/2;
        double t = tl - t0;
        return coord(e, t, dt);
    }

    public Double[] move(double t0, double tl, double dt, double x0, double v0, double m) {
        double e = (Math.pow(x0, 2) * this.k_stiffness)/2 + Math.pow(v0, 2)*m/2;
        double t = tl - t0;
        return coord(e, t, dt);
    }



    private Double[] coord(double e, double t, double dt) {
        return new Double[0];
    }
}
