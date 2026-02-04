package ru.yourname.shop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ru.yourname.shop.exception.AlreadyExistsException;
import ru.yourname.shop.exception.NotFoundException;

class ShopRepositoryTest {

    @Test
    void addNewProduct_Success() {
        ShopRepository repo = new ShopRepository();
        Product product = new Product(1, "Laptop", 1000);
        repo.add(product);
        assertNotNull(repo.findById(1));
        assertEquals("Laptop", repo.findById(1).getTitle());
    }

    @Test
    void addDuplicateProduct_ThrowsAlreadyExistsException() {
        ShopRepository repo = new ShopRepository();
        repo.add(new Product(2, "Mouse", 50));
        Product duplicate = new Product(2, "Another Mouse", 60);

        assertThrows(AlreadyExistsException.class, () -> {
            repo.add(duplicate);
        });
    }

    @Test
    void removeExistingProduct_Success() {
        ShopRepository repo = new ShopRepository();
        Product product = new Product(3, "Keyboard", 150);
        repo.add(product);
        repo.remove(3);
        assertNull(repo.findById(3));
    }

    @Test
    void removeNonExistingProduct_ThrowsNotFoundException() {
        ShopRepository repo = new ShopRepository();
        assertThrows(NotFoundException.class, () -> {
            repo.remove(999);
        });
    }

    @Test
    void findById_ExistingReturnsProduct() {
        ShopRepository repo = new ShopRepository();
        Product product = new Product(4, "Monitor", 200);
        repo.add(product);
        Product found = repo.findById(4);
        assertNotNull(found);
        assertEquals(4, found.getId());
        assertEquals("Monitor", found.getTitle());
    }

    @Test
    void findById_NonExistingReturnsNull() {
        ShopRepository repo = new ShopRepository();
        Product found = repo.findById(555);
        assertNull(found);
    }
}
