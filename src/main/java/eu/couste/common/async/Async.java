package eu.couste.common.async;

public abstract class Async {

    public static Waiter run(Task... tasks) {
        Waiter waiter = new Waiter();

        for (Task t : tasks) {
            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    t.setReturnValue(t.run());
                }
            });
            t.setThread(thread);
            waiter.addTask(t);
            thread.start();
        }
        return waiter;
    }
}
