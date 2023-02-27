package pe.business.app.products.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter
//@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PricesRs {

 public String productCode;
 public Long brandId;
 public String brandName;
 public String pricesList;

 @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
 public String startDate;

 @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
 public String endDate;

 public String price;

 public String finalPrice;




}
