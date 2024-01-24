package net.runelite.cache.definitions.sound;

public class SoundEffectTrackDefinition
{
    public int start;
    public InstrumentDefinition[] instruments = new InstrumentDefinition[10];
    public int end;

    public final byte[] mix()
    {
        int var2;
        int var1 = 0;

        for (var2 = 0; var2 < 10; ++var2)
        {
            if (this.instruments[var2] == null || this.instruments[var2].duration + this.instruments[var2].offset <= var1)
            {
                continue;
            }

            var1 = this.instruments[var2].duration + this.instruments[var2].offset;
        }

        if (var1 == 0)
        {
            return new byte[0];
        }

        var2 = var1 * 22050 / 1000;

        byte[] var3 = new byte[var2];

        for (int i = 0; i < 10; ++i)
        {
            if (this.instruments[i] == null)
            {
                continue;
            }

            int var5 = this.instruments[i].duration * 22050 / 1000;
            int var6 = this.instruments[i].offset * 22050 / 1000;

            int[] var7 = this.instruments[i].synthesize(var5, this.instruments[i].duration);

            for (int j = 0; j < var5; ++j)
            {
                int var9 = (var7[j] >> 8) + var3[j + var6];

                if ((var9 + 128 & -256) != 0)
                {
                    var9 = var9 >> 31 ^ 127;
                }

                var3[j + var6] = (byte) var9;
            }
        }

        return var3;
    }
}