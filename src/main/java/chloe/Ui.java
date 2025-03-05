package chloe;

import java.util.Scanner;

import chloe.exceptions.ParseErrorException;

public class Ui {
    // Line used when printing replies
    private static final String LINE = "\t**********************************************";
    static Parser parser = new Parser();

    public static void run() {
        sayHi(); // Greet the user
        Scanner scanner = new Scanner(System.in); // initiate scanner

        // Interactive loop
        while (true) {
            // Read user entries
            if (!scanner.hasNextLine()) { // Avoid reading if no input is available
                break;
            }
            String userEntry = scanner.nextLine();
            if (userEntry.equals("bye")) {
                break;
            }
            try {
                parser.handleCommand(userEntry);
            } catch (ParseErrorException e) {
                System.out.println(LINE);
                System.out.println(e.getMessage());
                System.out.println(LINE);
            }
        }

        scanner.close();
        sayBye(); // Say goodbye
    }

    /**
     * Displays a greeting message from Chloe.
     * This method prints a decorative line, a friendly message,
     * and another decorative line.
     */
    public static void sayHi() {
        System.out.println(LINE);
        System.out.println("\tHi! I'm Chloe!");
        System.out.println("\tHow can I help you today?");
        System.out.println(LINE);
    }

    /**
     * Displays a goodbye message from Chloe.Chloe.
     * This method prints a decorative line, a farewell message,
     * and another decorative line.
     */
    public static void sayBye() {
        System.out.println(LINE);
        System.out.println("\tOK bye!");
        System.out.println(LINE);
    }
}
