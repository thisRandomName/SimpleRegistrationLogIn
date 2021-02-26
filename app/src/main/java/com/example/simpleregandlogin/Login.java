package com.example.simpleregandlogin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText u_name,pass;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        u_name=findViewById(R.id.username);
        pass=findViewById(R.id.pass);
        login=findViewById(R.id.button3);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final String userName=u_name.getText().toString();
               final String passWord=pass.getText().toString();

                if(userName.isEmpty()||passWord.isEmpty()){
                    Toast.makeText(Login.this,"Empty fields!!",Toast.LENGTH_SHORT).show();
                }else{
                    UserDatabase userDatabase=UserDatabase.getUserDatabase(getApplicationContext());
                    final UserDao userDao=userDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            UserEntity userEntity=userDao.login(userName,passWord);
                            if(userEntity==null){
                                runOnUiThread(new Runnable(){
                                    @Override
                                    public void run(){
                                        Toast.makeText(Login.this,"invalid credentials",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }else{
                                Toast.makeText(Login.this,"LOGIN OK !!!!!!!!!!!",Toast.LENGTH_SHORT).show();

                            }
                        }
                    }).start();
                }



            }
        });
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);


    }
}