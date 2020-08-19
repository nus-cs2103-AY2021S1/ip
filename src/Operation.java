public class Operation {
    private Memory<Task> memory;

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
                memory.addMemory(new Task(order));
                Formating<Response> formatedEcho =
                        new Formating<>(new Echo(Status.ADD.toString() + order));
                System.out.println(formatedEcho);
            }
        }
    }
}
