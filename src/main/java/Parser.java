public class Parser {

    public static TaskList list_of_Content;

    public static boolean exit(String input) {
        return input.equals("bye");
    }

    public static void initiateTaskList(TaskList list) {
        list_of_Content = list;
    }
    
    public static void processInput(String input) throws InvalidException {
        
            int length = input.length();
            String[] isDone = input.split(" ");
            String firstChar = isDone[0];
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list: ");
                list_of_Content.showAllContent();
            } else if (firstChar.equals("find")) {
                String keyword = isDone[isDone.length - 1];
                list_of_Content.findKeyword(keyword);
            } else if (firstChar.equals("done")) {

                if (isDone.length == 1) {
                    throw new InvalidDoneException("OOPS!!! please provide me with the task to be marked as done");
                }

                if (isDone.length > 2) {
                    throw new InvalidDoneException("OOPS!!! I can only mark one task as done at a time");
                }
                String lastChar = isDone[isDone.length - 1];
                int index = Integer.parseInt(lastChar);

                if (index > list_of_Content.getSizeOfList()) {
                    int no_of_tasks = list_of_Content.getSizeOfList();
                    throw new InvalidException("There are only " + no_of_tasks + " tasks in the list; Please restate" +
                            " the task to be mark as done");
                }

                Task current = list_of_Content.getTheList().get(index - 1);
                current.set_Task_As_Done();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(current.timeConverted());

            } else if (firstChar.equals("delete")) {

                if (isDone.length == 1) {
                    throw new InvalidDeleteException("OOPS!!! please provide me with the task to be deleted");
                }

                if (isDone.length > 2) {
                    throw new InvalidDeleteException("OOPS!!! I can only delete one task at a time");
                }

                String lastChar = isDone[isDone.length - 1];
                int index = Integer.parseInt(lastChar);

                if (index > list_of_Content.getSizeOfList()) {
                    int no_of_tasks = list_of_Content.getSizeOfList();
                    throw new InvalidException("There are only " + no_of_tasks + " tasks in the list; Please restate" +
                            " the task to be deleted");
                }
                Task toBeRemove = list_of_Content.getTheList().get(index - 1);
                list_of_Content.removeTask(index - 1);
                int no_of_tasks_left = list_of_Content.getSizeOfList();
                System.out.println(" Noted. I've removed this task:  ");
                System.out.println(toBeRemove);
                System.out.println(" Now you have " + no_of_tasks_left + " tasks in the list. ");
            } else {
                if (firstChar.equals("todo")) {

                    if (isDone.length == 1) {
                        throw new InvalidTodoException("OOPS!!! The description of a todo cannot be empty." +
                                "please provide me with the task to be completed");
                    }
                    ToDo new_task = new ToDo(input.substring(firstChar.length() + 1));
                    list_of_Content.addTask(new_task);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(new_task.timeConverted());
                    System.out.println("Now you have " + list_of_Content.getSizeOfList() + " tasks in the list.");
                } else if (firstChar.equals("deadline")) {
                    
//                    System.out.println("reach deadline");

                    if (isDone.length == 1) {
                        throw new InvalidDeadlineException("OOPS!!! The description of a task cannot be empty." +
                                "please provide me with the name and duration of the task to be completed");
                    }

                    if (input.split("/by").length < 2) {
                        throw new InvalidDeadlineException("OOPS!!! please provide me with the" +
                                " duration of the task to be completed");
                    }
                    int index = input.indexOf("/by");
                   
                    String task = input.substring(firstChar.length() + 1, index);
                    String time = input.substring(index + 4);
                    Deadline deadline = new Deadline(task, time);
                    list_of_Content.addTask(deadline);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(deadline.timeConverted());
                    System.out.println("Now you have " + list_of_Content.getSizeOfList() + " tasks in the list.");
                } else if (firstChar.equals("event")) {

                    if (isDone.length == 1) {
                        throw new InvalidDeadlineException("OOPS!!! The description of a task cannot be empty." +
                                "please provide me with the name and time of the task");
                    }

                    if (input.split("/at").length < 2) {
                        throw new InvalidDeadlineException("OOPS!!! please provide me with the" +
                                " time of the task to be completed");
                    }

                    int index = input.indexOf("/at");
                    String task = input.substring(firstChar.length() + 1, index);
                    String duration = input.substring(index + 4);
                    Event event = new Event(task, duration);
                    list_of_Content.addTask(event);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(event.timeConverted());
                    System.out.println("Now you have " + list_of_Content.getSizeOfList() + " tasks in the list.");
                } else {
                    throw new InvalidException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }

        }
    
}
