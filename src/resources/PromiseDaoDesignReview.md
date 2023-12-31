# PromiseDao Design Review

## Overview

What's the problem with the way the PromiseDao currently works?

- The PromiseDao only supports one source for promises: the Delivery Promise Service (DPS). We already need to get promises from another service, the Order Fulfillment Service (OFS), so this is a good time to make the design more flexible. 

## Use Cases

What ways will the CS representatives use the new multiple-client PromiseDao?

- Retrieving promises, validating promises or order Ids or updating promises with new information.

In a few sentences, how does the PromiseDao work right now?

- The PromiseDao currently retrieves a list of promises by accessing the Order Manipulation Authority (OMA) service for the delivery date and the DPS client for validating the customerOrderItemId.

Consider a developer unfamiliar with the Missed Promise CLI. Can you add diagrams here that will help them understand how the PromiseDao works right now?

- Retrieving promises from different sources. 
- Validating promises or order IDs. 
- Updating promises with new information from various clients.

## Proposed Solution

Describe in a few sentences how your changes will satisfy the use cases you listed above. How will you enable getting promises from OFS? How will you allow new promise sources to be added easily in the future?

- Introduce a list of promise service client objects in the PromiseDao class. These client objects should represent various promise sources, such as DeliveryPromiseServiceClient and OrderFulfillmentServiceClient (OFS). This list will allow you to interact with different clients in a flexible manner.

In this approach, each client-specific logic can be contained within their respective client classes. The PromiseDao will maintain a list of client objects, and when CS representatives need to retrieve, validate, or update promises, the PromiseDao will iterate through the list and perform the corresponding actions on each client.

This approach keeps the code modular and allows for adding new promise sources in the future without modifying the PromiseDao class directly.

## Out of Scope

Consider a reviewer who misunderstands this design and believes you're going to make the PromiseDao perfect. What are you not going to do? 

- As previously mentioned, it's essential to understand that while this approach makes the design more flexible, there will still be limitations. Not every possible source of promises may be supported, but the focus is on code extensibility.

## Details

Include a UML diagram that will help clarify the changes you want to make.
You can leave out classes that don't participate in the new solution, but you should leave in anything that uses your updates. For instance, even if you don't change the `GetPromiseHistoryByOrderIdActivity`, it's going to use the `PromiseDao` that you changed, so you should leave it in. Also make sure to include a link to the PlantUML source. Pro Tip: you can change a class's [color](http://plantuml.com/color) by adding “#colorname” after its name! (For example, #lightgrey visually indicates that although a class is involved, it's not a major discussion point right now).

In detail, what calls will the software make, and how will it process the results? You may use a single narrative, but it should satisfy all of the use cases you described above.

- 

What do you expect the complexity (BigO) of this solution to be, and why? Clearly define the variable(s) you're using in your BigO notation.

- 

## Potential Issues

What could go wrong with your solution? What would surprise a customer service rep who was trying to perform one of the use cases? If you can't think of anything, remove this section.

- 
