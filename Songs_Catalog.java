import java.io.*;
import java.util.*;

class Song implements Comparable<Song> {

    private String artist;
    private String title;
    private Song next;

    public Song(String artist, String title) {
        setArtist(artist);
        setTitle(title);
    }

    public Song() {
        setArtist(null);
        setNext(null);
        setTitle(null);
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public Song getNext() {
        return next;
    }

    public void setNext(Song next) {
        this.next = next;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String toString() {
        return String.format("Artist: %s\nTitle: %s\n\n", getArtist(), getTitle());
    }

    public int compareTo(Song other) {
        return getArtist().compareTo(other.getArtist());
    }
}

class SongList {

    public Song head;
    public int numSongs;

    public SongList() {
        head = new Song();
        numSongs = 0;
    }

    public void readFile(String aFile) {

        boolean mazboot;

        try {
            Scanner input = new Scanner(new File(aFile));
            input.next();
            head.setArtist(input.nextLine());
            input.next();
            head.setTitle(input.nextLine());
            numSongs++;

            Song current = head;
            while (input.hasNextLine()) {

                input.nextLine();
                input.next();
                String a = input.nextLine();
                input.next();
                String t = input.nextLine();
                Song x = new Song(a, t);

                current.setNext(x);
                current = x;
                numSongs++;
            }
            input.close();
            mazboot = true;

        } catch (FileNotFoundException e) {
            System.out.println("File not found. Try again");
            mazboot = false;
        } catch (NoSuchElementException e) {
            System.out.println("Input format error. Check the file format");
            mazboot = false;
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            mazboot = false;
        }

        if (mazboot)
            System.out.println("Songs imported successfully.");

    }

    public void addSong(String artist, String titlle) {

        if (mawjood(titlle))
            System.out.print("The song exists aslan!\n");

        else {

            Song current = head;
            Song newsong = new Song(artist, titlle);

            if (head.compareTo(newsong) < 0) {
                newsong.setNext(head);
                head = newsong;

            } else {

                while (current.getNext().compareTo(newsong) > 0) {
                    current = current.getNext();
                }

                newsong.setNext(current.getNext());
                current.setNext(newsong);

            }
            System.out.println("Song added successfully.");
        }
    }

    public boolean mawjood(String titlle) {

        int c = 0;
        Song current = head;

        while (current != null) {
            if (current.getTitle().equalsIgnoreCase(" " + titlle)) {
                c++;
            }
            current = current.getNext();
        }

        return c != 0;
    }

    public void deleteByArtist(String artistt) {

        boolean mawjood = false;

        if (head.getArtist().equals(" " + artistt)) {
            head = head.getNext();
            mawjood = true;
        }

        int co = 0;

        Song current = head;
        while (current.getNext() != null) {

            if (current.getNext().getArtist().equals(" " + artistt)) {
                co++;
                current.setNext(current.getNext().getNext());

            } else {
                // Move to the next node only if the current node is not deleted
                current = current.getNext();
            }
        }

        if (co != 0)
            mawjood = true;
        if (mawjood == false)
            System.out.print("The song is not in the song list aslan");
    }

    public void deleteByTitle(String titlle) {

        if (head == null) {
            System.out.println("The song list is empty.");
            return;
        }

        if (head.getTitle().equals(" " + titlle)) {
            head = head.getNext();
            return;
        }

        else {

            Song current = head;
            while (current.getNext() != null && !current.getNext().getTitle().equals(" " + titlle)) {
                current = current.getNext();
            }
            if (current.getNext() == null)
                System.out.print("The song is not in the song list aslan\n");
            else
                current.setNext(current.getNext().getNext());

        }

    }

    public void searchByArtist(String artistt) {

        String present = "";
        boolean mawjood = false;
        int co = 0;

        Song current = head;
        while (current.getNext() != null) {

            if (current.getArtist().equalsIgnoreCase(" " + artistt)) {
                co++;
                if (present.equals(""))
                    present += current.getTitle();
                else
                    present += ", " + current.getTitle();
            }

            current = current.getNext();
        }

        if (co != 0)
            mawjood = true;

        if (mawjood == false)
            System.out.print("The artist is not in the song list aslan\n");
        else
            System.out.print("\nThe artist " + artistt + " sang the following song(s): \n" + present);
    }

    public SongList getSongsByArtist(String artistt) {

        SongList list = new SongList();
        list.head = null;

        Song current = head;
        int count = 0;
        Song curr = null;

        while (current != null) {

            if (current.getArtist().equals(" " + artistt)) {
                count++;
                Song newSong = new Song(current.getArtist(), current.getTitle());

                if (count == 1) {
                    list.head = newSong;
                    curr = list.head;
                } else if (count >= 2) {
                    curr.setNext(newSong);
                    curr = curr.getNext();
                }
            }
            current = current.getNext();
        }

        return list;
    }

    public void searchByTitle(String titlle) {

        Song current = head;

        while (current != null) {

            if (current.getTitle().equals(" " + titlle)) {
                System.out.print("The song " + titlle + " is written by " + current.getArtist() + "\n");
                break;
            }
            current.setNext(current.getNext());
        }

    }

    public Song getByTitle(String titlle) {

        Song current = head;

        while (current != null) {

            if (current.getTitle().equals(titlle)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    public void displayCatalog() {

        System.out.print("\nThe song list is: \n\n");

        Song current = head;

        while (current != null) {
            System.out.print(current.toString());
            current = current.getNext();
        }
    }

    public void writeFile(String aFile) throws FileNotFoundException {

        PrintStream prt = new PrintStream(aFile);
        Song current = head;

        while (current != null) {
            prt.print((current.toString()));
            current = current.getNext();
        }
    }
}

public class Songs_Catalog {
    public static void main(String[] args) {
        SongList album = new SongList();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nMenu:\n1. Import songs from a file\n2. Add a new song");
            System.out.println("3. Delete a song\n4. Search for a song\n5. Get a song by title");
            System.out.println("6. Get all songs by artist\n7. Display all songs\n8. Exit program");

            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter the absolute path to the file: ");
                    String filePath = scanner.nextLine();
                    album.readFile(filePath);
                    break;
                case 2:
                    System.out.print("Enter artist: ");
                    String artist = scanner.nextLine();
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    album.addSong(artist, title);

                    break;
                case 3:
                    System.out.print("Delete by artist or title (A or T): ");
                    String deleteOption = scanner.nextLine().toUpperCase();
                    if (deleteOption.equals("A")) {
                        System.out.print("Enter artist: ");
                        String deleteArtist = scanner.nextLine();
                        album.deleteByArtist(deleteArtist);
                    } else if (deleteOption.equals("T")) {
                        System.out.print("Enter title: ");
                        String deleteTitle = scanner.nextLine();
                        album.deleteByTitle(deleteTitle);
                    } else {
                        System.out.println("Invalid option. Please try again.");
                    }
                    break;
                case 4:
                    System.out.print("Search by artist or title (A or T): ");
                    String searchOption = scanner.nextLine().toUpperCase();
                    if (searchOption.equals("A")) {
                        System.out.print("Enter artist: ");
                        String searchArtist = scanner.nextLine();
                        album.searchByArtist(searchArtist);
                    } else if (searchOption.equals("T")) {
                        System.out.print("Enter title: ");
                        String searchTitle = scanner.nextLine();
                        album.searchByTitle(searchTitle);
                    } else {
                        System.out.println("Invalid option. Please try again.");
                    }
                    break;
                case 5:
                    System.out.print("Enter title to get the song: ");
                    String getTitle = scanner.nextLine();
                    Song song = album.getByTitle(" " + getTitle);
                    if (song != null) {
                        System.out.println(song.toString());
                    } else {
                        System.out.println("Song not found.");
                    }
                    break;
                case 6:
                    System.out.print("Enter artist to get all songs: ");
                    String getAllArtist = scanner.nextLine();
                    SongList artistSongs = album.getSongsByArtist(getAllArtist);
                    artistSongs.displayCatalog();
                    break;
                case 7:
                    album.displayCatalog();
                    break;
                case 8:
                    running = false;
                    System.out.println("\nThank you !\nExiting program.");
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
                    break;
            }
        }
        scanner.close();
    }
}