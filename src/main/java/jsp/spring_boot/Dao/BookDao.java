package jsp.spring_boot.Dao;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import jsp.spring_boot.entity.Book;
import jsp.spring_boot.exceptions.IdNotFoundException;
import jsp.spring_boot.exceptions.NoRecordAvailableException;
import jsp.spring_boot.repository.BookRepository;

@Repository
public class BookDao {
	
	@Autowired
	private BookRepository bookRepository;
	
	public Book saveBook(Book book) {
		return bookRepository.save(book);
		
	}
	
	public List<Book> getAllBooks(){
		return bookRepository.findAll();
	}
	
	public Optional<Book> getBookById(int id) {
		return bookRepository.findById(id);
	}
	
	
	public String deleteBook(Book book){
		bookRepository.deleteById(book.getId());
		return "record Deleted sucessfully";
	}
	
	public Optional<Book> updateBook(Book book) {
		return bookRepository.findById(book.getId());
	}
	
	public List<Book> getBookByAuthor(String author){
		return bookRepository.findByAuthor(author);
	}
	public List<Book> getBookByPrice(double price){
		return bookRepository.findByPriceGreaterThan(price);
	}
	
	public List<Book> getBookByPriceRange(double fromPrice,double toPrice){
		return bookRepository.findByPriceBetween(fromPrice,toPrice);
	}
	
	public List<Book> getBookByYear(int year){
		return bookRepository.findBookByPublicationYear(year);
	}
	
	public List<Book> getBookByAvailability(boolean availability){
		return bookRepository.findBookByAvailabilty(availability); 
	}
	
	public List<Book> getBookByAuthorAndPublicationYear(String author,int year){
		return bookRepository.findByAuthorAndPublictionYear(author, year);
	}
	
	public List<Book> getBookByGener(String gener){
		return bookRepository.findBookByGener(gener);
	}
	
	
	public Page<Book> getBookByPaginnation(int pageNumber,int pageSize){
		return bookRepository.findAll(PageRequest.of(pageNumber, pageSize));
	}
	
	public List<Book> getBookBySorting(String field){
		return bookRepository.findAll(Sort.by(field).descending());
	}
	
	public Page<Book> getBookByPaginationAndSorting(int pageNUmber,int pageSize,String field){
		return bookRepository.findAll(PageRequest.of(pageNUmber, pageSize,Sort.by(field).ascending()));
	}
}
