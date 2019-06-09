package com.example.exinputdevice;

//メイン実装
import java.io.*;
import java.net.Socket;
//デバッグ
import android.util.Log;

public class TcpConnect extends Thread {

    private String ip;
    private int port;

    public Socket socket;
    public InputStream inputStream;
    public OutputStream outputStream;
    public BufferedReader bufferedReader;
    public BufferedWriter bufferedWriter;

    //コンストラクタ
    TcpConnect(String ip, int port){
        this.ip = ip;
        this.port = port;
    }

    //通信開始
    @Override
    public void run(){

        try{

            //ソケット作成
            this.socket = new Socket(this.ip, this.port);

            //入出力ストリーム取得
            this.inputStream = socket.getInputStream();
            this.outputStream = socket.getOutputStream();

            //入出力バッファ作成
            this.bufferedReader = new BufferedReader( new InputStreamReader(inputStream, "UTF-8"));
            this.bufferedWriter = new BufferedWriter( new OutputStreamWriter(outputStream, "UTF-8"));

            //受信
            String receivedData = bufferedReader.readLine();

            //受信データ表示
            Log.d("tcp", "Received: " + receivedData);

        }catch (Exception e){

            e.printStackTrace();

        }

    }

    public void Disconnect(){

        try{

            this.inputStream.close();
            this.outputStream.close();
            this.socket.close();

            Log.d("tcp", "Disconnect.");

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void SendText(String str){

        try{

            OutputStream loutputStream = this.socket.getOutputStream();
            BufferedWriter lbufferedWriter = new BufferedWriter( new OutputStreamWriter(loutputStream, "UTF-8"));

            lbufferedWriter.write(str);
            lbufferedWriter.flush();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
