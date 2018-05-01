package com.zgq.android.drag.customview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.zgq.android.drag.customview.R;
import com.zgq.android.drag.customview.ScreenUtils.ScreenUtils;


/**
 * Created by zgq on 2018/4/27.
 */

public class GridItemView extends LinearLayout {
    private Path path3;
    private int dis;
    private int disY;

    private Paint textPaint = new Paint();
    private int paintMiddle;
    private int startDis;
    private int textSize = 12;
    private int width;

    private String con;
    public GridItemView(Context context) {
        super(context);
        addDrawText(getCon());
    }

    public GridItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public String getCon() {
        return con;
    }

    public void setCon(String con) {
        this.con = con;
    }

    private void addDrawText(String con) {
        if(!TextUtils.isEmpty(getCon())){
            setCon(con);
        }else{
            setCon("");
        }
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawRect(canvas);
    }

    private void drawRect(Canvas canvas) {
        if(TextUtils.isEmpty(getCon())){
            return;
        }
        textPaint.setAntiAlias(true);
        path3 = new Path();
        path3.moveTo(width - startDis,0);
        path3.lineTo(width - startDis - paintMiddle,0);
        path3.lineTo(width,startDis+paintMiddle);
        path3.lineTo(width,startDis);
        path3.close();
        textPaint.setColor(getResources().getColor(R.color.signColor));
        canvas.drawPath(path3,textPaint);
        canvas.save();
        textPaint.setStrokeWidth(3);
        textPaint.setTextSize(textSize* ScreenUtils.getDensity(getContext()));
        textPaint.setColor(Color.WHITE);
        textPaint.setTextAlign(Paint.Align.CENTER);
        dis = (paintMiddle/2 + startDis)/2;





    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(),widthMeasureSpec),getDefaultSize(getSuggestedMinimumHeight(),heightMeasureSpec));
        int childWithSize = getMeasuredWidth();
        int childHeightSize = getMeasuredHeight();
        heightMeasureSpec = widthMeasureSpec = MeasureSpec.makeMeasureSpec(widthMeasureSpec,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getWidth();
        paintMiddle = width/5;
        startDis = width/4;
    }
}
