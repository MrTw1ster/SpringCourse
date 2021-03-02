package ht4;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(int id) {
        super("Not found product with id = " + id);
    }
}
