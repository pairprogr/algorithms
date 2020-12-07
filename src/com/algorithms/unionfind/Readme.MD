
## Implementation

```java
package com.algorithms.unionfind;

public class UnionFindQuickFind {
    private final int[] id;

    // set id of each object to it self
    // N array accesses
    public UnionFindQuickFind(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++ ) {
            id[i] = i;
        }
    }

    // check if p and q are connected, i.e.
    // a part of same component 2 array accesses
    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    // change all entries with id[p] to id[q]
    // at mose 2n + 2 array accesses
    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
    }
}
```

## Cost Model

| Algorithm | init | union | find 
 -----------|------|-------|-----
| quick-find | N | N | 1 | 


quick-find: union operation too expensive, because it takes N<sup>2</sup> array accesses to process sequence of N union commands on N objects.

Quadratic algorithms don't scale with technology.

# Quick Union [lazy approach]

## Data structure

Data structure is almost the same, an array of integers. But with a slightly different meaning.

* Integer array id[] of size N.
* Interpretation: id[i] is parent of i.
* **Root** of i is id[id[id[id[...id[i]...]]]]
  * which keeps going until it doesn't change (algorithm ensures no cycles)

|  | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 
|---|---|---|---|---|---|---|---|---|---|---|
id[] | 0 | 1 | 9 | 4 | 9 | 6 | 6 | 7 |8 | 9 |

```
0
1
9 -> 2
  -> 4 -> 3 (p)
6 -> 5 (q)
7
8     
```
3 and 5 are not connected

* **Find** - Check if p and q have same root.
* **Union** - To merge components containing p and q,
set the id of p's root to the id of q's root.

|  | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
|---|---|---|---|---|---|---|---|---|---|---|
id[] | 0 | 1 | 9 | 4 | 9 | 6 | 6 | 7 |8 | *6 the only change|

```
0
1
6 -> 9 -> 2
  -> 4 -> 3 (p)
  -> 5 (q)
7
8     
```

3 and 5 are now connected

## Implementation

```java
package com.algorithms.unionfind;

public class UnionFindQuickUnion {
    private final int[] id;

    // set id of each object to it self
    // N array accesses
    public UnionFindQuickUnion(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++ ) {
            id[i] = i;
        }
    }
    
    // chase parent pointers until reach root
    // (depth of i array accesses)
    private int root(int i) {
        while (i != id[i]) {
            i = id[i];
        }
        
        return i;
    }

    // check if p and q are connected, i.e.
    // depth of p and q array accesses
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    // change root of p to point to root of q
    // depth of p and q array accesses
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        id[i] = j;
    }
}
```

## Cost Model

| Algorithm | init | union | find
 -----------|------|-------|-----
| quick-find | N | N | 1 |
| quick-union | N | N | N | 
