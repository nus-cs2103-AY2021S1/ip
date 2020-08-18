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

    @Override
    public String toString() {
        return this.description;
    }
}
