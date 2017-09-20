package com.example.android60;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends BaseActivity {
    /**
     * android6.0 - 封装的整体思路:减少航宇代码的编写提高代码的阅读性
     * 在基类BaseActivity中为子类提高权限的检查和权限申请的方法再请求权限的回调处理接口中,暴露出子类要覆写并实现其业务逻辑的方法
     * 如果子类继承基类就可以大大简化6.0权限的申请的步骤,根据基类暴露出覆写的方法 实现其对应的业务逻辑
     *  0.常量类
     *  1.在清单文件里配置权限
     *  2.创建一个基类,并在基类中为子类提高一个权限检查的方法
     *  3.在基类中为子类提供一个权限申请方法
     *  4.在基类中集中处理请求权限的回调的业务逻辑
     *  5.暴露给子类实现具体业务逻辑的方法,子类如果有此功能,覆写方法即可,没有,就不用管此方法
     */
    private Button button;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        SecondActivity.this.requewtPermission(Constants.CALL_PHONE, Manifest.permission.CALL_PHONE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE);
        boolean permission = SecondActivity.this.hasPermission(Manifest.permission.CALL_PHONE);
        System.out.println(permission + "AA");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                doCallPhone();
//                Toast.makeText(context, "AAASSSS", Toast.LENGTH_SHORT).show();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeToSd();
            }
        });

    }

    //    @PermissionSuccess(requestCode = 100)
    @Override
    public void doCallPhone() {
        String number = "10086";
        //用intent启动拨打电话
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);
//                Toast.makeText(this, "正在给尼玛", Toast.LENGTH_SHORT).show(); //执行打电话到10086操作
        Toast.makeText(this, "打电话成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void writeToSd() {
        Object o=null;
        System.out.println(o.toString());
    }
}
