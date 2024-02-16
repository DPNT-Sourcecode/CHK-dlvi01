package befaster.solutions.CHK;

import befaster.solutions.HLO.HelloSolution;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CheckoutSolutionTest {

    private CheckoutSolution checkout;

    @BeforeEach
    public void setUp() {
        checkout = new CheckoutSolution();
    }

    @Test
    public void test_checkout_with_one_item_A() {
        String items = "A";
        int expectedSum = 50;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_one_item_B() {
        String items = "B";
        int expectedSum = 30;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_one_item_C() {
        String items = "C";
        int expectedSum = 20;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_one_item_D() {
        String items = "D";
        int expectedSum = 15;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_multiple_items() {
        String items = "A B";
        int expectedSum = 80;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_special_offers() {
        String items = "A A A";
        int expectedSum = 130;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_special_offers_and_multiple_items() {
        String items = "A A A C D";
        int expectedSum = 165;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_no_items() {
        String items = "";
        int expectedSum = 0;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_empty_string() {
        String items = null;
        int expectedSum = 0;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_non_alphabetic_string() {
        String items = "-";
        int expectedSum = -1;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_invalid_string_input() {
        String items = "ABCa";
        int expectedSum = -1;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

}


