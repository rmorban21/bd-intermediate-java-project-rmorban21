package com.amazon.ata.deliveringonourpromise.activity;

import com.amazon.ata.deliveringonourpromise.App;
import com.amazon.ata.deliveringonourpromise.dao.ReadOnlyDao;
import com.amazon.ata.deliveringonourpromise.types.Order;
import com.amazon.ata.deliveringonourpromise.types.Promise;
import com.amazon.ata.deliveringonourpromise.types.PromiseHistory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests GetPromiseHistoryByOrderIdActivity.
 */
public class GetPromiseHistoryByOrderIdActivityTest {
    private GetPromiseHistoryByOrderIdActivity activity;
    private ReadOnlyDao<String, Order> orderDao;
    private ReadOnlyDao<String, List<Promise>> promiseDao;

    // participants: @BeforeEach means this method is run before each test method is executed, often setting up data +
    // an instance of the class under test.
    @BeforeEach
    private void createActivity() {
        orderDao = App.getOrderDao();
        promiseDao = App.getPromiseDao();
        activity = new GetPromiseHistoryByOrderIdActivity(orderDao, promiseDao);
    }

    @Test
    public void getPromiseHistoryByOrderId_nullOrderId_isRejected() {
        // GIVEN
        String orderId = null;

        // WHEN + THEN
        // (participants: we'll learn what this is doing later in the course)
        assertThrows(IllegalArgumentException.class, () -> activity.getPromiseHistoryByOrderId(orderId));
    }


    @Test
    public void getPromiseHistoryByOrderId_orderWithKnownAsin_returnsPromisesSortedByASIN() {
        // GIVEN
        String orderId = "900-3746403-0000002";

        // WHEN
        PromiseHistory promiseHistory = activity.getPromiseHistoryByOrderId(orderId);

        // THEN
        assertNotNull(promiseHistory);
        Order order = promiseHistory.getOrder();
        assertNotNull(order);
        List<Promise> promises = promiseHistory.getPromises();
        assertNotNull(promises);

        // Check if promises are sorted by ASIN in ascending order
        for (int i = 0; i < promises.size() - 1; i++) {
            String currentAsin = promises.get(i).getAsin();
            String nextAsin = promises.get(i + 1).getAsin();
            assertTrue(currentAsin.compareTo(nextAsin) <= 0, "Promises are not sorted by ASIN");
        }

    }


    @Test
    public void getPromiseHistoryByOrderId_orderWithDpsPromise_returnsDpsPromise() {
        // GIVEN - an order that hasn't shipped yet but should return a DPS promise
        String orderId = "900-3746401-0000001";

        // WHEN
        PromiseHistory history = activity.getPromiseHistoryByOrderId(orderId);

        // THEN
        boolean foundDpsPromise = false;
        for (Promise promise : history.getPromises()) {
            if (promise.getPromiseProvidedBy().equals("DPS")) {
                foundDpsPromise = true;
            }
        }
        assertTrue(foundDpsPromise,
                String.format("Expected to find a DPS promise for order ID '%s', but promises were: %s",
                        orderId,
                        history.getPromises().toString()
                )
        );
    }

    @Test
    public void promisesSortedByAsin() {
        // GIVEN
        String orderId = "900-3746403-0000002";

        // WHEN
        PromiseHistory promiseHistory = activity.getPromiseHistoryByOrderId(orderId);

        // THEN
        assertNotNull(promiseHistory);

        List<Promise> promises = promiseHistory.getPromises();
        assertNotNull(promises);

        System.out.println("Before sorting: " + promises);

        // Check if promises are sorted by ASIN in ascending order
        for (int i = 0; i < promises.size() - 1; i++) {
            String currentAsin = promises.get(i).getAsin();
            String nextAsin = promises.get(i + 1).getAsin();
            assertTrue(currentAsin.compareTo(nextAsin) <= 0, "Promises are not sorted by ASIN");
        }

        // Print promises after sorting
        System.out.println("After sorting: " + promises);
    }
}

