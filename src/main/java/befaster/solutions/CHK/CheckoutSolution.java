package befaster.solutions.CHK;

import java.util.*;

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

        // Handle this special offer:
        // any 3 of (S,T,X,Y,Z) for 45.
        checkoutSum = handleAny3SpecialOffer(skus, checkoutSum, skuCounts, unitPrices);

        // For every 2 pieces of E, 1 B is received for free.
        skuCounts = handleSpecialOffer1FreeFor3SpecificItems(skuCounts, 'E', 'B', 2);

        // For every 3 pieces of N, 1 M is received for free.
        skuCounts = handleSpecialOffer1FreeFor3SpecificItems(skuCounts, 'N', 'M', 3);

        // For every 3 pieces of R, 1 Q is received for free.
        skuCounts = handleSpecialOffer1FreeFor3SpecificItems(skuCounts, 'R', 'Q', 3);

        int fSpecialOfferUnitRequirement = 2;
        int uSpecialOfferUnitRequirement = 3;

        // Calculate the checkout sum.
        for (Map.Entry<Character, Integer> entry : skuCounts.entrySet()) {
            Character sku = entry.getKey();
            int count = entry.getValue();

            if (sku.equals('A')) {
                checkoutSum = handleBulkSpecialOffer(count, checkoutSum, unitPrices, sku, 3, 130, 5, 200);
            } else if (sku.equals('B')) {
                checkoutSum = handleMultiSKUPriceCalculation(count, checkoutSum, unitPrices, sku, 2, 45);
            } else if (sku.equals('F') && (count / fSpecialOfferUnitRequirement > 0)) {
                checkoutSum += handleMultipleOfSameTypeGetOneSameTypeFreeOffer(count, unitPrices, sku, fSpecialOfferUnitRequirement);
            } else if (sku.equals('H')) {
                checkoutSum = handleBulkSpecialOffer(count, checkoutSum, unitPrices, sku, 5, 45, 10, 80);
            } else if (sku.equals('K')) {
                checkoutSum = handleMultiSKUPriceCalculation(count, checkoutSum, unitPrices, sku, 2, 120);
            } else if (sku.equals('P')) {
                checkoutSum = handleMultiSKUPriceCalculation(count, checkoutSum, unitPrices, sku, 5, 200);
            } else if (sku.equals('Q')) {
                checkoutSum = handleMultiSKUPriceCalculation(count, checkoutSum, unitPrices, sku, 3, 80);
            } else if (sku.equals('U') && (count / uSpecialOfferUnitRequirement > 0)) {
                checkoutSum += handleMultipleOfSameTypeGetOneSameTypeFreeOffer(count, unitPrices, sku, uSpecialOfferUnitRequirement);
            } else if (sku.equals('V')) {
                checkoutSum = handleBulkSpecialOffer(count, checkoutSum, unitPrices, sku, 2, 90, 3, 130);
            }
            // Handle remaining SKUs.
            else if (!sku.equals('S') && !sku.equals('T') && !sku.equals('X') && !sku.equals('Y') && !sku.equals('Z')) {
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
        unitPrices.put('K', 70);
        unitPrices.put('L', 90);
        unitPrices.put('M', 15);
        unitPrices.put('N', 40);
        unitPrices.put('O', 10);
        unitPrices.put('P', 50);
        unitPrices.put('Q', 30);
        unitPrices.put('R', 50);
        unitPrices.put('S', 20);
        unitPrices.put('T', 20);
        unitPrices.put('U', 40);
        unitPrices.put('V', 50);
        unitPrices.put('W', 20);
        unitPrices.put('X', 17);
        unitPrices.put('Y', 20);
        unitPrices.put('Z', 21);
        return unitPrices;
    }

    private boolean hasNonAlphabeticAndLowercaseChars(String skus) {
        for (char c : skus.toCharArray()) {
            if (!Character.isLetter(c) || Character.isLowerCase(c)) {
                return true;
            }
        }
        return false;
    }

    private int handleBulkSpecialOffer(int count, int checkoutSum, Map<Character, Integer> unitPrices, Character sku, int specialOfferFirstUnitRequirement, int specialOfferFirstPrice, int specialOfferSecondUnitRequirement, int specialOfferSecondPrice) {
        // Handles price calculation for items, including special offer
        // calculation, where certain items cost less if you buy in bulk
        // and the more you buy, the less it costs.

        // Example 5A for 200, 3A for 130, but an individual A is 50.

        if (isSpecialOfferApplicable(count, specialOfferFirstUnitRequirement) || isSpecialOfferApplicable(count, specialOfferSecondUnitRequirement)) {
            checkoutSum += handleIncreasingBulkSpecialOffer(unitPrices, sku, count, specialOfferFirstUnitRequirement, specialOfferFirstPrice, specialOfferSecondUnitRequirement, specialOfferSecondPrice);
        } else {
            if (unitPrices.containsKey(sku)) {
                checkoutSum += count * unitPrices.get(sku);
            }
        }
        return checkoutSum;
    }

    private int handleIncreasingBulkSpecialOffer(Map<Character, Integer> unitPrices, Character sku, int count, int specialOfferFirstUnitRequirement, int specialOfferFirstPrice, int specialOfferSecondUnitRequirement, int specialOfferSecondPrice) {
        // Handles a special offer where certain items cost less if you buy in bulk
        // and the more you buy, the less it costs.

        int sum = 0;

        // If the SKU type's count is divisible by the second unit requirement (higher), then apply that special offer.
        // Then, if the remaining count is divisible by the first unit requirement (lower), then apply that special offer.
        // Then, if there are any remaining items of the type, then those are priced individually.
        // Example 5A for 200, 3A for 130, but an individual A is 50.

        boolean isDivisibleBySecondUnitRequirement = (count / specialOfferSecondUnitRequirement) > 0;

        if (isDivisibleBySecondUnitRequirement) {
            int multiplesOf5A = count / specialOfferSecondUnitRequirement;
            sum += multiplesOf5A * specialOfferSecondPrice;
            count = count - specialOfferSecondUnitRequirement * multiplesOf5A;
        }

        boolean isDivisibleByFirstUnitRequirement = (count / specialOfferFirstUnitRequirement) > 0;

        if (isDivisibleByFirstUnitRequirement) {
            int multiplesOf3A = count / specialOfferFirstUnitRequirement;
            sum += multiplesOf3A * specialOfferFirstPrice;
            count = count - specialOfferFirstUnitRequirement * multiplesOf3A;
        }

        if (count > 0) {
            sum += count * unitPrices.get(sku);
        }

        return sum;
    }

    private static int handleMultiSKUPriceCalculation(int count, int checkoutSum, Map<Character, Integer> unitPrices, Character sku, int specialOfferUnitRequirement, int specialOfferPrice) {
        // Handles price calculation for a specific SKU, including special offer calculation.

        if (isSpecialOfferApplicable(count, specialOfferUnitRequirement)) {
            checkoutSum += handleMultipleOfSameItemSpecialOffer(unitPrices, sku, count, specialOfferUnitRequirement, specialOfferPrice);
        } else {
            if (unitPrices.containsKey(sku)) {
                checkoutSum += count * unitPrices.get(sku);
            }
        }
        return checkoutSum;
    }

    private static boolean isSpecialOfferApplicable(int count, int specialOfferUnitRequirement) {
        return count / specialOfferUnitRequirement > 0;
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

    private static <K, V extends Comparable<? super V>> List<Map.Entry<K, V>> entriesSortedByValues(Map<K, V> map) {
        List<Map.Entry<K, V>> sortedEntries = new ArrayList<Map.Entry<K, V>>(map.entrySet());

        Collections.sort(sortedEntries,
                new Comparator<Map.Entry<K, V>>() {
                    @Override
                    public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) {
                        return e2.getValue().compareTo(e1.getValue());
                    }
                });

        return sortedEntries;
    }

    private static int handleAny3SpecialOffer(String skus, int checkoutSum, Map<Character, Integer> skuCounts, Map<Character, Integer> unitPrices) {
        // Handles any 3 of (S,T,X,Y,Z) for 45 special offer.

        Map<Character, Integer> specialItemPriceMap = new TreeMap<>();
        specialItemPriceMap.put('S', 20);
        specialItemPriceMap.put('T', 20);
        specialItemPriceMap.put('X', 17);
        specialItemPriceMap.put('Y', 20);
        specialItemPriceMap.put('Z', 21);

        entriesSortedByValues(specialItemPriceMap);

        StringBuilder sb = new StringBuilder();

        for (char c : skus.toCharArray()) {
            if (c == 'S' || c == 'T' || c == 'X' || c == 'Y' || c == 'Z') {
                sb.append(c);
            }
        }

        sb = new StringBuilder().append(sortStringByCharValueMap(sb.toString(), specialItemPriceMap));

        int lastDivisibleIndex = 0;

        for (int i = 0; i < sb.length(); i++) {
            if (i != 0 && (i + 1) % 3 == 0) {
                checkoutSum += 45;

                int currentCharCount = skuCounts.get(sb.charAt(i));
                currentCharCount--;
                skuCounts.put(sb.charAt(i), currentCharCount);

                int prevCharCount = skuCounts.get(sb.charAt(i - 1));
                prevCharCount--;
                skuCounts.put(sb.charAt(i - 1), prevCharCount);

                int prevPrevCharCount = skuCounts.get(sb.charAt(i - 2));
                prevPrevCharCount--;
                skuCounts.put(sb.charAt(i - 2), prevPrevCharCount);

                lastDivisibleIndex = i + 1;
            }
        }

        for (int j = lastDivisibleIndex; j < sb.length(); j++) {
            checkoutSum += unitPrices.get(sb.charAt(j));
        }
        return checkoutSum;
    }

    private static String sortStringByCharValueMap(String string, Map<Character, Integer> specialItemPriceMap) {
        // Sorts a string's characters into descending order
        // based on the values stored in specialItemPriceMap.

        List<Map.Entry<Character, Integer>> charValuePairs = new ArrayList<>(specialItemPriceMap.entrySet());

        // Sort the list by value in descending order.
        charValuePairs.sort((o1, o2) -> Integer.compare(o2.getValue(), o1.getValue()));

        // Build a new string with the sorted characters.
        StringBuilder sortedStr = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : charValuePairs) {
            sortedStr.append(entry.getKey());
        }

        return sortedStr.toString();
    }

    private Map<Character, Integer> handleSpecialOffer1FreeFor3SpecificItems(Map<Character, Integer> skuCounts, Character itemThreeIsNeededOf, Character freeItem, int numOfItemsNeededToQualifyForFree) {
        // Handles the special offer where a specific number of items get 1 different kind of item for free.

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

    private static int handleMultipleOfSameItemSpecialOffer(Map<Character, Integer> unitPrices, Character sku, int count, int specialOfferUnitRequirement, int specialOfferPrice) {
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

    private static int handleMultipleOfSameTypeGetOneSameTypeFreeOffer(int count, Map<Character, Integer> unitPrices, Character sku, int specialOfferUnitRequirement) {
        // Handle multiple item, get one item free (of the same type) special offer.

        int sum = 0;
        while (count > 0 && count / specialOfferUnitRequirement > 0) {
            count -= specialOfferUnitRequirement;
            sum += specialOfferUnitRequirement * unitPrices.get(sku);

            // 1 unit is free.
            count = count - 1;
        }

        // Handle any remaining units which were not a multiple of 2.
        if (count > 0) {
            sum += count * unitPrices.get(sku);
        }

        return sum;
    }
}


