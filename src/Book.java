public class Book {
    private int id;
    private String title;
    private String author;
    private int year;
    private boolean available;

    public Book(int id, String title, String author, int year, boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.available = available;
    }

    public void display() {
        System.out.println(id + " | " + title + " | " + author + " | " + year + " | " + (available ? "Available" : "Issued"));
    }
}
