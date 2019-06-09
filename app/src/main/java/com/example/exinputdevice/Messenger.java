package com.example.exinputdevice;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Messenger extends Thread {

    private String ip;
    private int port;
    private String message = "";

    public Messenger(String ip, int port, String message){
        this.ip = ip;
        this.port = port;
        this.message = message;
    }

    @Override
    public void run(){

        try{

            //ソケット作成
            Socket socket = new Socket(ip, port);

            //入出力ストリーム取得
            OutputStream outputStream = socket.getOutputStream();

            //入出力バッファ作成
            BufferedWriter bufferedWriter = new BufferedWriter( new OutputStreamWriter(outputStream, "UTF-8"));

            //編集と送信
            bufferedWriter.write(this.message);
            bufferedWriter.flush();

            Log.d("tcp", "send : " + this.message);

            //破棄
            outputStream.close();
            socket.close();

        }catch (Exception e){

            Log.d("tcp", "sendError: " + this.message);
            e.printStackTrace();

        }

    }

}
