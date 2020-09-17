package Mattbot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class testing {
    public Stream<String> inputStream;
    public String input;
    public static String helper(String input) {
        return input;
    }

    public void convertToStream() {
        Scanner sc = new Scanner(this.input);
        ArrayList<String> comboCommands = new ArrayList<>();
        String sum = "";
        while (sc.hasNext()) {
            String current = sc.next();
            if (gotComma(current)) {
                current = current.substring(0, current.length() - 1);
                sum += current + " ";
                comboCommands.add(sum);
                sum = "";
            } else {
                sum += current + " ";
            }
        }
        comboCommands.add(sum);
        System.out.println(comboCommands);
        Stream<String> comboCommandStream = comboCommands.stream();
        this.inputStream = comboCommandStream;
    }

    private static boolean gotComma(String word) {
        int lengthOfCurrent = word.length();
        boolean haveComma = false;
        if (word.charAt(lengthOfCurrent - 1) == ',') {
            haveComma = true;
        }
        return haveComma;
    }

    public static void main(String[] args) {
//        String[] test = {"1", "2", "3", "4", "5"};
//        Stream<String> testStream = Arrays.stream(test);
//        testStream.forEach(item->System.out.println(helper(item)));
        testing t = new testing();
        Scanner sc = new Scanner(System.in);
        t.input = sc.nextLine();
//        System.out.println(t.input);
        t.convertToStream();
        t.inputStream.forEach(x->System.out.println(x));
//        t.inputStream.peek(p->System.out.println(p));
    }
}
