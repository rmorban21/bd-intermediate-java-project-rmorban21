package com.amazon.ata.deliveringonourpromise;

import com.amazon.ata.deliveringonourpromise.types.Promise;

public interface PromiseServiceClient {
    Promise getPromiseByOrderItemId(String customerOrderItemId);

}
