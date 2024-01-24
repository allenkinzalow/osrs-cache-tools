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
package net.runelite.cache;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import net.runelite.cache.definitions.SpriteDefinition;
import net.runelite.cache.definitions.exporters.SoundEffectExporter;
import net.runelite.cache.definitions.exporters.SpriteExporter;
import net.runelite.cache.definitions.loaders.SpriteLoader;
import net.runelite.cache.definitions.loaders.sound.SoundEffectTrackLoader;
import net.runelite.cache.definitions.providers.SoundEffectProvider;
import net.runelite.cache.definitions.sound.SoundEffectTrackDefinition;
import net.runelite.cache.fs.Archive;
import net.runelite.cache.fs.Index;
import net.runelite.cache.fs.Storage;
import net.runelite.cache.fs.Store;

public class SoundEffectManager implements SoundEffectProvider
{
    private final Store store;
    private final HashMap<Integer, SoundEffectTrackDefinition> soundEffects = new HashMap<>();

    public SoundEffectManager(Store store)
    {
        this.store = store;
    }

    public void load() throws IOException
    {
        Storage storage = store.getStorage();
        Index index = store.getIndex(IndexType.SOUNDEFFECTS);

        for (Archive a : index.getArchives())
        {
            byte[] contents = a.decompress(storage.loadArchive(a));

            SoundEffectTrackLoader setLoader = new SoundEffectTrackLoader();
            SoundEffectTrackDefinition soundEffect = setLoader.load(contents);

            soundEffects.put(a.getArchiveId(), soundEffect);
        }
    }

    public Collection<SoundEffectTrackDefinition> getSoundEffects()
    {
        return Collections.unmodifiableCollection(soundEffects.values());
    }

    public int export(File outDir) throws IOException
    {
        for (Integer key : soundEffects.keySet())
        {
            if (key == 7700) {
                System.out.println("Exporting sound: " + key);
                SoundEffectTrackDefinition soundEffect = soundEffects.get(key);
                SoundEffectExporter exporter = new SoundEffectExporter(soundEffect);
                File out = new File(outDir, key + ".wav");

                exporter.exportTo(out);
            }
        }
        return soundEffects.size();
    }

    @Override
    public SoundEffectTrackDefinition provide(int soundId)
    {
        return soundEffects.get(soundId);
    }
}
