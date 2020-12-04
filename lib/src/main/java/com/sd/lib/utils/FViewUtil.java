package com.sd.lib.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FViewUtil
{
    private FViewUtil()
    {
    }

    /**
     * 设置view的背景
     *
     * @param view
     * @param drawable
     */
    public static void setBackgroundDrawable(View view, Drawable drawable)
    {
        if (view == null)
            return;

        final int paddingLeft = view.getPaddingLeft();
        final int paddingTop = view.getPaddingTop();
        final int paddingRight = view.getPaddingRight();
        final int paddingBottom = view.getPaddingBottom();

        view.setBackgroundDrawable(drawable);
        view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    /**
     * 设置view的背景
     *
     * @param view
     * @param resId 背景资源id
     */
    public static void setBackgroundResource(View view, int resId)
    {
        if (view == null)
            return;

        final int paddingLeft = view.getPaddingLeft();
        final int paddingTop = view.getPaddingTop();
        final int paddingRight = view.getPaddingRight();
        final int paddingBottom = view.getPaddingBottom();

        view.setBackgroundResource(resId);
        view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    /**
     * 设置view的左边margin（当view的LayoutParams instanceof MarginLayoutParams才有效）
     *
     * @param view
     * @param left
     * @return true-设置发生了变更
     */
    public static boolean setMarginLeft(View view, int left)
    {
        final MarginLayoutParams params = getMarginLayoutParams(view);
        if (params == null)
            return false;

        if (params.leftMargin == left)
            return false;

        params.leftMargin = left;
        view.setLayoutParams(params);
        return true;
    }

    /**
     * 设置view的顶部margin（当view的LayoutParams instanceof MarginLayoutParams才有效）
     *
     * @param view
     * @param top
     * @return true-设置发生了变更
     */
    public static boolean setMarginTop(View view, int top)
    {
        final MarginLayoutParams params = getMarginLayoutParams(view);
        if (params == null)
            return false;

        if (params.topMargin == top)
            return false;

        params.topMargin = top;
        view.setLayoutParams(params);
        return true;
    }

    /**
     * 设置view的右边margin（当view的LayoutParams instanceof MarginLayoutParams才有效）
     *
     * @param view
     * @param right
     * @return true-设置发生了变更
     */
    public static boolean setMarginRight(View view, int right)
    {
        final MarginLayoutParams params = getMarginLayoutParams(view);
        if (params == null)
            return false;

        if (params.rightMargin == right)
            return false;

        params.rightMargin = right;
        view.setLayoutParams(params);
        return true;
    }

    /**
     * 设置view的底部margin（当view的LayoutParams instanceof MarginLayoutParams才有效）
     *
     * @param view
     * @param bottom
     * @return true-设置发生了变更
     */
    public static boolean setMarginBottom(View view, int bottom)
    {
        final MarginLayoutParams params = getMarginLayoutParams(view);
        if (params == null)
            return false;

        if (params.bottomMargin == bottom)
            return false;

        params.bottomMargin = bottom;
        view.setLayoutParams(params);
        return true;
    }

    /**
     * 设置view的上下左右margin（当view的LayoutParams instanceof MarginLayoutParams才有效）
     *
     * @param view
     * @param margins
     */
    public static void setMargins(View view, int margins)
    {
        setMargin(view, margins, margins, margins, margins);
    }

    /**
     * 设置view的上下左右margin（当view的LayoutParams instanceof MarginLayoutParams才有效）
     *
     * @param view
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    public static void setMargin(View view, int left, int top, int right, int bottom)
    {
        final MarginLayoutParams params = getMarginLayoutParams(view);
        if (params == null)
            return;

        boolean needSet = false;
        if (params.leftMargin != left)
        {
            params.leftMargin = left;
            needSet = true;
        }
        if (params.topMargin != top)
        {
            params.topMargin = top;
            needSet = true;
        }
        if (params.rightMargin != right)
        {
            params.rightMargin = right;
            needSet = true;
        }
        if (params.bottomMargin != bottom)
        {
            params.bottomMargin = bottom;
            needSet = true;
        }

        if (needSet)
            view.setLayoutParams(params);
    }

    /**
     * 获得view的MarginLayoutParams，返回值可能为null
     *
     * @param view
     * @return
     */
    public static MarginLayoutParams getMarginLayoutParams(View view)
    {
        if (view == null)
            return null;

        final ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params != null && params instanceof MarginLayoutParams)
            return (MarginLayoutParams) params;

        return null;
    }

    /**
     * 设置该view在父布局中的gravity
     *
     * @param view
     * @param gravity
     */
    public static void setLayoutGravity(View view, int gravity)
    {
        if (view == null)
            return;

        final ViewGroup.LayoutParams p = view.getLayoutParams();
        if (p == null)
            return;

        if (p instanceof FrameLayout.LayoutParams)
        {
            final FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) p;
            if (params.gravity != gravity)
            {
                params.gravity = gravity;
                view.setLayoutParams(params);
            }
        } else if (p instanceof LinearLayout.LayoutParams)
        {
            final LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) p;
            if (params.gravity != gravity)
            {
                params.gravity = gravity;
                view.setLayoutParams(params);
            }
        }
    }

    /**
     * 设置view的左边padding
     *
     * @param view
     * @param padding
     */
    public static void setPaddingLeft(View view, int padding)
    {
        if (view == null)
            return;

        if (view.getPaddingLeft() != padding)
            view.setPadding(padding, view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
    }

    /**
     * 设置view的顶部padding
     *
     * @param view
     * @param padding
     */
    public static void setPaddingTop(View view, int padding)
    {
        if (view == null)
            return;

        if (view.getPaddingTop() != padding)
            view.setPadding(view.getPaddingLeft(), padding, view.getPaddingRight(), view.getPaddingBottom());
    }

    /**
     * 设置view的右边padding
     *
     * @param view
     * @param padding
     */
    public static void setPaddingRight(View view, int padding)
    {
        if (view == null)
            return;

        if (view.getPaddingRight() != padding)
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), padding, view.getPaddingBottom());
    }

    /**
     * 设置view的底部padding
     *
     * @param view
     * @param padding
     */
    public static void setPaddingBottom(View view, int padding)
    {
        if (view == null)
            return;

        if (view.getPaddingBottom() != padding)
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), padding);
    }

    /**
     * 设置View的padding，如果值为null，则维持原来的padding值
     *
     * @param view
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    public static void setPadding(View view, Integer left, Integer top, Integer right, Integer bottom)
    {
        if (view == null)
            return;

        final int paddingLeft = left != null ? left : view.getPaddingLeft();
        final int paddingTop = top != null ? top : view.getPaddingTop();
        final int paddingRight = right != null ? right : view.getPaddingRight();
        final int paddingBottom = bottom != null ? bottom : view.getPaddingBottom();

        if (view.getPaddingLeft() != paddingLeft ||
                view.getPaddingTop() != paddingTop ||
                view.getPaddingRight() != paddingRight ||
                view.getPaddingBottom() != paddingBottom)
        {
            view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        }
    }

    /**
     * 根据传入的宽度，获得按指定比例缩放后的高度
     *
     * @param scaleWidth  指定的比例宽度
     * @param scaleHeight 指定的比例高度
     * @param width       宽度
     * @return
     */
    public static int getScaleHeight(int scaleWidth, int scaleHeight, int width)
    {
        if (scaleWidth == 0)
            return 0;

        return scaleHeight * width / scaleWidth;
    }

    /**
     * 根据传入的高度，获得按指定比例缩放后的宽度
     *
     * @param scaleWidth  指定的比例宽度
     * @param scaleHeight 指定的比例高度
     * @param height      高度
     * @return
     */
    public static int getScaleWidth(int scaleWidth, int scaleHeight, int height)
    {
        if (scaleHeight == 0)
            return 0;

        return scaleWidth * height / scaleHeight;
    }

    /**
     * 测量view，测量后，可以获得view的测量宽高
     *
     * @param view
     */
    public static void measureView(View view)
    {
        if (view == null)
            return;

        final int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        final int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
    }

    /**
     * 获得view的高度
     *
     * @param view
     * @return
     */
    public static int getHeight(View view)
    {
        int height = 0;
        height = view.getHeight();
        if (height <= 0)
        {
            if (view.getLayoutParams() != null)
            {
                height = view.getLayoutParams().height;
            }
        }
        if (height <= 0)
        {
            measureView(view);
            height = view.getMeasuredHeight();
        }
        return height;
    }

    /**
     * 获得view的宽度
     *
     * @param view
     * @return
     */
    public static int getWidth(View view)
    {
        int width = 0;
        width = view.getWidth();
        if (width <= 0)
        {
            if (view.getLayoutParams() != null)
            {
                width = view.getLayoutParams().width;
            }
        }
        if (width <= 0)
        {
            measureView(view);
            width = view.getMeasuredWidth();
        }
        return width;
    }


    /**
     * 设置view的高度
     *
     * @param view
     * @param height
     * @return true-view的高度和指定的高度的一致
     */
    public static boolean setHeight(View view, int height)
    {
        final ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params == null)
            return false;

        if (params.height != height)
        {
            params.height = height;
            view.setLayoutParams(params);
        }
        return true;
    }

    /**
     * 设置view的宽度
     *
     * @param view
     * @param width
     * @return
     */
    public static boolean setWidth(View view, int width)
    {
        final ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params == null)
            return false;

        if (params.width != width)
        {
            params.width = width;
            view.setLayoutParams(params);
        }
        return true;
    }

    /**
     * 设置view的宽度和高度
     *
     * @param view
     * @param width
     * @param height
     */
    public static void setSize(View view, int width, int height)
    {
        final ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params == null)
        {
            view.setLayoutParams(new ViewGroup.LayoutParams(width, height));
            return;
        }

        boolean needSet = false;
        if (params.width != width)
        {
            params.width = width;
            needSet = true;
        }

        if (params.height != height)
        {
            params.height = height;
            needSet = true;
        }

        if (needSet)
            view.setLayoutParams(params);
    }

    /**
     * 当view的父布局是RelativeLayout的时候，设置view的布局规则
     *
     * @param view
     * @param anchorId
     * @param rules
     */
    public static void addRule(View view, int anchorId, Integer... rules)
    {
        if (view == null || rules == null)
            return;

        final ViewGroup.LayoutParams p = view.getLayoutParams();
        if (p instanceof RelativeLayout.LayoutParams)
        {
            final RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) p;
            for (Integer item : rules)
            {
                if (anchorId != 0)
                {
                    params.addRule(item, anchorId);
                } else
                {
                    params.addRule(item);
                }
            }
            view.setLayoutParams(params);
        }
    }

    /**
     * 当view的父布局是RelativeLayout的时候，移除view的布局规则
     *
     * @param view
     * @param rules 要移除的布局规则
     */
    @TargetApi(17)
    public static void removeRule(View view, Integer... rules)
    {
        if (view == null || rules == null)
            return;

        final ViewGroup.LayoutParams p = view.getLayoutParams();
        if (p instanceof RelativeLayout.LayoutParams)
        {
            final RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) p;
            for (Integer item : rules)
            {
                params.removeRule(item);
            }
            view.setLayoutParams(params);
        }
    }

    /**
     * 返回View的可见状态
     *
     * @param view
     * @return -1(view为null的时候)<br> {@link View#VISIBLE}<br> {@link View#INVISIBLE}<br> {@link View#GONE}<br>
     */
    public static int getVisibility(View view)
    {
        if (view == null)
            return -1;

        return view.getVisibility();
    }

    /**
     * 设置view的可见状态
     *
     * @param view
     * @param visibility
     * @return true-view处于设置的状态
     */
    public static boolean setVisibility(View view, int visibility)
    {
        if (view == null)
            return false;

        if (visibility == View.VISIBLE ||
                visibility == View.INVISIBLE ||
                visibility == View.GONE)
        {
            if (view.getVisibility() != visibility)
                view.setVisibility(visibility);
            return true;
        } else
        {
            throw new IllegalArgumentException("visibility is Illegal");
        }
    }

    /**
     * 设置View.VISIBLE或者View.GONE
     *
     * @param view
     * @param visible true-View.VISIBLE；false-View.GONE
     * @return true-view处于VISIBLE
     */
    public static boolean setVisibleOrGone(View view, boolean visible)
    {
        if (view == null)
            return false;

        if (visible)
        {
            if (view.getVisibility() != View.VISIBLE)
                view.setVisibility(View.VISIBLE);
            return true;
        } else
        {
            if (view.getVisibility() != View.GONE)
                view.setVisibility(View.GONE);
            return false;
        }
    }

    /**
     * 设置View.VISIBLE或者View.INVISIBLE
     *
     * @param view
     * @param visible true-View.VISIBLE；false-View.INVISIBLE
     * @return true-view处于VISIBLE
     */
    public static boolean setVisibleOrInvisible(View view, boolean visible)
    {
        if (view == null)
            return false;

        if (visible)
        {
            if (view.getVisibility() != View.VISIBLE)
                view.setVisibility(View.VISIBLE);
            return true;
        } else
        {
            if (view.getVisibility() != View.INVISIBLE)
                view.setVisibility(View.INVISIBLE);
            return false;
        }
    }

    /**
     * 设置view在VISIBLE和GONE之间切换
     *
     * @param view
     * @return true-view处于VISIBLE
     */
    public static boolean toggleVisibleOrGone(View view)
    {
        if (view == null)
            return false;

        if (view.getVisibility() == View.VISIBLE)
        {
            if (view.getVisibility() != View.GONE)
                view.setVisibility(View.GONE);
            return false;
        } else
        {
            if (view.getVisibility() != View.VISIBLE)
                view.setVisibility(View.VISIBLE);
            return true;
        }
    }

    /**
     * 设置view在VISIBLE和INVISIBLE之间切换
     *
     * @param view
     * @return true-view处于VISIBLE
     */
    public static boolean toggleVisibleOrInvisible(View view)
    {
        if (view == null)
            return false;

        if (view.getVisibility() == View.VISIBLE)
        {
            if (view.getVisibility() != View.INVISIBLE)
                view.setVisibility(View.INVISIBLE);
            return false;
        } else
        {
            if (view.getVisibility() != View.VISIBLE)
                view.setVisibility(View.VISIBLE);
            return true;
        }
    }

    /**
     * view是否被添加到界面上
     *
     * @param view
     * @return
     */
    public static boolean isAttached(View view)
    {
        if (view == null)
            return false;

        if (Build.VERSION.SDK_INT >= 19)
            return view.isAttachedToWindow();
        else
            return view.getWindowToken() != null;
    }

    /**
     * {@link #isViewUnder(View, int, int, int[])}
     *
     * @param view
     * @param event 触摸点
     * @return
     */
    public static boolean isViewUnder(View view, MotionEvent event)
    {
        if (view == null)
            return false;

        final int x = (int) event.getRawX();
        final int y = (int) event.getRawY();
        return isViewUnder(view, x, y, null);
    }

    /**
     * {@link #isViewUnder(View, int, int, int[])}
     *
     * @param view
     * @param x
     * @param y
     * @return
     */
    public static boolean isViewUnder(View view, int x, int y)
    {
        return isViewUnder(view, x, y, null);
    }

    /**
     * view是否在某个屏幕坐标下面
     *
     * @param view
     * @param x           屏幕x坐标
     * @param y           屏幕y坐标
     * @param outLocation 用于接收view的x和y坐标的数组，可以为null
     * @return
     */
    public static boolean isViewUnder(View view, int x, int y, int[] outLocation)
    {
        if (view == null)
            return false;

        if (!isAttached(view))
            return false;

        final int[] location = getLocationOnScreen(view, outLocation);
        final int left = location[0];
        final int top = location[1];
        final int right = left + view.getWidth();
        final int bottom = top + view.getHeight();

        return left < right && top < bottom
                && x >= left && x < right && y >= top && y < bottom;
    }

    /**
     * 获得view在屏幕上的坐标
     *
     * @param view
     * @param outLocation 如果为null或者长度不等于2，内部会创建一个长度为2的数组返回
     * @return
     */
    public static int[] getLocationOnScreen(View view, int[] outLocation)
    {
        if (outLocation == null || outLocation.length != 2)
            outLocation = new int[]{0, 0};

        if (view != null)
            view.getLocationOnScreen(outLocation);

        return outLocation;
    }

    /**
     * 获得view的截图
     *
     * @param view
     * @return
     */
    public static Bitmap createViewBitmap(View view)
    {
        if (view == null)
            return null;

        view.setDrawingCacheEnabled(true);
        final Bitmap drawingCache = view.getDrawingCache();
        if (drawingCache == null)
            return null;

        final Bitmap result = Bitmap.createBitmap(drawingCache);
        view.destroyDrawingCache();
        return result;
    }

    /**
     * 获得view的镜像
     *
     * @param view
     * @return
     */
    public static ImageView getViewsImage(View view)
    {
        final Bitmap bitmap = createViewBitmap(view);
        if (bitmap == null)
            return null;

        final ImageView imageView = new ImageView(view.getContext());
        imageView.setImageBitmap(bitmap);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(bitmap.getWidth(), bitmap.getHeight());
        imageView.setLayoutParams(params);
        return imageView;
    }

    public static void wrapperPopupWindow(PopupWindow pop)
    {
        if (pop == null)
            return;

        final ColorDrawable drawable = new ColorDrawable(0x00ffffff);
        pop.setBackgroundDrawable(drawable);
        pop.setWidth(FrameLayout.LayoutParams.MATCH_PARENT);
        pop.setHeight(FrameLayout.LayoutParams.WRAP_CONTENT);
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
    }

    /**
     * 按指定比例缩放view的高度
     *
     * @param view
     * @param scaleWidth  指定比例宽度
     * @param scaleHeight 指定比例高度
     */
    public static void scaleWidth(View view, int scaleWidth, int scaleHeight)
    {
        if (view == null || scaleHeight == 0)
            return;

        final int height = getHeight(view);
        final int width = getScaleWidth(scaleWidth, scaleHeight, height);
        setWidth(view, width);
    }

    /**
     * 按指定比例缩放view的高度
     *
     * @param view
     * @param scaleWidth  指定比例宽度
     * @param scaleHeight 指定比例高度
     */
    public static void scaleHeight(View view, int scaleWidth, int scaleHeight)
    {
        if (view == null || scaleWidth == 0)
            return;

        final int width = getWidth(view);
        final int height = getScaleHeight(scaleWidth, scaleHeight, width);
        setHeight(view, height);
    }

    /**
     * 设置view的宽度为WRAP_CONTENT
     *
     * @param view
     */
    public static void setWidthWrapContent(View view)
    {
        setWidth(view, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    /**
     * 设置view的宽度为MATCH_PARENT
     *
     * @param view
     */
    public static void setWidthMatchParent(View view)
    {
        setWidth(view, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    /**
     * 设置view的高度为WRAP_CONTENT
     *
     * @param view
     */
    public static void setHeightWrapContent(View view)
    {
        setHeight(view, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    /**
     * 设置view的高度为MATCH_PARENT
     *
     * @param view
     */
    public static void setHeightMatchParent(View view)
    {
        setHeight(view, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    /**
     * 设置view的宽度和weight，仅当view处于LinearLayout里面时有效
     *
     * @param view
     * @param width
     * @param weight
     */
    public static void setWidthWeight(View view, int width, float weight)
    {
        if (view == null)
            return;

        final ViewGroup.LayoutParams p = view.getLayoutParams();
        if (p instanceof LinearLayout.LayoutParams)
        {
            final LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) p;

            boolean needSet = false;
            if (params.width != width)
            {
                params.width = width;
                needSet = true;
            }
            if (params.weight != weight)
            {
                params.weight = weight;
                needSet = true;
            }

            if (needSet)
                view.setLayoutParams(params);
        }
    }

    /**
     * 设置view的高度和weight，仅当view处于LinearLayout里面时有效
     *
     * @param view
     * @param height
     * @param weight
     */
    public static void setHeightWeight(View view, int height, float weight)
    {
        if (view == null)
            return;

        final ViewGroup.LayoutParams p = view.getLayoutParams();
        if (p instanceof LinearLayout.LayoutParams)
        {
            final LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) p;

            boolean needSet = false;
            if (params.height != height)
            {
                params.height = height;
                needSet = true;
            }
            if (params.weight != weight)
            {
                params.weight = weight;
                needSet = true;
            }

            if (needSet)
                view.setLayoutParams(params);
        }
    }

    /**
     * 开始动画Drawable
     *
     * @param drawable
     */
    public static void startAnimationDrawable(Drawable drawable)
    {
        if (drawable instanceof AnimationDrawable)
        {
            final AnimationDrawable animationDrawable = (AnimationDrawable) drawable;
            if (!animationDrawable.isRunning())
                animationDrawable.start();
        }
    }

    /**
     * 停止动画Drawable
     *
     * @param drawable
     */
    public static void stopAnimationDrawable(Drawable drawable)
    {
        stopAnimationDrawable(drawable, 0);
    }

    /**
     * 停止动画Drawable
     *
     * @param drawable
     * @param stopIndex 要停止在第几帧
     */
    public static void stopAnimationDrawable(Drawable drawable, int stopIndex)
    {
        if (drawable instanceof AnimationDrawable)
        {
            final AnimationDrawable animationDrawable = (AnimationDrawable) drawable;
            animationDrawable.stop();
            animationDrawable.selectDrawable(stopIndex);
        }
    }

    /**
     * 重置view
     *
     * @param view
     */
    public static void resetView(View view)
    {
        if (view == null)
            return;

        view.setAlpha(1.0f);
        view.setRotation(0.0f);
        view.setRotationX(0.0f);
        view.setRotationY(0.0f);
        view.setTranslationX(0.0f);
        view.setTranslationY(0.0f);
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
    }

    /**
     * 测量文字的宽度
     *
     * @param textView
     * @param content  文字内容
     * @return
     */
    public static float measureText(TextView textView, String content)
    {
        if (textView == null || content == null)
            return 0;

        return textView.getPaint().measureText(content);
    }

    /**
     * 把view从它的父容器移除
     *
     * @param view
     */
    public static void removeView(final View view)
    {
        try
        {
            final ViewGroup viewGroup = (ViewGroup) view.getParent();
            viewGroup.removeView(view);
        } catch (Exception e)
        {
        }
    }

    /**
     * 用新的view去替换布局中的旧view
     *
     * @param oldView
     * @param newView
     */
    public static void replaceOldView(final View oldView, final View newView)
    {
        if (oldView == null || newView == null || oldView == newView)
            return;

        final ViewParent parent = oldView.getParent();
        if (parent instanceof ViewGroup)
        {
            final ViewGroup viewGroup = (ViewGroup) parent;
            final int index = viewGroup.indexOfChild(oldView);
            final ViewGroup.LayoutParams params = oldView.getLayoutParams();

            removeView(oldView);
            removeView(newView);

            viewGroup.addView(newView, index, params);
        }
    }

    /**
     * 替换child到parent，仅保留当前child对象在容器中
     *
     * @param parent
     * @param child
     */
    public static void replaceView(View parent, View child)
    {
        addView(parent, child, true);
    }

    /**
     * 添加child到parent
     *
     * @param parent
     * @param child
     */
    public static void addView(View parent, View child)
    {
        addView(parent, child, false);
    }

    /**
     * 添加child到parent
     *
     * @param parent         父容器
     * @param child          要添加的view
     * @param removeAllViews 添加的时候是否需要先移除parent的所有子view
     */
    private static void addView(final View parent, final View child, final boolean removeAllViews)
    {
        if (parent == null || child == null)
            return;

        if (!(parent instanceof ViewGroup))
            throw new IllegalArgumentException("parent must be instance of ViewGroup");

        final ViewGroup viewGroup = (ViewGroup) parent;
        if (child.getParent() != viewGroup)
        {
            if (removeAllViews)
                viewGroup.removeAllViews();

            removeView(child);
            viewGroup.addView(child);
        } else
        {
            if (removeAllViews)
            {
                final int count = viewGroup.getChildCount();
                for (int i = count - 1; i >= 0; i--)
                {
                    final View item = viewGroup.getChildAt(i);
                    if (item != child)
                        viewGroup.removeView(item);
                }
            }
        }
    }

    /**
     * 切换容器的内容为view<br>
     * 如果child没有被添加到parent，child将会被添加到parent，最终只显示child，隐藏parent的所有其他child
     *
     * @param parent
     * @param child
     */
    public static void toggleView(final ViewGroup parent, final View child)
    {
        if (child == null || parent == null)
            return;

        if (child.getParent() != parent)
        {
            removeView(child);
            parent.addView(child);
        }

        final int count = parent.getChildCount();
        for (int i = 0; i < count; i++)
        {
            final View item = parent.getChildAt(i);
            if (item == child)
            {
                item.setVisibility(View.VISIBLE);
            } else
            {
                item.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 设置状态栏背景透明
     *
     * @param activity
     * @param transparent true-透明，false不透明
     */
    public static boolean setStatusBarTransparent(Activity activity, boolean transparent)
    {
        try
        {
            Window window = activity.getWindow();
            WindowManager.LayoutParams params = window.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (transparent)
            {
                params.flags |= bits;
            } else
            {
                params.flags &= ~bits;
            }
            window.setAttributes(params);
            return true;
        } catch (Exception e)
        {
            return false;
        }
    }

    /**
     * 是否全屏
     *
     * @param window
     * @return
     */
    public static boolean isFullScreen(Window window)
    {
        if (window == null)
            return false;

        final WindowManager.LayoutParams params = window.getAttributes();
        if (params == null)
            return false;

        return (params.flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) == WindowManager.LayoutParams.FLAG_FULLSCREEN;
    }
}
