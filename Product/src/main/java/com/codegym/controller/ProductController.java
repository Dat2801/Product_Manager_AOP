package com.codegym.controller;

import com.codegym.exception.NotFoundException;
import com.codegym.model.Category;
import com.codegym.model.Product;
import com.codegym.service.category.ICategoryService;
import com.codegym.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView showInputNotAcceptable() {
        return new ModelAndView("notfound");
    }

    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("categoryList")
    public Iterable<Category> categories() {
        return categoryService.findALl();
    }

    @Autowired
    private IProductService productService;

//        @GetMapping("")
//    public ModelAndView getAll() {
//        ModelAndView modelAndView = new ModelAndView("list");
//        Iterable<Product> productList = productService.findALl();
//        modelAndView.addObject("products", productList);
//        return modelAndView;
//    }
    @GetMapping("")
    public ModelAndView getPage(@PageableDefault(size = 10) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("list");
        Page<Product> productPage = productService.findALl(pageable);
        modelAndView.addObject("products", productPage);
        return modelAndView;
    }

    @PostMapping("")
    public ModelAndView search(@RequestParam String search) {
        ModelAndView modelAndView = new ModelAndView("list");
        List<Product> result = productService.findProductName(search);
        modelAndView.addObject("products", result);
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView showFormEdit(@PathVariable Long id) throws NotFoundException {
        Product product = productService.findById(id);
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editProduct(@PathVariable Long id, @ModelAttribute Product product) {
        product.setId(id);
        productService.save(product);
        return new ModelAndView("redirect:/products");
    }

    @GetMapping("delete/{id}")
    public ModelAndView deleteProduct(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        productService.deleteById(id);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("p", new Product());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createProduct(@Validated @ModelAttribute Product product,BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()){
           return new ModelAndView("create");
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        productService.save(product);
        return modelAndView;
    }

}
