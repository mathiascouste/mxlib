package eu.couste.common.async;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class Waiter {

    private Map<String, Task> tasks = new HashMap<>();

    public void addTask(Task t) {
        this.tasks.put(t.getName(), t);
    }

    public Waiter join(String taskName) {
        try {
            tasks.get(taskName).getThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public Waiter joinAll() {
        for (Entry<String, Task> e : tasks.entrySet()) {
            this.join(e.getKey());
        }
        return this;
    }

}
