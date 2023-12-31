package com.agusoft.inmobiliaria.ui.inmuebles.detalles;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agusoft.inmobiliaria.R;
import com.agusoft.inmobiliaria.databinding.FragmentDetallesInmueblesBinding;
import com.agusoft.inmobiliaria.modelo.Inmueble;
import com.agusoft.inmobiliaria.modelo.Inquilino;
import com.agusoft.inmobiliaria.modelo.Propietario;
import com.agusoft.inmobiliaria.request.ApiClient;
import com.agusoft.inmobiliaria.ui.inmuebles.detalles.DetallesInmueblesViewModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class DetallesInmueblesFragment extends Fragment {
    private DetallesInmueblesViewModel dm;
    private Context context;
    private FragmentDetallesInmueblesBinding binding;
    private Inmueble inmuebleActual=null;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        dm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(DetallesInmueblesViewModel.class);
        binding = FragmentDetallesInmueblesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Bundle bundle = getArguments();
        dm.getInmuebleM().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble inmueble) {
                binding.textCodigoInmueble.setText(inmueble.getIdInmueble()+"");
                binding.textAmbientesInmueble.setText(inmueble.getAmbientes()+"");
                binding.textDireInmueble.setText(inmueble.getDireccion());
                binding.textPrecioIn.setText("$"+inmueble.getPrecio());
                binding.textUsoInmueble.setText(inmueble.getUso());
                binding.textTipoInmueble.setText(inmueble.getTipo());
                Glide.with(DetallesInmueblesFragment.this).load(inmueble.getImagen()).into(binding.imgInmueble);
                binding.cbDisponible.setChecked(inmueble.isEstado());
                inmuebleActual = inmueble;
            }
        });
        binding.cbDisponible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean disp = binding.cbDisponible.isChecked();
                inmuebleActual.setEstado(disp);
                dm.actualizarInmueble(inmuebleActual);
            }
        });
        dm.obtenerInformacionInmueble(bundle);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}