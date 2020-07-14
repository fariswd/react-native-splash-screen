package org.devio.rn.splashscreen;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.time.OffsetTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * SplashScreen
 * 启动屏
 * from：http://www.devio.org
 * Author:CrazyCodeBoy
 * GitHub:https://github.com/crazycodeboy
 * Email:crazycodeboy@gmail.com
 */
public class SplashScreen {
    private static Dialog mSplashDialog;
    private static WeakReference<Activity> mActivity;

    /**
     * 打开启动屏
     */
    public static void show(final Activity activity, final int themeResId) {
        if (activity == null) return;
        mActivity = new WeakReference<Activity>(activity);
        final ProgressBar[] mProgressBar = new ProgressBar[1];
        final ProgressBar[] mProgressBarShow = new ProgressBar[1];
        final int[] mProgressStatus = {0};
        final Handler mHandler = new Handler();

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!activity.isFinishing()) {
                    mSplashDialog = new Dialog(activity, themeResId);
                    mSplashDialog.setContentView(R.layout.launch_screen);
                    mSplashDialog.setCancelable(false);

                    RelativeLayout layout = (RelativeLayout) mSplashDialog.findViewById(R.id.splashbg);
                    TextView quote = (TextView) mSplashDialog.findViewById(R.id.quotes);
                    layout.setBackgroundResource(R.drawable.xbg1);
                    quote.setText(R.string.quote1);

                    Date d = new Date();
                    CharSequence s  = DateFormat.format("s", d.getTime());
                    int nums = Integer.parseInt(s.toString());

                    if(nums >= 0 && nums < 15) {
                        layout.setBackgroundResource(R.drawable.xbg1);
                        quote.setText(R.string.quote1);
                    } else if (nums >= 15 && nums < 30) {
                        layout.setBackgroundResource(R.drawable.xbg2);
                        quote.setText(R.string.quote2);
                    } else if (nums >= 30 && nums < 15) {
                        layout.setBackgroundResource(R.drawable.xbg3);
                        quote.setText(R.string.quote3);
                    } else {
                        layout.setBackgroundResource(R.drawable.xbg4);
                        quote.setText(R.string.quote4);
                    }
                    mProgressBar[0] = (ProgressBar) mSplashDialog.findViewById(R.id.progressbar);
                    mProgressBarShow[0] = (ProgressBar) mSplashDialog.findViewById(R.id.progressbarshow);

                    mSplashDialog.findViewById(R.id.progressbar);

                    if (!mSplashDialog.isShowing()) {
                        mSplashDialog.show();
                    //     new Thread(new Runnable() {
                    //         @Override
                    //         public void run() {
                    //             mProgressBarShow[0].setVisibility(View.INVISIBLE);
                    //             mProgressBar[0].setVisibility(View.VISIBLE);
                    //             while (mProgressStatus[0] < 50){
                    //                 mProgressStatus[0]++;
                    //                 android.os.SystemClock.sleep(60);
                    //                 mHandler.post(new Runnable() {
                    //                     @Override
                    //                     public void run() {
                    //                         mProgressBar[0].setProgress(mProgressStatus[0]);
                    //                     }
                    //                 });
                    //             }
                    //         }
                    //     }).start();
                    }
                }
            }
        });
    }

    /**
     * 打开启动屏
     */
    public static void show(final Activity activity, final boolean fullScreen) {
        int resourceId = fullScreen ? R.style.SplashScreen_Fullscreen : R.style.SplashScreen_SplashTheme;

        show(activity, resourceId);
    }

    /**
     * 打开启动屏
     */
    public static void show(final Activity activity) {
        show(activity, false);
    }

    /**
     * 关闭启动屏
     */
    public static void hide(Activity activity) {
        if (activity == null) {
            if (mActivity == null) {
                return;
            }
            activity = mActivity.get();
        }

        if (activity == null) return;

        final Activity _activity = activity;

        _activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mSplashDialog != null && mSplashDialog.isShowing()) {
                    boolean isDestroyed = false;

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        isDestroyed = _activity.isDestroyed();
                    }

                    if (!_activity.isFinishing() && !isDestroyed) {
                        mSplashDialog.dismiss();
                    }
                    mSplashDialog = null;
                }
            }
        });
    }
}
