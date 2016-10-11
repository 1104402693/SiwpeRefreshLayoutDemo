package com.gh.siwperefreshlayoutdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SwipeRefreshLayout mLayout;
    private RecyclerView mRecycleView;
    private List<String> datas = new ArrayList<>();
    private MyAdatpter mAdatpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLayout = (SwipeRefreshLayout) findViewById(R.id.refreshLayout);
        initDatas();
        initRecyclerView();
        initRefreshLayout();
    }

    private void initRecyclerView() {
        mRecycleView = (RecyclerView) findViewById(R.id.recyclerView);
        mAdatpter = new MyAdatpter(datas);
        mRecycleView.setAdapter(mAdatpter);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        // mRecycleView.setLayoutManager(new GridLayoutManager(this,2));
        //mRecycleView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));

        mRecycleView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mAdatpter.setOnItemClickListener(new MyAdatpter.OnItemClickListener() {
            @Override
            public void onClick(View v, int position, String city) {
                Toast.makeText(MainActivity.this, "city:" + city + "position" + position, Toast.LENGTH_LONG);
            }
        });
    }

    private void initRefreshLayout(){
        //设置刷新圈的颜色
        mLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_green_light,android.R.color.holo_red_light,android.R.color.holo_orange_light);
        //下拉距离触发下拉
        mLayout.setDistanceToTriggerSync(100);
        //设置刷新背景颜色
        mLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.item_press));
        //设置刷新
        mLayout.setSize(SwipeRefreshLayout.LARGE);
        mLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){

            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i=0;i<10;i++){
                            mAdatpter.addData(i,"new City:"+i);
                        }
                        mAdatpter.notifyItemChanged(0,10);
                        mRecycleView.scrollToPosition(0);
                        mLayout.setRefreshing(false);
                //正在刷新        mLayout.isRefreshing();
                    }
                },3000);
            }
        });
    }
    private void initDatas() {
        datas.add("Gena's Demo");
        datas.add("China");
        datas.add("Hoston");
        datas.add("Krea");
        datas.add("Chicago");
        datas.add("San Antnio");
        datas.add("Washington");
        datas.add("Las Vegas");
        datas.add("New york");
        datas.add("Boston");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_action_add:
                mAdatpter.addData(0, "new City");
                break;
            case R.id.id_action_delete:
                mAdatpter.removeData(1);
                break;
            case R.id.id_action_gridview:
                mRecycleView.setLayoutManager(new GridLayoutManager(this, 2));
                break;
            case R.id.id_action_listview:
                mRecycleView.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.id_action_stag:
                mRecycleView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
                break;
        }
        return true;
    }

}
