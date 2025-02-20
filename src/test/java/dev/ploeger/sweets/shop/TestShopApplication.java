package dev.ploeger.sweets.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestShopApplication {

    public static void main(String[] args) {
        SpringApplication.from(ShopApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
