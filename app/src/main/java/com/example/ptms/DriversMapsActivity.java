package com.example.ptms;

import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class DriversMapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener,
                com.google.android.gms.location.LocationListener{

    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    LocationRequest mLocationRequest;

    private Button LogoutDriBtn;
    private Button settingsDriBtn;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    private Boolean currentLogOutDriverStatus= false;
    private DatabaseReference AssignPasRef, AssignedPasPickUpRef;

    private String driverId, PasId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivers_maps);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        //get the online Driver Ids
        driverId = mAuth.getCurrentUser().getUid();

        LogoutDriBtn = (Button) findViewById(R.id.m_d_logout);
        settingsDriBtn = (Button) findViewById(R.id.m_d_setting);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        LogoutDriBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentLogOutDriverStatus=true;
                DisconnectTheDriver();

                mAuth.signOut();

                LogOutDriver();
            }
        });

        GetAssignedCustomerRequest();
    }

    //tell the driver where the location
    private void GetAssignedCustomerRequest()
    {
        AssignPasRef = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers").child("PasRideId");

        // get the passenger value
        AssignPasRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.exists())
                {
                    PasId = dataSnapshot.getValue().toString();
                    //get passenger current location
                    GetAssignedPasPickupLocation();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void GetAssignedPasPickupLocation()
    {
        AssignedPasPickUpRef = FirebaseDatabase.getInstance().getReference().child("passenger_requests").child(PasId).child("l");

        AssignedPasPickUpRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    List<Object> pasLocationMap = (List<Object>) dataSnapshot.getValue();
                    double LocationLat = 0;
                    double LocationLng = 0;

                    // Getting Lattitude and Longtiitude from the DB and coonvert it to  Double

                    if (pasLocationMap.get(0) != null)
                    {
                        LocationLat = Double.parseDouble(pasLocationMap.get(0).toString());
                    }
                    if (pasLocationMap.get(1) != null)
                    {
                        LocationLng = Double.parseDouble(pasLocationMap.get(1).toString());
                    }

                    // Add Marker for Driver Location
                    LatLng DriverLatLng = new LatLng(LocationLat, LocationLng);

                    mMap.addMarker(new MarkerOptions().position(DriverLatLng).title("Your Passengers are Here"));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void LogOutDriver() {
        Intent  welcomeIntent = new Intent(DriversMapsActivity.this, WelcomeActivity.class);
        welcomeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(welcomeIntent);
        finish();
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

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        buildGoogleApiClient();
        mMap.setMyLocationEnabled(true);
    }

    private void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        if(getApplicationContext()!=null){
            mLastLocation = location;

            LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());

            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(13));

            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

            DatabaseReference DriverAvailabilityref = FirebaseDatabase.getInstance().getReference().child("driversAvailable");
            GeoFire geoFireAvailable = new GeoFire(DriverAvailabilityref);

            DatabaseReference DriverWorkingref = FirebaseDatabase.getInstance().getReference().child("drivers_working");
            GeoFire geoFireWorking = new GeoFire(DriverWorkingref);

            switch (PasId)
            {
                case "": geoFireWorking.removeLocation(userId);
                    geoFireAvailable.setLocation(userId, new GeoLocation(location.getLatitude(), location.getLongitude()));
                    break;

                 default: geoFireAvailable.removeLocation(userId);
                     geoFireWorking.setLocation(userId, new GeoLocation(location.getLatitude(), location.getLongitude()));
                     break;
            }

            //geoFireAvailable.setLocation(userId, new GeoLocation(location.getLatitude(), location.getLongitude()));

            //geoFireWorking.setLocation(userId, new GeoLocation(location.getLatitude(), location.getLongitude()));
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
//        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
//        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("driversAvailable");
//
//        GeoFire geoFire = new GeoFire(ref);
//        geoFire.removeLocation(userId);
//
        if(!currentLogOutDriverStatus){
          DisconnectTheDriver();
        }
    }

    private void DisconnectTheDriver() {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("driversAvailable");

        GeoFire geoFire = new GeoFire(ref);
        geoFire.removeLocation(userId);

    }
}
