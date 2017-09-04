package model.library;

import model.book.Book;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Library {

    private Librarian manager;
    private String name;
    private List<Library> branches= new ArrayList<>();
    private List<Book> referenceBooks = new ArrayList<>();
    private List<Book> fictionBooks = new ArrayList<>();
    private List<Book> nonfictionBooks = new ArrayList<>();
    private List<Book> textBooks = new ArrayList<>();
    private List<Book> cookBooks = new ArrayList<>();
    private List<Book> issuedBooks = new ArrayList<>();

    public Library(String nm, Librarian manager) {
        this.name = nm;
        this.manager = manager;
    }

    // getters
    public String getName() {
        return name;
    }

    public Librarian getManager() {
        return manager;
    }

    // REQUIRES: bk != null
    // MODIFIES: this
    // EFFECTS: stores the given Book bk into the appropriate container within this class
    public void storeBook(Book bk) {
        if(bk==null){
            System.out.println("book reference is null");
            return;
        }
        switch (bk.getType()){
            case COOKING: cookBooks.add(bk);
                            break;
            case FICTION: fictionBooks.add(bk);
                            break;
            case TEXTBOOK: textBooks.add(bk);
                            break;
            case REFERENCE: referenceBooks.add(bk);
                            break;
            case NONFICTION:nonfictionBooks.add(bk);
                            break;
        }
    }

    // REQUIRES: bk != null
    // EFFECTS: return true if the given book is in the catalogue,
    //          regardless of its loan status, else return false
    public boolean inCatalogue(Book bk) {
        if(bk==null){
            System.out.println("Given Book reference is null");
            return false;
        }
        switch (bk.getType()){
            case NONFICTION: return nonfictionBooks.contains(bk);
            case REFERENCE:  return referenceBooks.contains(bk);
            case TEXTBOOK:   return textBooks.contains(bk);
            case FICTION:    return fictionBooks.contains(bk);
            case COOKING:    return cookBooks.contains(bk);
        }
        return false;
    }

    // REQUIRES: bk != null
    // EFFECTS: return true if the given book is available to loan
    //          Note: What requirements should a book meet to be available?
    public boolean canLoan(Book bk) {
        if(bk==null){
            System.out.println("Given Book reference is null");
            return false;
        }
        return !(bk.onLoan());
    }

    // REQUIRES: bk != null
    // EFFECTS: return true if the given book is available in the catalogue of this library's
    //          other branches; else, return false
    public boolean isInDiffBranch(Book bk) {
        //TODO: complete the implementation of this method
        if(bk==null){
            System.out.println("Given Book reference is null");
            return false;
        }
        System.out.println("There are no branches yet.");
        return false;
    }

    // REQUIRES: bk != null
    // MODIFIES: this
    // EFFECTS: set bk as being checked out from this library if possible
    //          return true if successful, else false
    public boolean checkOutBook(Book bk){
        if(bk.onLoan()){
            System.out.println("Sorry, the book is already on Loan");
        return false;
        }
        System.out.println("The book " +bk.getTitle() +" is being booked from " + this.name);
        bk.nowOnLoan();
        issuedBooks.add(bk);
        return true;
    }

    // REQUIRES: bk != null
    // MODIFIES: this
    // EFFECTS: set bk as being back in the library if it has been borrowed previously
    //          return true if successful, otherwise false
    public boolean returnBook(Book bk) {
        if(bk==null){
            System.out.println("Given Book reference is null");
            return false;
        }
        if(issuedBooks.contains(bk)){
            issuedBooks.remove(bk);
            bk.notOnLoan();
        return true;
        }

        return false;
    }

    // REQUIRES: manager != null
    // MODIFIES: this
    // EFFECTS: sets this library's librarian to manager; return true if successful, else false
    public boolean hireLibrarian(Librarian manager) {
        if(manager==null)
            return false;
        this.manager = manager;
        return true;
    }


    // Utility method, do not touch its implementation
    public void printCatalogue() {
        List<Book> totalCatalogue = new LinkedList<>();
        totalCatalogue.addAll(this.cookBooks);
        totalCatalogue.addAll(this.fictionBooks);
        totalCatalogue.addAll(this.nonfictionBooks);
        totalCatalogue.addAll(this.textBooks);
        totalCatalogue.addAll(this.referenceBooks);

        for (Book b : totalCatalogue) {
            System.out.println(b.getTitle() + " by " + b.getAuthor());
        }
    }


}
