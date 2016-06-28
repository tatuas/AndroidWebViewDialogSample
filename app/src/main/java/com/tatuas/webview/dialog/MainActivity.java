package com.tatuas.webview.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class MainActivity extends AppCompatActivity {

    private final static int REQUEST_CODE_FILE_PATH = 12345;

    private Button openFileButton;
    private Button openGoogleButton;
    private Button startButton;
    private EditText urlEditText;
    private SwitchCompat enableJavaScriptSwitchCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openFileButton = (Button) findViewById(R.id.open_file_button);
        startButton = (Button) findViewById(R.id.start_button);
        openGoogleButton = (Button) findViewById(R.id.open_google_button);
        urlEditText = (EditText) findViewById(R.id.url_edit_text);
        enableJavaScriptSwitchCompat = (SwitchCompat) findViewById(R.id.enable_javascript_switch_compat);

        urlEditText.setSelection(urlEditText.getText().length());
        openGoogleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urlEditText.setText("http://www.google.com/");
            }
        });
        openFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("file/*");
                startActivityForResult(intent, REQUEST_CODE_FILE_PATH);
            }
        });
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(WebViewDialogActivity.createIntent(
                        MainActivity.this,
                        urlEditText.getText().toString(),
                        enableJavaScriptSwitchCompat.isChecked()));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_FILE_PATH) {
            try {
                urlEditText.setText(URLDecoder.decode(data.getDataString(), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }
}
