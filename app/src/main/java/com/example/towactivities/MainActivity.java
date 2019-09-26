package com.example.towactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
private static final String LOG_TAG=MainActivity.class.getSimpleName();
public static final String EXTRA_MESSAGE="com.example.android.twoactivities.extra.MESSAGE";
public static final int TEXT_REQUEST=1;
private TextView mReplyHeaderTextView;
private TextView mReplyTextView;
private EditText mMessageEditText;
@Override
public void onSaveInstanceState(Bundle outState){
    super.onSaveInstanceState(outState);
    if(mReplyHeaderTextView.getVisibility()==View.VISIBLE){
        outState.putBoolean("reply_visible", true);
        outState.putString("repply_text",mReplyTextView.getText().toString());
    }
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG,"--------");
        Log.d(LOG_TAG,"onCreate");
        mMessageEditText=findViewById(R.id.editText_main);
        mReplyHeaderTextView=findViewById(R.id.text_header_reply);
        mReplyTextView=findViewById(R.id.text_message_reply);
if(savedInstanceState!=null){
    boolean isVisible=savedInstanceState.getBoolean("reply_visible");
    if(isVisible){
        mReplyHeaderTextView.setVisibility(View.VISIBLE);
        mReplyTextView.setText(savedInstanceState.getString("reply_text"));
        mReplyTextView.setVisibility(View.VISIBLE);
    }
}
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.d(LOG_TAG,"onStart");
    }


    public void ButtomSend(View view) {
        Log.d(LOG_TAG,"Buttom Clicked!!!");
        Intent intent=new Intent(this, SecondActivity.class);
        String message=mMessageEditText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode,data);
        if(resultCode==TEXT_REQUEST){
            if(resultCode==RESULT_OK){
                String reply=data.getStringExtra(SecondActivity.EXTRA_REPLY);
                mReplyHeaderTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setText(reply);
                mReplyTextView.setVisibility(View.VISIBLE);
            }
        }
    }

}
