package com.agusoft.inmobiliaria.ui.inquilinos;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.agusoft.inmobiliaria.modelo.Inmueble;
import com.agusoft.inmobiliaria.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

public class InquilinosViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<List<Inmueble>> listamutable;
    public InquilinosViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<List<Inmueble>> getListaMutable(){
        if(listamutable==null){
            listamutable=new MutableLiveData<>();
        }
        return listamutable;
    }

    public void armarLista(){
        List<Inmueble> list;
        list = ApiClient.getApi().obtenerPropiedadesAlquiladas();
        listamutable.setValue(list);
    }
}