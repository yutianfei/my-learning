package com.wsy.rxdemo.backdemo;

import android.os.SystemClock;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Description
 * 2016/5/25.
 */
public class CustomIterator implements Iterable<Long> {

    private List<Long> mNumberList = new ArrayList<>();

    public CustomIterator() {
        for (long i = 0; i < BackgroundActivity.MAX_PROGRESS; i++) {
            mNumberList.add(i + 1);
        }
    }

    @Override
    public Iterator<Long> iterator() {

        return new Iterator<Long>() {

            private int mCurrentIndex = 0;

            @Override
            public boolean hasNext() {
                return mCurrentIndex < mNumberList.size() && mNumberList.get(mCurrentIndex) != null;
            }

            @Override
            public Long next() {
                SystemClock.sleep(BackgroundActivity.EMIT_DELAY_MS);
                return mNumberList.get(mCurrentIndex++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
