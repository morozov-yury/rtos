package un.courcework.rtos.core;

/**
 * Created with IntelliJ IDEA.
 * User: yri_kun
 * Date: 21.10.13
 * Time: 0:15
 * To change this template use File | Settings | File Templates.
 */
public class Utils {

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}
