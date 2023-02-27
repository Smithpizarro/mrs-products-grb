package pe.business.app.products.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tbl_prices_rates")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class PriceRate {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private Double rate;

}
