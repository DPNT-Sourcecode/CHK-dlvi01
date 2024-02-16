package befaster.solutions.CHK;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        if (skus == null || skus.trim().isEmpty()) {
            return 0;
        }

        // This variable represents the total checkout value of the items.
        int checkoutSum = 0;

        // This map stores individual unit prices (no special offers).
        Map<Character, Integer> unitPrices = new HashMap<>();
        unitPrices.put('A', 50);
        unitPrices.put('B', 30);
        unitPrices.put('C', 20);
        unitPrices.put('D', 15);
        unitPrices.put('E', 40);

        // If the skus string contains non-alphabetic characters and lowercase characters, then return -1.
        if (hasNonAlphabeticAndLowercaseChars(skus)) {
            return -1;
        }

        // Store SKUs in a HashMap, so we can identify if there are multiples of 3As, or 2Bs, for example.
        Map<Character, Integer> skuCounts = getSkuCounts(skus);

        // For every 2 pieces of E, one B is received for free.
        skuCounts = handleSpecialOffer1BFor2Es(skuCounts);

        // Calculate the checkout sum.
        for (Map.Entry<Character, Integer> entry : skuCounts.entrySet()) {
            Character sku = entry.getKey();
            int count = entry.getValue();

            if (sku.equals('A')) {
                int specialOffer3AUnitRequirement = 3;
                int specialOffer3APrice = 130;

                int specialOffer5AUnitRequirement = 5;
                int specialOffer5APrice = 200;

                if (isSpecialOfferApplicable(count, specialOffer3AUnitRequirement) || isSpecialOfferApplicable(count, specialOffer5AUnitRequirement)) {
                    checkoutSum += handleASpecialOffer(unitPrices, sku, count, specialOffer3AUnitRequirement, specialOffer3APrice, specialOffer5AUnitRequirement, specialOffer5APrice);
                } else {
                    if (unitPrices.containsKey(sku)) {
                        checkoutSum += count * unitPrices.get(sku);
                    }
                }
            } else if (sku.equals('B')) {
                int specialOfferUnitRequirement = 2;
                int specialOfferPrice = 45;

                if (isSpecialOfferApplicable(count, specialOfferUnitRequirement)) {
                    checkoutSum += handleBSpecialOffer(unitPrices, sku, count, specialOfferUnitRequirement, specialOfferPrice);
                } else {
                    if (unitPrices.containsKey(sku)) {
                        checkoutSum += count * unitPrices.get(sku);
                    }
                }
            }
            // Other SKUs have no special offer, so no need to check if a special offer is applicable.
            else {
                if (unitPrices.containsKey(sku)) {
                    checkoutSum += count * unitPrices.get(sku);
                }
            }
        }

        return checkoutSum;
    }

    private int handleASpecialOffer(Map<Character, Integer> unitPrices, Character sku, int count, int specialOffer3AUnitRequirement, int specialOffer3APrice, int specialOffer5AUnitRequirement, int specialOffer5APrice) {

        // Handle both special offers for A: 3A for 130, 5A for 200.

        int sum = 0;

        // If A's count is divisible by 5, first apply that special offer.
        // Then, if the remaining count is divisible by 3, then apply that special offer.
        // Then, if there are any remaining As, then those are priced individually.

        boolean isDivisibleBy5 = (count / specialOffer5AUnitRequirement) > 0;

        if (isDivisibleBy5) {
            int multiplesOf5A = count / specialOffer5AUnitRequirement;
            sum += multiplesOf5A * specialOffer5APrice;
            count = count - specialOffer5AUnitRequirement * multiplesOf5A;
        }

        boolean isDivisibleBy3 = (count / specialOffer3AUnitRequirement) > 0;

        if (isDivisibleBy3) {
            int multiplesOf3A = count / specialOffer3AUnitRequirement;
            sum += multiplesOf3A * specialOffer3APrice;
            count = count - specialOffer3AUnitRequirement * multiplesOf3A;
        }

        if (count > 0) {
            sum += count * unitPrices.get(sku);
        }

        return sum;
    }

    private boolean hasNonAlphabeticAndLowercaseChars(String skus) {
        for (char c : skus.toCharArray()) {
            if (!Character.isLetter(c) || Character.isLowerCase(c)) {
                return true;
            }
        }
        return false;
    }

    private static Map<Character, Integer> getSkuCounts(String skus) {
        Map<Character, Integer> skuCounts = new HashMap<>();

        // Store all individual SKUs and their counts.
        for (char sku : skus.toCharArray()) {
            if (!skuCounts.containsKey(sku)) {
                skuCounts.put(sku, 1);
            } else {
                int currentCount = skuCounts.get(sku);
                currentCount++;
                skuCounts.put(sku, currentCount);
            }
        }
        return skuCounts;
    }

    private Map<Character, Integer> handleSpecialOffer1BFor2Es(Map<Character, Integer> skuCounts) {
        // Handle the special offer where 2Es get 1 B for free.

        if (skuCounts.containsKey('E') && skuCounts.containsKey('B')) {
            int eCount = skuCounts.get('E');
            int bCount = skuCounts.get('B');

            // Calculate the number of free Bs.
            int numOfFreeBs = eCount / 2;

            // Deduct the number of free Bs from bCount, put it back into the skuCounts map.
            // The remaining Bs will cost the customer.
            bCount -= numOfFreeBs;
            skuCounts.put('B', bCount);
        }

        return skuCounts;
    }

    private static boolean isSpecialOfferApplicable(int count, int specialOfferUnitRequirement) {
        return count / specialOfferUnitRequirement > 0;
    }

    private static int handleBSpecialOffer(Map<Character, Integer> unitPrices, Character sku, int count, int specialOfferUnitRequirement, int specialOfferPrice) {
        int sum = 0;

        int specialOfferCount = count / specialOfferUnitRequirement;

        // Handle special offers.
        if (specialOfferCount > 0) {
            sum += specialOfferCount * specialOfferPrice;
        }

        // Handle if there are any extra items that are not covered
        // by the special offer, say a 4th A item.
        if (((double) count / specialOfferUnitRequirement - specialOfferCount) > 0) {
            count = count - specialOfferUnitRequirement * specialOfferCount;
            sum += count * unitPrices.get(sku);
        }

        return sum;
    }
}

