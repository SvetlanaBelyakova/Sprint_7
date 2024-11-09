import courier.Courier;
import courier.CourierClient;
import courier.CourierCredentials;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class LoginCourierTests {

    private CourierClient courierClient;
    private int courierId;



    @Before

    public void setUp() {
        courierClient = new CourierClient();
    }

    @After
    public void tearDown() {
        courierClient.delete(courierId);
    }


    @Test
    @Step("Авторизация курьера")
    @DisplayName("Авторизация курьера. Позитивный сценарий")
    @Description("Авторизация курьера с валидными данными")
    public void courierCanBeLoginWithValidData() {

        Courier courier = Courier.getRandom();

        courierClient.create(courier);
        courierId = courierClient.login(CourierCredentials.from(courier)).extract().path("id");

        assertThat("Courier ID is incorrect", courierId, is(not(0)));


    }
}