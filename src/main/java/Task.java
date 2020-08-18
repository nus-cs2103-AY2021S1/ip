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
        return des.substring(des.indexOf('/') + 4, des.length());
    }

    public String getDeadlineTask() {
        String des = this.getDescription();
        return des.substring(0, des.indexOf('/') - 1);
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
