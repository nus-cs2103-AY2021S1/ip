import bot.Bot;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bot bot = new Bot("Straw Bot", "./assets/userData.txt");
        bot.init(new Scanner(System.in));
    }
}