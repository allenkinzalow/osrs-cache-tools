/*
 * Copyright (c) 2016-2017, Adam <Adam@sigterm.info>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.cache.definitions.exporters;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import net.runelite.cache.definitions.SpriteDefinition;
import net.runelite.cache.definitions.sound.SoundEffectDefinition;
import net.runelite.cache.definitions.sound.SoundEffectTrackDefinition;

public class SoundEffectExporter
{
    private final SoundEffectTrackDefinition soundEffect;

    public SoundEffectExporter(SoundEffectTrackDefinition soundEffect)
    {
        this.soundEffect = soundEffect;
    }

    public ByteArrayOutputStream export()
    {
        byte[] data = this.soundEffect.mix();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            AudioFormat audioFormat = new AudioFormat(22_050, 8, 1, true, false);
            AudioInputStream ais = new AudioInputStream(new ByteArrayInputStream(data), audioFormat, data.length);

            AudioSystem.write(ais, AudioFileFormat.Type.WAVE, bos);
        } catch (Exception e) {
            System.out.println(e);
        }
        return bos;
    }

    public void exportTo(File file) throws IOException
    {
        byte[] data = export().toByteArray();
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(data);
        }
    }
}
