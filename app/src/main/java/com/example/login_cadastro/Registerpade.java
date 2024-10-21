package com.example.login_cadastro;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Registerpade extends AppCompatActivity {

    TextInputEditText editTextemail, editTextsenha;

    Button cadastro;
    TextView login;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registerpade);

        editTextemail = findViewById(R.id.Email);
        editTextsenha = findViewById(R.id.Senha);
        login = findViewById(R.id.Aqui);
        cadastro = findViewById(R.id.Butao);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registerpade.this, MainActivity.class);
                startActivity(intent);
                finish();
            }


        });

        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email,senha;
                email = String.valueOf(editTextemail.getText());
                senha = String.valueOf(editTextsenha.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Registerpade.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(senha)){
                    Toast.makeText(Registerpade.this, "Enter senha", Toast.LENGTH_SHORT).show();
                    return;
                }
                firebaseAuth.createUserWithEmailAndPassword(email,senha)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()){
                                    Toast.makeText(Registerpade.this, "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                                    Intent Intent = new Intent(Registerpade.this, MainActivity.class);
                                    startActivity(Intent);
                                    finish();
                                }
                                else {
                                    Toast.makeText(Registerpade.this, "Erro ao cadastrar", Toast.LENGTH_SHORT).show();
                                }

                            }

                        });
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}