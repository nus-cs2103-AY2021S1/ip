import java.io.*;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Yoo ( ^-^)/ \nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        try {
            RunYoo.run(sc);
        } catch (YooException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        sc.close();
    }
}