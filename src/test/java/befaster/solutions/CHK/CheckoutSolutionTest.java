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
    public void test_checkout_with_one_item_E() {
        String items = "E";
        int expectedSum = 40;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_one_item_F() {
        String items = "F";
        int expectedSum = 10;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_one_F_free_as_special_offer() {
        String items = "FFF";
        int expectedSum = 20;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_two_F_no_special_offer() {
        String items = "FF";
        int expectedSum = 20;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_four_F_and_special_offer() {
        String items = "FFFF";
        int expectedSum = 30;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_five_F_and_special_offer() {
        String items = "FFFFF";
        int expectedSum = 40;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_six_F_and_special_offer() {
        String items = "FFFFFF";
        int expectedSum = 40;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_multiple_items() {
        String items = "AB";
        int expectedSum = 80;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_3_A_special_offers() {
        String items = "AAA";
        int expectedSum = 130;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_5_A_special_offers() {
        String items = "AAAAA";
        int expectedSum = 200;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_6_A_special_offers() {
        String items = "AAAAAA";
        int expectedSum = 250;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_8_A_special_offers() {
        String items = "AAAAAAAA";
        int expectedSum = 330;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_2_E_special_offers_and_B_comes_for_free() {
        String items = "EEB";
        int expectedSum = 80;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_2_E_special_offers_and_no_B_bought() {
        String items = "EE";
        int expectedSum = 80;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_2_E_special_offers_and_B_comes_for_free_but_extra_B_has_a_cost() {
        String items = "EEBB";
        int expectedSum = 110;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_special_offers_and_multiple_items() {
        String items = "AAACD";
        int expectedSum = 165;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_large_checkout_with_multiple_special_offers_and_multiple_items() {
        String items = "ABCDCBAABCABBAAA";
        int expectedSum = 495;

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

