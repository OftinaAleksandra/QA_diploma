package data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Data
@AllArgsConstructor
public class Cards {
    public String number;
    public String month;
    public String year;
    public String owner;
    public String cvcCvv;

    public static String approvedCardNumber() {
        return "4444 4444 4444 4441";
    }

    public static String declinedCardNumber() {
        return "4444 4444 4444 4442";
    }

    public static Faker faker = new Faker();
    public static Random random = new Random();


    private static String owner() {
        return faker.name().fullName();
    }

    public static String getMonthNumber() {
        List<String> monthsList = Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
        int index = random.nextInt(monthsList.size());
        return monthsList.get(index);
    }

    public static String getYear(){
        int year = LocalDate.now().plusYears(random.nextInt(5) + 1).getYear();
        return String.valueOf(year).substring(2);
    }

    public static String getCvcCvv(){
        return String.valueOf(faker.number().numberBetween(100,999));
    }

    public static Cards getApprovedCards () {
        return new Cards (approvedCardNumber(),
                getMonthNumber(),
                getYear(),
                owner(),
                getCvcCvv());
    }

    public static Cards getDeclinedCards () {
        return new Cards (declinedCardNumber(),
                getMonthNumber(),
                getYear(),
                owner(),
                getCvcCvv());
    }
}

