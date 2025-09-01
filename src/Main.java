import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LibraryManager lm = new LibraryManager();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Book");
            System.out.println("4. Delete Book");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. View Issued Books Only");
            System.out.println("8. View Overdue Books");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: lm.addBook(); break;
                case 2: lm.viewBooks(); break;
                case 3: lm.searchBook(); break;
                case 4: lm.deleteBook(); break;
                case 5: lm.issueBook(); break;
                case 6: lm.returnBook(); break;
                case 7: lm.viewIssuedBooks(); break;
                case 8: lm.viewOverdueBooks(); break;
                case 0: System.out.println("Goodbye!"); break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 0);

        sc.close();
    }
}
