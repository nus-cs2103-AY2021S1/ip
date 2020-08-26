public class Parser {
    public Command parseInput(String input) throws InvalidInputException,
            InvalidCommandException {
        if (input.length() == 0) {
          throw new InvalidInputException("Please input something");
        }
        String[] words = input.split(" ");
        try {
          return Command.valueOf(words[0].toUpperCase());
        } catch (IllegalArgumentException e) {
          throw new InvalidCommandException("What's that again? I can't understand.");
        }
    }

    public String parseTodo(String input) throws InvalidInputException {
        String[] words = input.split(" ");
        StringBuilder name = new StringBuilder();
        for (int i = 1; i < words.length; i++) {
            name.append(words[i]);
            if (i != words.length - 1) {
                name.append(" ");
            }
        }
        if (name.length() == 0) {
            throw new InvalidInputException("Sorry, do what? Please give me a valid input. Thank you.");
        }
        return name.toString();
    }

    public String[] parseDeadline(String input) throws InvalidInputException {
        String[] output = new String[2];
        String[] words = input.split(" ");
        StringBuilder name = new StringBuilder();
        StringBuilder deadline = new StringBuilder();
        Boolean deadlineWords = false;
        for (int i = 1; i < words.length; i++) {
            if (words[i].equals("/by")) {
                name.deleteCharAt(name.length() - 1);
                deadlineWords = true;
                i++;
            }
            if (deadlineWords) {
                deadline.append(words[i]);
                deadline.append(" ");
            } else {
                name.append(words[i]);
                name.append(" ");
            }
        }
        if (name.length() == 0 || deadline.length() == 0) {
            throw new InvalidInputException("Sorry, do what? Please give me a valid input. Thank you.");
        }
        deadline.deleteCharAt(deadline.length() - 1);
        output[0] = name.toString();
        output[1] = deadline.toString();
        return output;
    }

    public String[] parseEvent(String input) throws InvalidInputException {
        String[] output = new String[2];
        String[] words = input.split(" ");
        StringBuilder name = new StringBuilder();
        StringBuilder deadline = new StringBuilder();
        Boolean deadlineWords = false;
        for (int i = 1; i < words.length; i++) {
            if (words[i].equals("/at")) {
                name.deleteCharAt(name.length() - 1);
                deadlineWords = true;
                i++;
            }
            if (deadlineWords) {
                deadline.append(words[i]);
                deadline.append(" ");
            } else {
                name.append(words[i]);
                name.append(" ");
            }
        }
        if (name.length() == 0 || deadline.length() == 0) {
            throw new InvalidInputException("Sorry, do what? Please give me a valid input. Thank you.");
        }
        deadline.deleteCharAt(deadline.length() - 1);
        output[0] = name.toString();
        output[1] = deadline.toString();
        return output;
    }

    public int parseIndex(String input) throws InvalidInputException {
        String[] words = input.split(" ");
        if (words.length != 2) {
            throw new InvalidInputException("Sorry, do what? Please give me a valid input. Thank you.");
        }
        return Integer.parseInt(words[1]);
    }
}
