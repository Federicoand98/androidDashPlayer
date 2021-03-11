package Application.Adaption;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import Application.Buffer.DataSegmentBuffer;

public class InfoPoint {
    private static InfoPoint instance = null;

    public static InfoPoint getInfoPoint() {
        if(instance == null)
            instance = new InfoPoint();

        return instance;
    }

    // LT 700000 --- HT 1500000
    private long LT = 1000000;
    private long HT = 3000000;
    private Double downloadSpeed;
    private int bufferSize;
    private long lastElapsed;
    private DataSegmentBuffer buffer;
    private double ess;
    private DisplayMetrics metrics;

    private InfoPoint() {
        metrics = Resources.getSystem().getDisplayMetrics();
        this.downloadSpeed = (double)0;
        this.bufferSize = 104857600;
    }

    public double getLastDownloadSpeed() {
        synchronized(this.downloadSpeed) {
            return downloadSpeed;
        }
    }

    public void updateDownloadSpeed(double downloadSpeed) {
        synchronized(this.downloadSpeed) {
            this.downloadSpeed = downloadSpeed;
        }
    }

    public DisplayMetrics getScreenDimension() {
        return metrics;
    }

    public int getBufferSize() {
        return this.bufferSize;
    }

    public void setLastElapsed(long elapsed) {
        this.lastElapsed = elapsed;
    }

    public long getLastElapsed() {
        return this.lastElapsed;
    }

    public void attachBuffer(DataSegmentBuffer buffer) {
        this.buffer = buffer;
    }

    public int getBufferByteOccupation() {
        return this.buffer.lenght();
    }

    public void setLastESS(double ess) {
        this.ess = ess;
    }

    public double getLastESS() {
        return this.ess;
    }

    public long getLT() {
        return LT;
    }

    public long getHT() {
        return HT;
    }

}
