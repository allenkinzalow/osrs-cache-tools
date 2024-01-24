package net.runelite.cache.definitions.loaders.sound;

import net.runelite.cache.definitions.sound.SoundEffectTrackDefinition;
import net.runelite.cache.definitions.sound.InstrumentDefinition;
import net.runelite.cache.io.InputStream;

public class SoundEffectTrackLoader
{
    public SoundEffectTrackDefinition load(byte[] b)
    {
        SoundEffectTrackDefinition soundEffect = new SoundEffectTrackDefinition();
        InputStream in = new InputStream(b);

        load(soundEffect, in);

        return soundEffect;
    }

    private void load(SoundEffectTrackDefinition soundEffect, InputStream in)
    {
        for (int i = 0; i < 10; ++i)
        {
            int volume = in.readUnsignedByte();
            if (volume != 0)
            {
                in.setOffset(in.getOffset() - 1);

                InstrumentLoader instrumentLoader = new InstrumentLoader();
                InstrumentDefinition instrument = instrumentLoader.load(in);

                soundEffect.instruments[i] = instrument;
            }
        }

        soundEffect.start = in.readUnsignedShort();
        soundEffect.end = in.readUnsignedShort();
    }
}