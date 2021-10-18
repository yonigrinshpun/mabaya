package job.mabaya.controller;

import job.mabaya.model.Product;
import job.mabaya.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String findAll(Model model){
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "product-list";
    }

    @GetMapping("/product-create")
    public String createProductForm(Product product){
        return "product-create";
    }

    @PostMapping("/product-create")
    public String createProduct(Product product){
        productService.saveProduct(product);
        return "redirect:/products";
    }
}
