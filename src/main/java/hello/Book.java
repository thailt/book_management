package hello;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;

import java.util.concurrent.atomic.AtomicLong;


public class Book {

    private final AtomicLong counterBookId = new AtomicLong();

    @Id
    private long id; //"required": true,
    private String title;//"required": true,
    private String isbn;//"required": true,
    private String description;//"required": true,
    private String publisher;//"required": true,
    private String publicationDate;
    private String[] authors;//"required": true,
    private String[] categories;

    public String toString(){
        return String.format(
                "Book[Id=%s, title='%s', isbn='%s', description='%s', publisher='%s', publicationDate='%s', authors='%s', categories='%s']",
                id, title, isbn , description, publisher, publicationDate, authors, categories);
    }

    public Book(){}

//    public Book(long id, String description) {
//        this.id = id;
//        this.description = description;
//    }



    public Book(String title, String isbn, String[] authors, String publisher, String description,
                String[] categories, String publicationDate){
        this.id = counterBookId.incrementAndGet();
        this.title = title;
        this.isbn = isbn;
        this.authors = authors;
        this.publisher = publisher;
        this.description = description;
        this.categories = categories;
        this.publicationDate = publicationDate;

    }


    public long getId() {
        return this.id;
    }
    public boolean setId(long id) {
        this.id = id;
        return true;
    }
    public String getTitle() {
        return title;
    }
    public boolean setTitle(String title) {
        this.title = title;
        return true;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public String[] getAuthors() {
        return authors;
    }
    public boolean setAuthors(String[] authors) {
        this.authors = authors;
        return true;
    }
    public String getDescription() {
        return description;
    }
    public boolean setDescription(String description) {
        this.description = description;
        return true;
    }

    public String getPublisher(){
        return publisher;
    }

    public String getPublicationDate(){
        return publicationDate;
    }

    public boolean setPublisher(String publisher){
        this.publisher=publisher;
        return true;
    }

    public boolean setPublicationDate(String publicationDate){
        this.publicationDate=publicationDate;
        return true;
    }
//    categories
    public String[] getCategories(){
        return categories;
    }
    public boolean setCategories(String[] categories){
        this.categories=categories;
        return true;
    }
    
    public boolean update (Book newBook){
    	if (this.id != newBook.getId()){
    		return false;
    	}
    	if (!newBook.isValidBook()){
    		return false;
    	}
    	if (newBook.getTitle() != null){
    		this.title = newBook.getTitle();
    	}
    	
    	if (newBook.getIsbn() != null){
    		this.isbn = newBook.getIsbn();
    	}
    	
    	if (newBook.getAuthors() != null){
    		this.authors = newBook.getAuthors();
    	}
    	
    	if (newBook.getDescription() != null){
    		this.description = newBook.getDescription();
    	}
    	
    	if (newBook.getPublisher() != null){
    		this.publisher = newBook.getPublisher();
    	}
    	
    	if (newBook.getPublicationDate() != null){
    		this.publicationDate = newBook.getPublicationDate();
    	}

    	return true;
    }

    public boolean isValidBook(){
    	
        if (this.title == null  || this.title.length()<1 ) return false;
        if (this.isbn == null || this.isbn.length()<1 ) return false;
        if (this.description == null || this.description.length()<1 ) return false;
        if (this.publisher == null || this.publisher.length()<1 ) return false;;
        if (this.publicationDate == null || this.publicationDate.length()<1 ) return false;
        if (this.authors == null || this.authors[0].length()<1 ) return false;
        
    	return true;
    }
    
}
