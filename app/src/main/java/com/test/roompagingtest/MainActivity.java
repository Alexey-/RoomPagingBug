package com.test.roompagingtest;

import android.arch.paging.PagedList;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private MainDatabase mMainDatabase;
    private TestEntityDao mTestEntityDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainDatabase = MainDatabase.getDatabase(this);
        mTestEntityDao = mMainDatabase.getTestEntitiesDao();

        PagedList<TestEntity> pagedList = loadTestEntities();
        updateDisplay(pagedList.size());
    }

    public void onSaveClicked(View view) {
        List<TestEntity> testEntities = generateTestEntities(20);
        mTestEntityDao.saveTestEntities(testEntities);
        PagedList<TestEntity> pagedList = loadTestEntities();
        updateDisplay(pagedList.size());
    }

    public void onSaveAndWaitClicked(View view) {
        List<TestEntity> testEntities = generateTestEntities(20);
        mTestEntityDao.saveTestEntities(testEntities);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                PagedList<TestEntity> pagedList = loadTestEntities();
                updateDisplay(pagedList.size());
            }
        }, 100);
    }

    public void onClearDatabaseClicked(View view) {
        mTestEntityDao.deleteTestEnteties();
        PagedList<TestEntity> pagedList = loadTestEntities();
        updateDisplay(pagedList.size());
    }

    private List<TestEntity> generateTestEntities(int count) {
        List<TestEntity> entities = new ArrayList<>(count);
        for (int i = 0; i < count; ++i) {
            entities.add(new TestEntity(UUID.randomUUID().toString()));
        }
        return entities;
    }

    private PagedList<TestEntity> loadTestEntities() {
        return new PagedList.Builder<>(mTestEntityDao.getTestEntities().create(), 20)
                .setFetchExecutor(Executors.getBackgroundExecutor())
                .setNotifyExecutor(Executors.getMainThreadExecutor())
                .build();
    }

    private void updateDisplay(int entitiesCount) {
        TextView entitiesCountView = findViewById(R.id.entities_count);
        entitiesCountView.setText("Entities in database: " + entitiesCount);
    }

}
