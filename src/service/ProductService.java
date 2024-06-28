package service;

import model.Catalog;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductService implements IGenericService<Product,String>{
    public static List<Product> productList = new ArrayList<Product>();


    @Override
    public List<Product> getAll() {
        return productList;
    }

    @Override
    public void save(Product product) {
        Product productCheck = findById(product.getProductId());
        if (productCheck == null) {
            product.inputData();
            productList.add(product);
            System.out.println("Product added");
        }else {
            productList.set(productList.size(), product);
            System.out.println("Product updated");
        }
    }

    @Override
    public Product findById(String integer) {
        for (Product product : productList) {
            if (product.getProductId().equals(integer)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void delete(String integer) {
        boolean isExist = false;
        for (Product product : productList) {
            if (product.getProductId().equals(integer)) {
                productList.remove(product);
                isExist = true;
                System.out.println("Product removed");
                break;
            }
        }
        if(isExist){
            System.out.println("Product with id not found to delete");
        }
        System.out.println("Finish deleting Product");
    }

    public static void productMenu(Scanner sc) {
        do {
            System.out.println("➢ ===== PRODUCT-MENU =====");
            System.out.println("1. Enter number of products and information");
            System.out.println("2. Show all products");
            System.out.println("3. Sort products by price");
            System.out.println("4. Delete product by id");
            System.out.println("5. Search product by name");
            System.out.println("6. Update product by id");
            System.out.println("7. Exit");
            System.out.println("Enter your choice: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    return;
                default:
                    System.err.println("Please choose between 1 -> 3");
            }
        } while (true);
    }
}
