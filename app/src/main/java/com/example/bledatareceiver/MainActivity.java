package com.example.bledatareceiver;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ContractView {

    TextView xAxis, yAxis, informationTextView;
    Button addXButton, addYButton, subtractXButton, subtractYButton, discoveryButton;
    MainPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter(this);
        informationTextView = findViewById(R.id.informationTextView);

        discoveryButton = findViewById(R.id.discoveryButton);
        discoveryButton.setOnClickListener(v -> presenter.handleDiscoveryButtonPress());

        xAxis = findViewById(R.id.xAxis);
        yAxis = findViewById(R.id.yAxis);

        addXButton = findViewById(R.id.addXButton);
        addXButton.setOnClickListener(v -> presenter.handleAddXButtonPress());

        addYButton = findViewById(R.id.addYButton);
        addYButton.setOnClickListener(v -> presenter.handleAddYButtonPress());

        subtractXButton = findViewById(R.id.subtractXButton);
        subtractXButton.setOnClickListener(v -> presenter.handleSubtractXButtonPress());

        subtractYButton = findViewById(R.id.subtractYButton);
        subtractYButton.setOnClickListener(v -> presenter.handleSubtractYButtonPress());

        askLocationPermission();
    }

    private void askLocationPermission() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        if(checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
    }


    @Override
    public void showToast(String text) {
        Toast.makeText(this,text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setXCoordinate(int x) {
        xAxis.setText(x+"");
    }

    @Override
    public void setYCoordinate(int y) {
        yAxis.setText(y+"");
    }

    @Override
    public void changeInformationTextView(String text) {
        informationTextView.setText(text);
    }

    @Override
    public void sendEmailIntent(String data) {
        Intent email = new Intent(Intent.ACTION_SENDTO);
        email.putExtra(Intent.EXTRA_EMAIL, new String [] {"kjronning@gmail.com"});
        email.putExtra(Intent.EXTRA_SUBJECT,"BLEData");
        email.putExtra(Intent.EXTRA_TEXT, data);
        Intent chooser = Intent.createChooser(email, "Mail to ..");
        if (email.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        }
    }
}
