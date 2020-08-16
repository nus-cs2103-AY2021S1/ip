public class Output {
    void response(String s, TaskList taskList) {
        if (s.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        } else if (s.isEmpty()) {
            System.out.println("Please enter a command");
        } else if (s.equals("list")) {
            System.out.println("Here are the tasks in your list:");
            taskList.listAllTasks();
        } else if (s.length() >= 4 && s.substring(0, 4).equals("done")) {
            doneResponse(taskList, s);
        } else if (s.length() > 4 && s.substring(0, 4).equals("todo")) {
            todoResponse(taskList, s);
        } else if (s.length() > 8 && s.substring(0, 8).equals("deadline")) {
                deadlineResponse(taskList, s);
        } else if (s.length() > 5 && s.substring(0, 5).equals("event")) {
                eventResponse(taskList, s);
        } else {
            System.out.println(4);
        }
    }

    public void doneResponse(TaskList taskList, String command) {
        int numTodos = taskList.list.size();
        int todoIndex = Integer.valueOf(command.substring(5, 6));
        if (todoIndex <= numTodos && todoIndex > 0) {
            Task selectedTodo = taskList.getTask(todoIndex);
            selectedTodo.setDone();
            System.out.println("Nice! I've marked this task as done:\n " + selectedTodo);
        } else {
            System.out.println("Todo number not in TodoList");
        }
    }

    public void todoResponse(TaskList taskList, String command) {
        Todo todo = new Todo(command.substring(4));
        taskList.add(todo);
        System.out.println("Got it. I've added this task:\n " + todo + "\nNow you have "
                + taskList.list.size() + " in the list");
    }

    public void deadlineResponse(TaskList taskList, String command) {
        Deadline deadline = new Deadline(command.substring(8));
        taskList.add(deadline);
        System.out.println("Got it. I've added this task:\n" +
                deadline + "\nNow you have " + taskList.list.size() + " in the list");
    }

    public void eventResponse(TaskList taskList, String command) {
        Event event = new Event(command.substring(5));
        taskList.add(event);
        System.out.println("Got it. I've added this task:\n" +
                event + "\nNow you have " + taskList.list.size() + " in the list");
    }
}
