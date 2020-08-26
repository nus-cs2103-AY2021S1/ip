package main.java.duke;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public static String memory_file_dir = "./data/";
    public static String memory_file_name = "task_list.txt";

    public Duke(String memory_file_dir, String memory_file_name) {
        this.storage = new Storage(memory_file_dir, memory_file_name);
        try {
            this.tasks = new TaskList(storage.readMemoTasks(), memory_file_dir, memory_file_name);
        } catch (Exception ex) {
            HandleException.handleException(DukeException.ExceptionType.read_file);
            tasks = new TaskList();
            System.out.println(SpecialFormat.indent + "Let's start without the file!");
        }
        this.ui = new Ui(memory_file_dir, memory_file_name, tasks.task_collections);
    }

    public void run() {
        ui.processRequests();
    }

    public static void main(String[] args) {
        new Duke(memory_file_dir, memory_file_name).run();
    }


    /*public static String taskToMemoStr(Task t) {
        String memoStr = "";
        String[] info = t.getInfo();
        memoStr += "\n" + info[0] + split_notn + info[1] + split_notn + info[2];
        if (t.getType().equals("D") || t.getType().equals("E")) {
            memoStr += split_notn + info[3];
        }
        return memoStr;
    }

    public static void write_memory(List<Task> task_list) {
        try {
            FileWriter fw = new FileWriter(memory_file_dir + memory_file_name);
            String textToAppend = "";
            Iterator task_iter = task_list.iterator();
            while (task_iter.hasNext()) {
                Task t = (Task) task_iter.next();
                textToAppend += taskToMemoStr(t);
            }
            fw.write(textToAppend);
            fw.close();
        } catch (Exception ex) {
            handleException(DukeException.ExceptionType.read_file);
        }
    }

    public static void appendToFile(String filePath, Task t) {
        try {
            FileWriter fw = new FileWriter(filePath, true); // appending instead of overwriting
            fw.write(taskToMemoStr(t));
            fw.close();
        } catch (Exception ex) {
            handleException(DukeException.ExceptionType.read_file);
        }
    }*/

    /*public static DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDateTime(
            FormatStyle.MEDIUM, FormatStyle.SHORT);

    public static String processTime(String dateTime) {
        String date;
        String time;

        try {
            if (dateTime.length() > 11) {
                String[] parts = dateTime.split(" ", 2);
                time = parts[0].length() == 4 ? parts[0] : parts[1];
                date = parts[0].length() == 4 ? parts[1] : parts[0];
            } else {
                date = dateTime;
                time = "2359";
            }

            LocalTime lt = LocalTime.of(
                    Integer.parseInt(time.substring(0, 2)), Integer.parseInt(time.substring(2)));

            date = date.replaceAll("\\D", "-");
            String[] date_seg = date.split("-", 3);

            if (date_seg[0].length() != 4) {
                String temp = date_seg[0];
                date_seg[0] = date_seg[2];
                date_seg[2] = temp;
            }
            LocalDate ld = LocalDate.parse(date_seg[0] + "-" + date_seg[1] + "-" + date_seg[2]);

            LocalDateTime ldt = LocalDateTime.of(ld, lt);
            return ldt.format(dtf);
        } catch (Exception ex) {
            handleException(DukeException.ExceptionType.improper_dateTime);
        }
        return "Unknown Date/Time";
    }*/



    /*public static void readTasks() {
        task_collections = new ArrayList<>();
        File data_folder = new File(memory_file_dir);

        if (!data_folder.exists() && !data_folder.isDirectory()) {
            data_folder.mkdirs();
        }

        File task_list_file = new File(memory_file_dir + memory_file_name);
        if (!task_list_file.exists()) {
            try {
                task_list_file.createNewFile();
            } catch (Exception e) {
                handleException(DukeException.ExceptionType.read_file);
            }
        }

        Scanner sc = null;
        try {
            sc = new Scanner(task_list_file);
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, the memory cannot be read successfully.");
        }

        while (sc.hasNextLine()) {
            String curr_task = sc.nextLine();
            String[] temp_type = curr_task.split(split_notn, 2);
            String[] temp_details;
            switch(temp_type[0]) {
                case "T":
                    temp_details = temp_type[1].split(split_notn, 2);
                    task_collections.add(
                            new Todo(temp_details[1], temp_details[0].equals("0") ? false : true));
                case "E":
                    temp_details = temp_type[1].split(split_notn, 3);
                    task_collections.add(
                            new Event(temp_details[1], temp_details[2],
                                    temp_details[0].equals("0") ? false : true));
                    break;
                case "D":
                    temp_details = temp_type[1].split(split_notn, 3);
                    task_collections.add(
                            new Deadline(temp_details[1], temp_details[2],
                                    temp_details[0].equals("0") ? false : true));
                    break;
                default:
                    System.out.println();
            }
        }
    }*/

    /*public static void editMemoFile(String action, String[] input_split_arr) {
        int action_number = -1;
        String success_result = "";
        try {
            action_number = Integer.parseInt(input_split_arr[1]);
        } catch (Exception ex) {
            handleException(DukeException.ExceptionType.empty_illegal);
        }
        if (exception_absent) {
            try {
                if (action.equals("delete")) {
                    success_result = task_collections.get(action_number - 1).toString();
                    task_collections.remove(action_number - 1);
                } else {
                    task_collections.get(action_number - 1).markAsDone();
                }
                write_memory(task_collections);
            } catch (Exception ex) {
                handleException(DukeException.ExceptionType.empty_illegal);
            }
        }
        if (exception_absent) {
            if (action.equals("delete")) {
                System.out.println(indent + "Noted. I've removed this task:");
                System.out.println(indent + success_result);
                System.out.println(indent + "Now you have " + task_collections.size() + " tasks in the list.");
            } else {
                System.out.println(indent + "Nice! I've marked this task as done:");
                System.out.println(indent + "  [\u2713] " + task_collections.get(action_number - 1).toString().split("] ", 2)[1]);
            }
        }
    }

    public static void addMemoFile(String type, String[] input_split_arr) {
        Task t = null;
        if (!type.equals("todo")) {
            try {
                input_split_arr = input_split_arr[1].split(
                        type.equals("event") ? " /at " : " /by ", 2);
            } catch (Exception ex) {
                handleException(type.equals("deadline")
                        ? DukeException.ExceptionType.deadline_empty_incomplete
                        : DukeException.ExceptionType.event_empty_incomplete);
            }
        }
        if (exception_absent) {
            try {
                if (type.equals("todo")) {
                    t = new Todo(input_split_arr[1]);
                } else {
                    String time;
                    try {
                        time = input_split_arr[1];
                        //time = input_split_arr[1].split(" ", 2)[1];
                        time = processTime(time);
                        t = type.equals("event")
                                ? new Event(input_split_arr[0], time)
                                : new Deadline(input_split_arr[0], time);
                    } catch (Exception ex) {
                        handleException(type.equals("event")
                                ? DukeException.ExceptionType.event_empty_incomplete
                                : DukeException.ExceptionType.deadline_empty_incomplete);
                    }
                }
            } catch (Exception ex) {
                handleException(type.equals("todo")
                        ? DukeException.ExceptionType.todo_empty
                        : type.equals("event")
                        ? DukeException.ExceptionType.event_empty_incomplete
                        : DukeException.ExceptionType.deadline_empty_incomplete);
            }
            if (exception_absent) {
                task_collections.add(t);
                appendToFile(memory_file_dir + memory_file_name, t);
                if (exception_absent) {
                    System.out.println(indent + "Got it. I've added ths task:");
                    System.out.println(indent + "  " + task_collections.get(task_collections.size() - 1));
                    System.out.println(indent + "Now you have " + task_collections.size() + " tasks in the list.");
                }
            }
        }
    }*/

    /*public static void main(String[] args){
        readTasks();

        String greeting = starting_line + "Hello! This is J.A.R.V.I.S.\n" + indent + "How may I help you?" + ending_line;
        System.out.println(greeting);

        Scanner sc = new Scanner(System.in);
        boolean exit_bye = false;
        while (!exit_bye) {
            String input = sc.nextLine();
            exception_absent = true;
            if (input.equals("bye")) {
                System.out.println(starting_line + "Bye. Hope to see you again soon!" +  ending_line);
                exit_bye = true;
            } else {
                System.out.println(separation_line);
                if (input.equals("list")) {
                    int temp = 1;
                    System.out.println(indent + "Here are the tasks in your list:");
                    Iterator task_iter = task_collections.iterator();
                    while (task_iter.hasNext()) {
                        System.out.println(indent + temp + "." + task_iter.next());
                        temp++;
                    }
                } else {
                    String[] input_split_arr = input.split(" ", 2);
                    String type = input_split_arr[0];
                    if (type.equals("done") || type.equals("delete")) {
                        editMemoFile(type, input_split_arr);  //editTask(type, input_split_arr);
                    }
                    else if (type.equals("deadline") || type.equals("event") || type.equals("todo")){
                        addMemoFile(type, input_split_arr);   //addTask(type, input_split_arr);

                    } else {
                        handleException(DukeException.ExceptionType.no_meaning);
                    }
                }
                System.out.println(separation_line + "\n");
            }
        }
    }*/

}

//compile when current directory is at IndividualProject/text-ui-test
//javac -cp ../src/ ../src/main/java/Task.java   etc. (Task, Deadline, Event, Todo, Duke)
//sh runtest.sh



/*public static void editTask(String action, String[] input_split_arr) {
        int action_number = -1;
        String success_result = "";
        try {
            action_number = Integer.parseInt(input_split_arr[1]);
        } catch (Exception ex) {
            handleException(DukeException.ExceptionType.empty_illegal);
        }
        if (exception_absent) {
            try {
                if (action.equals("delete")) {
                    success_result = task_collections.get(action_number - 1).toString();
                    task_collections.remove(action_number - 1);
                } else {
                    task_collections.get(action_number - 1).markAsDone();
                }
            } catch (Exception ex) {
                handleException(DukeException.ExceptionType.empty_illegal);
            }
        }
        if (exception_absent) {
            if (action.equals("delete")) {
                System.out.println(indent + "Noted. I've removed this task:");
                System.out.println(indent + success_result);
                System.out.println(indent + "Now you have " + task_collections.size() + " tasks in the list.");
            } else {
                System.out.println(indent + "Nice! I've marked this task as done:");
                System.out.println(indent + "  [\u2713] " + task_collections.get(action_number - 1).toString().split("] ", 2)[1]);
            }
        }
    }

    public static void addTask(String type, String[] input_split_arr) {
        Task t = null;
        if (!type.equals("todo")) {
            try {
                input_split_arr = input_split_arr[1].split(" /", 2);
            } catch (Exception ex) {
                handleException(type.equals("deadline")
                        ? DukeException.ExceptionType.deadline_empty_incomplete
                        : DukeException.ExceptionType.event_empty_incomplete);
            }
        }
        if (exception_absent) {
            try {
                if (type.equals("todo")) {
                    t = new Todo(input_split_arr[1]);
                    System.out.println(input_split_arr[1]);
                } else {
                    String time;
                    try {
                        time = input_split_arr[1].split(" ", 2)[1];
                        t = type.equals("event")
                                ? new Event(input_split_arr[0], time)
                                : new Deadline(input_split_arr[0], time);
                    } catch (Exception ex) {
                        handleException(type.equals("event")
                                ? DukeException.ExceptionType.event_empty_incomplete
                                : DukeException.ExceptionType.deadline_empty_incomplete);
                    }
                }
            } catch (Exception ex) {
                handleException(type.equals("todo")
                        ? DukeException.ExceptionType.todo_empty
                        : type.equals("event")
                            ? DukeException.ExceptionType.event_empty_incomplete
                            : DukeException.ExceptionType.deadline_empty_incomplete);
            }
            if (exception_absent) {
                task_collections.add(t);
                if (exception_absent) {
                    System.out.println(indent + "Got it. I've added ths task:");
                    System.out.println(indent + "  " + task_collections.get(task_collections.size() - 1));
                    System.out.println(indent + "Now you have " + task_collections.size() + " tasks in the list.");
                }
            }
        }
    }*/