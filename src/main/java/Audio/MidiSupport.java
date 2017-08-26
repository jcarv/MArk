package Audio;
import java.io.*;
import javax.sound.midi.*;

public class MidiSupport {
    File midiFile;
    Sequencer	sm_sequencer = null;
    Synthesizer	sm_synthesizer = null;
    Sequence	sequence;
    Soundbank currentSoundbank;
    long position;
    long currentTime;
    int currentTick;
    double volume;

    public MidiSupport() {
        try {
            sm_synthesizer = MidiSystem.getSynthesizer();
        } catch (MidiUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        currentSoundbank = sm_synthesizer.getDefaultSoundbank();
        position = 0;
        currentTime = 0;
        volume = 0.0;
    }

    public MidiSupport(File midi) {
        try {
            sm_synthesizer = MidiSystem.getSynthesizer();
        } catch (MidiUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        currentSoundbank = sm_synthesizer.getDefaultSoundbank();
        midiFile = midi;
        position = 0;
        currentTime = 0;
        volume = 0.0;
    }

    public void setFile(File midi) {
        midiFile = midi;
        try
        {
            sequence = MidiSystem.getSequence(midiFile);
        }
        catch (InvalidMidiDataException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void setSequence(Sequence s) {
        sequence = s;
    }

    public float getTickValue() {
        //Note: The duration of a tick can vary between sequences but not within a sequence
        //The size of a tick is given in one of two types of units:
        //Pulses (ticks) per quarter note, abbreviated as PPQ
        //Ticks per frame, also known as SMPTE time code (a standard adopted by the Society of Motion Picture and Television Engineers)

        //If the unit is PPQ, the size of a tick is expressed as a fraction of a quarter note, which is a relative, not absolute, time value.
        //A quarter note is a musical duration value that often corresponds to one beat of the music (a quarter of a measure in 4/4 time).
        //The duration of a quarter note is dependent on the tempo, which can vary during the course of the music if the sequence contains tempo-change events.
        //So if the sequence's timing increments (ticks) occur, say 96 times per quarter note, each event's timing value measures that event's position in musical terms,
        //not as an absolute time value.

        //On the other hand, in the case of SMPTE, the units measure absolute time, and the notion of tempo is inapplicable.
        //There are actually four different SMPTE conventions available, which refer to the number of motion-picture frames per second.
        //The number of frames per second can be 24, 25, 29.97, or 30. With SMPTE time code, the size of a tick is expressed as a fraction of a frame.

        //The Java Sound API's definition of timing in a sequence mirrors that of the Standard MIDI Files specification.
        //However, there's one important difference. The tick values contained in MidiEvents measure cumulative time, rather than delta time.
        //In a standard MIDI file, each event's timing information measures the amount of time elapsed since the onset of the previous event in the sequence.
        //This is called delta time. But in the Java Sound API, the ticks aren't delta values; they're the previous event's time value plus the delta value.
        //In other words, in the Java Sound API the timing value for each event is always greater than that of the previous event in the sequence
        //(or equal, if the events are supposed to be simultaneous). Each event's timing value measures the time elapsed since the beginning of the sequence.
        int resolution = sequence.getResolution();
        System.out.println("Div type: " + sequence.getDivisionType());

        System.out.println("PPQ: " + Sequence.PPQ);
        System.out.println("SMPTE 24: " + Sequence.SMPTE_24);
        System.out.println("SMPTE 25: " + Sequence.SMPTE_25);
        System.out.println("SMPTE 30: " + Sequence.SMPTE_30);
        System.out.println("SMPTE 30DROP: " + Sequence.SMPTE_30DROP);

        return 0;
    }

    public int getTotalTick() {
        int totalTick = 0;
        if(sm_sequencer != null) {
            totalTick = (int)sm_sequencer.getTickLength();
        }

        return totalTick;
    }

    public int getCurrentTick() {
        if(sm_sequencer != null && sm_sequencer.isRunning()) {
            currentTick = (int)sm_sequencer.getTickPosition();
        }

        return currentTick;
    }

    public void setCurrentTick(long tick) {
        if(sm_sequencer != null) {
            sm_sequencer.setTickPosition(tick);
        }
    }

    public long getTotalTime() {
        long totalTime = 0;
        if(sm_sequencer != null) {
            totalTime = sm_sequencer.getMicrosecondLength();
        }

        return totalTime;
    }

    public long getCurrentTime() {
        if(sm_sequencer != null && sm_sequencer.isRunning()) {
            currentTime = sm_sequencer.getMicrosecondPosition();
        }

        return currentTime;
    }

    public Sequence getSequence() {
        return sequence;
    }
}
