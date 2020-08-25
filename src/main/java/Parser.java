public class Parser {
    int getTaskNumber(String s) {
        int taskNumber = Integer.parseInt(s.split(" ")[1]);
        return taskNumber;
    }

    Task stringToTask(String s) {
        String[] arr = s.split(" @");
        String type = arr[0];
        String status = arr[1].substring(1);
        Boolean completed;
        String name = arr[2].substring(1);
        String time = arr[3].substring(1);

        if (status.equals("x")) {
            completed = false;
        } else {
            completed = true;
        }

        if (type.equals("todo")) {
            return new Todo(name, completed);
        } else if (type.equals("deadline")) {
            return new Deadline(name, completed, time);
        } else {
            return new Event(name, completed, time);
        }
    }

    Task commandToTask(String s) throws IncompleteInputException {
        try {
            String[] arr = s.split(" ");
            String type = arr[0];
            String name = arr[1];
            String time;

            if (type.equals("todo")) {
                return new Todo(name);
            } else if (type.equals("deadline")) {
                time = arr[3];
                return new Deadline(name, time);
            } else {
                time = arr[3];
                return new Event(name, time);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IncompleteInputException();
        }
    }
}
