import courier.Courier;
import courier.CourierClient;
import courier.CourierCredentials;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginCourierNegativeTests {

    private CourierClient courierClient;

    @Before

    public void setUp() {
        courierClient = new CourierClient();
    }

    //@After//
   //public void tearDown() {
       // courierClient.delete(courierId);
    //}//

    @Test
    @Step("Авторизация курьера")
    @DisplayName("Авторизация курьера с невалидными данными. Негативный сценарий")
    @Description("Авторизовать курьера с невалидными данными")
    public void courierCanBeLoginWithInvalidData() {
        Courier courier = new Courier("login2", "password2", "hn2");

        ValidatableResponse  statusCodeNegativeResponse = courierClient.login(CourierCredentials.from(courier));

        assertEquals("Статус не 404 conflict!",404, statusCodeNegativeResponse.extract().statusCode());

    }

    @Test
    @Step("Авторизация курьера")
    @DisplayName("Авторизация курьера без логина. Негативный сценарий")
    @Description("Авторизовать курьера без логина")
    public void courierCanBeLoginWithNullLogin() {

        Courier courier = new Courier(null, "password2", "hn2");
        ValidatableResponse  statusCodeNegativeResponse = courierClient.login(CourierCredentials.from(courier));
        assertEquals("Статус не 400 conflict!",400, statusCodeNegativeResponse.extract().statusCode());

    }

    //@Test
    // @Step("Авторизация курьера")
    // @DisplayName("Авторизация курьера без пароля. Негативный сценарий")
    // @Description("Авторизовать курьера без пароля")
    // public void courierCanBeLoginWithNullPassword()
    // {
    //Courier courier = new Courier("login", null, "hn2");

    //ValidatableResponse  statusCodeNegativeResponse = courierClient.login(CourierCredentials.from(courier));

    //assertEquals("Статус не 400 conflict!",400, statusCodeNegativeResponse.extract().statusCode());

    //}//

}