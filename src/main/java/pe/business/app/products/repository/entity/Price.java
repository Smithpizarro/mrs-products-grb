package pe.business.app.products.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tbl_prices")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Price extends AuditingEntity implements Serializable,BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El id brand no puede ser vacia")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Brand brand;

    @Column(name="start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name="end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;


    @NotNull(message = "El id prices_rates no puede ser vacia")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "price_list", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private PriceRate priceRate;

    @NotNull(message = "El id producto no puede ser vacia")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Product product;

    @NotNull(message = "Priority no puede ser vacío")
    @Column(name="priority", nullable=false)
    private Long priority;

    @Positive(message = "El precio debe ser mayor que cero")
    private Double price;

    @NotNull(message = "La moneda no puede ser null")
    @NotEmpty(message = "La moneda  no debe ser vacía")
    private String curr;

    @PreUpdate
    public void preUpdate() {
        this.setUpdatedDate(new Date());
    }

}
