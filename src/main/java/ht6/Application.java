package ht6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		ProductService productService = context.getBean("productService", ProductService.class);
		UserService userService = context.getBean("userService", UserService.class);

		System.out.println("\nProducts for user 3\n");
		System.out.println("\n" + userService.getUserProducts(3));
		System.out.println("\nUsers for product 2\n");
		System.out.println("\n" + productService.getProductUsers(2));
		System.out.println("\nUsers for product 4\n");
		System.out.println("\n" + productService.getProductUsers(4));
		System.out.println("\nProducts for user 1 with current cost\n");
		System.out.println("\n" + userService.getUserProductsWithCurrentCost(1));
	}

}
