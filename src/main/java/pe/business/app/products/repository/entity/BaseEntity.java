package pe.business.app.products.repository.entity;


import java.util.Date;

public interface BaseEntity {

    public String getUpdatedBy();

    public void setUpdatedBy(String updatedBy);

    public Date getUpdatedDate();

    public void setUpdatedDate(Date updatedDate);


}

