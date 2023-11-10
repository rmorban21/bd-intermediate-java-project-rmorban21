package com.amazon.ata.deliveringonourpromise;

import com.amazon.ata.deliveringonourpromise.types.Promise;

public interface PromiseServiceClient {
    /**
     * Contract getter method for classes that implement the interface.
     * @param customerOrderItemId - Takes in String retrieved from Promise.
     * @return - no return needed within Interface.
     */
    Promise getPromiseByOrderItemId(String customerOrderItemId);

}
