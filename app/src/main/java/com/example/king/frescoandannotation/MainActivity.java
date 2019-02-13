package com.example.king.frescoandannotation;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.king.frescoandannotation.annotation.DraweeViewAnnotation;
import com.example.king.frescoandannotation.butterknife.BindView;
import com.example.king.frescoandannotation.butterknife.ButterKnife;
import com.example.king.frescoandannotation.butterknife.ContentView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.lang.reflect.Field;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @DraweeViewAnnotation("Hello world")
    @BindView(R.id.draweeView)
    SimpleDraweeView draweeView;

    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;
    @BindView(R.id.btn5)
    Button btn5;
    @BindView(R.id.btn6)
    Button btn6;
    private GenericDraweeHierarchy hierarchy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initView() {
        ButterKnife.bind(this);
        hierarchy = draweeView.getHierarchy();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RoundingParams roundingParams = RoundingParams.fromCornersRadius(0);
                roundingParams.setRoundAsCircle(true);
                hierarchy.setRoundingParams(roundingParams);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RoundingParams roundingParams = RoundingParams.fromCornersRadius(20);
                hierarchy.setRoundingParams(roundingParams);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                draweeView.setAspectRatio(2.71f);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uriGif = Uri.parse("http://www.zhaoapi.cn/images/girl.gif");
                DraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setUri(uriGif)
                        .setAutoPlayAnimations(true)
                        .build();
                draweeView.setController(controller);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取类的类类型
                Class<MainActivity> mainActivityClass = MainActivity.class;
                //获取域的值
                try {
                    Field field = mainActivityClass.getField("draweeView");
                    DraweeViewAnnotation annotation = field.getAnnotation(DraweeViewAnnotation.class);
                    if (annotation != null){
                        String value = annotation.value();
                        Toast.makeText(MainActivity.this,value,Toast.LENGTH_SHORT).show();
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initData() {
        Uri uri = Uri.parse("http://www.zhaoapi.cn/images/quarter/ad1.png");

        draweeView.setAspectRatio(2);
        draweeView.setImageURI(uri);

    }

}
