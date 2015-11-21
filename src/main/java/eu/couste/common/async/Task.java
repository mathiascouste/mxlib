package eu.couste.common.async;

public abstract class Task {

    private String name;
    private Thread thread;
    private Object returnValue;

    public Task(String name) {
        this.name = name;
    }

    public abstract Object run();

    public String getName() {
        return name;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public Object getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Object returnValue) {
        this.returnValue = returnValue;
    }
}
