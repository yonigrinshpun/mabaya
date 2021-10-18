package job.mabaya.controller;

import job.mabaya.model.CampaignForm;
import job.mabaya.model.Product;
import job.mabaya.service.CampaignService;
import job.mabaya.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CampaignController {

    @Autowired
    private CampaignService campaignService;
    @Autowired
    private ProductService productService;

    @GetMapping("/campaign-create")
    public String createCampaignForm(CampaignForm campaignForm, Model model){
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "campaign-create";
    }

    @PostMapping("/campaign-create")
    public String createCampaign(CampaignForm campaignForm){
       campaignService.saveCampaign(campaignForm);
       String newPath = "redirect:/campaign/" + campaignForm.getName();
//                + campaignForm.getName()
        return newPath;
    }
}
