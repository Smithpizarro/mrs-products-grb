package pe.business.app.products.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tbl_products")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Product extends AuditingEntity implements Serializable,BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "numero producto no debe ser vacío")
    @Column(name = "product_code", unique = true)
    private String productCode;

    @NotEmpty(message = "El nombre no debe ser vacío")
    private String name;

    private String description;

    @Positive(message = "El stock debe ser mayor que cero")
    private Double stock;

    @PreUpdate
    public void preUpdate() {
        this.setUpdatedDate(new Date());
    }

}
