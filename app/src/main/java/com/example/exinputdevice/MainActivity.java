package com.example.exinputdevice;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.IntentFilter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //BlueToothConnect
        //BlueToothがサポートされているか
        /*
        this.BTAdapter = BluetoothAdapter.getDefaultAdapter();
        if(this.BTAdapter != null) Log.d("SelfLog","Bluetoothはサポートされています");
        else  Log.d("SelfLog","Bluetoothがサポートされていません");

        //BlueToothが有効化されているか
        if(!this.BTAdapter.isEnabled()){
            //Bluetooth有効化インテント
            Intent reqEnableBTIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(reqEnableBTIntent,REQUEST_ENABLE_BT);
        }
        */

        //SS操作
        new Handler().postDelayed(new Runnable() {  //二秒後にアクティビティを移動
            @Override
            public void run() {
                Intent it = new Intent(getApplicationContext(), KeyInputMode.class);
                startActivity(it);
            }
        }, 2000);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /*
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == RESULT_OK) { //BlueTooth有効化インテントからBlueToothが有効化された
                Log.d("SelfLog","BlueToothが有効化されました");
                btBroadcastReceiver = new BtBroadcastReceiver();

                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
                registerReceiver(btBroadcastReceiver, intentFilter);

                if (this.BTAdapter.startDiscovery()) {
                    // 発見開始（結果はブロードキャストで取得）
                } else {
                    // 発見開始できず。Bluetooth が無効であるなど。
                }
            }else{
                Log.d("SelfLog","BlueToothが有効化されませんでした");
            }
        }
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
