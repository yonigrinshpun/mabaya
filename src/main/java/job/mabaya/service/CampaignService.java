package job.mabaya.service;

import job.mabaya.model.Campaign;
import job.mabaya.model.CampaignForm;
import job.mabaya.model.Product;
import job.mabaya.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final ProductService productService;
    @Autowired
    public CampaignService(CampaignRepository campaignRepository, ProductService productService) {
        this.campaignRepository = campaignRepository;
        this.productService = productService;
    }

    public Campaign saveCampaign(CampaignForm campaignForm){
        Date campaignDate = Date.valueOf(campaignForm.getStartDate());
        List<Product> products = productService.productsByList(campaignForm.getPromotedProducts());

        Campaign campaign = new Campaign(campaignForm.getName(), campaignDate, products, campaignForm.getBid());
        return campaignRepository.save(campaign);
    }

    public Campaign campaignById(String name){
        return campaignRepository.findById(name).get();
    }

}
