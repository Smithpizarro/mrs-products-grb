package pe.business.app.products.util;

import pe.business.app.products.model.PricesRs;
import pe.business.app.products.repository.entity.Brand;
import pe.business.app.products.repository.entity.Price;
import pe.business.app.products.repository.entity.PriceRate;
import pe.business.app.products.repository.entity.Product;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

public class Datos {

    public static PricesRs createPrices001() {
        return new PricesRs("35455",1L, "Sara","0.50","2021-06-14 00:00","2021-12-31 23:59","35.5","35.5 EUR");
    }

    public static PricesRs createPrices002() {
        return new PricesRs("35456",1L, "H&M","1.00","2021-06-15 00:00","2021-12-31 23:59","25.5","25.5 EUR");
    }

    public static Price createPrice001() throws ParseException {
        return new Price(1L,new Brand(1L,"Sara"),new SimpleDateFormat("dd-MM-yyyy HH:mm").parse("2021-06-15 00:00")
                ,new SimpleDateFormat("dd-MM-yyyy HH:mm").parse("2021-12-31 23:59"),new PriceRate(1L,0.50),
                new Product(1L,"35455", "LION ULTIMATE", "T-SHIRT LION BLACK size S",10.0),1L,35.50,"EUR");
    }

    public static Price createPrice002() throws ParseException {
        return new Price(1L,new Brand(1L,"Sara"),new SimpleDateFormat("dd-MM-yyyy HH:mm").parse("2021-06-15 00:00")
                ,new SimpleDateFormat("dd-MM-yyyy HH:mm").parse("2021-12-31 23:59"),new PriceRate(2L,0.50),
                new Product(1L,"35455", "LION ULTIMATE", "T-SHIRT LION BLACK size S",10.0),1L,35.50,"EUR");
    }


}
