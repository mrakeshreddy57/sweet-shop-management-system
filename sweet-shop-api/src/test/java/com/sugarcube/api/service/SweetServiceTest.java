package com.sugarcube.api.service;

import com.sugarcube.api.model.Sweet;
import com.sugarcube.api.repository.SweetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SweetServiceTest {

    @Mock
    private SweetRepository sweetRepository; // We mock the database so we don't need a real MongoDB running for tests.

    @InjectMocks
    private SweetService sweetService; // This is the class we are about to test.

    // TEST CASE 1: Successful Purchase
    @Test
    void purchaseSweet_ShouldDecreaseQuantity_WhenStockAvailable() {
        // 1. ARRANGE (Setup data)
        String sweetId = "1";
        // Create a fake sweet with 10 items in stock
        Sweet sweet = new Sweet("Chocolate", "Candy", 5.0, 10);
        sweet.setId(sweetId);

        // When the app asks the DB for ID "1", return our fake sweet
        when(sweetRepository.findById(sweetId)).thenReturn(Optional.of(sweet));
        // When the app saves to DB, just return the sweet object back
        when(sweetRepository.save(any(Sweet.class))).thenAnswer(i -> i.getArguments()[0]);

        // 2. ACT (Run the method)
        // NOTE: IntelliJ will show 'purchaseSweet' in RED because we haven't created it yet!
        Sweet updatedSweet = sweetService.purchaseSweet(sweetId);

        // 3. ASSERT (Verify results)
        // Quantity should drop from 10 to 9
        assertEquals(9, updatedSweet.getQuantity());
        // Verify the database 'save' method was actually called
        verify(sweetRepository, times(1)).save(sweet);
    }

    // TEST CASE 2: Out of Stock
    @Test
    void purchaseSweet_ShouldThrowException_WhenOutOfStock() {
        // 1. ARRANGE
        String sweetId = "2";
        // Create a fake sweet with 0 items
        Sweet sweet = new Sweet("Gummy Bear", "Candy", 2.0, 0);
        sweet.setId(sweetId);
        when(sweetRepository.findById(sweetId)).thenReturn(Optional.of(sweet));

        // 2. ACT & ASSERT
        // Verify that it explodes (throws exception) if we try to buy it
        assertThrows(RuntimeException.class, () -> sweetService.purchaseSweet(sweetId));
    }
}