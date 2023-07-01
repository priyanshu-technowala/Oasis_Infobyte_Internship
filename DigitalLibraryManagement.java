import java.util.*;

// Book class representing a book entity
class Book {
    private String title;
    private String author;
    private int id;

    public Book(String title, String author, int id) {
        this.title = title;
        this.author = author;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    // Add a book to the library
    public void addBook(Book book) {
        books.add(book);
    }

    // Remove a book from the library
    public void removeBook(Book book) {
        books.remove(book);
    }

    // Get all books in the library
    public List<Book> getAllBooks() {
        return books;
    }
}

// User class representing a library user
class User {
    public String username;

    User(String username) {
        this.username = username;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
};

// Admin class representing an admin user

class Admin extends User {
    String password;

    public Admin(String username, String password) {
        super(username);
        this.password = password;

    }

    void viewUser(Map<Integer, String> map) {
        System.out.println("List of all the Library Users is:->");
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

    }

    void login() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Admin name:");
        String adminname = scanner.nextLine();

        System.out.print("Enter Password: ");
        String adminpassword = scanner.nextLine();

        if (adminname.equals(username) && adminpassword.equals(password)) {
            System.out.println("yes, Successfully verified you are the Admin!");
        } else {
            System.out.println("No, You are not Admin. You enterd invalid credentials.");
            System.out.println("Try Again With valid credentials if you Are Admin");
            login();

        }
    }

    // Add a book to the library
    public void addBookToLibrary(Library library, Book book) {
        library.addBook(book);
        System.out
                .println("Book added to the library with Title " + book.getTitle() + " and Author " + book.getAuthor());
    }

    // Remove a book from the library
    public void removeBookFromLibrary(Library library, Book book) {
        library.removeBook(book);
        System.out.println(
                "Book removed from the library with Title " + book.getTitle() + " and Author " + book.getAuthor());
    }
}

// User class representing a normal user
class RegularUser extends User {
    int id;

    public RegularUser(String username, int id) {
        super(username);
        this.id = id;
    }

    void insertUserEntryInDatabase(int id, String username, Map<Integer, String> map) {

        map.put(id, username);

    }

    // View all books in the library
    public void viewBooksInLibrary(Library library) {
        List<Book> books = library.getAllBooks();
        System.out.println("Books in the library:");
        for (Book book : books) {
            System.out.println("Title: " + book.getTitle() + ", Author: " + book.getAuthor() + ", ID: " + book.getId());
        }
    }
}

// Main class to test the digital library management system
public class DigitalLibraryManagement {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        Admin admin = new Admin("Priyanshu", "123456789");
        // default user is admin
        RegularUser user = new RegularUser("Priyanshu", 13);

        System.out.println("Welcome to the Digital Library Management System.");
        System.out.println("Are you Admin yes/no");
        String isAdmin = sc.next();
        if (isAdmin.equals("yes")) {
            admin.login();
            System.out.println("What would you like to do?");
            while (true) {
                System.out.println("1. Add book to the Library");
                System.out.println("2. Remove book from the Library");
                System.out.println("3. View books in the Library");
                System.out.println("4. View all the Users who are issuing books");
                System.out.println("5. Exit");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Enter book title");
                        String title = sc.next();
                        System.out.println("Enter book autor");
                        String autor = sc.next();
                        System.out.println("Enter book id");
                        int id = sc.nextInt();
                        Book book = new Book(title, autor, id);
                        admin.addBookToLibrary(library, book);
                        break;
                    case 2:
                        System.out.println("Enter book title");
                        title = sc.next();
                        System.out.println("Enter book autor");
                        autor = sc.next();
                        System.out.println("Enter book id");
                        id = sc.nextInt();
                        book = new Book(title, autor, id);
                        admin.removeBookFromLibrary(library, book);
                        break;
                    case 3:
                        user.viewBooksInLibrary(library);
                        break;
                    case 4:
                        admin.viewUser(map);
                        break;
                    case 5:
                        System.out.println("Thank you for using the Digital Library.");
                        return;
                }
            }

        } else {
            System.out.println("Are you a new User yes/no ");
            String reply = sc.next();

            // Perform login
            int Id;
            String username;
            if (reply.equals("yes")) {
                System.out.println("SignUp New Account");
                System.out.println("Create Id");
                Id = sc.nextInt();
                System.out.println("Enter UserName");
                username = sc.next();
                user.insertUserEntryInDatabase(Id, username, map);
                System.out.println("SignUP successful!");
            } else {

                System.out.println("Login your Account");

                System.out.println("Enter the User ID");
                Id = sc.nextInt();
                System.out.println("Enter the User Name");
                username = sc.next();
                user.insertUserEntryInDatabase(Id, username, map);
            }
            System.out.println(
                    "You are not the Admin so you have to limited priviledge 1. access of book issuing \n 2. viewing Books");
            System.out.println("What would you like to do?");
            while (true) {
                System.out.println("1. Book issue");
                System.out.println("2. View books in the Library");
                System.out.println("3. View all the Users who are issuing books");
                System.out.println("4. Exit");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Enter book title");
                        String title = sc.next();
                        System.out.println("Enter book autor");
                        String autor = sc.next();
                        System.out.println("Enter book id");
                        int id = sc.nextInt();
                        Book book = new Book(title, autor, id);
                        admin.removeBookFromLibrary(library, book);

                        break;
                    case 2:
                        user.viewBooksInLibrary(library);
                        break;
                    case 3:
                        System.out.println("This permission is only for Admin");
                        admin.login();
                        admin.viewUser(map);
                        break;
                    case 4:
                        System.out.println("Thank you for using the Digital Library.");
                        return;

                }
            }
        }
    }
}