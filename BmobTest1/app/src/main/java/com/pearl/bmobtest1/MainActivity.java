package com.pearl.bmobtest1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends AppCompatActivity {

    private EditText mName, mFeedback;
    private Button submit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bmob.initialize(this, "your app ID key");

        mName = (EditText)findViewById(R.id.name);
        mFeedback = (EditText)findViewById(R.id.feedback);
        submit_btn = (Button)findViewById(R.id.submit_btn);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });

    }


        public void submit(){
        String name = mName.getText().toString();
        String feedback = mFeedback.getText().toString();
        if(name.equals("")||feedback.equals("")){
            return;
        }

        Feedback feedbackObj = new Feedback();
        feedbackObj.setName(name);
        feedbackObj.setFeedback(feedback);


        //写入服务器
        feedbackObj.save(this, new SaveListener() {
            @Override
            public void onSuccess() {
                Log.e("TAG", "SUCCESS");
            }

            @Override
            public void onFailure(int i, String s) {

                   Log.e("TAG", "FAIL");
            }
        });

    }
}
