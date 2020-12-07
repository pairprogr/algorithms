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
