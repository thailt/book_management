package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


@RestController
public class BookController {

    @Autowired
    private BookRepository repository ;


    @RequestMapping(value = "/books", method = RequestMethod.GET, 
    		produces="application/json")
    public Page<Book> getAllBook(@RequestParam(value = "page", defaultValue = "1") int page,
                          @RequestParam(value = "perPage", defaultValue = "5") int perPage) {
        
    	//paging starting from 0, while default is 0
    	if (page==0) page=1;
    	
    	//page, and perPage over the limit, return default value
    	
    	PageRequest paging = new PageRequest (page-1, perPage );
        Page<Book> pageOfBook = repository.findAll(paging);
        return pageOfBook;
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST , 
    		consumes="application/json")
    public Book createBook(@Valid @RequestBody Book newBook) {
    	if (!newBook.isValidBook()) return null;
    	
    	Book first1book = repository.findTop1ByOrderByIdDesc();
    	
    	if (first1book != null){
    		newBook.setId(first1book.getId()+1);
    	}
    	else{
    		newBook.setId(0);
    	}
    	
        repository.save(newBook);
        return newBook;
    }

    @RequestMapping(value = "/books/{ID}", method = RequestMethod.GET , produces="application/json")
    public Book getBook(@PathVariable(value = "ID") long ID) {
    	return repository.findById(ID);
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.PUT , 
    		consumes="application/json", produces="application/json")
    public Book updateBook(@PathVariable(value = "id") int id,
    		@Valid @RequestBody Book updateBook ) {
    	
    	if (!updateBook.isValidBook()) return null;
    	
    	Book book = repository.findById(id);
    	if (book == null || id != updateBook.getId()) {
    		return null;
    	}
    	
    	boolean result = book.update(updateBook);
    	if (result) {
    		repository.save(book);
    	}
        return book;
    }
    

    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
    public boolean deleteBook(@PathVariable(value = "id") long id) {
    	if (repository.findById(id) == null) return false;
    	
    	repository.delete(id);
    	return true;

    }
}
