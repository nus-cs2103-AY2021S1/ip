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

                    Task.write(input);

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
