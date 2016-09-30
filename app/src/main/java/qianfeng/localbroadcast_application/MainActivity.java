package qianfeng.localbroadcast_application;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/*
这种 LocalBroadcast的特点是： 只能通过动态注册，不能静态注册。
        而且只是本App内发送广播和接收广播有效，其他的App则看不到这些广播。
        是实现本App内的广播通信，只在本App中有效
 */
public class MainActivity extends AppCompatActivity {

    private LocalBroadcastManager manager;
    private TextView tv;
    private BroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = ((TextView) findViewById(R.id.tv));

        manager = LocalBroadcastManager.getInstance(this); // 只能用getInstance()获取一个LocalBroadcastManager实例
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.111");
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                tv.setText("android.1111");
            }
        };
        manager.registerReceiver(receiver,filter);
    }

    public void sendLocalBroadcast(View view) {
        manager.sendBroadcast(new Intent("android.111"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.unregisterReceiver(receiver);
    }
}
