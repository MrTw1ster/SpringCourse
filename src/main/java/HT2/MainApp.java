package HT2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ProductService productService = context.getBean("productService", ProductService.class);

        Scanner sc = new Scanner(System.in);
        while (true) {
            String cmd = sc.next();
            if (cmd.equals("add")) {
                if (sc.hasNextInt()) {
                    int id = sc.nextInt();
                    if (sc.hasNextDouble()) {
                        double cost = sc.nextDouble();
                        String title = sc.nextLine().trim();
                        productService.addProduct(id, title, cost);
                    } else {
                        System.out.println("incorrect cost");
                    }
                } else {
                    System.out.println("incorrect id");
                }
            }
            if (cmd.equals("get")) {
                if (sc.hasNextInt()) {
                    int id = sc.nextInt();
                    System.out.println(productService.getProduct(id));
                }
            }
            if (cmd.equals("get_list")) {
                String[] arrStr = sc.nextLine().trim().split(" ");
                try {
                    System.out.println(productService.getListProduct(Arrays.stream(arrStr)
                            .map(Integer::parseInt)
                            .collect(Collectors.toList())));
                } catch (NumberFormatException e) {
                    System.out.println("incorrect id");
                }
            }
            if (cmd.equals("update_title")) {
                if (sc.hasNextInt()) {
                    int id = sc.nextInt();
                    String newTitle = sc.nextLine().trim();
                    productService.updateTitle(id, newTitle);
                } else {
                    System.out.println("incorrect id");
                }
            }
            if (cmd.equals("update_cost")) {
                if (sc.hasNextInt()) {
                    int id = sc.nextInt();
                    if (sc.hasNextDouble()) {
                        double cost = sc.nextDouble();
                        productService.updateCost(id, cost);
                    } else {
                        System.out.println("incorrect cost");
                    }
                } else {
                    System.out.println("incorrect id");
                }
            }
            if (cmd.equals("delete")) {
                if (sc.hasNextInt()) {
                    int id = sc.nextInt();
                    productService.deleteProduct(id);
                } else {
                    System.out.println("incorrect id");
                }
            }
            if (cmd.equals("exit")) {
                break;
            }
        }
        context.close();
    }
}
