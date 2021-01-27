package duke.tool;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import duke.model.AnnotatedPoint;
import duke.model.Interval;
import duke.model.PointType;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a schedule manager which does scheduling tasks.
 */
public class ScheduleManager {
    private LocalDate startingTime;
    private LocalDate endTime;
    private TaskList taskList;
    private TimeConverter timeConverter;

    /**
     * Constructs a schedule manager to find free slots.
     * @param startingTime starting time of the scheduling.
     * @param endTime end time of the scheduling.
     * @param taskList list of task.
     */
    public ScheduleManager(LocalDate startingTime, LocalDate endTime,
                           TaskList taskList, TimeConverter timeConverter) {
        this.startingTime = startingTime;
        this.endTime = endTime;
        this.taskList = taskList;
        this.timeConverter = timeConverter;
    }

    /**
     * Creates a list of annotated points and queue them into a list for scheduling.
     * @param interval entire time span for scheduling.
     * @param remove occupied time slot.
     * @return queue sorted by point value.
     */
    public ArrayList<AnnotatedPoint> initQueue(ArrayList<Interval> interval, ArrayList<Interval> remove) {
        // annotate all points and put them in a list
        ArrayList<AnnotatedPoint> queue = new ArrayList<>();
        for (Interval i : interval) {
            queue.add(new AnnotatedPoint(i.getStart(), PointType.Start));
            queue.add(new AnnotatedPoint(i.getEnd(), PointType.End));
        }
        for (Interval i : remove) {
            queue.add(new AnnotatedPoint(i.getStart(), PointType.GapStart));
            queue.add(new AnnotatedPoint(i.getEnd(), PointType.GapEnd));
        }

        // sort the queue
        Collections.sort(queue);

        return queue;
    }

    /**
     * Finds non-overlapping intervals in a queue of annotated point.
     * @param queue queue of annotated points.
     * @return an array list of intervals.
     */
    public ArrayList<Interval> doSweep(List<AnnotatedPoint> queue) {
        ArrayList<Interval> result = new ArrayList<>();

        // iterate over the queue
        boolean isInterval = false;
        boolean isGap = false;
        int intervalStart = 0;
        for (AnnotatedPoint point : queue) {
            switch (point.getType()) {
            case Start:
                if (!isGap) {
                    intervalStart = point.getValue();
                }
                isInterval = true;
                break;
            case End:
                if (!isGap) {
                    result.add(new Interval(intervalStart, point.getValue()));
                }
                isInterval = false;
                break;
            case GapStart:
                if (isInterval) {
                    result.add(new Interval(intervalStart, point.getValue()));
                }
                isGap = true;
                break;
            case GapEnd:
                if (isInterval) {
                    intervalStart = point.getValue();
                }
                isGap = false;
                break;
            default:
                break;
            }
        }
        return result;
    }

    /**
     * Finds free slots in a day. Events are assumed to have a duration of 1 hour.
     * @return an array list containing free slot intervals.
     */
    public ArrayList<Interval> findFreeSlots() {
        ArrayList<Interval> interval = new ArrayList<>();
        interval.add(new Interval(0, 1439));
        ArrayList<Interval> remove = new ArrayList<>();

        for (int i = 0; i < this.taskList.getSize(); i++) {
            if (this.taskList.get(i) instanceof Event) {
                Task event = this.taskList.get(i);
                int dayOfYear = ((Event) event).getTime().getDayOfYear();
                if (dayOfYear == this.startingTime.getDayOfYear()) {
                    LocalDateTime startingTime = ((Event) event).getTime();
                    int startPoint = this.timeConverter.convertTimeToInt(startingTime);
                    int endPoint = startPoint + 60;
                    remove.add(new Interval(startPoint, endPoint));
                }
            }
        }

        ArrayList<AnnotatedPoint> queue = initQueue(interval, remove);

        return doSweep(queue);
    }
}
