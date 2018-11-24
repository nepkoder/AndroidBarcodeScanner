package com.example.newarbhai.nepkoderbarcode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class MainActivity extends AppCompatActivity implements OnClickListener {
    private Button scanBtn;
    private TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar();
        setContentView(R.layout.activity_main);
        scanBtn = (Button) findViewById(R.id.scanbutton);
        display = (TextView) findViewById(R.id.displayValue);
        scanBtn.setOnClickListener(this);

    }

    public void onClick(View v) {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setPrompt("Scan a barcode");
        integrator.setOrientationLocked(false);
        integrator.setCaptureActivity(CaptureActivityPortrait.class);
        integrator.setBeepEnabled(true);
        integrator.initiateScan();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result.getContents() == null || result.getContents().equals("")) {
            Toast.makeText(getBaseContext(), "Cancel.. done", Toast.LENGTH_LONG).show();
        } else if (result.getContents() != null) {
            String scanContent = result.getContents();
            String scanFormat = result.getFormatName();

            display.setText("Code: " +scanContent + " format: " +scanFormat);

            Toast.makeText(this, "Code " +scanContent, Toast.LENGTH_LONG).show();

//            IntentIntegrator integrator = new IntentIntegrator(this);
//            integrator.setPrompt("Scan a barcode");
//            integrator.setOrientationLocked(false);
//            integrator.initiateScan();


        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }

    }



}
