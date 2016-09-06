package mrwint.gbtasgen.segment.pokemon.gen1.rta;

import mrwint.gbtasgen.metric.pokemon.gen1.Gen1CheckDVMetric;
import mrwint.gbtasgen.move.Move;
import mrwint.gbtasgen.move.pokemon.ChangeOptionsMove;
import mrwint.gbtasgen.move.pokemon.gen1.OverworldInteract;
import mrwint.gbtasgen.segment.Segment;
import mrwint.gbtasgen.segment.pokemon.TextSegment;
import mrwint.gbtasgen.segment.pokemon.WalkToSegment;
import mrwint.gbtasgen.segment.util.*;

public class ChooseSquirtle extends SeqSegment {

    @Override
    public void execute() {
        seq(new MoveSegment(ChangeOptionsMove.get(false))); // set options
        seq(new WalkToSegment(7, 1)); // go downstairs
        seq(new WalkToSegment(4, 7)); // leave house
        seq(new WalkToSegment(3, 8, false)); // leave house
        seq(new WalkToSegment(10, 1)); // walk into grass

        seq(new TextSegment()); // OAK: don't go out
        // oak in grass
        seq(new SkipTextsSegment(6));
        // go to oak's house
        seq(new SkipTextsSegment(18));

        seq(new WalkToSegment(7, 3, false)); // walk to ball
        seqMove(new OverworldInteract(3)); // Squirtle ball

        seq(Segment.press(Move.B)); // cancel dex
        seq(Segment.press(Move.A)); // cancel dex

        // chose mon text
        seq(new SkipTextsSegment(1)); // do you want?
        seq(new SkipTextsSegment(1, true)); // want!
        seq(new SkipTextsSegment(1)); // energetic

        seq(new TextSegment(Move.B));
        seq(new SkipTextsSegment(1)); // want to give a nick
        seqUnbounded(new TextSegment(Move.B, true)); // to Squirtle?
        seqUnbounded(Segment.press(Move.A)); // (yes)
        seqSkipInputUnbounded(1); // wait to input "A"
        seqUnbounded(Segment.press(Move.A)); // "A"
        seq(new DelayMoveSegment(new DelayMoveSegment.PressButtonFactory(Move.START), new CheckMetricSegment(new Gen1CheckDVMetric(12, 0, 7, 10, 0))));

        seq(new SkipTextsSegment(2)); // rival choose mon
        seq(new WalkToSegment(5, 6)); // try leaving
        seq(new SkipTextsSegment(4)); // rival challenges you

    }
}