package com.kunfei.bookshelf.view.popupwindow;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kunfei.bookshelf.R;
import com.kunfei.bookshelf.service.ReadAloudService;
import com.monke.mprogressbar.MHorProgressBar;
import com.monke.mprogressbar.OnProgressListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyReadBottomMenu extends FrameLayout {

    @BindView(R.id.vw_bg)
    View vwBg;
    /*
    @BindView(R.id.fab_read_aloud_timer)
    FloatingActionButton fabReadAloudTimer;
    @BindView(R.id.tv_read_aloud_timer)
    TextView tvReadAloudTimer;
    @BindView(R.id.ll_read_aloud_timer)
    LinearLayout llReadAloudTimer;
    @BindView(R.id.fabReadAloud)
    FloatingActionButton fabReadAloud;
    @BindView(R.id.fabAutoPage)
    FloatingActionButton fabAutoPage;
    @BindView(R.id.fabReplaceRule)
    FloatingActionButton fabReplaceRule;
    */
    @BindView(R.id.fabNightTheme)
    FloatingActionButton fabNightTheme;


    @BindView(R.id.tv_pre)
    TextView tvPre;
    @BindView(R.id.hpb_read_progress)
    MHorProgressBar hpbReadProgress;
    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.ll_catalog)
    LinearLayout llCatalog;
    @BindView(R.id.ll_adjust)
    LinearLayout llAdjust;
    @BindView(R.id.ll_font)
    LinearLayout llFont;
    @BindView(R.id.ll_change_source)
    LinearLayout llChangeSource;
    @BindView(R.id.ll_setting)
    LinearLayout llSetting;
    @BindView(R.id.llNavigationBar)
    LinearLayout llNavigationBar;
    @BindView(R.id.ll_download)
    LinearLayout llDownload;
    @BindView(R.id.vwNavigationBar)
    View vwNavigationBar;

    private Callback callback;

    public MyReadBottomMenu(Context context) {
        super(context);
        init(context);
    }

    public MyReadBottomMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyReadBottomMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_pop_read_menu, this);
        ButterKnife.bind(this, view);
        vwBg.setOnClickListener(null);
        vwNavigationBar.setOnClickListener(null);
    }

    public void setNavigationBarHeight(int height) {
        vwNavigationBar.getLayoutParams().height = height;
    }

    public void setListener(Callback callback) {
        this.callback = callback;
        bindEvent();
    }

    private void bindEvent() {
       // llReadAloudTimer.setOnClickListener(view -> callback.dismiss());
        // .setOnClickListener(view -> callback.dismiss());

        //阅读进度
        hpbReadProgress.setProgressListener(new OnProgressListener() {
            @Override
            public void moveStartProgress(float dur) {

            }

            @Override
            public void durProgressChange(float dur) {

            }

            @Override
            public void moveStopProgress(float dur) {
                int realDur = (int) Math.ceil(dur);
                if (hpbReadProgress.getDurProgress() != realDur)
                    hpbReadProgress.setDurProgress(realDur);
                callback.skipToPage(realDur);
            }

            @Override
            public void setDurProgress(float dur) {

            }
        });




        //夜间模式
        fabNightTheme.setOnClickListener(view -> callback.setNightTheme());
        fabNightTheme.setOnLongClickListener(view -> {
            callback.toast(R.string.night_theme);
            return true;
        });

        //上一章
        tvPre.setOnClickListener(view -> callback.skipPreChapter());

        //下一章
        tvNext.setOnClickListener(view -> callback.skipNextChapter());

        //目录
        llCatalog.setOnClickListener(view -> callback.openChapterList());

        //调节
        llAdjust.setOnClickListener(view -> callback.openAdjust());

        //界面
        llFont.setOnClickListener(view -> callback.openReadInterface());

        //换源
        llChangeSource.setOnClickListener(view -> callback.openChangeSource());

        //下载
        llDownload.setOnClickListener(view -> callback.openDownload());

        //设置
        llSetting.setOnClickListener(view -> callback.openMoreSetting());

        //tvReadAloudTimer.setOnClickListener(null);
    }

    public void setFabReadAloudImage(int id) {
        //fabReadAloud.setImageResource(id);
    }

    public void setReadAloudTimer(boolean visibility) {
        if (visibility) {
            //llReadAloudTimer.setVisibility(VISIBLE);
        } else {
           // llReadAloudTimer.setVisibility(GONE);
        }
    }

    public void setReadAloudTimer(String text) {
        //tvReadAloudTimer.setText(text);
    }

    public void setFabReadAloudText(String text) {
       // fabReadAloud.setContentDescription(text);
    }

    public MHorProgressBar getReadProgress() {
        return hpbReadProgress;
    }

    public void setTvPre(boolean enable) {
        tvPre.setEnabled(enable);
    }

    public void setTvNext(boolean enable) {
        tvNext.setEnabled(enable);
    }

    public void setAutoPage(boolean autoPage) {
        if (autoPage) {
           // fabAutoPage.setImageResource(R.drawable.ic_auto_page_stop);
           // fabAutoPage.setContentDescription(getContext().getString(R.string.auto_next_page_stop));
        } else {
           // fabAutoPage.setImageResource(R.drawable.ic_auto_page);
           // fabAutoPage.setContentDescription(getContext().getString(R.string.auto_next_page));
        }
    }

    public void setFabNightTheme(boolean isNightTheme) {
        if (isNightTheme) {
            fabNightTheme.setImageResource(R.drawable.ic_daytime);
        } else {
            fabNightTheme.setImageResource(R.drawable.ic_moon);
        }
    }

    public interface Callback {
        void skipToPage(int page);

        void onMediaButton();

        void autoPage();

        void setNightTheme();

        void skipPreChapter();

        void skipNextChapter();

        void openTTS();

        void openChangeSource();

        void openDownload();

        void openChapterList();

        void openAdjust();

        void openReadInterface();

        void openMoreSetting();

        void toast(int id);

        void dismiss();
    }

}
