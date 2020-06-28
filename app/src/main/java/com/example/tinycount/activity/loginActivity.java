package com.example.tinycount.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tinycount.MainActivity;
import com.example.tinycount.R;

public class loginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText editTextName,editTextPwd;
    TextView textViewRegister;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = (Button) this.findViewById(R.id.buttonLogin);
        editTextName = (EditText) findViewById(R.id.editTextLoginName);
        editTextPwd = (EditText) findViewById(R.id.editTextLoginPassword);
        textViewRegister = (TextView)findViewById(R.id.textViewRegister);

        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Register();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });

        SharedPreferences sp = this.getSharedPreferences("tinyaccount", Context.MODE_PRIVATE);
        String name = sp.getString("name",null);
        editTextName.setText(name);

    }

    private void Register(){
        Intent intent = new Intent();//显示intent，是一个具体的页面
        intent.setClass(this,RegisterActivity.class);
        startActivity(intent);
    }

    private void Login(){
        String name = editTextName.getText().toString(); //获取输入信息
        String pwd = editTextPwd.getText().toString();

        SharedPreferences sp = this.getSharedPreferences("tinyaccount", Context.MODE_PRIVATE);
        String password = sp.getString("password",null);
        if(password==null || !pwd.equals(password)){
            Toast.makeText(this,"登陆失败",Toast.LENGTH_LONG).show();
        }else {
            this.finish();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        }
    }
}
