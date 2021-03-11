package Application.Utils;

import java.util.Vector;

import jLibdash.dash.mpd.IAdaptationSet;
import jLibdash.dash.mpd.IBaseUrl;
import jLibdash.dash.mpd.IMPD;
import jLibdash.dash.mpd.IPeriod;


public interface BaseUrlResolver {

    public static Vector<IBaseUrl> resolveBaseUrl(IMPD mpd, IPeriod period,
                                                  IAdaptationSet adaptationSet, int mpdBaseUrl, int periodBaseUrl,
                                                  int  adaptationSetBaseUrl) {

        Vector<IBaseUrl> urls = new Vector<IBaseUrl>();
        Vector<IBaseUrl> curr = null;

        curr = mpd.getBaseURLs();
        if(curr.size() > 0) {
            if(curr.size() > mpdBaseUrl)
                urls.add(curr.get(mpdBaseUrl));
            else
                urls.add(curr.get(0));
        }

        curr = period.getBaseURLs();
        if(curr.size() > 0) {
            if(curr.size() > periodBaseUrl)
                urls.add(curr.get(periodBaseUrl));
            else
                urls.add(curr.get(0));
        }

        curr = adaptationSet.getBaseURLs();
        if(curr.size() > 0) {
            if(curr.size() > adaptationSetBaseUrl)
                urls.add(curr.get(adaptationSetBaseUrl));
            else
                urls.add(curr.get(0));
        }

        if(urls.size() > 0) {

            String url = urls.get(0).getUrl();
            if(!url.substring(0, 7).equalsIgnoreCase("http://") && !url.substring(0, 8).equalsIgnoreCase("https://"))
                urls.add(0, mpd.getMPDPathBaseUrl());

        }
        else {
            urls.add(mpd.getMPDPathBaseUrl());
        }

        return urls;
    }
}
