package tools;

import com.ninja_squad.dbsetup.operation.Operation;
import static com.ninja_squad.dbsetup.Operations.*;



public class SetupDb {

    public static final Operation DELETE_ALL =
            deleteAllFrom("OrderItems","Products","Orders");


    public static final Operation INSERT_DATA =
            sequenceOf(

                    insertInto("Orders")
                            .columns("ID", "DeliveryDate", "IsGift", "MaxDiscount", "OrderDate", "OrderName", "OrderNumber", "PreferableDeliveryDate", "TotalPrice", "Assigne", "Customer", "OrderStatusRef")
                            .values(1, "2015-06-25 21:05:28", "", 0, "2015-05-25 21:05:28", "OrderName1", 1, "2015-05-26 00:00:00 ", 1, 8, 10, 1)
                            .values(2, "2015-06-25 21:05:28", "", 0, "2015-05-25 21:05:28", "OrderName2", 2, "2015-05-26 00:00:00 ", 1, 8, 10, 1)
                            .values(3, "2015-06-25 21:05:28", "", 0, "2015-05-25 21:05:28", "OrderName1", 3, "2015-05-26 00:00:00 ", 1, 8, 10, 1)
                            .values(4, "2015-06-25 21:05:28", "", 0, "2015-05-25 21:05:28", "OrderName2", 4, "2015-05-26 00:00:00 ", 1, 8, 10, 1)
                            .build(),

                    insertInto("Products")
                            .columns("ID", "IsProductActive", "ProductDescription", "ProductName", "ProductPrice")
                            .values(1, 1, "some description", "testProduct1", 100)
                            .values(2, 1, "some description", "testProduct2", 100)
                            .values(3, 1, "some description", "testProduct3", 100)
                            .values(4, 1, "some description", "testProduct4", 100)
                            .build(),

                    insertInto("OrderItems")
                            .columns("ID", "Cost", "ItemPrice", "Quantity", "DimensionRef", "OrderRef", "ProductRef")
                            .values(1, 100, 100, 1, 1, 1, 1)
                            .values(2, 100, 100, 1, 1, 2, 2)
                            .values(3, 100, 100, 1, 1, 3, 3)
                            .values(4, 100, 100, 1, 1, 4, 4)
                            .build());
}