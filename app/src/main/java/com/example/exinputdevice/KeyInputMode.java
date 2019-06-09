package com.example.exinputdevice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;

public class KeyInputMode extends AppCompatActivity {

    private TcpConnect tcpConnect;
    private final String serverIP = "192.168.3.64";
    private final int serverPort = 14230;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_input_mode);

        this.tcpConnect = new TcpConnect(serverIP, serverPort);
        this.tcpConnect.start();

    }
}
