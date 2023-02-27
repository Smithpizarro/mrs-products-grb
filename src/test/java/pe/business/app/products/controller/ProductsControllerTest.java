package pe.business.app.products.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pe.business.app.products.model.PricesRs;
import pe.business.app.products.service.ProductsService;
import pe.business.app.products.util.Constant;
import pe.business.app.products.util.ErrorMessageBuilder;
import pe.business.app.products.util.ErrorMessageDef;

import static pe.business.app.products.util.Datos.*;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ProductsController.class)
class ProductsControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductsService productsService;

    @MockBean
    @Autowired
    private ErrorMessageBuilder errorMessageBuilder;

    @MockBean
    @Autowired
    private ErrorMessageDef errorMessageDef;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @DisplayName("Return response ok  when requestParameters are nulls")
    @ParameterizedTest(name = "to ''{0}'' when parameters are ''{1}'' ''{2}'' ''{3}'' ")
    @CsvSource(value = {"FIND_ALL,null,null,null"
            }, delimiter = ',',nullValues={"null"})
    void ReturnResponseOkWhenRequestParametersAreNull(String typeFind, String productCode,
                                                    Long brandId, Date aplicationDate) throws Exception  {
      List<PricesRs>  pricesList= Arrays.asList(createPrices001(),
              createPrices002());

      when(productsService.findPrices(typeFind,productCode,brandId,aplicationDate
          )).thenReturn(pricesList);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("desRpta", "Respuesta Exitosa");
        response.put("detRpta", pricesList);
        mvc.perform(get("/products/prices").contentType(MediaType.APPLICATION_JSON))
                // Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.detRpta[0].productCode").value("35455"))
                .andExpect(jsonPath("$.detRpta[1].productCode").value("35456"))
                .andExpect(jsonPath("$.detRpta[0].finalPrice").value("35.5 EUR"))
                .andExpect(jsonPath("$.detRpta[1].finalPrice").value("25.5 EUR"))
                .andExpect(jsonPath("$.detRpta", hasSize(2)))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));

        verify(productsService).findPrices(Constant.FIND_ALL,null,
                null,null);
    }

    @DisplayName("Return response ok  when requestParameters are not nulls")
    @ParameterizedTest(name = "to ''{0}'' when parameters are ''{1}'' ''{2}'' ''{3}'' ")
    @CsvSource(value = {"FIND_BY_BRAND_PRODUCT_APPDATE,35455,1,2021-06-14 10:00",
                        "FIND_BY_BRAND_PRODUCT_APPDATE,35455,1,2021-06-14 16:00",
                        "FIND_BY_BRAND_PRODUCT_APPDATE,35455,1,2021-06-14 21:00",
                        "FIND_BY_BRAND_PRODUCT_APPDATE,35455,1,2021-06-15 10:00",
                        "FIND_BY_BRAND_PRODUCT_APPDATE,35455,1,2021-06-16 21:00",
    }, delimiter = ',',nullValues={"null"})
    void ReturnResponseOkWhenRequestParameterIsFindAll(String typeFind, String productCode,
                                                       Long brandId, String aplicationDate) throws Exception  {
        List<PricesRs>  pricesList= Arrays.asList(createPrices001(),
                createPrices002());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        when(productsService.findPrices(typeFind,productCode,brandId,formatter.parse(aplicationDate)
        )).thenReturn(pricesList);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("desRpta", "Respuesta Exitosa");
        response.put("detRpta", pricesList);
        mvc.perform(get("/products/prices")
                        .param("productCode", productCode)
                        .param("brandId", brandId.toString())
                        .param("aplicationDate", aplicationDate)
                        .contentType(MediaType.APPLICATION_JSON))
                // Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.detRpta[0].productCode").value("35455"))
                .andExpect(jsonPath("$.detRpta[1].productCode").value("35456"))
                .andExpect(jsonPath("$.detRpta[0].finalPrice").value("35.5 EUR"))
                .andExpect(jsonPath("$.detRpta[1].finalPrice").value("25.5 EUR"))
                .andExpect(jsonPath("$.detRpta", hasSize(2)))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));

        verify(productsService).findPrices(Constant.FIND_BY_BRAND_PRODUCT_APPDATE,productCode,
                brandId,formatter.parse(aplicationDate));
    }
}