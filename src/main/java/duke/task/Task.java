package duke.task;

import duke.exception.DukeException;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getFirstWord() {
        String[] arr = this.description.split(" ", 2);
        String firstWord = arr[0];
        return firstWord;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public boolean isTodo() {
        return this.getFirstWord().equals("todo");
    }

    public boolean isDeadline() {
        return this.getFirstWord().equals("deadline");
    }

    public boolean isEvent() {
        return this.getFirstWord().equals("event");
    }

    public int getNumber() {
        String[] arr = this.description.split(" ", 2);
        if (arr[0].equals("done") || arr[0].equals("delete")) {
            String stringNum = arr[1];
            return Integer.parseInt(stringNum);
        }
        return -1; // this is return when user did not input a 'done' or 'delete statement
    }

    public String getDescription() {
        return this.description;
    }

    public String getDate() {

        String des = this.getDescription();

        if (des.indexOf('/') == -1) {

        }
        return des.substring(des.indexOf('/') + 4, des.length());
    }

    public boolean isSingleWord() {
        return !this.description.contains(" ");
    }

    public boolean isInvalidTask() {
        String firstWord = this.getFirstWord();
        return !(firstWord.equals("todo") || firstWord.equals("deadline") || firstWord.equals("event"));
    }

    public void validate() throws DukeException {
        if (this.isInvalidTask()) { // checks if input is not to do, deadline, or event
            throw new DukeException("   ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        if (this.isSingleWord()) { // checks if input is only the type of task, but no description
            throw new DukeException("   ☹ OOPS!!! The description of a " + this.getFirstWord() + " cannot be empty.");
        }

        if (!this.getDescription().contains("/by") && this.isDeadline()) {
            // "/by" is not in the description, i.e no date
            throw new DukeException("   ☹ OOPS!!! The description of a deadline must contain a date.");
        }

        if (!this.getDescription().contains("/at") && this.isEvent()) {
            // "/at" is not in the description, i.e no date
            throw new DukeException("   ☹ OOPS!!! The description of an event must contain a date.");
        }
    }

    public String taskToText() { // converts description text to file text
        String des = this.description;
        String task = des.substring(des.indexOf(" ") + 1);
        if (this.isTodo()) {
            return (this.isDone ? "T | 1 | " + task
                    : "T | 0 | " + task);
        } else if (this.isDeadline()) {
            return (this.isDone ? "D | 1 | " + task.substring(0, task.indexOf('/') - 1)
                    + " | " + this.getDate()
                    : "D | 0 | " + task.substring(0, task.indexOf('/') - 1)
                    + " | " + this.getDate());
        } else if (this.isEvent()) {
            return (this.isDone ? "E | 1 | " + task.substring(0, task.indexOf('/') - 1)
                    + " | " + this.getDate()
                    : "E | 0 | " + task.substring(0, task.indexOf('/') - 1)
                    + " | " + this.getDate());
        } else {
            return "";
        }
    }

    public ToDo convertToTodo() {
        ToDo todo = new ToDo(this.description);
        todo.isDone = this.isDone;
        return todo;
    }

    public Deadline convertToDeadline() {
        Deadline d = new Deadline(this.description, this.getDate());
        d.isDone = this.isDone;
        return d;
    }

    public Event convertToEvent() {
        Event e = new Event(this.description, this.getDate());
        e.isDone = this.isDone;
        return e;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Task)) {
            return false;
        }

        Task t = (Task) o;

        if (this.description.equals(t.description) && this.isDone == t.isDone) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        String des = this.description;
        String task = des.substring(des.indexOf(" "));
        if (this.isTodo()) {
            return "[" + this.getStatusIcon() + "] " + task;
        } else {
            return "[" + this.getStatusIcon() + "] " + task.substring(0, task.indexOf('/') - 1);
        }
    }
}
