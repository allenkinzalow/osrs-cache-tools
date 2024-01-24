package net.runelite.cache.definitions.loaders.sound;

import net.runelite.cache.definitions.sound.AudioEnvelopeDefinition;
import net.runelite.cache.io.InputStream;

public class AudioEnvelopeLoader
{
    public AudioEnvelopeDefinition load(InputStream in)
    {
        AudioEnvelopeDefinition audioEnvelope = new AudioEnvelopeDefinition();

        load(audioEnvelope, in);

        return audioEnvelope;
    }

    private void load(AudioEnvelopeDefinition audioEnvelope, InputStream in)
    {
        audioEnvelope.form = in.readUnsignedByte();
        audioEnvelope.start = in.readInt();
        audioEnvelope.end = in.readInt();
        this.loadSegments(audioEnvelope, in);
    }

    public final void loadSegments(AudioEnvelopeDefinition audioEnvelope, InputStream in)
    {
        audioEnvelope.segments = in.readUnsignedByte();
        audioEnvelope.durations = new int[audioEnvelope.segments];
        audioEnvelope.phases = new int[audioEnvelope.segments];

        for (int i = 0; i < audioEnvelope.segments; ++i)
        {
            audioEnvelope.durations[i] = in.readUnsignedShort();
            audioEnvelope.phases[i] = in.readUnsignedShort();
        }

    }
}