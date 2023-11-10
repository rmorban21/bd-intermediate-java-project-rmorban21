package com.amazon.ata.deliveringonourpromise.comparators;

import com.amazon.ata.deliveringonourpromise.types.Promise;

import java.util.Comparator;

public class PromiseAsinComparator implements Comparator<Promise> {
    @Override
    public int compare(Promise o1, Promise o2) {
        String asin1 = o1.getAsin();
        String asin2 = o2.getAsin();
        return asin1.compareTo(asin2);
    }
}
