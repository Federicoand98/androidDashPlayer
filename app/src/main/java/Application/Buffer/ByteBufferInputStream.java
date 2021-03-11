package Application.Buffer;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class ByteBufferInputStream extends InputStream {

    private ByteBuffer buffer;

    public ByteBufferInputStream(ByteBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public int read() throws IOException {
        if(buffer == null)
            throw new IOException("can't read from null buffer");
        if(buffer.remaining() <= 0)
            return -1;

        return buffer.get();
    }

    @Override
    public int read(byte[] dst) throws IOException {
        if(buffer == null)
            throw new IOException("can't read from null buffer");
        if(dst == null)
            throw new NullPointerException();

        int toRead = buffer.remaining();
        int readable = dst.length;
        if(toRead > readable)
            toRead = readable;
        if(toRead == 0)
            return -1;

        buffer.get(dst);
        return toRead;
    }

    @Override
    public int read(byte[] dst, int off, int len) throws IOException {
        if(buffer == null)
            throw new IOException("can't read from null buffer");
        if(dst == null)
            throw new NullPointerException();
        else if(off < 0 || len < 0 || len > dst.length - off)
            throw new IndexOutOfBoundsException();
        else if(len == 0)
            return 0;

        int toRead = buffer.remaining();
        if(toRead > len)
            toRead = len;
        if(toRead == 0)
            return -1;

        buffer.get(dst, off, len);
        return toRead;
    }
}
