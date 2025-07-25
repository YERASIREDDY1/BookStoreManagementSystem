package jsp.spring_boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.spring_boot.Dao.BookDao;
import jsp.spring_boot.dto.ResponseStructure;
import jsp.spring_boot.entity.Book;
import jsp.spring_boot.exceptions.IdNotFoundException;
import jsp.spring_boot.exceptions.NoRecordAvailableException;

@Service
public class BookService {

	@Autowired
	public BookDao bookDao;
	
	public ResponseEntity<ResponseStructure<Book>> saveBook(Book book){
		ResponseStructure<Book> response=new ResponseStructure<Book>();
		bookDao.saveBook(book);
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Sucess");
		response.setData(book);
		return new ResponseEntity<ResponseStructure<Book>>(response,HttpStatus.OK);
	}
	
	
	
	public ResponseEntity<ResponseStructure<List<Book>>> getAllBooks(){
		List<Book> books=bookDao.getAllBooks();
			if(books.size()>0){
			ResponseStructure<List<Book>> response=new ResponseStructure<List<Book>>();
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Retrive all data");
			response.setData(books);
			return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
			}
			throw new NoRecordAvailableException("NO record found in the DataBase");
	}
	
	
	public ResponseEntity<ResponseStructure<Book>> getBookById(int id){
		Optional<Book> opt=bookDao.getBookById(id);
		if(!opt.isEmpty()) {
		ResponseStructure<Book> response=new ResponseStructure<Book>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Book record with id "+id+" has retived sucessfully");
		response.setData(opt.get());
		return new ResponseEntity<ResponseStructure<Book>>(response,HttpStatus.OK);
		}
		throw new IdNotFoundException("NO records are found by id :"+id);
	}
	
	public ResponseEntity<ResponseStructure<Book>> updateBook(Book book){
		Optional<Book> opt=bookDao.getBookById(book.getId());
		ResponseStructure<Book> response=new ResponseStructure<Book>();
		if(!opt.isEmpty()) {
			Optional<Book> books=bookDao.updateBook(book);
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Book Record Upadted Sucessfully");
		response.setData(book);
		return new ResponseEntity<ResponseStructure<Book>>(response, HttpStatus.OK);
		}
		throw new IdNotFoundException("unable to update the book as the id is not found");
	}
	public ResponseEntity<ResponseStructure<String>> deleteBook(int id){
		Optional<Book> opt=bookDao.getBookById(id);
		ResponseStructure<String> response=new ResponseStructure<String>();
		if(!opt.isEmpty()) {
			String data=bookDao.deleteBook(opt.get());
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Book Record Deleted Sucessfully");
		response.setData(data);
		return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.OK);
		}
		throw new IdNotFoundException("Unable to delete the Book record As the Id :"+id+" not found in the datadase");
	}
	
	
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByAuthor(String author){
		List<Book> books=bookDao.getBookByAuthor(author);
		if(books.size()>0){
		ResponseStructure<List<Book>> response=new ResponseStructure<List<Book>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Retrive all data");
		response.setData(books);
		return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
		}
		throw new NoRecordAvailableException("NO record found in the DataBase");
}
	
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByPriceGreaterThan(Double price){
		List<Book> books=bookDao.getBookByPrice(price);
		if(books.size()>0) {
			ResponseStructure<List<Book>> response=new ResponseStructure<List<Book>>();
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Books recordf pricees greater than "+price+ " has been retrived");
			response.setData(books);
			return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
		}
		throw new NoRecordAvailableException("NO book records are found in the the price greater than"+price);
	}
	
	public ResponseEntity<ResponseStructure<List<Book>>> getBookBetween(double fromPrice,double toPrice){
		List<Book> books=bookDao.getBookByPriceRange(fromPrice, toPrice);
		if(books.size()>0) {
			ResponseStructure<List<Book>> response=new ResponseStructure<List<Book>>();
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Books records in range"+fromPrice+"to "+toPrice+" of has been retrived");
			response.setData(books);
			return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
		}
		throw new NoRecordAvailableException("NO book records are found in the the price in the range");
	}
	
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByPublishionYear(int year){
		List<Book> books=bookDao.getBookByYear(year);
		if(books.size()>0) {
			ResponseStructure<List<Book>> response=new ResponseStructure<List<Book>>();
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Books records published in the year :"+year +" of has been retrived");
			response.setData(books);
			return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
		}
		throw new NoRecordAvailableException("NO books  are published  in the the year :"+year);
	}
	
	
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByAvailability(boolean availability){
		List<Book> books=bookDao.getBookByAvailability(availability);
		if(books.size()>0) {
			ResponseStructure<List<Book>> response=new ResponseStructure<List<Book>>();
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Books records are not avalable");
			response.setData(books);
			return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
		}
		throw new NoRecordAvailableException("BOOKS are out of stock ");
	}
	
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByAuthorAndPublicationYear(String author,int year){
		List<Book> books=bookDao.getBookByAuthorAndPublicationYear(author, year);
		if(books.size()>0) {
			ResponseStructure<List<Book>> response=new ResponseStructure<List<Book>>();
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Books records are not avalable");
			response.setData(books);
			return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
		}
		throw new NoRecordAvailableException("BOOKS are not published by author : "+author+" in the year"+year);
	}
	public ResponseEntity<ResponseStructure<List<Book>>> getBookByGener(String gener){
		List<Book> books=bookDao.getBookByGener(gener);
		if(books.size()>0) {
			ResponseStructure<List<Book>> response=new ResponseStructure<List<Book>>();
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Books records are retrived ");
			response.setData(books);
			return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
		}
		throw new NoRecordAvailableException("BOOKS are not available in the gener"+gener);
	}
	
	
	public ResponseEntity<ResponseStructure<Page<Book>>> getBookByPagination(int pageNumber,int pageSize){
		Page<Book> book=bookDao.getBookByPaginnation(pageNumber, pageSize);
		if(!book.isEmpty()) {
		ResponseStructure<Page<Book>> response=new ResponseStructure<Page<Book>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Retrives all data from bokk records");
		response.setData(bookDao.getBookByPaginnation(pageNumber, pageSize));
		return new ResponseEntity<ResponseStructure<Page<Book>>>(response,HttpStatus.OK);
		}
		throw new NoRecordAvailableException("No data is found in the database");
	}
	
	public ResponseEntity<ResponseStructure<List<Book>>> getBookBysorting(String field){
		List<Book> books=bookDao.getBookByGener(field);
		if(books.size()>0) {
			ResponseStructure<List<Book>> response=new ResponseStructure<List<Book>>();
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Books records are retrived ");
			response.setData(books);
			return new ResponseEntity<ResponseStructure<List<Book>>>(response, HttpStatus.OK);
		}
		throw new NoRecordAvailableException("BOOKS are not available in the database");
	}
	
	public ResponseEntity<ResponseStructure<Page<Book>>> getBookByPaginationAndSorting(int pageNumber,int pageSize,String field){
		Page<Book> book=bookDao.getBookByPaginationAndSorting(pageNumber, pageSize,field);
		if(!book.isEmpty()) {
		ResponseStructure<Page<Book>> response=new ResponseStructure<Page<Book>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Retrives all data from bokk records");
		response.setData(bookDao.getBookByPaginnation(pageNumber, pageSize));
		return new ResponseEntity<ResponseStructure<Page<Book>>>(response,HttpStatus.OK);
		}
		throw new NoRecordAvailableException("No data is found in the database");
	}
}
