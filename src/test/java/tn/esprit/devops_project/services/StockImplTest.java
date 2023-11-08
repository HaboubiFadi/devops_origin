package tn.esprit.devops_project.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class StockImplTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    @Test
    void testAddStock() {
        Stock stockToAdd = new Stock();
        stockToAdd.setTitle("Test Stock");
        stockToAdd.setProducts(new HashSet<>());

        Mockito.when(stockRepository.save(stockToAdd)).thenReturn(stockToAdd);

        Stock addedStock = stockService.addStock(stockToAdd);

        assertNotNull(addedStock);
        assertEquals("Test Stock", addedStock.getTitle());
    }

    @Test
    void testRetrieveStock() {
        Long stockId = 1L;
        Stock stock = new Stock();
        stock.setIdStock(stockId);
        stock.setTitle("Test Stock");
        stock.setProducts(new HashSet<>());

        Mockito.when(stockRepository.findById(stockId)).thenReturn(Optional.of(stock));

        Stock retrievedStock = stockService.retrieveStock(stockId);

        assertNotNull(retrievedStock);
        assertEquals(stockId, retrievedStock.getIdStock());
        assertEquals("Test Stock", retrievedStock.getTitle());
    }

    @Test
    void testRetrieveAllStock() {
        List<Stock> stockList = new ArrayList<>();
        stockList.add(new Stock(1L, "Stock 1", new HashSet<>()));
        stockList.add(new Stock(2L, "Stock 2", new HashSet<>()));

        Mockito.when(stockRepository.findAll()).thenReturn(stockList);

        List<Stock> retrievedStocks = stockService.retrieveAllStock();

        assertNotNull(retrievedStocks);
        assertEquals(2, retrievedStocks.size());
    }

    @Test
    void testUpdateStock() {
        Long stockId = 1L;
        Stock stock = new Stock();
        stock.setIdStock(stockId);
        stock.setTitle("Test Stock");
        stock.setProducts(new HashSet<>());

        Mockito.when(stockRepository.findById(stockId)).thenReturn(Optional.of(stock));
        Mockito.when(stockRepository.save(stock)).thenReturn(stock);

        Stock updatedStock = stockService.updateStock(stockId, "Updated Stock");

        assertNotNull(updatedStock);
        assertEquals(stockId, updatedStock.getIdStock());
        assertEquals("Updated Stock", updatedStock.getTitle());
    }


}
