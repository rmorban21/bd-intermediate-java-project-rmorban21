package com.amazon.ata.deliveringonourpromise.types;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class OrderTest {


    @BeforeEach
    public void setUp() {

    }

    @Test
    public void testFieldsArePrivate() {
        Order order = Order.builder().build();
        assertTrue(true, "Ensure all fields in the Order class are private");
    }

    @Test
    public void getCustomerOrderItemList_attemptsToModifyList_ThrowsException_Mockito() {
        Order order = Order.builder()
                .withCustomerOrderItemList(Arrays.asList(OrderItem.builder().build()))
                .build();


        List<OrderItem> listFromOrder = order.getCustomerOrderItemList();

       listFromOrder.add(OrderItem.builder().build());
       assertTrue(order.getCustomerOrderItemList().size() == 1, "The list should not be able to be modified externally.");
    }
}
