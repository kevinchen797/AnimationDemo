package com.animation.cmy.animationtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements AnimationAdapter.ListViewCheckListener {

    private ListView lvAnimation;
    private AnimationAdapter mAdapter;
    private List<People> peopleList = new ArrayList<>();
    private People people;
    private Set<String> selectOptions = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        for (int i=0; i<20; i++) {
            people = new People();
            people.setName("南京旭品" + i);
            people.setAge(4 * (i+1));
            peopleList.add(people);
        }

        mAdapter = new AnimationAdapter(this, peopleList);
        //设置头像点击事件监听
        mAdapter.setInterface(this);
        lvAnimation.setAdapter(mAdapter);
    }

    private void initView() {
        lvAnimation = (ListView) findViewById(R.id.lv_animation);
        lvAnimation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("cmy", "ITEM CLICK");
                selectOptions = mAdapter.getSelectOptions();
                people = peopleList.get(position);
                if (selectOptions.size() > 0) { // 多选模式
                    if (selectOptions.contains(people.getName())) {
                        selectOptions.remove(people.getName());
                        mAdapter.setSelectOptions(selectOptions);
                        Log.d("cmy", "CANCLE + 1");
                    } else {
                        selectOptions.add(people.getName());
                        mAdapter.setSelectOptions(selectOptions);
                        Log.d("cmy", "SELECT + 1");
                    }
                    mAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "点击跳转事件", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void ListViewCheckListener(Set<String> selectOptions) {

    }
}
