package com.simplepermissionutil;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.permissionutil.PermissionListener;
import com.permissionutil.PermissionUtil;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        applyPermission();
    }


    //申请权限
    private void applyPermission(){
        findViewById(R.id.tv_checkPermission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 点击检查 相机、打电话 权限
                 */
                PermissionUtil permissionUtil = new PermissionUtil(MainActivity.this);
                permissionUtil.requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE},
                        new PermissionListener() {
                            @Override
                            public void onGranted() {
                                //所有权限都已经授权
                                Toast.makeText(MainActivity.this, "所有权限都已授权", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onDenied(List<String> deniedPermission) {
                                //Toast第一个被拒绝的权限
                                Toast.makeText(MainActivity.this, "拒绝了权限" + deniedPermission.get(0), Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onShouldShowRationale(List<String> deniedPermission) {
                                //Toast第一个勾选不在提示的权限
                                Toast.makeText(MainActivity.this, "这个权限" + deniedPermission.get(0)+"勾选了不在提示，要像用户解释为什么需要这权限", Toast.LENGTH_LONG).show();
                            }
                        });

            }
        });
    }




}
