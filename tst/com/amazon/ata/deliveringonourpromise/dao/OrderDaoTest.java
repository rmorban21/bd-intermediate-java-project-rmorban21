package com.amazon.ata.deliveringonourpromise.dao;

import com.amazon.ata.deliveringonourpromise.ordermanipulationauthority.OrderManipulationAuthorityClient;
import com.amazon.ata.deliveringonourpromise.types.Order;
import com.amazon.ata.ordermanipulationauthority.OrderCondition;
import com.amazon.ata.ordermanipulationauthority.OrderResult;
import com.amazon.ata.ordermanipulationauthority.OrderResultItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class OrderDaoTest {

    private OrderDao dao;
    private OrderManipulationAuthorityClient omaClientMock;

    @BeforeEach
    public void setup() {
        omaClientMock = Mockito.mock(OrderManipulationAuthorityClient.class);
        dao = new OrderDao(omaClientMock);
    }

    @Test
    public void get_forKnownOrderId_returnsOrder() {
        // GIVEN
        String knownOrderId = "900-3746401-0000001";

        // Create a simple OrderResult object with the known order ID
        OrderResult mockOrderResult = OrderResult.builder()
                .withOrderId(knownOrderId)
                .withCondition(OrderCondition.AUTHORIZED)
                .withCustomerOrderItemList(Collections.singletonList(OrderResultItem.builder().build())) // Ensure customerOrderItemList is not null
                .withMarketplaceId("1")
                .withOrderDate(ZonedDateTime.now().minusDays(1))
                .withShipOption("second")
                .build();

        when(omaClientMock.getCustomerOrderByOrderId(knownOrderId)).thenReturn(mockOrderResult);

        // WHEN
        Order result = dao.get(knownOrderId);

        // THEN
        assertNotNull(result, "Expected a non-null result");
        assertEquals(knownOrderId, result.getOrderId(), "Expected Order ID to match");
    }

    @Test
    public void get_forKnownOrderId_returnsNullWhenOmaClientReturnsNull() {
        // GIVEN
        String knownOrderId = "900-3746401-0000001";

        // Simulate the OmaClient returning null for a known order ID
        when(omaClientMock.getCustomerOrderByOrderId(knownOrderId)).thenReturn(null);

        // WHEN
        Order result = dao.get(knownOrderId);

        // THEN
        assertNull(result, "Expected null result when OmaClient returns null");
    }



    @Test
    public void get_forInvalidOrderIdFormat_returnsErrorMessage() {
        String invalidOrderId = "!@#$%^";

        when(omaClientMock.getCustomerOrderByOrderId(invalidOrderId)).thenReturn(null);
        Order result = dao.get(invalidOrderId);

        assertNull(result, "Expected error message when User inputs invalid Order ID format");

    }

    @Test
    public void get_forValidButNonExistentOrderId_returnsErrorMessage() {
        // GIVEN
        String nonExistentOrderId = "900-0000000-0000000";

        // WHEN
        when(omaClientMock.getCustomerOrderByOrderId(nonExistentOrderId)).thenReturn(null);
        Order result = dao.get(nonExistentOrderId);

        // THEN
        assertNull(result, "Expected error message when User inputs a valid but non-existent Order ID");
    }


}
