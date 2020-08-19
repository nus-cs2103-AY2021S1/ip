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
        if (arr[0].equals("done")) {
            String stringNum = arr[1];
            return Integer.parseInt(stringNum);
        }
        return -1; // this is return when user did not input a 'done' statement
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

    public String getDeadlineTask() {
        String des = this.getDescription();
        return des.substring(0, des.indexOf('/') - 1);
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

        if (!this.getDescription().contains("/by") && this.isDeadline()) { // "/by" is not in the description, i.e no date
            throw new DukeException("   ☹ OOPS!!! The description of a deadline must contain a date.");
        }

        if (!this.getDescription().contains("/at") && this.isEvent()) { // "/at" is not in the description, i.e no date
            throw new DukeException("   ☹ OOPS!!! The description of an event must contain a date.");
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
