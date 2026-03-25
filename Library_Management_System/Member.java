package hexaware_traning_fds.Library_Management_System;
import java.time.LocalDate;
public class Member {
    int id;
    String name;
    Book issuedBook;
    LocalDate issueDate;
    LocalDate dueDate;

    Member(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
