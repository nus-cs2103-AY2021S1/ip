package luoyi.duke.data.task;

import java.util.Optional;

import luoyi.duke.common.TimeWrapper;

/**
 * Encapsulate a task which has a time component.
 */
public abstract class TimedTask extends Task {

    protected final TimeWrapper time;

    /**
     * Returns a new TimedTask object.
     *
     * @param description Description of the task.
     * @param isDone      Boolean to keep track of whether the task is done.
     */
    protected TimedTask(String description, TimeWrapper time, boolean isDone) {
        super(description, isDone);
        this.time = time;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<TimeWrapper> getTime() {
        return Optional.ofNullable(time);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSameTime(String date) {
        return time.equals(TimeWrapper.getTimeWrapper(date));
    }
}
