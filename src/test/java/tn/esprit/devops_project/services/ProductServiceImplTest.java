package tn.esprit.devops_project.services;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.ProductServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private StockRepository stockRepository;

    @Test
    public void testAddProduct() {
        Product product = new Product();
        Long idStock = 1L;
        Stock stock = new Stock();
        when(stockRepository.findById(idStock)).thenReturn(Optional.of(stock));
        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.addProduct(product, idStock);

        verify(stockRepository, times(1)).findById(idStock);
        verify(productRepository, times(1)).save(product);
        assertEquals(stock, result.getStock());
    }

    @Test
    public void testRetrieveProduct() {
        Long id = 1L;
        Product expectedProduct = new Product();
        when(productRepository.findById(id)).thenReturn(Optional.of(expectedProduct));

        Product result = productService.retrieveProduct(id);

        verify(productRepository, times(1)).findById(id);
        assertEquals(expectedProduct, result);
    }

    @Test
    public void testRetreiveAllProduct() {
        List<Product> expectedProducts = new ArrayList<>();
        when(productRepository.findAll()).thenReturn(expectedProducts);

        List<Product> result = productService.retreiveAllProduct();

        verify(productRepository, times(1)).findAll();
        assertEquals(expectedProducts, result);
    }
}