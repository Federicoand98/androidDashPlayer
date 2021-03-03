package Adaption;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.chunk.MediaChunk;

import java.util.List;

public class AlwaysLowestFormat implements FormatEvaluator {


    @Override
    public void enable() {

    }

    @Override
    public void disable() {

    }

    @Override
    public void evaluate(List<? extends MediaChunk> queue, long playbackPositionUs, Format[] formats, Evaluation evaluation) {
        evaluation.format = formats[0];
    }
}
