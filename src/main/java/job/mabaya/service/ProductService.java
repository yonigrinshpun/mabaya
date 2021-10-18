package job.mabaya.service;

import job.mabaya.model.Campaign;
import job.mabaya.model.Product;
import job.mabaya.repository.CampaignRepository;
import job.mabaya.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CampaignRepository campaignRepository;
    @Autowired
    public ProductService(ProductRepository productRepository, CampaignRepository campaignRepository) {
        this.productRepository = productRepository;
        this.campaignRepository = campaignRepository;
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public Product productById(Long serialNumber) {return productRepository.getById(serialNumber);}

    public List<Product> productsByList(List<String> productList){
        List<Product> myList = new ArrayList<>();
        for(String productId: productList){
            Long serialNumber = Long.valueOf(productId);
            myList.add(productById(serialNumber));
        }
        return myList;
    }

    public List<Product> productByCategory(String category){
        return productRepository.findByCategory(category);
    }

    public Double bestBit(Product product){
        ZoneOffset defaultZone = ZoneOffset.ofHours(2);
        final int campaignPeriod = 10;
        Date fromDate = Date.from(LocalDate.now().minusDays(campaignPeriod).atStartOfDay().toInstant(defaultZone));
        List<Campaign> campaigns = campaignRepository.findByStartDateGreaterThan(fromDate);
        if(campaigns.size()==0){return (double)0;}
        try {
            return campaigns.stream().filter(c -> c.getPromotedProducts().contains(product))
                    .max((x, y) -> (int) (x.getBid() - y.getBid())).get().getBid();
        } catch (Exception e) {
            return (double)0;
        }
    }

    public Product ServeAd(String category) {
        List<Product> products = productByCategory(category);
        if (products.size() == 0) {
            products = findAll();
        }
        return products.stream().max((x, y) -> (int) (bestBit(x) - bestBit(y))).orElse(null);


    }
}
