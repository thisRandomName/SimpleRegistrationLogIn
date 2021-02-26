package com.example.simpleregandlogin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText firstName,lastName,username,password,bio;
    Button submit,login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstName = findViewById(R.id.view);
        lastName = findViewById(R.id.view2);
        bio = findViewById(R.id.view3);
        username = findViewById(R.id.view4);
        password = findViewById(R.id.view5);
        submit = findViewById(R.id.button);
        login=findViewById(R.id.button2);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create new user
                UserEntity userEntity = new UserEntity();
                //set new user's input
                userEntity.setFirst_name(firstName.getText().toString());
                userEntity.setLast_name(lastName.getText().toString());
                userEntity.setBio(bio.getText().toString());
                userEntity.setUsername(username.getText().toString());
                userEntity.setPassword(password.getText().toString());

                //validation
                if(validateInput(userEntity)){
                    //initialize database
                    UserDatabase userDatabase=UserDatabase.getUserDatabase(getApplicationContext());
                    //initialize userDao
                    UserDao userDao=userDatabase.userDao();
                    //insert operation on thread
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            userDao.insert(userEntity);
                            Toast.makeText(MainActivity.this,"New user successfully registered!!",Toast.LENGTH_SHORT).show();

                        }

                    }).start();


                }else{
                    Toast.makeText(MainActivity.this,"Please fill in ALL FIELDS!!",Toast.LENGTH_SHORT).show();
                }
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Login.class));
            }
        });
    }

    private Boolean validateInput(UserEntity userEntity){
        if(userEntity.getFirst_name().isEmpty()||userEntity.getLast_name().isEmpty()||userEntity.getBio().isEmpty()||userEntity.getUsername().isEmpty()||userEntity.getPassword().isEmpty()){
            return false;
        }
        return true;
    }

    ///

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);


    }




}