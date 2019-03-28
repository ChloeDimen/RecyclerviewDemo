package com.jetway.recyclerviewdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jetway.recyclerviewdemo.commonAdapter.MulitiSpoort;
import com.jetway.recyclerviewdemo.commonAdapter.RecyclerviewCommonAdapter;
import com.jetway.recyclerviewdemo.commonAdapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MulitiActivity extends AppCompatActivity {
private RecyclerView recycler_datas;
private List<ChatData> mChatData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muliti);
        recycler_datas=findViewById(R.id.recycler);
        mChatData = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            if (i % 3 == 0) {
                mChatData.add(new ChatData("自己内容" + i, 1));

            } else {
                mChatData.add(new ChatData("内容" + i, 0));
            }

        }
        recycler_datas.setLayoutManager(new LinearLayoutManager(this));
        recycler_datas.setAdapter(new MyAdapter(this,mChatData));
    }

    private  class MyAdapter extends RecyclerviewCommonAdapter<ChatData> {
        public MyAdapter(Context context, List<ChatData> datas) {
            super(context, datas, new MulitiSpoort<ChatData>() {
                @Override
                public int getLayoutId(ChatData item) {
                    if (item.isMe == 1) {
                        return R.layout.item_right;
                    }
                    return R.layout.item_left;
                }
            });
        }

        @Override
        public void convert(ViewHolder holder, ChatData o, int position) {
            holder.setText(R.id.chat_data, o.str);


        }
    }

    public class ChatData{
    private String str;
    private int isMe;

        public ChatData(String str, int isMe) {
            this.str = str;
            this.isMe = isMe;
        }
    }

}
