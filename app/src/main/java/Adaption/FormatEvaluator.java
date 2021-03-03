package Adaption;

import android.os.Handler;
        import android.util.Log;


import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.chunk.MediaChunk;

import java.util.HashMap;
        import java.util.List;import java.util.Random;

/**
 * Selects from a number of available formats during playback.
 */
public interface FormatEvaluator {

    /**
     * The trigger for the initial format selection.
     */
    static final int TRIGGER_INITIAL = 0;
    /**
     * The trigger for a format selection that was triggered by the user.
     */
    static final int TRIGGER_MANUAL = 1;
    /**
     * The trigger for an adaptive format selection.
     */
    static final int TRIGGER_ADAPTIVE = 2;
    /**
     * Implementations may define custom trigger codes greater than or equal to this value.
     */
    static final int TRIGGER_CUSTOM_BASE = 10000;

    /**
     * Enables the evaluator.
     */
    void enable();

    /**
     * Disables the evaluator.
     */
    void disable();

    /**
     * Update the supplied evaluation.
     * <p>
     * When the method is invoked, {@code evaluation} will contain the currently selected
     * format (null for the first evaluation), the most recent trigger (TRIGGER_INITIAL for the
     * first evaluation) and the current queue size. The implementation should update these
     * fields as necessary.
     * <p>
     * The trigger should be considered "sticky" for as long as a given representation is selected,
     * and so should only be changed if the representation is also changed.
     *
     * @param queue A read only representation of the currently buffered {@link MediaChunk}s.
     * @param playbackPositionUs The current playback position.
     * @param formats The formats from which to select, ordered by decreasing bandwidth.
     * @param evaluation The evaluation.
     */
    // TODO: Pass more useful information into this method, and finalize the interface.
    void evaluate(List<? extends MediaChunk> queue, long playbackPositionUs, Format[] formats, Evaluation evaluation);

    /**
     * A format evaluation.
     */
    public static final class Evaluation {

        /**
         * The desired size of the queue.
         */
        public int queueSize;

        /**
         * The sticky reason for the format selection.
         */
        public int trigger;

        /**
         * The selected format.
         */
        public Format format;

        public Evaluation() {
            trigger = TRIGGER_INITIAL;
        }
    }
}