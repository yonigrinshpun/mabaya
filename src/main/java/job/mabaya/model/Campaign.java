package job.mabaya.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "campaign", schema = "HOMEWORK")
public class Campaign {
    @Id
    private String name;
    private Date startDate;
    @ManyToMany
    private List<Product> promotedProducts;
    private double Bid;

    public Campaign(String name, Date startDate, List<Product> promotedProducts, double bid) {
        this.name = name;
        this.startDate = startDate;
        this.promotedProducts = promotedProducts;
        Bid = bid;
    }

    public Campaign() {

    }
}
