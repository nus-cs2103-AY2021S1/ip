package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public abstract class Task {

    protected String description;
    protected boolean isDone;
    protected LocalDate date;
    protected ArrayList<Tag> tags = new ArrayList<>();

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected Task(String description, LocalDate date) {
        this.description = description;
        this.date = date;
    }

    public String getDate() {
        if (date != null) {
            return date.format(DateTimeFormatter.ofPattern("dd MMM uuuu"));
        } else {
            return "";
        }
    }

    public String getSaveDate() {
        if (date != null) {
            return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        } else {
            return "";
        }
    }

    public String getStatusIcon() {
        return (isDone ? "O" : "X"); //return tick or X symbols
    }

    public String getStatusLetter() {
        return (isDone ? "y" : "n");
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void addTag(String tagDescription) {
        tags.add(new Tag(tagDescription));
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public String listTags() {
        StringBuffer tagsBuffer = new StringBuffer();
        for (int i = 0; i < tags.size(); i++) {
            tagsBuffer.append(tags.get(i).toString() + " ");
        }
        return tagsBuffer.toString().trim();
    }

    public abstract String createSaveDataLine();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description + " " + listTags();
    }
}
