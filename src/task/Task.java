package task;

import ui.UIPrint;

public class Task {

    private String icon;
    private String description;
    private boolean isDone;

    protected Task(String icon, String description) {
        this.icon = icon;
        this.description = description;
        isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        String statusIcon = isDone ? UIPrint.tick : UIPrint.cross;

        return icon + statusIcon + " " + description;
    }
}
