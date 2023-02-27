package pe.business.app.products.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import pe.business.app.products.model.PricesRs;
import pe.business.app.products.repository.PricesRepository;
import pe.business.app.products.repository.entity.Price;
import pe.business.app.products.util.Constant;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ProductsServiceImpl implements ProductsService {


    @Autowired
    PricesRepository pricesRepository;


    @Override
    @Transactional(readOnly = true)
    public List<PricesRs> findPrices(String typeFind, String productCode, Long brandId, Date aplicationDate) {

        List<PricesRs> pricesListRs = new ArrayList<>();
        List<Price> priceList = null;
        if(typeFind.equals(Constant.FIND_ALL))
          priceList = pricesRepository.findAll();
        else{
          priceList = pricesRepository.findByProductCodeAndBrandIdAndStartBeforeAndEndAfter(productCode, brandId, aplicationDate);
        }
        for (Price price : priceList){
            PricesRs priceRs = new PricesRs();
            priceRs.setProductCode(price.getProduct().getProductCode());
            priceRs.setBrandId(price.getBrand().getId());
            priceRs.setBrandName(price.getBrand().getName());
            priceRs.setPricesList(price.getPriceRate().getRate().toString());
            SimpleDateFormat formatter  = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            priceRs.setStartDate(formatter.format(new Timestamp(price.getStartDate().getTime())));
            priceRs.setEndDate(formatter.format(new Timestamp(price.getEndDate().getTime())));
            priceRs.setPrice(price.getPrice().toString());
            priceRs.setFinalPrice(new StringBuilder().append(price.getPrice()+price.getPriceRate().getRate()).append(" ").append(price.getCurr()).toString());
            pricesListRs.add(priceRs);
        }
        return  pricesListRs;

    }


}
