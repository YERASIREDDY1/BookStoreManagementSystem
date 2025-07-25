package jsp.spring_boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jsp.spring_boot.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	
	//fetch by authorr
	List<Book> findByAuthor(String author);
	//fetch by pricegraterthan
	List<Book> findByPriceGreaterThan(double price);
	//fetch between range
	List<Book> findByPriceBetween(double startRange,double endRange);
	//fetch by author and publication year
	List<Book> findByAuthorAndPublictionYear(String author,int publictionYear);
	
	@Query("select b from Book b where b.publictionYear=?1")
	List<Book> findBookByPublicationYear(int publictionYear);

	@Query("select b from Book b where b.genre=?1")
	List<Book> findBookByGener(String genre);

	@Query("select b from Book b where b.availability=:availability")
	List<Book> findBookByAvailabilty(boolean availability);
	
	
}
