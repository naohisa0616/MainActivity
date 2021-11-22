package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.os.Bundle;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private SurfaceView mSurfaceView;
    private SurfaceHolder mHolder;
    private MediaPlayer mMediaPlayer;

    private final SurfaceHolder.Callback mCallback = new SurfaceHolder.Callback() {

        /** SurfaceViewが生成された時に呼び出される */
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            final String path = "http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8";
            try {
                // MediaPlayerを生成
                mMediaPlayer = new MediaPlayer();
                // 動画ファイルをMediaPlayerに読み込ませる
                mMediaPlayer.setDataSource(path);
                // 読み込んだ動画ファイルを画面に表示する
                mMediaPlayer.setDisplay(holder);
                //動画を再生
                mMediaPlayer.prepare();
                mMediaPlayer.start();

            } catch (IllegalArgumentException e) {
                e.printStackTrace();

            } catch (SecurityException e) {
                e.printStackTrace();

            } catch (IllegalStateException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();

            } finally {

            }
        }

        //
        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        /** SurfaceViewが終了した時に呼び出される */
        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            if (mMediaPlayer != null) {
                // システムリソースを解放
                mMediaPlayer.release();
                mMediaPlayer = null;
            }
        }

    };

    @Override
    //画面表示時に実行
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSurfaceView = (SurfaceView) findViewById(R.id.surfaceview);
        // SurfaceViewにコールバックを設定（SufaceViewが生成された時、状態が変化した時、終了した時のイベントが受け取れるようになる）
        mHolder = mSurfaceView.getHolder();
        mHolder.addCallback(mCallback);
    }
}