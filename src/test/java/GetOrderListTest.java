import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.*;
import orders.Order;
import orders.OrdersClient;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.core.IsNot.not;

public class GetOrderListTest {
    private OrdersClient orderClient;
    private ValidatableResponse response;
    private int limit;

    @Before
    public void setUp() {
        orderClient = new OrdersClient();
    }

    @Test
    @Step("Получение списка заказов")
    @DisplayName("Создание и получение заказа. Позитивный сценарий")
    @Description("Создать заказ и получить список заказов")

    public void getOrdersList() {

        Order order = Order.Randomize();

        orderClient.create(order);
        response = orderClient.getList(limit);

        response.assertThat().statusCode(200);
        response.assertThat().body("data.orders", not(emptyArray()));
    }
}