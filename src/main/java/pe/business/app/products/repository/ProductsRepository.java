package pe.business.app.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.business.app.products.repository.entity.Product;

@Repository
public interface ProductsRepository extends JpaRepository<Product,Long> {

    public Product findByProductCode(String  productCode);
}
