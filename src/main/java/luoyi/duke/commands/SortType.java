package luoyi.duke.commands;

import java.util.Comparator;
import java.util.Optional;

import luoyi.duke.common.TimeWrapper;
import luoyi.duke.data.task.ITask;

/**
 * Type of sort, which can be based on time or description alphabetically.
 *
 * When a time without actual time parameter (date or datetime) is defined to be smaller.
 */
public enum SortType {
    TIME((o1, o2) -> {
        Optional<TimeWrapper> t1 = o1.getTime();
        Optional<TimeWrapper> t2 = o2.getTime();
        if (t1.isEmpty() && t2.isEmpty()) {
            return 0;
        } else if (t1.isPresent() && t2.isEmpty()) {
            return 1;
        } else if (t1.isEmpty() && t2.isPresent()) {
            return -1;
        }
        assert t1.isPresent() && t2.isPresent();

        return t1.get().compareTo(t2.get());
    }),
    DESCRIPTION((o1, o2) -> {
        return o1.getDescription().compareTo(o2.getDescription());
    });

    private final Comparator<? super ITask> taskComparator;

    SortType(Comparator<? super ITask> taskComparator) {
        this.taskComparator = taskComparator;
    }

    /**
     * Returns the comparator associated with the sort type.
     *
     * @return Comparator associated with the sort type.
     */
    public Comparator<? super ITask> getTaskComparator() {
        return taskComparator;
    }
}
