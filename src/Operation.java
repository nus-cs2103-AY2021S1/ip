public class Operation {
    public static Memory<Task> memory;

    public Operation(Memory<Task> memory) {
        this.memory = memory;
    }


    public void run(String order) {

        if (order.equals(Status.LIST.name().toLowerCase())) {
            System.out.println(
                    new Formating<>(Status.LIST.toString() + memory)
            );
        } else {
            if (order.length() >= 6
                        && order.substring(0, 4).equals(Status.DONE.name().toLowerCase())) {
                done(order);
            } else if (order.length() == 0) { }

            else {
                identifier(order);
            }
        }
    }

    public void done(String order) {
        try {
            int num =
                    Integer.parseInt(new Formating<>(order.substring(4)).shorten().getContent());

            if (num > memory.getMemory().size()) {
                DukeException.numberExcessException();
            } else {
                Task task = memory.getMemory().get(num - 1);
                task.setDone();
                System.out.println(
                        new Formating<>(new Echo(Status.DONE.toString() + task)));
            }
        } catch (NumberFormatException e) {
            DukeException.numberFormatException();
        }
    }
    

    public void identifier(String description) {
        int len = description.length();
        int pointer = 0;

        while (pointer < len && description.charAt(pointer) != ' ') {
            pointer++;
        }

        //the identity of the task has been found.
        String idetity = description.substring(0, pointer);

        //to check if the input is not a todo or event or deadline
        if (!idetity.equals(Status.TODO.toString())
            & !idetity.equals(Status.EVENT.toString())
            & !idetity.equals(Status.DEADLINE.toString())) {
            DukeException.inputFormatException();
            return;
        }


        //pointer goes on to find details of the task
        int separator = pointer;
        while (separator < len && description.charAt(separator) != '/') {
            separator++;
        }

        //situation that there is no detail of the task, throw error
        if (pointer == separator) {
            DukeException.emptyTaskException();
            return;
        }

        //details of the task is found
        String detail = description.substring(pointer + 1, separator);

        Task task;
        if (idetity.equals(Status.TODO.toString())) {

            task = new Todo(detail);

        } else {

            while (separator < len && description.charAt(separator) != ' ') {
                separator++;
            }

            String time;

            if (separator < len - 1) {
                time = description.substring(separator + 1);
            } else {
                time = description.substring(separator);
                DukeException.timeMissingException();
                return;

            }

            if (idetity.equals(Status.DEADLINE.toString())) {
                task = new Deadline(detail, time);
            } else {
                task = new Event(detail, time);
            }
        }
        memory.addMemory(task);
        Formating<Task> formatedEcho =
                new Formating<>(task);
        System.out.println(formatedEcho);
    }

}
