package com.example.william.blueeye;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tvTarget;
    TextView tvLastScore;
    TextView tvTotalScore;
    TextView tvIndex;
    SeekBar  sbBulsseye;
    Button   btnOk;
    ImageButton btnHelp;
    ImageButton   btnReplay;

    Context mContext;

    int randomScore;
    int finallyScore = 0;
    int index = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        findView();
        randomOfScore();
        setListener();
    }

    private void findView() {
        tvTarget = (TextView) findViewById(R.id.tv_target);
        tvLastScore = (TextView) findViewById(R.id.tv_last_score);
        tvTotalScore = (TextView) findViewById(R.id.tv_total_score);
        tvIndex = (TextView) findViewById(R.id.tv_index);
        sbBulsseye = (SeekBar) findViewById(R.id.sb_bulsseye);
        btnOk = (Button) findViewById(R.id.btn_ok);
        btnHelp = (ImageButton) findViewById(R.id.btn_help);
        btnReplay = (ImageButton) findViewById(R.id.btn_replay);
    }

    private void setListener() {
        btnOk.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int currentScore = sbBulsseye.getProgress();
                int score = 100 - Math.abs(currentScore - randomScore);
                finallyScore += score;
                tvLastScore.setText("第" + index + "局得分：" + score);
                tvTotalScore.setText("总得分：" + finallyScore);
                tvIndex.setText("现在是第" + ++index + "局");
                sbBulsseye.setProgress(0);
                randomOfScore();
            }
        });

        btnReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randomOfScore();
                setViewText();
            }
        });

        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("帮助").setMessage("大家好，这是我的第一个安卓小程序，完全照着视频教程写的，嘿嘿，以后我会时不时地添加自己的新功能哒~~").setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private void setViewText() {
        sbBulsseye.setProgress(0);
        tvLastScore.setText("上一局得分：0");
        tvTotalScore.setText("总得分：0");
        tvIndex.setText("第1局");
        index = 1;
        finallyScore = 0;
        randomOfScore();
    }



    private void randomOfScore(){
        Random random = new Random();
        randomScore = (int) (Math.sqrt(300) * random.nextGaussian() + 50) % 100 + 1;
        tvTarget.setText("小样，把进度条拖到" + randomScore);
    }
}
