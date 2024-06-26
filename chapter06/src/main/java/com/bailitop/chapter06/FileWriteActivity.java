package com.bailitop.chapter06;

import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import util.DateUtil;
import util.FileUtil;
import util.ToastUtil;

public class FileWriteActivity  extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private EditText et_name;
    private EditText et_age;
    private EditText et_height;
    private EditText et_weight;
    private TextView  tv_path;
    private String mPath;
    private boolean isMarried = false;
    private String[] typeArray = {"未婚", "已婚"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_write);
        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_age = findViewById(R.id.et_age);
        et_height = findViewById(R.id.et_height);
        et_weight = findViewById(R.id.et_weight);
        tv_path = findViewById(R.id.tv_path);
        CheckBox ck_married = findViewById(R.id.ck_married);
        ck_married.setOnCheckedChangeListener(this);
        findViewById(R.id.btn_save).setOnClickListener(this);
        if(getIntent().getBooleanExtra("is_external", false)){
            mPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/";
        }else{
            mPath = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + "/";
        }
    }

    @Override
    public void onClick(View v) {
        String height = null;
        String name = null;
        String age = null;
        String weight = null;


        if (v.getId() == R.id.btn_save) {
            name = et_name.getText().toString();
            age = et_age.getText().toString();
            height = et_height.getText().toString();
            weight = et_weight.getText().toString();
        }
        if (TextUtils.isEmpty(name)) {
            ToastUtil.show(this, "请先填写姓名");
            return;
        } else if (TextUtils.isEmpty(age)) {
            ToastUtil.show(this, "请先填写年龄");
            return;
        } else if (TextUtils.isEmpty(height)) {
            ToastUtil.show(this, "请先填写身高");
            return;
        } else if (TextUtils.isEmpty(weight)) {
            ToastUtil.show(this, "请先填写体重");
            return;
        }

        String content = String.format("　姓名：%s\n　年龄：%s\n　身高：%scm\n　体重：%skg\n　婚否：%s\n　注册时间：%s\n",
                name, age, height, weight, typeArray[isMarried ? 1 : 0], DateUtil.getNowDateTime("yyyy-MM-dd HH:mm:ss"));
        String file_path = mPath + DateUtil.getNowDateTime("") + ".txt";
        FileUtil.saveText(file_path, content); // 把字符串内容保存为文本文件
        tv_path.setText("用户注册信息文件的保存路径为：\n" + file_path);
        ToastUtil.show(this, "数据已写入存储卡文件");
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        isMarried = isChecked;
    }
}
