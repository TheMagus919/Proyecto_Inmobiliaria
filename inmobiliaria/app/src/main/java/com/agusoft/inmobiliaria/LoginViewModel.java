package com.agusoft.inmobiliaria;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.agusoft.inmobiliaria.modelo.Propietario;
import com.agusoft.inmobiliaria.request.ApiClient;

public class LoginViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Boolean> resultado = new MutableLiveData<>();
    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public MutableLiveData<Boolean> getResultado() {
        if(resultado == null){
            resultado = new MutableLiveData<>();
        }
        return resultado;
    }

    public void login(String usuario, String contraseña) {
        boolean autenticado;
        ApiClient api = ApiClient.getApi();
        if(!usuario.isEmpty() && !contraseña.isEmpty()){
            Propietario pro = api.login(usuario,contraseña);
            if(pro !=null){
                Intent intent = new Intent(context,opciones.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                autenticado = true;
            }else{
                autenticado = false;
            }
            resultado.setValue(autenticado);
        }
    }
}
