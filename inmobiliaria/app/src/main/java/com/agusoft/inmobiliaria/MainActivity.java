package com.agusoft.inmobiliaria;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.agusoft.inmobiliaria.databinding.ActivityMainBinding;
import com.agusoft.inmobiliaria.request.ApiClient;

public class MainActivity extends AppCompatActivity {

    private LoginViewModel lg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ApiClient api = ApiClient.getApi();
        solicitarPermisos();
        lg = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginViewModel.class);
        lg.getResultado().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean bool) {
                if(bool){
                    binding.edEmail.setText("");
                    binding.edPassword.setText("");
                    binding.txErrorEmail.setVisibility(View.INVISIBLE);
                }else{
                    binding.txErrorEmail.setVisibility(View.VISIBLE);
                }

            }
        });
        binding.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lg.login(binding.edEmail.getText().toString(),binding.edPassword.getText().toString());
            }
        });
    }

    public void solicitarPermisos(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M
                && (checkSelfPermission(ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED) ||
                (checkSelfPermission(ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED)){
            requestPermissions(new String[]{ACCESS_FINE_LOCATION,ACCESS_COARSE_LOCATION},1000);
        }
    }
}