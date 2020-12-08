package com.algorithms.unionfind;

public class UnionFindQuickUnionWeighted {
    private final int[] id;
    private final int[] sz;

    // set id of each object to it self
    // N array accesses
    public UnionFindQuickUnionWeighted(int N) {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++ ) {
            id[i] = i;
            sz[i] = 1;
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

        if (i == j) {
            return;
        } else if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = j;
            sz[i] += sz[j];
        }
    }
}
