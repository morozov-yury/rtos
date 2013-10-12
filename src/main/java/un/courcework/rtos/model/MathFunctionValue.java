package un.courcework.rtos.model;

/**
 * Created with IntelliJ IDEA.
 * User: yri_kun
 * Date: 13.10.13
 * Time: 0:31
 * To change this template use File | Settings | File Templates.
 */
public class MathFunctionValue {

    private Double t;
    private Double f;

    public MathFunctionValue(Double t, Double f) {
        this.t = t;
        this.f = f;
    }

    public Double getT() {
        return t;
    }

    public void setT(Double t) {
        this.t = t;
    }

    public Double getF() {
        return f;
    }

    public void setF(Double f) {
        this.f = f;
    }
}
