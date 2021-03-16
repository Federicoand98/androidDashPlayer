package Application.Threading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class AbstractService implements Runnable, Service {

    private boolean isRunning;
    private Lock serviceLock;

    public AbstractService() {
        this.isRunning = false;
        this.serviceLock = new ReentrantLock();
    }

    protected abstract void service();
    public abstract void shutdown();

    @Override
    public final void start() {
        new Thread(this).start();
    }

    @Override
    public final void run() {

        serviceLock.lock();
        try {
            isRunning = true;
        } finally {serviceLock.unlock();}

        service();

        serviceLock.lock();
        try {
            isRunning = false;
        } finally {serviceLock.unlock();}

    }

    @Override
    public final boolean isRunning() {
        return isRunning;
    }
}
