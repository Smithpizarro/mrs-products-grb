package pe.business.app.products.service;


import pe.business.app.products.model.PricesRs;

import java.util.Date;
import java.util.List;

public interface ProductsService {

    public List<PricesRs> findPrices(String typeFind,String productCode, Long brandId, Date aplicationDate);

}
