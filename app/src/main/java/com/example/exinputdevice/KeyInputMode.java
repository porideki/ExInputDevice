package com.example.exinputdevice;

import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class KeyInputMode extends AppCompatActivity {

    private TcpConnect tcpConnect;
    private final String serverIP = "172.18.4.158";
    //private final String serverIP = "202.13.160.41";
    private final int serverPort = 14230;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_input_mode);

        this.tcpConnect = new TcpConnect(serverIP, serverPort);
        this.tcpConnect.start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        this.tcpConnect.Disconnect();

    }

    public void onClick_bt_sendText(View view){

        EditText editText = (EditText) findViewById(R.id.et_sendText);
        String message = ((EditText) findViewById(R.id.et_sendText)).getText().toString();

        SendText(message);

        editText.setText("");

    }

    public void onclick_consttext(View view){

        if(view == findViewById(R.id.bt_Enter)){
            this.SendText("{ENTER}");
        }else if(view == findViewById(R.id.bt_Tab)){
            this.SendText("{TAB}");
        }else if(view == findViewById(R.id.bt_Backspace)) {
            this.SendText("{BS}");
        }else if(view == findViewById(R.id.bt_Pgup)) {
            this.SendText("{PGUP}");
        }else if(view == findViewById(R.id.bt_Pgdn)) {
            this.SendText("{PGDN}");
        }else if(view == findViewById(R.id.bt_Left)) {
            this.SendText("{LEFT}");
        }else if(view == findViewById(R.id.bt_Right)) {
            this.SendText("{RIGHT}");
        }

    }

    public void SendText(String message){

        if(this.tcpConnect.socket.isConnected()) {

            Messenger messenger = new Messenger(this.serverIP, this.serverPort, message);
            messenger.start();

        }

    }

}
