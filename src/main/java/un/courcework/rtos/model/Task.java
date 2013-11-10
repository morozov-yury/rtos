package un.courcework.rtos.model;

public class Task {

    private String name;

    private int tStartIntActive;

    private int tEndIntActive;

    private int tPlanCall;

    private int tPeriodCall;

    private int tVaitMax;

    private int tExecMax;

    private int priority;

    private int tSession;

    private int nSession;

    private TaskState taskState;

    private TaskStatus taskStatus;

    public Task(String name, int tStartIntActive, int tEndIntActive, int tPlanCall, int tPeriodCall, int tVaitMax,
                int tExecMax, int priority, int tSession, int nSession, TaskState taskState, TaskStatus taskStatus) {
        this.name = name;
        this.tStartIntActive = tStartIntActive;
        this.tEndIntActive = tEndIntActive;
        this.tPlanCall = tPlanCall;
        this.tPeriodCall = tPeriodCall;
        this.tVaitMax = tVaitMax;
        this.tExecMax = tExecMax;
        this.priority = priority;
        this.tSession = tSession;
        this.nSession = nSession;
        this.taskState = taskState;
        this.taskStatus = taskStatus;
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

    public int gettPlanCall() {
        return tPlanCall;
    }

    public void settPlanCall(int tPlanCall) {
        this.tPlanCall = tPlanCall;
    }

    public TaskState getTaskState() {
        return taskState;
    }

    public void setTaskState(TaskState taskState) {
        this.taskState = taskState;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public int getnSession() {
        return nSession;
    }

    public void setnSession(int nSession) {
        this.nSession = nSession;
    }
}
