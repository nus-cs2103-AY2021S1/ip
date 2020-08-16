import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static String longLine = "________________________________________________________________________________";
    private static ArrayList<String> theList = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        lineFormatter("Hello!!!! I'm Duke \nWhat can I do for you?!?!?!" );
        add_input();

    }

    public static void echo_input(){

        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String input = scanner.nextLine();
            if(input.toLowerCase().equals("bye")){
                byeGreetings();
                break;
            } else {
                lineFormatter(input);
            }
        }
    }

    public static void add_input(){
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String input = scanner.nextLine();
            if(input.toLowerCase().equals("list")){
                StringBuffer result = new StringBuffer();
                for(int i = 0; i < theList.size(); i ++){
                    result.append((i + 1)+ ". " + theList.get(i) + "\n");
                }
                lineFormatter(result.toString());
            } else if(input.toLowerCase().equals("bye")){
                byeGreetings();
                break;
            } else {
                added_to_List(input);
            }
        }
    }

    public static void added_to_List(String printable) {
        lineFormatter("added: " + printable);
        theList.add(printable);


    }
    public static void lineFormatter (String printable){
        System.out.println(longLine);
        System.out.println(printable);
        System.out.println(longLine);
    }

    public static void byeGreetings () {
        lineFormatter("Bye! Hope to see you soon again?!");
    }


}
