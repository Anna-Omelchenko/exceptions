package ru.netology.shop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.shop.exception.AlreadyExistsException;
import ru.netology.shop.exception.NotFoundException;

public class ShopRepositoryTest {

    @Test
    public void testRemoveByExistingId() {
        ShopRepository shopRepository = new ShopRepository();
        int productIdA = 15;
        Product productA = new Product(productIdA, "Torch", 500);
        Product productB = new Product(25, "Jacket", 5000);
        shopRepository.add(productA);
        shopRepository.add(productB);
        shopRepository.removeById(productIdA);
        Assertions.assertArrayEquals(new Product[]{productB}, shopRepository.findAll());
    }

    @Test
    public void testRemoveByNonExistingId() {
        ShopRepository shopRepository = new ShopRepository();
        Product productA = new Product(15, "Torch", 500);
        Product productB = new Product(25, "Jacket", 5000);
        shopRepository.add(productA);
        shopRepository.add(productB);
        Assertions.assertThrows(NotFoundException.class, () -> shopRepository.removeById(100));
        Assertions.assertArrayEquals(new Product[]{productA, productB}, shopRepository.findAll());
    }

    @Test
    public void testAddProduct() {
        ShopRepository shopRepository = new ShopRepository();
        Product productA = new Product(15, "Torch", 500);
        shopRepository.add(productA);
        Assertions.assertArrayEquals(new Product[]{productA}, shopRepository.findAll());
        Product productB = new Product(25, "Jacket", 5000);
        shopRepository.add(productB);
        Assertions.assertArrayEquals(new Product[]{productA, productB}, shopRepository.findAll());
    }

    @Test
    public void testAddExistingProduct() {
        ShopRepository shopRepository = new ShopRepository();
        Product productA = new Product(15, "Torch", 500);
        Product productB = new Product(15, "Jacket", 5000);
        shopRepository.add(productA);
        Assertions.assertArrayEquals(new Product[]{productA}, shopRepository.findAll());
        Assertions.assertThrows(AlreadyExistsException.class, () -> shopRepository.add(productB));
        Assertions.assertArrayEquals(new Product[]{productA}, shopRepository.findAll());
    }
}
