package un.courcework.rtos.model;

public class Task {

    private Integer id;
    private Integer tStartIntActive;
    private Integer tEndIntActive;
    private Integer tPlanCall;
    private Integer tPeriodCall;
    private Integer tVaitMax;
    private Integer tExecMax;
    private Integer priority;
    private Integer tSession;
    private Integer nSession;
    private TaskState taskState;
    private TaskStatus taskStatus;

    public Task(Integer id, Integer tStartIntActive, Integer tEndIntActive, Integer tPlanCall,
                Integer tPeriodCall, Integer tVaitMax, Integer tExecMax, Integer priority,
                Integer tSession, Integer nSession, TaskState taskState, TaskStatus taskStatus) {
        this.id = id;
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

    public Integer gettStartIntActive() {
        return tStartIntActive;
    }

    public void settStartIntActive(Integer tStartIntActive) {
        this.tStartIntActive = tStartIntActive;
    }

    public Integer gettEndIntActive() {
        return tEndIntActive;
    }

    public void settEndIntActive(Integer tEndIntActive) {
        this.tEndIntActive = tEndIntActive;
    }

    public Integer gettPeriodCall() {
        return tPeriodCall;
    }

    public void settPeriodCall(Integer tPeriodCall) {
        this.tPeriodCall = tPeriodCall;
    }

    public Integer gettVaitMax() {
        return tVaitMax;
    }

    public void settVaitMax(Integer tVaitMax) {
        this.tVaitMax = tVaitMax;
    }

    public Integer gettExecMax() {
        return tExecMax;
    }

    public void settExecMax(Integer tExecMax) {
        this.tExecMax = tExecMax;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer gettSession() {
        return tSession;
    }

    public void settSession(Integer tSession) {
        this.tSession = tSession;
    }

    public Integer gettPlanCall() {
        return tPlanCall;
    }

    public void settPlanCall(Integer tPlanCall) {
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

    public Integer getnSession() {
        return nSession;
    }

    public void setnSession(Integer nSession) {
        this.nSession = nSession;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
