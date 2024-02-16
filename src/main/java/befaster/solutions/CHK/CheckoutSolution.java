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

        // If the skus string contains non-alphabetic characters and lowercase characters, then return -1.
        if (hasNonAlphabeticAndLowercaseChars(skus)) {
            return -1;
        }

        // Store SKUs in a HashMap, so we can identify if there are multiples of 3As, or 2Bs, for example.
        Map<Character, Integer> skuCounts = getSkuCounts(skus);

        // Calculate the checkout sum.
        for (Map.Entry<Character, Integer> entry : skuCounts.entrySet()) {
            Character sku = entry.getKey();
            int count = entry.getValue();

            if (sku.equals('A')) {
                int specialOfferUnitRequirement = 3;
                int specialOfferPrice = 130;

                if (isSpecialOfferApplicable(count, specialOfferUnitRequirement)) {
                    checkoutSum += handleSpecialOffer(unitPrices, sku, count,  specialOfferUnitRequirement, specialOfferPrice);
                } else {
                    if (unitPrices.containsKey(sku)) {
                        checkoutSum += count * unitPrices.get(sku);
                    }
                }
            } else if (sku.equals('B')) {
                int specialOfferUnitRequirement = 2;
                int specialOfferPrice = 45;

                if (isSpecialOfferApplicable(count, specialOfferUnitRequirement)) {
                    checkoutSum += handleSpecialOffer(unitPrices, sku, count,  specialOfferUnitRequirement, specialOfferPrice);
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

    private boolean hasNonAlphabeticAndLowercaseChars(String skus) {
        for (char c: skus.toCharArray()) {
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

    private static boolean isSpecialOfferApplicable(int count, int specialOfferUnitRequirement) {
        return count / specialOfferUnitRequirement > 0;
    }

    private static int handleSpecialOffer(Map<Character, Integer> unitPrices, Character sku, int count, int specialOfferUnitRequirement, int specialOfferPrice) {
        int sum = 0;

        int specialOfferCount = count / specialOfferUnitRequirement;

        // Handle special offers.
        if (specialOfferCount > 0) {
            sum += specialOfferCount * specialOfferPrice;
        }

        // Handle if there are any extra items that are not covered
        // by the special offer, say a 4th A item.
        if (((double) count / specialOfferUnitRequirement - specialOfferCount) > 0) {
            count = count - specialOfferUnitRequirement;
            sum += count * unitPrices.get(sku);
        }

        return sum;
    }
}





