package com.example.receptorapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.receptorapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ClimaReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Usar View Binding para inflar la vista
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Registrar el BroadcastReceiver dinámicamente
        receiver = new ClimaReceiver();
        IntentFilter filter = new IntentFilter("com.example.CLIMA");
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Desregistrar el BroadcastReceiver cuando la actividad se destruya
        unregisterReceiver(receiver);
    }

    // BroadcastReceiver que recibirá y mostrará la información del clima
    public class ClimaReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.example.CLIMA")) {
                String ciudad = intent.getStringExtra("ciudad");
                String temperatura = intent.getStringExtra("temperatura");
                String clima = intent.getStringExtra("clima");

                // Mostrar los datos recibidos en los TextViews usando View Binding
                binding.txtCiudad.setText("Ciudad: " + ciudad);
                binding.txtTemperatura.setText("Temperatura: " + temperatura);
                binding.txtClima.setText("Clima: " + clima);
            }
        }
    }
}
