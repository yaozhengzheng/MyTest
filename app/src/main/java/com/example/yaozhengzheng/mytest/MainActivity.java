package com.example.yaozhengzheng.mytest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * checkbox单选多选的实例
 */
public class MainActivity extends AppCompatActivity {


    @InjectView(R.id.cb_hobby1)
    CheckBox cbHobby1;
    @InjectView(R.id.cb_hobby2)
    CheckBox cbHobby2;
    @InjectView(R.id.cb_hobby3)
    CheckBox cbHobby3;
    @InjectView(R.id.btn_true)
    Button btnTrue;
    @InjectView(R.id.cb_hobby4)
    CheckBox cbHobby4;
    @InjectView(R.id.tv_show)
    TextView tvShow;

    /**
     * 绑定状态切换事件
     *
     * @param savedInstanceState
     */

    private MyCheckedChangeListener listener = new MyCheckedChangeListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        cbHobby1.setOnCheckedChangeListener(listener);
        cbHobby2.setOnCheckedChangeListener(listener);
        cbHobby3.setOnCheckedChangeListener(listener);
    }

    /**
     * 确定按钮跟多选框点击事件
     *
     * @param view
     */
    @OnClick({R.id.btn_true, R.id.cb_hobby4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_true:
                String str = checkedString();
                Toast.makeText(this, "当前选中的是：" + str, Toast.LENGTH_SHORT).show();
                tvShow.setText(str);
                break;
            case R.id.cb_hobby4:
                boolean bl = cbHobby4.isChecked();
                cbHobby1.setChecked(bl);
                cbHobby2.setChecked(bl);
                cbHobby3.setChecked(bl);
                break;
        }
    }


    /**
     * 以内部类的形式实现选中状态改变的监听事件
     */
    class MyCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            /*CheckBox cb = (CheckBox) compoundButton;
            String str = cb.getText().toString();
            if (isChecked) {
                Toast.makeText(MainActivity.this, "当前选中的是：" + str, Toast.LENGTH_SHORT).show();
            }*/
            if (cbHobby1.isChecked() && cbHobby2.isChecked() && cbHobby3.isChecked()) {
                cbHobby4.setChecked(true);
            } else {
                cbHobby4.setChecked(false);
            }
        }
    }


    /**
     * 获取checkbox中最终选择的文本，给出提示
     */
    public String checkedString() {
        StringBuilder sb = new StringBuilder();
        if (cbHobby1.isChecked()) {
            sb.append(cbHobby1.getText().toString() + ",");
        }
        if (cbHobby2.isChecked()) {
            sb.append(cbHobby2.getText().toString() + ",");
        }
        if (cbHobby3.isChecked()) {
            sb.append(cbHobby3.getText().toString() + ",");
        }
        return sb.toString();
    }

}
