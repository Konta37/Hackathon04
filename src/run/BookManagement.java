package run;

import jdk.jfr.Category;
import model.Catalog;
import model.Product;
import service.CatalogService;
import service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookManagement {
    private static final ProductService productService = new ProductService();
    private static final CatalogService catalogService = new CatalogService();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("➢ ===== BASIC-MENU =====");
            System.out.println("1. Catalog Management");
            System.out.println("2. Product Management");
            System.out.println("3. Exit");
            System.out.println("Enter your choice: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    catalogMenu(sc);
                    break;
                case 2:
                    ProductService.productMenu(sc);
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.err.println("Please choose between 1 -> 3");
            }
        } while (true);
    }

    public static void catalogMenu(Scanner sc) {
        do {
            System.out.println("➢ ===== CATALOG-MENU =====");
            System.out.println("1. Enter number of catalogs and information");
            System.out.println("2. Show all catalogs");
            System.out.println("3. Update name catalog by id");
            System.out.println("4. Delete catalog by id");
            System.out.println("5. Exit");
            System.out.println("Enter your choice: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    addCatalogs(sc);
                    break;
                case 2:
                    showAllCatalogs();
                    break;
                case 3:
                    updateCatalogById(sc);
                    break;
                case 4:
                    deleteCatalogById(sc);
                    break;
                case 5:
                    return;
                default:
                    System.err.println("Please choose between 1 -> 3");
            }
        } while (true);
    }

    public static void addCatalogs(Scanner sc){
        System.out.println("Enter number of Catalogs: ");
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            Catalog catalogAdd = new Catalog();
            catalogAdd.inputData();
            catalogService.save(catalogAdd);
        }
    }
    public static void showAllCatalogs(){
        for (Catalog a : catalogService.getAll()) {
            System.out.println(a.toString());
            System.out.println("Finish showing all catalogs");
        }
    }
    public static void updateCatalogById(Scanner sc){
        System.out.println("Enter Catalog ID: ");
        do {
            String input = sc.nextLine();
            try {
                Catalog catalogUpdate = catalogService.findById(Integer.parseInt(input));
                if (catalogUpdate != null) {
                    catalogUpdate.setCatalogName(catalogUpdate.inputCatalogName(sc));
                    System.out.println("Update name successfully");
                    return;
                }else {
                    System.err.println("Catalog not found");
                }
            }catch (NumberFormatException e) {
                System.err.println("Invalid Catalog ID. Must be a number. Try again!");
            }
        }while (true);
    }
    public static void deleteCatalogById(Scanner sc){
        System.out.println("Enter Catalog ID: ");
        do {
            String input = sc.nextLine();
            try {
                catalogService.delete(Integer.parseInt(input));
                return;
            }catch (NumberFormatException e) {
                System.err.println("Invalid Catalog ID. Must be a number. Try again!");
            }
        }while (true);
    }
}
