import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IncorrectInputException, IOException {
        Scanner sc = new Scanner(System.in);

        Chatbot bot = new Chatbot();
        bot.greeting();

        try {
            File file = new File("src/main/java/tasklist.txt");
            file.getParentFile().mkdirs();
//            System.out.println(file.exists());
            if (!file.exists()) {
                file.createNewFile();
            } else {
                Scanner reader = new Scanner(file);
                System.out.println("Here is your current tasks: ");
                while (reader.hasNextLine()) {
                    String data = reader.nextLine();
                    System.out.println(data);
                }
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("File cannot be found/ opened");
            e.printStackTrace();
        }

        while (sc.hasNext() && bot.chat(sc.nextLine())) {
//            String next = sc.next();
//            bot.chat(next);
        }
        sc.close();
    }
}
