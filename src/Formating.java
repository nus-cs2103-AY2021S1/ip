public class Formating<T> {
    private final T content;

    public Formating(T content) {
        this.content = content;
    }

    public T getContent() {
        return this.content;
    }

    public Formating<String> shorten() {
        try {
            String input = (String) content;
            int length = input.length();
            int frontPos = 0;
            int backPos = length - 1;
            while (frontPos < length && input.charAt(frontPos) == ' ') {
                frontPos++;
            }

            while (backPos >= 0 && input.charAt(backPos) == ' ') {
                backPos--;
            }

            if (frontPos > backPos) {
                return new Formating<>("");
            }
            return new Formating<>(input.substring(frontPos, backPos + 1));
        } catch (ClassCastException e) {
            DukeException.classCastException();
            return null;
        }
    }

    public Task stringToTask() {
        try {
            String input = (String) content;
            String[] inputArray = input.split(" ");
            char typeOfTask = inputArray[0].charAt(1);
            boolean isDone = false;
            if (inputArray[0].substring(4, 5).equals("\u2713")) {
                isDone = true;
            }

            Task task;
            if (typeOfTask == 'T') {
                task = new Todo(inputArray[1]);
            } else {
                int lenOfArray = inputArray.length;
                int lenOfLastInArray = inputArray[lenOfArray - 1].length();
                String time = inputArray[lenOfArray - 1]
                        .substring(0, lenOfLastInArray - 1);
                if (typeOfTask == 'D') {
                    task = new Deadline(inputArray[1], time);
                } else {
                    task = new Event(inputArray[1], time);
                }
            }
            if (isDone) {
                task.setDone();
            }
            return task;
        } catch (ClassCastException e) {
            DukeException.classCastException();
            return null;
        }
    }

    @Override
    public String toString() {
        String underscore =
                "  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

        if (this.content instanceof Task) {

            return underscore + "\n" +
                    Status.TASKADDED.toString() +
                    content + "\n" +
                    String.format(Status.REPORT.toString(), Operation.memory.getMemory().size()) +
                    "\n" +
                    underscore;
        }

        return underscore + "\n" +
                content + "\n" +
                underscore;
    }
}

