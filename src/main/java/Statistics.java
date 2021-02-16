import java.time.DayOfWeek;
import java.time.LocalDate;

public class Statistics {
    private int totalTasksAdded;
    private int totalTasksCompleted;
    private int totalTasksDeleted;

    private DayOfWeek day;
    private boolean isMonday;

    Statistics(int added, int completed, int deleted) {
        this.totalTasksAdded = added;
        this.totalTasksCompleted = completed;
        this.totalTasksDeleted = deleted;

        this.day = LocalDate.now().getDayOfWeek();
        if (this.day.equals(DayOfWeek.MONDAY)) {
            this.isMonday = true;
        } else {
            this.isMonday = false;
        }
    }

    Statistics(String data) {
        String[] arr = data.split(" @");
        this.totalTasksAdded = Integer.parseInt(arr[0]);
        this.totalTasksCompleted = Integer.parseInt(arr[1].substring(1));
        this.totalTasksDeleted = Integer.parseInt(arr[2].substring(1));

        this.day = LocalDate.now().getDayOfWeek();
        if (this.day.equals(DayOfWeek.MONDAY)) {
            this.isMonday = true;
        } else {
            this.isMonday = false;
        }
    }

    Statistics reset() {
        if (isMonday) {
            return new Statistics(0, 0, 0);
        } else {
            return this;
        }
    }

    int getTotalTasksAdded() {
        return this.totalTasksAdded;
    }

    int getTotalTasksCompleted() {
        return this.totalTasksCompleted;
    }

    int getTotalTasksDeleted() {
        return this.totalTasksDeleted;
    }

    Statistics addTask() {
        return new Statistics(this.totalTasksAdded + 1, this.totalTasksCompleted, this.totalTasksDeleted);
    }

    Statistics addCompletedTask() {
        return new Statistics(this.totalTasksAdded, this.totalTasksCompleted + 1, this.totalTasksDeleted);
    }

    Statistics addDeletedTask() {
        return new Statistics(this.totalTasksAdded, this.totalTasksCompleted, this.totalTasksDeleted + 1);
    }

    String statisticsToData() {
        return this.totalTasksAdded + " @ " + this.totalTasksCompleted + " @ " + this.totalTasksDeleted;
    }

    @Override
    public String toString() {
        return "total tasks added: " + this.totalTasksAdded + "\n"
                + "total tasks completed: " + this.totalTasksCompleted + "\n"
                + "total tasks deleted: " + this.totalTasksDeleted;
    }
}
