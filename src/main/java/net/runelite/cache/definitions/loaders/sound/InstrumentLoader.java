package net.runelite.cache.definitions.loaders.sound;

import net.runelite.cache.definitions.sound.InstrumentDefinition;
import net.runelite.cache.definitions.sound.AudioEnvelopeDefinition;
import net.runelite.cache.io.InputStream;

public class InstrumentLoader
{
    private final AudioEnvelopeLoader aeLoader = new AudioEnvelopeLoader();
    private final SoundEffectLoader seLoader = new SoundEffectLoader();

    public InstrumentDefinition load(InputStream in)
    {
        InstrumentDefinition instrument = new InstrumentDefinition();

        load(instrument, in);

        return instrument;
    }

    private void load(InstrumentDefinition instrument, InputStream in)
    {
        instrument.pitch = aeLoader.load(in);
        instrument.volume = aeLoader.load(in);
        int volume = in.readUnsignedByte();
        if (volume != 0)
        {
            in.setOffset(in.getOffset() - 1);
            instrument.pitchModifier = aeLoader.load(in);
            instrument.pitchModifierAmplitude = aeLoader.load(in);
        }

        volume = in.readUnsignedByte();
        if (volume != 0)
        {
            in.setOffset(in.getOffset() - 1);
            instrument.volumeMultiplier = aeLoader.load(in);
            instrument.volumeMultiplierAmplitude = aeLoader.load(in);
        }

        volume = in.readUnsignedByte();
        if (volume != 0)
        {
            in.setOffset(in.getOffset() - 1);
            instrument.release = aeLoader.load(in);
            instrument.field1175 = aeLoader.load(in);
        }

        for (int i = 0; i < 10; ++i)
        {
            int vol = in.readUnsignedShortSmart();
            if (vol == 0)
            {
                break;
            }

            instrument.oscillatorVolume[i] = vol;
            instrument.oscillatorPitch[i] = in.readShortSmart();
            instrument.oscillatorDelays[i] = in.readUnsignedShortSmart();
        }

        instrument.delayTime = in.readUnsignedShortSmart();
        instrument.delayDecay = in.readUnsignedShortSmart();
        instrument.duration = in.readUnsignedShort();
        instrument.offset = in.readUnsignedShort();
        instrument.filterEnvelope = new AudioEnvelopeDefinition();
        instrument.filter = seLoader.load(in, instrument.filterEnvelope);
    }
}