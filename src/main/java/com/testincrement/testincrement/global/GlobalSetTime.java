package com.testincrement.testincrement.global;

import com.google.common.base.MoreObjects;

public class GlobalSetTime {

    private volatile long beginTime;

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("beginTime", beginTime)
                .toString();
    }
}
