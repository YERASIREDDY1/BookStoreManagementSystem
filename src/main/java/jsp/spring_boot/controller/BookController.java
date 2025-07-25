package jsp.spring_boot.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties.Http;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jsp.spring_boot.dto.ResponseStructure;
import jsp.spring_boot.entity.Book;
import jsp.spring_boot.exceptions.IdNotFoundException;
import jsp.spring_boot.exceptions.NoRecordAvailableException;
import jsp.spring_boot.repository.BookRepository;
import jsp.spring_boot.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BookService bookService;
	@PostMapping
	public ResponseEntity<ResponseStructure<Book>> saveBook(@RequestBody Book book) {  
		return bookService.saveBook(book);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Book>>> getAllBook(){
		return bookService.getAllBooks();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Book>> getBookById(@PathVariable Integer id) {
		return bookService.getBookById(id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Book>> updateBook(@RequestBody Book book) {
		return bookService.updateBook(book);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteBook(@PathVariable Integer id) {
		return bookService.deleteBook(id);
	}
	
	@GetMapping("/pages/{pageNumber}/{pageSize}")
	public ResponseEntity<ResponseStructure<Page<Book>>> getBookByPagination(@PathVariable int pageNumber,@PathVariable int pageSize){
		return bookService.getBookByPagination(pageNumber, pageSize);
	}
	
	
	@GetMapping("/author/{author}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByAuthor(@PathVariable String author){
		return bookService.getBookByAuthor(author);
	}
	@GetMapping("/price/{price}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByPriceGraterThan(@PathVariable double price){
		return bookService.getBookByPriceGreaterThan(price);
	}
	@GetMapping("/price/{startRange}/{endRange}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBookInpriceBetween(@PathVariable double startRange,@PathVariable double endRange){
		return bookService.getBookBetween(startRange, endRange);
	}
	@GetMapping("year/{year}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByYear(@PathVariable int years){
		return bookService.getBookByPublishionYear(years);
	}
	@GetMapping("/gener/{gener}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByGener(@PathVariable String gener){
		return bookService.getBookByGener(gener);
	}
	@GetMapping("/availability/{availability}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByAvailability(@PathVariable boolean availability){
		return bookService.getBookByAvailability(availability);
	}
	@GetMapping("/author/{author}/{publicationYear}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByAuthorAndPublicationYear(@PathVariable String author,@PathVariable int publicationYear){
		return bookService.getBookByAuthorAndPublicationYear(author, publicationYear);
	}
	
	@GetMapping("/sort/{field}")
	public ResponseEntity<ResponseStructure<List<Book>>> getBookBySorting(@PathVariable String field){
		return bookService.getBookBysorting(field);
	}
	
	@GetMapping("/pages/{pageNumber}/{pageSize}/{gener}")
	public ResponseEntity<ResponseStructure<Page<Book>>> getBookByPaginationAndSorting(@PathVariable int pageNumber,int pageSize,String gener){
		return bookService.getBookByPaginationAndSorting(pageNumber, pageSize, gener);
	}
}
