package yutiantian.mylibrary.BlueTooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yutiantian.mylibrary.R;

public class BlueTooth extends AppCompatActivity {

    @Bind(R.id.btn_bluetooth)
    Button btnBluetooth;
    private BluetoothAdapter adapter;
    private String LOG_NAME="BlueTooth";
    private Context mcontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_tooth);
        ButterKnife.bind(this);
        mcontext=this;
    }

    @OnClick(R.id.btn_bluetooth)
    public void onClick() {
        adapter=BluetoothAdapter.getDefaultAdapter();
        if(adapter==null){
            Log.e(LOG_NAME,"设备不支持蓝牙");
            return;
        }
        //开启蓝牙
        if(!adapter.isEnabled()){
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            // 设置蓝牙可见性，最多300秒
            intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            mcontext.startActivity(intent);
        }
        // 设置广播信息过滤
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        intentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        intentFilter.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        // 注册广播接收器，接收并处理搜索结果
        mcontext.registerReceiver(receiver, intentFilter);
        // 寻找蓝牙设备，android会将查找到的设备以广播形式发出去
//        adapter=BluetoothAdapter.getDefaultAdapter();
        adapter.startDiscovery();
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                System.out.println(device.getName()+" address: "+device.getAddress());
                if(device.getAddress().equals("8C:DE:52:AC:36:CA")){
                    Log.e(LOG_NAME,"stop");
                    adapter.cancelDiscovery();
                }
            }
        }
    };



}
