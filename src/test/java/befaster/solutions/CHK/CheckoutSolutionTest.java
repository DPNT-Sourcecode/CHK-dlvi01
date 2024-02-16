package befaster.solutions.CHK;

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
    public void test_checkout_with_1_A() {
        String items = "A";
        int expectedSum = 50;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_1_B() {
        String items = "B";
        int expectedSum = 30;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_1_C() {
        String items = "C";
        int expectedSum = 20;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_1_D() {
        String items = "D";
        int expectedSum = 15;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_1_E() {
        String items = "E";
        int expectedSum = 40;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_1_F() {
        String items = "F";
        int expectedSum = 10;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_1_G() {
        String items = "G";
        int expectedSum = 20;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_1_H() {
        String items = "H";
        int expectedSum = 10;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_1_I() {
        String items = "I";
        int expectedSum = 35;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_1_J() {
        String items = "J";
        int expectedSum = 60;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_1_K() {
        String items = "K";
        int expectedSum = 70;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_1_L() {
        String items = "L";
        int expectedSum = 90;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_1_M() {
        String items = "M";
        int expectedSum = 15;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_1_N() {
        String items = "N";
        int expectedSum = 40;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_1_O() {
        String items = "O";
        int expectedSum = 10;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_1_P() {
        String items = "P";
        int expectedSum = 50;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_1_Q() {
        String items = "Q";
        int expectedSum = 30;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_1_R() {
        String items = "R";
        int expectedSum = 50;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_1_S() {
        String items = "S";
        int expectedSum = 20;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_1_T() {
        String items = "T";
        int expectedSum = 20;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_1_U() {
        String items = "U";
        int expectedSum = 40;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_1_V() {
        String items = "V";
        int expectedSum = 50;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_1_W() {
        String items = "W";
        int expectedSum = 20;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_1_X() {
        String items = "X";
        int expectedSum = 17;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_1_Y() {
        String items = "Y";
        int expectedSum = 20;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_1_Z() {
        String items = "Z";
        int expectedSum = 21;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_three_F_free_and_1_F_free_special_offer() {
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
    public void test_checkout_with_3_A_special_offer() {
        String items = "AAA";
        int expectedSum = 130;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_5_A_special_offer() {
        String items = "AAAAA";
        int expectedSum = 200;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_6_A_special_offer() {
        String items = "AAAAAA";
        int expectedSum = 250;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_8_A_special_offer() {
        String items = "AAAAAAAA";
        int expectedSum = 330;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_2_E_special_offer_and_B_comes_for_free() {
        String items = "EEB";
        int expectedSum = 80;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_2_E_special_offer_and_no_B_bought() {
        String items = "EE";
        int expectedSum = 80;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_2_E_special_offer_and_B_comes_for_free_but_extra_B_has_a_cost() {
        String items = "EEBB";
        int expectedSum = 110;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_special_offer_and_multiple_items() {
        String items = "AAACD";
        int expectedSum = 165;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_large_checkout_with_multiple_special_offer_and_multiple_items() {
        String items = "ABCDCBAABCABBAAA";
        int expectedSum = 495;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_5_H_special_offer() {
        String items = "HHHHH";
        int expectedSum = 45;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_10_H_special_offer() {
        String items = "HHHHHHHHHH";
        int expectedSum = 80;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_2_K_special_offer() {
        String items = "KK";
        int expectedSum = 120;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_3_N_1_M_free_special_offer() {
        String items = "NNNM";
        int expectedSum = 120;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_5_P_special_offer() {
        String items = "PPPPP";
        int expectedSum = 200;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_3_Q_special_offer() {
        String items = "QQQ";
        int expectedSum = 80;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_3_R_1_Q_free_special_offer() {
        String items = "RRRQ";
        int expectedSum = 150;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_3_U_1_U_free_special_offer() {
        String items = "UUU";
        int expectedSum = 120;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_4_U_1_U_free_special_offer() {
        String items = "UUUU";
        int expectedSum = 120;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_2_V_special_offer() {
        String items = "VV";
        int expectedSum = 90;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_with_3_V_special_offer() {
        String items = "VVV";
        int expectedSum = 130;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_any_3_special_offer_first_variant() {
        String items = "STX";
        int expectedSum = 45;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_any_3_special_offer_second_variant() {
        String items = "XYZ";
        int expectedSum = 45;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_any_3_special_offer_third_variant() {
        String items = "ZZZ";
        int expectedSum = 45;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_any_3_special_offer_fourth_variant() {
        String items = "SSSZ";
        int expectedSum = 65;

        assertThat(checkout.checkout(items), equalTo(expectedSum));
    }

    @Test
    public void test_checkout_any_3_special_offer_multiple_offers() {
        String items = "XYZSTX";
        int expectedSum = 90;

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

