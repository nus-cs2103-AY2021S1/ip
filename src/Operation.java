public class Operation {
    public static Memory<Task> memory;

    public Operation(Memory<Task> memory) {
        this.memory = memory;
    }


    public void run(String order) throws Exception {

        if (order.equals(Status.LIST.name().toLowerCase())) {
            System.out.println(
                    new Formating<>(Status.LIST.toString() + memory)
            );
        } else {
            if (order.length() >= 6
                    && order.substring(0, 4).equals(Status.DONE.name().toLowerCase())) {

                try {
                    int num =
                            Integer.parseInt(new Formating<>(order.substring(4)).shorten().getContent());

                    if (num > memory.getMemory().size()) {
                        System.out.println(new Formating<>(Status.EXCESS.toString()));
                    } else {
                        Task task = memory.getMemory().get(num - 1);
                        task.setDone();
                        System.out.println(
                                new Formating<>(new Echo(Status.DONE.toString() + task)));
                    }
                } catch (NumberFormatException e) {
                    System.out.println(new Formating<>(Status.NUMBERFORMATEXCEPTION.toString()));
                }

            } else if (order.length() == 0) { }

            else {
                Task task = identifier(order);
                memory.addMemory(task);
                Formating<Task> formatedEcho =
                        new Formating<>(task);
                System.out.println(formatedEcho);
            }
        }
    }

    public Task identifier(String description) throws Exception {
        int len = description.length();
        int pointer = 0;

        while (pointer < len && description.charAt(pointer) != ' ') {
            pointer++;
        }

        String indetity = description.substring(0, pointer);

        int separator = pointer;
        while (separator < len && description.charAt(separator) != '/') {
            separator++;
        }

        if (pointer == separator) {
            throw new Exception("please do a todo/deadline/event");
        }
        String detail = description.substring(pointer + 1, separator);

        if (indetity.equals(Status.TODO.toString())) {
            return new Task(detail);
        } else {
            while (separator < len && description.charAt(separator) != ' ') {
                separator++;
            }

            String time;

            if (separator < len - 1) {
                time = description.substring(separator + 1);
            } else {
                time = description.substring(separator);
            }
            if (indetity.equals(Status.DEADLINE.toString())) {
                return new Deadline(detail, time);
            } else if (indetity.equals(Status.EVENT.toString())){
                return new Event(detail, time);
            } else {
                throw new Exception("wrong input");
            }
        }
    }

}
