package SpaceEvaders.Utilities;


public class BooleanToggler {
    private boolean running = false;
    private boolean value = false;
    private boolean defaultValue;
    private double interval;
    private double intervalTimer;
    private double duration;
    private double durationTimer;


    public BooleanToggler(boolean _defaultValue, double _interval, double _duration) {
        defaultValue = _defaultValue;
        interval = _interval;
        duration = _duration;
    }

    public void start() {
        running = true;
        durationTimer = duration;
        intervalTimer = interval;
    }

    public void update(double deltaTime) {
        if (!running)
            return;
        durationTimer -= deltaTime;
        intervalTimer -= deltaTime;
        if (intervalTimer <= 0) {
            value = !value;
            intervalTimer = interval;
        }
        if (durationTimer <= 0) {
            running = false;
            value = !(!defaultValue);
        }
    }

    public boolean getValue() {
        return value;
    }
}
