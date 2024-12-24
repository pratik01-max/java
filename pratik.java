public class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    // Constructor
    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
    }

    // Getters and Setters
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    // Display book details
    public void displayBook() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("Available: " + (isAvailable ? "Yes" : "No"));
    }
}

public class User {
    private String name;
    private String userId;
    private List<Book> borrowedBooks;

    // Constructor
    public User(String name, String userId) {
        this.name = name;
        this.userId = userId;
        this.borrowedBooks = new ArrayList<>();
    }

    // Methods
    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public void borrowBook(Book book) {
        if (book.isAvailable()) {
            borrowedBooks.add(book);
            book.setAvailable(false);  // Mark book as unavailable
            System.out.println(name + " has borrowed: " + book.getTitle());
        } else {
            System.out.println("Sorry, this book is currently unavailable.");
        }
    }

    public void returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            book.setAvailable(true); // Mark book as available
            System.out.println(name + " has returned: " + book.getTitle());
        } else {
            System.out.println("You didn't borrow this book.");
        }
    }

    public void displayUserInfo() {
        System.out.println("User ID: " + userId);
        System.out.println("Name: " + name);
        System.out.println("Books borrowed: ");
        for (Book book : borrowedBooks) {
            System.out.println("- " + book.getTitle());
        }
    }
}

import java.util.*;

public class Library {
    private Map<String, Book> books;  // Book collection
    private Map<String, User> users;  // Registered users

    // Constructor
    public Library() {
        books = new HashMap<>();
        users = new HashMap<>();
    }

    // Add a new book
    public void addBook(String title, String author, String isbn) {
        Book newBook = new Book(title, author, isbn);
        books.put(isbn, newBook);
        System.out.println("Book added: " + title);
    }

    // Add a new user
    public void addUser(String name, String userId) {
        User newUser = new User(name, userId);
        users.put(userId, newUser);
        System.out.println("User added: " + name);
    }

    // Display all books
    public void displayBooks() {
        for (Book book : books.values()) {
            book.displayBook();
        }
    }

    // Search for a book by title
    public void searchBookByTitle(String title) {
        for (Book book : books.values()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.displayBook();
                return;
            }
        }
        System.out.println("No book found with that title.");
    }

    // Borrow a book
    public void borrowBook(String userId, String isbn) {
        User user = users.get(userId);
        Book book = books.get(isbn);

        if (user != null && book != null) {
            user.borrowBook(book);
        } else {
            System.out.println("Invalid user or book.");
        }
    }

    // Return a book
    public void returnBook(String userId, String isbn) {
        User user = users.get(userId);
        Book book = books.get(isbn);

        if (user != null && book != null) {
            user.returnBook(book);
        } else {
            System.out.println("Invalid user or book.");
        }
    }
}

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        // Pre-add some books and users for demonstration
        library.addBook("The Great Gatsby", "F. Scott Fitzgerald", "123456789");
        library.addBook("1984", "George Orwell", "987654321");
        library.addUser("Alice", "user001");
        library.addUser("Bob", "user002");

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Add User");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Display Books");
            System.out.println("6. Search Book by Title");
            System.out.println("7. Display User Info");
            System.out.println("8. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter book ISBN: ");
                    String isbn = scanner.nextLine();
                    library.addBook(title, author, isbn);
                    break;
                case 2:
                    System.out.print("Enter user name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter user ID: ");
                    String userId = scanner.nextLine();
                    library.addUser(name, userId);
                    break;
                case 3:
                    System.out.print("Enter user ID: ");
                    userId = scanner.nextLine();
                    System.out.print("Enter book ISBN to borrow: ");
                    isbn = scanner.nextLine();
                    library.borrowBook(userId, isbn);
                    break;
                case 4:
                    System.out.print("Enter user ID: ");
                    userId = scanner.nextLine();
                    System.out.print("Enter book ISBN to return: ");
                    isbn = scanner.nextLine();
                    library.returnBook(userId, isbn);
                    break;
                case 5:
                    library.displayBooks();
                    break;
                case 6:
                    System.out.print("Enter book title to search: ");
                    title = scanner.nextLine();
                    library.searchBookByTitle(title);
                    break;
                case 7:
                    System.out.print("Enter user ID: ");
                    userId = scanner.nextLine();
                    User user = library.users.get(userId);
                    if (user != null) {
                        user.displayUserInfo();
                    } else {
                        System.out.println("User not found.");
                    }
                    break;
                case 8:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Random;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {
    // Game settings
    private final int TILE_SIZE = 20;
    private final int WIDTH = 600;
    private final int HEIGHT = 600;
    private final int GAME_SPEED = 100; // Milliseconds

    private LinkedList<Point> snake;
    private Point food;
    private String direction;
    private boolean gameOver;
    private Timer gameTimer;

    // Constructor
    public SnakeGame() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        snake = new LinkedList<>();
        snake.add(new Point(100, 100)); // Initial snake position
        direction = "RIGHT"; // Snake starts moving right
        spawnFood();

        gameOver = false;
        gameTimer = new Timer(GAME_SPEED, this);
        gameTimer.start();
    }

    // Start a new game
    private void resetGame() {
        snake.clear();
        snake.add(new Point(100, 100)); // Reset snake position
        direction = "RIGHT"; // Reset direction
        spawnFood();
        gameOver = false;
        gameTimer.start();
    }

    // Spawn food at a random location
    private void spawnFood() {
        Random rand = new Random();
        int x = rand.nextInt(WIDTH / TILE_SIZE) * TILE_SIZE;
        int y = rand.nextInt(HEIGHT / TILE_SIZE) * TILE_SIZE;
        food = new Point(x, y);
    }

    // Handle game updates
    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameOver) {
            gameTimer.stop();
            return;
        }

        // Move the snake
        Point head = snake.getFirst();
        Point newHead = null;

        // Move the snake based on the current direction
        if (direction.equals("RIGHT")) {
            newHead = new Point(head.x + TILE_SIZE, head.y);
        } else if (direction.equals("LEFT")) {
            newHead = new Point(head.x - TILE_SIZE, head.y);
        } else if (direction.equals("UP")) {
            newHead = new Point(head.x, head.y - TILE_SIZE);
        } else if (direction.equals("DOWN")) {
            newHead = new Point(head.x, head.y + TILE_SIZE);
        }

        // Add new head to the snake and remove the tail
        snake.addFirst(newHead);

        // Check if the snake eats food
        if (newHead.equals(food)) {
            spawnFood(); // Spawn new food
        } else {
            snake.removeLast(); // Remove the last part of the snake
        }

        // Check for collisions with walls or itself
        if (newHead.x < 0 || newHead.x >= WIDTH || newHead.y < 0 || newHead.y >= HEIGHT || snake.contains(newHead)) {
            gameOver = true;
        }

        repaint();
    }

    // Draw everything to the screen
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the snake
        g.setColor(Color.GREEN);
        for (Point p : snake) {
            g.fillRect(p.x, p.y, TILE_SIZE, TILE_SIZE);
        }

        // Draw the food
        g.setColor(Color.RED);
        g.fillRect(food.x, food.y, TILE_SIZE, TILE_SIZE);

        // Draw game over screen if the game is over
        if (gameOver) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Game Over!", WIDTH / 3, HEIGHT / 2);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Press R to Restart", WIDTH / 3, HEIGHT / 2 + 40);
        }
    }

    // Handle key press events for controlling the snake
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT && !direction.equals("RIGHT")) {
            direction = "LEFT";
        } else if (keyCode == KeyEvent.VK_RIGHT && !direction.equals("LEFT")) {
            direction = "RIGHT";
        } else if (keyCode == KeyEvent.VK_UP && !direction.equals("DOWN")) {
            direction = "UP";
        } else if (keyCode == KeyEvent.VK_DOWN && !direction.equals("UP")) {
            direction = "DOWN";
        } else if (keyCode == KeyEvent.VK_R && gameOver) {
            resetGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    // Main method to run the game
    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        SnakeGame game = new SnakeGame();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

package com.medicaldiagnosis;

public class Symptom {
    private String name;
    private String severity;

    public Symptom(String name, String severity) {
        this.name = name;
        this.severity = severity;
    }

    public String getName() {
        return name;
    }

    public String getSeverity() {
        return severity;
    }

    @Override
    public String toString() {
        return "Symptom{" +
                "name='" + name + '\'' +
                ", severity='" + severity + '\'' +
                '}';
    }
}

package com.medicaldiagnosis;

import java.util.List;

public class Condition {
    private String name;
    private List<Symptom> symptoms;
    private String treatment;
    private String advice;

    public Condition(String name, List<Symptom> symptoms, String treatment, String advice) {
        this.name = name;
        this.symptoms = symptoms;
        this.treatment = treatment;
        this.advice = advice;
    }

    public String getName() {
        return name;
    }

    public List<Symptom> getSymptoms() {
        return symptoms;
    }

    public String getTreatment() {
        return treatment;
    }

    public String getAdvice() {
        return advice;
    }

    @Override
    public String toString() {
        return "Condition{" +
                "name='" + name + '\'' +
                ", symptoms=" + symptoms +
                ", treatment='" + treatment + '\'' +
                ", advice='" + advice + '\'' +
                '}';
    }
}

package com.medicaldiagnosis;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {
    private String name;
    private List<Symptom> symptoms;

    public User(String name) {
        this.name = name;
        this.symptoms = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Symptom> getSymptoms() {
        return symptoms;
    }

    public void addSymptom(Symptom symptom) {
        symptoms.add(symptom);
    }

    public void collectSymptoms() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your symptoms:");

        while (true) {
            System.out.print("Enter symptom name (or 'done' to finish): ");
            String symptomName = scanner.nextLine();
            if ("done".equalsIgnoreCase(symptomName)) {
                break;
            }
            System.out.print("Enter severity (mild, moderate, severe): ");
            String severity = scanner.nextLine();
            addSymptom(new Symptom(symptomName, severity));
        }
    }
}

package com.medicaldiagnosis;

import java.util.ArrayList;
import java.util.List;

public class DiagnosisService {

    // Simple method to simulate diagnosis based on symptoms
    public List<Condition> diagnose(List<Symptom> symptoms) {
        List<Condition> potentialConditions = new ArrayList<>();
        
        // Example logic for diagnosis (this would be expanded in a real application)
        for (Symptom symptom : symptoms) {
            if (symptom.getName().equalsIgnoreCase("fever")) {
                List<Symptom> fluSymptoms = new ArrayList<>();
                fluSymptoms.add(new Symptom("fever", "severe"));
                fluSymptoms.add(new Symptom("cough", "moderate"));
                fluSymptoms.add(new Symptom("tiredness", "severe"));
                potentialConditions.add(new Condition("Flu", fluSymptoms, "Rest and Hydrate", "Consult a doctor if symptoms worsen."));
            }
        }
        
        return potentialConditions;
    }
}

import java.util.*;

class Book {
    String title;
    String author;
    boolean isAvailable;

    Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    void borrowBook() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("You borrowed: " + title);
        } else {
            System.out.println("Sorry, this book is not available.");
        }
    }

    void returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println("You returned: " + title);
        } else {
            System.out.println("This book was not borrowed.");
        }
    }

    @Override
    public String toString() {
        return title + " by " + author + " (Available: " + isAvailable + ")";
    }
}

class User {
    String name;
    List<Book> borrowedBooks;

    User(String name) {
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    void borrowBook(Book book) {
        if (book.isAvailable) {
            book.borrowBook();
            borrowedBooks.add(book);
        } else {
            System.out.println("Book not available for borrowing.");
        }
    }

    void returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            book.returnBook();
            borrowedBooks.remove(book);
        } else {
            System.out.println("You did not borrow this book.");
        }
    }

    void showBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println(name + " has not borrowed any books.");
        } else {
            System.out.println(name + "'s borrowed books:");
            for (Book book : borrowedBooks) {
                System.out.println(book);
            }
        }
    }
}

class Library {
    List<Book> books;
    List<User> users;

    Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    void addBook(Book book) {
        books.add(book);
    }

    void addUser(User user) {
        users.add(user);
    }

    Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    User findUserByName(String name) {
        for (User user : users) {
            if (user.name.equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }

    void showAllBooks() {
        System.out.println("All books in the library:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    void showAllUsers() {
        System.out.println("All registered users:");
        for (User user : users) {
            System.out.println(user.name);
        }
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        Library library = new Library();
        
        // Add some books
        Book book1 = new Book("Java Programming", "John Doe");
        Book book2 = new Book("Data Structures", "Jane Smith");
        library.addBook(book1);
        library.addBook(book2);

        // Add some users
        User user1 = new User("Alice");
        User user2 = new User("Bob");
        library.addUser(user1);
        library.addUser(user2);

        // Main loop
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Show all books");
            System.out.println("2. Show all users");
            System.out.println("3. Borrow a book");
            System.out.println("4. Return a book");
            System.out.println("5. Show borrowed books");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                library.showAllBooks();
            } else if (choice == 2) {
                library.showAllUsers();
            } else if (choice == 3) {
                System.out.print("Enter your name: ");
                String name = scanner.nextLine();
                User user = library.findUserByName(name);
                if (user != null) {
                    System.out.print("Enter book title to borrow: ");
                    String title = scanner.nextLine();
                    Book book = library.findBookByTitle(title);
                    if (book != null) {
                        user.borrowBook(book);
                    } else {
                        System.out.println("Book not found.");
                    }
                } else {
                    System.out.println("User not found.");
                }
            } else if (choice == 4) {
                System.out.print("Enter your name: ");
                String name = scanner.nextLine();
                User user = library.findUserByName(name);
                if (user != null) {
                    System.out.print("Enter book title to return: ");
                    String title = scanner.nextLine();
                    Book book = library.findBookByTitle(title);
                    if (book != null) {
                        user.returnBook(book);
                    } else {
                        System.out.println("Book not found.");
                    }
                } else {
                    System.out.println("User not found.");
                }
            } else if (choice == 5) {
                System.out.print("Enter your name: ");
                String name = scanner.nextLine();
                User user = library.findUserByName(name);
                if (user != null) {
                    user.showBorrowedBooks();
                } else {
                    System.out.println("User not found.");
                }
            } else if (choice == 6) {
                System.out.println("Exiting system.");
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Random;

public class SnakeGame extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int UNIT_SIZE = 25; // Snake unit size
    private static final int GAME_UNITS = (WIDTH * HEIGHT) / (UNIT_SIZE * UNIT_SIZE); // Total units on the screen
    private static final int DELAY = 100; // Game speed

    private LinkedList<Point> snake; // Snake body
    private Point food; // Food location
    private char direction = 'R'; // Snake's current direction (R: right, L: left, U: up, D: down)
    private boolean gameOver = false;
    private boolean foodEaten = false;

    public SnakeGame() {
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        this.snake = new LinkedList<>();
        this.snake.add(new Point(5, 5)); // Starting position of the snake
        this.generateFood();

        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (gameOver) return;

                int key = e.getKeyCode();

                // Change direction based on key press
                if (key == KeyEvent.VK_LEFT && direction != 'R') {
                    direction = 'L';
                }
                if (key == KeyEvent.VK_RIGHT && direction != 'L') {
                    direction = 'R';
                }
                if (key == KeyEvent.VK_UP && direction != 'D') {
                    direction = 'U';
                }
                if (key == KeyEvent.VK_DOWN && direction != 'U') {
                    direction = 'D';
                }
            }
        });

        Timer timer = new Timer(DELAY, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!gameOver) {
                    move();
                    checkCollision();
                    checkFood();
                    repaint();
                }
            }
        });
        timer.start();
    }

    public void move() {
        Point head = snake.getFirst();
        Point newHead = null;

        // Moving the snake in the current direction
        switch (direction) {
            case 'L':
                newHead = new Point(head.x - 1, head.y);
                break;
            case 'R':
                newHead = new Point(head.x + 1, head.y);
                break;
            case 'U':
                newHead = new Point(head.x, head.y - 1);
                break;
            case 'D':
                newHead = new Point(head.x, head.y + 1);
                break;
        }

        snake.addFirst(newHead); // Add the new head to the snake

        if (foodEaten) {
            foodEaten = false;
        } else {
            snake.removeLast(); // Remove the tail if no food is eaten
        }
    }

    public void checkCollision() {
        Point head = snake.getFirst();

        // Check if the snake collides with the wall
        if (head.x < 0 || head.x >= WIDTH / UNIT_SIZE || head.y < 0 || head.y >= HEIGHT / UNIT_SIZE) {
            gameOver = true;
        }

        // Check if the snake collides with itself
        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                gameOver = true;
            }
        }
    }

    public void checkFood() {
        Point head = snake.getFirst();
        
        // Check if the snake has eaten food
        if (head.equals(food)) {
            foodEaten = true;
            generateFood();
        }
    }

    public void generateFood() {
        Random rand = new Random();
        int x = rand.nextInt(WIDTH / UNIT_SIZE);
        int y = rand.nextInt(HEIGHT / UNIT_SIZE);
        food = new Point(x, y);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Draw the game board background
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Draw the snake
        g.setColor(Color.green);
        for (Point point : snake) {
            g.fillRect(point.x * UNIT_SIZE, point.y * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
        }

        // Draw the food
        g.setColor(Color.red);
        g.fillRect(food.x * UNIT_SIZE, food.y * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);

        // If the game is over, display the "Game Over" message
        if (gameOver) {
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("Game Over", WIDTH / 3, HEIGHT / 2);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SnakeGame game = new SnakeGame();
                game.setVisible(true);
            }
        });
    }
}

public class Book {
    private String title;
    private String author;
    private boolean isAvailable;

    // Constructor
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true; // A book is available by default
    }

    // Getters and setters
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return title + " by " + author + " (Available: " + isAvailable + ")";
    }
}

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private List<Book> borrowedBooks;

    // Constructor
    public User(String name) {
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        if (book.isAvailable()) {
            borrowedBooks.add(book);
            book.setAvailable(false);  // Mark book as borrowed
            System.out.println(name + " borrowed: " + book.getTitle());
        } else {
            System.out.println("Sorry, the book " + book.getTitle() + " is not available.");
        }
    }

    public void returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            book.setAvailable(true);  // Mark book as available
            System.out.println(name + " returned: " + book.getTitle());
        } else {
            System.out.println(name + " did not borrow " + book.getTitle());
        }
    }

    public void showBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println(name + " has not borrowed any books.");
        } else {
            System.out.println(name + "'s borrowed books:");
            for (Book book : borrowedBooks) {
                System.out.println(book);
            }
        }
    }
}


