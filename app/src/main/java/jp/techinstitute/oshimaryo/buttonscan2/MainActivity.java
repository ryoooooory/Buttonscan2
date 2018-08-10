package jp.techinstitute.oshimaryo.buttonscan2;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = (Button) findViewById(R.id.button);
        final EditText editText = (EditText) findViewById(R.id.edittext);
        final TextView textView = (TextView) findViewById(R.id.textview);



        button.setOnClickListener(new View.OnClickListener() {      //クリック時のイベント
            @Override
            public void onClick(View view) {
                String inputtext = editText.getText().toString(); //ここに押された時の処理を記述
                textView.setText(inputtext);             //textviewにtextの内容をセット

                {
                    try {
                        FileOutputStream fileOutputStream = openFileOutput("myfile.txt", MODE_PRIVATE);
                        fileOutputStream.write(inputtext.getBytes());                   //データに書き込み
                    } catch (FileNotFoundException e) {         //例外処理
                    } catch (IOException e) {
                    }

                }
            }
        });


        {
            try {
                FileInputStream fileInputStream;
                fileInputStream = openFileInput("myfile.txt");              //ファイルの取得
                byte[] readBytes = new byte[fileInputStream.available()];
                fileInputStream.read(readBytes);
                String read = new String(readBytes);            //保存していた内容をStringにする
                String text = "こんにちは" + read;    //以前に入力していればそれを加える
                textView.setText(text);             //textviewにtextの内容をセット,以前にセットしてればこんにちはに加える

            } catch (FileNotFoundException e) {
            } catch (IOException e) {
            }


        }
    }

}
