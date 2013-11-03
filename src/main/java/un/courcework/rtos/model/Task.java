package un.courcework.rtos.model;

public class Task {

    private String name;

    private int tStartIntActive;

    private int tEndIntActive;

    private int tPeriodCall;

    private int tVaitMax;

    private int tExecMax;

    private int tCrit;

    private int priority;

    private int tSession;

    public Task(String name, int tStartIntActive, int tEndIntActive, int tPeriodCall, int tVaitMax, int tExecMax,
                int tCrit, int priority, int tSession) {
        this.name = name;
        this.tStartIntActive = tStartIntActive;
        this.tEndIntActive = tEndIntActive;
        this.tPeriodCall = tPeriodCall;
        this.tVaitMax = tVaitMax;
        this.tExecMax = tExecMax;
        this.tCrit = tCrit;
        this.priority = priority;
        this.tSession = tSession;
    }

    public int gettStartIntActive() {
        return tStartIntActive;
    }

    public void settStartIntActive(int tStartIntActive) {
        this.tStartIntActive = tStartIntActive;
    }

    public int gettEndIntActive() {
        return tEndIntActive;
    }

    public void settEndIntActive(int tEndIntActive) {
        this.tEndIntActive = tEndIntActive;
    }

    public int gettPeriodCall() {
        return tPeriodCall;
    }

    public void settPeriodCall(int tPeriodCall) {
        this.tPeriodCall = tPeriodCall;
    }

    public int gettVaitMax() {
        return tVaitMax;
    }

    public void settVaitMax(int tVaitMax) {
        this.tVaitMax = tVaitMax;
    }

    public int gettExecMax() {
        return tExecMax;
    }

    public void settExecMax(int tExecMax) {
        this.tExecMax = tExecMax;
    }

    public int gettCrit() {
        return tCrit;
    }

    public void settCrit(int tCrit) {
        this.tCrit = tCrit;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int gettSession() {
        return tSession;
    }

    public void settSession(int tSession) {
        this.tSession = tSession;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
