package com.slimgreen.davis.slimgreen;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.slimgreen.davis.slimgreen.Modelo.Servicio;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener{

    private GoogleMap mMap;
    private ArrayList<Servicio> servicios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent i = getIntent();
        servicios = (ArrayList<Servicio>) i.getSerializableExtra("servicios");
        Toast.makeText(MapsActivity.this, "Servicios encontrados : " + servicios.size(), Toast.LENGTH_SHORT).show();

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for(Servicio s :servicios){
            LatLng coordenadas = new LatLng(s.getLatitud(), s.getLongitud());
            builder.include(coordenadas);
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(coordenadas)
                    .title(s.getNombre())
                    .snippet(s.getDireccion())
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.store)));
            marker.setTag(s);
        }
        /*LatLng sydney = new LatLng(4.689303, -74.039898);
        Marker marker = mMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title("Restaurante Zoe")
                .snippet("ABIERTO")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.store)));
        marker.setTag("hola amigo :v");
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));

        mMap.setOnInfoWindowClickListener(this);*/
        LatLngBounds bounds = builder.build();
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        int padding = (int) (width * 0.10); // offset from edges of the map 10% of screen

        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding);
        mMap.animateCamera(cu);

        /*LatLng c = new LatLng(servicios.get(0).getLatitud(), servicios.get(0).getLongitud());
        mMap.moveCamera(CameraUpdateFactory.newLatLng(c));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));*/

        mMap.setOnInfoWindowClickListener(this);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Servicio servicioClicked = (Servicio)marker.getTag();
        Intent i = new Intent(MapsActivity.this, DetalleServicio.class);
        i.putExtra("servicio", servicioClicked);
        startActivity(i);


        //Toast.makeText(MapsActivity.this, "Marker TAG: " + servicioClicked.getNombre(), Toast.LENGTH_SHORT).show();
    }
}
