package com.example.popularnetworklanguage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ArrayList<Words>lst;
    private TextView tv_explanation;
    private EditText et_input;
    private ImageButton btn_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_explanation= (TextView) findViewById(R.id.tv_explanation);
        et_input= (EditText) findViewById(R.id.et_input);
        btn_search= (ImageButton) findViewById(R.id.btn_search);
        btn_search.setOnClickListener(this);
        File file=initData();
        manipulateDatabase Manipulator=new manipulateDatabase(file);
        lst=Manipulator.readDataBase();
    }
//    public void query(View v){
//        String input=et_input.getText().toString();
//        Pattern pattern=Pattern.compile(input);
//        Matcher matcher = pattern.matcher()
//        for(Words w:lst){
//            if(w.getName()==input){
//                tv_explanation.setText(w.getExplanation());
//                et_input.setText("");
//            }
//        }
//    }

    /**
     * 释放APK包里面的database文件到手机
     */
    public File initData(){
        File file =this.getDatabasePath("dictionary1_1.db");
        if(!file.exists()){
            file.getParentFile().mkdirs();
        }else{
            Log.d("app", "initData: already exits");
            return file;
        }
        BufferedInputStream Input=null;
        BufferedOutputStream Ourput=null;
        try {
            Input=new BufferedInputStream(getAssets().open("dictionary1_1.db"));
            Ourput=new BufferedOutputStream(new FileOutputStream(file));
            byte[] buffer =new byte[8000];
            int len=0;
            while ((len=Input.read(buffer))!=-1){
                Ourput.write(buffer,0,len);
                Ourput.flush();
            }
            Log.d("app", "initData: ");
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(Input!=null)Input.close();
                if(Ourput!=null)Ourput.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_search){
            String input=et_input.getText().toString();
            input =reduceWhiteSpace(input);
            Log.d("app",input);
            for(Words w:lst){
                String name=reduceWhiteSpace(w.getName());//shishi w.getname.tostring
                Pattern pattern=Pattern.compile(name);
                Matcher matcher=pattern.matcher(input);
                Log.d("app", "onClick: "+name+"\n"+input);
                if(matcher.find()){
                    Log.d("app", "onClick: "+w.getExplanation().toString());
//                    String explanation=w.getExplanation().replaceAll("(\\s{2}|\\s+)"," ");
//                    explanation.trim();
//                    char[]liist=explanation.toCharArray();
//                    String temp="";
//                    for(char c:liist) {
//                        temp += c;
//                    }
//                    Log.d("app", "onClick: "+temp);
//                    explanation=temp;
                    tv_explanation.setText("解释："+w.getExplanation());
                    et_input.setText("");
                }

//                Log.d("app", "onClick:"+w.getName());
//                Boolean judge=(input=="PPAP");
//
//                Log.d("app", "onClick: "+judge);
//                if("PPAP"==input){
//                    Log.d("app", "onClick: find correct one");
//                    tv_explanation.setText(w.getExplanation());
//                    et_input.setText("");
//                }
            }
        }
    }
    public String reduceWhiteSpace(String other){
        other.replaceAll("(\\s+)"," ");
        other.trim();
        return other;
    }
}
