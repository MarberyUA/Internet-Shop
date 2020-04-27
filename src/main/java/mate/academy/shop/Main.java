package mate.academy.shop;

import mate.academy.shop.lib.Injector;
import mate.academy.shop.model.Order;
import mate.academy.shop.model.Product;
import mate.academy.shop.model.ShoppingCard;
import mate.academy.shop.model.User;
import mate.academy.shop.service.OrderService;
import mate.academy.shop.service.ProductService;
import mate.academy.shop.service.ShoppingCardService;
import mate.academy.shop.service.UserService;

public class Main {
    private static Injector injector = Injector.getInstance("mate.academy.shop");

    public static void main(String[] args) {
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);

        Product iphone = new Product("Iphone 11", 1000.0);
        Product iphonePro = new Product("Iphone 11Pro", 1500.0);
        Product meat = new Product("meat", 100.0);
        Product milk = new Product("milk", 30.0);
        Product water = new Product("water", 50.0);
        Product cat = new Product("cat", 900.0);
        productService.create(iphone);
        productService.create(iphonePro);
        productService.create(meat);
        productService.create(milk);
        productService.create(water);
        productService.create(cat);
        
        char a = 'd';
        System.out.printf(((byte) a) + "");
        System.out.println(productService.getAll());

        UserService userService = (UserService) injector.getInstance(UserService.class);
        User user = new User();
        user.setName("Rostislav");
        User registeredUser = userService.create(user);

        System.out.println(userService.get(registeredUser.getId()));
        System.out.println(userService.getAll());
        registeredUser.setName("Rostislav Levadnyi");
        System.out.println(userService.update(registeredUser));

        ShoppingCardService shoppingCardService = (ShoppingCardService)
                injector.getInstance(ShoppingCardService.class);
        ShoppingCard shoppingCard = new ShoppingCard();
        shoppingCard = shoppingCardService.create(shoppingCard, registeredUser);
        shoppingCardService.addProduct(shoppingCard, milk);
        System.out.println(shoppingCardService.getByUserId(registeredUser.getId()));
        shoppingCardService.addProduct(shoppingCard, cat);
        shoppingCardService.addProduct(shoppingCard, water);
        System.out.println(shoppingCardService.getByUserId(registeredUser.getId()));
        shoppingCardService.deleteProduct(shoppingCard, water);
        System.out.println(shoppingCardService.getByUserId(registeredUser.getId()));

        OrderService orderService = (OrderService) injector.getInstance(OrderService.class);
        Order order = orderService.completeOrder(shoppingCard.getProductsInShopping(),
                registeredUser);
        System.out.println(orderService.getUserOrders(registeredUser));
        shoppingCardService.clear(shoppingCard);

        shoppingCardService.addProduct(shoppingCard, iphone);
        shoppingCardService.addProduct(shoppingCard, iphonePro);

        Order newOrder = orderService.completeOrder(shoppingCard.getProductsInShopping(),
                registeredUser);
        System.out.println(orderService.getUserOrders(registeredUser));
    }
}
