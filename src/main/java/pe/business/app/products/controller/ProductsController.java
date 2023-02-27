package pe.business.app.products.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pe.business.app.products.controller.exception.ServiceException;
import pe.business.app.products.model.PricesRs;
import pe.business.app.products.model.TransactionRs;
import pe.business.app.products.service.ProductsService;
import pe.business.app.products.util.Constant;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/products")
@Validated
public class ProductsController {

    @Autowired
    ProductsService productsService;

    public static final String CODE_POTENCIAL="7003";

    @GetMapping(path = "/prices")
    public ResponseEntity<TransactionRs<List<PricesRs>>> listPrices( @RequestParam(name = "productCode" , required = false) String productCode,
                                                                     @RequestParam(name = "brandId" , required = false) Long brandId,
                                                                     @RequestParam(name = "aplicationDate" , required = false) String aplicationDate) throws ParseException {
        TransactionRs<List<PricesRs>> response = new TransactionRs<List<PricesRs>>();
        List<PricesRs> prices =  new ArrayList<>();
        Date appDate=null;

        if(aplicationDate!=null) {
            appDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(aplicationDate);
        }
        if (null ==  productCode && brandId== null && aplicationDate==null) {
             prices = productsService.findPrices(Constant.FIND_ALL,null,null,null);

        } else {
             prices = productsService.findPrices(Constant.FIND_BY_BRAND_PRODUCT_APPDATE,productCode,brandId,appDate);
        }

        if (prices.isEmpty()) {
            throw new ServiceException(CODE_POTENCIAL);
        }
        response.setRespuesta(prices);
        response.isSuccess();
        return  ResponseEntity.status( HttpStatus.OK).body(response);
    }

}
