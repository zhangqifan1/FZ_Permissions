package com.example.android60;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

public class MainActivity extends AppCompatActivity {
    /**
     * 第三方的android6.0权限库:PermissionGen
     * 优点:使用简单,代码量少,易于阅读
     * 缺点:通过观看源码PermissionGen类,发现在找到注解方法后(具体看DoExecuteSuccess使用反射执行注解方法,以至于程序性能会受到影响)
     * <p>
     * <p>
     * 使用流程:
     * 1.添加依赖
     * 2.添加权限
     * 3.判断涉及到用户隐私的功能是否授权了对应的权限，如果没有自动申请
     * 4.申请权限成功，自动执行的逻辑
     * 5.用户不同意权限，自动回调的逻辑
     * 6.覆写处理回调的接口方法
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void CallPhone(View view) {

        //进行动态权限的适配,程序正常运行,判断涉及到用户隐私的功能是否授权对应的权限 如果没有就自动申请
        //参数 1.上下文 2.int型的权限区分码 3.String数组要申请的权限
        PermissionGen.needPermission(this, 100, new String[]{Manifest.permission.CALL_PHONE});
//        doCallPhone();
    }

    public void Tiao(View view) {
        startActivity(new Intent(MainActivity.this, SecondActivity.class));
    }

    /**
     * 权限申请成功自动执行的逻辑(使用注解)
     */
    @PermissionSuccess(requestCode = 100)
    private void doCallPhone() {
        //执行打电话到10086操作
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
        Toast.makeText(this, "正在给尼玛", Toast.LENGTH_SHORT).show();
    }

    /**
     * 申请权限失败
     */
    @PermissionFail(requestCode = 100)
    private void doCallPhoneFail() {
        Toast.makeText(this, "权限没配好啊!!!略略略", Toast.LENGTH_SHORT).show();
    }
    //覆写回调处理的接口方法

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //不用调用父类的方法了而是使用Permission的回调 唯一不同的就是多出一个上下文
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }
}
