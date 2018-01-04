package com.hencoder.hencoderpracticedraw2.sample;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw2.R;

public class Exp1XfermodeView extends View {
  private Paint mPaint;
  private Bitmap mBottomBitmap, mTopBitmap;
  private Rect mDinamicRect;

  private Xfermode mPorterDuffXfermode;
  // 图层混合模式
  private PorterDuff.Mode mPorterDuffMode;
  // 总宽高
  private int mTotalWidth, mTotalHeight;
  private Resources mResources;

  private int mStartTop, mEndTop;

  public Exp1XfermodeView(Context context) {
    super(context);
    init();
  }

  private void init() {
    mResources = getResources();
    initBitmap();
    initDynamicRect();
    initPaint();
    initXfermode();
  }

  public Exp1XfermodeView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init();

  }
  {
    setLayerType(LAYER_TYPE_SOFTWARE, null);
  }

  // 初始化bitmap
  private void initBitmap() {
    mTopBitmap = ((BitmapDrawable) mResources.getDrawable(R.mipmap.red_circle)).getBitmap();

    mBottomBitmap = Bitmap.createBitmap(820, 120, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(mBottomBitmap);
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    paint.setColor(Color.LTGRAY);
    paint.setTextSize(120);

    String text = "Android Studio";
    canvas.drawText(text, 0, text.length(),0, 120, paint);

  }


  private void initDynamicRect() {
    mDinamicRect = new Rect(100, 100 + mBottomBitmap.getHeight(), 100 + mBottomBitmap.getWidth(), 100 + mBottomBitmap.getHeight());
    mStartTop = mDinamicRect.bottom;
    mEndTop = 100;
  }


  // 初始化混合模式
  private void initXfermode() {
    mPorterDuffMode = PorterDuff.Mode.SRC_IN;
    mPorterDuffXfermode = new PorterDuffXfermode(mPorterDuffMode);
  }

  private void initPaint() {
    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    System.out.println("TestXfermodeView.onDraw");

    // 背景铺白
//    canvas.drawColor(Color.WHITE);
    // 保存为单独的层
    int saveCount = canvas.saveLayer(0, 0, mTotalWidth, mTotalHeight, mPaint,
        Canvas.ALL_SAVE_FLAG);
    // 绘制目标图
    canvas.drawBitmap(mBottomBitmap, 100, 100, mPaint);
//    // 设置混合模式
    mPaint.setXfermode(mPorterDuffXfermode);
//    // 绘制源图
    mPaint.setStyle(Paint.Style.FILL);
    mPaint.setColor(Color.RED);
    canvas.drawRect(mDinamicRect, mPaint);
    mPaint.setXfermode(null);
    canvas.restoreToCount(saveCount);

    System.out.println("mStartTop = " + mStartTop);
    System.out.println("mEndTop = " + mEndTop);
    if (mDinamicRect.top >= mEndTop) {
      mDinamicRect.top -= 2;
      postInvalidateDelayed(20);
    } else {
      mDinamicRect.top = mStartTop;
      postInvalidate();
    }

  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    System.out.println("TestXfermodeView.onSizeChanged");
    mTotalWidth = w;
    mTotalHeight = h;
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    System.out.println("TestXfermodeView.onMeasure");
  }

  @Override
  protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    super.onLayout(changed, left, top, right, bottom);
    System.out.println("TestXfermodeView.onLayout");
  }
}
