package com.jetway.recyclerviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements OnStartDragListener /*,ItemTouchMoveListener*/{
    private RecyclerView rl_data;

    private List<String> number;
    MyRecyclerViewAdapter adapter;
    ItemTouchHelper itemTouchHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rl_data = findViewById(R.id.rl_data);
        rl_data.setLayoutManager(new LinearLayoutManager(this));
        number = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            number.add("序列号" + i);
        }
        adapter = new MyRecyclerViewAdapter(this, number,this);
        rl_data.setAdapter(adapter);
        MyItemTouchHelperCallBack callback = new MyItemTouchHelperCallBack(adapter);
         itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(rl_data);

    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.gridview:
                rl_data.setLayoutManager(new GridLayoutManager(MainActivity.this,4));
                break;
            case R.id.listview:
                rl_data.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                break;
        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        itemTouchHelper.startDrag(viewHolder);
    }


    /*@Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        //1:数据交换；  2：刷新
        Collections.swap(number,fromPosition,toPosition);
        adapter.notifyItemMoved(fromPosition,toPosition);
        return false;
    }

    @Override
    public boolean onItemRemove(int position) {
        //1:删除数据；  2：刷新
        number.remove(position);
        adapter.notifyItemRemoved(position);
        return false;
    }*/
}
