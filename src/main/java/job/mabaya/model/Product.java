package job.mabaya.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "product", schema = "HOMEWORK")
public class Product {
    @Id
    @Column(name = "serial_Number")
    private Long serialNumber;
    private String title;
    private String category;
    private Double price;

}
