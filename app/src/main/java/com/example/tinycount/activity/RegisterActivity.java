package com.example.tinycount.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tinycount.R;

public class RegisterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button buttonRegister = (Button)this.findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void register(){
        EditText editTextName = (EditText)this.findViewById(R.id.editTextRegisterName);
        EditText editTextPassword = (EditText)this.findViewById(R.id.editTextPassword);
        EditText editTextPassword2 = (EditText)this.findViewById(R.id.editTextPassword2);
        EditText editTextPrompt = (EditText)this.findViewById(R.id.editTextPrompt);
        String name = editTextName.getText().toString();
        if (name.length() < 1){
            Toast.makeText(this,"昵称不能为空.",Toast.LENGTH_LONG).show();
            return;
        }
        String pwd = editTextPassword.getText().toString();
        String pwd2 = editTextPassword2.getText().toString();
        if (pwd.length() < 1){
            Toast.makeText(this,"密码不能为空.",Toast.LENGTH_LONG).show();
            return;
        }
        if (!pwd.equals(pwd2)){
            Toast.makeText(this,"两次密码不相同.",Toast.LENGTH_LONG).show();
            return;
        }
        String prompt = editTextPrompt.getText().toString();
        //实现数据存储  sharePreference对象实现数据写入
        SharedPreferences sp = this.getSharedPreferences("tinyaccount", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name",name);
        editor.putString("password",pwd);
        editor.putString("prompt",prompt);

        editor.apply();
        finish();
        Intent intent = new Intent(this,loginActivity.class);
        startActivity(intent);
    }
}
