package eu.couste.common.time;

/**
 * Interface to allow the class implementing this to subscribe to the timewarper
 * 
 * @version 0.1
 */
public interface TimeObserver {

    /**
     * Method called by the timewarper you subscribe to. It is called at regular intervals.
     */
    public void tilt();
}
