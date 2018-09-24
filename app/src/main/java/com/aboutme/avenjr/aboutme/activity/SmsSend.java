package com.aboutme.avenjr.aboutme.activity;
import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.TextView;

import com.aboutme.avenjr.aboutme.R;
import com.aboutme.avenjr.aboutme.view.DialogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aboutme.avenjr.aboutme.R;

public class SmsSend extends AppCompatActivity {

    private static final int PERMITION_SEMS = 1;
    @BindView(R.id.btn_send)
    Button btnSend;

    @BindView(R.id.txt_message)
    TextView txtMessage;

    String SENT = "SMS_SENT";
    String DELIVERED = "SMS_DELIVERED";
    PendingIntent sentPI, deliveredPI;
    BroadcastReceiver sentReceiver, smsDeliverdReveiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_send);
        ButterKnife.bind(this);

        sentPI = PendingIntent.getBroadcast(this,0,new Intent(SENT),0);
        deliveredPI = PendingIntent.getBroadcast(this,0,new Intent(DELIVERED),0);

    }

    @OnClick(R.id.btn_send)
    public void sendSms(){
        String sms = txtMessage.getText().toString();
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS}, PERMITION_SEMS);
        } else {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("+919604780735","7875430906",sms,sentPI,deliveredPI);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sentReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()){
                    case Activity.RESULT_OK:
                        DialogUtil.errorDialog(SmsSend.this,"Sms Send");
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        DialogUtil.errorDialog(SmsSend.this,"Generaic failure");
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        DialogUtil.errorDialog(SmsSend.this,"No Service");
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                            DialogUtil.errorDialog(SmsSend.this,"Null PDU");
                            break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        DialogUtil.errorDialog(SmsSend.this,"Radio Off");
                        break;
                }
            }
        };

        smsDeliverdReveiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()){
                    case Activity.RESULT_OK:
                        DialogUtil.errorDialog(SmsSend.this,"Sms Delivered");
                        break;
                    case Activity.RESULT_CANCELED:
                        DialogUtil.errorDialog(SmsSend.this,"SMS not delivered");
                        break;
                }
            }
        };

        registerReceiver(smsDeliverdReveiver,new IntentFilter(DELIVERED));
        registerReceiver(sentReceiver,new IntentFilter(SENT));

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(sentReceiver);
        unregisterReceiver(smsDeliverdReveiver);
    }
}


