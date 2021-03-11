package Application.Adaption;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.chunk.MediaChunk;

import java.util.List;
import java.util.Random;

public class RandomFormat implements FormatEvaluator {

    private final Random random;

    public RandomFormat() {
        this.random = new Random();
    }

    @Override
    public void enable() {

    }

    @Override
    public void disable() {

    }

    @Override
    public void evaluate(List<? extends MediaChunk> queue, long playbackPositionUs, Format[] formats, Evaluation evaluation) {
        Format newFormat = formats[random.nextInt(formats.length)];

        if(evaluation.format != null && !evaluation.format.id.equals(newFormat.id)) {
            evaluation.trigger = TRIGGER_ADAPTIVE;
        }

        evaluation.format = newFormat;
    }
}
