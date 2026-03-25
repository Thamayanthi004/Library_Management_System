package hexaware_traning_fds.Library_Management_System;

import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

public class Main{

    static ArrayList<Book> books = new ArrayList<>();
    static ArrayList<Member> members = new ArrayList<>();

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n1.Create Member\n2.Add Book\n3.Issue Book\n4.Return Book\n5.Show Books\n6.Remove Book\n7.Update Book\n8.Remove Member\n9.Exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: createMember(); break;
                case 2: addBook(); break;
                case 3: issueBook(); break;
                case 4: returnBook(); break;
                case 5: showBooks(); break;
                case 6: removeBook(); break;
                case 7: updateBook(); break;
                case 8: removeMember(); break;
                case 9: System.exit(0);
            }
        }
    }

    
    static void createMember() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        members.add(new Member(id, name));
        System.out.println("Member added!");
    }

    
    static void addBook() {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Author: ");
        String author = sc.nextLine();

        books.add(new Book(id, title, author));
        System.out.println("Book added!");
    }

    
    static void issueBook() {
        System.out.print("Enter Member ID: ");
        int mid = sc.nextInt();

        System.out.print("Enter Book ID: ");
        int bid = sc.nextInt();

        Member m = findMember(mid);
        Book b = findBook(bid);

        if (m == null || b == null) {
            System.out.println("Invalid ID!");
            return;
        }

        if (b.isIssued || m.issuedBook != null) {
            System.out.println("Cannot issue!");
            return;
        }

        m.issuedBook = b;
        b.isIssued = true;

        m.issueDate = LocalDate.now();
        m.dueDate = m.issueDate.plusDays(7);

        System.out.println("Book issued!");
    }

    
    static void returnBook() {
        System.out.print("Enter Member ID: ");
        int mid = sc.nextInt();

        Member m = findMember(mid);

        if (m == null || m.issuedBook == null) {
            System.out.println("No book found!");
            return;
        }

        LocalDate returnDate = LocalDate.now();

        long lateDays = ChronoUnit.DAYS.between(m.dueDate, returnDate);

        if (lateDays > 0) {
            System.out.println("Fine: ₹" + lateDays * 10);
        } else {
            System.out.println("No Fine");
        }

        m.issuedBook.isIssued = false;
        m.issuedBook = null;

        System.out.println("Book returned!");
    }

  
    static void showBooks() {
        for (Book b : books) {
            System.out.println(b.id + " | " + b.title + " | " + b.author + " | " + (b.isIssued ? "Issued" : "Available"));
        }
    }

    
    static void removeBook() {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();

        books.removeIf(b -> b.id == id);
        System.out.println("Book removed!");
    }

    
    static void updateBook() {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        Book b = findBook(id);
        if (b == null) return;

        System.out.print("New Title: ");
        b.title = sc.nextLine();

        System.out.print("New Author: ");
        b.author = sc.nextLine();

        System.out.println("Updated!");
    }

    
    static void removeMember() {
        System.out.print("Enter Member ID: ");
        int id = sc.nextInt();

        members.removeIf(m -> m.id == id);
        System.out.println("Member removed!");
    }

    
    static Member findMember(int id) {
        for (Member m : members) {
            if (m.id == id) return m;
        }
        return null;
    }

    static Book findBook(int id) {
        for (Book b : books) {
            if (b.id == id) return b;
        }
        return null;
    }
}