
package hello;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@RepositoryResource(collectionResourceRel = "book", path = "books")
public interface BookRepository extends MongoRepository<Book, Long> {
	
//	@RequestMapping(value = "/books/{bookID}", method = RequestMethod.GET)
	//Book findByBookId(@PathVariable(value = "bookID") long bookID);
	Page <Book> findAll( Pageable pagable);
	Book findById(long Id);
	
	Book findTop1ByOrderByIdDesc();
//	boolean exists (long Id);
	
}
