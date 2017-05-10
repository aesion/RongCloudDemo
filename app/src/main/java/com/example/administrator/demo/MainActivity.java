package com.example.administrator.demo;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.CSCustomServiceInfo;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

public class MainActivity extends Activity {

    public static String liantong = "L3oOXmtzl/8Fr0PeeK0P4w4PiEkkVZYHTQOCZQ7U0FjzxzjCBkgxOFgItl7WiMXgeRku/Nl80RkoJVBHp6zuMw==";
    String yidong = "98VoJ+VxB+r2VjhSJfwwAQIQ/1+HXSzwPXc+lrVVik202zsoiH46FAgKLJ9rSqNm+uSeFRWeOa41JQ6nA5/EjA==";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
            @Override
            public UserInfo getUserInfo(String s) {//用户信息提供者的S会改变，所以从服务器拿，参考微商猎手
                UserInfo userInfo = new UserInfo("10010", "联通", Uri.parse("http://rongcloud-web.qiniudn.com/docs_demo_rongcloud_logo.png"));
                return userInfo;
            }
        }, true);
    }

    public void connect(View view) {
        RongIM.connect(liantong, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {

            }

            @Override
            public void onSuccess(String s) {
                Log.e("", "连接成功—————>" + s);
                Toast.makeText(MainActivity.this, "连接成功" + s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });
    }

    public void connectyidong(View view) {
        RongIM.connect(yidong, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {

            }
            @Override
            public void onSuccess(String s) {
                Log.e("","连接成功—————>"+s);
                Toast.makeText(MainActivity.this,"连接成功"+s,Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });
    }

    public void list(View view) {
//        startActivity(new Intent(this, ConversationListActivity.class));
            Map<String, Boolean> map = new HashMap<>();
            map.put(Conversation.ConversationType.PRIVATE.getName(), false); // 会话列表需要显示私聊会话, 第二个参数 true 代表私聊会话需要聚合显示
            map.put(Conversation.ConversationType.GROUP.getName(), false);  // 会话列表需要显示群组会话, 第二个参数 false 代表群组会话不需要聚合显示
            RongIM.getInstance().startConversationList(this, map);
    }

    public void convers(View view) {
        RongIM.getInstance().startPrivateChat(this, "10086", "移动");
    }


    public void kefu(View view) {
        //首先需要构造使用客服者的用户信息
        CSCustomServiceInfo.Builder csBuilder = new CSCustomServiceInfo.Builder();
        CSCustomServiceInfo csInfo = csBuilder.nickName("融云").build();

/**
 * 启动客户服聊天界面。
 *
 * @param context           应用上下文。
 * @param customerServiceId 要与之聊天的客服 Id。
 * @param title             聊天的标题，开发者可以在聊天界面通过 intent.getData().getQueryParameter("title") 获取该值, 再手动设置为标题。
 * @param customServiceInfo 当前使用客服者的用户信息。{@link io.rong.imlib.model.CSCustomServiceInfo}
 */
        RongIM.getInstance().startCustomerServiceChat(this, "KEFU149404318864090", "在线客服",csInfo);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        RongIM.getInstance().disconnect();//不设置收不到推送
    }
}
