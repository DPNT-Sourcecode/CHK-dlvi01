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
        Map<Character, Integer> unitPrices = storeUnitPrices();

        // If the skus string contains non-alphabetic characters and lowercase characters, then return -1.
        if (hasNonAlphabeticAndLowercaseChars(skus)) {
            return -1;
        }

        // Store SKUs in a HashMap, so we can identify if there are multiples of 3As, or 2Bs, for example.
        Map<Character, Integer> skuCounts = getSkuCounts(skus);

        // For every 2 pieces of E, 1 B is received for free.
        skuCounts = handleSpecialOffer1FreeFor3SpecificItems(skuCounts, 'E', 'B', 2);

        // For every 3 pieces of N, 1 M is received for free.
        skuCounts = handleSpecialOffer1FreeFor3SpecificItems(skuCounts, 'N', 'M', 3);

        int fSpecialOfferUnitRequirement = 2;

        // Calculate the checkout sum.
        for (Map.Entry<Character, Integer> entry : skuCounts.entrySet()) {
            Character sku = entry.getKey();
            int count = entry.getValue();

            if (sku.equals('A')) {
                checkoutSum = handleAPriceCalculation(count, checkoutSum, unitPrices, sku);
            } else if (sku.equals('B')) {
                checkoutSum = handleBPriceCalculation(count, checkoutSum, unitPrices, sku);
            } else if (sku.equals('F') && (count / fSpecialOfferUnitRequirement > 0)) {
                checkoutSum += handleFSpecialOffer(count, unitPrices, sku, fSpecialOfferUnitRequirement);
            } else if (sku.equals('H')) {
                checkoutSum = handleHPriceCalculation(count, checkoutSum, unitPrices, sku);
            } else if (sku.equals('K')) {
                checkoutSum = handleKPriceCalculation(count, checkoutSum, unitPrices, sku);
            } else if (sku.equals('P')) {
                checkoutSum = handlePPriceCalculation(count, checkoutSum, unitPrices, sku);
            }
            // Handle remaining SKUs without special offers.
            else {
                if (unitPrices.containsKey(sku)) {
                    checkoutSum += count * unitPrices.get(sku);
                }
            }
        }

        return checkoutSum;
    }

    private static Map<Character, Integer> storeUnitPrices() {
        Map<Character, Integer> unitPrices = new HashMap<>();
        unitPrices.put('A', 50);
        unitPrices.put('B', 30);
        unitPrices.put('C', 20);
        unitPrices.put('D', 15);
        unitPrices.put('E', 40);
        unitPrices.put('F', 10);
        unitPrices.put('G', 20);
        unitPrices.put('H', 10);
        unitPrices.put('I', 35);
        unitPrices.put('J', 60);
        unitPrices.put('K', 80);
        unitPrices.put('L', 90);
        unitPrices.put('M', 15);
        unitPrices.put('N', 40);
        unitPrices.put('O', 10);
        unitPrices.put('P', 50);
        unitPrices.put('Q', 30);
        unitPrices.put('R', 50);
        unitPrices.put('S', 30);
        unitPrices.put('T', 20);
        unitPrices.put('U', 40);
        unitPrices.put('V', 50);
        unitPrices.put('W', 20);
        unitPrices.put('X', 90);
        unitPrices.put('Y', 10);
        unitPrices.put('Z', 50);
        return unitPrices;
    }

    private int handleAPriceCalculation(int count, int checkoutSum, Map<Character, Integer> unitPrices, Character sku) {
        // Handles price calculation for A items, including special offer calculation.

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

    private static int handleBPriceCalculation(int count, int checkoutSum, Map<Character, Integer> unitPrices, Character sku) {
        // Handles price calculation for B items, including special offer calculation.

        int specialOfferUnitRequirement = 2;
        int specialOfferPrice = 45;

        if (isSpecialOfferApplicable(count, specialOfferUnitRequirement)) {
            checkoutSum += handle2ForSpecialOffer(unitPrices, sku, count, specialOfferUnitRequirement, specialOfferPrice);
        } else {
            if (unitPrices.containsKey(sku)) {
                checkoutSum += count * unitPrices.get(sku);
            }
        }
        return checkoutSum;
    }

    private int handleHPriceCalculation(int count, int checkoutSum, Map<Character, Integer> unitPrices, Character sku) {
        // Handles price calculation for H items, including special offer calculation.

        int specialOffer5HUnitRequirement = 5;
        int specialOffer5HPrice = 45;

        int specialOffer10HUnitRequirement = 10;
        int specialOffer10HPrice = 80;

        if (isSpecialOfferApplicable(count, specialOffer5HUnitRequirement) || isSpecialOfferApplicable(count, specialOffer10HUnitRequirement)) {
            checkoutSum += handleASpecialOffer(unitPrices, sku, count, specialOffer5HUnitRequirement, specialOffer5HPrice, specialOffer10HUnitRequirement, specialOffer10HPrice);
        } else {
            if (unitPrices.containsKey(sku)) {
                checkoutSum += count * unitPrices.get(sku);
            }
        }
        return checkoutSum;
    }

    private static int handleKPriceCalculation(int count, int checkoutSum, Map<Character, Integer> unitPrices, Character sku) {
        // Handles price calculation for K items, including special offer calculation.

        int specialOfferUnitRequirement = 2;
        int specialOfferPrice = 150;

        if (isSpecialOfferApplicable(count, specialOfferUnitRequirement)) {
            checkoutSum += handle2ForSpecialOffer(unitPrices, sku, count, specialOfferUnitRequirement, specialOfferPrice);
        } else {
            if (unitPrices.containsKey(sku)) {
                checkoutSum += count * unitPrices.get(sku);
            }
        }
        return checkoutSum;
    }

    private static int handlePPriceCalculation(int count, int checkoutSum, Map<Character, Integer> unitPrices, Character sku) {
        // Handles price calculation for P items, including special offer calculation.

        int specialOfferUnitRequirement = 5;
        int specialOfferPrice = 200;

        if (isSpecialOfferApplicable(count, specialOfferUnitRequirement)) {
            checkoutSum += handle2ForSpecialOffer(unitPrices, sku, count, specialOfferUnitRequirement, specialOfferPrice);
        } else {
            if (unitPrices.containsKey(sku)) {
                checkoutSum += count * unitPrices.get(sku);
            }
        }
        return checkoutSum;
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

    private Map<Character, Integer> handleSpecialOffer1FreeFor3SpecificItems(Map<Character, Integer> skuCounts, Character itemThreeIsNeededOf, Character freeItem, int numOfItemsNeededToQualifyForFree) {
        // Handle the special offer where a specific number of items get 1 different kind of item for free.

        if (skuCounts.containsKey(itemThreeIsNeededOf) && skuCounts.containsKey(freeItem)) {
            int threeItemCount = skuCounts.get(itemThreeIsNeededOf);
            int freeItemCount = skuCounts.get(freeItem);

            // Calculate the number of free items.
            int numOfFreeItems = threeItemCount / numOfItemsNeededToQualifyForFree;

            // Deduct the number of free items, put it back into the skuCounts map.
            // The remaining different kind item will cost the customer.
            freeItemCount -= numOfFreeItems;
            skuCounts.put(freeItem, freeItemCount);
        }

        return skuCounts;
    }

    private static boolean isSpecialOfferApplicable(int count, int specialOfferUnitRequirement) {
        return count / specialOfferUnitRequirement > 0;
    }

    private static int handle2ForSpecialOffer(Map<Character, Integer> unitPrices, Character sku, int count, int specialOfferUnitRequirement, int specialOfferPrice) {
        int sum = 0;

        int specialOfferCount = count / specialOfferUnitRequirement;

        // Handle special offers.
        if (specialOfferCount > 0) {
            sum += specialOfferCount * specialOfferPrice;
        }

        // Handle if there are any extra items that are not covered
        // by the special offer, say a 4th item.
        if (((double) count / specialOfferUnitRequirement - specialOfferCount) > 0) {
            count = count - specialOfferUnitRequirement * specialOfferCount;
            sum += count * unitPrices.get(sku);
        }

        return sum;
    }

    private static int handleFSpecialOffer(int count, Map<Character, Integer> unitPrices, Character sku, int specialOfferUnitRequirement) {
        // Handle 2F get one F free special offer.

        int sum = 0;
        while (count > 0 && count / specialOfferUnitRequirement > 0) {
            count -= specialOfferUnitRequirement;
            sum += specialOfferUnitRequirement * unitPrices.get(sku);

            // 1 F is free.
            count = count - 1;
        }

        // Handle any remaining Fs which were not a multiple of 2.
        if (count > 0) {
            sum += count * unitPrices.get(sku);
        }

        return sum;
    }
}



