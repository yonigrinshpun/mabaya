package job.mabaya.model;

import lombok.Data;

import javax.persistence.Id;
import java.util.List;

@Data
public class CampaignForm {
    @Id
    private String name;
    private String startDate;
    private List<String> promotedProducts;
    private double Bid;

    public CampaignForm() {

    }
}
