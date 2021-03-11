package Application.Buffer;

import Application.Network.DataSegment;

public interface DataSegmentBuffer {
    public boolean put(DataSegment dataSegment) throws InterruptedException;
    //public DecodedSegment<?> take() throws InterruptedException;
    public DataSegment peekFirstElement() throws InterruptedException;
    //public boolean switchElement(DecodedSegment<?> decodedSegment) throws InterruptedException;

    public void clear();
    public int lenght();
    public int capacity();
    public void setBlocking(boolean blocking);
}
