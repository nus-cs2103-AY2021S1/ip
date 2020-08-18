import java.util.Scanner;

public class Duke {

//    public static void echo(String s1){
//        System.out.println(s1);
//    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo +
                "People call me Duke the all-known ," +
                "ask me anything by typing a line.");

        Scanner sc = new Scanner(System.in);
        String input;

        for(int i = 0; i < 100000; i ++){

            input = sc.nextLine();

            if(!input.equals("bye")){
                //Duke.echo(input);
                if(!input.equals("list")){

                    if(!input.contains("done")) {

                        if(input.contains("todo")) {

                            Task.write(input, "todo", "");

                        }else {
                            String byOrAt = input.substring(input.indexOf("/") + 4);

                            if(input.contains("deadline")){

                                Task.write(input, "deadline", byOrAt);

                            }else if(input.contains("event")){

                                Task.write(input, "event", byOrAt);

                            }
                        }

                    }else{

                        int ref = Integer.parseInt(Character.toString(input.charAt(5))) - 1;

                        if(Task.taskStorage[ref] != null) {

                            Task done = Task.taskStorage[ref].markAsDone();
                            Task.taskStorage[ref] = done;

                            System.out.println("Nice! I've marked this task as done:\n"
                                    + Task.taskStorage[ref]);

                        }else{

                            System.out.println("I am afraid that it is not possible" +
                                    "to do an unknown task.");

                        }

                    }

                }else{

                    Task.read();
                }
            }else {
                System.out.println(" Bye. Hope to see you again soon!");
                sc.close();
                System.exit(0);
            }
        }
    }
}
