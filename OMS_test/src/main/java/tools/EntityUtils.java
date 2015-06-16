package tools;

import entity.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olya on 15.06.2015.
 */
public class EntityUtils {
    private static final String PRODUCT_DESCRIPTION = "ProductDescription";
    private static final String PRODUCT_NAME = "Product";

    /**
     * Generate random product.
     * @param index an integer using on randomizing.
     * @return new product.
     */
    public static Product generateRandomProduct(int index) {
        Product product = new Product();
        product.setProductName(PRODUCT_NAME + index);
        product.setProductDescription(PRODUCT_DESCRIPTION + index);
        product.setProductPrice(Math.random() * 1000);
        product.setActive(true);
        return product;
    }

    /**
     * Return a list with n products.
     * @param size the number of new products.
     * @return list of products.
     */
    public static List<Product> generateRandomProducts(int size) {
        List<Product> entities = new ArrayList<Product>();
        for (int i = 0; i < size; i++) {
            entities.add(EntityUtils.generateRandomProduct(i));
        }
        return entities;
    }
}
