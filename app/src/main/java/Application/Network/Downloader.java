package Application.Network;

public class Downloader implements Runnable {
    @Override
    public void run() {
        for(;;) {
            System.out.println("CIAO");
        }
    }

    /*
    Per stoppare i thread ddevo:
    ad esempio nel controller

    Handler handler = new Handler();

    Downloader downloader = new Downloader();
    downloader.run();

    handler.removeCallbacks(downloader);
     */
}
