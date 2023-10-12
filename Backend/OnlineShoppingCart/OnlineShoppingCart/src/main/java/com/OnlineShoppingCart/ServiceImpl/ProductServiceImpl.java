package com.OnlineShoppingCart.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OnlineShoppingCart.Entity.Product;
import com.OnlineShoppingCart.Exceptions.ProductCategoryNotFoundException;
import com.OnlineShoppingCart.Exceptions.ProductNotFoundException;
import com.OnlineShoppingCart.Exceptions.ProductTypeNotExistsException;
import com.OnlineShoppingCart.Repository.ProductRepository;
import com.OnlineShoppingCart.Service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepository productRepo;
	@Override
	public Product addProduct(Product product) throws ProductNotFoundException {
		Optional<Product> P1 = productRepo.findById(product.getProductId());
		List<Product> l1 = productRepo.findAll();
		if (!P1.isPresent()) {
			//product.setProductId(l1.get(l1.size() - 1).getProductId() +1);
			//product.setProductId(l1.get(l1.size()-1).getProductId() +10);
			 if (l1.isEmpty()) {
			        // The list is empty, so set the product ID to an initial value, e.g., 1.
			        product.setProductId(1);
			    } else {
			        // The list is not empty, so set the product ID to the last product's ID + 10.
			        product.setProductId(l1.get(l1.size() - 1).getProductId() + 10);
			    }
			return productRepo.save(product);
		} else {
			throw new ProductNotFoundException("Product is present already");
		}
		

	}

	@Override
	public List<Product> getAllProduct() throws ProductNotFoundException {

		List<Product> p1 = productRepo.findAll();
		if (!p1.isEmpty()) {

			return productRepo.findAll();
		} else {

			throw new ProductNotFoundException("Data is not found");
		}

	}

	@Override
	public Product getProductById(int productId) throws ProductNotFoundException {

		Optional<Product> p1 = productRepo.findById(productId);
		// Optional<Product> findById = productRepo.findById(productId);
		if (p1.isPresent()) {

			return p1.get();

		} else {

			throw new ProductNotFoundException(productId + " is not found");
		}

	}

	@Override
	public List<Product> getProductByName(String productName) throws ProductNotFoundException {

		List<Product> p1 = productRepo.findByProductName(productName);
		if (!p1.isEmpty()) {

			return productRepo.findByProductName(productName);
		} else {

			throw new ProductNotFoundException(productName + " is not Exists");

		}
	}

	@Override
	public Product updateProducts(int productId, Product product) throws ProductNotFoundException {

		Optional<Product> p1 = productRepo.findByProductId(productId);
		if (p1.isPresent()) {
			Product existingProduct = p1.get();
			existingProduct.setProductName(product.getProductName());
			existingProduct.setPrice(product.getPrice());
			existingProduct.setRating(product.getRating());
			existingProduct.setCategory(product.getCategory());
			existingProduct.setDescription(product.getDescription());
			existingProduct.setImage(product.getImage());
			existingProduct.setProductType(product.getProductType());
			existingProduct.setReview(product.getReview());
			existingProduct.setSpecification(product.getSpecification());
			productRepo.save(existingProduct);
			return existingProduct;

		}
		else {
			throw new ProductNotFoundException("product not found");
		}
	}

	@Override
	public String deleteProductById(int productId) throws ProductNotFoundException {

		Optional<Product> p1 = productRepo.findById(productId);
		// Optional<Product> id = productRepo.findByProductId(productId);
		if (p1.isPresent()) {
			// productRepo.delete(p1.get());
			productRepo.deleteById(productId);
			return " Product will be Deleted successfully";
		} else {

			throw new ProductNotFoundException("Product Not exists with that correspong Id");

		}

	}

	@Override
	public List<Product> getProductByCategory(String category) throws ProductCategoryNotFoundException {

		List<Product> p1 = productRepo.findByCategory(category);
		if (!p1.isEmpty()) {
			return productRepo.findByCategory(category);
		} else {

			throw new ProductCategoryNotFoundException(category + " not exists ");
		}

	}

	@Override
	public List<Product> getProductByType(String productType) throws ProductTypeNotExistsException {

		List<Product> p1 = productRepo.findByProductType(productType);
		if (!p1.isEmpty()) {

			return productRepo.findByProductType(productType);
		} else {

			throw new ProductTypeNotExistsException(productType + " Does not exists");
		}
	}
	
	public List<Product> getAllProductsByName(String name){
		return productRepo.findAllByProductName(name);
	}

	
}
