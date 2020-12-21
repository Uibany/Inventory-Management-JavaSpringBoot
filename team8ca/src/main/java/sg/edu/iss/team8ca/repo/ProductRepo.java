package sg.edu.iss.team8ca.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sg.edu.iss.team8ca.model.Product;

@Repository
@Transactional
public interface ProductRepo extends JpaRepository<Product, Long>{
	
	@Transactional
	@Query("SELECT product FROM Product product WHERE product.productId = :productId")
	public Product findProductByProductId(@Param("productId") long productId);
	
	@Modifying
    @Transactional
    @Query("delete from Product p where p.productId = ?1")
    void deleteProductByid(long id);

	@Modifying
    @Transactional
    @Query("select count(*) from Product p where p.productId = ?1 and p.quantity >= ?2")
	List<Integer> getFixsetDetails(Long productId, Integer quantity);

	@Modifying
    @Transactional
    @Query("update Product p set p.quantity=p.quantity-?2 where p.productId = ?1")
	void updateQuantityOfProduct(Long productId, Integer quantity);

}
