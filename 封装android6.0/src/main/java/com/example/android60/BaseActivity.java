package com.example.android60;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by DELL on 2017/9/20.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 为子类提供一个权限检查方法
     * @param permissions ：表示不定参数 在调用该方法时可以传入多个String对象（JDK5的新特性）
     * @return
     */
    public boolean hasPermission(String ...permissions){
        for (String permission:permissions) {
            if(ContextCompat.checkSelfPermission(this,permission)!= PackageManager.PERMISSION_GRANTED){
                return true;
            }
        }
        return false;
    }
    /**
     * 在基类中为子类提供一个权限申请方法
     * 参数 1.int，区分码 2.权限
     */
    public void requewtPermission(int code,String ...permissions){
        ActivityCompat.requestPermissions(this,permissions,code);
    }

    /**
     * 基类集中处请求理权限回调的业务逻辑
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case Constants.CALL_PHONE:
                //判断权限是否申请成功 成功就执行打电话的逻辑
                //注意：因为集合里只有一个权限所以参数为0代表打电话的权限
                for (int i = 0; i < grantResults.length; i++) {
                    if(grantResults[i]==PackageManager.PERMISSION_GRANTED){

                    } else {
                        Toast.makeText(this, "用户没有授予权限", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
            case Constants.WRTE_EXTERNAL_STORAGE:
                //判断打电话权限是否申请成功 成功就执行打电话的逻辑
                //注意：因为集合里只有一个权限所以参数为0代表打电话的权限
                for (int i = 0; i < grantResults.length; i++) {
                    if(grantResults[i]==PackageManager.PERMISSION_GRANTED){

                    } else {
                        Toast.makeText(this, "用户没有授予权限", Toast.LENGTH_SHORT).show();
                    }
                }



                break;
            case Constants.READ_EXTERNAL_STORAGE:
                //判断打电话权限是否申请成功 成功就执行打电话的逻辑
                //注意：因为集合里只有一个权限所以参数为0代表打电话的权限
                for (int i = 0; i < grantResults.length; i++) {
                    if(grantResults[i]==PackageManager.PERMISSION_GRANTED){

                    } else {
                        Toast.makeText(this, "用户没有授予权限", Toast.LENGTH_SHORT).show();
                    }
                }



                break;
        }
    }
    /**
     * 写一个空实现 而不是抽象方法因为有些页面没有打电话的功能 如果是抽象方法的话还要必须重写此方法阅读性不是很好所以写一个空实现
     * 子类如果有此功能，复写此方法即可 不用再管权限配置
     */
    public void doCallPhone(){

    }

    /**************************************************/

    public void writeToSd(){

    }

















}
