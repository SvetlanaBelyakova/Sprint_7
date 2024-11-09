import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import orders.Order;
import orders.OrdersClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;



@RunWith(Parameterized.class)
public class CreateOrderTest {

    private OrdersClient orderClient;
    private Order order;
    private final String[] colors = new String[2];
    private ValidatableResponse response;
    private int orderTrack;
    private int orderId;

    @Before
    public void setUp() {
        orderClient = new OrdersClient();
        order = Order.Randomize();
    }

    @After
    public void tearDown(){
        orderId = orderClient.getTrack(orderTrack).extract().path("order.id");
        orderClient.deleteTrack(orderId);
    }

    @Parameterized.Parameters(name = "{index}: цвет самоката: {0} и {1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                        {"", ""},
                        {"BLACK", ""},
                        {"", "GREY"},
                        {"BLACK", "GREY"}
                }
        );
    }

    public CreateOrderTest(String color_1, String color_2) {
        colors[0] = color_1;
        colors[1] = color_2;
    }


    @Test
    @Step("Создание заказа")
    @DisplayName("Создание заказа. Позитивный сценарий")
    @Description("Создать заказ передав валидные данные")

    public void NewOrderTest() {

        order.setColor(colors);

        response = orderClient.create(order);
        orderTrack = response.assertThat().extract().path("track");

        response.assertThat().statusCode(201);
        assertThat("Track is incorrect", orderTrack, is(not(0)));
        orderId = response.extract().path("track");
    }
}