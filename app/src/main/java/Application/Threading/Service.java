package Application.Threading;

public interface Service {

    public void start();
    public void shutdown();
    public boolean isRunning();
}
