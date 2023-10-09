package com.agusoft.inmobiliaria;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.hardware.SensorEvent;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.agusoft.inmobiliaria.modelo.Propietario;
import com.agusoft.inmobiliaria.request.ApiClient;

public class LoginViewModel extends AndroidViewModel {
    private Context context;
    private int mov=0;
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
                Intent intent = new Intent(context, OpcionesActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                autenticado = true;
            }else{
                autenticado = false;
            }
            resultado.setValue(autenticado);
        }
    }

    public void getSensorLlamada(SensorEvent sensorEvent){
        if(sensorEvent.values[0]<-6&& mov==0){
            mov++;
        }else{
            if(sensorEvent.values[0]>6&&mov==1){
                mov++;
            }
        }
        if(mov==2){
            mov=0;
            Intent in = new Intent(Intent.ACTION_CALL);
            in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            in.setData(Uri.parse("tel:2664553747"));
            context.startActivity(in);
        }
    }

}
