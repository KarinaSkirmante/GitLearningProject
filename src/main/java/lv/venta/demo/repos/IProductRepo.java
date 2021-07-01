package lv.venta.demo.repos;

import java.util.ArrayList;

//comment branch

import org.springframework.data.repository.CrudRepository;

import lv.venta.demo.models.Product;

public interface IProductRepo extends CrudRepository<Product, Integer> {

	Product findByTitle(String string);

	ArrayList<Product> findByCategory(String string);

	ArrayList<Product> findByCategoryAndQuantity(String string, int i);

	ArrayList<Product> findByPriceLessThan(float f);

}
