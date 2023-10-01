package com.agusoft.inmobiliaria.ui.inmuebles.detalles;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.agusoft.inmobiliaria.modelo.Inmueble;
import com.agusoft.inmobiliaria.request.ApiClient;

public class DetallesInmueblesViewModel extends AndroidViewModel {
    private MutableLiveData<Inmueble> inmuebleM;
    private Context context;
    public DetallesInmueblesViewModel(@NonNull Application application) {
        super(application);
        this.context= application.getApplicationContext();
    }
    public LiveData<Inmueble> getInmuebleM() {
        if(inmuebleM == null){
            inmuebleM = new MutableLiveData<>();
        }
        return inmuebleM;
    }

    public void actualizarInmueble(Inmueble inmueble){
        ApiClient.getApi().actualizarInmueble(inmueble);
    }
    public void obtenerInformacionInmueble(Bundle bundle){
        Inmueble inmueble= (Inmueble) bundle.get("inmueble");
        inmuebleM.setValue(inmueble);
    }
    public void guardarimg(String s){
        SharedPreferences sp = context.getSharedPreferences("info.xml",0);
        SharedPreferences.Editor editor= sp.edit();
        editor.putString("img",s);
        editor.commit();
    }
}