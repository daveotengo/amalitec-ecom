package com.amalitec.amalitececom;

import com.amalitec.amalitececom.auth.AuthenticationService;
import com.amalitec.amalitececom.auth.RegisterRequest;
import com.amalitec.amalitececom.exception.GraphQLErrorAdapter;
import com.amalitec.amalitececom.model.Order;
import com.amalitec.amalitececom.model.Product;
import com.amalitec.amalitececom.model.ProductLine;
import com.amalitec.amalitececom.model.User;
import com.amalitec.amalitececom.repository.OrderRepository;
import com.amalitec.amalitececom.repository.ProductRepository;
import com.amalitec.amalitececom.repository.UserRepository;
import com.amalitec.amalitececom.service.OrderService;
import com.amalitec.amalitececom.service.ProductService;
import com.amalitec.amalitececom.service.UserService;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.kickstart.execution.error.GraphQLErrorHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.amalitec.amalitececom.auth.Role.ADMIN;
import static com.amalitec.amalitececom.auth.Role.MANAGER;


@SpringBootApplication
//@EnableAspectJAutoProxy
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AmalitecEcomApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmalitecEcomApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService service,
            ProductService productService,
            OrderService orderService,
            UserService userService

    ) {
        return args -> {
            var admin = RegisterRequest.builder()
                    .name("Admin")
                    .email("admin@mail.com")
                    .password("password")
                    .role(ADMIN)
                    .build();
            System.out.println("Admin token: " + service.register(admin).getAccessToken());

            var manager = RegisterRequest.builder()
                    .name("Manger")
                    .email("manager@mail.com")
                    .password("password")
                    .role(MANAGER)
                    .build();
            System.out.println("Manager token: " + service.register(manager).getAccessToken());

            // Create two products
            String productA="Product A";
            String productB="Product B";

            Product product1 = Product.builder()
                    .name(productA)
                    .price(Double.valueOf(20.0))
                    .stock(50)
                    .build();

            Product product2 = Product.builder()
                    .name(productB)
                    .price(Double.valueOf(30.0))
                    .stock(30)
                    .build();

            productService.createProductList(List.of(product1, product2));

            Product product= productService.getByProductName(productA);
            Product productBObject= productService.getByProductName(productB);

            Optional<User> userAdmin =userService.getByEmail(admin.getEmail());
            Optional<User> userManager =userService.getByEmail(manager.getEmail());
            if(userManager.isPresent()&&userAdmin.isPresent()) {

            // Create two orders with the created products and users
            Order order1 = Order.builder()
                    .user(userAdmin.get()) // Assign the user who placed the order
                    .build();

            ProductLine productLine1 = new ProductLine(5, product, order1);
            ProductLine productLine2 = new ProductLine(6, productBObject, order1);

            order1.setProductLineList(List.of(productLine1, productLine2));

            Order order2 = Order.builder()
                    .user(userManager.get()) // Assign the user who placed the order
                    .build();

            order2.setProductLineList(List.of(productLine1, productLine2));

            orderService.createListOfOrders(List.of(order1, order2));
            }

        };
    }




}
