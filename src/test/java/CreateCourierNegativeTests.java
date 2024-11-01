import courier.Courier;
import courier.CourierClient;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreateCourierNegativeTests {

    private CourierClient courierClient;
    //private int courierId;


    @Before

    public void setUp() {
        courierClient = new CourierClient();
    }


    @Test
    @Step("Создание курьера")
    @DisplayName("Создание курьера без логина. Негативный сценарий")
    @Description("Создать курьера без логина")

    public void courierWithoutLogin() {

        Courier courier = new Courier(null, "password11", "hna11");


        ValidatableResponse courierNotCreated = courierClient.create(courier);
        //courierId = courierClient.login(CourierCredentials.from(courier)).extract().path("id");
        //boolean isCourierDeleted = courierClient.delete(courierId);



        assertEquals("Статус не 400 conflict!",400, courierNotCreated.extract().statusCode());

    }
    @Test
    @Step("Создание курьера")
    @DisplayName("Создание курьера без пароля. Негативный сценарий")
    @Description("Создать курьера без пароля")

    public void courierWithoutPassword() {

        Courier courier = new Courier("login", null, "hna");

        ValidatableResponse courierNotCreated = courierClient.create(courier);

        assertEquals("Статус не 400 conflict!",400, courierNotCreated.extract().statusCode());

    }
    @Test
    @Step("Создание курьера")
    @DisplayName("Создание курьера без firstName. Негативный сценарий")
    @Description("Создать курьера без firstName")
    public void courierWithoutFirstName() {

        Courier courier = new Courier("login", "password", null);

        ValidatableResponse courierNotCreated = courierClient.create(courier);

        assertEquals("Статус не 409 conflict!",409, courierNotCreated.extract().statusCode());

    }
}