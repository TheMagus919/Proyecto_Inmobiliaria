package com.agusoft.inmobiliaria.ui.contratos.pagos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.agusoft.inmobiliaria.modelo.Contrato;
import com.agusoft.inmobiliaria.modelo.Pago;
import com.agusoft.inmobiliaria.request.ApiClient;

import java.util.List;

public class PagosViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<List<Pago>> listaPagos;
    public PagosViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<List<Pago>> getListaPagos(){
        if(listaPagos==null){
            listaPagos=new MutableLiveData<>();
        }
        return listaPagos;
    }

    public void armarLista(Bundle bundle){
        List<Pago> list;
        Contrato contrato = (Contrato) bundle.getSerializable("contrato");
        list = ApiClient.getApi().obtenerPagos(contrato);
        listaPagos.setValue(list);
    }
}