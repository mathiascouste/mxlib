package eu.couste.common.time;

import java.util.HashMap;
import java.util.Map;


/**
 * Class
 * 
 * @version 0.2
 */
public class TimeWarper {

    public static final int DEFAULT = 0;
    public static final int NO_STOP_MODE = 1;
    private static final int GAP = 16;
    private static final int DEFAULT_FPS = 25;
    private int mode;
    private long lastTransit;
    private double acceleration;
    private long realGameDuration;
    private Map<TimeObserver, PeriodAndRemaining> observers;

    /**
     * Constructor
     */
    public TimeWarper() {
        this.acceleration = 1;
        this.realGameDuration = 1000L / DEFAULT_FPS;
        this.lastTransit = -1;
        this.observers = new HashMap<TimeObserver, PeriodAndRemaining>();
        this.mode = DEFAULT;
    }

    /**
     * Initializes the time warper
     */
    public void init() {
        this.lastTransit = System.currentTimeMillis();
    }

    /**
     * TODO
     * 
     * @return
     * @throws InterruptedException
     */
    public long transit() throws InterruptedException {
        long currentTime = System.currentTimeMillis();
        long timeFromLastTransit = currentTime - this.lastTransit;

        long restWaitDuration = -1;
        long timeToReturn = -1;

        if (this.mode == DEFAULT) {
            if (acceleration <= GAP) {
                long realWaitDuration = (long) (realGameDuration / acceleration);
                restWaitDuration = realWaitDuration - timeFromLastTransit;
                timeToReturn = realGameDuration;
            } else {
                double semiAcceleration = acceleration / GAP;
                long realWaitDuration = realGameDuration / GAP;
                restWaitDuration = realWaitDuration - timeFromLastTransit;
                this.lastTransit = System.currentTimeMillis();
                timeToReturn = (long) (realGameDuration * semiAcceleration);
            }
            if (restWaitDuration > 0) {
                Thread.sleep(restWaitDuration);
            }

        } else if (this.mode == NO_STOP_MODE) {
            timeToReturn = this.realGameDuration;
        }

        this.lastTransit = System.currentTimeMillis();
        this.informObservers(timeToReturn);
        return timeToReturn;
    }

    private class PeriodAndRemaining {

        private long period, remaining;

        public PeriodAndRemaining(long period, long remaining) {
            this.period = period;
            this.remaining = remaining;
        }
    }

    private void informObservers(long time) {
        for (Map.Entry<TimeObserver, PeriodAndRemaining> e : this.observers.entrySet()) {
            PeriodAndRemaining pr = e.getValue();
            pr.remaining -= time;
            if (pr.remaining <= 0) {
                pr.remaining = pr.period;
                e.getKey().tilt();
            }
        }
    }

    /**
     * TODO
     * 
     * @param observer
     * @param period
     */
    public void subscribe(TimeObserver observer, long period) {
        this.observers.put(observer, new PeriodAndRemaining(period, 0));
    }

    /**
     * TODO
     * 
     * @param observer
     */
    public void unsubscribe(TimeObserver observer) {
        this.observers.remove(observer);
    }

    /*
     * 
     * GETTERS AND SETTERS
     */

    /**
     * @return the mode
     */
    public int getMode() {
        return mode;
    }

    /**
     * @param mode the mode to set
     */
    public void setMode(int mode) {
        this.mode = mode;
    }

    /**
     * @return the lastTransit
     */
    public long getLastTransit() {
        return lastTransit;
    }

    /**
     * @param lastTransit the lastTransit to set
     */
    public void setLastTransit(long lastTransit) {
        this.lastTransit = lastTransit;
    }

    /**
     * @return the acceleration
     */
    public double getAcceleration() {
        return acceleration;
    }

    /**
     * @param acceleration the acceleration to set
     */
    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    /**
     * @return the realGameDuration
     */
    public long getRealGameDuration() {
        return realGameDuration;
    }

    /**
     * @param realGameDuration the realGameDuration to set
     */
    public void setRealGameDuration(long realGameDuration) {
        this.realGameDuration = realGameDuration;
    }

    /**
     * @return the observers
     */
    public Map<TimeObserver, PeriodAndRemaining> getObservers() {
        return observers;
    }

    /**
     * @param observers the observers to set
     */
    public void setObservers(Map<TimeObserver, PeriodAndRemaining> observers) {
        this.observers = observers;
    }

}
