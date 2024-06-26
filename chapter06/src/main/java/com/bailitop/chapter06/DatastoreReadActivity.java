package com.bailitop.chapter06;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import util.DatastoreUtil;

public class DatastoreReadActivity  extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_data;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datastore_read);
        tv_data = findViewById(R.id.tv_data);
        readDatastore();
    }
    // 从数据仓库中读取信息
    private void readDatastore() {
        DatastoreUtil datastore = DatastoreUtil.getInstance(this); // 获取数据仓库工具的实例
        String desc = "数据仓库中保存的信息如下：";
        desc = String.format("%s\n　%s为%s", desc, "姓名", datastore.getStringValue("name"));
        desc = String.format("%s\n　%s为%d", desc, "年龄", datastore.getIntValue("age"));
        desc = String.format("%s\n　%s为%d", desc, "身高",datastore.getIntValue("height"));
        desc = String.format("%s\n　%s为%.2f", desc, "体重",datastore.getDoubleValue("weight"));
        desc = String.format("%s\n　%s为%b", desc, "婚否",datastore.getBooleanValue("married"));
        desc = String.format("%s\n　%s为%s", desc, "更新时间", datastore.getStringValue("update_time"));
        tv_data.setText(desc);
    }

    @Override
    public void onClick(View view) {

    }
}
