package com.jetway.recyclerviewdemo;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jetway.recyclerviewdemo.commonAdapter.ItemClickListener;
import com.jetway.recyclerviewdemo.commonAdapter.ItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

public class BaseRecyclerActivity extends AppCompatActivity {
private List<String>mDatas;
private RecyclerView recycler_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_recycler);
        initDatas();
        recycler_data = findViewById(R.id.recycler_data);
        recycler_data.setLayoutManager(new GridLayoutManager(this,3));
        ListAdapter adapter = new ListAdapter(this,mDatas,R.layout.recycler_item);
        adapter.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                Toast.makeText(BaseRecyclerActivity.this,mDatas.get(position)+"个",Toast.LENGTH_LONG).show();
            }
        });
        adapter.setOnItemLongClickListener(new ItemLongClickListener() {
            @Override
            public boolean OnItemLongClickListener(int position) {
                Toast.makeText(BaseRecyclerActivity.this,mDatas.get(position)+"个",Toast.LENGTH_LONG).show();
                return false;
            }
        });
        recycler_data.setAdapter( adapter);
      //  recycler_data.setAdapter(new MyRecyclerAdater());
       // recycler_data.addItemDecoration(new MyItemDecoration());
        recycler_data.addItemDecoration(new GradLayoutItemDecoration(this,R.drawable.item_drivder));
    }

    private void initDatas() {
        mDatas = new ArrayList<>();
        for (int i = 'A'; i <'Z' ; i++) {
            mDatas.add((char) i+"");
            
        }
    }

    private class MyItemDecoration extends RecyclerView.ItemDecoration {
        private Paint mPaint = new Paint();

        public MyItemDecoration() {

            mPaint.setColor(getResources().getColor(R.color.colorAccent));
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            Rect rect = new Rect();
            rect.left = parent.getPaddingLeft();
            rect.right = parent.getWidth() - parent.getPaddingRight();
            int postion = parent.getChildCount();
            for (int i = 1; i < postion; i++) {
                rect.top = rect.bottom-1;
                rect.bottom = parent.getChildAt(i).getTop();
                c.drawRect(rect, mPaint);
            }
        }


        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            int position = parent.getChildAdapterPosition(view);
            if (position != 0) {
                outRect.top=1;
            }
        }
    }

    private class MyRecyclerAdater extends RecyclerView.Adapter<MyRecyclerAdater.ViewHolder> {


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(BaseRecyclerActivity.this).inflate(R.layout.recycler_item, parent, false);
            ViewHolder holder = new ViewHolder(inflate);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.mTextView.setText(mDatas.get(position));
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;
            public ViewHolder(View itemView) {
                super(itemView);
                mTextView =itemView. findViewById(R.id.item_data);
            }
        }
    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.gridview:
                recycler_data.setLayoutManager(new GridLayoutManager(BaseRecyclerActivity.this,4));
                break;
            case R.id.listview:
                recycler_data.setLayoutManager(new LinearLayoutManager(BaseRecyclerActivity.this));
                break;
        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
