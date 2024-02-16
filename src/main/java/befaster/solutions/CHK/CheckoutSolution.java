package befaster.solutions.CHK;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        // This variable represents the total checkout value of the items.
        int checkoutSum = 0;

        // This map stores individual unit prices (no special offers).
        Map<String, Integer> unitPrices = new HashMap<>();
        unitPrices.put("A", 50);
        unitPrices.put("B", 30);
        unitPrices.put("C", 20);
        unitPrices.put("D", 15);

        // Store SKUs in a HashMap, so we can identify if there are multiples of 3As, or 2Bs, for example.
        Map<String, Integer> skuCounts = getSkuCounts(skus);

        // Calculate the checkout sum.
        for (Map.Entry<String, Integer> entry : skuCounts.entrySet()) {
            String sku = entry.getKey();
            int count = entry.getValue();

            if (sku.equals("A")) {
                int specialOfferUnitRequirement = 3;
                int specialOfferPrice = 130;

                if (isSpecialOfferApplicable(count, specialOfferUnitRequirement)) {
                    checkoutSum += handleSpecialOffer(unitPrices, sku, count,  specialOfferUnitRequirement, specialOfferPrice);
                } else {
                    checkoutSum += count * unitPrices.get(sku);
                }
            } else if (sku.equals("B")) {
                int specialOfferUnitRequirement = 2;
                int specialOfferPrice = 45;

                if (isSpecialOfferApplicable(count, specialOfferUnitRequirement)) {
                    checkoutSum += handleSpecialOffer(unitPrices, sku, count,  specialOfferUnitRequirement, specialOfferPrice);
                } else {
                    checkoutSum += count * unitPrices.get(sku);
                }
            }
            // Other SKUs have no special offer, so no need to check if a special offer is applicable.
            else {
                checkoutSum += count * unitPrices.get(sku);
            }
        }

        return checkoutSum;
    }

    private static Map<String, Integer> getSkuCounts(String skus) {
        Map<String, Integer> skuCounts = new HashMap<>();

        // Split the string around whitespaces.
        // Assuming SKUs are separated by whitespace.
        String[] skuArray = skus.split(" ");

        // Store all individual SKUs and their counts.
        for (String sku : skuArray) {
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

    private static int handleSpecialOffer(Map<String, Integer> unitPrices, String sku, int count, int specialOfferUnitRequirement, int specialOfferPrice) {
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
