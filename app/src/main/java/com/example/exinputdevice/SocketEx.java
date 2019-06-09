package com.example.exinputdevice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketEx extends AppCompatActivity implements View.OnClickListener {

    private final static String BR=//改行
            System.getProperty("line.separator");
    private final static String IP=//IPアドレスの指定
            "60.130.251.105";
    private TextView lblReceive;//受信ラベル
    private EditText edtSend;	//送信エディットテキスト
    private Button	 btnSend;	//送信ボタン

    private Socket		 socket; //ソケット
    private InputStream  in;		//入力ストリーム
    private OutputStream out;	//出力ストリーム

    private final Handler handler=new Handler();//ハンドラ

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_socket_ex);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //レイアウトの生成
        LinearLayout layout=new LinearLayout(this);
        layout.setBackgroundColor(Color.rgb(255, 255, 255));
        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout);

        //送信エディットテキストの生成
        edtSend=new EditText(this);
        //edtSend.setId(0);
        edtSend.setText("",TextView.BufferType.NORMAL);
        setLLParams(edtSend,
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(edtSend);

        //送信ボタンの生成
        btnSend=new Button(this);
        btnSend.setText("送信");
        btnSend.setOnClickListener(this);
        setLLParams(btnSend);
        layout.addView(btnSend);

        //受信ラベルの生成
        lblReceive=new TextView(this);
        //lblReceive.setId(1);
        lblReceive.setText("");
        lblReceive.setTextColor(Color.rgb(0, 0, 0));
        setLLParams(lblReceive,
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(lblReceive);

    }

    //アプリの開始
    @Override
    public void onStart(){
        super.onStart();

        //スレッド生成
        (new Thread(){public void run(){
            try{
                connect(IP,8080);
            }catch(Exception e){
            }
        }}).start();
    }

    //アプリの停止
    @Override
    public void onStop(){
        super.onStop();
        disconnect();
    }

    //受信テキストの追加
    private void addText(final String text){
        //ハンドラによるユーザーインタフェース操作
        handler.post(new Runnable(){
            public void run(){
                lblReceive.setText(text+
                        BR+lblReceive.getText());
            }
        });
    }

    //接続
    private void connect(String ip,int port){
        int size;
        String str;
        byte[] w=new byte[1024];
        try{
            //ソケット通信
            addText("接続中");
            socket=new Socket(ip,port);
            in =socket.getInputStream();
            out=socket.getOutputStream();
            addText("接続完了");

            //受信ループ
            while(socket!=null && socket.isConnected()){
                //データの受信
                size=in.read(w);
                if(size<=0)continue;
                str=new String(w,0,size,"UTF-8");

                //ラベルへの文字列追加
                addText(str);
            }
        }catch(Exception e){
            addText("通信失敗しました");
        }
    }

    //切断
    private void disconnect(){
        try{
            socket.close();
            socket=null;
        }catch(Exception e){
        }
    }

    //ボタンクリックイベントの処理
    public void onClick(View v){
        if(v==btnSend){
            try{
                //データの送信
                if(socket!=null && socket.isConnected()){
                    byte[] w=edtSend.getText().toString().getBytes("UTF8");
                    out.write(w);
                    out.flush();
                    edtSend.setText("",TextView.BufferType.NORMAL);
                }
            }catch(Exception e){
                addText("通信失敗しました");
            }
        }
    }

    //ライナーレイアウトのパラメータ指定
    private static void setLLParams(View view){
        view.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
    }

    //ライナーレイアウトのパラメータ指定
    private static void setLLParams(View view,int w, int h){
        view.setLayoutParams(new LinearLayout.LayoutParams(w,h));
    }

}
