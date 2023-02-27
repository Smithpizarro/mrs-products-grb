package pe.business.app.products.service;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pe.business.app.products.model.PricesRs;
import pe.business.app.products.repository.PricesRepository;
import pe.business.app.products.repository.entity.Price;
import pe.business.app.products.util.Constant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static pe.business.app.products.util.Datos.*;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class ProductsServiceImplTest {

    @MockBean
    PricesRepository pricesRepository;

    @InjectMocks
    ProductsService productsService = new ProductsServiceImpl();

    @Test
    @DisplayName("Return All List Prices when requestParameters are  nulls")
    void returnAlListPricesWhenRequestParametersAreNulls() throws ParseException {
        List<Price>  pricesList= Arrays.asList(createPrice001(),
                createPrice001());
        when(pricesRepository.findAll()).thenReturn(pricesList);

        List<PricesRs> prices = productsService.findPrices(Constant.FIND_ALL,null,null,null);

        assertFalse(prices.isEmpty());
        assertEquals(2, prices.size());
        verify(pricesRepository).findAll();
    }

    @DisplayName("Return List Prices when requestParameters are not nulls")
    @ParameterizedTest(name = "to ''{0}'' when parameters are ''{1}'' ''{2}'' ")
    @CsvSource(value = {"35455,1,2021-06-14 10:00",
            "35455,1,2021-06-14 16:00",
            "35455,1,2021-06-14 21:00",
            "35455,1,2021-06-15 10:00",
            "35455,1,2021-06-16 21:00",
    }, delimiter = ',',nullValues={"null"})
    void returnAlListPricesWhenRequestParametersAreNotNulls(String productCode,
                                                            Long brandId, String aplicationDate) throws ParseException {
        List<Price>  pricesList= Arrays.asList(createPrice001(),
                createPrice001());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        when(pricesRepository.findByProductCodeAndBrandIdAndStartBeforeAndEndAfter(productCode,
                brandId,formatter.parse(aplicationDate))).thenReturn(pricesList);

        List<PricesRs> cuentas = productsService.findPrices(Constant.FIND_BY_BRAND_PRODUCT_APPDATE,productCode,brandId,formatter.parse(aplicationDate));

        assertFalse(cuentas.isEmpty());
        assertEquals(2, cuentas.size());
        verify(pricesRepository).findByProductCodeAndBrandIdAndStartBeforeAndEndAfter(productCode,
                brandId,formatter.parse(aplicationDate));
    }
}