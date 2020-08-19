import javax.swing.text.html.Option;

public class Formating<T> {
    private final T content;

    public Formating(T content) {
        this.content = content;
    }

    public T getContent() {
        return this.content;
    }

    public Formating<String> shorten() {
        try {
            String input = (String) content;
            int length = input.length();
            int frontPos = 0;
            int backPos = length - 1;
            while (frontPos < length && input.charAt(frontPos) == ' ') {
                frontPos++;
            }

            while (backPos >= 0 && input.charAt(backPos) == ' ') {
                backPos--;
            }
            return new Formating<String>(input.substring(frontPos, backPos + 1));
        } catch (ClassCastException e) {
            System.out.println(new Formating<>(Status.CLASSCASTEXCEPTION.toString()));
            return null;
        }
    }

    @Override
    public String toString() {
        String underscore =
                "  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + "\n";

        if (this.content instanceof Task) {

            return underscore +
                    Status.TASKADDED.toString() +
                    content + "\n" +
                    String.format(Status.REPORT.toString(), Operation.memory.getMemory().size()) +
                    "\n" +
                    underscore;
        }

        return underscore +
                content + "\n" +
                underscore;
    }
}

