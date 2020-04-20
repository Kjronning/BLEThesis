package com.example.bledatareceiver;

import android.support.v7.app.AppCompatActivity;
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
}
