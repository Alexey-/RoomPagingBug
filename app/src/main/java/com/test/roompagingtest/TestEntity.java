package com.test.roompagingtest;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Objects;

@Entity
public class TestEntity {

    @PrimaryKey
    @NonNull
    private String mId;

    public TestEntity(@NonNull String id) {
        mId = id;
    }

    @NonNull
    public String getId() {
        return mId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestEntity that = (TestEntity) o;
        return Objects.equals(mId, that.mId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mId);
    }
}
