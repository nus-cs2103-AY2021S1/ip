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
        String type;
        String name;
        String time;
        try {
            if (s.contains("/")) { // event or deadline
                String[] arr = s.split("/");
                String[] typeAndName = arr[0].split(" ", 2);
                type = typeAndName[0];
                name = typeAndName[1];
                time = arr[1].split(" ")[1];

                if (type.equals("deadline")) {
                    return new Deadline(name, time);
                } else {
                    return new Event(name, time);
                }
            } else { // to-do
                String[] arr = s.split(" ", 2);
                name = arr[1];
                return new Todo(name);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IncompleteInputException();
        }
    }

    String getKeyword(String s) {
        return s.split(" ")[1];
    }
}
