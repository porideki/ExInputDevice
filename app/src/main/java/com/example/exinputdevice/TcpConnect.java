package com.example.exinputdevice;

//メイン実装
import java.io.*;
import java.net.Socket;
//デバッグ
import android.util.Log;

public class TcpConnect extends Thread {

    private String ip;
    private int port;

    TcpConnect(String ip, int port){
        this.ip = ip;
        this.port = port;
    }

    @Override
    public void run(){

        try{

            //ソケット作成
            Socket socket = new Socket(this.ip, this.port);

            //入出力ストリーム取得
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            //入出力バッファ作成
            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream, "UTF-8"));
            BufferedWriter bufferedWriter = new BufferedWriter( new OutputStreamWriter(outputStream, "UTF-8"));

            //編集と送信
            bufferedWriter.write("Hello, Tcp.\n");
            bufferedWriter.flush();

            //受信
            String receivedData = bufferedReader.readLine();

            //受信データ表示
            Log.d("tcp", "Received: " + receivedData);

            //破棄
            inputStream.close();
            outputStream.close();
            socket.close();

        }catch (Exception e){

            e.printStackTrace();

        }

    }

}
