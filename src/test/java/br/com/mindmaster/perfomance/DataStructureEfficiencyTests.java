package br.com.mindmaster.perfomance;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DisplayName("Practical Examples: Impact of Data Structures and Algorithms on Efficiency")
public class DataStructureEfficiencyTests {

    @Nested
    @DisplayName("Duplicate Verification: List vs. Set")
    class DuplicateCheckingTests {

        @Test
        @DisplayName("Compare List and Set for Duplicate Verification")
        void testListVsSetForDuplicateChecking() {
            // Generate a large list of random numbers
            List<Integer> randomNumbers = generateRandomIntegers(1_000_000);

            // Measuring execution time using List
            Instant listStart = Instant.now();
            boolean hasDuplicatesList = hasDuplicatesWithList(randomNumbers);
            Instant listEnd = Instant.now();
            long listDuration = Duration.between(listStart, listEnd).toMillis();
            log.info("Duplicate check using List took: {} ms", listDuration);

            // Measuring execution time using Set
            Instant setStart = Instant.now();
            boolean hasDuplicatesSet = hasDuplicatesWithSet(randomNumbers);
            Instant setEnd = Instant.now();
            long setDuration = Duration.between(setStart, setEnd).toMillis();
            log.info("Duplicate check using Set took: {} ms", setDuration);

            // Validate that both approaches produce the same result
            assertThat(hasDuplicatesList).isEqualTo(hasDuplicatesSet);
        }

        /**
         * Verify duplicates using a List (inefficient for large datasets).
         */
        private boolean hasDuplicatesWithList(List<Integer> numbers) {
            for (int i = 0; i < numbers.size(); i++) {
                for (int j = i + 1; j < numbers.size(); j++) {
                    if (numbers.get(i).equals(numbers.get(j))) {
                        return true;
                    }
                }
            }
            return false;
        }

        /**
         * Verify duplicates using a Set (efficient).
         */
        private boolean hasDuplicatesWithSet(List<Integer> numbers) {
            Set<Integer> uniqueNumbers = new HashSet<>();
            for (Integer num : numbers) {
                if (!uniqueNumbers.add(num)) {
                    return true;
                }
            }
            return false;
        }
    }

    @Nested
    @DisplayName("Frequency Counting: List vs. Map")
    class FrequencyCountingTests {

        @Test
        @DisplayName("Count Frequencies Using List, HashMap, and TreeMap")
        void testFrequencyCountingWithListVsMap() {
            // Generate a large array of numbers
            int[] randomNumbers = IntStream.range(0, 1_000_000).toArray();

            // Count frequencies with HashMap
            Instant hashMapStart = Instant.now();
            Map<Integer, Integer> frequencyMap = countFrequencyWithMap(randomNumbers);
            Instant hashMapEnd = Instant.now();
            long hashMapDuration = Duration.between(hashMapStart, hashMapEnd).toMillis();
            log.info("Frequency counting using HashMap took: {} ms", hashMapDuration);

            // Count frequencies with TreeMap
            Instant treeMapStart = Instant.now();
            Map<Integer, Integer> frequencyTreeMap = countFrequencyWithTreeMap(randomNumbers);
            Instant treeMapEnd = Instant.now();
            long treeMapDuration = Duration.between(treeMapStart, treeMapEnd).toMillis();
            log.info("Frequency counting using TreeMap took: {} ms", treeMapDuration);

            // Validate that both approaches produce the same result
            assertThat(frequencyMap).isEqualTo(frequencyTreeMap);
        }

        /**
         * Count the frequency of each number using a HashMap (efficient).
         */
        private Map<Integer, Integer> countFrequencyWithMap(int[] numbers) {
            Map<Integer, Integer> frequencyMap = new HashMap<>();
            for (int num : numbers) {
                frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
            }
            return frequencyMap;
        }

        /**
         * Count the frequency of each number using a TreeMap. Maintains sorted keys.
         */
        private Map<Integer, Integer> countFrequencyWithTreeMap(int[] numbers) {
            Map<Integer, Integer> frequencyMap = new TreeMap<>();
            for (int num : numbers) {
                frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
            }
            return frequencyMap;
        }
    }

    @Nested
    @DisplayName("Sorting Performance: Bubble Sort vs Collections.sort()")
    class SortingPerformanceTests {

        @Test
        @DisplayName("Compare Bubble Sort and Collections.sort()")
        void testSortingPerformance() {
            // Generate a large list of random numbers
            List<Integer> randomNumbersForBubbleSort = generateRandomIntegers(50_000);
            List<Integer> randomNumbersForCollectionsSort = new ArrayList<>(randomNumbersForBubbleSort);

            // Bubble sort benchmark
            Instant bubbleSortStart = Instant.now();
            bubbleSort(randomNumbersForBubbleSort);
            Instant bubbleSortEnd = Instant.now();
            long bubbleSortDuration = Duration.between(bubbleSortStart, bubbleSortEnd).toMillis();
            log.info("Bubble Sort took: {} ms", bubbleSortDuration);

            // Collections.sort benchmark
            Instant collectionsSortStart = Instant.now();
            Collections.sort(randomNumbersForCollectionsSort);
            Instant collectionsSortEnd = Instant.now();
            long collectionsSortDuration = Duration.between(collectionsSortStart, collectionsSortEnd).toMillis();
            log.info("Collections.sort took: {} ms", collectionsSortDuration);

            // Validate that both approaches produce the same sorted result
            assertThat(randomNumbersForBubbleSort).isEqualTo(randomNumbersForCollectionsSort);
        }

        /**
         * Implementation of Bubble Sort for demonstration purposes.
         */
        private void bubbleSort(List<Integer> list) {
            int n = list.size();
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (list.get(j) > list.get(j + 1)) {
                        // Swap elements
                        int temp = list.get(j);
                        list.set(j, list.get(j + 1));
                        list.set(j + 1, temp);
                    }
                }
            }
        }
    }

    /**
     * Generate a list of random integers.
     */
    private List<Integer> generateRandomIntegers(int count) {
        return new Random().ints(count, 0, 1_000_000)
                .boxed()
                .collect(Collectors.toList());
    }

}