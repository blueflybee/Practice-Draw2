package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw2.R;

public class Practice06LightingColorFilterView extends View {
  Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
  Bitmap bitmap;

  public Practice06LightingColorFilterView(Context context) {
    super(context);
  }

  public Practice06LightingColorFilterView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public Practice06LightingColorFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  {
    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    // 使用 Paint.setColorFilter() 来设置 LightingColorFilter

    // 第一个 LightingColorFilter：去掉红色部分
//        R' = R * mul.R / 0xff + add.R
//        G' = G * mul.G / 0xff + add.G
//        B' = B * mul.B / 0xff + add.B
    paint.setColorFilter(new LightingColorFilter(0x00ffff, 0));
    canvas.drawBitmap(bitmap, 0, 0, paint);

    // 第二个 LightingColorFilter：增强绿色部分
    paint.setColorFilter(new LightingColorFilter(0xffffff, 0x002f00));
    canvas.drawBitmap(bitmap, bitmap.getWidth() + 100, 0, paint);


//    paint.reset();
//    paint.setColorFilter(new PorterDuffColorFilter(Color.GREEN, PorterDuff.Mode.DST_OUT));

    canvas.drawBitmap(bitmap, bitmap.getWidth() + 100, 0, paint);
  }
}
