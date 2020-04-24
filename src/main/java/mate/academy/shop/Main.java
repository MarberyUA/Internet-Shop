package mate.academy.shop;

import mate.academy.shop.lib.Injector;
import mate.academy.shop.model.Product;
import mate.academy.shop.service.ProductService;

public class Main {
    private static Injector injector = Injector.getInstance("mate.academy.shop");

    public static void main(String[] args) {
        ProductService itemService = (ProductService) injector.getInstance(ProductService.class);
        Product iPhone = new Product("Iphone 11", 1000.0);
        Product iPhonePro = new Product("Iphone 11Pro", 1500.0);
        itemService.create(iPhone);
        itemService.create(iPhonePro);

        System.out.println(itemService.get(iPhone.getId()));
        System.out.println(itemService.get(iPhonePro.getId()));

        iPhonePro.setName("Iphone 11ProPro");

        System.out.println(itemService.update(iPhonePro));

        System.out.println(itemService.delete(iPhonePro.getId()));
    }
}
