package ultilities;

import com.github.javafaker.Faker;

public class DataGenerator {

    public static String randomDescription() {
        Faker faker = new Faker();
        String data = faker.shakespeare().kingRichardIIIQuote();
        return data;
    }
}
