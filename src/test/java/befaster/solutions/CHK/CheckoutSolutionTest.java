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
    public void test_checkout_with_one_item() {
        String items = "A";
        int expectedSum = 50;

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
}
