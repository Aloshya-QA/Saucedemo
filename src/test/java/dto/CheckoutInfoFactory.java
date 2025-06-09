package dto;

import com.github.javafaker.Faker;

public class CheckoutInfoFactory {

    public static CheckoutInfo checkoutInfo() {
        Faker faker = new Faker();
        return new CheckoutInfo(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.address().zipCode()
        );
    }
}