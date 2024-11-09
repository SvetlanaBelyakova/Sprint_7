package orders;
import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class Order {

    public final String firstName;
    public final String lastName;
    public final String address;
    public final String metroStation;
    public final String phone;
    public final int rentTime;
    public final String deliveryDate;
    public final String comment;
    public String[] color;

    public Order(String firstName,
                 String lastName,
                 String address,
                 String metroStation,
                 String phone,
                 int rentTime,
                 String deliveryDate,
                 String comment,
                 String[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    public static Order Randomize() {
        Random random = new Random();

        // Генерация случайных данных
        final String firstName = "Имя" + random.nextInt(10); // Пример генерации имени
        final String lastName = "Фамилия" + random.nextInt(10); // Пример генерации фамилии
        final String address = "Улица " + random.nextInt(10) + ", дом " + random.nextInt(10); // Пример адреса
        final String metroStation = RandomStringUtils.random(1, "123456789");
        final String phone = "+7923" + RandomStringUtils.random(6, "123456789");
        final int rentTime = random.nextInt(10) + 1; // Генерация времени аренды от 1 до 10
        final String deliveryDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        final String comment = "Комментарий к заказу от " + deliveryDate;
        String[] color = {""}; // Пример массива цветов

        return new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
    }

    public void setColor(String[] strings) {
        this.color = strings;
    }
}