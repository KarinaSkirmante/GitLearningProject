package lv.venta.demo.controllers;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lv.venta.demo.models.Product;
import lv.venta.demo.repos.IProductRepo;



//CONTROLLER FROM MVC
//comment
@Controller
public class ProductController {
	
	@Autowired
	IProductRepo prodRepo;
	
	//ArrayList<Product> allProducts = new ArrayList<Product>();
	
	@GetMapping("/home")// localhost:8080/home
	public String getHomePage()
	{
		System.out.println("getHomePage is working");
		Product p1 = new Product("Dators", "Portatīvais asus", 400.99f, 4.5, "Super dators", 2);
		Product p2 = new Product("Pārtika", "Saldējums ekselence", 0.99f, 0.3, "Loti garšīgs saldējums", 15);
		Product p3 = new Product("Pārtika", "Limonāde", 0.67f, 0.6, "Atsvaidzinoša", 5);
			
		/*
		allProducts.add(p1);
		allProducts.add(p2);
		allProducts.add(p3);
		*/
		
		prodRepo.save(p1);
		prodRepo.save(p2);
		prodRepo.save(p3);
		
		
		
		Product result = prodRepo.findByTitle("Limonāde");
		System.out.println(result);
		
		ArrayList<Product> result2 = prodRepo.findByCategory("Pārtika");
		System.out.println(result2);
		
		ArrayList<Product> result3 = prodRepo.findByCategoryAndQuantity("Pārtika", 5);
		System.out.println(result3);
		
		
		ArrayList<Product> result4  =prodRepo.findByPriceLessThan(1f);
		System.out.println(result4);
		
		//findByTitle
		//findByPrice
		//findByPriceLessThan
		//findByCategory
		
		return "home-page";//will show home-page.html
	}
	
	@GetMapping("/testProduct")//localhost:8080/testProduct
	public String getTestProduct(Model model)
	{
		Product myTestProduct = new Product("Saldumi", "Saldējums Ekselence", 0.99f, 0.3, "Loti garšīgs saldējums", 15);
		model.addAttribute("packet", myTestProduct);
		System.out.println(myTestProduct.toString());
		return "test-product-page";//will show test-product-page.html
	}
	
	@GetMapping("/testProduct2")//localhost:8080/testProduct2
	public String getTestProduct2(Model model)
	{
		Product p1 = new Product("Dators", "Portatīvais ASUS", 400.99f, 4.5, "Super dators", 2);
		model.addAttribute("packet", p1);
		return "test-product-page";//will show test-product-page.html
	}
	
	@GetMapping("/allProducts")//localhost:8080/allProducts
	public String getAllProducts(Model model)
	{
		//model.addAttribute("packet", allProducts);
		model.addAttribute("packet", prodRepo.findAll());
		return "all-product-page";//will show all-product-page.html
		
	}
	
	
	
	@GetMapping("/selectProduct") //localhost:8080/selectProduct?id=2
	public String getSelectProduct(Model model, @RequestParam(name="id") int id)
	{
		
		/*
		for(int i = 0; i < allProducts.size(); i++)//i=0, i=1, i=2
		{
			if(allProducts.get(i).getId() == id )
				model.addAttribute("packet", allProducts.get(i));
		}
		*/
		
		if(prodRepo.existsById(id))
		{
			Product prodFromDB = prodRepo.findById(id).get();
			model.addAttribute("packet", prodFromDB);
		}
		
		return "test-product-page";//will show test-product-page.html
		
		
	}
	
	@GetMapping("/allProducts/{id}")//localhost:8080/allProducts/2
	public String getAllProductsById(Model model, @PathVariable(name="id") int id)
	{
		/*for(int i = 0; i < allProducts.size(); i++)//i=0, i=1, i=2
		{
			if(allProducts.get(i).getId() == id )
				model.addAttribute("packet", allProducts.get(i));
		}*/
		if(prodRepo.existsById(id))
		{
			Product prodFromDB = prodRepo.findById(id).get();
			model.addAttribute("packet", prodFromDB);
		}
		
		return "test-product-page";//will show test-product-page.html
	}
	
	@GetMapping("/deleteProduct/{id}")//localhost:8080/deleteProduct/2
	public String getDeleteProducyById(@PathVariable(name="id") int id, Model model)
	{
		/*for(int i = 0; i < allProducts.size(); i++)//i=0, i=1, i=2
		{
			if(allProducts.get(i).getId() == id )
			{
				allProducts.remove(allProducts.get(i));
				break;
			}
		}*/
		
		if(prodRepo.existsById(id))
		{
			prodRepo.deleteById(id); 
		}		
		
		//model.addAttribute("packet", allProduct);
		model.addAttribute("packet", prodRepo.findAll());
		return "all-product-page";//will show all-product-page.html
	}

	@GetMapping("/addProduct")//localhost:8080/addProduct
	public String getAddProduct(Product product)//sent empty product to html
	{
		return "add-product-page";//html file
	}
	
	
	@PostMapping("/addProduct")
	public String postAddProduct(@Valid Product product, BindingResult result)//got fill product from html
	{
		if(result.hasErrors())
		{
			return "add-product-page";
		}
		else
		{
			System.out.println(product);
			Product p1 = new Product(product.getCategory(), product.getTitle(), product.getPrice(),product.getWeight(), product.getDescription(), product.getQuantity() );
			//allProducts.add(p1);
			prodRepo.save(p1);
			return "redirect:/allProducts";//will simulate /allProduct url address
		}
	}
	
	
	
	@GetMapping("/updateProduct/{id}")//localhost:8080/updateProduct/2
	public String getUpdateProductById(@PathVariable(name="id") int id, Model model)
	{
		/*for(int i = 0; i < allProducts.size(); i++)//i=0, i=1, i=2
		{
			if(allProducts.get(i).getId() == id )
			{
				model.addAttribute("product", allProducts.get(i));
			}
			
		}*/
		if(prodRepo.existsById(id))
		{
			Product prodFromDB = prodRepo.findById(id).get();
			model.addAttribute("product", prodFromDB);
		}
		
		
		return "update-product-page";
	}
	
	@PostMapping("/updateProduct/{id}")
	public String postUpdateProductById(@PathVariable(name="id") int id, Product product )
	{
		/*System.out.println(product);
		for(int i = 0; i < allProducts.size(); i++)//i=0, i=1, i=2
		{
			Product temp = allProducts.get(i);
			if(temp.getTitle().equals(product.getTitle()))
			{
				temp.setCategory(product.getCategory());
				temp.setPrice(product.getPrice());
				temp.setWeight(product.getWeight());
				temp.setDescription(product.getDescription());
				temp.setQuantity(product.getQuantity());
			}
		}
		*/
		if(prodRepo.existsById(id))
		{
			Product temp = prodRepo.findById(id).get();
			temp.setCategory(product.getCategory());
			temp.setPrice(product.getPrice());
			temp.setWeight(product.getWeight());
			temp.setDescription(product.getDescription());
			temp.setQuantity(product.getQuantity());
			prodRepo.save(temp);
		}
		
		
		
		return "redirect:/allProducts/"+id;//localhost:8080/allProducts/1, ja id=1
	}
	
	
	
	
	
	
	
}
