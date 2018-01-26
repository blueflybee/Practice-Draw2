package com.hencoder.hencoderpracticedraw2.sample;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw2.R;

public class VideoDownloadProgressBar extends View {
  private Paint mPaint;

  private Xfermode mPorterDuffXfermode;
  // 图层混合模式
  private PorterDuff.Mode mPorterDuffMode;
  // 总宽高
  private int mTotalWidth, mTotalHeight;
  private Resources mResources;
  private Bitmap mRocketBitmap;
  private RectF mBackgroundRectF;
  private RectF mForegroundRectF;

  public VideoDownloadProgressBar(Context context) {
    super(context);
    init();
  }

  public VideoDownloadProgressBar(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public VideoDownloadProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    mResources = getResources();
    initRocketBitmap();
    initPaint();
    initXfermode();
    initRoundRect();
  }

  private void initRoundRect() {
    mBackgroundRectF = new RectF(200, 200, 1000, 250);
    mForegroundRectF = new RectF(200, 200, 800, 250);
  }

  {
    setLayerType(LAYER_TYPE_SOFTWARE, null);
  }


  private void initRocketBitmap() {
    mRocketBitmap = BitmapFactory.decodeResource(mResources, R.mipmap.pic_rocket);
  }


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
    drawBackgroundProgress(canvas);
//    // 设置混合模式
    mPaint.setXfermode(mPorterDuffXfermode);
//    // 绘制源图
    drawForegroundProgress(canvas);
    drawRocket(canvas);
    mPaint.setXfermode(null);
    canvas.restoreToCount(saveCount);

//    System.out.println("mStartTop = " + mStartTop);
//    System.out.println("mEndTop = " + mEndTop);
//    if (mDinamicRect.top >= mEndTop) {
//      mDinamicRect.top -= 2;
//      postInvalidateDelayed(20);
//    } else {
//      mDinamicRect.top = mStartTop;
//      postInvalidate();
//    }

  }

  private void drawRocket(Canvas canvas) {
    canvas.drawBitmap(mRocketBitmap, 950, 210, mPaint);
  }

  private void drawForegroundProgress(Canvas canvas) {
    mPaint.setStrokeWidth(0);
    mPaint.setStyle(Paint.Style.FILL);
    mPaint.setColor(Color.parseColor("#1594F6"));
    canvas.drawRoundRect(mForegroundRectF, 100, 100, mPaint);
  }

  private void drawBackgroundProgress(Canvas canvas) {
    mPaint.setStrokeWidth(0);
    mPaint.setStyle(Paint.Style.FILL);
    mPaint.setColor(Color.parseColor("#E6E6E6"));
    canvas.drawRoundRect(mBackgroundRectF, 100, 100, mPaint);
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
