# 🚀 Data Structures & Algorithms: Efficiency in Practice

> **Purpose:** Demonstrate, with real Java benchmarks, the impact of different data structures and algorithms on performance in tasks like duplicate checking, frequency counting, and sorting.



## 📖 Table of Contents

- [Context](#context)
- [Study Cases](#study-cases)
- [Mathematical Analysis](#mathematical-analysis)
- [Conclusions](#conclusions)
- [Running the Benchmarks](#running-the-benchmarks)
- [References](#references)

---

## Context

Algorithms and data structures are the backbone of software performance. Choosing the right one can dramatically improve runtime, scalability, and resource usage.
This project shows, with **real benchmarks in Java**, how different choices impact execution time in tasks developers face daily.

---

## Study Cases

### 1️⃣ Duplicate Checking: List vs. Set

**Problem:** Detect if a collection of numbers contains duplicates.

- **List-based approach:** Uses nested loops comparing each element to every other
   - Complexity: **O(n²)**
   - Disadvantage: Performance degrades exponentially as the dataset grows.
- **Set-based approach:** Uses a `HashSet` and checks the return value of `add()`
   - Complexity: **O(n)** (with average O(1) per insertion)
   - Advantage: Scales linearly, much faster for large datasets.

**Benchmark result example (1 million elements):**  
- `Set` finished in milliseconds, while `List` could take hours in extreme cases.

```java
// List O(n²): nested loops
for (int i = 0; i < n; i++) {
    for (int j = i + 1; j < n; j++) {
        if (list.get(i).equals(list.get(j))) return true;
    }
}

// Set O(n): linear insertion and duplicate detection
Set<Integer> uniqueNumbers = new HashSet<>();
for (Integer num : list) {
    if (!uniqueNumbers.add(num)) return true;
}
```

---

### 2️⃣ Frequency Counting: HashMap vs. TreeMap

**Problem:** Count how many times each number appears.

- **HashMap:** Fast insertion and update in O(1) average, does not keep keys ordered.
- **TreeMap:** Maintains keys sorted, but operations take O(log n) due to its balanced tree structure.

**Benchmark result example (1 million elements):**
- HashMap was 2–3x faster than TreeMap;
- Both produced identical frequency counts (but TreeMap returned sorted keys).

```java
// HashMap O(1) average
Map<Integer, Integer> freq = new HashMap<>();
freq.put(num, freq.getOrDefault(num, 0) + 1);

// TreeMap O(log n) per operation
Map<Integer, Integer> freqTree = new TreeMap<>();
freqTree.put(num, freqTree.getOrDefault(num, 0) + 1);
```

---

### 3️⃣ Sorting Performance: Bubble Sort vs. Collections.sort()

**Problem:** Sort a list of numbers.
- Bubble Sort:
   - Complexity: O(n²)
   - Use: Educational purposes only; impractical for production.
- Collections.sort():
   - Uses Java’s TimSort, a hybrid of MergeSort and InsertionSort;
   - Complexity: O(n log n)
   - Use: Industry-standard for sorting.

**Benchmark result example (50,000 elements):**
- Bubble Sort took minutes;
- Collections.sort() completed in milliseconds.


Why is List O(n²) for duplicate checking?
Each element is compared to every other → roughly n * (n-1)/2 comparisons → O(n²).

Why is Set O(n)?
Each add() in HashSet has an average O(1) cost; for n elements → O(n) total.

Why is TreeMap O(log n) per operation?
TreeMap uses balanced trees (Red-Black) with O(log n) per insert/update → O(n log n) for n elements.

---

## Mathematical Analysis

| Task                 | Algorithm/Data Structure   | Complexity | Scales for large datasets?        |
|----------------------|----------------------------|------------|-----------------------------------|
| Duplicate checking   | List (nested loops)        | O(n²)      | ❌ No                            |
| Duplicate checking   | HashSet                    | O(n)       | ✅ Yes                           |
| Frequency counting   | HashMap                    | O(n)       | ✅ Yes                           |
| Frequency counting   | TreeMap                    | O(n log n) | ⚠️ Use if ordered keys needed   |
| Sorting              | Bubble Sort                | O(n²)      | ❌ No                            |
| Sorting              | TimSort (Collections.sort) | O(n log n) | ✅ Yes                           |


## Conclusions

✅ Key learnings:
- The choice of data structure can make or break application performance.
- For duplicate checking or frequency counting on large datasets, hash-based structures (HashSet/HashMap) are the best choice.
- Bubble Sort is great for education but never for production use.
- Always measure performance with real data — benchmarks expose bottlenecks theory can’t predict.

## References
- 📄 [Big O Cheat Sheet](https://www.bigocheatsheet.com/)
- 📚 [Oracle Java Documentation](https://docs.oracle.com/en/java/)
- 📘 [Effective Java - Joshua Bloch](https://www.oreilly.com/library/view/effective-java-3rd/9780134686097)