## `OrderDao` test plan

This is a sample test plan template. Amazon teams may have different test plan
expectations, different file formats, or even ignore test plans altogether.

Writing a test plan helps manage expectations and provides a goal before you
begin writing the actual test code.

Remember from the unit test lesson that ATA expects test names to follow the
pattern "methodName_testCase_expectedBehavior".

Every test plan has:

1. A name
    * A description
2. Given  
   The pre-conditions required for this test to work.
   ATA expects this to be an unordered list. The items should be
   related to the "testCase" portion of the test name.
3. When  
   The actions to take to achieve the desired result.
   ATA expects this to be an ordered list, because the actions to take are
   generally required to be taken in a particular order.
   The items should include the "methodName" from the test name.
4. Then  
   Testable results.
   ATA expects this to be an unordered list. The items should be
   related to the "expectedBehavior" portion of the test name.

We've filled out a happy path test for the `OrderDao#get` method.
Copy and modify it to complete your test plan.

### get_forKnownOrderId_returnsOrder

Happy case, verifying that the OrderDao can return an order.

#### Given

* An order ID that we know exists

#### When

1. We call `get()` with that order ID

### Then

* The result is not null

### get_forKnownOrderId_returnsNullWhenOmaClientReturnsNull

Test case, verifying that the OrderDao returns null when omaClient returns null

### GIVEN

* An order ID that we know exists

### WHEN

1. Simulate OMAClient returning null for the known orderID

### THEN

* OrderDao returns null

### get_forInvalidOrderIdFormat_returnsErrorMessage

Test case, verifying that the OrderDao returns null for an invalid order ID format.

### GIVEN

* An order ID with an invalid format

### WHEN

1. We call get() with that order ID

### THEN

* The result is an error message

### get_forValidButNonExistentOrderId_returnsErrorMessage

Test case, verifying that the OrderDao returns Error Message given validID but no OrderData

### GIVEN

* A valid order ID format

### WHEN

1. We call get() with that order ID

### THEN

* OrderDao returns null and the error message is displayed to the user.
