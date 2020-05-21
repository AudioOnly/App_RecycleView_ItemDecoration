package com.yrn.app_recycleview_itemdecoration.decoration;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yrn.app_recycleview_itemdecoration.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * App_RecycleView_ItemDecoration
 * <p>
 * Created by Xinhoo on 2020/5/21
 * Describe:
 */
public class TextItemDecoration extends RecyclerView.ItemDecoration {
    private Paint mPaint, textPaint, dividerPaint;
    private Bitmap mBitmap;

    public TextItemDecoration(Context mContext) {
        super();
        this.mPaint = new Paint();
        this.textPaint = new Paint();
        this.dividerPaint = new Paint();
        mPaint.setColor(Color.YELLOW);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setStrokeWidth(8);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(32f);
        textPaint.setTextAlign(Paint.Align.CENTER);

        dividerPaint.setColor(Color.RED);
        //成功
        mBitmap = BitmapFactory.decodeStream(getClass().getResourceAsStream("/res/drawable/add_person_selected_icon.png"));
        //失败
//        mBitmap = BitmapFactory.decodeStream(getClass().getResourceAsStream("/res/mipmap-xxhdpi/add_person_selected_icon.png"));
        //成功
//        mBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.add_person_selected_icon);

    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        //1、针对 RecyclerView 本身,所以在 onDraw 方法中需要遍历屏幕上可见的 ItemView,分别获取它们的位置信息，然后分别的绘制对应的分割线。
        //2、Itemdecoration的onDraw()绘制会先于ItemView的onDraw()绘制，
        int childCount = parent.getChildCount();// 获取RecyclerView的Child view的个数
        // 遍历每个Item，分别获取它们的位置信息，然后再绘制对应的分割线
        for (int i = 0; i < childCount; i++) {
            // 获取每个Item的位置
            final View child = parent.getChildAt(i);
            int index = parent.getChildAdapterPosition(child);
//            if (index % 5 == 0) {
            //设置矩形分割线的宽度 10px
            final int mDivider = 10;
            // 矩形左上顶点 = (ItemView的左边界,ItemView的下边界)
            final int left = child.getLeft();
            final int top = child.getBottom();
            // 矩形右下顶点 = (ItemView的右边界,矩形的下边界)
            final int right = child.getRight();
            final int bottom = top + mDivider;
            // 通过Canvas绘制矩形（分割线）
            c.drawRect(left, top, right, bottom, dividerPaint);
//            }
        }

    }

    /**
     * 同样是绘制内容，但与onDraw（）的区别是：绘制在图层的最上层
     *
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
//        int childCount = parent.getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            View child = parent.getChildAt(i);
//            final int left = child.getRight() - mBitmap.getWidth();
//            final int top = child.getTop();
//            c.drawBitmap(mBitmap, left, top, mPaint);
//        }


        // 获取RecyclerView的Child view的个数
        int childCount = parent.getChildCount();
        // 遍历每个Item，分别获取它们的位置信息，然后再绘制对应的分割线
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            int index = parent.getChildAdapterPosition(child);
            if (index % 5 == 0) {
                int item = (index) / 5;
                if (i < 5) {
                    if (i == 1 && child.getTop() < 100) {
                        int left = 0;
                        int top = child.getTop() - 100;
                        int right = child.getRight();
                        int bottom = child.getTop() - 50;
                        RectF rectF = new RectF(left, top, right, bottom);
                        c.drawRect(rectF, mPaint);

                        //计算baseline
                        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
                        float distance = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
                        float baseline = rectF.centerY() + distance;
                        c.drawText("这a是条目" + item + "视图i是+" + i + "顶部的高低" + child.getTop() + "index++" + index, rectF.centerX(), baseline, textPaint);
                    } else {
                        int left = 0;
                        int top = 0;
                        int right = child.getRight();
                        int bottom = 50;
                        RectF rectF = new RectF(left, top, right, bottom);

                        //计算baseline
                        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
                        float distance = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
                        float baseline = rectF.centerY() + distance;
                        c.drawRect(rectF, mPaint);
                        if (i == 0) {
                            c.drawText("这b是条目" + (item + 1) + "视图i是+" + i + "顶部的高低" + child.getTop() + "index++" + index, rectF.centerX(), baseline, textPaint);
                        } else {
                            c.drawText("这c是条目" + (item) + "视图i是+" + i + "顶部的高低" + child.getTop() + "index++" + index, rectF.centerX(), baseline, textPaint);
                        }
                    }

                }
                if (i != 0) {
                    int left = 0;
                    int top = child.getTop() - 50;
                    int right = child.getRight();
                    int bottom = child.getTop();
                    RectF rectF = new RectF(left, top, right, bottom);

                    //计算baseline
                    Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
                    float distance = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
                    float baseline = rectF.centerY() + distance;
                    c.drawRect(rectF, mPaint);
                    c.drawText("这d是条目" + item + "视图i是+" + i + "顶部的高低" + child.getTop() + "index++" + index, rectF.centerX(), baseline, textPaint);
                }
            }
        }
    }

    /**
     * 设置ItemView的内嵌偏移长度（inset）
     *
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //针对每一个 ItemView，
        //设置内嵌套偏移长度（个人理解：类似于View的padding，是针对Item整个的）
//        outRect.set(50,50,50,50);
        int position = parent.getChildAdapterPosition(view);
        if (position % 5 == 0) {
            outRect.set(0, 50, 0, 0);
        } else {
            outRect.set(0, 1, 0, 0);
        }

    }
}
