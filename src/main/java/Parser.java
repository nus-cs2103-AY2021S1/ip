public class Parser {

    public static TaskList listOfContent;

    /**
     * Returns a boolean to indicate whether program should exit.
     * @param input user input.
     * @return Returns a boolean.
     */
    public static boolean exit(String input) {
        return input.equals("bye");
    }

    /**
     * initiate taskList for Parser.
     * @param list TaskList that manages tasks.
     */
    public static void initiateTaskList(TaskList list) {
        listOfContent = list;
    }

    /**
     * Processes input and add the tasks to taskList.
     * @param input user input.
     * @throws InvalidException InvalidException if input is illegal.
     * @return
     */
    public static String processInput(String input) throws InvalidException {
        
            int length = input.length();
            String[] isDone = input.split(" ");
            String firstChar = isDone[0];

        if (input.equals("bye")) {
            Storage.write(listOfContent);
            return Ui.exitMessage();
   
        }
        if (input.equals("list")) {
                System.out.println("Here are the tasks in your list: ");
                return listOfContent.showAllContent();
            } else if (firstChar.equals("find")) {
                String keyword = isDone[isDone.length - 1];
                return listOfContent.findKeyword(keyword);
            } else if (firstChar.equals("done")) {

                if (isDone.length == 1) {
                    throw new InvalidDoneException("OOPS!!! please provide me with the task to be marked as done");
                }

                if (isDone.length > 2) {
                    throw new InvalidDoneException("OOPS!!! I can only mark one task as done at a time");
                }
                String lastChar = isDone[isDone.length - 1];
                int index = Integer.parseInt(lastChar);

                if (index > listOfContent.getSizeOfList()) {
                    int no_of_tasks = listOfContent.getSizeOfList();
                    throw new InvalidException("There are only " + no_of_tasks + " tasks in the list; Please restate" +
                            " the task to be mark as done");
                }

                Task current = listOfContent.getTheList().get(index - 1);
                current.setTaskAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(current.timeConverted());
                return current.timeConverted();

            } else if (firstChar.equals("delete")) {

                if (isDone.length == 1) {
                    throw new InvalidDeleteException("OOPS!!! please provide me with the task to be deleted");
                }

                if (isDone.length > 2) {
                    throw new InvalidDeleteException("OOPS!!! I can only delete one task at a time");
                }

                String lastChar = isDone[isDone.length - 1];
                int index = Integer.parseInt(lastChar);

                if (index > listOfContent.getSizeOfList()) {
                    int no_of_tasks = listOfContent.getSizeOfList();
                    throw new InvalidException("There are only " + no_of_tasks + " tasks in the list; Please restate" +
                            " the task to be deleted");
                }
                Task toBeRemove = listOfContent.getTheList().get(index - 1);
                            int no_of_tasks_left = listOfContent.getSizeOfList();
                System.out.println(" Noted. I've removed this task:  ");
                System.out.println(toBeRemove);
                System.out.println(" Now you have " + no_of_tasks_left + " tasks in the list. ");
                return listOfContent.removeTask(index - 1);

            } else {
                if (firstChar.equals("todo")) {

                    if (isDone.length == 1) {
                        throw new InvalidTodoException("OOPS!!! The description of a todo cannot be empty." +
                                "please provide me with the task to be completed");
                    }
                    ToDo new_task = new ToDo(input.substring(firstChar.length() + 1));
                    listOfContent.addTask(new_task);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(new_task.timeConverted());
                    System.out.println("Now you have " + listOfContent.getSizeOfList() + " tasks in the list.");
                    return listOfContent.addStringTask(new_task);

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
                    listOfContent.addTask(deadline);
                                        System.out.println("Got it. I've added this task: ");
                    System.out.println(deadline.timeConverted());
                    System.out.println("Now you have " + listOfContent.getSizeOfList() + " tasks in the list.");
                    return listOfContent.addStringTask(deadline);

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
                    Task event = new Event(task, duration);
                    listOfContent.addTask(event);
                                        System.out.println("Got it. I've added this task: ");
                    System.out.println(event.timeConverted());
                    System.out.println("Now you have " + listOfContent.getSizeOfList() + " tasks in the list.");
                    return listOfContent.addStringTask(event);

                } else {
                    throw new InvalidException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }

//        return firstChar;
    }
    
}
