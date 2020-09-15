package duke;

import java.time.LocalDate;

public class AdditionalInfo {
    private int taskIndex;
    private String description;
    private String time;
    private LocalDate date;

    public AdditionalInfo(int taskIndex, String description, String time, LocalDate date) {
        this.taskIndex = taskIndex;
        this.description = description;
        this.time = time;
        this.date = date;
    }

    public AdditionalInfo(int taskIndex) {
        assert taskIndex >= 0 : "Index of task cannot be smaller than 0";
        this.taskIndex = taskIndex;
    }

    public AdditionalInfo(String description) {
        this.description = description;
    }

    public AdditionalInfo(String description, String time, LocalDate date) {
        this.description = description;
        this.time = time;
        this.date = date;
    }

    public AdditionalInfo(String description, String time) {
        this.description = description;
        this.time = time;
    }

    public int getTaskIndex() {
        return taskIndex;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }

    public LocalDate getDate() {
        return date;
    }
}
