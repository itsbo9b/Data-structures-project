

# 🌳 Tree Table

**Tree Table** is a hybrid data structure developed as part of a Data Structures and Algorithms (DSA) project. It combines the speed of **hash tables** with the hierarchical organization of **tree structures** to manage and query student records efficiently.

---

## 📦 Project Summary

The Tree Table works as follows:
- A **hash table** is used to group students by **enrollment year**, derived from the first few digits of their **student ID**.
- Each index in the hash table holds a **tree** (e.g., binary search tree), which stores detailed student records for that specific year.
- This approach improves both **data organization** and **retrieval performance**, especially for operations constrained by year.

---

## 🔍 Key Features

- 🧮 **Hashed by Enrollment Year**: The first digits of a student ID (e.g., `2021`, `2022`) determine their position in the hash table.
- 🌲 **Tree at Each Index**: A tree stores all student data for that specific year, allowing efficient in-year searches and insertions.
- ⚡ **Efficient Operations**: Combine `O(1)` hash access with `O(log n)` (or better, depending on the tree) operations within each year.
- 🧠 **Real-world Modeling**: Reflects real-world applications such as academic systems, where grouping by enrollment year is common.

---

## 🧾 Data Stored per Student

Each student node in the tree may include:
- `studentID` (e.g., `20231234`)
- `enrollmentYear` (derived from `studentID`)
- `name`
- `major`
- `GPA` or academic info (optional)
- Any additional metadata

---

## 🛠️ Use Case Example

```text
Input Student ID: 20231123
→ Extract Year: 2023
→ Hash Index: hash(2023) % table_size
→ Insert into Tree at Index

Tree at index stores:
├── 20231123 (Alice)
├── 20231245 (Bob)
└── 20231567 (Charlie)
