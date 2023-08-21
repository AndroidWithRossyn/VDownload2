package com.fastdownload.wallpaper.livetoolsexplore.shimer;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fastdownload.wallpaper.livetoolsexplore.R;


public class ShimmerFrameLayout extends FrameLayout {
  private final Paint mContentPaint = new Paint();
  private final ShimmerDrawable mShimmerDrawable = new ShimmerDrawable();

  private boolean mShowShimmer = true;
  private boolean mStoppedShimmerBecauseVisibility = false;

  public ShimmerFrameLayout(Context context) {
    super(context);
    init(context, null);
  }

  public ShimmerFrameLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs);
  }

  public ShimmerFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public ShimmerFrameLayout(
      Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init(context, attrs);
  }

  private void init(Context context, @Nullable AttributeSet attrs) {
    setWillNotDraw(false);
    mShimmerDrawable.setCallback(this);

    if (attrs == null) {
      setShimmer(new Shimmer.AlphaHighlightBuilder().build());
      return;
    }

    TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ShimmerFrameLayout, 0, 0);
    try {
      Shimmer.Builder shimmerBuilder =
          a.hasValue(R.styleable.ShimmerFrameLayout_shimmer_colored)
                  && a.getBoolean(R.styleable.ShimmerFrameLayout_shimmer_colored, false)
              ? new Shimmer.ColorHighlightBuilder()
              : new Shimmer.AlphaHighlightBuilder();
      setShimmer(shimmerBuilder.consumeAttributes(a).build());
    } finally {
      a.recycle();
    }
  }

  public ShimmerFrameLayout setShimmer(@Nullable Shimmer shimmer) {
    mShimmerDrawable.setShimmer(shimmer);
    if (shimmer != null && shimmer.clipToChildren) {
      setLayerType(LAYER_TYPE_HARDWARE, mContentPaint);
    } else {
      setLayerType(LAYER_TYPE_NONE, null);
    }

    return this;
  }

  public @Nullable Shimmer getShimmer() {
    return mShimmerDrawable.getShimmer();
  }


  public void startShimmer() {
    mShimmerDrawable.startShimmer();
  }


  public void stopShimmer() {
    mStoppedShimmerBecauseVisibility = false;
    mShimmerDrawable.stopShimmer();
  }
  public boolean isShimmerStarted() {
    return mShimmerDrawable.isShimmerStarted();
  }

  public void showShimmer(boolean startShimmer) {
    mShowShimmer = true;
    if (startShimmer) {
      startShimmer();
    }
    invalidate();
  }


  public void hideShimmer() {
    stopShimmer();
    mShowShimmer = false;
    invalidate();
  }


  public boolean isShimmerVisible() {
    return mShowShimmer;
  }

  public boolean isShimmerRunning() {
    return mShimmerDrawable.isShimmerRunning();
  }

  @Override
  public void onLayout(boolean changed, int left, int top, int right, int bottom) {
    super.onLayout(changed, left, top, right, bottom);
    final int width = getWidth();
    final int height = getHeight();
    mShimmerDrawable.setBounds(0, 0, width, height);
  }

  @Override
  protected void onVisibilityChanged(View changedView, int visibility) {
    super.onVisibilityChanged(changedView, visibility);

    if (mShimmerDrawable == null) {
      return;
    }
    if (visibility != View.VISIBLE) {
      if (isShimmerStarted()) {
        stopShimmer();
        mStoppedShimmerBecauseVisibility = true;
      }
    } else if (mStoppedShimmerBecauseVisibility) {
      mShimmerDrawable.maybeStartShimmer();
      mStoppedShimmerBecauseVisibility = false;
    }
  }

  @Override
  public void onAttachedToWindow() {
    super.onAttachedToWindow();
    mShimmerDrawable.maybeStartShimmer();
  }

  @Override
  public void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    stopShimmer();
  }

  @Override
  public void dispatchDraw(Canvas canvas) {
    super.dispatchDraw(canvas);
    if (mShowShimmer) {
      mShimmerDrawable.draw(canvas);
    }
  }

  @Override
  protected boolean verifyDrawable(@NonNull Drawable who) {
    return super.verifyDrawable(who) || who == mShimmerDrawable;
  }

  public void setStaticAnimationProgress(float value) {
    mShimmerDrawable.setStaticAnimationProgress(value);
  }

  public void clearStaticAnimationProgress() {
    mShimmerDrawable.clearStaticAnimationProgress();
  }
}