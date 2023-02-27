package pe.business.app.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.business.app.products.repository.entity.Brand;
import pe.business.app.products.repository.entity.Price;
import pe.business.app.products.repository.entity.Product;

import java.util.Date;
import java.util.List;

@Repository
public interface PricesRepository extends JpaRepository<Price,Long> {

    @Query("SELECT p FROM Price p WHERE (:brandId is null or p.brand.id = :brandId) and (:productCode is null"
            + " or p.product.productCode = :productCode) and (:aplicationDate is null or (p.startDate <=:aplicationDate and :aplicationDate <= p.endDate))")
    List<Price> findByProductCodeAndBrandIdAndStartBeforeAndEndAfter(@Param("productCode") String productCode, @Param("brandId") Long brandId, @Param("aplicationDate") Date aplicationDate);


}
