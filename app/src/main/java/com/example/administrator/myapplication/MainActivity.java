package com.example.administrator.myapplication;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {
    public static final String RECO_UUID = "24DDF411-8CF1-440C-87CD-E368DAF9C93E";
    public static final boolean SCAN_RECO_ONLY = true;                          // RECO 비콘만 스캔할 것인지
    public static final boolean ENABLE_BACKGROUND_RANGING_TIMEOUT = true;   // ranging 실행 후 10초 후 자동 정지
    public static final boolean DISCONTINUOUS_SCAN = false;
    private static final int REQUEST_ENABLE_BT = 1;

    private BluetoothManager mBluetoothManager;
    private BluetoothAdapter mBluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = mBluetoothManager.getAdapter();

        if(mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
            Intent enableBTIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBTIntent, REQUEST_ENABLE_BT);     // 블루투스 켜기 요청
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ENABLE_BT && resultCode == Activity.RESULT_CANCELED) {
            //사용자가 블루투스 요청을 허용하지 않았을 경우, 어플리케이션은 종료됩니다.
            finish();
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    protected void onResume() {
        Log.i("MainActivity", "onResume()");
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        Log.i("MainActivity", "onDestroy");
        super.onDestroy();
    }

    public void onButtonClicked(View v) {
        Button btn = (Button)v;

        if(btn.getId() == R.id.listButton) {
            final Intent intent = new Intent(this, BeaconListActivity.class);
            startActivity(intent);
        }
    }
}
