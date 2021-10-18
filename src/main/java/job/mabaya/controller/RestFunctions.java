package job.mabaya.controller;

import job.mabaya.model.Campaign;
import job.mabaya.model.Product;
import job.mabaya.service.CampaignService;
import job.mabaya.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RestFunctions {
    private final ProductService productService;
    private final CampaignService campaignService;
    @Autowired
    public RestFunctions(ProductService productService, CampaignService campaignService) {
        this.productService = productService;
        this.campaignService = campaignService;
    }

    @GetMapping("/campaign/{name}")
    public Campaign CreateCampaign(@PathVariable("name") String name){
        return campaignService.campaignById(name);

    }

    @GetMapping("/category")
    public Product ServeAd(@RequestParam(defaultValue = "comp") String category){
        return productService.ServeAd(category);
    }
}
