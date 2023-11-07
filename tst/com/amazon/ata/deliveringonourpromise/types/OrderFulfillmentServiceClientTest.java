package com.amazon.ata.deliveringonourpromise.types;

import com.amazon.ata.deliveringonourpromise.App;
import com.amazon.ata.deliveringonourpromise.data.OrderDatastore;
import com.amazon.ata.deliveringonourpromise.orderfulfillmentservice.OrderFulfillmentServiceClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderFulfillmentServiceClientTest {

    private OrderFulfillmentServiceClient client;
    private String orderItemId;

    @BeforeEach
    private void setup() {
        client = App.getOrderFulfillmentServiceClient();
        String orderId = "111-7497023-2960776";
        orderItemId = OrderDatastore.getDatastore().getOrderData(orderId).getCustomerOrderItemList()
                .get(0).getCustomerOrderItemId();
    }

    @Test
    public void getOrderFulfillmentServiceClient_nullOrderItemId_returnsNull() {
        orderItemId = null;

        Promise promise = client.getOrderPromiseByOrderItemId(orderItemId);

        assertNull(promise);
    }

    @Test
    public void getOrderFulfillmentServiceClient_nonexistentOrderItemId_returnsNull() {
        orderItemId = "20";

        Promise promise = client.getOrderPromiseByOrderItemId(orderItemId);

        assertNull(promise);
    }

    @Test
    public void getOrderFulfillmentServiceClient_validItemId_hasCorrectOrderItemId() {
        Promise promise = client.getOrderPromiseByOrderItemId(orderItemId);
        assertEquals(orderItemId, promise.getCustomerOrderItemId());
    }

    @Test
    public void getOrderFulfillmentServiceClient_validItemId_setsLatestArrivalDate() {
        Promise promise = client.getOrderPromiseByOrderItemId(orderItemId);
        assertNotNull(promise.getPromiseLatestArrivalDate());
    }

    @Test
    public void getOrderFulfillmentServiceClient_validItemId_setsPromiseLatestShipDate() {
        Promise promise = client.getOrderPromiseByOrderItemId(orderItemId);
        assertNotNull(promise.getPromiseLatestShipDate());
    }

    @Test
    public void getOrderFulfillmentServiceClient_validItemId_setsEffectiveDate() {
        Promise promise = client.getOrderPromiseByOrderItemId(orderItemId);
        assertNotNull(promise.getPromiseEffectiveDate());
    }

    @Test
    public void getOrderFulfillmentServiceClient_validItemId_setsPromiseProvidedBy() {
        Promise promise = client.getOrderPromiseByOrderItemId(orderItemId);
        assertEquals(orderItemId, promise.getPromiseProvidedBy());
    }

    @Test
    public void getOrderFulfillmentServiceClient_validItemId_setsAsin() {
        Promise promise = client.getOrderPromiseByOrderItemId(orderItemId);
        assertEquals(orderItemId, promise.getAsin());
    }



}
